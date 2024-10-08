package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DmXaTmpAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
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
import vn.vnpt.domain.DmXaTmp;
import vn.vnpt.repository.DmXaTmpRepository;
import vn.vnpt.service.dto.DmXaTmpDTO;
import vn.vnpt.service.mapper.DmXaTmpMapper;

/**
 * Integration tests for the {@link DmXaTmpResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DmXaTmpResourceIT {

    private static final String DEFAULT_TEN_XA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_XA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/dm-xa-tmps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{maXa}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DmXaTmpRepository dmXaTmpRepository;

    @Autowired
    private DmXaTmpMapper dmXaTmpMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDmXaTmpMockMvc;

    private DmXaTmp dmXaTmp;

    private DmXaTmp insertedDmXaTmp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmXaTmp createEntity() {
        return new DmXaTmp().tenXa(DEFAULT_TEN_XA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmXaTmp createUpdatedEntity() {
        return new DmXaTmp().tenXa(UPDATED_TEN_XA);
    }

    @BeforeEach
    public void initTest() {
        dmXaTmp = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDmXaTmp != null) {
            dmXaTmpRepository.delete(insertedDmXaTmp);
            insertedDmXaTmp = null;
        }
    }

    @Test
    @Transactional
    void createDmXaTmp() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DmXaTmp
        DmXaTmpDTO dmXaTmpDTO = dmXaTmpMapper.toDto(dmXaTmp);
        var returnedDmXaTmpDTO = om.readValue(
            restDmXaTmpMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmXaTmpDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DmXaTmpDTO.class
        );

        // Validate the DmXaTmp in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDmXaTmp = dmXaTmpMapper.toEntity(returnedDmXaTmpDTO);
        assertDmXaTmpUpdatableFieldsEquals(returnedDmXaTmp, getPersistedDmXaTmp(returnedDmXaTmp));

        insertedDmXaTmp = returnedDmXaTmp;
    }

    @Test
    @Transactional
    void createDmXaTmpWithExistingId() throws Exception {
        // Create the DmXaTmp with an existing ID
        dmXaTmp.setMaXa(1L);
        DmXaTmpDTO dmXaTmpDTO = dmXaTmpMapper.toDto(dmXaTmp);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmXaTmpMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmXaTmpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmXaTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDmXaTmps() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        // Get all the dmXaTmpList
        restDmXaTmpMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maXa,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maXa").value(hasItem(dmXaTmp.getMaXa().intValue())))
            .andExpect(jsonPath("$.[*].tenXa").value(hasItem(DEFAULT_TEN_XA)));
    }

    @Test
    @Transactional
    void getDmXaTmp() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        // Get the dmXaTmp
        restDmXaTmpMockMvc
            .perform(get(ENTITY_API_URL_ID, dmXaTmp.getMaXa()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.maXa").value(dmXaTmp.getMaXa().intValue()))
            .andExpect(jsonPath("$.tenXa").value(DEFAULT_TEN_XA));
    }

    @Test
    @Transactional
    void getDmXaTmpsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        Long id = dmXaTmp.getMaXa();

        defaultDmXaTmpFiltering("maXa.equals=" + id, "maXa.notEquals=" + id);

        defaultDmXaTmpFiltering("maXa.greaterThanOrEqual=" + id, "maXa.greaterThan=" + id);

        defaultDmXaTmpFiltering("maXa.lessThanOrEqual=" + id, "maXa.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDmXaTmpsByTenXaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        // Get all the dmXaTmpList where tenXa equals to
        defaultDmXaTmpFiltering("tenXa.equals=" + DEFAULT_TEN_XA, "tenXa.equals=" + UPDATED_TEN_XA);
    }

    @Test
    @Transactional
    void getAllDmXaTmpsByTenXaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        // Get all the dmXaTmpList where tenXa in
        defaultDmXaTmpFiltering("tenXa.in=" + DEFAULT_TEN_XA + "," + UPDATED_TEN_XA, "tenXa.in=" + UPDATED_TEN_XA);
    }

    @Test
    @Transactional
    void getAllDmXaTmpsByTenXaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        // Get all the dmXaTmpList where tenXa is not null
        defaultDmXaTmpFiltering("tenXa.specified=true", "tenXa.specified=false");
    }

    @Test
    @Transactional
    void getAllDmXaTmpsByTenXaContainsSomething() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        // Get all the dmXaTmpList where tenXa contains
        defaultDmXaTmpFiltering("tenXa.contains=" + DEFAULT_TEN_XA, "tenXa.contains=" + UPDATED_TEN_XA);
    }

    @Test
    @Transactional
    void getAllDmXaTmpsByTenXaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        // Get all the dmXaTmpList where tenXa does not contain
        defaultDmXaTmpFiltering("tenXa.doesNotContain=" + UPDATED_TEN_XA, "tenXa.doesNotContain=" + DEFAULT_TEN_XA);
    }

    private void defaultDmXaTmpFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDmXaTmpShouldBeFound(shouldBeFound);
        defaultDmXaTmpShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmXaTmpShouldBeFound(String filter) throws Exception {
        restDmXaTmpMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maXa,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maXa").value(hasItem(dmXaTmp.getMaXa().intValue())))
            .andExpect(jsonPath("$.[*].tenXa").value(hasItem(DEFAULT_TEN_XA)));

        // Check, that the count call also returns 1
        restDmXaTmpMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=maXa,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmXaTmpShouldNotBeFound(String filter) throws Exception {
        restDmXaTmpMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maXa,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmXaTmpMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=maXa,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDmXaTmp() throws Exception {
        // Get the dmXaTmp
        restDmXaTmpMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDmXaTmp() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmXaTmp
        DmXaTmp updatedDmXaTmp = dmXaTmpRepository.findById(dmXaTmp.getMaXa()).orElseThrow();
        // Disconnect from session so that the updates on updatedDmXaTmp are not directly saved in db
        em.detach(updatedDmXaTmp);
        updatedDmXaTmp.tenXa(UPDATED_TEN_XA);
        DmXaTmpDTO dmXaTmpDTO = dmXaTmpMapper.toDto(updatedDmXaTmp);

        restDmXaTmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmXaTmpDTO.getMaXa())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmXaTmpDTO))
            )
            .andExpect(status().isOk());

        // Validate the DmXaTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDmXaTmpToMatchAllProperties(updatedDmXaTmp);
    }

    @Test
    @Transactional
    void putNonExistingDmXaTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmXaTmp.setMaXa(longCount.incrementAndGet());

        // Create the DmXaTmp
        DmXaTmpDTO dmXaTmpDTO = dmXaTmpMapper.toDto(dmXaTmp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmXaTmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmXaTmpDTO.getMaXa())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmXaTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmXaTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDmXaTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmXaTmp.setMaXa(longCount.incrementAndGet());

        // Create the DmXaTmp
        DmXaTmpDTO dmXaTmpDTO = dmXaTmpMapper.toDto(dmXaTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmXaTmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmXaTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmXaTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDmXaTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmXaTmp.setMaXa(longCount.incrementAndGet());

        // Create the DmXaTmp
        DmXaTmpDTO dmXaTmpDTO = dmXaTmpMapper.toDto(dmXaTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmXaTmpMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmXaTmpDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmXaTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDmXaTmpWithPatch() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmXaTmp using partial update
        DmXaTmp partialUpdatedDmXaTmp = new DmXaTmp();
        partialUpdatedDmXaTmp.setMaXa(dmXaTmp.getMaXa());

        partialUpdatedDmXaTmp.tenXa(UPDATED_TEN_XA);

        restDmXaTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmXaTmp.getMaXa())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmXaTmp))
            )
            .andExpect(status().isOk());

        // Validate the DmXaTmp in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmXaTmpUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedDmXaTmp, dmXaTmp), getPersistedDmXaTmp(dmXaTmp));
    }

    @Test
    @Transactional
    void fullUpdateDmXaTmpWithPatch() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmXaTmp using partial update
        DmXaTmp partialUpdatedDmXaTmp = new DmXaTmp();
        partialUpdatedDmXaTmp.setMaXa(dmXaTmp.getMaXa());

        partialUpdatedDmXaTmp.tenXa(UPDATED_TEN_XA);

        restDmXaTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmXaTmp.getMaXa())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmXaTmp))
            )
            .andExpect(status().isOk());

        // Validate the DmXaTmp in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmXaTmpUpdatableFieldsEquals(partialUpdatedDmXaTmp, getPersistedDmXaTmp(partialUpdatedDmXaTmp));
    }

    @Test
    @Transactional
    void patchNonExistingDmXaTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmXaTmp.setMaXa(longCount.incrementAndGet());

        // Create the DmXaTmp
        DmXaTmpDTO dmXaTmpDTO = dmXaTmpMapper.toDto(dmXaTmp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmXaTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dmXaTmpDTO.getMaXa())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmXaTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmXaTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDmXaTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmXaTmp.setMaXa(longCount.incrementAndGet());

        // Create the DmXaTmp
        DmXaTmpDTO dmXaTmpDTO = dmXaTmpMapper.toDto(dmXaTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmXaTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmXaTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmXaTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDmXaTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmXaTmp.setMaXa(longCount.incrementAndGet());

        // Create the DmXaTmp
        DmXaTmpDTO dmXaTmpDTO = dmXaTmpMapper.toDto(dmXaTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmXaTmpMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dmXaTmpDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmXaTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDmXaTmp() throws Exception {
        // Initialize the database
        insertedDmXaTmp = dmXaTmpRepository.saveAndFlush(dmXaTmp);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dmXaTmp
        restDmXaTmpMockMvc
            .perform(delete(ENTITY_API_URL_ID, dmXaTmp.getMaXa()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dmXaTmpRepository.count();
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

    protected DmXaTmp getPersistedDmXaTmp(DmXaTmp dmXaTmp) {
        return dmXaTmpRepository.findById(dmXaTmp.getMaXa()).orElseThrow();
    }

    protected void assertPersistedDmXaTmpToMatchAllProperties(DmXaTmp expectedDmXaTmp) {
        assertDmXaTmpAllPropertiesEquals(expectedDmXaTmp, getPersistedDmXaTmp(expectedDmXaTmp));
    }

    protected void assertPersistedDmXaTmpToMatchUpdatableProperties(DmXaTmp expectedDmXaTmp) {
        assertDmXaTmpAllUpdatablePropertiesEquals(expectedDmXaTmp, getPersistedDmXaTmp(expectedDmXaTmp));
    }
}
