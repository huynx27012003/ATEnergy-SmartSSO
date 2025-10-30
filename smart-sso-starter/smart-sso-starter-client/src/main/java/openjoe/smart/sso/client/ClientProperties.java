package openjoe.smart.sso.client;

import openjoe.smart.sso.client.constant.ClientConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;

@ConfigurationProperties("smart.sso")
public class ClientProperties {

    /**
     * Server base URL
     */
    private String serverUrl;

    /**
     * Application ID
     */
    private String clientId;

    /**
     * Application secret
     */
    private String clientSecret;

    /**
     * URL patterns to intercept, default matches all
     */
    private String[] urlPatterns = {ClientConstant.URL_FUZZY_MATCH};

    /**
     * URL patterns to exclude
     */
    private String[] excludeUrls;

    /**
     * Filter order, default 10
     */
    private int order = 10;

    /**
     * Client logout path
     */
    private String logoutPath = "/logout";

    /**
     * Client filter container name
     */
    private String name = "clientContainer";

    /**
     * Token name prefix for cookie/header
     */
    private String tokenNamePrefix = "smart-sso-token-";

    /**
     * Whether SPA/H5 mode is enabled (default false)
     */
    private Boolean h5Enabled = false;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String[] getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(String[] urlPatterns) {
        this.urlPatterns = urlPatterns;
    }

    public String[] getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(String[] excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTokenNamePrefix() {
        return tokenNamePrefix;
    }

    public void setTokenNamePrefix(String tokenNamePrefix) {
        this.tokenNamePrefix = tokenNamePrefix;
    }

    public String getLogoutPath() {
        return logoutPath;
    }

    public void setLogoutPath(String logoutPath) {
        this.logoutPath = logoutPath;
    }

    public Boolean getH5Enabled() {
        return h5Enabled;
    }

    public void setH5Enabled(Boolean h5Enabled) {
        this.h5Enabled = h5Enabled;
    }
}
