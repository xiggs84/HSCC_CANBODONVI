package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DanhMucCanBoAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucCanBoAllPropertiesEquals(DanhMucCanBo expected, DanhMucCanBo actual) {
        assertDanhMucCanBoAutoGeneratedPropertiesEquals(expected, actual);
        assertDanhMucCanBoAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucCanBoAllUpdatablePropertiesEquals(DanhMucCanBo expected, DanhMucCanBo actual) {
        assertDanhMucCanBoUpdatableFieldsEquals(expected, actual);
        assertDanhMucCanBoUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucCanBoAutoGeneratedPropertiesEquals(DanhMucCanBo expected, DanhMucCanBo actual) {
        assertThat(expected)
            .as("Verify DanhMucCanBo auto generated properties")
            .satisfies(e -> assertThat(e.getIdCanBo()).as("check idCanBo").isEqualTo(actual.getIdCanBo()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucCanBoUpdatableFieldsEquals(DanhMucCanBo expected, DanhMucCanBo actual) {
        assertThat(expected)
            .as("Verify DanhMucCanBo relevant properties")
            .satisfies(e -> assertThat(e.getTenCanBo()).as("check tenCanBo").isEqualTo(actual.getTenCanBo()))
            .satisfies(e -> assertThat(e.getDiaChi()).as("check diaChi").isEqualTo(actual.getDiaChi()))
            .satisfies(e -> assertThat(e.getNamSinh()).as("check namSinh").isEqualTo(actual.getNamSinh()))
            .satisfies(e -> assertThat(e.getEmail()).as("check email").isEqualTo(actual.getEmail()))
            .satisfies(e -> assertThat(e.getSoDienThoai()).as("check soDienThoai").isEqualTo(actual.getSoDienThoai()))
            .satisfies(e -> assertThat(e.getSoCmnd()).as("check soCmnd").isEqualTo(actual.getSoCmnd()))
            .satisfies(e -> assertThat(e.getTenDangNhap()).as("check tenDangNhap").isEqualTo(actual.getTenDangNhap()))
            .satisfies(e -> assertThat(e.getMatKhau()).as("check matKhau").isEqualTo(actual.getMatKhau()))
            .satisfies(e -> assertThat(e.getTrangThai()).as("check trangThai").isEqualTo(actual.getTrangThai()))
            .satisfies(e -> assertThat(e.getClientId()).as("check clientId").isEqualTo(actual.getClientId()))
            .satisfies(e -> assertThat(e.getClientSecret()).as("check clientSecret").isEqualTo(actual.getClientSecret()))
            .satisfies(e -> assertThat(e.getUsernameKyso()).as("check usernameKyso").isEqualTo(actual.getUsernameKyso()))
            .satisfies(e -> assertThat(e.getPasswordKyso()).as("check passwordKyso").isEqualTo(actual.getPasswordKyso()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucCanBoUpdatableRelationshipsEquals(DanhMucCanBo expected, DanhMucCanBo actual) {
        // empty method
    }
}
