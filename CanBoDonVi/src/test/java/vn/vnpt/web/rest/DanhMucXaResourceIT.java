package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucXaAsserts.*;
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
import vn.vnpt.domain.DanhMucXa;
import vn.vnpt.repository.DanhMucXaRepository;
import vn.vnpt.service.dto.DanhMucXaDTO;
import vn.vnpt.service.mapper.DanhMucXaMapper;

/**
 * Integration tests for the {@link DanhMucXaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucXaResourceIT {

    private static final String DEFAULT_TEN_XA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_XA = "BBBBBBBBBB";

    private static final String DEFAULT_MA_HUYEN = "AAAAAAAAAA";
    private static final String UPDATED_MA_HUYEN = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-xas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{maXa}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucXaRepository danhMucXaRepository;

    @Autowired
    private DanhMucXaMapper danhMucXaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucXaMockMvc;

    private DanhMucXa danhMucXa;

    private DanhMucXa insertedDanhMucXa;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucXa createEntity() {
        return new DanhMucXa().maXa(UUID.randomUUID().toString()).tenXa(DEFAULT_TEN_XA).maHuyen(DEFAULT_MA_HUYEN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucXa createUpdatedEntity() {
        return new DanhMucXa().maXa(UUID.randomUUID().toString()).tenXa(UPDATED_TEN_XA).maHuyen(UPDATED_MA_HUYEN);
    }

    @BeforeEach
    public void initTest() {
        danhMucXa = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucXa != null) {
            danhMucXaRepository.delete(insertedDanhMucXa);
            insertedDanhMucXa = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucXa() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucXa
        DanhMucXaDTO danhMucXaDTO = danhMucXaMapper.toDto(danhMucXa);
        var returnedDanhMucXaDTO = om.readValue(
            restDanhMucXaMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucXaDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucXaDTO.class
        );

        // Validate the DanhMucXa in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucXa = danhMucXaMapper.toEntity(returnedDanhMucXaDTO);
        assertDanhMucXaUpdatableFieldsEquals(returnedDanhMucXa, getPersistedDanhMucXa(returnedDanhMucXa));

        insertedDanhMucXa = returnedDanhMucXa;
    }

    @Test
    @Transactional
    void createDanhMucXaWithExistingId() throws Exception {
        // Create the DanhMucXa with an existing ID
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);
        DanhMucXaDTO danhMucXaDTO = danhMucXaMapper.toDto(danhMucXa);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucXaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucXaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucXa in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucXas() throws Exception {
        // Initialize the database
        danhMucXa.setMaXa(UUID.randomUUID().toString());
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList
        restDanhMucXaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maXa,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maXa").value(hasItem(danhMucXa.getMaXa())))
            .andExpect(jsonPath("$.[*].tenXa").value(hasItem(DEFAULT_TEN_XA)))
            .andExpect(jsonPath("$.[*].maHuyen").value(hasItem(DEFAULT_MA_HUYEN)));
    }

    @Test
    @Transactional
    void getDanhMucXa() throws Exception {
        // Initialize the database
        danhMucXa.setMaXa(UUID.randomUUID().toString());
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get the danhMucXa
        restDanhMucXaMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucXa.getMaXa()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.maXa").value(danhMucXa.getMaXa()))
            .andExpect(jsonPath("$.tenXa").value(DEFAULT_TEN_XA))
            .andExpect(jsonPath("$.maHuyen").value(DEFAULT_MA_HUYEN));
    }

    @Test
    @Transactional
    void getDanhMucXasByIdFiltering() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        String id = danhMucXa.getMaXa();

        defaultDanhMucXaFiltering("maXa.equals=" + id, "maXa.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllDanhMucXasByTenXaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList where tenXa equals to
        defaultDanhMucXaFiltering("tenXa.equals=" + DEFAULT_TEN_XA, "tenXa.equals=" + UPDATED_TEN_XA);
    }

    @Test
    @Transactional
    void getAllDanhMucXasByTenXaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList where tenXa in
        defaultDanhMucXaFiltering("tenXa.in=" + DEFAULT_TEN_XA + "," + UPDATED_TEN_XA, "tenXa.in=" + UPDATED_TEN_XA);
    }

    @Test
    @Transactional
    void getAllDanhMucXasByTenXaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList where tenXa is not null
        defaultDanhMucXaFiltering("tenXa.specified=true", "tenXa.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucXasByTenXaContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList where tenXa contains
        defaultDanhMucXaFiltering("tenXa.contains=" + DEFAULT_TEN_XA, "tenXa.contains=" + UPDATED_TEN_XA);
    }

    @Test
    @Transactional
    void getAllDanhMucXasByTenXaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList where tenXa does not contain
        defaultDanhMucXaFiltering("tenXa.doesNotContain=" + UPDATED_TEN_XA, "tenXa.doesNotContain=" + DEFAULT_TEN_XA);
    }

    @Test
    @Transactional
    void getAllDanhMucXasByMaHuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList where maHuyen equals to
        defaultDanhMucXaFiltering("maHuyen.equals=" + DEFAULT_MA_HUYEN, "maHuyen.equals=" + UPDATED_MA_HUYEN);
    }

    @Test
    @Transactional
    void getAllDanhMucXasByMaHuyenIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList where maHuyen in
        defaultDanhMucXaFiltering("maHuyen.in=" + DEFAULT_MA_HUYEN + "," + UPDATED_MA_HUYEN, "maHuyen.in=" + UPDATED_MA_HUYEN);
    }

    @Test
    @Transactional
    void getAllDanhMucXasByMaHuyenIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList where maHuyen is not null
        defaultDanhMucXaFiltering("maHuyen.specified=true", "maHuyen.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucXasByMaHuyenContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList where maHuyen contains
        defaultDanhMucXaFiltering("maHuyen.contains=" + DEFAULT_MA_HUYEN, "maHuyen.contains=" + UPDATED_MA_HUYEN);
    }

    @Test
    @Transactional
    void getAllDanhMucXasByMaHuyenNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        // Get all the danhMucXaList where maHuyen does not contain
        defaultDanhMucXaFiltering("maHuyen.doesNotContain=" + UPDATED_MA_HUYEN, "maHuyen.doesNotContain=" + DEFAULT_MA_HUYEN);
    }

    private void defaultDanhMucXaFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDanhMucXaShouldBeFound(shouldBeFound);
        defaultDanhMucXaShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhMucXaShouldBeFound(String filter) throws Exception {
        restDanhMucXaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maXa,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maXa").value(hasItem(danhMucXa.getMaXa())))
            .andExpect(jsonPath("$.[*].tenXa").value(hasItem(DEFAULT_TEN_XA)))
            .andExpect(jsonPath("$.[*].maHuyen").value(hasItem(DEFAULT_MA_HUYEN)));

        // Check, that the count call also returns 1
        restDanhMucXaMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=maXa,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhMucXaShouldNotBeFound(String filter) throws Exception {
        restDanhMucXaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maXa,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhMucXaMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=maXa,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucXa() throws Exception {
        // Get the danhMucXa
        restDanhMucXaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucXa() throws Exception {
        // Initialize the database
        danhMucXa.setMaXa(UUID.randomUUID().toString());
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucXa
        DanhMucXa updatedDanhMucXa = danhMucXaRepository.findById(danhMucXa.getMaXa()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucXa are not directly saved in db
        em.detach(updatedDanhMucXa);
        updatedDanhMucXa.tenXa(UPDATED_TEN_XA).maHuyen(UPDATED_MA_HUYEN);
        DanhMucXaDTO danhMucXaDTO = danhMucXaMapper.toDto(updatedDanhMucXa);

        restDanhMucXaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucXaDTO.getMaXa())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucXaDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucXa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucXaToMatchAllProperties(updatedDanhMucXa);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucXa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucXa.setMaXa(UUID.randomUUID().toString());

        // Create the DanhMucXa
        DanhMucXaDTO danhMucXaDTO = danhMucXaMapper.toDto(danhMucXa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucXaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucXaDTO.getMaXa())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucXaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucXa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucXa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucXa.setMaXa(UUID.randomUUID().toString());

        // Create the DanhMucXa
        DanhMucXaDTO danhMucXaDTO = danhMucXaMapper.toDto(danhMucXa);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucXaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucXaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucXa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucXa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucXa.setMaXa(UUID.randomUUID().toString());

        // Create the DanhMucXa
        DanhMucXaDTO danhMucXaDTO = danhMucXaMapper.toDto(danhMucXa);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucXaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucXaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucXa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucXaWithPatch() throws Exception {
        // Initialize the database
        danhMucXa.setMaXa(UUID.randomUUID().toString());
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucXa using partial update
        DanhMucXa partialUpdatedDanhMucXa = new DanhMucXa();
        partialUpdatedDanhMucXa.setMaXa(danhMucXa.getMaXa());

        partialUpdatedDanhMucXa.maHuyen(UPDATED_MA_HUYEN);

        restDanhMucXaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucXa.getMaXa())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucXa))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucXa in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucXaUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucXa, danhMucXa),
            getPersistedDanhMucXa(danhMucXa)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucXaWithPatch() throws Exception {
        // Initialize the database
        danhMucXa.setMaXa(UUID.randomUUID().toString());
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucXa using partial update
        DanhMucXa partialUpdatedDanhMucXa = new DanhMucXa();
        partialUpdatedDanhMucXa.setMaXa(danhMucXa.getMaXa());

        partialUpdatedDanhMucXa.tenXa(UPDATED_TEN_XA).maHuyen(UPDATED_MA_HUYEN);

        restDanhMucXaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucXa.getMaXa())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucXa))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucXa in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucXaUpdatableFieldsEquals(partialUpdatedDanhMucXa, getPersistedDanhMucXa(partialUpdatedDanhMucXa));
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucXa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucXa.setMaXa(UUID.randomUUID().toString());

        // Create the DanhMucXa
        DanhMucXaDTO danhMucXaDTO = danhMucXaMapper.toDto(danhMucXa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucXaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucXaDTO.getMaXa())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucXaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucXa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucXa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucXa.setMaXa(UUID.randomUUID().toString());

        // Create the DanhMucXa
        DanhMucXaDTO danhMucXaDTO = danhMucXaMapper.toDto(danhMucXa);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucXaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucXaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucXa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucXa() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucXa.setMaXa(UUID.randomUUID().toString());

        // Create the DanhMucXa
        DanhMucXaDTO danhMucXaDTO = danhMucXaMapper.toDto(danhMucXa);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucXaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucXaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucXa in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucXa() throws Exception {
        // Initialize the database
        danhMucXa.setMaXa(UUID.randomUUID().toString());
        insertedDanhMucXa = danhMucXaRepository.saveAndFlush(danhMucXa);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucXa
        restDanhMucXaMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucXa.getMaXa()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucXaRepository.count();
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

    protected DanhMucXa getPersistedDanhMucXa(DanhMucXa danhMucXa) {
        return danhMucXaRepository.findById(danhMucXa.getMaXa()).orElseThrow();
    }

    protected void assertPersistedDanhMucXaToMatchAllProperties(DanhMucXa expectedDanhMucXa) {
        assertDanhMucXaAllPropertiesEquals(expectedDanhMucXa, getPersistedDanhMucXa(expectedDanhMucXa));
    }

    protected void assertPersistedDanhMucXaToMatchUpdatableProperties(DanhMucXa expectedDanhMucXa) {
        assertDanhMucXaAllUpdatablePropertiesEquals(expectedDanhMucXa, getPersistedDanhMucXa(expectedDanhMucXa));
    }
}
