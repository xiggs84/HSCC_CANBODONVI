package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class LoaiChungThucAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiChungThucAllPropertiesEquals(LoaiChungThuc expected, LoaiChungThuc actual) {
        assertLoaiChungThucAutoGeneratedPropertiesEquals(expected, actual);
        assertLoaiChungThucAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiChungThucAllUpdatablePropertiesEquals(LoaiChungThuc expected, LoaiChungThuc actual) {
        assertLoaiChungThucUpdatableFieldsEquals(expected, actual);
        assertLoaiChungThucUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiChungThucAutoGeneratedPropertiesEquals(LoaiChungThuc expected, LoaiChungThuc actual) {
        assertThat(expected)
            .as("Verify LoaiChungThuc auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiChungThucUpdatableFieldsEquals(LoaiChungThuc expected, LoaiChungThuc actual) {
        assertThat(expected)
            .as("Verify LoaiChungThuc relevant properties")
            .satisfies(e -> assertThat(e.getDienGiai()).as("check dienGiai").isEqualTo(actual.getDienGiai()))
            .satisfies(e -> assertThat(e.getKhungGia()).as("check khungGia").isEqualTo(actual.getKhungGia()))
            .satisfies(e -> assertThat(e.getHasBenB()).as("check hasBenB").isEqualTo(actual.getHasBenB()))
            .satisfies(e -> assertThat(e.getHasTaiSan()).as("check hasTaiSan").isEqualTo(actual.getHasTaiSan()))
            .satisfies(e -> assertThat(e.getTrangThai()).as("check trangThai").isEqualTo(actual.getTrangThai()))
            .satisfies(e -> assertThat(e.getFileChungThuc()).as("check fileChungThuc").isEqualTo(actual.getFileChungThuc()))
            .satisfies(e -> assertThat(e.getSrcChungThuc()).as("check srcChungThuc").isEqualTo(actual.getSrcChungThuc()))
            .satisfies(e ->
                assertThat(e.getSrcChungThucContentType())
                    .as("check srcChungThuc contenty type")
                    .isEqualTo(actual.getSrcChungThucContentType())
            )
            .satisfies(e -> assertThat(e.getNgayThaoTac()).as("check ngayThaoTac").isEqualTo(actual.getNgayThaoTac()))
            .satisfies(e -> assertThat(e.getNguoiThaoTac()).as("check nguoiThaoTac").isEqualTo(actual.getNguoiThaoTac()))
            .satisfies(e -> assertThat(e.getIdDonVi()).as("check idDonVi").isEqualTo(actual.getIdDonVi()))
            .satisfies(e -> assertThat(e.getIdLoaiSo()).as("check idLoaiSo").isEqualTo(actual.getIdLoaiSo()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLoaiChungThucUpdatableRelationshipsEquals(LoaiChungThuc expected, LoaiChungThuc actual) {
        // empty method
    }
}
