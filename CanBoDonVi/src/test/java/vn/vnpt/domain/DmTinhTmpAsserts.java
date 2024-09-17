package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DmTinhTmpAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDmTinhTmpAllPropertiesEquals(DmTinhTmp expected, DmTinhTmp actual) {
        assertDmTinhTmpAutoGeneratedPropertiesEquals(expected, actual);
        assertDmTinhTmpAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDmTinhTmpAllUpdatablePropertiesEquals(DmTinhTmp expected, DmTinhTmp actual) {
        assertDmTinhTmpUpdatableFieldsEquals(expected, actual);
        assertDmTinhTmpUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDmTinhTmpAutoGeneratedPropertiesEquals(DmTinhTmp expected, DmTinhTmp actual) {
        assertThat(expected)
            .as("Verify DmTinhTmp auto generated properties")
            .satisfies(e -> assertThat(e.getMaTinh()).as("check maTinh").isEqualTo(actual.getMaTinh()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDmTinhTmpUpdatableFieldsEquals(DmTinhTmp expected, DmTinhTmp actual) {
        assertThat(expected)
            .as("Verify DmTinhTmp relevant properties")
            .satisfies(e -> assertThat(e.getTenTinh()).as("check tenTinh").isEqualTo(actual.getTenTinh()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDmTinhTmpUpdatableRelationshipsEquals(DmTinhTmp expected, DmTinhTmp actual) {
        // empty method
    }
}