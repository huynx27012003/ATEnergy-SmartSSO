package openjoe.smart.sso.base.constant;

/**
 * SSO base constants
 * * @author huynx */
public class BaseConstant {

    /**
     * Server login path
     */
    public static final String LOGIN_PATH = "/sso/login";

    /**
     * Server logout path
     */
    public static final String LOGOUT_PATH = "/sso/logout";

    /**
     * OAuth2 base path
     */
    public static final String AUTH_PATH = "/sso/oauth2";

    /**
     * Permission path
     */
    public static final String PERMISSION_PATH = "/sso/permission";

    /**
     * Client callback parameter name (redirect URI)
     */
    public static final String REDIRECT_URI = "redirectUri";

    /**
     * Client logout callback URI parameter name
     */
    public static final String LOGOUT_URI = "logoutUri";

    /**
     * Single logout callback parameter name
     */
    public static final String LOGOUT_PARAMETER_NAME = "logoutRequest";

    /**
     * Grant type
     */
    public static final String GRANT_TYPE = "grantType";

    /**
     * Client ID
     */
    public static final String CLIENT_ID = "clientId";

    /**
     * Client secret
     */
    public static final String CLIENT_SECRET = "clientSecret";

    /**
     * Access token
     */
    public static final String ACCESS_TOKEN = "accessToken";

    /**
     * Refresh token
     */
    public static final String REFRESH_TOKEN = "refreshToken";

    /**
     * Authorization code (authorization_code flow)
     */
    public static final String AUTH_CODE = "code";

    /**
     * Access token endpoint
     */
    public static final String ACCESS_TOKEN_PATH = BaseConstant.AUTH_PATH + "/access-token";

    /**
        * Refresh token endpoint
        */
    public static final String REFRESH_TOKEN_PATH = BaseConstant.AUTH_PATH + "/refresh-token";
}
