package openjoe.smart.sso.client.constant;

/** * @author huynx */
public class ClientConstant {

    /**
     * Fuzzy match suffix
     */
    public static final String URL_FUZZY_MATCH = "/*";

    /**
     * Not logged in or expired (no token, or both accessToken and refreshToken are invalid)
     */
    public static final int NO_LOGIN = 10;

    /**
     * accessToken expired but refreshToken valid, used to notify client to refresh token
     */
    public static final int NO_TOKEN = 15;

    /**
     * No permission to access
     */
    public static final int NO_PERMISSION = 20;
}
