package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DmTaiSanAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDmTaiSanAllPropertiesEquals(DmTaiSan expected, DmTaiSan actual) {
        assertDmTaiSanAutoGeneratedPropertiesEquals(expected, actual);
        assertDmTaiSanAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDmTaiSanAllUpdatablePropertiesEquals(DmTaiSan expected, DmTaiSan actual) {
        assertDmTaiSanUpdatableFieldsEquals(expected, actual);
        assertDmTaiSanUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDmTaiSanAutoGeneratedPropertiesEquals(DmTaiSan expected, DmTaiSan actual) {
        assertThat(expected)
            .as("Verify DmTaiSan auto generated properties")
            .satisfies(e -> assertThat(e.getIdTaiSan()).as("check idTaiSan").isEqualTo(actual.getIdTaiSan()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDmTaiSanUpdatableFieldsEquals(DmTaiSan expected, DmTaiSan actual) {
        assertThat(expected)
            .as("Verify DmTaiSan relevant properties")
            .satisfies(e -> assertThat(e.getTenTaiSan()).as("check tenTaiSan").isEqualTo(actual.getTenTaiSan()))
            .satisfies(e -> assertThat(e.getTrangThai()).as("check trangThai").isEqualTo(actual.getTrangThai()))
            .satisfies(e -> assertThat(e.getThongTinTs()).as("check thongTinTs").isEqualTo(actual.getThongTinTs()))
            .satisfies(e -> assertThat(e.getGhiChu()).as("check ghiChu").isEqualTo(actual.getGhiChu()))
            .satisfies(e -> assertThat(e.getNgayThaoTac()).as("check ngayThaoTac").isEqualTo(actual.getNgayThaoTac()))
            .satisfies(e -> assertThat(e.getNguoiThaoTac()).as("check nguoiThaoTac").isEqualTo(actual.getNguoiThaoTac()))
            .satisfies(e -> assertThat(e.getIdDuongSu()).as("check idDuongSu").isEqualTo(actual.getIdDuongSu()))
            .satisfies(e -> assertThat(e.getIdTsGoc()).as("check idTsGoc").isEqualTo(actual.getIdTsGoc()))
            .satisfies(e -> assertThat(e.getMaTaiSan()).as("check maTaiSan").isEqualTo(actual.getMaTaiSan()))
            .satisfies(e -> assertThat(e.getIdLoaiNganChan()).as("check idLoaiNganChan").isEqualTo(actual.getIdLoaiNganChan()))
            .satisfies(e -> assertThat(e.getNgayBdNganChan()).as("check ngayBdNganChan").isEqualTo(actual.getNgayBdNganChan()))
            .satisfies(e -> assertThat(e.getNgayKtNganChan()).as("check ngayKtNganChan").isEqualTo(actual.getNgayKtNganChan()))
            .satisfies(e -> assertThat(e.getIdMaster()).as("check idMaster").isEqualTo(actual.getIdMaster()))
            .satisfies(e -> assertThat(e.getStrSearch()).as("check strSearch").isEqualTo(actual.getStrSearch()))
            .satisfies(e -> assertThat(e.getIdDonVi()).as("check idDonVi").isEqualTo(actual.getIdDonVi()))
            .satisfies(e -> assertThat(e.getSoHsCv()).as("check soHsCv").isEqualTo(actual.getSoHsCv()))
            .satisfies(e -> assertThat(e.getSoCc()).as("check soCc").isEqualTo(actual.getSoCc()))
            .satisfies(e -> assertThat(e.getSoVaoSo()).as("check soVaoSo").isEqualTo(actual.getSoVaoSo()))
            .satisfies(e -> assertThat(e.getMoTa()).as("check moTa").isEqualTo(actual.getMoTa()))
            .satisfies(e -> assertThat(e.getLoaiNganChan()).as("check loaiNganChan").isEqualTo(actual.getLoaiNganChan()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDmTaiSanUpdatableRelationshipsEquals(DmTaiSan expected, DmTaiSan actual) {
        // empty method
    }
}
