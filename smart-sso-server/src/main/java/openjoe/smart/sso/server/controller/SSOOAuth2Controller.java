package openjoe.smart.sso.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import openjoe.smart.sso.base.constant.BaseConstant;
import openjoe.smart.sso.base.entity.Result;
import openjoe.smart.sso.base.entity.Token;
import openjoe.smart.sso.base.entity.TokenUser;
import openjoe.smart.sso.base.enums.GrantTypeEnum;
import openjoe.smart.sso.server.entity.CodeContent;
import openjoe.smart.sso.server.entity.TicketGrantingTicketContent;
import openjoe.smart.sso.server.entity.TokenContent;
import openjoe.smart.sso.server.manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(BaseConstant.AUTH_PATH)
@Api(tags = "OAuth2 APIs")
public class SSOOAuth2Controller {
    @Autowired
    private AppManager appManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private AbstractCodeManager codeManager;
    @Autowired
    private AbstractTokenManager tokenManager;
    @Autowired
    private AbstractTicketGrantingTicketManager tgtManager;
    @Autowired
    private PermissionManager permissionManager;

    /**
     * Exchange authorization code for access token
     */
    @ApiOperation("Get access token by authorization_code")
    @RequestMapping(value = "/access-token", method = RequestMethod.GET)
    public Result<Token> getAccessToken(
            @RequestParam(value = BaseConstant.GRANT_TYPE) String grantType,
            @RequestParam(value = BaseConstant.CLIENT_ID) String clientId,
            @RequestParam(value = BaseConstant.CLIENT_SECRET) String clientSecret,
            @RequestParam(value = BaseConstant.AUTH_CODE) String code,
            @RequestParam(value = BaseConstant.LOGOUT_URI) String logoutUri) {

        // Validate grant type
        if (!GrantTypeEnum.AUTHORIZATION_CODE.getValue().equals(grantType)) {
            return Result.error("Only authorization_code grant type is supported");
        }

        // Validate application credentials
        Result<Void> appResult = appManager.validate(clientId, clientSecret);
        if (!appResult.isSuccess()) {
            return Result.error(appResult.getMessage());
        }

        // Validate authorization code
        CodeContent codeContent = codeManager.get(code);
        if (codeContent == null || !codeContent.getClientId().equals(clientId)) {
            return Result.error("Invalid or expired code");
        }
        codeManager.remove(code);

        // Validate TGT
        TicketGrantingTicketContent tgtContent = tgtManager.get(codeContent.getTgt());
        if (tgtContent == null) {
            return Result.error("Server TGT has expired");
        }

        Result<TokenUser> userResult = userManager.getTokenUser(tgtContent.getUserId());
        if (!userResult.isSuccess()) {
            return Result.error(userResult.getMessage());
        }

        // Create token
        TokenContent tc = tokenManager.create(tgtContent.getUserId(), logoutUri, codeContent);

        // Refresh TGT TTL
        tgtManager.refresh(tc.getTgt());

        // Enrich user with permission payload for the current app (avoid extra round trip)
        Result<Long> appIdResult = appManager.validate(clientId);
        if (appIdResult.isSuccess()) {
            TokenUser tu = userResult.getData();
            openjoe.smart.sso.base.entity.TokenPermission tp = permissionManager.getUserPermission(tgtContent.getUserId(), appIdResult.getData());
            if (tp != null) {
                tu.setPermissions(tp.getPermissionSet());
                tu.setNoPermissions(tp.getNoPermissionSet());
                tu.setMenuList(tp.getMenuList());
            }
        }

        // Return token
        return Result.success(new Token(tc.getAccessToken(), tokenManager.getAccessTokenTimeout(), tc.getRefreshToken(),
                tokenManager.getRefreshTokenTimeout(), userResult.getData()));
    }

