package openjoe.smart.sso.client.token;

import openjoe.smart.sso.base.entity.LifecycleManager;
import openjoe.smart.sso.base.entity.Token;

public interface TokenStorage extends LifecycleManager<TokenWrapper> {


    default void create(Token token) {
        create(token.getAccessToken(), new TokenWrapper(token, token.getExpiresIn(), token.getRefreshExpiresIn()));
    }


    String getAccessToken(String refreshToken);
}
