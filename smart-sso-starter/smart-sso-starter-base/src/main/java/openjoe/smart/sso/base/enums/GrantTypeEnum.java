package openjoe.smart.sso.base.enums;

/**
 * OAuth2 grant types
 * * @author huynx */
public enum GrantTypeEnum {

    /**
     * Authorization code
     */
    AUTHORIZATION_CODE("authorization_code");

    private String value;

    GrantTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
