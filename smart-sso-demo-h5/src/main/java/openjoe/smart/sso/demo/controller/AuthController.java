package openjoe.smart.sso.demo.controller;

import openjoe.smart.sso.base.entity.Result;
import openjoe.smart.sso.base.entity.Token;
import openjoe.smart.sso.client.util.SSOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login_url")
    public Result<String> getLoginUrl(@RequestParam String redirectUri) {
        return Result.success(SSOUtils.buildLoginUrl(redirectUri));
    }

    @RequestMapping("/logout_url")
    public Result<String> getLogoutUrl(@RequestParam String redirectUri) {
        return Result.success(SSOUtils.buildLogoutUrl(redirectUri));
    }

    @RequestMapping(value = "/access-token", method = RequestMethod.GET)
    public Result<Token> getAccessToken(@RequestParam String code) {
        Result<Token> result = SSOUtils.getHttpAccessToken(code);
        if (!result.isSuccess()) {
            return result;
        }
        return Result.success(result.getData());
    }
    @RequestMapping(value = "/debug-access-token", method = RequestMethod.GET)
    public Result<Token> debugAccessToken(@RequestParam String code) {
        Result<Token> result = SSOUtils.getHttpAccessToken(code);
        return result;
    }

    @RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
    public Result<Token> getRefreshToken(String refreshToken) {
        Result<Token> result = SSOUtils.getHttpRefreshToken(refreshToken);
        if (!result.isSuccess()) {
            return result;
        }
        return Result.success(result.getData());
    }
}
