package openjoe.smart.sso.server.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import openjoe.smart.sso.server.stage.core.Result;
import openjoe.smart.sso.server.stage.exception.ApplicationException;
import openjoe.smart.sso.server.entity.App;
import openjoe.smart.sso.server.enums.ErrorCodeEnum;
import openjoe.smart.sso.server.service.AppService;
import openjoe.smart.sso.server.util.ClientCredentialsGenerator;
import openjoe.smart.sso.server.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
@Api(tags = "Application Management")
@Controller
@RequestMapping("/admin/app")
@SuppressWarnings("rawtypes")
public class AppController {

	@Autowired
	private AppService appService;

    @ApiOperation("Initial Page")
	@RequestMapping(method = RequestMethod.GET)
	public String execute() {
		return "/admin/app";
	}

    @ApiOperation("Add/Edit Page")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) Long id, Model model) {
		App app;
		if (id == null) {
			app = new App();
			app.setIsEnable(true);
		}
		else {
			app = appService.getById(id);
		}
		model.addAttribute("app", app);
		return "/admin/app-edit";
	}

    @ApiOperation("Query application credentials")
	@ResponseBody
	@RequestMapping(value = "/credentials", method = RequestMethod.GET)
	public Result credential(@RequestParam Long id) {
		return Result.success(appService.getById(id));
	}

    @ApiOperation("List")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result list(
			@RequestParam(required = false) String name,
			@RequestParam Long current,
			@RequestParam Long size) {
		return Result.success(appService.selectPage(name, current, size));
	}

    @ApiOperation("Validate application code")
	@ResponseBody
	@RequestMapping(value = "/validate-code", method = RequestMethod.POST)
	public Result validateCode(
			@RequestParam(required = false) Long id,
			@RequestParam String code) {
		App db = appService.selectByCode(code);
		if (null != db && !db.getId().equals(id)) {
			throw new ApplicationException(ErrorCodeEnum.E1003);
		}
		return Result.success();
	}

    @ApiOperation("Enable/Disable")
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public Result enable(
			@RequestParam String ids,
			@RequestParam Boolean isEnable) {
		appService.enable(isEnable, ConvertUtils.convertToIdList(ids));
		return Result.success();
	}

    @ApiOperation("Add or Update")
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(
			@RequestParam(required = false) Long id,
			@RequestParam String name,
			@RequestParam String code,
			@RequestParam Boolean isEnable,
			@RequestParam Integer sort) {
		App app;
		if (id == null) {
			app = new App();
			app.setCreateTime(new Date());
			app.setClientId(appService.generateClientId());
			app.setClientSecret(ClientCredentialsGenerator.generateClientSecret(app.getClientId()));
		}
		else {
			app = appService.getById(id);
		}
		app.setName(name);
		app.setSort(sort);
		app.setIsEnable(isEnable);
		app.setCode(code);
		appService.saveOrUpdate(app);
		return Result.success();
	}

    @ApiOperation("Delete")
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result delete(@RequestParam String ids) {
		appService.deleteByIds(ConvertUtils.convertToIdList(ids));
		return Result.success();
	}
}
