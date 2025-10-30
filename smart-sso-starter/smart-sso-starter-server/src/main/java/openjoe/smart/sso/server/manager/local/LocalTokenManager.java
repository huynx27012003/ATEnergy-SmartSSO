package openjoe.smart.sso.server.manager.local;

import openjoe.smart.sso.base.entity.ExpirationPolicy;
import openjoe.smart.sso.base.entity.ExpirationWrapper;
import openjoe.smart.sso.server.entity.TokenContent;
import openjoe.smart.sso.server.manager.AbstractTokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Local token manager
 * * @author huynx */
public class LocalTokenManager extends AbstractTokenManager implements ExpirationPolicy {

    private final Logger logger = LoggerFactory.getLogger(LocalTokenManager.class);
    private Map<String, ExpirationWrapper<String>> accessTokenMap = new ConcurrentHashMap<>();
    private Map<String, ExpirationWrapper<TokenContent>> refreshTokenMap = new ConcurrentHashMap<>();
    private Map<String, Set<String>> tgtMap = new ConcurrentHashMap<>();

    public LocalTokenManager(int accessTokenTimeout, int refreshTokenTimeout, int threadPoolSize) {
        super(accessTokenTimeout, refreshTokenTimeout, threadPoolSize);
    }

    @Override
    public void create(String refreshToken, TokenContent tokenContent) {
        ExpirationWrapper<String> atWrapper = new ExpirationWrapper(refreshToken, getAccessTokenTimeout());
        accessTokenMap.put(tokenContent.getAccessToken(), atWrapper);

        ExpirationWrapper<TokenContent> rtWrapper = new ExpirationWrapper(tokenContent, getRefreshTokenTimeout());
        refreshTokenMap.put(refreshToken, rtWrapper);

        tgtMap.computeIfAbsent(tokenContent.getTgt(), a -> new HashSet<>()).add(refreshToken);
        logger.debug("Token created successfully, accessToken:{}, refreshToken:{}", tokenContent.getAccessToken(), refreshToken);
    }

    @Override
    public TokenContent get(String refreshToken) {
        ExpirationWrapper<TokenContent> wrapper = refreshTokenMap.get(refreshToken);
        if (wrapper == null || wrapper.checkExpired()) {
            return null;
        } else {
            return wrapper.getObject();
        }
    }

    @Override
    public TokenContent getByAccessToken(String accessToken) {
        ExpirationWrapper<String> wrapper = accessTokenMap.get(accessToken);
        if (wrapper == null || wrapper.checkExpired()) {
            return null;
        }
        return get(wrapper.getObject());
    }

    @Override
    public void remove(String refreshToken) {
        // Delete refreshToken
        ExpirationWrapper<TokenContent> wrapper = refreshTokenMap.remove(refreshToken);
        if (wrapper == null) {
            return;
        }

        // Delete accessToken
        accessTokenMap.remove(wrapper.getObject().getAccessToken());

        // Remove refreshToken from TGT mapping
        Set<String> refreshTokenSet = tgtMap.get(wrapper.getObject().getTgt());
        if (CollectionUtils.isEmpty(refreshTokenSet)) {
            return;
        }
        refreshTokenSet.remove(refreshToken);
    }

    @Override
    public void removeByTgt(String tgt) {
        // Delete refreshToken set for TGT mapping
        Set<String> refreshTokenSet = tgtMap.remove(tgt);
        if (CollectionUtils.isEmpty(refreshTokenSet)) {
            return;
        }
        submitRemoveToken(refreshTokenSet);
    }

    @Override
    public void processRemoveToken(String refreshToken) {
        // Delete refreshToken
        ExpirationWrapper<TokenContent> wrapper = refreshTokenMap.remove(refreshToken);
        if (wrapper == null) {
            return;
        }
        TokenContent tokenContent = wrapper.getObject();
        if (tokenContent == null) {
            return;
        }

        // Delete accessToken
        accessTokenMap.remove(tokenContent.getAccessToken());

        // Send client logout request
        logger.debug("Send client logout request, accessToken:{}, refreshToken:{}, logoutUri:{}", tokenContent.getAccessToken(), refreshToken, tokenContent.getLogoutUri());
        sendLogoutRequest(tokenContent.getLogoutUri(), tokenContent.getAccessToken());
    }

    @Override
    public Map<String, Set<String>> getClientIdMapByTgt(Set<String> tgtSet) {
        Map<String, Set<String>> clientIdMap = new HashMap<>();
        tgtSet.forEach(tgt -> {
            Set<String> refreshTokenSet = tgtMap.get(tgt);
            Set<String> clientIdSet = new HashSet<>();
            refreshTokenSet.forEach(refreshToken -> {
                ExpirationWrapper<TokenContent> wrapper = refreshTokenMap.get(refreshToken);
                if (wrapper == null) {
                    return;
                }
                TokenContent tokenContent = wrapper.getObject();
                if (tokenContent == null) {
                    return;
                }
                clientIdSet.add(tokenContent.getClientId());
            });
            clientIdMap.put(tgt, clientIdSet);
        });
        return clientIdMap;
    }

    @Override
    public void verifyExpired() {
        accessTokenMap.forEach((accessToken, wrapper) -> {
            if (wrapper.checkExpired()) {
                accessTokenMap.remove(accessToken);
                logger.debug("Token expired, accessToken:{}", accessToken);
            }
        });

        refreshTokenMap.forEach((refreshToken, wrapper) -> {
            if (wrapper.checkExpired()) {
                remove(refreshToken);
                logger.debug("Refresh token expired, accessToken:{}, refreshToken:{}", wrapper.getObject().getAccessToken(), refreshToken);
            }
        });
    }
}
