package openjoe.smart.sso.server.stage.core;


public interface IErrorCode extends IMessage {

    
    Integer getCode();

    default String getDesc(){
        return getKey();
    }

    @Override
    default String getKey() {
        return getCode().toString();
    }

    @Override
    default String getMessage(Object... args) {
        return Message.getOrDefault(getKey(), getDesc(), args);
    }
}