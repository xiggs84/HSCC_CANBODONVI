package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.LoaiHopDongCongChungAsserts.*;
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
import vn.vnpt.domain.LoaiHopDongCongChung;
import vn.vnpt.repository.LoaiHopDongCongChungRepository;
import vn.vnpt.service.dto.LoaiHopDongCongChungDTO;
import vn.vnpt.service.mapper.LoaiHopDongCongChungMapper;

/**
 * Integration tests for the {@link LoaiHopDongCongChungResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LoaiHopDongCongChungResourceIT {

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_GIA_TRI = 1L;
    private static final Long UPDATED_GIA_TRI = 2L;

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String ENTITY_API_URL = "/api/loai-hop-dong-cong-chungs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LoaiHopDongCongChungRepository loaiHopDongCongChungRepository;

    @Autowired
    private LoaiHopDongCongChungMapper loaiHopDongCongChungMapper;

    @Autowired
    private MockMvc restLoaiHopDongCongChungMockMvc;

    private LoaiHopDongCongChung loaiHopDongCongChung;

    private LoaiHopDongCongChung insertedLoaiHopDongCongChung;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiHopDongCongChung createEntity() {
        return new LoaiHopDongCongChung().dienGiai(DEFAULT_DIEN_GIAI).giaTri(DEFAULT_GIA_TRI).trangThai(DEFAULT_TRANG_THAI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiHopDongCongChung createUpdatedEntity() {
        return new LoaiHopDongCongChung().dienGiai(UPDATED_DIEN_GIAI).giaTri(UPDATED_GIA_TRI).trangThai(UPDATED_TRANG_THAI);
    }

    @BeforeEach
    public void initTest() {
        loaiHopDongCongChung = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLoaiHopDongCongChung != null) {
            loaiHopDongCongChungRepository.delete(insertedLoaiHopDongCongChung);
            insertedLoaiHopDongCongChung = null;
        }
    }

    @Test
    void createLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);
        var returnedLoaiHopDongCongChungDTO = om.readValue(
            restLoaiHopDongCongChungMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LoaiHopDongCongChungDTO.class
        );

        // Validate the LoaiHopDongCongChung in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLoaiHopDongCongChung = loaiHopDongCongChungMapper.toEntity(returnedLoaiHopDongCongChungDTO);
        assertLoaiHopDongCongChungUpdatableFieldsEquals(
            returnedLoaiHopDongCongChung,
            getPersistedLoaiHopDongCongChung(returnedLoaiHopDongCongChung)
        );

        insertedLoaiHopDongCongChung = returnedLoaiHopDongCongChung;
    }

    @Test
    void createLoaiHopDongCongChungWithExistingId() throws Exception {
        // Create the LoaiHopDongCongChung with an existing ID
        loaiHopDongCongChung.setId("existing_id");
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiHopDongCongChungMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiHopDongCongChungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllLoaiHopDongCongChungs() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.save(loaiHopDongCongChung);

        // Get all the loaiHopDongCongChungList
        restLoaiHopDongCongChungMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiHopDongCongChung.getId())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));
    }

    @Test
    void getLoaiHopDongCongChung() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.save(loaiHopDongCongChung);

        // Get the loaiHopDongCongChung
        restLoaiHopDongCongChungMockMvc
            .perform(get(ENTITY_API_URL_ID, loaiHopDongCongChung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loaiHopDongCongChung.getId()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.giaTri").value(DEFAULT_GIA_TRI.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()));
    }

    @Test
    void getNonExistingLoaiHopDongCongChung() throws Exception {
        // Get the loaiHopDongCongChung
        restLoaiHopDongCongChungMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingLoaiHopDongCongChung() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.save(loaiHopDongCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiHopDongCongChung
        LoaiHopDongCongChung updatedLoaiHopDongCongChung = loaiHopDongCongChungRepository
            .findById(loaiHopDongCongChung.getId())
            .orElseThrow();
        updatedLoaiHopDongCongChung.dienGiai(UPDATED_DIEN_GIAI).giaTri(UPDATED_GIA_TRI).trangThai(UPDATED_TRANG_THAI);
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(updatedLoaiHopDongCongChung);

        restLoaiHopDongCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiHopDongCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isOk());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLoaiHopDongCongChungToMatchAllProperties(updatedLoaiHopDongCongChung);
    }

    @Test
    void putNonExistingLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiHopDongCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiHopDongCongChungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateLoaiHopDongCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.save(loaiHopDongCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiHopDongCongChung using partial update
        LoaiHopDongCongChung partialUpdatedLoaiHopDongCongChung = new LoaiHopDongCongChung();
        partialUpdatedLoaiHopDongCongChung.setId(loaiHopDongCongChung.getId());

        restLoaiHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiHopDongCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiHopDongCongChung))
            )
            .andExpect(status().isOk());

        // Validate the LoaiHopDongCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiHopDongCongChungUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLoaiHopDongCongChung, loaiHopDongCongChung),
            getPersistedLoaiHopDongCongChung(loaiHopDongCongChung)
        );
    }

    @Test
    void fullUpdateLoaiHopDongCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.save(loaiHopDongCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiHopDongCongChung using partial update
        LoaiHopDongCongChung partialUpdatedLoaiHopDongCongChung = new LoaiHopDongCongChung();
        partialUpdatedLoaiHopDongCongChung.setId(loaiHopDongCongChung.getId());

        partialUpdatedLoaiHopDongCongChung.dienGiai(UPDATED_DIEN_GIAI).giaTri(UPDATED_GIA_TRI).trangThai(UPDATED_TRANG_THAI);

        restLoaiHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiHopDongCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiHopDongCongChung))
            )
            .andExpect(status().isOk());

        // Validate the LoaiHopDongCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiHopDongCongChungUpdatableFieldsEquals(
            partialUpdatedLoaiHopDongCongChung,
            getPersistedLoaiHopDongCongChung(partialUpdatedLoaiHopDongCongChung)
        );
    }

    @Test
    void patchNonExistingLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, loaiHopDongCongChungDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteLoaiHopDongCongChung() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.save(loaiHopDongCongChung);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the loaiHopDongCongChung
        restLoaiHopDongCongChungMockMvc
            .perform(delete(ENTITY_API_URL_ID, loaiHopDongCongChung.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return loaiHopDongCongChungRepository.count();
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

    protected LoaiHopDongCongChung getPersistedLoaiHopDongCongChung(LoaiHopDongCongChung loaiHopDongCongChung) {
        return loaiHopDongCongChungRepository.findById(loaiHopDongCongChung.getId()).orElseThrow();
    }

    protected void assertPersistedLoaiHopDongCongChungToMatchAllProperties(LoaiHopDongCongChung expectedLoaiHopDongCongChung) {
        assertLoaiHopDongCongChungAllPropertiesEquals(
            expectedLoaiHopDongCongChung,
            getPersistedLoaiHopDongCongChung(expectedLoaiHopDongCongChung)
        );
    }

    protected void assertPersistedLoaiHopDongCongChungToMatchUpdatableProperties(LoaiHopDongCongChung expectedLoaiHopDongCongChung) {
        assertLoaiHopDongCongChungAllUpdatablePropertiesEquals(
            expectedLoaiHopDongCongChung,
            getPersistedLoaiHopDongCongChung(expectedLoaiHopDongCongChung)
        );
    }
}
