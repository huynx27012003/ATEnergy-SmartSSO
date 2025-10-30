package openjoe.smart.sso.server.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import openjoe.smart.sso.server.service.LoginUserService;
import openjoe.smart.sso.server.stage.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;
import java.util.stream.Stream;
@Api(tags = "Login User Management")
@Controller
@RequestMapping("/admin/login-user")
@SuppressWarnings("rawtypes")
public class LoginUserController {

	@Autowired
	private LoginUserService loginUserService;

    @ApiOperation("Initial Page")
	@RequestMapping(method = RequestMethod.GET)
	public String execute() {
		return "/admin/login-user";
	}

    @ApiOperation("List")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result list(
			@RequestParam(required = false) String account,
			@RequestParam(required = false) String name,
			@RequestParam Long current,
			@RequestParam Long size) {
		return Result.success(loginUserService.selectPage(account, name, current, size));
	}

    @ApiOperation("Logout")
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Result logout(@RequestParam String tgts) {
		loginUserService.logout(Stream.of(tgts.split(",")).filter(s -> StringUtils.hasLength(s)).map(s -> s.trim()).collect(Collectors.toList()));
		return Result.success();
	}
}
