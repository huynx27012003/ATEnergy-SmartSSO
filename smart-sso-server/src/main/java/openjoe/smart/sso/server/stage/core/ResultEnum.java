package openjoe.smart.sso.server.stage.core;
public enum ResultEnum implements IErrorCode {

    SUCCESS(1, "SUCCESS"),
    VALIDATION_ERROR(9998, "VALIDATION_ERROR"),
    ERROR(9999, "ERROR");

    private Integer code;
    private String desc;

    ResultEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
