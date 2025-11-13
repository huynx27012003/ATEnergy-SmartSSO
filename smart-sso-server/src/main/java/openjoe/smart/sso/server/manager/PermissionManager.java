package openjoe.smart.sso.server.manager;

import openjoe.smart.sso.base.entity.TokenPermission;

public interface PermissionManager {

 
    TokenPermission getUserPermission(Long userId, Long appId);
}
