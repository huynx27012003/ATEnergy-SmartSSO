package openjoe.smart.sso.server.manager;

import openjoe.smart.sso.base.entity.Result;
import openjoe.smart.sso.base.entity.TokenUser;


public interface UserManager {

 
    Result<Long> validate(String username, String password);

    Result<TokenUser> getTokenUser(Long userId);
}
