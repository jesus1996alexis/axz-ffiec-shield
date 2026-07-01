package com.axz.ffiec.authentication.entity;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a refresh token issued by AXZ FFIEC Shield.
 *
 * <p>This domain model captures the token lifecycle, subject identifier,
 * expiration state, revocation state, and request context associated with a
 * long-lived authentication session.</p>
 *
 * <p>The model is intentionally independent of persistence, transport, and
 * service concerns. It can therefore be used by domain, application, and
 * infrastructure layers without depending on JPA or any storage mechanism.</p>
 *
 * <p>The token value is excluded from string rendering to reduce the risk of
 * accidental credential disclosure in logs or diagnostic output.</p>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "token")
public class RefreshToken {

    /**
     * Unique refresh token identifier.
     */
    @EqualsAndHashCode.Include
    private UUID id;

    /**
     * Opaque refresh token value presented by clients to renew access.
     */
    private String token;

    /**
     * Unique identifier of the user to whom this refresh token was issued.
     */
    private UUID userId;

    /**
     * Timestamp when the refresh token was issued.
     */
    @Default
    private Instant issuedAt = Instant.now();

    /**
     * Timestamp after which the refresh token must no longer be accepted.
     */
    private Instant expiresAt;

    /**
     * Timestamp when the refresh token was revoked.
     */
    private Instant revokedAt;

    /**
     * Indicates whether the refresh token has been revoked.
     */
    @Default
    private boolean revoked = false;

    /**
     * Client device identifier associated with the refresh token.
     */
    private String deviceId;

    /**
     * IP address observed when the refresh token was issued.
     */
    private String ipAddress;

    /**
     * User agent observed when the refresh token was issued.
     */
    private String userAgent;

    /**
     * Determines whether this refresh token has expired.
     *
     * @return {@code true} when the expiration timestamp has been reached
     */
    public boolean isExpired() {
        return expiresAt != null && !Instant.now().isBefore(expiresAt);
    }

    /**
     * Determines whether this refresh token has been revoked.
     *
     * @return {@code true} when the token has been explicitly revoked
     */
    public boolean isRevoked() {
        return revoked || revokedAt != null;
    }

    /**
     * Determines whether this refresh token is currently usable.
     *
     * @return {@code true} when the token has a usable value, belongs to a
     *         user, has an expiration timestamp, has not expired, and has not
     *         been revoked
     */
    public boolean isValid() {
        return token != null
                && !token.isBlank()
                && userId != null
                && expiresAt != null
                && !isExpired()
                && !isRevoked();
    }

    /**
     * Revokes this refresh token and records the revocation timestamp.
     */
    public void revoke() {
        revoked = true;

        if (revokedAt == null) {
            revokedAt = Instant.now();
        }
    }
}
