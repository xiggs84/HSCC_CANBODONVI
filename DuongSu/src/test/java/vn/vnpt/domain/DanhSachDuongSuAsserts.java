package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DanhSachDuongSuAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhSachDuongSuAllPropertiesEquals(DanhSachDuongSu expected, DanhSachDuongSu actual) {
        assertDanhSachDuongSuAutoGeneratedPropertiesEquals(expected, actual);
        assertDanhSachDuongSuAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhSachDuongSuAllUpdatablePropertiesEquals(DanhSachDuongSu expected, DanhSachDuongSu actual) {
        assertDanhSachDuongSuUpdatableFieldsEquals(expected, actual);
        assertDanhSachDuongSuUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhSachDuongSuAutoGeneratedPropertiesEquals(DanhSachDuongSu expected, DanhSachDuongSu actual) {
        assertThat(expected)
            .as("Verify DanhSachDuongSu auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhSachDuongSuUpdatableFieldsEquals(DanhSachDuongSu expected, DanhSachDuongSu actual) {
        assertThat(expected)
            .as("Verify DanhSachDuongSu relevant properties")
            .satisfies(e -> assertThat(e.getTenDuongSu()).as("check tenDuongSu").isEqualTo(actual.getTenDuongSu()))
            .satisfies(e -> assertThat(e.getDiaChi()).as("check diaChi").isEqualTo(actual.getDiaChi()))
            .satisfies(e -> assertThat(e.getTrangThai()).as("check trangThai").isEqualTo(actual.getTrangThai()))
            .satisfies(e -> assertThat(e.getNgayThaoTac()).as("check ngayThaoTac").isEqualTo(actual.getNgayThaoTac()))
            .satisfies(e -> assertThat(e.getNguoiThaoTac()).as("check nguoiThaoTac").isEqualTo(actual.getNguoiThaoTac()))
            .satisfies(e -> assertThat(e.getIdDsGoc()).as("check idDsGoc").isEqualTo(actual.getIdDsGoc()))
            .satisfies(e -> assertThat(e.getIdMaster()).as("check idMaster").isEqualTo(actual.getIdMaster()))
            .satisfies(e -> assertThat(e.getIdDonVi()).as("check idDonVi").isEqualTo(actual.getIdDonVi()))
            .satisfies(e -> assertThat(e.getStrSearch()).as("check strSearch").isEqualTo(actual.getStrSearch()))
            .satisfies(e -> assertThat(e.getSoGiayTo()).as("check soGiayTo").isEqualTo(actual.getSoGiayTo()))
            .satisfies(e -> assertThat(e.getIdLoaiNganChan()).as("check idLoaiNganChan").isEqualTo(actual.getIdLoaiNganChan()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhSachDuongSuUpdatableRelationshipsEquals(DanhSachDuongSu expected, DanhSachDuongSu actual) {
        assertThat(expected)
            .as("Verify DanhSachDuongSu relationships")
            .satisfies(e -> assertThat(e.getIdDuongSu()).as("check idDuongSu").isEqualTo(actual.getIdDuongSu()));
    }
}
