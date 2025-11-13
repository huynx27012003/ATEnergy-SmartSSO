package openjoe.smart.sso.server.service;

import openjoe.smart.sso.server.dto.PermissionDTO;
import openjoe.smart.sso.server.entity.Permission;
import openjoe.smart.sso.server.stage.mybatisplus.service.BaseService;

import java.util.Collection;
import java.util.List;

public interface PermissionService extends BaseService<Permission> {


	List<PermissionDTO> selectTree(Long appId, Long roleId, Boolean isEnable);

	void delete(Long id, Long appId);

	void deleteByAppIds(Collection<Long> idList);
}
