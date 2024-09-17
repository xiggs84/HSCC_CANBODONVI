package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DanhMucTinhAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucTinhAllPropertiesEquals(DanhMucTinh expected, DanhMucTinh actual) {
        assertDanhMucTinhAutoGeneratedPropertiesEquals(expected, actual);
        assertDanhMucTinhAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucTinhAllUpdatablePropertiesEquals(DanhMucTinh expected, DanhMucTinh actual) {
        assertDanhMucTinhUpdatableFieldsEquals(expected, actual);
        assertDanhMucTinhUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucTinhAutoGeneratedPropertiesEquals(DanhMucTinh expected, DanhMucTinh actual) {
        // empty method
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucTinhUpdatableFieldsEquals(DanhMucTinh expected, DanhMucTinh actual) {
        assertThat(expected)
            .as("Verify DanhMucTinh relevant properties")
            .satisfies(e -> assertThat(e.getMaTinh()).as("check maTinh").isEqualTo(actual.getMaTinh()))
            .satisfies(e -> assertThat(e.getTenTinh()).as("check tenTinh").isEqualTo(actual.getTenTinh()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucTinhUpdatableRelationshipsEquals(DanhMucTinh expected, DanhMucTinh actual) {
        // empty method
    }
}
