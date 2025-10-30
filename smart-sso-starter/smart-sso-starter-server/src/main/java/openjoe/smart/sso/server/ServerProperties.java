package openjoe.smart.sso.server;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("smart.sso.server")
public class ServerProperties {

    /*
     * Authorization code TTL, default 10 minutes (seconds)
     */
    private int codeTimeout = 600;

    /*
     * AccessToken TTL, default 30 minutes (seconds)
     */
    private int accessTokenTimeout = 1800;

    /**
     * SSO session TTL, default 2 hours (seconds)
     *
     * Note: refreshToken and TGT share the same TTL
     */
    private int timeout = 7200;

    /**
     * TGT cookie name, consistent with CAS
     */
    private String cookieName = "TGC";

    /*
     * Thread pool size for remote logout notifications
     */
    private int threadPoolSize = 2;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getCodeTimeout() {
        return codeTimeout;
    }

    public void setCodeTimeout(int codeTimeout) {
        this.codeTimeout = codeTimeout;
    }

    public int getAccessTokenTimeout() {
        return accessTokenTimeout;
    }

    public void setAccessTokenTimeout(int accessTokenTimeout) {
        this.accessTokenTimeout = accessTokenTimeout;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }
}
