package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucVaiTroAsserts.*;
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
import vn.vnpt.domain.DanhMucVaiTro;
import vn.vnpt.repository.DanhMucVaiTroRepository;
import vn.vnpt.service.dto.DanhMucVaiTroDTO;
import vn.vnpt.service.mapper.DanhMucVaiTroMapper;

/**
 * Integration tests for the {@link DanhMucVaiTroResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucVaiTroResourceIT {

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final String DEFAULT_ID_LOAI_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_ID_LOAI_HOP_DONG = "BBBBBBBBBB";

    private static final String DEFAULT_ID_LOAI_VAI_TRO = "AAAAAAAAAA";
    private static final String UPDATED_ID_LOAI_VAI_TRO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-vai-tros";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucVaiTroRepository danhMucVaiTroRepository;

    @Autowired
    private DanhMucVaiTroMapper danhMucVaiTroMapper;

    @Autowired
    private MockMvc restDanhMucVaiTroMockMvc;

    private DanhMucVaiTro danhMucVaiTro;

    private DanhMucVaiTro insertedDanhMucVaiTro;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucVaiTro createEntity() {
        return new DanhMucVaiTro()
            .dienGiai(DEFAULT_DIEN_GIAI)
            .idLoaiHopDong(DEFAULT_ID_LOAI_HOP_DONG)
            .idLoaiVaiTro(DEFAULT_ID_LOAI_VAI_TRO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucVaiTro createUpdatedEntity() {
        return new DanhMucVaiTro()
            .dienGiai(UPDATED_DIEN_GIAI)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idLoaiVaiTro(UPDATED_ID_LOAI_VAI_TRO);
    }

    @BeforeEach
    public void initTest() {
        danhMucVaiTro = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucVaiTro != null) {
            danhMucVaiTroRepository.delete(insertedDanhMucVaiTro);
            insertedDanhMucVaiTro = null;
        }
    }

    @Test
    void createDanhMucVaiTro() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucVaiTro
        DanhMucVaiTroDTO danhMucVaiTroDTO = danhMucVaiTroMapper.toDto(danhMucVaiTro);
        var returnedDanhMucVaiTroDTO = om.readValue(
            restDanhMucVaiTroMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucVaiTroDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucVaiTroDTO.class
        );

        // Validate the DanhMucVaiTro in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucVaiTro = danhMucVaiTroMapper.toEntity(returnedDanhMucVaiTroDTO);
        assertDanhMucVaiTroUpdatableFieldsEquals(returnedDanhMucVaiTro, getPersistedDanhMucVaiTro(returnedDanhMucVaiTro));

        insertedDanhMucVaiTro = returnedDanhMucVaiTro;
    }

    @Test
    void createDanhMucVaiTroWithExistingId() throws Exception {
        // Create the DanhMucVaiTro with an existing ID
        danhMucVaiTro.setId("existing_id");
        DanhMucVaiTroDTO danhMucVaiTroDTO = danhMucVaiTroMapper.toDto(danhMucVaiTro);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucVaiTroMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucVaiTroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucVaiTro in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDanhMucVaiTros() throws Exception {
        // Initialize the database
        insertedDanhMucVaiTro = danhMucVaiTroRepository.save(danhMucVaiTro);

        // Get all the danhMucVaiTroList
        restDanhMucVaiTroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMucVaiTro.getId())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].idLoaiHopDong").value(hasItem(DEFAULT_ID_LOAI_HOP_DONG)))
            .andExpect(jsonPath("$.[*].idLoaiVaiTro").value(hasItem(DEFAULT_ID_LOAI_VAI_TRO)));
    }

    @Test
    void getDanhMucVaiTro() throws Exception {
        // Initialize the database
        insertedDanhMucVaiTro = danhMucVaiTroRepository.save(danhMucVaiTro);

        // Get the danhMucVaiTro
        restDanhMucVaiTroMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucVaiTro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhMucVaiTro.getId()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.idLoaiHopDong").value(DEFAULT_ID_LOAI_HOP_DONG))
            .andExpect(jsonPath("$.idLoaiVaiTro").value(DEFAULT_ID_LOAI_VAI_TRO));
    }

    @Test
    void getNonExistingDanhMucVaiTro() throws Exception {
        // Get the danhMucVaiTro
        restDanhMucVaiTroMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDanhMucVaiTro() throws Exception {
        // Initialize the database
        insertedDanhMucVaiTro = danhMucVaiTroRepository.save(danhMucVaiTro);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucVaiTro
        DanhMucVaiTro updatedDanhMucVaiTro = danhMucVaiTroRepository.findById(danhMucVaiTro.getId()).orElseThrow();
        updatedDanhMucVaiTro.dienGiai(UPDATED_DIEN_GIAI).idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG).idLoaiVaiTro(UPDATED_ID_LOAI_VAI_TRO);
        DanhMucVaiTroDTO danhMucVaiTroDTO = danhMucVaiTroMapper.toDto(updatedDanhMucVaiTro);

        restDanhMucVaiTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucVaiTroDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucVaiTroDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucVaiTro in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucVaiTroToMatchAllProperties(updatedDanhMucVaiTro);
    }

    @Test
    void putNonExistingDanhMucVaiTro() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucVaiTro.setId(UUID.randomUUID().toString());

        // Create the DanhMucVaiTro
        DanhMucVaiTroDTO danhMucVaiTroDTO = danhMucVaiTroMapper.toDto(danhMucVaiTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucVaiTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucVaiTroDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucVaiTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucVaiTro in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDanhMucVaiTro() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucVaiTro.setId(UUID.randomUUID().toString());

        // Create the DanhMucVaiTro
        DanhMucVaiTroDTO danhMucVaiTroDTO = danhMucVaiTroMapper.toDto(danhMucVaiTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucVaiTroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucVaiTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucVaiTro in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDanhMucVaiTro() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucVaiTro.setId(UUID.randomUUID().toString());

        // Create the DanhMucVaiTro
        DanhMucVaiTroDTO danhMucVaiTroDTO = danhMucVaiTroMapper.toDto(danhMucVaiTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucVaiTroMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucVaiTroDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucVaiTro in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDanhMucVaiTroWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucVaiTro = danhMucVaiTroRepository.save(danhMucVaiTro);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucVaiTro using partial update
        DanhMucVaiTro partialUpdatedDanhMucVaiTro = new DanhMucVaiTro();
        partialUpdatedDanhMucVaiTro.setId(danhMucVaiTro.getId());

        partialUpdatedDanhMucVaiTro
            .dienGiai(UPDATED_DIEN_GIAI)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idLoaiVaiTro(UPDATED_ID_LOAI_VAI_TRO);

        restDanhMucVaiTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucVaiTro.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucVaiTro))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucVaiTro in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucVaiTroUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucVaiTro, danhMucVaiTro),
            getPersistedDanhMucVaiTro(danhMucVaiTro)
        );
    }

    @Test
    void fullUpdateDanhMucVaiTroWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucVaiTro = danhMucVaiTroRepository.save(danhMucVaiTro);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucVaiTro using partial update
        DanhMucVaiTro partialUpdatedDanhMucVaiTro = new DanhMucVaiTro();
        partialUpdatedDanhMucVaiTro.setId(danhMucVaiTro.getId());

        partialUpdatedDanhMucVaiTro
            .dienGiai(UPDATED_DIEN_GIAI)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idLoaiVaiTro(UPDATED_ID_LOAI_VAI_TRO);

        restDanhMucVaiTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucVaiTro.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucVaiTro))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucVaiTro in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucVaiTroUpdatableFieldsEquals(partialUpdatedDanhMucVaiTro, getPersistedDanhMucVaiTro(partialUpdatedDanhMucVaiTro));
    }

    @Test
    void patchNonExistingDanhMucVaiTro() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucVaiTro.setId(UUID.randomUUID().toString());

        // Create the DanhMucVaiTro
        DanhMucVaiTroDTO danhMucVaiTroDTO = danhMucVaiTroMapper.toDto(danhMucVaiTro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucVaiTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucVaiTroDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucVaiTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucVaiTro in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDanhMucVaiTro() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucVaiTro.setId(UUID.randomUUID().toString());

        // Create the DanhMucVaiTro
        DanhMucVaiTroDTO danhMucVaiTroDTO = danhMucVaiTroMapper.toDto(danhMucVaiTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucVaiTroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucVaiTroDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucVaiTro in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDanhMucVaiTro() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucVaiTro.setId(UUID.randomUUID().toString());

        // Create the DanhMucVaiTro
        DanhMucVaiTroDTO danhMucVaiTroDTO = danhMucVaiTroMapper.toDto(danhMucVaiTro);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucVaiTroMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucVaiTroDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucVaiTro in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDanhMucVaiTro() throws Exception {
        // Initialize the database
        insertedDanhMucVaiTro = danhMucVaiTroRepository.save(danhMucVaiTro);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucVaiTro
        restDanhMucVaiTroMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucVaiTro.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucVaiTroRepository.count();
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

    protected DanhMucVaiTro getPersistedDanhMucVaiTro(DanhMucVaiTro danhMucVaiTro) {
        return danhMucVaiTroRepository.findById(danhMucVaiTro.getId()).orElseThrow();
    }

    protected void assertPersistedDanhMucVaiTroToMatchAllProperties(DanhMucVaiTro expectedDanhMucVaiTro) {
        assertDanhMucVaiTroAllPropertiesEquals(expectedDanhMucVaiTro, getPersistedDanhMucVaiTro(expectedDanhMucVaiTro));
    }

    protected void assertPersistedDanhMucVaiTroToMatchUpdatableProperties(DanhMucVaiTro expectedDanhMucVaiTro) {
        assertDanhMucVaiTroAllUpdatablePropertiesEquals(expectedDanhMucVaiTro, getPersistedDanhMucVaiTro(expectedDanhMucVaiTro));
    }
}
