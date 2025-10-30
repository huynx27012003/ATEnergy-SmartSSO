package openjoe.smart.sso.server.manager;

import openjoe.smart.sso.base.constant.BaseConstant;
import openjoe.smart.sso.base.entity.LifecycleManager;
import openjoe.smart.sso.base.entity.TokenUser;
import openjoe.smart.sso.base.util.HttpUtils;
import openjoe.smart.sso.server.entity.CodeContent;
import openjoe.smart.sso.server.entity.TokenContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;


public abstract class AbstractTokenManager implements LifecycleManager<TokenContent> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private int accessTokenTimeout;

    private int refreshTokenTimeout;

    protected final ExecutorService executorService;

    public AbstractTokenManager(int accessTokenTimeout, int refreshTokenTimeout, int threadPoolSize) {
        this.accessTokenTimeout = accessTokenTimeout;
        this.refreshTokenTimeout = refreshTokenTimeout;
        this.executorService = new ThreadPoolExecutor(
                threadPoolSize,
                threadPoolSize,
                3L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000)
        );
    }


    public abstract TokenContent getByAccessToken(String accessToken);


    public abstract void removeByTgt(String tgt);

    public TokenContent create(TokenContent tc) {
        return create(tc.getUserId(), tc.getLogoutUri(), tc);
    }

    public TokenContent create(Long userId, String logoutUri, CodeContent codeContent) {
        String accessToken = "AT-" + UUID.randomUUID().toString().replaceAll("-", "");
        String refreshToken = "RT-" + UUID.randomUUID().toString().replaceAll("-", "");
        TokenContent tc = new TokenContent(accessToken, refreshToken, userId, logoutUri, codeContent.getTgt(), codeContent.getClientId());
        create(refreshToken, tc);
        return tc;
    }

    protected void submitRemoveToken(Set<String> refreshTokenSet) {
        // Store all Futures to wait for completion later
        List<Future<?>> futures = new ArrayList<>();

        refreshTokenSet.forEach(refreshToken -> {
            // Submit client logout tasks and capture Future
            Future<?> future = executorService.submit(() -> {
                try {
                    processRemoveToken(refreshToken);
                } catch (Exception e) {
                    logger.error("Exception occurred while deleting token", e);
                }
            });
            futures.add(future);
        });

        // Wait for all submitted tasks to complete
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                logger.error("Exception occurred while executing token deletion task", e);
            }
        }
    }

    public abstract void processRemoveToken(String refreshToken);

    public abstract Map<String, Set<String>> getClientIdMapByTgt(Set<String> tgtSet);

    protected void sendLogoutRequest(String redirectUri, String accessToken) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(BaseConstant.LOGOUT_PARAMETER_NAME, accessToken);
        HttpUtils.postHeader(redirectUri, headerMap);
    }

    public int getAccessTokenTimeout() {
        return accessTokenTimeout;
    }

    public void setAccessTokenTimeout(int accessTokenTimeout) {
        this.accessTokenTimeout = accessTokenTimeout;
    }

    public int getRefreshTokenTimeout() {
        return refreshTokenTimeout;
    }

    public void setRefreshTokenTimeout(int refreshTokenTimeout) {
        this.refreshTokenTimeout = refreshTokenTimeout;
    }
}
