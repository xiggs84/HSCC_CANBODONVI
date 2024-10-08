package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucTinhAsserts.*;
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
import vn.vnpt.domain.DanhMucTinh;
import vn.vnpt.repository.DanhMucTinhRepository;
import vn.vnpt.service.dto.DanhMucTinhDTO;
import vn.vnpt.service.mapper.DanhMucTinhMapper;

/**
 * Integration tests for the {@link DanhMucTinhResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucTinhResourceIT {

    private static final String DEFAULT_TEN_TINH = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TINH = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-tinhs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{maTinh}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucTinhRepository danhMucTinhRepository;

    @Autowired
    private DanhMucTinhMapper danhMucTinhMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucTinhMockMvc;

    private DanhMucTinh danhMucTinh;

    private DanhMucTinh insertedDanhMucTinh;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucTinh createEntity() {
        return new DanhMucTinh().maTinh(UUID.randomUUID().toString()).tenTinh(DEFAULT_TEN_TINH);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucTinh createUpdatedEntity() {
        return new DanhMucTinh().maTinh(UUID.randomUUID().toString()).tenTinh(UPDATED_TEN_TINH);
    }

    @BeforeEach
    public void initTest() {
        danhMucTinh = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucTinh != null) {
            danhMucTinhRepository.delete(insertedDanhMucTinh);
            insertedDanhMucTinh = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucTinh() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucTinh
        DanhMucTinhDTO danhMucTinhDTO = danhMucTinhMapper.toDto(danhMucTinh);
        var returnedDanhMucTinhDTO = om.readValue(
            restDanhMucTinhMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucTinhDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucTinhDTO.class
        );

        // Validate the DanhMucTinh in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucTinh = danhMucTinhMapper.toEntity(returnedDanhMucTinhDTO);
        assertDanhMucTinhUpdatableFieldsEquals(returnedDanhMucTinh, getPersistedDanhMucTinh(returnedDanhMucTinh));

        insertedDanhMucTinh = returnedDanhMucTinh;
    }

    @Test
    @Transactional
    void createDanhMucTinhWithExistingId() throws Exception {
        // Create the DanhMucTinh with an existing ID
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);
        DanhMucTinhDTO danhMucTinhDTO = danhMucTinhMapper.toDto(danhMucTinh);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucTinhMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucTinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucTinh in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucTinhs() throws Exception {
        // Initialize the database
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        // Get all the danhMucTinhList
        restDanhMucTinhMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maTinh,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(danhMucTinh.getMaTinh())))
            .andExpect(jsonPath("$.[*].tenTinh").value(hasItem(DEFAULT_TEN_TINH)));
    }

    @Test
    @Transactional
    void getDanhMucTinh() throws Exception {
        // Initialize the database
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        // Get the danhMucTinh
        restDanhMucTinhMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucTinh.getMaTinh()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.maTinh").value(danhMucTinh.getMaTinh()))
            .andExpect(jsonPath("$.tenTinh").value(DEFAULT_TEN_TINH));
    }

    @Test
    @Transactional
    void getDanhMucTinhsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        String id = danhMucTinh.getMaTinh();

        defaultDanhMucTinhFiltering("maTinh.equals=" + id, "maTinh.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllDanhMucTinhsByTenTinhIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        // Get all the danhMucTinhList where tenTinh equals to
        defaultDanhMucTinhFiltering("tenTinh.equals=" + DEFAULT_TEN_TINH, "tenTinh.equals=" + UPDATED_TEN_TINH);
    }

    @Test
    @Transactional
    void getAllDanhMucTinhsByTenTinhIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        // Get all the danhMucTinhList where tenTinh in
        defaultDanhMucTinhFiltering("tenTinh.in=" + DEFAULT_TEN_TINH + "," + UPDATED_TEN_TINH, "tenTinh.in=" + UPDATED_TEN_TINH);
    }

    @Test
    @Transactional
    void getAllDanhMucTinhsByTenTinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        // Get all the danhMucTinhList where tenTinh is not null
        defaultDanhMucTinhFiltering("tenTinh.specified=true", "tenTinh.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucTinhsByTenTinhContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        // Get all the danhMucTinhList where tenTinh contains
        defaultDanhMucTinhFiltering("tenTinh.contains=" + DEFAULT_TEN_TINH, "tenTinh.contains=" + UPDATED_TEN_TINH);
    }

    @Test
    @Transactional
    void getAllDanhMucTinhsByTenTinhNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        // Get all the danhMucTinhList where tenTinh does not contain
        defaultDanhMucTinhFiltering("tenTinh.doesNotContain=" + UPDATED_TEN_TINH, "tenTinh.doesNotContain=" + DEFAULT_TEN_TINH);
    }

    private void defaultDanhMucTinhFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDanhMucTinhShouldBeFound(shouldBeFound);
        defaultDanhMucTinhShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhMucTinhShouldBeFound(String filter) throws Exception {
        restDanhMucTinhMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maTinh,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(danhMucTinh.getMaTinh())))
            .andExpect(jsonPath("$.[*].tenTinh").value(hasItem(DEFAULT_TEN_TINH)));

        // Check, that the count call also returns 1
        restDanhMucTinhMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=maTinh,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhMucTinhShouldNotBeFound(String filter) throws Exception {
        restDanhMucTinhMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maTinh,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhMucTinhMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=maTinh,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucTinh() throws Exception {
        // Get the danhMucTinh
        restDanhMucTinhMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucTinh() throws Exception {
        // Initialize the database
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucTinh
        DanhMucTinh updatedDanhMucTinh = danhMucTinhRepository.findById(danhMucTinh.getMaTinh()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucTinh are not directly saved in db
        em.detach(updatedDanhMucTinh);
        updatedDanhMucTinh.tenTinh(UPDATED_TEN_TINH);
        DanhMucTinhDTO danhMucTinhDTO = danhMucTinhMapper.toDto(updatedDanhMucTinh);

        restDanhMucTinhMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucTinhDTO.getMaTinh())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucTinhDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucTinh in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucTinhToMatchAllProperties(updatedDanhMucTinh);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucTinh() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());

        // Create the DanhMucTinh
        DanhMucTinhDTO danhMucTinhDTO = danhMucTinhMapper.toDto(danhMucTinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucTinhMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucTinhDTO.getMaTinh())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucTinhDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucTinh in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucTinh() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());

        // Create the DanhMucTinh
        DanhMucTinhDTO danhMucTinhDTO = danhMucTinhMapper.toDto(danhMucTinh);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucTinhMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucTinhDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucTinh in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucTinh() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());

        // Create the DanhMucTinh
        DanhMucTinhDTO danhMucTinhDTO = danhMucTinhMapper.toDto(danhMucTinh);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucTinhMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucTinhDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucTinh in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucTinhWithPatch() throws Exception {
        // Initialize the database
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucTinh using partial update
        DanhMucTinh partialUpdatedDanhMucTinh = new DanhMucTinh();
        partialUpdatedDanhMucTinh.setMaTinh(danhMucTinh.getMaTinh());

        restDanhMucTinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucTinh.getMaTinh())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucTinh))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucTinh in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucTinhUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucTinh, danhMucTinh),
            getPersistedDanhMucTinh(danhMucTinh)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucTinhWithPatch() throws Exception {
        // Initialize the database
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucTinh using partial update
        DanhMucTinh partialUpdatedDanhMucTinh = new DanhMucTinh();
        partialUpdatedDanhMucTinh.setMaTinh(danhMucTinh.getMaTinh());

        partialUpdatedDanhMucTinh.tenTinh(UPDATED_TEN_TINH);

        restDanhMucTinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucTinh.getMaTinh())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucTinh))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucTinh in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucTinhUpdatableFieldsEquals(partialUpdatedDanhMucTinh, getPersistedDanhMucTinh(partialUpdatedDanhMucTinh));
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucTinh() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());

        // Create the DanhMucTinh
        DanhMucTinhDTO danhMucTinhDTO = danhMucTinhMapper.toDto(danhMucTinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucTinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucTinhDTO.getMaTinh())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucTinhDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucTinh in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucTinh() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());

        // Create the DanhMucTinh
        DanhMucTinhDTO danhMucTinhDTO = danhMucTinhMapper.toDto(danhMucTinh);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucTinhMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucTinhDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucTinh in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucTinh() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());

        // Create the DanhMucTinh
        DanhMucTinhDTO danhMucTinhDTO = danhMucTinhMapper.toDto(danhMucTinh);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucTinhMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucTinhDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucTinh in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucTinh() throws Exception {
        // Initialize the database
        danhMucTinh.setMaTinh(UUID.randomUUID().toString());
        insertedDanhMucTinh = danhMucTinhRepository.saveAndFlush(danhMucTinh);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucTinh
        restDanhMucTinhMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucTinh.getMaTinh()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucTinhRepository.count();
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

    protected DanhMucTinh getPersistedDanhMucTinh(DanhMucTinh danhMucTinh) {
        return danhMucTinhRepository.findById(danhMucTinh.getMaTinh()).orElseThrow();
    }

    protected void assertPersistedDanhMucTinhToMatchAllProperties(DanhMucTinh expectedDanhMucTinh) {
        assertDanhMucTinhAllPropertiesEquals(expectedDanhMucTinh, getPersistedDanhMucTinh(expectedDanhMucTinh));
    }

    protected void assertPersistedDanhMucTinhToMatchUpdatableProperties(DanhMucTinh expectedDanhMucTinh) {
        assertDanhMucTinhAllUpdatablePropertiesEquals(expectedDanhMucTinh, getPersistedDanhMucTinh(expectedDanhMucTinh));
    }
}
