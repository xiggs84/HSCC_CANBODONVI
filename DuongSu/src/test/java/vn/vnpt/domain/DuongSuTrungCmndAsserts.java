package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DuongSuTrungCmndAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDuongSuTrungCmndAllPropertiesEquals(DuongSuTrungCmnd expected, DuongSuTrungCmnd actual) {
        assertDuongSuTrungCmndAutoGeneratedPropertiesEquals(expected, actual);
        assertDuongSuTrungCmndAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDuongSuTrungCmndAllUpdatablePropertiesEquals(DuongSuTrungCmnd expected, DuongSuTrungCmnd actual) {
        assertDuongSuTrungCmndUpdatableFieldsEquals(expected, actual);
        assertDuongSuTrungCmndUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDuongSuTrungCmndAutoGeneratedPropertiesEquals(DuongSuTrungCmnd expected, DuongSuTrungCmnd actual) {
        assertThat(expected)
            .as("Verify DuongSuTrungCmnd auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDuongSuTrungCmndUpdatableFieldsEquals(DuongSuTrungCmnd expected, DuongSuTrungCmnd actual) {
        assertThat(expected)
            .as("Verify DuongSuTrungCmnd relevant properties")
            .satisfies(e -> assertThat(e.getTenDuongSu()).as("check tenDuongSu").isEqualTo(actual.getTenDuongSu()))
            .satisfies(e -> assertThat(e.getDiaChi()).as("check diaChi").isEqualTo(actual.getDiaChi()))
            .satisfies(e -> assertThat(e.getTrangThai()).as("check trangThai").isEqualTo(actual.getTrangThai()))
            .satisfies(e -> assertThat(e.getThongTinDs()).as("check thongTinDs").isEqualTo(actual.getThongTinDs()))
            .satisfies(e -> assertThat(e.getNgayThaoTac()).as("check ngayThaoTac").isEqualTo(actual.getNgayThaoTac()))
            .satisfies(e -> assertThat(e.getNguoiThaoTac()).as("check nguoiThaoTac").isEqualTo(actual.getNguoiThaoTac()))
            .satisfies(e -> assertThat(e.getIdDsGoc()).as("check idDsGoc").isEqualTo(actual.getIdDsGoc()))
            .satisfies(e -> assertThat(e.getIdMaster()).as("check idMaster").isEqualTo(actual.getIdMaster()))
            .satisfies(e -> assertThat(e.getIdDonVi()).as("check idDonVi").isEqualTo(actual.getIdDonVi()))
            .satisfies(e -> assertThat(e.getStrSearch()).as("check strSearch").isEqualTo(actual.getStrSearch()))
            .satisfies(e -> assertThat(e.getSoGiayTo()).as("check soGiayTo").isEqualTo(actual.getSoGiayTo()))
            .satisfies(e -> assertThat(e.getIdDuongSuMin()).as("check idDuongSuMin").isEqualTo(actual.getIdDuongSuMin()))
            .satisfies(e -> assertThat(e.getIdMasterMin()).as("check idMasterMin").isEqualTo(actual.getIdMasterMin()))
            .satisfies(e -> assertThat(e.getIdDuongSuMax()).as("check idDuongSuMax").isEqualTo(actual.getIdDuongSuMax()))
            .satisfies(e -> assertThat(e.getIdMasterMax()).as("check idMasterMax").isEqualTo(actual.getIdMasterMax()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDuongSuTrungCmndUpdatableRelationshipsEquals(DuongSuTrungCmnd expected, DuongSuTrungCmnd actual) {
        assertThat(expected)
            .as("Verify DuongSuTrungCmnd relationships")
            .satisfies(e -> assertThat(e.getIdDuongSu()).as("check idDuongSu").isEqualTo(actual.getIdDuongSu()));
    }
}