package openjoe.smart.sso.base.entity;

/**
 * Token object returned from server
 * * @author huynx */
public class Token {

    /**
     * Access token
     */
    private String accessToken;
    /**
     * Access token TTL in seconds
     */
    private int expiresIn;
    /**
     * Refresh token
     */
    private String refreshToken;
    /**
     * Refresh token TTL in seconds
     */
    private int refreshExpiresIn;
    /**
     * User information
     */
    private TokenUser tokenUser;

    public Token() {
        super();
    }

    public Token(String accessToken, int expiresIn, String refreshToken, int refreshExpiresIn, TokenUser tokenUser) {
        super();
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.refreshExpiresIn = refreshExpiresIn;
        this.tokenUser = tokenUser;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(int refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public TokenUser getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(TokenUser tokenUser) {
        this.tokenUser = tokenUser;
    }
}
