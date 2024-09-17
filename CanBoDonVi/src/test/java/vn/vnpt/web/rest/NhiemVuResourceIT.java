package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.NhiemVuAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.IntegrationTest;
import vn.vnpt.domain.NhiemVu;
import vn.vnpt.repository.NhiemVuRepository;
import vn.vnpt.service.dto.NhiemVuDTO;
import vn.vnpt.service.mapper.NhiemVuMapper;

/**
 * Integration tests for the {@link NhiemVuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NhiemVuResourceIT {

    private static final String DEFAULT_TEN_NHIEM_VU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NHIEM_VU = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/nhiem-vus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idNhiemVu}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private NhiemVuRepository nhiemVuRepository;

    @Autowired
    private NhiemVuMapper nhiemVuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNhiemVuMockMvc;

    private NhiemVu nhiemVu;

    private NhiemVu insertedNhiemVu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhiemVu createEntity() {
        return new NhiemVu().idNhiemVu(UUID.randomUUID().toString()).tenNhiemVu(DEFAULT_TEN_NHIEM_VU);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NhiemVu createUpdatedEntity() {
        return new NhiemVu().idNhiemVu(UUID.randomUUID().toString()).tenNhiemVu(UPDATED_TEN_NHIEM_VU);
    }

    @BeforeEach
    public void initTest() {
        nhiemVu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedNhiemVu != null) {
            nhiemVuRepository.delete(insertedNhiemVu);
            insertedNhiemVu = null;
        }
    }

    @Test
    @Transactional
    void createNhiemVu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the NhiemVu
        NhiemVuDTO nhiemVuDTO = nhiemVuMapper.toDto(nhiemVu);
        var returnedNhiemVuDTO = om.readValue(
            restNhiemVuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhiemVuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            NhiemVuDTO.class
        );

        // Validate the NhiemVu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedNhiemVu = nhiemVuMapper.toEntity(returnedNhiemVuDTO);
        assertNhiemVuUpdatableFieldsEquals(returnedNhiemVu, getPersistedNhiemVu(returnedNhiemVu));

        insertedNhiemVu = returnedNhiemVu;
    }

    @Test
    @Transactional
    void createNhiemVuWithExistingId() throws Exception {
        // Create the NhiemVu with an existing ID
        insertedNhiemVu = nhiemVuRepository.saveAndFlush(nhiemVu);
        NhiemVuDTO nhiemVuDTO = nhiemVuMapper.toDto(nhiemVu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNhiemVuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhiemVuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NhiemVu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNhiemVus() throws Exception {
        // Initialize the database
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());
        insertedNhiemVu = nhiemVuRepository.saveAndFlush(nhiemVu);

        // Get all the nhiemVuList
        restNhiemVuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idNhiemVu,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idNhiemVu").value(hasItem(nhiemVu.getIdNhiemVu())))
            .andExpect(jsonPath("$.[*].tenNhiemVu").value(hasItem(DEFAULT_TEN_NHIEM_VU)));
    }

    @Test
    @Transactional
    void getNhiemVu() throws Exception {
        // Initialize the database
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());
        insertedNhiemVu = nhiemVuRepository.saveAndFlush(nhiemVu);

        // Get the nhiemVu
        restNhiemVuMockMvc
            .perform(get(ENTITY_API_URL_ID, nhiemVu.getIdNhiemVu()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idNhiemVu").value(nhiemVu.getIdNhiemVu()))
            .andExpect(jsonPath("$.tenNhiemVu").value(DEFAULT_TEN_NHIEM_VU));
    }

    @Test
    @Transactional
    void getNonExistingNhiemVu() throws Exception {
        // Get the nhiemVu
        restNhiemVuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNhiemVu() throws Exception {
        // Initialize the database
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());
        insertedNhiemVu = nhiemVuRepository.saveAndFlush(nhiemVu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nhiemVu
        NhiemVu updatedNhiemVu = nhiemVuRepository.findById(nhiemVu.getIdNhiemVu()).orElseThrow();
        // Disconnect from session so that the updates on updatedNhiemVu are not directly saved in db
        em.detach(updatedNhiemVu);
        updatedNhiemVu.tenNhiemVu(UPDATED_TEN_NHIEM_VU);
        NhiemVuDTO nhiemVuDTO = nhiemVuMapper.toDto(updatedNhiemVu);

        restNhiemVuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nhiemVuDTO.getIdNhiemVu())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nhiemVuDTO))
            )
            .andExpect(status().isOk());

        // Validate the NhiemVu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedNhiemVuToMatchAllProperties(updatedNhiemVu);
    }

    @Test
    @Transactional
    void putNonExistingNhiemVu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());

        // Create the NhiemVu
        NhiemVuDTO nhiemVuDTO = nhiemVuMapper.toDto(nhiemVu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhiemVuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nhiemVuDTO.getIdNhiemVu())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nhiemVuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhiemVu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNhiemVu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());

        // Create the NhiemVu
        NhiemVuDTO nhiemVuDTO = nhiemVuMapper.toDto(nhiemVu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhiemVuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nhiemVuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhiemVu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNhiemVu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());

        // Create the NhiemVu
        NhiemVuDTO nhiemVuDTO = nhiemVuMapper.toDto(nhiemVu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhiemVuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nhiemVuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NhiemVu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNhiemVuWithPatch() throws Exception {
        // Initialize the database
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());
        insertedNhiemVu = nhiemVuRepository.saveAndFlush(nhiemVu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nhiemVu using partial update
        NhiemVu partialUpdatedNhiemVu = new NhiemVu();
        partialUpdatedNhiemVu.setIdNhiemVu(nhiemVu.getIdNhiemVu());

        partialUpdatedNhiemVu.tenNhiemVu(UPDATED_TEN_NHIEM_VU);

        restNhiemVuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNhiemVu.getIdNhiemVu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNhiemVu))
            )
            .andExpect(status().isOk());

        // Validate the NhiemVu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNhiemVuUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedNhiemVu, nhiemVu), getPersistedNhiemVu(nhiemVu));
    }

    @Test
    @Transactional
    void fullUpdateNhiemVuWithPatch() throws Exception {
        // Initialize the database
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());
        insertedNhiemVu = nhiemVuRepository.saveAndFlush(nhiemVu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nhiemVu using partial update
        NhiemVu partialUpdatedNhiemVu = new NhiemVu();
        partialUpdatedNhiemVu.setIdNhiemVu(nhiemVu.getIdNhiemVu());

        partialUpdatedNhiemVu.tenNhiemVu(UPDATED_TEN_NHIEM_VU);

        restNhiemVuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNhiemVu.getIdNhiemVu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNhiemVu))
            )
            .andExpect(status().isOk());

        // Validate the NhiemVu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNhiemVuUpdatableFieldsEquals(partialUpdatedNhiemVu, getPersistedNhiemVu(partialUpdatedNhiemVu));
    }

    @Test
    @Transactional
    void patchNonExistingNhiemVu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());

        // Create the NhiemVu
        NhiemVuDTO nhiemVuDTO = nhiemVuMapper.toDto(nhiemVu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNhiemVuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nhiemVuDTO.getIdNhiemVu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(nhiemVuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhiemVu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNhiemVu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());

        // Create the NhiemVu
        NhiemVuDTO nhiemVuDTO = nhiemVuMapper.toDto(nhiemVu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhiemVuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(nhiemVuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NhiemVu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNhiemVu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());

        // Create the NhiemVu
        NhiemVuDTO nhiemVuDTO = nhiemVuMapper.toDto(nhiemVu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNhiemVuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(nhiemVuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NhiemVu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNhiemVu() throws Exception {
        // Initialize the database
        nhiemVu.setIdNhiemVu(UUID.randomUUID().toString());
        insertedNhiemVu = nhiemVuRepository.saveAndFlush(nhiemVu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the nhiemVu
        restNhiemVuMockMvc
            .perform(delete(ENTITY_API_URL_ID, nhiemVu.getIdNhiemVu()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return nhiemVuRepository.count();
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

    protected NhiemVu getPersistedNhiemVu(NhiemVu nhiemVu) {
        return nhiemVuRepository.findById(nhiemVu.getIdNhiemVu()).orElseThrow();
    }

    protected void assertPersistedNhiemVuToMatchAllProperties(NhiemVu expectedNhiemVu) {
        assertNhiemVuAllPropertiesEquals(expectedNhiemVu, getPersistedNhiemVu(expectedNhiemVu));
    }

    protected void assertPersistedNhiemVuToMatchUpdatableProperties(NhiemVu expectedNhiemVu) {
        assertNhiemVuAllUpdatablePropertiesEquals(expectedNhiemVu, getPersistedNhiemVu(expectedNhiemVu));
    }
}
