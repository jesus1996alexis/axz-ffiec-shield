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
@ToString
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
}
