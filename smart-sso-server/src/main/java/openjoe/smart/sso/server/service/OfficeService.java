package openjoe.smart.sso.server.service;

import openjoe.smart.sso.server.stage.mybatisplus.service.BaseService;
import openjoe.smart.sso.server.entity.Office;

import java.util.List;

public interface OfficeService extends BaseService<Office> {


	void enable(Boolean isEnable, List<Long> idList);
	
	List<Office> selectList(Boolean isEnable, Boolean isParent, Long currentId, String prefix);
	
	List<Long> selectIdListByParentId(Long parentId);
}