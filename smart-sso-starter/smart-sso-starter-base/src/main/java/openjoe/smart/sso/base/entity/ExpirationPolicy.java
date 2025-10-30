package openjoe.smart.sso.base.entity;

/**
 * Expiration cleanup contract
 * * @author huynx */
public interface ExpirationPolicy {

    /**
     * Periodically remove expired entries
     */
    void verifyExpired();
}
