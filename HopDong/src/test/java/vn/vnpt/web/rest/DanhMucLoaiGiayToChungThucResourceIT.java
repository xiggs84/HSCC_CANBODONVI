package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucLoaiGiayToChungThucAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import vn.vnpt.domain.DanhMucLoaiGiayToChungThuc;
import vn.vnpt.repository.DanhMucLoaiGiayToChungThucRepository;
import vn.vnpt.service.dto.DanhMucLoaiGiayToChungThucDTO;
import vn.vnpt.service.mapper.DanhMucLoaiGiayToChungThucMapper;

/**
 * Integration tests for the {@link DanhMucLoaiGiayToChungThucResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucLoaiGiayToChungThucResourceIT {

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final String DEFAULT_ID_LOAI_SO = "AAAAAAAAAA";
    private static final String UPDATED_ID_LOAI_SO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-loai-giay-to-chung-thucs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucLoaiGiayToChungThucRepository danhMucLoaiGiayToChungThucRepository;

    @Autowired
    private DanhMucLoaiGiayToChungThucMapper danhMucLoaiGiayToChungThucMapper;

    @Autowired
    private MockMvc restDanhMucLoaiGiayToChungThucMockMvc;

    private DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc;

    private DanhMucLoaiGiayToChungThuc insertedDanhMucLoaiGiayToChungThuc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiGiayToChungThuc createEntity() {
        return new DanhMucLoaiGiayToChungThuc().dienGiai(DEFAULT_DIEN_GIAI).idLoaiSo(DEFAULT_ID_LOAI_SO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiGiayToChungThuc createUpdatedEntity() {
        return new DanhMucLoaiGiayToChungThuc().dienGiai(UPDATED_DIEN_GIAI).idLoaiSo(UPDATED_ID_LOAI_SO);
    }

    @BeforeEach
    public void initTest() {
        danhMucLoaiGiayToChungThuc = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucLoaiGiayToChungThuc != null) {
            danhMucLoaiGiayToChungThucRepository.delete(insertedDanhMucLoaiGiayToChungThuc);
            insertedDanhMucLoaiGiayToChungThuc = null;
        }
    }

    @Test
    void createDanhMucLoaiGiayToChungThuc() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucLoaiGiayToChungThuc
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucMapper.toDto(danhMucLoaiGiayToChungThuc);
        var returnedDanhMucLoaiGiayToChungThucDTO = om.readValue(
            restDanhMucLoaiGiayToChungThucMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(danhMucLoaiGiayToChungThucDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucLoaiGiayToChungThucDTO.class
        );

        // Validate the DanhMucLoaiGiayToChungThuc in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucMapper.toEntity(returnedDanhMucLoaiGiayToChungThucDTO);
        assertDanhMucLoaiGiayToChungThucUpdatableFieldsEquals(
            returnedDanhMucLoaiGiayToChungThuc,
            getPersistedDanhMucLoaiGiayToChungThuc(returnedDanhMucLoaiGiayToChungThuc)
        );

        insertedDanhMucLoaiGiayToChungThuc = returnedDanhMucLoaiGiayToChungThuc;
    }

    @Test
    void createDanhMucLoaiGiayToChungThucWithExistingId() throws Exception {
        // Create the DanhMucLoaiGiayToChungThuc with an existing ID
        danhMucLoaiGiayToChungThuc.setId("existing_id");
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucMapper.toDto(danhMucLoaiGiayToChungThuc);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiGiayToChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiGiayToChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDanhMucLoaiGiayToChungThucs() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucRepository.save(danhMucLoaiGiayToChungThuc);

        // Get all the danhMucLoaiGiayToChungThucList
        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMucLoaiGiayToChungThuc.getId())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].idLoaiSo").value(hasItem(DEFAULT_ID_LOAI_SO)));
    }

    @Test
    void getDanhMucLoaiGiayToChungThuc() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucRepository.save(danhMucLoaiGiayToChungThuc);

        // Get the danhMucLoaiGiayToChungThuc
        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucLoaiGiayToChungThuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhMucLoaiGiayToChungThuc.getId()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.idLoaiSo").value(DEFAULT_ID_LOAI_SO));
    }

    @Test
    void getNonExistingDanhMucLoaiGiayToChungThuc() throws Exception {
        // Get the danhMucLoaiGiayToChungThuc
        restDanhMucLoaiGiayToChungThucMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDanhMucLoaiGiayToChungThuc() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucRepository.save(danhMucLoaiGiayToChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiGiayToChungThuc
        DanhMucLoaiGiayToChungThuc updatedDanhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucRepository
            .findById(danhMucLoaiGiayToChungThuc.getId())
            .orElseThrow();
        updatedDanhMucLoaiGiayToChungThuc.dienGiai(UPDATED_DIEN_GIAI).idLoaiSo(UPDATED_ID_LOAI_SO);
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucMapper.toDto(
            updatedDanhMucLoaiGiayToChungThuc
        );

        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiGiayToChungThucDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiGiayToChungThucDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiGiayToChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucLoaiGiayToChungThucToMatchAllProperties(updatedDanhMucLoaiGiayToChungThuc);
    }

    @Test
    void putNonExistingDanhMucLoaiGiayToChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiGiayToChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiGiayToChungThuc
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucMapper.toDto(danhMucLoaiGiayToChungThuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiGiayToChungThucDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiGiayToChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiGiayToChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDanhMucLoaiGiayToChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiGiayToChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiGiayToChungThuc
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucMapper.toDto(danhMucLoaiGiayToChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiGiayToChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiGiayToChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDanhMucLoaiGiayToChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiGiayToChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiGiayToChungThuc
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucMapper.toDto(danhMucLoaiGiayToChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiGiayToChungThucDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiGiayToChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDanhMucLoaiGiayToChungThucWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucRepository.save(danhMucLoaiGiayToChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiGiayToChungThuc using partial update
        DanhMucLoaiGiayToChungThuc partialUpdatedDanhMucLoaiGiayToChungThuc = new DanhMucLoaiGiayToChungThuc();
        partialUpdatedDanhMucLoaiGiayToChungThuc.setId(danhMucLoaiGiayToChungThuc.getId());

        partialUpdatedDanhMucLoaiGiayToChungThuc.dienGiai(UPDATED_DIEN_GIAI).idLoaiSo(UPDATED_ID_LOAI_SO);

        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiGiayToChungThuc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiGiayToChungThuc))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiGiayToChungThuc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiGiayToChungThucUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucLoaiGiayToChungThuc, danhMucLoaiGiayToChungThuc),
            getPersistedDanhMucLoaiGiayToChungThuc(danhMucLoaiGiayToChungThuc)
        );
    }

    @Test
    void fullUpdateDanhMucLoaiGiayToChungThucWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucRepository.save(danhMucLoaiGiayToChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiGiayToChungThuc using partial update
        DanhMucLoaiGiayToChungThuc partialUpdatedDanhMucLoaiGiayToChungThuc = new DanhMucLoaiGiayToChungThuc();
        partialUpdatedDanhMucLoaiGiayToChungThuc.setId(danhMucLoaiGiayToChungThuc.getId());

        partialUpdatedDanhMucLoaiGiayToChungThuc.dienGiai(UPDATED_DIEN_GIAI).idLoaiSo(UPDATED_ID_LOAI_SO);

        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiGiayToChungThuc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiGiayToChungThuc))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiGiayToChungThuc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiGiayToChungThucUpdatableFieldsEquals(
            partialUpdatedDanhMucLoaiGiayToChungThuc,
            getPersistedDanhMucLoaiGiayToChungThuc(partialUpdatedDanhMucLoaiGiayToChungThuc)
        );
    }

    @Test
    void patchNonExistingDanhMucLoaiGiayToChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiGiayToChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiGiayToChungThuc
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucMapper.toDto(danhMucLoaiGiayToChungThuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucLoaiGiayToChungThucDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiGiayToChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiGiayToChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDanhMucLoaiGiayToChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiGiayToChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiGiayToChungThuc
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucMapper.toDto(danhMucLoaiGiayToChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiGiayToChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiGiayToChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDanhMucLoaiGiayToChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiGiayToChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiGiayToChungThuc
        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO = danhMucLoaiGiayToChungThucMapper.toDto(danhMucLoaiGiayToChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiGiayToChungThucDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiGiayToChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDanhMucLoaiGiayToChungThuc() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThucRepository.save(danhMucLoaiGiayToChungThuc);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucLoaiGiayToChungThuc
        restDanhMucLoaiGiayToChungThucMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucLoaiGiayToChungThuc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucLoaiGiayToChungThucRepository.count();
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

    protected DanhMucLoaiGiayToChungThuc getPersistedDanhMucLoaiGiayToChungThuc(DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc) {
        return danhMucLoaiGiayToChungThucRepository.findById(danhMucLoaiGiayToChungThuc.getId()).orElseThrow();
    }

    protected void assertPersistedDanhMucLoaiGiayToChungThucToMatchAllProperties(
        DanhMucLoaiGiayToChungThuc expectedDanhMucLoaiGiayToChungThuc
    ) {
        assertDanhMucLoaiGiayToChungThucAllPropertiesEquals(
            expectedDanhMucLoaiGiayToChungThuc,
            getPersistedDanhMucLoaiGiayToChungThuc(expectedDanhMucLoaiGiayToChungThuc)
        );
    }

    protected void assertPersistedDanhMucLoaiGiayToChungThucToMatchUpdatableProperties(
        DanhMucLoaiGiayToChungThuc expectedDanhMucLoaiGiayToChungThuc
    ) {
        assertDanhMucLoaiGiayToChungThucAllUpdatablePropertiesEquals(
            expectedDanhMucLoaiGiayToChungThuc,
            getPersistedDanhMucLoaiGiayToChungThuc(expectedDanhMucLoaiGiayToChungThuc)
        );
    }
}
