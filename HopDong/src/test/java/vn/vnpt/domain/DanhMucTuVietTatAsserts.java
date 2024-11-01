package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DanhMucTuVietTatAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucTuVietTatAllPropertiesEquals(DanhMucTuVietTat expected, DanhMucTuVietTat actual) {
        assertDanhMucTuVietTatAutoGeneratedPropertiesEquals(expected, actual);
        assertDanhMucTuVietTatAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucTuVietTatAllUpdatablePropertiesEquals(DanhMucTuVietTat expected, DanhMucTuVietTat actual) {
        assertDanhMucTuVietTatUpdatableFieldsEquals(expected, actual);
        assertDanhMucTuVietTatUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucTuVietTatAutoGeneratedPropertiesEquals(DanhMucTuVietTat expected, DanhMucTuVietTat actual) {
        assertThat(expected)
            .as("Verify DanhMucTuVietTat auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucTuVietTatUpdatableFieldsEquals(DanhMucTuVietTat expected, DanhMucTuVietTat actual) {
        assertThat(expected)
            .as("Verify DanhMucTuVietTat relevant properties")
            .satisfies(e -> assertThat(e.getTuVietTat()).as("check tuVietTat").isEqualTo(actual.getTuVietTat()))
            .satisfies(e -> assertThat(e.getDienGiai()).as("check dienGiai").isEqualTo(actual.getDienGiai()))
            .satisfies(e -> assertThat(e.getIdDonVi()).as("check idDonVi").isEqualTo(actual.getIdDonVi()))
            .satisfies(e -> assertThat(e.getNguoiThaoTac()).as("check nguoiThaoTac").isEqualTo(actual.getNguoiThaoTac()))
            .satisfies(e -> assertThat(e.getNgayThaoTac()).as("check ngayThaoTac").isEqualTo(actual.getNgayThaoTac()))
            .satisfies(e -> assertThat(e.getTrangThai()).as("check trangThai").isEqualTo(actual.getTrangThai()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucTuVietTatUpdatableRelationshipsEquals(DanhMucTuVietTat expected, DanhMucTuVietTat actual) {
        // empty method
    }
}