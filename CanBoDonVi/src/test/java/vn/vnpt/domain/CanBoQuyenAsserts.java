package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class CanBoQuyenAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCanBoQuyenAllPropertiesEquals(CanBoQuyen expected, CanBoQuyen actual) {
        assertCanBoQuyenAutoGeneratedPropertiesEquals(expected, actual);
        assertCanBoQuyenAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCanBoQuyenAllUpdatablePropertiesEquals(CanBoQuyen expected, CanBoQuyen actual) {
        assertCanBoQuyenUpdatableFieldsEquals(expected, actual);
        assertCanBoQuyenUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCanBoQuyenAutoGeneratedPropertiesEquals(CanBoQuyen expected, CanBoQuyen actual) {
        assertThat(expected)
            .as("Verify CanBoQuyen auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCanBoQuyenUpdatableFieldsEquals(CanBoQuyen expected, CanBoQuyen actual) {
        // empty method

    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCanBoQuyenUpdatableRelationshipsEquals(CanBoQuyen expected, CanBoQuyen actual) {
        assertThat(expected)
            .as("Verify CanBoQuyen relationships")
            .satisfies(e -> assertThat(e.getDanhMucDonVi()).as("check danhMucDonVi").isEqualTo(actual.getDanhMucDonVi()));
    }
}
