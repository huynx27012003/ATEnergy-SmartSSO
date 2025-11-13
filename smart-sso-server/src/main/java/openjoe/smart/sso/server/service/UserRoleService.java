package openjoe.smart.sso.server.service;

import openjoe.smart.sso.server.stage.mybatisplus.service.BaseService;
import openjoe.smart.sso.server.entity.UserRole;

import java.util.Collection;
import java.util.List;

public interface UserRoleService extends BaseService<UserRole> {
	

    void allocate(Long userId, List<Long> roleIdList);

	void deleteByRoleIds(Collection<Long> idList);

	void deleteByUserIds(Collection<Long> idList);

    List<Long> findRoleIdListByUserId(Long userId);
}
