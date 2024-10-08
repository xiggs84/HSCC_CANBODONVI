package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DmHuyenTmpAsserts.*;
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
import vn.vnpt.domain.DmHuyenTmp;
import vn.vnpt.repository.DmHuyenTmpRepository;
import vn.vnpt.service.dto.DmHuyenTmpDTO;
import vn.vnpt.service.mapper.DmHuyenTmpMapper;

/**
 * Integration tests for the {@link DmHuyenTmpResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DmHuyenTmpResourceIT {

    private static final String DEFAULT_TEN_HUYEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_HUYEN = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/dm-huyen-tmps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{maHuyen}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DmHuyenTmpRepository dmHuyenTmpRepository;

    @Autowired
    private DmHuyenTmpMapper dmHuyenTmpMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDmHuyenTmpMockMvc;

    private DmHuyenTmp dmHuyenTmp;

    private DmHuyenTmp insertedDmHuyenTmp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmHuyenTmp createEntity() {
        return new DmHuyenTmp().tenHuyen(DEFAULT_TEN_HUYEN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmHuyenTmp createUpdatedEntity() {
        return new DmHuyenTmp().tenHuyen(UPDATED_TEN_HUYEN);
    }

    @BeforeEach
    public void initTest() {
        dmHuyenTmp = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDmHuyenTmp != null) {
            dmHuyenTmpRepository.delete(insertedDmHuyenTmp);
            insertedDmHuyenTmp = null;
        }
    }

    @Test
    @Transactional
    void createDmHuyenTmp() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DmHuyenTmp
        DmHuyenTmpDTO dmHuyenTmpDTO = dmHuyenTmpMapper.toDto(dmHuyenTmp);
        var returnedDmHuyenTmpDTO = om.readValue(
            restDmHuyenTmpMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmHuyenTmpDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DmHuyenTmpDTO.class
        );

        // Validate the DmHuyenTmp in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDmHuyenTmp = dmHuyenTmpMapper.toEntity(returnedDmHuyenTmpDTO);
        assertDmHuyenTmpUpdatableFieldsEquals(returnedDmHuyenTmp, getPersistedDmHuyenTmp(returnedDmHuyenTmp));

        insertedDmHuyenTmp = returnedDmHuyenTmp;
    }

    @Test
    @Transactional
    void createDmHuyenTmpWithExistingId() throws Exception {
        // Create the DmHuyenTmp with an existing ID
        dmHuyenTmp.setMaHuyen(1L);
        DmHuyenTmpDTO dmHuyenTmpDTO = dmHuyenTmpMapper.toDto(dmHuyenTmp);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmHuyenTmpMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmHuyenTmpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmHuyenTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDmHuyenTmps() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        // Get all the dmHuyenTmpList
        restDmHuyenTmpMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maHuyen,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maHuyen").value(hasItem(dmHuyenTmp.getMaHuyen().intValue())))
            .andExpect(jsonPath("$.[*].tenHuyen").value(hasItem(DEFAULT_TEN_HUYEN)));
    }

    @Test
    @Transactional
    void getDmHuyenTmp() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        // Get the dmHuyenTmp
        restDmHuyenTmpMockMvc
            .perform(get(ENTITY_API_URL_ID, dmHuyenTmp.getMaHuyen()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.maHuyen").value(dmHuyenTmp.getMaHuyen().intValue()))
            .andExpect(jsonPath("$.tenHuyen").value(DEFAULT_TEN_HUYEN));
    }

    @Test
    @Transactional
    void getDmHuyenTmpsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        Long id = dmHuyenTmp.getMaHuyen();

        defaultDmHuyenTmpFiltering("maHuyen.equals=" + id, "maHuyen.notEquals=" + id);

        defaultDmHuyenTmpFiltering("maHuyen.greaterThanOrEqual=" + id, "maHuyen.greaterThan=" + id);

        defaultDmHuyenTmpFiltering("maHuyen.lessThanOrEqual=" + id, "maHuyen.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDmHuyenTmpsByTenHuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        // Get all the dmHuyenTmpList where tenHuyen equals to
        defaultDmHuyenTmpFiltering("tenHuyen.equals=" + DEFAULT_TEN_HUYEN, "tenHuyen.equals=" + UPDATED_TEN_HUYEN);
    }

    @Test
    @Transactional
    void getAllDmHuyenTmpsByTenHuyenIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        // Get all the dmHuyenTmpList where tenHuyen in
        defaultDmHuyenTmpFiltering("tenHuyen.in=" + DEFAULT_TEN_HUYEN + "," + UPDATED_TEN_HUYEN, "tenHuyen.in=" + UPDATED_TEN_HUYEN);
    }

    @Test
    @Transactional
    void getAllDmHuyenTmpsByTenHuyenIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        // Get all the dmHuyenTmpList where tenHuyen is not null
        defaultDmHuyenTmpFiltering("tenHuyen.specified=true", "tenHuyen.specified=false");
    }

    @Test
    @Transactional
    void getAllDmHuyenTmpsByTenHuyenContainsSomething() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        // Get all the dmHuyenTmpList where tenHuyen contains
        defaultDmHuyenTmpFiltering("tenHuyen.contains=" + DEFAULT_TEN_HUYEN, "tenHuyen.contains=" + UPDATED_TEN_HUYEN);
    }

    @Test
    @Transactional
    void getAllDmHuyenTmpsByTenHuyenNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        // Get all the dmHuyenTmpList where tenHuyen does not contain
        defaultDmHuyenTmpFiltering("tenHuyen.doesNotContain=" + UPDATED_TEN_HUYEN, "tenHuyen.doesNotContain=" + DEFAULT_TEN_HUYEN);
    }

    private void defaultDmHuyenTmpFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDmHuyenTmpShouldBeFound(shouldBeFound);
        defaultDmHuyenTmpShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmHuyenTmpShouldBeFound(String filter) throws Exception {
        restDmHuyenTmpMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maHuyen,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maHuyen").value(hasItem(dmHuyenTmp.getMaHuyen().intValue())))
            .andExpect(jsonPath("$.[*].tenHuyen").value(hasItem(DEFAULT_TEN_HUYEN)));

        // Check, that the count call also returns 1
        restDmHuyenTmpMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=maHuyen,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmHuyenTmpShouldNotBeFound(String filter) throws Exception {
        restDmHuyenTmpMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maHuyen,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmHuyenTmpMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=maHuyen,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDmHuyenTmp() throws Exception {
        // Get the dmHuyenTmp
        restDmHuyenTmpMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDmHuyenTmp() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmHuyenTmp
        DmHuyenTmp updatedDmHuyenTmp = dmHuyenTmpRepository.findById(dmHuyenTmp.getMaHuyen()).orElseThrow();
        // Disconnect from session so that the updates on updatedDmHuyenTmp are not directly saved in db
        em.detach(updatedDmHuyenTmp);
        updatedDmHuyenTmp.tenHuyen(UPDATED_TEN_HUYEN);
        DmHuyenTmpDTO dmHuyenTmpDTO = dmHuyenTmpMapper.toDto(updatedDmHuyenTmp);

        restDmHuyenTmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmHuyenTmpDTO.getMaHuyen())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmHuyenTmpDTO))
            )
            .andExpect(status().isOk());

        // Validate the DmHuyenTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDmHuyenTmpToMatchAllProperties(updatedDmHuyenTmp);
    }

    @Test
    @Transactional
    void putNonExistingDmHuyenTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmHuyenTmp.setMaHuyen(longCount.incrementAndGet());

        // Create the DmHuyenTmp
        DmHuyenTmpDTO dmHuyenTmpDTO = dmHuyenTmpMapper.toDto(dmHuyenTmp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmHuyenTmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmHuyenTmpDTO.getMaHuyen())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmHuyenTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmHuyenTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDmHuyenTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmHuyenTmp.setMaHuyen(longCount.incrementAndGet());

        // Create the DmHuyenTmp
        DmHuyenTmpDTO dmHuyenTmpDTO = dmHuyenTmpMapper.toDto(dmHuyenTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmHuyenTmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmHuyenTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmHuyenTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDmHuyenTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmHuyenTmp.setMaHuyen(longCount.incrementAndGet());

        // Create the DmHuyenTmp
        DmHuyenTmpDTO dmHuyenTmpDTO = dmHuyenTmpMapper.toDto(dmHuyenTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmHuyenTmpMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmHuyenTmpDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmHuyenTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDmHuyenTmpWithPatch() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmHuyenTmp using partial update
        DmHuyenTmp partialUpdatedDmHuyenTmp = new DmHuyenTmp();
        partialUpdatedDmHuyenTmp.setMaHuyen(dmHuyenTmp.getMaHuyen());

        restDmHuyenTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmHuyenTmp.getMaHuyen())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmHuyenTmp))
            )
            .andExpect(status().isOk());

        // Validate the DmHuyenTmp in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmHuyenTmpUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDmHuyenTmp, dmHuyenTmp),
            getPersistedDmHuyenTmp(dmHuyenTmp)
        );
    }

    @Test
    @Transactional
    void fullUpdateDmHuyenTmpWithPatch() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmHuyenTmp using partial update
        DmHuyenTmp partialUpdatedDmHuyenTmp = new DmHuyenTmp();
        partialUpdatedDmHuyenTmp.setMaHuyen(dmHuyenTmp.getMaHuyen());

        partialUpdatedDmHuyenTmp.tenHuyen(UPDATED_TEN_HUYEN);

        restDmHuyenTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmHuyenTmp.getMaHuyen())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmHuyenTmp))
            )
            .andExpect(status().isOk());

        // Validate the DmHuyenTmp in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmHuyenTmpUpdatableFieldsEquals(partialUpdatedDmHuyenTmp, getPersistedDmHuyenTmp(partialUpdatedDmHuyenTmp));
    }

    @Test
    @Transactional
    void patchNonExistingDmHuyenTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmHuyenTmp.setMaHuyen(longCount.incrementAndGet());

        // Create the DmHuyenTmp
        DmHuyenTmpDTO dmHuyenTmpDTO = dmHuyenTmpMapper.toDto(dmHuyenTmp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmHuyenTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dmHuyenTmpDTO.getMaHuyen())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmHuyenTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmHuyenTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDmHuyenTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmHuyenTmp.setMaHuyen(longCount.incrementAndGet());

        // Create the DmHuyenTmp
        DmHuyenTmpDTO dmHuyenTmpDTO = dmHuyenTmpMapper.toDto(dmHuyenTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmHuyenTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmHuyenTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmHuyenTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDmHuyenTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmHuyenTmp.setMaHuyen(longCount.incrementAndGet());

        // Create the DmHuyenTmp
        DmHuyenTmpDTO dmHuyenTmpDTO = dmHuyenTmpMapper.toDto(dmHuyenTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmHuyenTmpMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dmHuyenTmpDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmHuyenTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDmHuyenTmp() throws Exception {
        // Initialize the database
        insertedDmHuyenTmp = dmHuyenTmpRepository.saveAndFlush(dmHuyenTmp);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dmHuyenTmp
        restDmHuyenTmpMockMvc
            .perform(delete(ENTITY_API_URL_ID, dmHuyenTmp.getMaHuyen()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dmHuyenTmpRepository.count();
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

    protected DmHuyenTmp getPersistedDmHuyenTmp(DmHuyenTmp dmHuyenTmp) {
        return dmHuyenTmpRepository.findById(dmHuyenTmp.getMaHuyen()).orElseThrow();
    }

    protected void assertPersistedDmHuyenTmpToMatchAllProperties(DmHuyenTmp expectedDmHuyenTmp) {
        assertDmHuyenTmpAllPropertiesEquals(expectedDmHuyenTmp, getPersistedDmHuyenTmp(expectedDmHuyenTmp));
    }

    protected void assertPersistedDmHuyenTmpToMatchUpdatableProperties(DmHuyenTmp expectedDmHuyenTmp) {
        assertDmHuyenTmpAllUpdatablePropertiesEquals(expectedDmHuyenTmp, getPersistedDmHuyenTmp(expectedDmHuyenTmp));
    }
}
