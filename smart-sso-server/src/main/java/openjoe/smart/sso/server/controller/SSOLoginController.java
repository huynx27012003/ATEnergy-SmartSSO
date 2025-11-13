package openjoe.smart.sso.server.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import openjoe.smart.sso.base.constant.BaseConstant;
import openjoe.smart.sso.base.entity.Result;
import openjoe.smart.sso.server.manager.AbstractCodeManager;
import openjoe.smart.sso.server.manager.AbstractTicketGrantingTicketManager;
import openjoe.smart.sso.server.manager.AppManager;
import openjoe.smart.sso.server.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@Controller
@RequestMapping(BaseConstant.LOGIN_PATH)
public class SSOLoginController {

    @Autowired
    private AbstractCodeManager codeManager;
    @Autowired
    private AbstractTicketGrantingTicketManager tgtManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private AppManager appManager;

    
    @RequestMapping(method = RequestMethod.GET)
    public String login(
            @RequestParam(value = BaseConstant.REDIRECT_URI) String redirectUri,
            @RequestParam(value = BaseConstant.CLIENT_ID) String clientId,
            HttpServletRequest request) throws UnsupportedEncodingException {
        String tgt = tgtManager.get(request);
        if (!StringUtils.hasLength(tgt)) {
            return goLoginPage(redirectUri, clientId, request);
        }
        return generateCodeAndRedirect(tgt, clientId, redirectUri);
    }

    
    @RequestMapping(method = RequestMethod.POST)
    public String login(
            @RequestParam(value = BaseConstant.REDIRECT_URI) String redirectUri,
            @RequestParam(value = BaseConstant.CLIENT_ID) String clientId,
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        Result<Long> appResult = appManager.validate(clientId);
        if (!appResult.isSuccess()) {
            request.setAttribute("errorMessage", appResult.getMessage());
            return goLoginPage(redirectUri, clientId, request);
        }

        Result<Long> result = userManager.validate(username, password);
        if (!result.isSuccess()) {
            request.setAttribute("errorMessage", result.getMessage());
            return goLoginPage(redirectUri, clientId, request);
        }

        String tgt = tgtManager.getOrCreate(result.getData(), request, response);
        return generateCodeAndRedirect(tgt, clientId, redirectUri);
    }

    private String goLoginPage(String redirectUri, String clientId, HttpServletRequest request) {
        request.setAttribute(BaseConstant.REDIRECT_URI, redirectUri);
        request.setAttribute(BaseConstant.CLIENT_ID, clientId);
        return "/login";
    }

  
    private String generateCodeAndRedirect(String tgt, String clientId, String redirectUri) throws UnsupportedEncodingException {
        String code = codeManager.create(tgt, clientId);
        return "redirect:" + authRedirectUri(redirectUri, code);
    }

   
    private String authRedirectUri(String redirectUri, String code) throws UnsupportedEncodingException {
        StringBuilder sbf = new StringBuilder(redirectUri);
        if (redirectUri.indexOf("?") > -1) {
            sbf.append("&");
        } else {
            sbf.append("?");
        }
        sbf.append(BaseConstant.AUTH_CODE).append("=").append(code);
        return URLDecoder.decode(sbf.toString(), "utf-8");
    }

}