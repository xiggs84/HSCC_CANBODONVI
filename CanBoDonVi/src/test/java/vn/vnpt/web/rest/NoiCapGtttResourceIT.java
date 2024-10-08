package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.NoiCapGtttAsserts.*;
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
import vn.vnpt.domain.NoiCapGttt;
import vn.vnpt.repository.NoiCapGtttRepository;
import vn.vnpt.service.dto.NoiCapGtttDTO;
import vn.vnpt.service.mapper.NoiCapGtttMapper;

/**
 * Integration tests for the {@link NoiCapGtttResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NoiCapGtttResourceIT {

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;
    private static final Long SMALLER_TRANG_THAI = 1L - 1L;

    private static final String ENTITY_API_URL = "/api/noi-cap-gttts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idNoiCap}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private NoiCapGtttRepository noiCapGtttRepository;

    @Autowired
    private NoiCapGtttMapper noiCapGtttMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNoiCapGtttMockMvc;

    private NoiCapGttt noiCapGttt;

    private NoiCapGttt insertedNoiCapGttt;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoiCapGttt createEntity() {
        return new NoiCapGttt().dienGiai(DEFAULT_DIEN_GIAI).trangThai(DEFAULT_TRANG_THAI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoiCapGttt createUpdatedEntity() {
        return new NoiCapGttt().dienGiai(UPDATED_DIEN_GIAI).trangThai(UPDATED_TRANG_THAI);
    }

    @BeforeEach
    public void initTest() {
        noiCapGttt = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedNoiCapGttt != null) {
            noiCapGtttRepository.delete(insertedNoiCapGttt);
            insertedNoiCapGttt = null;
        }
    }

    @Test
    @Transactional
    void createNoiCapGttt() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the NoiCapGttt
        NoiCapGtttDTO noiCapGtttDTO = noiCapGtttMapper.toDto(noiCapGttt);
        var returnedNoiCapGtttDTO = om.readValue(
            restNoiCapGtttMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(noiCapGtttDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            NoiCapGtttDTO.class
        );

        // Validate the NoiCapGttt in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedNoiCapGttt = noiCapGtttMapper.toEntity(returnedNoiCapGtttDTO);
        assertNoiCapGtttUpdatableFieldsEquals(returnedNoiCapGttt, getPersistedNoiCapGttt(returnedNoiCapGttt));

        insertedNoiCapGttt = returnedNoiCapGttt;
    }

    @Test
    @Transactional
    void createNoiCapGtttWithExistingId() throws Exception {
        // Create the NoiCapGttt with an existing ID
        noiCapGttt.setIdNoiCap(1L);
        NoiCapGtttDTO noiCapGtttDTO = noiCapGtttMapper.toDto(noiCapGttt);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoiCapGtttMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(noiCapGtttDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NoiCapGttt in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNoiCapGttts() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList
        restNoiCapGtttMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idNoiCap,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idNoiCap").value(hasItem(noiCapGttt.getIdNoiCap().intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));
    }

    @Test
    @Transactional
    void getNoiCapGttt() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get the noiCapGttt
        restNoiCapGtttMockMvc
            .perform(get(ENTITY_API_URL_ID, noiCapGttt.getIdNoiCap()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idNoiCap").value(noiCapGttt.getIdNoiCap().intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()));
    }

    @Test
    @Transactional
    void getNoiCapGtttsByIdFiltering() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        Long id = noiCapGttt.getIdNoiCap();

        defaultNoiCapGtttFiltering("idNoiCap.equals=" + id, "idNoiCap.notEquals=" + id);

        defaultNoiCapGtttFiltering("idNoiCap.greaterThanOrEqual=" + id, "idNoiCap.greaterThan=" + id);

        defaultNoiCapGtttFiltering("idNoiCap.lessThanOrEqual=" + id, "idNoiCap.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByDienGiaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where dienGiai equals to
        defaultNoiCapGtttFiltering("dienGiai.equals=" + DEFAULT_DIEN_GIAI, "dienGiai.equals=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByDienGiaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where dienGiai in
        defaultNoiCapGtttFiltering("dienGiai.in=" + DEFAULT_DIEN_GIAI + "," + UPDATED_DIEN_GIAI, "dienGiai.in=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByDienGiaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where dienGiai is not null
        defaultNoiCapGtttFiltering("dienGiai.specified=true", "dienGiai.specified=false");
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByDienGiaiContainsSomething() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where dienGiai contains
        defaultNoiCapGtttFiltering("dienGiai.contains=" + DEFAULT_DIEN_GIAI, "dienGiai.contains=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByDienGiaiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where dienGiai does not contain
        defaultNoiCapGtttFiltering("dienGiai.doesNotContain=" + UPDATED_DIEN_GIAI, "dienGiai.doesNotContain=" + DEFAULT_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where trangThai equals to
        defaultNoiCapGtttFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where trangThai in
        defaultNoiCapGtttFiltering("trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI, "trangThai.in=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where trangThai is not null
        defaultNoiCapGtttFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where trangThai is greater than or equal to
        defaultNoiCapGtttFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where trangThai is less than or equal to
        defaultNoiCapGtttFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where trangThai is less than
        defaultNoiCapGtttFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllNoiCapGtttsByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        // Get all the noiCapGtttList where trangThai is greater than
        defaultNoiCapGtttFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    private void defaultNoiCapGtttFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultNoiCapGtttShouldBeFound(shouldBeFound);
        defaultNoiCapGtttShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultNoiCapGtttShouldBeFound(String filter) throws Exception {
        restNoiCapGtttMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idNoiCap,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idNoiCap").value(hasItem(noiCapGttt.getIdNoiCap().intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));

        // Check, that the count call also returns 1
        restNoiCapGtttMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idNoiCap,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultNoiCapGtttShouldNotBeFound(String filter) throws Exception {
        restNoiCapGtttMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idNoiCap,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restNoiCapGtttMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idNoiCap,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingNoiCapGttt() throws Exception {
        // Get the noiCapGttt
        restNoiCapGtttMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNoiCapGttt() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the noiCapGttt
        NoiCapGttt updatedNoiCapGttt = noiCapGtttRepository.findById(noiCapGttt.getIdNoiCap()).orElseThrow();
        // Disconnect from session so that the updates on updatedNoiCapGttt are not directly saved in db
        em.detach(updatedNoiCapGttt);
        updatedNoiCapGttt.dienGiai(UPDATED_DIEN_GIAI).trangThai(UPDATED_TRANG_THAI);
        NoiCapGtttDTO noiCapGtttDTO = noiCapGtttMapper.toDto(updatedNoiCapGttt);

        restNoiCapGtttMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noiCapGtttDTO.getIdNoiCap())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(noiCapGtttDTO))
            )
            .andExpect(status().isOk());

        // Validate the NoiCapGttt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedNoiCapGtttToMatchAllProperties(updatedNoiCapGttt);
    }

    @Test
    @Transactional
    void putNonExistingNoiCapGttt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        noiCapGttt.setIdNoiCap(longCount.incrementAndGet());

        // Create the NoiCapGttt
        NoiCapGtttDTO noiCapGtttDTO = noiCapGtttMapper.toDto(noiCapGttt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoiCapGtttMockMvc
            .perform(
                put(ENTITY_API_URL_ID, noiCapGtttDTO.getIdNoiCap())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(noiCapGtttDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiCapGttt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNoiCapGttt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        noiCapGttt.setIdNoiCap(longCount.incrementAndGet());

        // Create the NoiCapGttt
        NoiCapGtttDTO noiCapGtttDTO = noiCapGtttMapper.toDto(noiCapGttt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiCapGtttMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(noiCapGtttDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiCapGttt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNoiCapGttt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        noiCapGttt.setIdNoiCap(longCount.incrementAndGet());

        // Create the NoiCapGttt
        NoiCapGtttDTO noiCapGtttDTO = noiCapGtttMapper.toDto(noiCapGttt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiCapGtttMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(noiCapGtttDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoiCapGttt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNoiCapGtttWithPatch() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the noiCapGttt using partial update
        NoiCapGttt partialUpdatedNoiCapGttt = new NoiCapGttt();
        partialUpdatedNoiCapGttt.setIdNoiCap(noiCapGttt.getIdNoiCap());

        partialUpdatedNoiCapGttt.dienGiai(UPDATED_DIEN_GIAI);

        restNoiCapGtttMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoiCapGttt.getIdNoiCap())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNoiCapGttt))
            )
            .andExpect(status().isOk());

        // Validate the NoiCapGttt in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNoiCapGtttUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedNoiCapGttt, noiCapGttt),
            getPersistedNoiCapGttt(noiCapGttt)
        );
    }

    @Test
    @Transactional
    void fullUpdateNoiCapGtttWithPatch() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the noiCapGttt using partial update
        NoiCapGttt partialUpdatedNoiCapGttt = new NoiCapGttt();
        partialUpdatedNoiCapGttt.setIdNoiCap(noiCapGttt.getIdNoiCap());

        partialUpdatedNoiCapGttt.dienGiai(UPDATED_DIEN_GIAI).trangThai(UPDATED_TRANG_THAI);

        restNoiCapGtttMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNoiCapGttt.getIdNoiCap())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNoiCapGttt))
            )
            .andExpect(status().isOk());

        // Validate the NoiCapGttt in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNoiCapGtttUpdatableFieldsEquals(partialUpdatedNoiCapGttt, getPersistedNoiCapGttt(partialUpdatedNoiCapGttt));
    }

    @Test
    @Transactional
    void patchNonExistingNoiCapGttt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        noiCapGttt.setIdNoiCap(longCount.incrementAndGet());

        // Create the NoiCapGttt
        NoiCapGtttDTO noiCapGtttDTO = noiCapGtttMapper.toDto(noiCapGttt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNoiCapGtttMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, noiCapGtttDTO.getIdNoiCap())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(noiCapGtttDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiCapGttt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNoiCapGttt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        noiCapGttt.setIdNoiCap(longCount.incrementAndGet());

        // Create the NoiCapGttt
        NoiCapGtttDTO noiCapGtttDTO = noiCapGtttMapper.toDto(noiCapGttt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiCapGtttMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(noiCapGtttDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NoiCapGttt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNoiCapGttt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        noiCapGttt.setIdNoiCap(longCount.incrementAndGet());

        // Create the NoiCapGttt
        NoiCapGtttDTO noiCapGtttDTO = noiCapGtttMapper.toDto(noiCapGttt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNoiCapGtttMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(noiCapGtttDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NoiCapGttt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNoiCapGttt() throws Exception {
        // Initialize the database
        insertedNoiCapGttt = noiCapGtttRepository.saveAndFlush(noiCapGttt);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the noiCapGttt
        restNoiCapGtttMockMvc
            .perform(delete(ENTITY_API_URL_ID, noiCapGttt.getIdNoiCap()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return noiCapGtttRepository.count();
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

    protected NoiCapGttt getPersistedNoiCapGttt(NoiCapGttt noiCapGttt) {
        return noiCapGtttRepository.findById(noiCapGttt.getIdNoiCap()).orElseThrow();
    }

    protected void assertPersistedNoiCapGtttToMatchAllProperties(NoiCapGttt expectedNoiCapGttt) {
        assertNoiCapGtttAllPropertiesEquals(expectedNoiCapGttt, getPersistedNoiCapGttt(expectedNoiCapGttt));
    }

    protected void assertPersistedNoiCapGtttToMatchUpdatableProperties(NoiCapGttt expectedNoiCapGttt) {
        assertNoiCapGtttAllUpdatablePropertiesEquals(expectedNoiCapGttt, getPersistedNoiCapGttt(expectedNoiCapGttt));
    }
}
