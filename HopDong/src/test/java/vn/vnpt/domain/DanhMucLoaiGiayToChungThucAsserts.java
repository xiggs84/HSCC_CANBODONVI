package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DanhMucLoaiGiayToChungThucAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucLoaiGiayToChungThucAllPropertiesEquals(
        DanhMucLoaiGiayToChungThuc expected,
        DanhMucLoaiGiayToChungThuc actual
    ) {
        assertDanhMucLoaiGiayToChungThucAutoGeneratedPropertiesEquals(expected, actual);
        assertDanhMucLoaiGiayToChungThucAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucLoaiGiayToChungThucAllUpdatablePropertiesEquals(
        DanhMucLoaiGiayToChungThuc expected,
        DanhMucLoaiGiayToChungThuc actual
    ) {
        assertDanhMucLoaiGiayToChungThucUpdatableFieldsEquals(expected, actual);
        assertDanhMucLoaiGiayToChungThucUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucLoaiGiayToChungThucAutoGeneratedPropertiesEquals(
        DanhMucLoaiGiayToChungThuc expected,
        DanhMucLoaiGiayToChungThuc actual
    ) {
        assertThat(expected)
            .as("Verify DanhMucLoaiGiayToChungThuc auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucLoaiGiayToChungThucUpdatableFieldsEquals(
        DanhMucLoaiGiayToChungThuc expected,
        DanhMucLoaiGiayToChungThuc actual
    ) {
        assertThat(expected)
            .as("Verify DanhMucLoaiGiayToChungThuc relevant properties")
            .satisfies(e -> assertThat(e.getDienGiai()).as("check dienGiai").isEqualTo(actual.getDienGiai()))
            .satisfies(e -> assertThat(e.getIdLoaiSo()).as("check idLoaiSo").isEqualTo(actual.getIdLoaiSo()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucLoaiGiayToChungThucUpdatableRelationshipsEquals(
        DanhMucLoaiGiayToChungThuc expected,
        DanhMucLoaiGiayToChungThuc actual
    ) {
        // empty method
    }
}
