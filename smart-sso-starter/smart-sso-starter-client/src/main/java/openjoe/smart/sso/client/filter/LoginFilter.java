package openjoe.smart.sso.client.filter;

import openjoe.smart.sso.base.constant.BaseConstant;
import openjoe.smart.sso.base.entity.Result;
import openjoe.smart.sso.base.entity.Token;
import openjoe.smart.sso.base.entity.TokenPermission;
import openjoe.smart.sso.client.ClientProperties;
import openjoe.smart.sso.client.constant.ClientConstant;
import openjoe.smart.sso.client.token.TokenWrapper;
import openjoe.smart.sso.client.util.ClientContextHolder;
import openjoe.smart.sso.client.util.SSOUtils;
import org.springframework.core.annotation.Order;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(20)
public class LoginFilter extends AbstractClientFilter {

    private ClientProperties properties;

    public LoginFilter(ClientProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean isAccessAllowed() throws IOException {
        // In H5 mode, allow main H5 landing and popup callback pages to load without redirect.
        try {
            if (Boolean.TRUE.equals(properties.getH5Enabled())) {
                jakarta.servlet.http.HttpServletRequest req = ClientContextHolder.getRequest();
                String sp = req.getServletPath();
                if ("/".equals(sp) || "/index.html".equals(sp) || "/oauth2-popup-callback.html".equals(sp)) {
                    return true;
                }
            }
        } catch (Throwable ignore) {}

        TokenWrapper tokenWrapper = SSOUtils.getTokenWrapper();
        // Token already present locally
        if (tokenWrapper != null) {
            // If accessToken not expired, allow request
            if (!tokenWrapper.checkExpired() && holderToken(tokenWrapper.getObject())) {
                return true;
            }
            // If accessToken expired but refreshToken still valid
            if (!tokenWrapper.checkRefreshExpired()) {
                // In SPA mode, instruct client to refresh token via API
                if (properties.getH5Enabled()) {
                    responseJson(ClientConstant.NO_TOKEN, "Token has expired");
                    return false;
                }
                // In server-rendered mode, refresh token automatically
                else {
                    Result<Token> result = SSOUtils.getHttpRefreshTokenInCookie(tokenWrapper.getObject().getRefreshToken());
                    if (result.isSuccess() && holderToken(result.getData())) {
                        return true;
                    }
                }
            }
        }

        String code = ClientContextHolder.getRequest().getParameter(BaseConstant.AUTH_CODE);
        // Request carries authorization code
        if (code != null && SSOUtils.getHttpAccessTokenInCookie(code).isSuccess()) {
            // Redirect once to remove code parameter from URL
            redirectLocalRemoveCode();
        } else {
            // Redirect to SSO server login
            redirectLogin();
        }
        return false;
    }


    private boolean holderToken(Token token) {
        TokenPermission permission = SSOUtils.getTokenPermission(token.getAccessToken());
        if (permission == null) {
            return false;
        }
        ClientContextHolder.setUser(token.getTokenUser());
        ClientContextHolder.setPermission(permission);
        return true;
    }


    private void redirectLogin() throws IOException {
        if (isAjaxRequest()) {
            responseJson(ClientConstant.NO_LOGIN, "Not logged in or session timed out");
        } else {
            String loginUrl = SSOUtils.buildLoginUrl(getCurrentUrl());
            ClientContextHolder.getResponse().sendRedirect(loginUrl);
        }
    }

    public static String getCurrentUrl() {
        HttpServletRequest request = ClientContextHolder.getRequest();
        StringBuilder urlBuilder = new StringBuilder(request.getRequestURL());
        String queryString = request.getQueryString();
        if (queryString != null) {
            urlBuilder.append("?").append(queryString);
        }
        return urlBuilder.toString();
    }


    private void redirectLocalRemoveCode() throws IOException {
        String currentUrl = getCurrentUrl();
        currentUrl = currentUrl.substring(0, currentUrl.indexOf(BaseConstant.AUTH_CODE) - 1);
        ClientContextHolder.getResponse().sendRedirect(currentUrl);
    }

    protected boolean isAjaxRequest() {
        return "XMLHttpRequest".equals(ClientContextHolder.getRequest().getHeader("X-Requested-With"));
    }

    public void setProperties(ClientProperties properties) {
        this.properties = properties;
    }

    public ClientProperties getProperties() {
        return properties;
    }
}
