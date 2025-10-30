package openjoe.smart.sso.client.token.local;

import openjoe.smart.sso.base.entity.ExpirationPolicy;
import openjoe.smart.sso.base.entity.ExpirationWrapper;
import openjoe.smart.sso.base.entity.TokenPermission;
import openjoe.smart.sso.client.token.TokenPermissionStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Local implementation for token permission storage
 * * @author huynx */
public final class LocalTokenPermissionStorage implements TokenPermissionStorage, ExpirationPolicy {
    private final Logger logger = LoggerFactory.getLogger(LocalTokenPermissionStorage.class);
    private final Map<String, ExpirationWrapper<TokenPermission>> tokenPermissionMap = new ConcurrentHashMap<>();

    @Override
    public void create(String accessToken, ExpirationWrapper<TokenPermission> wrapper) {
        tokenPermissionMap.put(accessToken, wrapper);
        logger.debug("Token permission created successfully, accessToken:{}", accessToken);
    }

    @Override
    public ExpirationWrapper<TokenPermission> get(String accessToken) {
        return tokenPermissionMap.get(accessToken);
    }

    @Override
    public void remove(String accessToken) {
        tokenPermissionMap.remove(accessToken);
    }

    @Override
    public void verifyExpired() {
        tokenPermissionMap.forEach((accessToken, wrapper) -> {
            if (wrapper.checkExpired()) {
                remove(accessToken);
                logger.debug("Token permission expired, accessToken:{}", accessToken);
            }
        });
    }
}
