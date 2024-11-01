package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucLoaiSoCongChungAsserts.*;
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
import vn.vnpt.domain.DanhMucLoaiSoCongChung;
import vn.vnpt.repository.DanhMucLoaiSoCongChungRepository;
import vn.vnpt.service.dto.DanhMucLoaiSoCongChungDTO;
import vn.vnpt.service.mapper.DanhMucLoaiSoCongChungMapper;

/**
 * Integration tests for the {@link DanhMucLoaiSoCongChungResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucLoaiSoCongChungResourceIT {

    private static final String DEFAULT_TEN_LOAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOAI = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String ENTITY_API_URL = "/api/danh-muc-loai-so-cong-chungs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucLoaiSoCongChungRepository danhMucLoaiSoCongChungRepository;

    @Autowired
    private DanhMucLoaiSoCongChungMapper danhMucLoaiSoCongChungMapper;

    @Autowired
    private MockMvc restDanhMucLoaiSoCongChungMockMvc;

    private DanhMucLoaiSoCongChung danhMucLoaiSoCongChung;

    private DanhMucLoaiSoCongChung insertedDanhMucLoaiSoCongChung;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiSoCongChung createEntity() {
        return new DanhMucLoaiSoCongChung().tenLoai(DEFAULT_TEN_LOAI).trangThai(DEFAULT_TRANG_THAI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiSoCongChung createUpdatedEntity() {
        return new DanhMucLoaiSoCongChung().tenLoai(UPDATED_TEN_LOAI).trangThai(UPDATED_TRANG_THAI);
    }

    @BeforeEach
    public void initTest() {
        danhMucLoaiSoCongChung = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucLoaiSoCongChung != null) {
            danhMucLoaiSoCongChungRepository.delete(insertedDanhMucLoaiSoCongChung);
            insertedDanhMucLoaiSoCongChung = null;
        }
    }

    @Test
    void createDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);
        var returnedDanhMucLoaiSoCongChungDTO = om.readValue(
            restDanhMucLoaiSoCongChungMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucLoaiSoCongChungDTO.class
        );

        // Validate the DanhMucLoaiSoCongChung in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungMapper.toEntity(returnedDanhMucLoaiSoCongChungDTO);
        assertDanhMucLoaiSoCongChungUpdatableFieldsEquals(
            returnedDanhMucLoaiSoCongChung,
            getPersistedDanhMucLoaiSoCongChung(returnedDanhMucLoaiSoCongChung)
        );

        insertedDanhMucLoaiSoCongChung = returnedDanhMucLoaiSoCongChung;
    }

    @Test
    void createDanhMucLoaiSoCongChungWithExistingId() throws Exception {
        // Create the DanhMucLoaiSoCongChung with an existing ID
        danhMucLoaiSoCongChung.setId("existing_id");
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucLoaiSoCongChungMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDanhMucLoaiSoCongChungs() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.save(danhMucLoaiSoCongChung);

        // Get all the danhMucLoaiSoCongChungList
        restDanhMucLoaiSoCongChungMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMucLoaiSoCongChung.getId())))
            .andExpect(jsonPath("$.[*].tenLoai").value(hasItem(DEFAULT_TEN_LOAI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));
    }

    @Test
    void getDanhMucLoaiSoCongChung() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.save(danhMucLoaiSoCongChung);

        // Get the danhMucLoaiSoCongChung
        restDanhMucLoaiSoCongChungMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucLoaiSoCongChung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhMucLoaiSoCongChung.getId()))
            .andExpect(jsonPath("$.tenLoai").value(DEFAULT_TEN_LOAI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()));
    }

    @Test
    void getNonExistingDanhMucLoaiSoCongChung() throws Exception {
        // Get the danhMucLoaiSoCongChung
        restDanhMucLoaiSoCongChungMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDanhMucLoaiSoCongChung() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.save(danhMucLoaiSoCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiSoCongChung
        DanhMucLoaiSoCongChung updatedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository
            .findById(danhMucLoaiSoCongChung.getId())
            .orElseThrow();
        updatedDanhMucLoaiSoCongChung.tenLoai(UPDATED_TEN_LOAI).trangThai(UPDATED_TRANG_THAI);
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(updatedDanhMucLoaiSoCongChung);

        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiSoCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucLoaiSoCongChungToMatchAllProperties(updatedDanhMucLoaiSoCongChung);
    }

    @Test
    void putNonExistingDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiSoCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDanhMucLoaiSoCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.save(danhMucLoaiSoCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiSoCongChung using partial update
        DanhMucLoaiSoCongChung partialUpdatedDanhMucLoaiSoCongChung = new DanhMucLoaiSoCongChung();
        partialUpdatedDanhMucLoaiSoCongChung.setId(danhMucLoaiSoCongChung.getId());

        partialUpdatedDanhMucLoaiSoCongChung.tenLoai(UPDATED_TEN_LOAI);

        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiSoCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiSoCongChung))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiSoCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiSoCongChungUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucLoaiSoCongChung, danhMucLoaiSoCongChung),
            getPersistedDanhMucLoaiSoCongChung(danhMucLoaiSoCongChung)
        );
    }

    @Test
    void fullUpdateDanhMucLoaiSoCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.save(danhMucLoaiSoCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiSoCongChung using partial update
        DanhMucLoaiSoCongChung partialUpdatedDanhMucLoaiSoCongChung = new DanhMucLoaiSoCongChung();
        partialUpdatedDanhMucLoaiSoCongChung.setId(danhMucLoaiSoCongChung.getId());

        partialUpdatedDanhMucLoaiSoCongChung.tenLoai(UPDATED_TEN_LOAI).trangThai(UPDATED_TRANG_THAI);

        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiSoCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiSoCongChung))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiSoCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiSoCongChungUpdatableFieldsEquals(
            partialUpdatedDanhMucLoaiSoCongChung,
            getPersistedDanhMucLoaiSoCongChung(partialUpdatedDanhMucLoaiSoCongChung)
        );
    }

    @Test
    void patchNonExistingDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucLoaiSoCongChungDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(UUID.randomUUID().toString());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDanhMucLoaiSoCongChung() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.save(danhMucLoaiSoCongChung);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucLoaiSoCongChung
        restDanhMucLoaiSoCongChungMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucLoaiSoCongChung.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucLoaiSoCongChungRepository.count();
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

    protected DanhMucLoaiSoCongChung getPersistedDanhMucLoaiSoCongChung(DanhMucLoaiSoCongChung danhMucLoaiSoCongChung) {
        return danhMucLoaiSoCongChungRepository.findById(danhMucLoaiSoCongChung.getId()).orElseThrow();
    }

    protected void assertPersistedDanhMucLoaiSoCongChungToMatchAllProperties(DanhMucLoaiSoCongChung expectedDanhMucLoaiSoCongChung) {
        assertDanhMucLoaiSoCongChungAllPropertiesEquals(
            expectedDanhMucLoaiSoCongChung,
            getPersistedDanhMucLoaiSoCongChung(expectedDanhMucLoaiSoCongChung)
        );
    }

    protected void assertPersistedDanhMucLoaiSoCongChungToMatchUpdatableProperties(DanhMucLoaiSoCongChung expectedDanhMucLoaiSoCongChung) {
        assertDanhMucLoaiSoCongChungAllUpdatablePropertiesEquals(
            expectedDanhMucLoaiSoCongChung,
            getPersistedDanhMucLoaiSoCongChung(expectedDanhMucLoaiSoCongChung)
        );
    }
}
