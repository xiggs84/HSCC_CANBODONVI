package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucCapQuanLyAsserts.*;
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
import vn.vnpt.domain.DanhMucCapQuanLy;
import vn.vnpt.repository.DanhMucCapQuanLyRepository;
import vn.vnpt.service.dto.DanhMucCapQuanLyDTO;
import vn.vnpt.service.mapper.DanhMucCapQuanLyMapper;

/**
 * Integration tests for the {@link DanhMucCapQuanLyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucCapQuanLyResourceIT {

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-cap-quan-lies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCapQl}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucCapQuanLyRepository danhMucCapQuanLyRepository;

    @Autowired
    private DanhMucCapQuanLyMapper danhMucCapQuanLyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucCapQuanLyMockMvc;

    private DanhMucCapQuanLy danhMucCapQuanLy;

    private DanhMucCapQuanLy insertedDanhMucCapQuanLy;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucCapQuanLy createEntity() {
        return new DanhMucCapQuanLy().dienGiai(DEFAULT_DIEN_GIAI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucCapQuanLy createUpdatedEntity() {
        return new DanhMucCapQuanLy().dienGiai(UPDATED_DIEN_GIAI);
    }

    @BeforeEach
    public void initTest() {
        danhMucCapQuanLy = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucCapQuanLy != null) {
            danhMucCapQuanLyRepository.delete(insertedDanhMucCapQuanLy);
            insertedDanhMucCapQuanLy = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucCapQuanLy() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucCapQuanLy
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO = danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);
        var returnedDanhMucCapQuanLyDTO = om.readValue(
            restDanhMucCapQuanLyMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucCapQuanLyDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucCapQuanLyDTO.class
        );

        // Validate the DanhMucCapQuanLy in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucCapQuanLy = danhMucCapQuanLyMapper.toEntity(returnedDanhMucCapQuanLyDTO);
        assertDanhMucCapQuanLyUpdatableFieldsEquals(returnedDanhMucCapQuanLy, getPersistedDanhMucCapQuanLy(returnedDanhMucCapQuanLy));

        insertedDanhMucCapQuanLy = returnedDanhMucCapQuanLy;
    }

    @Test
    @Transactional
    void createDanhMucCapQuanLyWithExistingId() throws Exception {
        // Create the DanhMucCapQuanLy with an existing ID
        danhMucCapQuanLy.setIdCapQl(1L);
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO = danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucCapQuanLyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucCapQuanLyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucCapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucCapQuanLies() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        // Get all the danhMucCapQuanLyList
        restDanhMucCapQuanLyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCapQl,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCapQl").value(hasItem(danhMucCapQuanLy.getIdCapQl().intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)));
    }

    @Test
    @Transactional
    void getDanhMucCapQuanLy() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        // Get the danhMucCapQuanLy
        restDanhMucCapQuanLyMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucCapQuanLy.getIdCapQl()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCapQl").value(danhMucCapQuanLy.getIdCapQl().intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI));
    }

    @Test
    @Transactional
    void getDanhMucCapQuanLiesByIdFiltering() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        Long id = danhMucCapQuanLy.getIdCapQl();

        defaultDanhMucCapQuanLyFiltering("idCapQl.equals=" + id, "idCapQl.notEquals=" + id);

        defaultDanhMucCapQuanLyFiltering("idCapQl.greaterThanOrEqual=" + id, "idCapQl.greaterThan=" + id);

        defaultDanhMucCapQuanLyFiltering("idCapQl.lessThanOrEqual=" + id, "idCapQl.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDanhMucCapQuanLiesByDienGiaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        // Get all the danhMucCapQuanLyList where dienGiai equals to
        defaultDanhMucCapQuanLyFiltering("dienGiai.equals=" + DEFAULT_DIEN_GIAI, "dienGiai.equals=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllDanhMucCapQuanLiesByDienGiaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        // Get all the danhMucCapQuanLyList where dienGiai in
        defaultDanhMucCapQuanLyFiltering("dienGiai.in=" + DEFAULT_DIEN_GIAI + "," + UPDATED_DIEN_GIAI, "dienGiai.in=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllDanhMucCapQuanLiesByDienGiaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        // Get all the danhMucCapQuanLyList where dienGiai is not null
        defaultDanhMucCapQuanLyFiltering("dienGiai.specified=true", "dienGiai.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCapQuanLiesByDienGiaiContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        // Get all the danhMucCapQuanLyList where dienGiai contains
        defaultDanhMucCapQuanLyFiltering("dienGiai.contains=" + DEFAULT_DIEN_GIAI, "dienGiai.contains=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllDanhMucCapQuanLiesByDienGiaiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        // Get all the danhMucCapQuanLyList where dienGiai does not contain
        defaultDanhMucCapQuanLyFiltering("dienGiai.doesNotContain=" + UPDATED_DIEN_GIAI, "dienGiai.doesNotContain=" + DEFAULT_DIEN_GIAI);
    }

    private void defaultDanhMucCapQuanLyFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDanhMucCapQuanLyShouldBeFound(shouldBeFound);
        defaultDanhMucCapQuanLyShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhMucCapQuanLyShouldBeFound(String filter) throws Exception {
        restDanhMucCapQuanLyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCapQl,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCapQl").value(hasItem(danhMucCapQuanLy.getIdCapQl().intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)));

        // Check, that the count call also returns 1
        restDanhMucCapQuanLyMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idCapQl,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhMucCapQuanLyShouldNotBeFound(String filter) throws Exception {
        restDanhMucCapQuanLyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCapQl,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhMucCapQuanLyMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idCapQl,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucCapQuanLy() throws Exception {
        // Get the danhMucCapQuanLy
        restDanhMucCapQuanLyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucCapQuanLy() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucCapQuanLy
        DanhMucCapQuanLy updatedDanhMucCapQuanLy = danhMucCapQuanLyRepository.findById(danhMucCapQuanLy.getIdCapQl()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucCapQuanLy are not directly saved in db
        em.detach(updatedDanhMucCapQuanLy);
        updatedDanhMucCapQuanLy.dienGiai(UPDATED_DIEN_GIAI);
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO = danhMucCapQuanLyMapper.toDto(updatedDanhMucCapQuanLy);

        restDanhMucCapQuanLyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucCapQuanLyDTO.getIdCapQl())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucCapQuanLyDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucCapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucCapQuanLyToMatchAllProperties(updatedDanhMucCapQuanLy);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCapQuanLy.setIdCapQl(longCount.incrementAndGet());

        // Create the DanhMucCapQuanLy
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO = danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucCapQuanLyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucCapQuanLyDTO.getIdCapQl())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucCapQuanLyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucCapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCapQuanLy.setIdCapQl(longCount.incrementAndGet());

        // Create the DanhMucCapQuanLy
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO = danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucCapQuanLyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucCapQuanLyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucCapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCapQuanLy.setIdCapQl(longCount.incrementAndGet());

        // Create the DanhMucCapQuanLy
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO = danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucCapQuanLyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucCapQuanLyDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucCapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucCapQuanLyWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucCapQuanLy using partial update
        DanhMucCapQuanLy partialUpdatedDanhMucCapQuanLy = new DanhMucCapQuanLy();
        partialUpdatedDanhMucCapQuanLy.setIdCapQl(danhMucCapQuanLy.getIdCapQl());

        restDanhMucCapQuanLyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucCapQuanLy.getIdCapQl())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucCapQuanLy))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucCapQuanLy in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucCapQuanLyUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucCapQuanLy, danhMucCapQuanLy),
            getPersistedDanhMucCapQuanLy(danhMucCapQuanLy)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucCapQuanLyWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucCapQuanLy using partial update
        DanhMucCapQuanLy partialUpdatedDanhMucCapQuanLy = new DanhMucCapQuanLy();
        partialUpdatedDanhMucCapQuanLy.setIdCapQl(danhMucCapQuanLy.getIdCapQl());

        partialUpdatedDanhMucCapQuanLy.dienGiai(UPDATED_DIEN_GIAI);

        restDanhMucCapQuanLyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucCapQuanLy.getIdCapQl())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucCapQuanLy))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucCapQuanLy in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucCapQuanLyUpdatableFieldsEquals(
            partialUpdatedDanhMucCapQuanLy,
            getPersistedDanhMucCapQuanLy(partialUpdatedDanhMucCapQuanLy)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCapQuanLy.setIdCapQl(longCount.incrementAndGet());

        // Create the DanhMucCapQuanLy
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO = danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucCapQuanLyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucCapQuanLyDTO.getIdCapQl())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucCapQuanLyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucCapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCapQuanLy.setIdCapQl(longCount.incrementAndGet());

        // Create the DanhMucCapQuanLy
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO = danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucCapQuanLyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucCapQuanLyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucCapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCapQuanLy.setIdCapQl(longCount.incrementAndGet());

        // Create the DanhMucCapQuanLy
        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO = danhMucCapQuanLyMapper.toDto(danhMucCapQuanLy);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucCapQuanLyMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucCapQuanLyDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucCapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucCapQuanLy() throws Exception {
        // Initialize the database
        insertedDanhMucCapQuanLy = danhMucCapQuanLyRepository.saveAndFlush(danhMucCapQuanLy);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucCapQuanLy
        restDanhMucCapQuanLyMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucCapQuanLy.getIdCapQl()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucCapQuanLyRepository.count();
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

    protected DanhMucCapQuanLy getPersistedDanhMucCapQuanLy(DanhMucCapQuanLy danhMucCapQuanLy) {
        return danhMucCapQuanLyRepository.findById(danhMucCapQuanLy.getIdCapQl()).orElseThrow();
    }

    protected void assertPersistedDanhMucCapQuanLyToMatchAllProperties(DanhMucCapQuanLy expectedDanhMucCapQuanLy) {
        assertDanhMucCapQuanLyAllPropertiesEquals(expectedDanhMucCapQuanLy, getPersistedDanhMucCapQuanLy(expectedDanhMucCapQuanLy));
    }

    protected void assertPersistedDanhMucCapQuanLyToMatchUpdatableProperties(DanhMucCapQuanLy expectedDanhMucCapQuanLy) {
        assertDanhMucCapQuanLyAllUpdatablePropertiesEquals(
            expectedDanhMucCapQuanLy,
            getPersistedDanhMucCapQuanLy(expectedDanhMucCapQuanLy)
        );
    }
}
