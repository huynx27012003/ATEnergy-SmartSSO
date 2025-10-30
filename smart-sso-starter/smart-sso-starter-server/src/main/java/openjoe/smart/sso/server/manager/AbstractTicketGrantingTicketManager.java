package openjoe.smart.sso.server.manager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import openjoe.smart.sso.base.entity.LifecycleManager;
import openjoe.smart.sso.base.util.CookieUtils;
import openjoe.smart.sso.server.entity.TicketGrantingTicketContent;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
public abstract class AbstractTicketGrantingTicketManager implements LifecycleManager<TicketGrantingTicketContent> {

    private AbstractTokenManager tokenManager;
    private int timeout;
    private String cookieName;

    public AbstractTicketGrantingTicketManager(int timeout, String cookieName, AbstractTokenManager tokenManager) {
        this.timeout = timeout;
        this.cookieName = cookieName;
        this.tokenManager = tokenManager;
    }

    /**
     * 登录成功后，根据用户信息创建令牌
     *
     * @param userId
     * @return
     */
    String create(String tgt, Long userId) {
        create(tgt, new TicketGrantingTicketContent(userId, System.currentTimeMillis()));
        return tgt;
    }

    public String getOrCreate(Long userId, HttpServletRequest request, HttpServletResponse response) {
        String tgt = getCookieTgt(request);
        // No TGT found in cookie
        if (!StringUtils.hasLength(tgt)) {
            tgt = create("TGT-" + UUID.randomUUID().toString().replaceAll("-", ""), userId);

            // Store TGT into cookie
            CookieUtils.addCookie(cookieName, tgt, "/", request, response);
        } else {
            create(tgt, userId);
        }
        return tgt;
    }

    public void invalidate(HttpServletRequest request, HttpServletResponse response) {
        String tgt = getCookieTgt(request);
        if (!StringUtils.hasLength(tgt)) {
            return;
        }
        invalidate(tgt);
        // Remove TGT cookie
        CookieUtils.removeCookie(cookieName, "/", response);
    }

    public void invalidate(String tgt) {
        // Remove login ticket
        remove(tgt);
        // Remove all tokens and notify all clients to logout (invalidate local tokens)
        tokenManager.removeByTgt(tgt);
    }

    public String get(HttpServletRequest request) {
        String tgt = getCookieTgt(request);
        if (!StringUtils.hasLength(tgt) || get(tgt) == null) {
            return null;
        } else {
            return tgt;
        }
    }

    private String getCookieTgt(HttpServletRequest request) {
        return CookieUtils.getCookieValue(cookieName, request);
    }

    public AbstractTokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(AbstractTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    /**
     * Refresh expiration
     *
     * @param tgt
     * @return
     */
    public abstract void refresh(String tgt);

    public abstract Map<String, TicketGrantingTicketContent> getTgtMap(Set<Long> userIds, Long current, Long size);
}
