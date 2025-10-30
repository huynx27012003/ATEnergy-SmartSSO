package openjoe.smart.sso.base.entity;

import java.beans.Transient;

/**
 * Standard API result wrapper
 */
public class Result<T> {

    public static final int SUCCESS_CODE = 1;
    public static final int ERROR_CODE = 9999;

    private T data;
    private int code;
    private String message;

    public Result() {}

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, "Success");
    }

    public static <T> Result<T> success(T data) {
        Result<T> r = success();
        r.setData(data);
        return r;
    }

    public static <T> Result<T> error() {
        return new Result<>(ERROR_CODE, "System error");
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(ERROR_CODE, message);
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @Transient
    public boolean isSuccess() {
        return SUCCESS_CODE == code;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

