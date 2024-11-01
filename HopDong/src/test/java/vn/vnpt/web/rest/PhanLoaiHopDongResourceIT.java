package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.PhanLoaiHopDongAsserts.*;
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
import vn.vnpt.domain.PhanLoaiHopDong;
import vn.vnpt.repository.PhanLoaiHopDongRepository;
import vn.vnpt.service.dto.PhanLoaiHopDongDTO;
import vn.vnpt.service.mapper.PhanLoaiHopDongMapper;

/**
 * Integration tests for the {@link PhanLoaiHopDongResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PhanLoaiHopDongResourceIT {

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final String DEFAULT_ID_NHOM_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_ID_NHOM_HOP_DONG = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/phan-loai-hop-dongs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PhanLoaiHopDongRepository phanLoaiHopDongRepository;

    @Autowired
    private PhanLoaiHopDongMapper phanLoaiHopDongMapper;

    @Autowired
    private MockMvc restPhanLoaiHopDongMockMvc;

    private PhanLoaiHopDong phanLoaiHopDong;

    private PhanLoaiHopDong insertedPhanLoaiHopDong;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhanLoaiHopDong createEntity() {
        return new PhanLoaiHopDong().dienGiai(DEFAULT_DIEN_GIAI).idNhomHopDong(DEFAULT_ID_NHOM_HOP_DONG);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhanLoaiHopDong createUpdatedEntity() {
        return new PhanLoaiHopDong().dienGiai(UPDATED_DIEN_GIAI).idNhomHopDong(UPDATED_ID_NHOM_HOP_DONG);
    }

    @BeforeEach
    public void initTest() {
        phanLoaiHopDong = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedPhanLoaiHopDong != null) {
            phanLoaiHopDongRepository.delete(insertedPhanLoaiHopDong);
            insertedPhanLoaiHopDong = null;
        }
    }

    @Test
    void createPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);
        var returnedPhanLoaiHopDongDTO = om.readValue(
            restPhanLoaiHopDongMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(phanLoaiHopDongDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PhanLoaiHopDongDTO.class
        );

        // Validate the PhanLoaiHopDong in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPhanLoaiHopDong = phanLoaiHopDongMapper.toEntity(returnedPhanLoaiHopDongDTO);
        assertPhanLoaiHopDongUpdatableFieldsEquals(returnedPhanLoaiHopDong, getPersistedPhanLoaiHopDong(returnedPhanLoaiHopDong));

        insertedPhanLoaiHopDong = returnedPhanLoaiHopDong;
    }

    @Test
    void createPhanLoaiHopDongWithExistingId() throws Exception {
        // Create the PhanLoaiHopDong with an existing ID
        phanLoaiHopDong.setId("existing_id");
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhanLoaiHopDongMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(phanLoaiHopDongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPhanLoaiHopDongs() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.save(phanLoaiHopDong);

        // Get all the phanLoaiHopDongList
        restPhanLoaiHopDongMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phanLoaiHopDong.getId())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].idNhomHopDong").value(hasItem(DEFAULT_ID_NHOM_HOP_DONG)));
    }

    @Test
    void getPhanLoaiHopDong() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.save(phanLoaiHopDong);

        // Get the phanLoaiHopDong
        restPhanLoaiHopDongMockMvc
            .perform(get(ENTITY_API_URL_ID, phanLoaiHopDong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(phanLoaiHopDong.getId()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.idNhomHopDong").value(DEFAULT_ID_NHOM_HOP_DONG));
    }

    @Test
    void getNonExistingPhanLoaiHopDong() throws Exception {
        // Get the phanLoaiHopDong
        restPhanLoaiHopDongMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingPhanLoaiHopDong() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.save(phanLoaiHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the phanLoaiHopDong
        PhanLoaiHopDong updatedPhanLoaiHopDong = phanLoaiHopDongRepository.findById(phanLoaiHopDong.getId()).orElseThrow();
        updatedPhanLoaiHopDong.dienGiai(UPDATED_DIEN_GIAI).idNhomHopDong(UPDATED_ID_NHOM_HOP_DONG);
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(updatedPhanLoaiHopDong);

        restPhanLoaiHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, phanLoaiHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(phanLoaiHopDongDTO))
            )
            .andExpect(status().isOk());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPhanLoaiHopDongToMatchAllProperties(updatedPhanLoaiHopDong);
    }

    @Test
    void putNonExistingPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(UUID.randomUUID().toString());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, phanLoaiHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(phanLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(UUID.randomUUID().toString());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(phanLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(UUID.randomUUID().toString());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(phanLoaiHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePhanLoaiHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.save(phanLoaiHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the phanLoaiHopDong using partial update
        PhanLoaiHopDong partialUpdatedPhanLoaiHopDong = new PhanLoaiHopDong();
        partialUpdatedPhanLoaiHopDong.setId(phanLoaiHopDong.getId());

        partialUpdatedPhanLoaiHopDong.dienGiai(UPDATED_DIEN_GIAI).idNhomHopDong(UPDATED_ID_NHOM_HOP_DONG);

        restPhanLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPhanLoaiHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPhanLoaiHopDong))
            )
            .andExpect(status().isOk());

        // Validate the PhanLoaiHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPhanLoaiHopDongUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPhanLoaiHopDong, phanLoaiHopDong),
            getPersistedPhanLoaiHopDong(phanLoaiHopDong)
        );
    }

    @Test
    void fullUpdatePhanLoaiHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.save(phanLoaiHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the phanLoaiHopDong using partial update
        PhanLoaiHopDong partialUpdatedPhanLoaiHopDong = new PhanLoaiHopDong();
        partialUpdatedPhanLoaiHopDong.setId(phanLoaiHopDong.getId());

        partialUpdatedPhanLoaiHopDong.dienGiai(UPDATED_DIEN_GIAI).idNhomHopDong(UPDATED_ID_NHOM_HOP_DONG);

        restPhanLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPhanLoaiHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPhanLoaiHopDong))
            )
            .andExpect(status().isOk());

        // Validate the PhanLoaiHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPhanLoaiHopDongUpdatableFieldsEquals(
            partialUpdatedPhanLoaiHopDong,
            getPersistedPhanLoaiHopDong(partialUpdatedPhanLoaiHopDong)
        );
    }

    @Test
    void patchNonExistingPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(UUID.randomUUID().toString());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, phanLoaiHopDongDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(phanLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(UUID.randomUUID().toString());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(phanLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(UUID.randomUUID().toString());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(phanLoaiHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePhanLoaiHopDong() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.save(phanLoaiHopDong);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the phanLoaiHopDong
        restPhanLoaiHopDongMockMvc
            .perform(delete(ENTITY_API_URL_ID, phanLoaiHopDong.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return phanLoaiHopDongRepository.count();
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

    protected PhanLoaiHopDong getPersistedPhanLoaiHopDong(PhanLoaiHopDong phanLoaiHopDong) {
        return phanLoaiHopDongRepository.findById(phanLoaiHopDong.getId()).orElseThrow();
    }

    protected void assertPersistedPhanLoaiHopDongToMatchAllProperties(PhanLoaiHopDong expectedPhanLoaiHopDong) {
        assertPhanLoaiHopDongAllPropertiesEquals(expectedPhanLoaiHopDong, getPersistedPhanLoaiHopDong(expectedPhanLoaiHopDong));
    }

    protected void assertPersistedPhanLoaiHopDongToMatchUpdatableProperties(PhanLoaiHopDong expectedPhanLoaiHopDong) {
        assertPhanLoaiHopDongAllUpdatablePropertiesEquals(expectedPhanLoaiHopDong, getPersistedPhanLoaiHopDong(expectedPhanLoaiHopDong));
    }
}
