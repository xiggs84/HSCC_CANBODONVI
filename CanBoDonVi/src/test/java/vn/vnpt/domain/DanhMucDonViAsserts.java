package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DanhMucDonViAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucDonViAllPropertiesEquals(DanhMucDonVi expected, DanhMucDonVi actual) {
        assertDanhMucDonViAutoGeneratedPropertiesEquals(expected, actual);
        assertDanhMucDonViAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucDonViAllUpdatablePropertiesEquals(DanhMucDonVi expected, DanhMucDonVi actual) {
        assertDanhMucDonViUpdatableFieldsEquals(expected, actual);
        assertDanhMucDonViUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucDonViAutoGeneratedPropertiesEquals(DanhMucDonVi expected, DanhMucDonVi actual) {
        assertThat(expected)
            .as("Verify DanhMucDonVi auto generated properties")
            .satisfies(e -> assertThat(e.getIdDonVi()).as("check idDonVi").isEqualTo(actual.getIdDonVi()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucDonViUpdatableFieldsEquals(DanhMucDonVi expected, DanhMucDonVi actual) {
        assertThat(expected)
            .as("Verify DanhMucDonVi relevant properties")
            .satisfies(e -> assertThat(e.getTenDonVi()).as("check tenDonVi").isEqualTo(actual.getTenDonVi()))
            .satisfies(e -> assertThat(e.getDiaChi()).as("check diaChi").isEqualTo(actual.getDiaChi()))
            .satisfies(e -> assertThat(e.getNguoiDaiDien()).as("check nguoiDaiDien").isEqualTo(actual.getNguoiDaiDien()))
            .satisfies(e -> assertThat(e.getSoDienThoai()).as("check soDienThoai").isEqualTo(actual.getSoDienThoai()))
            .satisfies(e -> assertThat(e.getIdDonViQl()).as("check idDonViQl").isEqualTo(actual.getIdDonViQl()))
            .satisfies(e -> assertThat(e.getNgayKhaiBao()).as("check ngayKhaiBao").isEqualTo(actual.getNgayKhaiBao()))
            .satisfies(e -> assertThat(e.getTrangThai()).as("check trangThai").isEqualTo(actual.getTrangThai()))
            .satisfies(e -> assertThat(e.getSoNha()).as("check soNha").isEqualTo(actual.getSoNha()))
            .satisfies(e -> assertThat(e.getMaSoThue()).as("check maSoThue").isEqualTo(actual.getMaSoThue()))
            .satisfies(e -> assertThat(e.getHoaDonDt()).as("check hoaDonDt").isEqualTo(actual.getHoaDonDt()))
            .satisfies(e -> assertThat(e.getMaDonViIgate()).as("check maDonViIgate").isEqualTo(actual.getMaDonViIgate()))
            .satisfies(e -> assertThat(e.getMaCoQuanIgate()).as("check maCoQuanIgate").isEqualTo(actual.getMaCoQuanIgate()))
            .satisfies(e -> assertThat(e.getKySo()).as("check kySo").isEqualTo(actual.getKySo()))
            .satisfies(e -> assertThat(e.getQrScan()).as("check qrScan").isEqualTo(actual.getQrScan()))
            .satisfies(e -> assertThat(e.getVerifyIdCard()).as("check verifyIdCard").isEqualTo(actual.getVerifyIdCard()))
            .satisfies(e -> assertThat(e.getIsVerifyFace()).as("check isVerifyFace").isEqualTo(actual.getIsVerifyFace()))
            .satisfies(e -> assertThat(e.getIsElastic()).as("check isElastic").isEqualTo(actual.getIsElastic()))
            .satisfies(e -> assertThat(e.getApikeyCccd()).as("check apikeyCccd").isEqualTo(actual.getApikeyCccd()))
            .satisfies(e -> assertThat(e.getApikeyFace()).as("check apikeyFace").isEqualTo(actual.getApikeyFace()))
            .satisfies(e -> assertThat(e.getVerifyCodeCccd()).as("check verifyCodeCccd").isEqualTo(actual.getVerifyCodeCccd()))
            .satisfies(e -> assertThat(e.getUsernameElastic()).as("check usernameElastic").isEqualTo(actual.getUsernameElastic()))
            .satisfies(e -> assertThat(e.getPasswordElastic()).as("check passwordElastic").isEqualTo(actual.getPasswordElastic()))
            .satisfies(e -> assertThat(e.getIdNhiemVu()).as("check idNhiemVu").isEqualTo(actual.getIdNhiemVu()))
            .satisfies(e -> assertThat(e.getIdLoaiDv()).as("check idLoaiDv").isEqualTo(actual.getIdLoaiDv()))
            .satisfies(e -> assertThat(e.getIdCapQl()).as("check idCapQl").isEqualTo(actual.getIdCapQl()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucDonViUpdatableRelationshipsEquals(DanhMucDonVi expected, DanhMucDonVi actual) {
        assertThat(expected)
            .as("Verify DanhMucDonVi relationships")
            .satisfies(e -> assertThat(e.getCapQuanLy()).as("check capQuanLy").isEqualTo(actual.getCapQuanLy()))
            .satisfies(e -> assertThat(e.getLoaiDonVi()).as("check loaiDonVi").isEqualTo(actual.getLoaiDonVi()))
            .satisfies(e -> assertThat(e.getNhiemVu()).as("check nhiemVu").isEqualTo(actual.getNhiemVu()));
    }
}
