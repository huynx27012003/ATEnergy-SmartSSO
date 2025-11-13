package openjoe.smart.sso.server.service;

import openjoe.smart.sso.server.stage.core.Page;
import openjoe.smart.sso.server.stage.mybatisplus.service.BaseService;
import openjoe.smart.sso.server.dto.PermissionDTO;
import openjoe.smart.sso.server.entity.Role;

import java.util.Collection;
import java.util.List;

public interface RoleService extends BaseService<Role> {
	

	void enable(Boolean isEnable, List<Long> idList);
	

	Page<Role> selectPage(String name, Long current, Long size);
	

	List<Role> selectAll(Boolean isEnable);
	
	List<PermissionDTO> getRoleList(Long userId);

	void deleteByIds(Collection<Long> idList);
}
