package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class LoaiDonViAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiDonViAllPropertiesEquals(LoaiDonVi expected, LoaiDonVi actual) {
        assertLoaiDonViAutoGeneratedPropertiesEquals(expected, actual);
        assertLoaiDonViAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiDonViAllUpdatablePropertiesEquals(LoaiDonVi expected, LoaiDonVi actual) {
        assertLoaiDonViUpdatableFieldsEquals(expected, actual);
        assertLoaiDonViUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiDonViAutoGeneratedPropertiesEquals(LoaiDonVi expected, LoaiDonVi actual) {
        // empty method
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiDonViUpdatableFieldsEquals(LoaiDonVi expected, LoaiDonVi actual) {
        assertThat(expected)
            .as("Verify LoaiDonVi relevant properties")
            .satisfies(e -> assertThat(e.getIdLoaiDv()).as("check idLoaiDv").isEqualTo(actual.getIdLoaiDv()))
            .satisfies(e -> assertThat(e.getTenLoaiDv()).as("check tenLoaiDv").isEqualTo(actual.getTenLoaiDv()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiDonViUpdatableRelationshipsEquals(LoaiDonVi expected, LoaiDonVi actual) {
        // empty method
    }
}