package openjoe.smart.sso.server.service;

import openjoe.smart.sso.server.entity.App;
import openjoe.smart.sso.server.stage.core.Page;
import openjoe.smart.sso.server.stage.mybatisplus.service.BaseService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AppService extends BaseService<App> {
	
	
	void enable(Boolean isEnable, List<Long> idList);
	

	List<App> selectAll(Boolean isEnable);
	

	Page<App> selectPage(String name, Long current, Long size);

	App selectByCode(String code);

	App selectByClientId(String clientId);

	void deleteByIds(Collection<Long> idList);

	Map<String, App> selectMapByClientIds(Collection<String> clientIdList);

	String generateClientId();
}