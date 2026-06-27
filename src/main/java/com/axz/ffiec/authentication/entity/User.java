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
 * Represents an authenticated application user within AXZ FFIEC Shield.
 *
 * <p>This domain model captures identity, credential state, account lifecycle
 * flags, multi-factor authentication readiness, and operational timestamps
 * required by authentication and security workflows.</p>
 *
 * <p>The model is intentionally independent of persistence, transport,
 * and service layers, allowing it to be reused across different modules
 * without infrastructure dependencies.</p>
 *
 * <p>The passwordHash field is excluded from string rendering to prevent
 * accidental exposure in logs or diagnostic information.</p>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "passwordHash")
public class User {

    /**
     * Unique user identifier.
     */
    @EqualsAndHashCode.Include
    private UUID id;

    /**
     * Unique username.
     */
    private String username;

    /**
     * User email address.
     */
    private String email;

    /**
     * Securely hashed password.
     */
    private String passwordHash;

    /**
     * User first name.
     */
    private String firstName;

    /**
     * User last name.
     */
    private String lastName;

    /**
     * Indicates whether the account is enabled.
     */
    @Default
    private boolean enabled = true;

    /**
     * Indicates whether the account has been locked.
     */
    @Default
    private boolean accountLocked = false;

    /**
     * Indicates whether the account has expired.
     */
    @Default
    private boolean accountExpired = false;

    /**
     * Indicates whether the credentials have expired.
     */
    @Default
    private boolean credentialsExpired = false;

    /**
     * Indicates whether the email address has been verified.
     */
    @Default
    private boolean emailVerified = false;

    /**
     * Indicates whether Multi-Factor Authentication is enabled.
     */
    @Default
    private boolean mfaEnabled = false;

    /**
     * User creation timestamp.
     */
    @Default
    private Instant createdAt = Instant.now();

    /**
     * Last update timestamp.
     */
    private Instant updatedAt;

    /**
     * Last successful authentication timestamp.
     */
    private Instant lastLoginAt;

    /**
     * Returns whether the account is currently unlocked.
     *
     * @return true if the account is not locked
     */
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    /**
     * Returns whether the account has not expired.
     *
     * @return true if the account has not expired
     */
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    /**
     * Returns whether the user credentials are still valid.
     *
     * @return true if the credentials have not expired
     */
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    /**
     * Returns whether the account is enabled.
     *
     * @return true if the account is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }
}