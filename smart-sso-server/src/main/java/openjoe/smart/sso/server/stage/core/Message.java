package openjoe.smart.sso.server.stage.core;

import java.text.MessageFormat;


public abstract class Message {

    /**
     * 定义默认实现
     */
    protected static final Message DEFAULT = new Message() {

        @Override
        public String getMessageOrDefault(String key, String defaultValue, Object... args) {
            if (args == null || args.length == 0) {
                return defaultValue;
            }
            try {
                return MessageFormat.format(defaultValue, args);
            } catch (Exception e) {
                return defaultValue;
            }
        }
    };

    /**
     * 当前实例，可通过子类赋值覆盖
     */
    protected static Message local = DEFAULT;

    public static String get(String key, Object... args) {
        return getOrDefault(key, key, args);
    }

    public static String getOrDefault(String key, String defaultValue, Object... args) {
        return local.getMessageOrDefault(key, defaultValue, args);
    }

    public abstract String getMessageOrDefault(String key, String defaultValue, Object... args);
}