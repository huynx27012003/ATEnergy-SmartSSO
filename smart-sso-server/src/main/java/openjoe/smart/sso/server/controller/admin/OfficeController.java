package openjoe.smart.sso.server.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import openjoe.smart.sso.server.stage.core.Result;
import openjoe.smart.sso.server.entity.Office;
import openjoe.smart.sso.server.service.OfficeService;
import openjoe.smart.sso.server.util.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Api(tags = "Organization")
@Controller
@RequestMapping("/admin/office")
@SuppressWarnings("rawtypes")
public class OfficeController {

	@Autowired
	private OfficeService officeService;

    @ApiOperation("Initial Page")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/office";
	}
	
    @ApiOperation("List")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result list(
			@RequestParam Long current,
			@RequestParam Long size) {
		return Result.success(officeService.selectList(null, null, null, "--"));
	}

    @ApiOperation("Add/Edit Page")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) Long id, Model model) {
		Office office;
		if (id == null) {
			office = new Office();
			office.setIsEnable(true);
		}
		else {
			office = officeService.getById(id);
		}
		model.addAttribute("officeList", officeService.selectList(null, null, id, "--"));
		model.addAttribute("office", office);
		return "/admin/office-edit";
	}

    @ApiOperation("Add or Update")
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long parentId,
			@RequestParam String name,
			@RequestParam Integer sort,
			@RequestParam Boolean isEnable
			) {
		Office office;
		if (id == null) {
			office = new Office();
		}
		else {
			office = officeService.getById(id);
		}
		office.setParentId(parentId);
		office.setName(name);
		office.setSort(sort);
		office.setIsEnable(isEnable);
		officeService.saveOrUpdate(office);
		return Result.success();
	}

    @ApiOperation("Enable/Disable")
	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public Result enable(
			@RequestParam String ids,
			@RequestParam Boolean isEnable) {
		officeService.enable(isEnable, ConvertUtils.convertToIdList(ids));
		return Result.success();
	}

    @ApiOperation("Delete")
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result delete(
			@RequestParam String ids) {
		officeService.removeByIds(ConvertUtils.convertToIdList(ids));
		return Result.success();
	}
}
