package com.axz.ffiec.authentication.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
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
 * Represents an authorization role within AXZ FFIEC Shield.
 *
 * <p>This domain model defines a named security role that can be assigned
 * to users or interpreted by authorization workflows. It captures operational
 * state, lifecycle timestamps, and whether the role is managed by the system.</p>
 *
 * <p>The model is intentionally independent of persistence, transport,
 * and service layers, allowing the domain type to remain reusable across
 * application boundaries without infrastructure dependencies.</p>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "permissions")
public class Role {

    /**
     * Unique role identifier.
     */
    @EqualsAndHashCode.Include
    private UUID id;

    /**
     * Unique role name used by authorization logic.
     */
    private String name;

    /**
     * Human-readable description of the role's purpose and intended usage.
     */
    private String description;

    /**
     * Indicates whether the role is managed by the framework and should be
     * protected from ordinary user-driven changes.
     */
    @Default
    private boolean systemRole = false;

    /**
     * Indicates whether the role is currently available for authorization
     * decisions and assignments.
     */
    @Default
    private boolean active = true;

    /**
     * Role creation timestamp.
     */
    @Default
    private Instant createdAt = Instant.now();

    /**
     * Last update timestamp.
     */
    private Instant updatedAt;

    /**
     * Permissions granted by this role.
     */
    @Default
    private Set<Permission> permissions = new HashSet<>();

    /**
     * Grants a permission to this role.
     *
     * @param permission permission to grant
     */
    public void addPermission(Permission permission) {
        if (permission != null) {
            permissions.add(permission);
        }
    }

    /**
     * Removes a permission from this role.
     *
     * @param permission permission to remove
     */
    public void removePermission(Permission permission) {
        permissions.remove(permission);
    }

    /**
     * Determines whether this role grants a permission with the given name.
     *
     * @param permissionName permission name to check
     * @return true if the role grants the permission
     */
    public boolean hasPermission(String permissionName) {
        if (permissionName == null) {
            return false;
        }

        return permissions.stream()
                .anyMatch(permission -> permissionName.equals(permission.getName()));
    }
}
