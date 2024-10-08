package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.LoaiDonViAsserts.*;
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
import vn.vnpt.domain.LoaiDonVi;
import vn.vnpt.repository.LoaiDonViRepository;
import vn.vnpt.service.dto.LoaiDonViDTO;
import vn.vnpt.service.mapper.LoaiDonViMapper;

/**
 * Integration tests for the {@link LoaiDonViResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LoaiDonViResourceIT {

    private static final String DEFAULT_TEN_LOAI_DV = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOAI_DV = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/loai-don-vis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idLoaiDv}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LoaiDonViRepository loaiDonViRepository;

    @Autowired
    private LoaiDonViMapper loaiDonViMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoaiDonViMockMvc;

    private LoaiDonVi loaiDonVi;

    private LoaiDonVi insertedLoaiDonVi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiDonVi createEntity() {
        return new LoaiDonVi().idLoaiDv(UUID.randomUUID().toString()).tenLoaiDv(DEFAULT_TEN_LOAI_DV);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiDonVi createUpdatedEntity() {
        return new LoaiDonVi().idLoaiDv(UUID.randomUUID().toString()).tenLoaiDv(UPDATED_TEN_LOAI_DV);
    }

    @BeforeEach
    public void initTest() {
        loaiDonVi = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLoaiDonVi != null) {
            loaiDonViRepository.delete(insertedLoaiDonVi);
            insertedLoaiDonVi = null;
        }
    }

    @Test
    @Transactional
    void createLoaiDonVi() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LoaiDonVi
        LoaiDonViDTO loaiDonViDTO = loaiDonViMapper.toDto(loaiDonVi);
        var returnedLoaiDonViDTO = om.readValue(
            restLoaiDonViMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiDonViDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LoaiDonViDTO.class
        );

        // Validate the LoaiDonVi in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLoaiDonVi = loaiDonViMapper.toEntity(returnedLoaiDonViDTO);
        assertLoaiDonViUpdatableFieldsEquals(returnedLoaiDonVi, getPersistedLoaiDonVi(returnedLoaiDonVi));

        insertedLoaiDonVi = returnedLoaiDonVi;
    }

    @Test
    @Transactional
    void createLoaiDonViWithExistingId() throws Exception {
        // Create the LoaiDonVi with an existing ID
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);
        LoaiDonViDTO loaiDonViDTO = loaiDonViMapper.toDto(loaiDonVi);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiDonViMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiDonViDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLoaiDonVis() throws Exception {
        // Initialize the database
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        // Get all the loaiDonViList
        restLoaiDonViMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idLoaiDv,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idLoaiDv").value(hasItem(loaiDonVi.getIdLoaiDv())))
            .andExpect(jsonPath("$.[*].tenLoaiDv").value(hasItem(DEFAULT_TEN_LOAI_DV)));
    }

    @Test
    @Transactional
    void getLoaiDonVi() throws Exception {
        // Initialize the database
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        // Get the loaiDonVi
        restLoaiDonViMockMvc
            .perform(get(ENTITY_API_URL_ID, loaiDonVi.getIdLoaiDv()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idLoaiDv").value(loaiDonVi.getIdLoaiDv()))
            .andExpect(jsonPath("$.tenLoaiDv").value(DEFAULT_TEN_LOAI_DV));
    }

    @Test
    @Transactional
    void getLoaiDonVisByIdFiltering() throws Exception {
        // Initialize the database
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        String id = loaiDonVi.getIdLoaiDv();

        defaultLoaiDonViFiltering("idLoaiDv.equals=" + id, "idLoaiDv.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllLoaiDonVisByTenLoaiDvIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        // Get all the loaiDonViList where tenLoaiDv equals to
        defaultLoaiDonViFiltering("tenLoaiDv.equals=" + DEFAULT_TEN_LOAI_DV, "tenLoaiDv.equals=" + UPDATED_TEN_LOAI_DV);
    }

    @Test
    @Transactional
    void getAllLoaiDonVisByTenLoaiDvIsInShouldWork() throws Exception {
        // Initialize the database
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        // Get all the loaiDonViList where tenLoaiDv in
        defaultLoaiDonViFiltering("tenLoaiDv.in=" + DEFAULT_TEN_LOAI_DV + "," + UPDATED_TEN_LOAI_DV, "tenLoaiDv.in=" + UPDATED_TEN_LOAI_DV);
    }

    @Test
    @Transactional
    void getAllLoaiDonVisByTenLoaiDvIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        // Get all the loaiDonViList where tenLoaiDv is not null
        defaultLoaiDonViFiltering("tenLoaiDv.specified=true", "tenLoaiDv.specified=false");
    }

    @Test
    @Transactional
    void getAllLoaiDonVisByTenLoaiDvContainsSomething() throws Exception {
        // Initialize the database
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        // Get all the loaiDonViList where tenLoaiDv contains
        defaultLoaiDonViFiltering("tenLoaiDv.contains=" + DEFAULT_TEN_LOAI_DV, "tenLoaiDv.contains=" + UPDATED_TEN_LOAI_DV);
    }

    @Test
    @Transactional
    void getAllLoaiDonVisByTenLoaiDvNotContainsSomething() throws Exception {
        // Initialize the database
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        // Get all the loaiDonViList where tenLoaiDv does not contain
        defaultLoaiDonViFiltering("tenLoaiDv.doesNotContain=" + UPDATED_TEN_LOAI_DV, "tenLoaiDv.doesNotContain=" + DEFAULT_TEN_LOAI_DV);
    }

    private void defaultLoaiDonViFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultLoaiDonViShouldBeFound(shouldBeFound);
        defaultLoaiDonViShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLoaiDonViShouldBeFound(String filter) throws Exception {
        restLoaiDonViMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idLoaiDv,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idLoaiDv").value(hasItem(loaiDonVi.getIdLoaiDv())))
            .andExpect(jsonPath("$.[*].tenLoaiDv").value(hasItem(DEFAULT_TEN_LOAI_DV)));

        // Check, that the count call also returns 1
        restLoaiDonViMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idLoaiDv,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLoaiDonViShouldNotBeFound(String filter) throws Exception {
        restLoaiDonViMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idLoaiDv,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLoaiDonViMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idLoaiDv,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingLoaiDonVi() throws Exception {
        // Get the loaiDonVi
        restLoaiDonViMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLoaiDonVi() throws Exception {
        // Initialize the database
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiDonVi
        LoaiDonVi updatedLoaiDonVi = loaiDonViRepository.findById(loaiDonVi.getIdLoaiDv()).orElseThrow();
        // Disconnect from session so that the updates on updatedLoaiDonVi are not directly saved in db
        em.detach(updatedLoaiDonVi);
        updatedLoaiDonVi.tenLoaiDv(UPDATED_TEN_LOAI_DV);
        LoaiDonViDTO loaiDonViDTO = loaiDonViMapper.toDto(updatedLoaiDonVi);

        restLoaiDonViMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiDonViDTO.getIdLoaiDv())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiDonViDTO))
            )
            .andExpect(status().isOk());

        // Validate the LoaiDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLoaiDonViToMatchAllProperties(updatedLoaiDonVi);
    }

    @Test
    @Transactional
    void putNonExistingLoaiDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());

        // Create the LoaiDonVi
        LoaiDonViDTO loaiDonViDTO = loaiDonViMapper.toDto(loaiDonVi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiDonViMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiDonViDTO.getIdLoaiDv())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiDonViDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLoaiDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());

        // Create the LoaiDonVi
        LoaiDonViDTO loaiDonViDTO = loaiDonViMapper.toDto(loaiDonVi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiDonViMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiDonViDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLoaiDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());

        // Create the LoaiDonVi
        LoaiDonViDTO loaiDonViDTO = loaiDonViMapper.toDto(loaiDonVi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiDonViMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiDonViDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLoaiDonViWithPatch() throws Exception {
        // Initialize the database
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiDonVi using partial update
        LoaiDonVi partialUpdatedLoaiDonVi = new LoaiDonVi();
        partialUpdatedLoaiDonVi.setIdLoaiDv(loaiDonVi.getIdLoaiDv());

        partialUpdatedLoaiDonVi.tenLoaiDv(UPDATED_TEN_LOAI_DV);

        restLoaiDonViMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiDonVi.getIdLoaiDv())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiDonVi))
            )
            .andExpect(status().isOk());

        // Validate the LoaiDonVi in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiDonViUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLoaiDonVi, loaiDonVi),
            getPersistedLoaiDonVi(loaiDonVi)
        );
    }

    @Test
    @Transactional
    void fullUpdateLoaiDonViWithPatch() throws Exception {
        // Initialize the database
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiDonVi using partial update
        LoaiDonVi partialUpdatedLoaiDonVi = new LoaiDonVi();
        partialUpdatedLoaiDonVi.setIdLoaiDv(loaiDonVi.getIdLoaiDv());

        partialUpdatedLoaiDonVi.tenLoaiDv(UPDATED_TEN_LOAI_DV);

        restLoaiDonViMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiDonVi.getIdLoaiDv())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiDonVi))
            )
            .andExpect(status().isOk());

        // Validate the LoaiDonVi in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiDonViUpdatableFieldsEquals(partialUpdatedLoaiDonVi, getPersistedLoaiDonVi(partialUpdatedLoaiDonVi));
    }

    @Test
    @Transactional
    void patchNonExistingLoaiDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());

        // Create the LoaiDonVi
        LoaiDonViDTO loaiDonViDTO = loaiDonViMapper.toDto(loaiDonVi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiDonViMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, loaiDonViDTO.getIdLoaiDv())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiDonViDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLoaiDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());

        // Create the LoaiDonVi
        LoaiDonViDTO loaiDonViDTO = loaiDonViMapper.toDto(loaiDonVi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiDonViMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiDonViDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLoaiDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());

        // Create the LoaiDonVi
        LoaiDonViDTO loaiDonViDTO = loaiDonViMapper.toDto(loaiDonVi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiDonViMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(loaiDonViDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLoaiDonVi() throws Exception {
        // Initialize the database
        loaiDonVi.setIdLoaiDv(UUID.randomUUID().toString());
        insertedLoaiDonVi = loaiDonViRepository.saveAndFlush(loaiDonVi);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the loaiDonVi
        restLoaiDonViMockMvc
            .perform(delete(ENTITY_API_URL_ID, loaiDonVi.getIdLoaiDv()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return loaiDonViRepository.count();
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

    protected LoaiDonVi getPersistedLoaiDonVi(LoaiDonVi loaiDonVi) {
        return loaiDonViRepository.findById(loaiDonVi.getIdLoaiDv()).orElseThrow();
    }

    protected void assertPersistedLoaiDonViToMatchAllProperties(LoaiDonVi expectedLoaiDonVi) {
        assertLoaiDonViAllPropertiesEquals(expectedLoaiDonVi, getPersistedLoaiDonVi(expectedLoaiDonVi));
    }

    protected void assertPersistedLoaiDonViToMatchUpdatableProperties(LoaiDonVi expectedLoaiDonVi) {
        assertLoaiDonViAllUpdatablePropertiesEquals(expectedLoaiDonVi, getPersistedLoaiDonVi(expectedLoaiDonVi));
    }
}
