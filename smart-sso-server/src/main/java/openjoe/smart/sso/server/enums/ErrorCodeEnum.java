package openjoe.smart.sso.server.enums;

import openjoe.smart.sso.server.stage.core.IErrorCode;

/**
 * Error code enumeration
 */
public enum ErrorCodeEnum implements IErrorCode {

    E1001(1001, "Password cannot be empty"),
    E1002(1002, "Password encryption error"),
    E1003(1003, "Application code already exists"),
    E1004(1004, "Login name already exists");

    private Integer code;
    private String desc;

    ErrorCodeEnum(Integer code, String desc) {
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
