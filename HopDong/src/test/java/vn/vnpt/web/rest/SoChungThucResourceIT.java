package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.SoChungThucAsserts.*;
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
import vn.vnpt.domain.SoChungThuc;
import vn.vnpt.repository.SoChungThucRepository;
import vn.vnpt.service.dto.SoChungThucDTO;
import vn.vnpt.service.mapper.SoChungThucMapper;

/**
 * Integration tests for the {@link SoChungThucResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SoChungThucResourceIT {

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final String DEFAULT_TEN_SO = "AAAAAAAAAA";
    private static final String UPDATED_TEN_SO = "BBBBBBBBBB";

    private static final Long DEFAULT_GIA_TRI = 1L;
    private static final Long UPDATED_GIA_TRI = 2L;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String DEFAULT_ID_LOAI_SO = "AAAAAAAAAA";
    private static final String UPDATED_ID_LOAI_SO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/so-chung-thucs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SoChungThucRepository soChungThucRepository;

    @Autowired
    private SoChungThucMapper soChungThucMapper;

    @Autowired
    private MockMvc restSoChungThucMockMvc;

    private SoChungThuc soChungThuc;

    private SoChungThuc insertedSoChungThuc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoChungThuc createEntity() {
        return new SoChungThuc()
            .idDonVi(DEFAULT_ID_DON_VI)
            .tenSo(DEFAULT_TEN_SO)
            .giaTri(DEFAULT_GIA_TRI)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .trangThai(DEFAULT_TRANG_THAI)
            .idLoaiSo(DEFAULT_ID_LOAI_SO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoChungThuc createUpdatedEntity() {
        return new SoChungThuc()
            .idDonVi(UPDATED_ID_DON_VI)
            .tenSo(UPDATED_TEN_SO)
            .giaTri(UPDATED_GIA_TRI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);
    }

    @BeforeEach
    public void initTest() {
        soChungThuc = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedSoChungThuc != null) {
            soChungThucRepository.delete(insertedSoChungThuc);
            insertedSoChungThuc = null;
        }
    }

    @Test
    void createSoChungThuc() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SoChungThuc
        SoChungThucDTO soChungThucDTO = soChungThucMapper.toDto(soChungThuc);
        var returnedSoChungThucDTO = om.readValue(
            restSoChungThucMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soChungThucDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SoChungThucDTO.class
        );

        // Validate the SoChungThuc in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedSoChungThuc = soChungThucMapper.toEntity(returnedSoChungThucDTO);
        assertSoChungThucUpdatableFieldsEquals(returnedSoChungThuc, getPersistedSoChungThuc(returnedSoChungThuc));

        insertedSoChungThuc = returnedSoChungThuc;
    }

    @Test
    void createSoChungThucWithExistingId() throws Exception {
        // Create the SoChungThuc with an existing ID
        soChungThuc.setId("existing_id");
        SoChungThucDTO soChungThucDTO = soChungThucMapper.toDto(soChungThuc);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSoChungThucMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soChungThucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SoChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSoChungThucs() throws Exception {
        // Initialize the database
        insertedSoChungThuc = soChungThucRepository.save(soChungThuc);

        // Get all the soChungThucList
        restSoChungThucMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(soChungThuc.getId())))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].tenSo").value(hasItem(DEFAULT_TEN_SO)))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiSo").value(hasItem(DEFAULT_ID_LOAI_SO)));
    }

    @Test
    void getSoChungThuc() throws Exception {
        // Initialize the database
        insertedSoChungThuc = soChungThucRepository.save(soChungThuc);

        // Get the soChungThuc
        restSoChungThucMockMvc
            .perform(get(ENTITY_API_URL_ID, soChungThuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(soChungThuc.getId()))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.tenSo").value(DEFAULT_TEN_SO))
            .andExpect(jsonPath("$.giaTri").value(DEFAULT_GIA_TRI.intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.idLoaiSo").value(DEFAULT_ID_LOAI_SO));
    }

    @Test
    void getNonExistingSoChungThuc() throws Exception {
        // Get the soChungThuc
        restSoChungThucMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingSoChungThuc() throws Exception {
        // Initialize the database
        insertedSoChungThuc = soChungThucRepository.save(soChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soChungThuc
        SoChungThuc updatedSoChungThuc = soChungThucRepository.findById(soChungThuc.getId()).orElseThrow();
        updatedSoChungThuc
            .idDonVi(UPDATED_ID_DON_VI)
            .tenSo(UPDATED_TEN_SO)
            .giaTri(UPDATED_GIA_TRI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);
        SoChungThucDTO soChungThucDTO = soChungThucMapper.toDto(updatedSoChungThuc);

        restSoChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soChungThucDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soChungThucDTO))
            )
            .andExpect(status().isOk());

        // Validate the SoChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSoChungThucToMatchAllProperties(updatedSoChungThuc);
    }

    @Test
    void putNonExistingSoChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soChungThuc.setId(UUID.randomUUID().toString());

        // Create the SoChungThuc
        SoChungThucDTO soChungThucDTO = soChungThucMapper.toDto(soChungThuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soChungThucDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSoChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soChungThuc.setId(UUID.randomUUID().toString());

        // Create the SoChungThuc
        SoChungThucDTO soChungThucDTO = soChungThucMapper.toDto(soChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSoChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soChungThuc.setId(UUID.randomUUID().toString());

        // Create the SoChungThuc
        SoChungThucDTO soChungThucDTO = soChungThucMapper.toDto(soChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoChungThucMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soChungThucDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSoChungThucWithPatch() throws Exception {
        // Initialize the database
        insertedSoChungThuc = soChungThucRepository.save(soChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soChungThuc using partial update
        SoChungThuc partialUpdatedSoChungThuc = new SoChungThuc();
        partialUpdatedSoChungThuc.setId(soChungThuc.getId());

        partialUpdatedSoChungThuc
            .tenSo(UPDATED_TEN_SO)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idLoaiSo(UPDATED_ID_LOAI_SO);

        restSoChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoChungThuc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSoChungThuc))
            )
            .andExpect(status().isOk());

        // Validate the SoChungThuc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSoChungThucUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSoChungThuc, soChungThuc),
            getPersistedSoChungThuc(soChungThuc)
        );
    }

    @Test
    void fullUpdateSoChungThucWithPatch() throws Exception {
        // Initialize the database
        insertedSoChungThuc = soChungThucRepository.save(soChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soChungThuc using partial update
        SoChungThuc partialUpdatedSoChungThuc = new SoChungThuc();
        partialUpdatedSoChungThuc.setId(soChungThuc.getId());

        partialUpdatedSoChungThuc
            .idDonVi(UPDATED_ID_DON_VI)
            .tenSo(UPDATED_TEN_SO)
            .giaTri(UPDATED_GIA_TRI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);

        restSoChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoChungThuc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSoChungThuc))
            )
            .andExpect(status().isOk());

        // Validate the SoChungThuc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSoChungThucUpdatableFieldsEquals(partialUpdatedSoChungThuc, getPersistedSoChungThuc(partialUpdatedSoChungThuc));
    }

    @Test
    void patchNonExistingSoChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soChungThuc.setId(UUID.randomUUID().toString());

        // Create the SoChungThuc
        SoChungThucDTO soChungThucDTO = soChungThucMapper.toDto(soChungThuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, soChungThucDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(soChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSoChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soChungThuc.setId(UUID.randomUUID().toString());

        // Create the SoChungThuc
        SoChungThucDTO soChungThucDTO = soChungThucMapper.toDto(soChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(soChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSoChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soChungThuc.setId(UUID.randomUUID().toString());

        // Create the SoChungThuc
        SoChungThucDTO soChungThucDTO = soChungThucMapper.toDto(soChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoChungThucMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(soChungThucDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSoChungThuc() throws Exception {
        // Initialize the database
        insertedSoChungThuc = soChungThucRepository.save(soChungThuc);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the soChungThuc
        restSoChungThucMockMvc
            .perform(delete(ENTITY_API_URL_ID, soChungThuc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return soChungThucRepository.count();
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

    protected SoChungThuc getPersistedSoChungThuc(SoChungThuc soChungThuc) {
        return soChungThucRepository.findById(soChungThuc.getId()).orElseThrow();
    }

    protected void assertPersistedSoChungThucToMatchAllProperties(SoChungThuc expectedSoChungThuc) {
        assertSoChungThucAllPropertiesEquals(expectedSoChungThuc, getPersistedSoChungThuc(expectedSoChungThuc));
    }

    protected void assertPersistedSoChungThucToMatchUpdatableProperties(SoChungThuc expectedSoChungThuc) {
        assertSoChungThucAllUpdatablePropertiesEquals(expectedSoChungThuc, getPersistedSoChungThuc(expectedSoChungThuc));
    }
}
