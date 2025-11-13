package openjoe.smart.sso.server.service;

import openjoe.smart.sso.server.dto.LoginUserDTO;
import openjoe.smart.sso.server.stage.core.Page;

import java.util.List;

public interface LoginUserService {


	Page<LoginUserDTO> selectPage(String account, String name, Long current, Long size);


	void logout(List<String> tgtList);
}
