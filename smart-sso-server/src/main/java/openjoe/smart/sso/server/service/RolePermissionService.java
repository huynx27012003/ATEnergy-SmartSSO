package openjoe.smart.sso.server.service;

import openjoe.smart.sso.server.stage.mybatisplus.service.BaseService;
import openjoe.smart.sso.server.entity.RolePermission;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RolePermissionService extends BaseService<RolePermission> {
	

	List<RolePermission> selectByRoleIds(List<Long> roleIdList);
	

	void allocate(Long appId, Long roleId, List<Long> permissionIdList);
	

	void deleteByPermissionIds(List<Long> idList);
	

	void deleteByRoleIds(Collection<Long> idList);
	

	void deleteByAppIds(Collection<Long> idList);

	Set<Long> findPermissionIdSetByRoleIds(List<Long> roleIdList);
}