    /**
     * Issue access token directly by username/password (no redirect)
     */
    @ApiOperation("Get access token by username/password (no UI)")
    @RequestMapping(value = "/access-token", method = RequestMethod.POST)
    public Result<Token> getAccessTokenByPassword(
            @RequestParam(value = BaseConstant.GRANT_TYPE) String grantType,
            @RequestParam(value = BaseConstant.CLIENT_ID) String clientId,
            @RequestParam(value = BaseConstant.CLIENT_SECRET) String clientSecret,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(value = BaseConstant.LOGOUT_URI, required = false) String logoutUri) {

        if (!StringUtils.hasLength(grantType) || !"password".equals(grantType)) {
            return Result.error("Only password grantType is supported for POST /access-token");
        }

        // Validate application credentials
        Result<Void> appResult = appManager.validate(clientId, clientSecret);
        if (!appResult.isSuccess()) {
            return Result.error(appResult.getMessage());
        }

        // Validate user credentials
        Result<Long> userValidate = userManager.validate(username, password);
        if (!userValidate.isSuccess()) {
            return Result.error(userValidate.getMessage());
        }

        Long userId = userValidate.getData();

        // Create TGT programmatically (no cookie)
        String tgt = "TGT-" + java.util.UUID.randomUUID().toString().replaceAll("-", "");
        tgtManager.create(tgt, new TicketGrantingTicketContent(userId, System.currentTimeMillis()));

        // Build token using a synthetic CodeContent (binds clientId + tgt)
        CodeContent codeContent = new CodeContent(tgt, clientId);
        TokenContent tc = tokenManager.create(userId, logoutUri, codeContent);

        // Refresh TGT TTL
        tgtManager.refresh(tgt);

        // Load token user info
        Result<TokenUser> userResult = userManager.getTokenUser(userId);
        if (!userResult.isSuccess()) {
            return Result.error(userResult.getMessage());
        }

        // Enrich permission using app id resolved from clientId
        Result<Long> appIdResult = appManager.validate(clientId);
        if (appIdResult.isSuccess()) {
            TokenUser tu = userResult.getData();
            openjoe.smart.sso.base.entity.TokenPermission tp = permissionManager.getUserPermission(userId, appIdResult.getData());
            if (tp != null) {
                tu.setPermissions(tp.getPermissionSet());
                tu.setNoPermissions(tp.getNoPermissionSet());
                tu.setMenuList(tp.getMenuList());
            }
        }

        return Result.success(new Token(tc.getAccessToken(), tokenManager.getAccessTokenTimeout(), tc.getRefreshToken(),
                tokenManager.getRefreshTokenTimeout(), userResult.getData()));
    }

    /**
     * Refresh access token using refresh token
     */
    @ApiOperation("Refresh access token by refreshToken")
    @RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
    public Result<Token> getRefreshToken(
            @RequestParam(value = BaseConstant.CLIENT_ID) String clientId,
            @RequestParam(value = BaseConstant.REFRESH_TOKEN) String refreshToken) {
        Result<Long> appResult = appManager.validate(clientId);
        if (!appResult.isSuccess()) {
            return Result.error(appResult.getMessage());
        }

        TokenContent atContent = tokenManager.get(refreshToken);
        if (atContent == null) {
            return Result.error("Invalid or expired refreshToken");
        }

        Result<TokenUser> userResult = userManager.getTokenUser(atContent.getUserId());
        if (!userResult.isSuccess()) {
            return Result.error(userResult.getMessage());
        }

        // Remove old token and create a new one
        tokenManager.remove(refreshToken);
        TokenContent tc = tokenManager.create(atContent);

        // Refresh TGT TTL
        tgtManager.refresh(tc.getTgt());

        // Enrich permission using client id stored in token content
        Result<Long> appIdResult = appManager.validate(clientId);
        if (appIdResult.isSuccess()) {
            TokenUser tu = userResult.getData();
            openjoe.smart.sso.base.entity.TokenPermission tp = permissionManager.getUserPermission(atContent.getUserId(), appIdResult.getData());
            if (tp != null) {
                tu.setPermissions(tp.getPermissionSet());
                tu.setNoPermissions(tp.getNoPermissionSet());
                tu.setMenuList(tp.getMenuList());
            }
        }

        // Return new token
        return Result.success(new Token(tc.getAccessToken(), tokenManager.getAccessTokenTimeout(), tc.getRefreshToken(),
                tokenManager.getRefreshTokenTimeout(), userResult.getData()));
}
}
