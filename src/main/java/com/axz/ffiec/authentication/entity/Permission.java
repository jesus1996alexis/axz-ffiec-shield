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
 * Represents an authorization permission within AXZ FFIEC Shield.
 *
 * <p>This domain model describes a discrete authorization capability that can
 * be evaluated by security workflows. A permission combines a named business
 * meaning with the protected resource and action it governs.</p>
 *
 * <p>The model is intentionally independent of persistence, transport, and
 * service concerns. It can therefore be used by application, domain, and
 * infrastructure layers without depending on JPA or any storage mechanism.</p>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Permission {

    /**
     * Unique permission identifier.
     */
    @EqualsAndHashCode.Include
    private UUID id;

    /**
     * Unique permission name used by authorization logic and policy checks.
     */
    private String name;

    /**
     * Human-readable description of the permission's purpose and intended use.
     */
    private String description;

    /**
     * Protected resource or domain object governed by this permission.
     */
    private String resource;

    /**
     * Authorized operation that may be performed on the protected resource.
     */
    private String action;

    /**
     * Indicates whether the permission is managed by the framework and should
     * be protected from ordinary user-driven changes.
     */
    @Default
    private boolean systemPermission = false;

    /**
     * Indicates whether the permission is currently available for authorization
     * decisions.
     */
    @Default
    private boolean active = true;

    /**
     * Permission creation timestamp.
     */
    @Default
    private Instant createdAt = Instant.now();

    /**
     * Last update timestamp.
     */
    private Instant updatedAt;

    /**
     * Determines whether this permission is managed by the framework.
     *
     * @return {@code true} when the permission is a system-managed permission
     */
    public boolean isSystemPermission() {
        return systemPermission;
    }

    /**
     * Determines whether this permission is available for authorization checks.
     *
     * @return {@code true} when the permission is active
     */
    public boolean isActive() {
        return active;
    }
}
