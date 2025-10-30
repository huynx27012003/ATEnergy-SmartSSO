package openjoe.smart.sso.base.entity;


public class ExpirationWrapper<T> {

    private T object;


    private int expiresIn;


    private Long expired;

    public ExpirationWrapper() {
        super();
    }

    public ExpirationWrapper(T object, int expiresIn) {
        super();
        this.object = object;
        this.expiresIn = expiresIn;
        this.expired = System.currentTimeMillis() + expiresIn * 1000;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getExpired() {
        return expired;
    }

    public void setExpired(Long expired) {
        this.expired = expired;
    }


    public boolean checkExpired() {
        return System.currentTimeMillis() > getExpired();
    }
}
