package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucTuVietTatAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import vn.vnpt.IntegrationTest;
import vn.vnpt.domain.DanhMucTuVietTat;
import vn.vnpt.repository.DanhMucTuVietTatRepository;
import vn.vnpt.service.dto.DanhMucTuVietTatDTO;
import vn.vnpt.service.mapper.DanhMucTuVietTatMapper;

/**
 * Integration tests for the {@link DanhMucTuVietTatResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucTuVietTatResourceIT {

    private static final String DEFAULT_TU_VIET_TAT = "AAAAAAAAAA";
    private static final String UPDATED_TU_VIET_TAT = "BBBBBBBBBB";

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String ENTITY_API_URL = "/api/danh-muc-tu-viet-tats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucTuVietTatRepository danhMucTuVietTatRepository;

    @Autowired
    private DanhMucTuVietTatMapper danhMucTuVietTatMapper;

    @Autowired
    private MockMvc restDanhMucTuVietTatMockMvc;

    private DanhMucTuVietTat danhMucTuVietTat;

    private DanhMucTuVietTat insertedDanhMucTuVietTat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucTuVietTat createEntity() {
        return new DanhMucTuVietTat()
            .tuVietTat(DEFAULT_TU_VIET_TAT)
            .dienGiai(DEFAULT_DIEN_GIAI)
            .idDonVi(DEFAULT_ID_DON_VI)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .trangThai(DEFAULT_TRANG_THAI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucTuVietTat createUpdatedEntity() {
        return new DanhMucTuVietTat()
            .tuVietTat(UPDATED_TU_VIET_TAT)
            .dienGiai(UPDATED_DIEN_GIAI)
            .idDonVi(UPDATED_ID_DON_VI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI);
    }

    @BeforeEach
    public void initTest() {
        danhMucTuVietTat = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucTuVietTat != null) {
            danhMucTuVietTatRepository.delete(insertedDanhMucTuVietTat);
            insertedDanhMucTuVietTat = null;
        }
    }

    @Test
    void createDanhMucTuVietTat() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucTuVietTat
        DanhMucTuVietTatDTO danhMucTuVietTatDTO = danhMucTuVietTatMapper.toDto(danhMucTuVietTat);
        var returnedDanhMucTuVietTatDTO = om.readValue(
            restDanhMucTuVietTatMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucTuVietTatDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucTuVietTatDTO.class
        );

        // Validate the DanhMucTuVietTat in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucTuVietTat = danhMucTuVietTatMapper.toEntity(returnedDanhMucTuVietTatDTO);
        assertDanhMucTuVietTatUpdatableFieldsEquals(returnedDanhMucTuVietTat, getPersistedDanhMucTuVietTat(returnedDanhMucTuVietTat));

        insertedDanhMucTuVietTat = returnedDanhMucTuVietTat;
    }

    @Test
    void createDanhMucTuVietTatWithExistingId() throws Exception {
        // Create the DanhMucTuVietTat with an existing ID
        danhMucTuVietTat.setId("existing_id");
        DanhMucTuVietTatDTO danhMucTuVietTatDTO = danhMucTuVietTatMapper.toDto(danhMucTuVietTat);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucTuVietTatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucTuVietTatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucTuVietTat in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDanhMucTuVietTats() throws Exception {
        // Initialize the database
        insertedDanhMucTuVietTat = danhMucTuVietTatRepository.save(danhMucTuVietTat);

        // Get all the danhMucTuVietTatList
        restDanhMucTuVietTatMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMucTuVietTat.getId())))
            .andExpect(jsonPath("$.[*].tuVietTat").value(hasItem(DEFAULT_TU_VIET_TAT)))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));
    }

    @Test
    void getDanhMucTuVietTat() throws Exception {
        // Initialize the database
        insertedDanhMucTuVietTat = danhMucTuVietTatRepository.save(danhMucTuVietTat);

        // Get the danhMucTuVietTat
        restDanhMucTuVietTatMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucTuVietTat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhMucTuVietTat.getId()))
            .andExpect(jsonPath("$.tuVietTat").value(DEFAULT_TU_VIET_TAT))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()));
    }

    @Test
    void getNonExistingDanhMucTuVietTat() throws Exception {
        // Get the danhMucTuVietTat
        restDanhMucTuVietTatMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDanhMucTuVietTat() throws Exception {
        // Initialize the database
        insertedDanhMucTuVietTat = danhMucTuVietTatRepository.save(danhMucTuVietTat);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucTuVietTat
        DanhMucTuVietTat updatedDanhMucTuVietTat = danhMucTuVietTatRepository.findById(danhMucTuVietTat.getId()).orElseThrow();
        updatedDanhMucTuVietTat
            .tuVietTat(UPDATED_TU_VIET_TAT)
            .dienGiai(UPDATED_DIEN_GIAI)
            .idDonVi(UPDATED_ID_DON_VI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI);
        DanhMucTuVietTatDTO danhMucTuVietTatDTO = danhMucTuVietTatMapper.toDto(updatedDanhMucTuVietTat);

        restDanhMucTuVietTatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucTuVietTatDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucTuVietTatDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucTuVietTat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucTuVietTatToMatchAllProperties(updatedDanhMucTuVietTat);
    }

    @Test
    void putNonExistingDanhMucTuVietTat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTuVietTat.setId(UUID.randomUUID().toString());

        // Create the DanhMucTuVietTat
        DanhMucTuVietTatDTO danhMucTuVietTatDTO = danhMucTuVietTatMapper.toDto(danhMucTuVietTat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucTuVietTatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucTuVietTatDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucTuVietTatDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucTuVietTat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDanhMucTuVietTat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTuVietTat.setId(UUID.randomUUID().toString());

        // Create the DanhMucTuVietTat
        DanhMucTuVietTatDTO danhMucTuVietTatDTO = danhMucTuVietTatMapper.toDto(danhMucTuVietTat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucTuVietTatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucTuVietTatDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucTuVietTat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDanhMucTuVietTat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTuVietTat.setId(UUID.randomUUID().toString());

        // Create the DanhMucTuVietTat
        DanhMucTuVietTatDTO danhMucTuVietTatDTO = danhMucTuVietTatMapper.toDto(danhMucTuVietTat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucTuVietTatMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucTuVietTatDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucTuVietTat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDanhMucTuVietTatWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucTuVietTat = danhMucTuVietTatRepository.save(danhMucTuVietTat);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucTuVietTat using partial update
        DanhMucTuVietTat partialUpdatedDanhMucTuVietTat = new DanhMucTuVietTat();
        partialUpdatedDanhMucTuVietTat.setId(danhMucTuVietTat.getId());

        partialUpdatedDanhMucTuVietTat
            .tuVietTat(UPDATED_TU_VIET_TAT)
            .dienGiai(UPDATED_DIEN_GIAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI);

        restDanhMucTuVietTatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucTuVietTat.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucTuVietTat))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucTuVietTat in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucTuVietTatUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucTuVietTat, danhMucTuVietTat),
            getPersistedDanhMucTuVietTat(danhMucTuVietTat)
        );
    }

    @Test
    void fullUpdateDanhMucTuVietTatWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucTuVietTat = danhMucTuVietTatRepository.save(danhMucTuVietTat);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucTuVietTat using partial update
        DanhMucTuVietTat partialUpdatedDanhMucTuVietTat = new DanhMucTuVietTat();
        partialUpdatedDanhMucTuVietTat.setId(danhMucTuVietTat.getId());

        partialUpdatedDanhMucTuVietTat
            .tuVietTat(UPDATED_TU_VIET_TAT)
            .dienGiai(UPDATED_DIEN_GIAI)
            .idDonVi(UPDATED_ID_DON_VI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI);

        restDanhMucTuVietTatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucTuVietTat.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucTuVietTat))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucTuVietTat in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucTuVietTatUpdatableFieldsEquals(
            partialUpdatedDanhMucTuVietTat,
            getPersistedDanhMucTuVietTat(partialUpdatedDanhMucTuVietTat)
        );
    }

    @Test
    void patchNonExistingDanhMucTuVietTat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTuVietTat.setId(UUID.randomUUID().toString());

        // Create the DanhMucTuVietTat
        DanhMucTuVietTatDTO danhMucTuVietTatDTO = danhMucTuVietTatMapper.toDto(danhMucTuVietTat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucTuVietTatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucTuVietTatDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucTuVietTatDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucTuVietTat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDanhMucTuVietTat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTuVietTat.setId(UUID.randomUUID().toString());

        // Create the DanhMucTuVietTat
        DanhMucTuVietTatDTO danhMucTuVietTatDTO = danhMucTuVietTatMapper.toDto(danhMucTuVietTat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucTuVietTatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucTuVietTatDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucTuVietTat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDanhMucTuVietTat() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTuVietTat.setId(UUID.randomUUID().toString());

        // Create the DanhMucTuVietTat
        DanhMucTuVietTatDTO danhMucTuVietTatDTO = danhMucTuVietTatMapper.toDto(danhMucTuVietTat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucTuVietTatMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucTuVietTatDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucTuVietTat in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDanhMucTuVietTat() throws Exception {
        // Initialize the database
        insertedDanhMucTuVietTat = danhMucTuVietTatRepository.save(danhMucTuVietTat);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucTuVietTat
        restDanhMucTuVietTatMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucTuVietTat.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucTuVietTatRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected DanhMucTuVietTat getPersistedDanhMucTuVietTat(DanhMucTuVietTat danhMucTuVietTat) {
        return danhMucTuVietTatRepository.findById(danhMucTuVietTat.getId()).orElseThrow();
    }

    protected void assertPersistedDanhMucTuVietTatToMatchAllProperties(DanhMucTuVietTat expectedDanhMucTuVietTat) {
        assertDanhMucTuVietTatAllPropertiesEquals(expectedDanhMucTuVietTat, getPersistedDanhMucTuVietTat(expectedDanhMucTuVietTat));
    }

    protected void assertPersistedDanhMucTuVietTatToMatchUpdatableProperties(DanhMucTuVietTat expectedDanhMucTuVietTat) {
        assertDanhMucTuVietTatAllUpdatablePropertiesEquals(
            expectedDanhMucTuVietTat,
            getPersistedDanhMucTuVietTat(expectedDanhMucTuVietTat)
        );
    }
}
