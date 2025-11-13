package openjoe.smart.sso.server.service;

import openjoe.smart.sso.server.entity.User;
import openjoe.smart.sso.server.stage.core.Page;
import openjoe.smart.sso.server.stage.mybatisplus.service.BaseService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService extends BaseService<User> {
	

	void enable(Boolean isEnable, List<Long> idList);
	
	
	void resetPassword(String password, List<Long> idList);

	Page<User> selectPage(String account, String name, Long officeId, Long current, Long size);
	

	User selectByAccount(String account);
	
	void updatePassword(Long id, String newPassword);

	void deleteByIds(Collection<Long> idList);

	Map<Long, User> selectMapByIds(Collection<Long> idList);

	Set<Long> selectUserIds(String account, String name);
}
