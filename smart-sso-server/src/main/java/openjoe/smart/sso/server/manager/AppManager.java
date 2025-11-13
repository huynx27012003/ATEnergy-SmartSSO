package openjoe.smart.sso.server.manager;

import openjoe.smart.sso.base.entity.Result;

public interface AppManager {

 
    Result<Long> validate(String clientId);

   
    Result<Void> validate(String clientId, String clientSecret);
}
