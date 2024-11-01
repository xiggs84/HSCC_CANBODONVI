package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.QuanHeNhanThanAsserts.*;
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
import vn.vnpt.domain.QuanHeNhanThan;
import vn.vnpt.repository.QuanHeNhanThanRepository;
import vn.vnpt.service.dto.QuanHeNhanThanDTO;
import vn.vnpt.service.mapper.QuanHeNhanThanMapper;

/**
 * Integration tests for the {@link QuanHeNhanThanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuanHeNhanThanResourceIT {

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_QUAN_HE_DOI_UNG = 1L;
    private static final Long UPDATED_ID_QUAN_HE_DOI_UNG = 2L;
    private static final Long SMALLER_ID_QUAN_HE_DOI_UNG = 1L - 1L;

    private static final String ENTITY_API_URL = "/api/quan-he-nhan-thans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idQuanHe}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QuanHeNhanThanRepository quanHeNhanThanRepository;

    @Autowired
    private QuanHeNhanThanMapper quanHeNhanThanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuanHeNhanThanMockMvc;

    private QuanHeNhanThan quanHeNhanThan;

    private QuanHeNhanThan insertedQuanHeNhanThan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuanHeNhanThan createEntity() {
        return new QuanHeNhanThan().dienGiai(DEFAULT_DIEN_GIAI).idQuanHeDoiUng(DEFAULT_ID_QUAN_HE_DOI_UNG);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuanHeNhanThan createUpdatedEntity() {
        return new QuanHeNhanThan().dienGiai(UPDATED_DIEN_GIAI).idQuanHeDoiUng(UPDATED_ID_QUAN_HE_DOI_UNG);
    }

    @BeforeEach
    public void initTest() {
        quanHeNhanThan = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedQuanHeNhanThan != null) {
            quanHeNhanThanRepository.delete(insertedQuanHeNhanThan);
            insertedQuanHeNhanThan = null;
        }
    }

    @Test
    @Transactional
    void createQuanHeNhanThan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the QuanHeNhanThan
        QuanHeNhanThanDTO quanHeNhanThanDTO = quanHeNhanThanMapper.toDto(quanHeNhanThan);
        var returnedQuanHeNhanThanDTO = om.readValue(
            restQuanHeNhanThanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeNhanThanDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QuanHeNhanThanDTO.class
        );

        // Validate the QuanHeNhanThan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedQuanHeNhanThan = quanHeNhanThanMapper.toEntity(returnedQuanHeNhanThanDTO);
        assertQuanHeNhanThanUpdatableFieldsEquals(returnedQuanHeNhanThan, getPersistedQuanHeNhanThan(returnedQuanHeNhanThan));

        insertedQuanHeNhanThan = returnedQuanHeNhanThan;
    }

    @Test
    @Transactional
    void createQuanHeNhanThanWithExistingId() throws Exception {
        // Create the QuanHeNhanThan with an existing ID
        quanHeNhanThan.setIdQuanHe(1L);
        QuanHeNhanThanDTO quanHeNhanThanDTO = quanHeNhanThanMapper.toDto(quanHeNhanThan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuanHeNhanThanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeNhanThanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuanHeNhanThan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThans() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList
        restQuanHeNhanThanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idQuanHe,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idQuanHe").value(hasItem(quanHeNhanThan.getIdQuanHe().intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].idQuanHeDoiUng").value(hasItem(DEFAULT_ID_QUAN_HE_DOI_UNG.intValue())));
    }

    @Test
    @Transactional
    void getQuanHeNhanThan() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get the quanHeNhanThan
        restQuanHeNhanThanMockMvc
            .perform(get(ENTITY_API_URL_ID, quanHeNhanThan.getIdQuanHe()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idQuanHe").value(quanHeNhanThan.getIdQuanHe().intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.idQuanHeDoiUng").value(DEFAULT_ID_QUAN_HE_DOI_UNG.intValue()));
    }

    @Test
    @Transactional
    void getQuanHeNhanThansByIdFiltering() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        Long id = quanHeNhanThan.getIdQuanHe();

        defaultQuanHeNhanThanFiltering("idQuanHe.equals=" + id, "idQuanHe.notEquals=" + id);

        defaultQuanHeNhanThanFiltering("idQuanHe.greaterThanOrEqual=" + id, "idQuanHe.greaterThan=" + id);

        defaultQuanHeNhanThanFiltering("idQuanHe.lessThanOrEqual=" + id, "idQuanHe.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByDienGiaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where dienGiai equals to
        defaultQuanHeNhanThanFiltering("dienGiai.equals=" + DEFAULT_DIEN_GIAI, "dienGiai.equals=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByDienGiaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where dienGiai in
        defaultQuanHeNhanThanFiltering("dienGiai.in=" + DEFAULT_DIEN_GIAI + "," + UPDATED_DIEN_GIAI, "dienGiai.in=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByDienGiaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where dienGiai is not null
        defaultQuanHeNhanThanFiltering("dienGiai.specified=true", "dienGiai.specified=false");
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByDienGiaiContainsSomething() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where dienGiai contains
        defaultQuanHeNhanThanFiltering("dienGiai.contains=" + DEFAULT_DIEN_GIAI, "dienGiai.contains=" + UPDATED_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByDienGiaiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where dienGiai does not contain
        defaultQuanHeNhanThanFiltering("dienGiai.doesNotContain=" + UPDATED_DIEN_GIAI, "dienGiai.doesNotContain=" + DEFAULT_DIEN_GIAI);
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByIdQuanHeDoiUngIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where idQuanHeDoiUng equals to
        defaultQuanHeNhanThanFiltering(
            "idQuanHeDoiUng.equals=" + DEFAULT_ID_QUAN_HE_DOI_UNG,
            "idQuanHeDoiUng.equals=" + UPDATED_ID_QUAN_HE_DOI_UNG
        );
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByIdQuanHeDoiUngIsInShouldWork() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where idQuanHeDoiUng in
        defaultQuanHeNhanThanFiltering(
            "idQuanHeDoiUng.in=" + DEFAULT_ID_QUAN_HE_DOI_UNG + "," + UPDATED_ID_QUAN_HE_DOI_UNG,
            "idQuanHeDoiUng.in=" + UPDATED_ID_QUAN_HE_DOI_UNG
        );
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByIdQuanHeDoiUngIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where idQuanHeDoiUng is not null
        defaultQuanHeNhanThanFiltering("idQuanHeDoiUng.specified=true", "idQuanHeDoiUng.specified=false");
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByIdQuanHeDoiUngIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where idQuanHeDoiUng is greater than or equal to
        defaultQuanHeNhanThanFiltering(
            "idQuanHeDoiUng.greaterThanOrEqual=" + DEFAULT_ID_QUAN_HE_DOI_UNG,
            "idQuanHeDoiUng.greaterThanOrEqual=" + UPDATED_ID_QUAN_HE_DOI_UNG
        );
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByIdQuanHeDoiUngIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where idQuanHeDoiUng is less than or equal to
        defaultQuanHeNhanThanFiltering(
            "idQuanHeDoiUng.lessThanOrEqual=" + DEFAULT_ID_QUAN_HE_DOI_UNG,
            "idQuanHeDoiUng.lessThanOrEqual=" + SMALLER_ID_QUAN_HE_DOI_UNG
        );
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByIdQuanHeDoiUngIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where idQuanHeDoiUng is less than
        defaultQuanHeNhanThanFiltering(
            "idQuanHeDoiUng.lessThan=" + UPDATED_ID_QUAN_HE_DOI_UNG,
            "idQuanHeDoiUng.lessThan=" + DEFAULT_ID_QUAN_HE_DOI_UNG
        );
    }

    @Test
    @Transactional
    void getAllQuanHeNhanThansByIdQuanHeDoiUngIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        // Get all the quanHeNhanThanList where idQuanHeDoiUng is greater than
        defaultQuanHeNhanThanFiltering(
            "idQuanHeDoiUng.greaterThan=" + SMALLER_ID_QUAN_HE_DOI_UNG,
            "idQuanHeDoiUng.greaterThan=" + DEFAULT_ID_QUAN_HE_DOI_UNG
        );
    }

    private void defaultQuanHeNhanThanFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultQuanHeNhanThanShouldBeFound(shouldBeFound);
        defaultQuanHeNhanThanShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQuanHeNhanThanShouldBeFound(String filter) throws Exception {
        restQuanHeNhanThanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idQuanHe,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idQuanHe").value(hasItem(quanHeNhanThan.getIdQuanHe().intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].idQuanHeDoiUng").value(hasItem(DEFAULT_ID_QUAN_HE_DOI_UNG.intValue())));

        // Check, that the count call also returns 1
        restQuanHeNhanThanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idQuanHe,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQuanHeNhanThanShouldNotBeFound(String filter) throws Exception {
        restQuanHeNhanThanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idQuanHe,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQuanHeNhanThanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idQuanHe,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingQuanHeNhanThan() throws Exception {
        // Get the quanHeNhanThan
        restQuanHeNhanThanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuanHeNhanThan() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeNhanThan
        QuanHeNhanThan updatedQuanHeNhanThan = quanHeNhanThanRepository.findById(quanHeNhanThan.getIdQuanHe()).orElseThrow();
        // Disconnect from session so that the updates on updatedQuanHeNhanThan are not directly saved in db
        em.detach(updatedQuanHeNhanThan);
        updatedQuanHeNhanThan.dienGiai(UPDATED_DIEN_GIAI).idQuanHeDoiUng(UPDATED_ID_QUAN_HE_DOI_UNG);
        QuanHeNhanThanDTO quanHeNhanThanDTO = quanHeNhanThanMapper.toDto(updatedQuanHeNhanThan);

        restQuanHeNhanThanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quanHeNhanThanDTO.getIdQuanHe())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeNhanThanDTO))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeNhanThan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQuanHeNhanThanToMatchAllProperties(updatedQuanHeNhanThan);
    }

    @Test
    @Transactional
    void putNonExistingQuanHeNhanThan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeNhanThan.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeNhanThan
        QuanHeNhanThanDTO quanHeNhanThanDTO = quanHeNhanThanMapper.toDto(quanHeNhanThan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanHeNhanThanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quanHeNhanThanDTO.getIdQuanHe())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeNhanThanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeNhanThan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuanHeNhanThan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeNhanThan.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeNhanThan
        QuanHeNhanThanDTO quanHeNhanThanDTO = quanHeNhanThanMapper.toDto(quanHeNhanThan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeNhanThanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeNhanThanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeNhanThan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuanHeNhanThan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeNhanThan.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeNhanThan
        QuanHeNhanThanDTO quanHeNhanThanDTO = quanHeNhanThanMapper.toDto(quanHeNhanThan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeNhanThanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeNhanThanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuanHeNhanThan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuanHeNhanThanWithPatch() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeNhanThan using partial update
        QuanHeNhanThan partialUpdatedQuanHeNhanThan = new QuanHeNhanThan();
        partialUpdatedQuanHeNhanThan.setIdQuanHe(quanHeNhanThan.getIdQuanHe());

        partialUpdatedQuanHeNhanThan.idQuanHeDoiUng(UPDATED_ID_QUAN_HE_DOI_UNG);

        restQuanHeNhanThanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuanHeNhanThan.getIdQuanHe())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuanHeNhanThan))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeNhanThan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuanHeNhanThanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQuanHeNhanThan, quanHeNhanThan),
            getPersistedQuanHeNhanThan(quanHeNhanThan)
        );
    }

    @Test
    @Transactional
    void fullUpdateQuanHeNhanThanWithPatch() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeNhanThan using partial update
        QuanHeNhanThan partialUpdatedQuanHeNhanThan = new QuanHeNhanThan();
        partialUpdatedQuanHeNhanThan.setIdQuanHe(quanHeNhanThan.getIdQuanHe());

        partialUpdatedQuanHeNhanThan.dienGiai(UPDATED_DIEN_GIAI).idQuanHeDoiUng(UPDATED_ID_QUAN_HE_DOI_UNG);

        restQuanHeNhanThanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuanHeNhanThan.getIdQuanHe())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuanHeNhanThan))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeNhanThan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuanHeNhanThanUpdatableFieldsEquals(partialUpdatedQuanHeNhanThan, getPersistedQuanHeNhanThan(partialUpdatedQuanHeNhanThan));
    }

    @Test
    @Transactional
    void patchNonExistingQuanHeNhanThan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeNhanThan.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeNhanThan
        QuanHeNhanThanDTO quanHeNhanThanDTO = quanHeNhanThanMapper.toDto(quanHeNhanThan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanHeNhanThanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, quanHeNhanThanDTO.getIdQuanHe())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(quanHeNhanThanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeNhanThan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuanHeNhanThan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeNhanThan.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeNhanThan
        QuanHeNhanThanDTO quanHeNhanThanDTO = quanHeNhanThanMapper.toDto(quanHeNhanThan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeNhanThanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(quanHeNhanThanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeNhanThan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuanHeNhanThan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeNhanThan.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeNhanThan
        QuanHeNhanThanDTO quanHeNhanThanDTO = quanHeNhanThanMapper.toDto(quanHeNhanThan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeNhanThanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(quanHeNhanThanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuanHeNhanThan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuanHeNhanThan() throws Exception {
        // Initialize the database
        insertedQuanHeNhanThan = quanHeNhanThanRepository.saveAndFlush(quanHeNhanThan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the quanHeNhanThan
        restQuanHeNhanThanMockMvc
            .perform(delete(ENTITY_API_URL_ID, quanHeNhanThan.getIdQuanHe()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return quanHeNhanThanRepository.count();
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

    protected QuanHeNhanThan getPersistedQuanHeNhanThan(QuanHeNhanThan quanHeNhanThan) {
        return quanHeNhanThanRepository.findById(quanHeNhanThan.getIdQuanHe()).orElseThrow();
    }

    protected void assertPersistedQuanHeNhanThanToMatchAllProperties(QuanHeNhanThan expectedQuanHeNhanThan) {
        assertQuanHeNhanThanAllPropertiesEquals(expectedQuanHeNhanThan, getPersistedQuanHeNhanThan(expectedQuanHeNhanThan));
    }

    protected void assertPersistedQuanHeNhanThanToMatchUpdatableProperties(QuanHeNhanThan expectedQuanHeNhanThan) {
        assertQuanHeNhanThanAllUpdatablePropertiesEquals(expectedQuanHeNhanThan, getPersistedQuanHeNhanThan(expectedQuanHeNhanThan));
    }
}
