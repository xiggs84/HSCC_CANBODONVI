package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucHuyenAsserts.*;
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
import vn.vnpt.domain.DanhMucHuyen;
import vn.vnpt.repository.DanhMucHuyenRepository;
import vn.vnpt.service.dto.DanhMucHuyenDTO;
import vn.vnpt.service.mapper.DanhMucHuyenMapper;

/**
 * Integration tests for the {@link DanhMucHuyenResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucHuyenResourceIT {

    private static final String DEFAULT_TEN_HUYEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_HUYEN = "BBBBBBBBBB";

    private static final String DEFAULT_MA_TINH = "AAAAAAAAAA";
    private static final String UPDATED_MA_TINH = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-huyens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{maHuyen}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucHuyenRepository danhMucHuyenRepository;

    @Autowired
    private DanhMucHuyenMapper danhMucHuyenMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucHuyenMockMvc;

    private DanhMucHuyen danhMucHuyen;

    private DanhMucHuyen insertedDanhMucHuyen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucHuyen createEntity() {
        return new DanhMucHuyen().maHuyen(UUID.randomUUID().toString()).tenHuyen(DEFAULT_TEN_HUYEN).maTinh(DEFAULT_MA_TINH);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucHuyen createUpdatedEntity() {
        return new DanhMucHuyen().maHuyen(UUID.randomUUID().toString()).tenHuyen(UPDATED_TEN_HUYEN).maTinh(UPDATED_MA_TINH);
    }

    @BeforeEach
    public void initTest() {
        danhMucHuyen = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucHuyen != null) {
            danhMucHuyenRepository.delete(insertedDanhMucHuyen);
            insertedDanhMucHuyen = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucHuyen() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucHuyen
        DanhMucHuyenDTO danhMucHuyenDTO = danhMucHuyenMapper.toDto(danhMucHuyen);
        var returnedDanhMucHuyenDTO = om.readValue(
            restDanhMucHuyenMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucHuyenDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucHuyenDTO.class
        );

        // Validate the DanhMucHuyen in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucHuyen = danhMucHuyenMapper.toEntity(returnedDanhMucHuyenDTO);
        assertDanhMucHuyenUpdatableFieldsEquals(returnedDanhMucHuyen, getPersistedDanhMucHuyen(returnedDanhMucHuyen));

        insertedDanhMucHuyen = returnedDanhMucHuyen;
    }

    @Test
    @Transactional
    void createDanhMucHuyenWithExistingId() throws Exception {
        // Create the DanhMucHuyen with an existing ID
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);
        DanhMucHuyenDTO danhMucHuyenDTO = danhMucHuyenMapper.toDto(danhMucHuyen);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucHuyenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucHuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucHuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucHuyens() throws Exception {
        // Initialize the database
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList
        restDanhMucHuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maHuyen,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maHuyen").value(hasItem(danhMucHuyen.getMaHuyen())))
            .andExpect(jsonPath("$.[*].tenHuyen").value(hasItem(DEFAULT_TEN_HUYEN)))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(DEFAULT_MA_TINH)));
    }

    @Test
    @Transactional
    void getDanhMucHuyen() throws Exception {
        // Initialize the database
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get the danhMucHuyen
        restDanhMucHuyenMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucHuyen.getMaHuyen()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.maHuyen").value(danhMucHuyen.getMaHuyen()))
            .andExpect(jsonPath("$.tenHuyen").value(DEFAULT_TEN_HUYEN))
            .andExpect(jsonPath("$.maTinh").value(DEFAULT_MA_TINH));
    }

    @Test
    @Transactional
    void getDanhMucHuyensByIdFiltering() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        String id = danhMucHuyen.getMaHuyen();

        defaultDanhMucHuyenFiltering("maHuyen.equals=" + id, "maHuyen.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllDanhMucHuyensByTenHuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList where tenHuyen equals to
        defaultDanhMucHuyenFiltering("tenHuyen.equals=" + DEFAULT_TEN_HUYEN, "tenHuyen.equals=" + UPDATED_TEN_HUYEN);
    }

    @Test
    @Transactional
    void getAllDanhMucHuyensByTenHuyenIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList where tenHuyen in
        defaultDanhMucHuyenFiltering("tenHuyen.in=" + DEFAULT_TEN_HUYEN + "," + UPDATED_TEN_HUYEN, "tenHuyen.in=" + UPDATED_TEN_HUYEN);
    }

    @Test
    @Transactional
    void getAllDanhMucHuyensByTenHuyenIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList where tenHuyen is not null
        defaultDanhMucHuyenFiltering("tenHuyen.specified=true", "tenHuyen.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucHuyensByTenHuyenContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList where tenHuyen contains
        defaultDanhMucHuyenFiltering("tenHuyen.contains=" + DEFAULT_TEN_HUYEN, "tenHuyen.contains=" + UPDATED_TEN_HUYEN);
    }

    @Test
    @Transactional
    void getAllDanhMucHuyensByTenHuyenNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList where tenHuyen does not contain
        defaultDanhMucHuyenFiltering("tenHuyen.doesNotContain=" + UPDATED_TEN_HUYEN, "tenHuyen.doesNotContain=" + DEFAULT_TEN_HUYEN);
    }

    @Test
    @Transactional
    void getAllDanhMucHuyensByMaTinhIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList where maTinh equals to
        defaultDanhMucHuyenFiltering("maTinh.equals=" + DEFAULT_MA_TINH, "maTinh.equals=" + UPDATED_MA_TINH);
    }

    @Test
    @Transactional
    void getAllDanhMucHuyensByMaTinhIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList where maTinh in
        defaultDanhMucHuyenFiltering("maTinh.in=" + DEFAULT_MA_TINH + "," + UPDATED_MA_TINH, "maTinh.in=" + UPDATED_MA_TINH);
    }

    @Test
    @Transactional
    void getAllDanhMucHuyensByMaTinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList where maTinh is not null
        defaultDanhMucHuyenFiltering("maTinh.specified=true", "maTinh.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucHuyensByMaTinhContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList where maTinh contains
        defaultDanhMucHuyenFiltering("maTinh.contains=" + DEFAULT_MA_TINH, "maTinh.contains=" + UPDATED_MA_TINH);
    }

    @Test
    @Transactional
    void getAllDanhMucHuyensByMaTinhNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        // Get all the danhMucHuyenList where maTinh does not contain
        defaultDanhMucHuyenFiltering("maTinh.doesNotContain=" + UPDATED_MA_TINH, "maTinh.doesNotContain=" + DEFAULT_MA_TINH);
    }

    private void defaultDanhMucHuyenFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDanhMucHuyenShouldBeFound(shouldBeFound);
        defaultDanhMucHuyenShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhMucHuyenShouldBeFound(String filter) throws Exception {
        restDanhMucHuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maHuyen,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maHuyen").value(hasItem(danhMucHuyen.getMaHuyen())))
            .andExpect(jsonPath("$.[*].tenHuyen").value(hasItem(DEFAULT_TEN_HUYEN)))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(DEFAULT_MA_TINH)));

        // Check, that the count call also returns 1
        restDanhMucHuyenMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=maHuyen,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhMucHuyenShouldNotBeFound(String filter) throws Exception {
        restDanhMucHuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maHuyen,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhMucHuyenMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=maHuyen,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucHuyen() throws Exception {
        // Get the danhMucHuyen
        restDanhMucHuyenMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucHuyen() throws Exception {
        // Initialize the database
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucHuyen
        DanhMucHuyen updatedDanhMucHuyen = danhMucHuyenRepository.findById(danhMucHuyen.getMaHuyen()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucHuyen are not directly saved in db
        em.detach(updatedDanhMucHuyen);
        updatedDanhMucHuyen.tenHuyen(UPDATED_TEN_HUYEN).maTinh(UPDATED_MA_TINH);
        DanhMucHuyenDTO danhMucHuyenDTO = danhMucHuyenMapper.toDto(updatedDanhMucHuyen);

        restDanhMucHuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucHuyenDTO.getMaHuyen())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucHuyenDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucHuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucHuyenToMatchAllProperties(updatedDanhMucHuyen);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucHuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());

        // Create the DanhMucHuyen
        DanhMucHuyenDTO danhMucHuyenDTO = danhMucHuyenMapper.toDto(danhMucHuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucHuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucHuyenDTO.getMaHuyen())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucHuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucHuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucHuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());

        // Create the DanhMucHuyen
        DanhMucHuyenDTO danhMucHuyenDTO = danhMucHuyenMapper.toDto(danhMucHuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucHuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucHuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucHuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucHuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());

        // Create the DanhMucHuyen
        DanhMucHuyenDTO danhMucHuyenDTO = danhMucHuyenMapper.toDto(danhMucHuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucHuyenMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucHuyenDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucHuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucHuyenWithPatch() throws Exception {
        // Initialize the database
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucHuyen using partial update
        DanhMucHuyen partialUpdatedDanhMucHuyen = new DanhMucHuyen();
        partialUpdatedDanhMucHuyen.setMaHuyen(danhMucHuyen.getMaHuyen());

        partialUpdatedDanhMucHuyen.tenHuyen(UPDATED_TEN_HUYEN);

        restDanhMucHuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucHuyen.getMaHuyen())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucHuyen))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucHuyen in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucHuyenUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucHuyen, danhMucHuyen),
            getPersistedDanhMucHuyen(danhMucHuyen)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucHuyenWithPatch() throws Exception {
        // Initialize the database
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucHuyen using partial update
        DanhMucHuyen partialUpdatedDanhMucHuyen = new DanhMucHuyen();
        partialUpdatedDanhMucHuyen.setMaHuyen(danhMucHuyen.getMaHuyen());

        partialUpdatedDanhMucHuyen.tenHuyen(UPDATED_TEN_HUYEN).maTinh(UPDATED_MA_TINH);

        restDanhMucHuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucHuyen.getMaHuyen())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucHuyen))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucHuyen in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucHuyenUpdatableFieldsEquals(partialUpdatedDanhMucHuyen, getPersistedDanhMucHuyen(partialUpdatedDanhMucHuyen));
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucHuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());

        // Create the DanhMucHuyen
        DanhMucHuyenDTO danhMucHuyenDTO = danhMucHuyenMapper.toDto(danhMucHuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucHuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucHuyenDTO.getMaHuyen())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucHuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucHuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucHuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());

        // Create the DanhMucHuyen
        DanhMucHuyenDTO danhMucHuyenDTO = danhMucHuyenMapper.toDto(danhMucHuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucHuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucHuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucHuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucHuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());

        // Create the DanhMucHuyen
        DanhMucHuyenDTO danhMucHuyenDTO = danhMucHuyenMapper.toDto(danhMucHuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucHuyenMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucHuyenDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucHuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucHuyen() throws Exception {
        // Initialize the database
        danhMucHuyen.setMaHuyen(UUID.randomUUID().toString());
        insertedDanhMucHuyen = danhMucHuyenRepository.saveAndFlush(danhMucHuyen);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucHuyen
        restDanhMucHuyenMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucHuyen.getMaHuyen()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucHuyenRepository.count();
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

    protected DanhMucHuyen getPersistedDanhMucHuyen(DanhMucHuyen danhMucHuyen) {
        return danhMucHuyenRepository.findById(danhMucHuyen.getMaHuyen()).orElseThrow();
    }

    protected void assertPersistedDanhMucHuyenToMatchAllProperties(DanhMucHuyen expectedDanhMucHuyen) {
        assertDanhMucHuyenAllPropertiesEquals(expectedDanhMucHuyen, getPersistedDanhMucHuyen(expectedDanhMucHuyen));
    }

    protected void assertPersistedDanhMucHuyenToMatchUpdatableProperties(DanhMucHuyen expectedDanhMucHuyen) {
        assertDanhMucHuyenAllUpdatablePropertiesEquals(expectedDanhMucHuyen, getPersistedDanhMucHuyen(expectedDanhMucHuyen));
    }
}
