package openjoe.smart.sso.client.token;

import openjoe.smart.sso.base.entity.ExpirationWrapper;
import openjoe.smart.sso.base.entity.LifecycleManager;
import openjoe.smart.sso.base.entity.Token;
import openjoe.smart.sso.base.entity.TokenPermission;

public interface TokenPermissionStorage extends LifecycleManager<ExpirationWrapper<TokenPermission>> {


    default void create(Token token, TokenPermission tokenPermission) {
        create(token.getAccessToken(), new ExpirationWrapper<>(tokenPermission, token.getRefreshExpiresIn()));
    }
}
