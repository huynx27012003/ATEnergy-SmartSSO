package openjoe.smart.sso.server.stage.core;


public interface IMessage {

  
    String getKey();

    default String getMessage(Object... args) {
        return Message.get(getKey(), args);
    }
}