package openjoe.smart.sso.client.filter;

import openjoe.smart.sso.base.entity.TokenPermission;
import openjoe.smart.sso.client.ClientProperties;
import openjoe.smart.sso.client.constant.ClientConstant;
import openjoe.smart.sso.client.util.ClientContextHolder;
import org.springframework.core.annotation.Order;

import java.io.IOException;

/**
 * Permission enforcement filter.
 */
@Order(30)
public class PermissionFilter extends AbstractClientFilter {

    private ClientProperties properties;

    public PermissionFilter(ClientProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean isAccessAllowed() throws IOException {
        String path = ClientContextHolder.getRequest().getServletPath();
        TokenPermission permission = ClientContextHolder.getPermission();

        // In H5 mode, allow public H5 pages to render even without token
        if (permission == null && Boolean.TRUE.equals(properties.getH5Enabled())) {
            if ("/".equals(path) || "/index.html".equals(path) || "/oauth2-popup-callback.html".equals(path)) {
                return true;
            }
        }

        // If permission is missing at this point, treat as not logged in (avoid NPE)
        if (permission == null) {
            responseJson(ClientConstant.NO_LOGIN, "no login");
            return false;
        }

        if (isPermitted(permission, path)) {
            return true;
        } else {
            responseJson(ClientConstant.NO_PERMISSION, "no permission");
            return false;
        }
    }

    private boolean isPermitted(TokenPermission tokenPermission, String path) {
        if (tokenPermission.getPermissionSet() != null && tokenPermission.getPermissionSet().contains(path)) {
            return true;
        }
        if (tokenPermission.getNoPermissionSet() != null && tokenPermission.getNoPermissionSet().contains(path)) {
            return false;
        }
        // Default allow when not explicitly denied
        return true;
    }

    public void setProperties(ClientProperties properties) {
        this.properties = properties;
    }

    public ClientProperties getProperties() {
        return properties;
    }
}

