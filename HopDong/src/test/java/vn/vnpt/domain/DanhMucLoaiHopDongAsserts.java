package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DanhMucLoaiHopDongAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucLoaiHopDongAllPropertiesEquals(DanhMucLoaiHopDong expected, DanhMucLoaiHopDong actual) {
        assertDanhMucLoaiHopDongAutoGeneratedPropertiesEquals(expected, actual);
        assertDanhMucLoaiHopDongAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucLoaiHopDongAllUpdatablePropertiesEquals(DanhMucLoaiHopDong expected, DanhMucLoaiHopDong actual) {
        assertDanhMucLoaiHopDongUpdatableFieldsEquals(expected, actual);
        assertDanhMucLoaiHopDongUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucLoaiHopDongAutoGeneratedPropertiesEquals(DanhMucLoaiHopDong expected, DanhMucLoaiHopDong actual) {
        assertThat(expected)
            .as("Verify DanhMucLoaiHopDong auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucLoaiHopDongUpdatableFieldsEquals(DanhMucLoaiHopDong expected, DanhMucLoaiHopDong actual) {
        assertThat(expected)
            .as("Verify DanhMucLoaiHopDong relevant properties")
            .satisfies(e -> assertThat(e.getDienGiai()).as("check dienGiai").isEqualTo(actual.getDienGiai()))
            .satisfies(e -> assertThat(e.getIdVaiTro1()).as("check idVaiTro1").isEqualTo(actual.getIdVaiTro1()))
            .satisfies(e -> assertThat(e.getIdVaiTro2()).as("check idVaiTro2").isEqualTo(actual.getIdVaiTro2()))
            .satisfies(e -> assertThat(e.getFileHopDong()).as("check fileHopDong").isEqualTo(actual.getFileHopDong()))
            .satisfies(e -> assertThat(e.getSrcHopDong()).as("check srcHopDong").isEqualTo(actual.getSrcHopDong()))
            .satisfies(e ->
                assertThat(e.getSrcHopDongContentType()).as("check srcHopDong contenty type").isEqualTo(actual.getSrcHopDongContentType())
            )
            .satisfies(e -> assertThat(e.getDieuKhoan()).as("check dieuKhoan").isEqualTo(actual.getDieuKhoan()))
            .satisfies(e -> assertThat(e.getIdDonVi()).as("check idDonVi").isEqualTo(actual.getIdDonVi()))
            .satisfies(e -> assertThat(e.getTrangThai()).as("check trangThai").isEqualTo(actual.getTrangThai()))
            .satisfies(e -> assertThat(e.getNgayThaoTac()).as("check ngayThaoTac").isEqualTo(actual.getNgayThaoTac()))
            .satisfies(e -> assertThat(e.getNguoiThaoTac()).as("check nguoiThaoTac").isEqualTo(actual.getNguoiThaoTac()))
            .satisfies(e -> assertThat(e.getSrcLoiChung()).as("check srcLoiChung").isEqualTo(actual.getSrcLoiChung()))
            .satisfies(e ->
                assertThat(e.getSrcLoiChungContentType())
                    .as("check srcLoiChung contenty type")
                    .isEqualTo(actual.getSrcLoiChungContentType())
            )
            .satisfies(e -> assertThat(e.getIdNhomHopDong()).as("check idNhomHopDong").isEqualTo(actual.getIdNhomHopDong()))
            .satisfies(e -> assertThat(e.getFileLoiChung()).as("check fileLoiChung").isEqualTo(actual.getFileLoiChung()))
            .satisfies(e -> assertThat(e.getChuyenTaiSan()).as("check chuyenTaiSan").isEqualTo(actual.getChuyenTaiSan()))
            .satisfies(e -> assertThat(e.getLoaiSuaDoi()).as("check loaiSuaDoi").isEqualTo(actual.getLoaiSuaDoi()))
            .satisfies(e -> assertThat(e.getLoaiHuyBo()).as("check loaiHuyBo").isEqualTo(actual.getLoaiHuyBo()))
            .satisfies(e -> assertThat(e.getTrangThaiDuyet()).as("check trangThaiDuyet").isEqualTo(actual.getTrangThaiDuyet()))
            .satisfies(e -> assertThat(e.getIdPhanLoaiHopDong()).as("check idPhanLoaiHopDong").isEqualTo(actual.getIdPhanLoaiHopDong()))
            .satisfies(e -> assertThat(e.getSrcCv()).as("check srcCv").isEqualTo(actual.getSrcCv()))
            .satisfies(e -> assertThat(e.getSrcTb()).as("check srcTb").isEqualTo(actual.getSrcTb()))
            .satisfies(e -> assertThat(e.getSrcTtpc()).as("check srcTtpc").isEqualTo(actual.getSrcTtpc()))
            .satisfies(e -> assertThat(e.getDgTen()).as("check dgTen").isEqualTo(actual.getDgTen()))
            .satisfies(e -> assertThat(e.getNhomTen()).as("check nhomTen").isEqualTo(actual.getNhomTen()))
            .satisfies(e -> assertThat(e.getIdVaiTro3()).as("check idVaiTro3").isEqualTo(actual.getIdVaiTro3()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucLoaiHopDongUpdatableRelationshipsEquals(DanhMucLoaiHopDong expected, DanhMucLoaiHopDong actual) {
        // empty method
    }
}