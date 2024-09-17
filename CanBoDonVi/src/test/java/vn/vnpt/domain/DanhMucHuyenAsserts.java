package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DanhMucHuyenAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucHuyenAllPropertiesEquals(DanhMucHuyen expected, DanhMucHuyen actual) {
        assertDanhMucHuyenAutoGeneratedPropertiesEquals(expected, actual);
        assertDanhMucHuyenAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucHuyenAllUpdatablePropertiesEquals(DanhMucHuyen expected, DanhMucHuyen actual) {
        assertDanhMucHuyenUpdatableFieldsEquals(expected, actual);
        assertDanhMucHuyenUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucHuyenAutoGeneratedPropertiesEquals(DanhMucHuyen expected, DanhMucHuyen actual) {
        // empty method
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucHuyenUpdatableFieldsEquals(DanhMucHuyen expected, DanhMucHuyen actual) {
        assertThat(expected)
            .as("Verify DanhMucHuyen relevant properties")
            .satisfies(e -> assertThat(e.getMaHuyen()).as("check maHuyen").isEqualTo(actual.getMaHuyen()))
            .satisfies(e -> assertThat(e.getTenHuyen()).as("check tenHuyen").isEqualTo(actual.getTenHuyen()))
            .satisfies(e -> assertThat(e.getMaTinh()).as("check maTinh").isEqualTo(actual.getMaTinh()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucHuyenUpdatableRelationshipsEquals(DanhMucHuyen expected, DanhMucHuyen actual) {
        // empty method
    }
}