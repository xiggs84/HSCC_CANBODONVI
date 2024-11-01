package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.QuanHeMasterAsserts.*;
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
import vn.vnpt.domain.QuanHeMaster;
import vn.vnpt.repository.QuanHeMasterRepository;
import vn.vnpt.service.dto.QuanHeMasterDTO;
import vn.vnpt.service.mapper.QuanHeMasterMapper;

/**
 * Integration tests for the {@link QuanHeMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuanHeMasterResourceIT {

    private static final Long DEFAULT_ID_DUONG_SU = 1L;
    private static final Long UPDATED_ID_DUONG_SU = 2L;
    private static final Long SMALLER_ID_DUONG_SU = 1L - 1L;

    private static final Long DEFAULT_ID_DUONG_SU_QH = 1L;
    private static final Long UPDATED_ID_DUONG_SU_QH = 2L;
    private static final Long SMALLER_ID_DUONG_SU_QH = 1L - 1L;

    private static final String ENTITY_API_URL = "/api/quan-he-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QuanHeMasterRepository quanHeMasterRepository;

    @Autowired
    private QuanHeMasterMapper quanHeMasterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuanHeMasterMockMvc;

    private QuanHeMaster quanHeMaster;

    private QuanHeMaster insertedQuanHeMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuanHeMaster createEntity() {
        return new QuanHeMaster().idDuongSu(DEFAULT_ID_DUONG_SU).idDuongSuQh(DEFAULT_ID_DUONG_SU_QH);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuanHeMaster createUpdatedEntity() {
        return new QuanHeMaster().idDuongSu(UPDATED_ID_DUONG_SU).idDuongSuQh(UPDATED_ID_DUONG_SU_QH);
    }

    @BeforeEach
    public void initTest() {
        quanHeMaster = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedQuanHeMaster != null) {
            quanHeMasterRepository.delete(insertedQuanHeMaster);
            insertedQuanHeMaster = null;
        }
    }

    @Test
    @Transactional
    void createQuanHeMaster() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the QuanHeMaster
        QuanHeMasterDTO quanHeMasterDTO = quanHeMasterMapper.toDto(quanHeMaster);
        var returnedQuanHeMasterDTO = om.readValue(
            restQuanHeMasterMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeMasterDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QuanHeMasterDTO.class
        );

        // Validate the QuanHeMaster in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedQuanHeMaster = quanHeMasterMapper.toEntity(returnedQuanHeMasterDTO);
        assertQuanHeMasterUpdatableFieldsEquals(returnedQuanHeMaster, getPersistedQuanHeMaster(returnedQuanHeMaster));

        insertedQuanHeMaster = returnedQuanHeMaster;
    }

    @Test
    @Transactional
    void createQuanHeMasterWithExistingId() throws Exception {
        // Create the QuanHeMaster with an existing ID
        quanHeMaster.setId(1L);
        QuanHeMasterDTO quanHeMasterDTO = quanHeMasterMapper.toDto(quanHeMaster);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuanHeMasterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuanHeMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuanHeMasters() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList
        restQuanHeMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quanHeMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(DEFAULT_ID_DUONG_SU.intValue())))
            .andExpect(jsonPath("$.[*].idDuongSuQh").value(hasItem(DEFAULT_ID_DUONG_SU_QH.intValue())));
    }

    @Test
    @Transactional
    void getQuanHeMaster() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get the quanHeMaster
        restQuanHeMasterMockMvc
            .perform(get(ENTITY_API_URL_ID, quanHeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(quanHeMaster.getId().intValue()))
            .andExpect(jsonPath("$.idDuongSu").value(DEFAULT_ID_DUONG_SU.intValue()))
            .andExpect(jsonPath("$.idDuongSuQh").value(DEFAULT_ID_DUONG_SU_QH.intValue()));
    }

    @Test
    @Transactional
    void getQuanHeMastersByIdFiltering() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        Long id = quanHeMaster.getId();

        defaultQuanHeMasterFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultQuanHeMasterFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultQuanHeMasterFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSu equals to
        defaultQuanHeMasterFiltering("idDuongSu.equals=" + DEFAULT_ID_DUONG_SU, "idDuongSu.equals=" + UPDATED_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSu in
        defaultQuanHeMasterFiltering(
            "idDuongSu.in=" + DEFAULT_ID_DUONG_SU + "," + UPDATED_ID_DUONG_SU,
            "idDuongSu.in=" + UPDATED_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSu is not null
        defaultQuanHeMasterFiltering("idDuongSu.specified=true", "idDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSu is greater than or equal to
        defaultQuanHeMasterFiltering(
            "idDuongSu.greaterThanOrEqual=" + DEFAULT_ID_DUONG_SU,
            "idDuongSu.greaterThanOrEqual=" + UPDATED_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSu is less than or equal to
        defaultQuanHeMasterFiltering(
            "idDuongSu.lessThanOrEqual=" + DEFAULT_ID_DUONG_SU,
            "idDuongSu.lessThanOrEqual=" + SMALLER_ID_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSu is less than
        defaultQuanHeMasterFiltering("idDuongSu.lessThan=" + UPDATED_ID_DUONG_SU, "idDuongSu.lessThan=" + DEFAULT_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSu is greater than
        defaultQuanHeMasterFiltering("idDuongSu.greaterThan=" + SMALLER_ID_DUONG_SU, "idDuongSu.greaterThan=" + DEFAULT_ID_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuQhIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSuQh equals to
        defaultQuanHeMasterFiltering("idDuongSuQh.equals=" + DEFAULT_ID_DUONG_SU_QH, "idDuongSuQh.equals=" + UPDATED_ID_DUONG_SU_QH);
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuQhIsInShouldWork() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSuQh in
        defaultQuanHeMasterFiltering(
            "idDuongSuQh.in=" + DEFAULT_ID_DUONG_SU_QH + "," + UPDATED_ID_DUONG_SU_QH,
            "idDuongSuQh.in=" + UPDATED_ID_DUONG_SU_QH
        );
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuQhIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSuQh is not null
        defaultQuanHeMasterFiltering("idDuongSuQh.specified=true", "idDuongSuQh.specified=false");
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuQhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSuQh is greater than or equal to
        defaultQuanHeMasterFiltering(
            "idDuongSuQh.greaterThanOrEqual=" + DEFAULT_ID_DUONG_SU_QH,
            "idDuongSuQh.greaterThanOrEqual=" + UPDATED_ID_DUONG_SU_QH
        );
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuQhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSuQh is less than or equal to
        defaultQuanHeMasterFiltering(
            "idDuongSuQh.lessThanOrEqual=" + DEFAULT_ID_DUONG_SU_QH,
            "idDuongSuQh.lessThanOrEqual=" + SMALLER_ID_DUONG_SU_QH
        );
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuQhIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSuQh is less than
        defaultQuanHeMasterFiltering("idDuongSuQh.lessThan=" + UPDATED_ID_DUONG_SU_QH, "idDuongSuQh.lessThan=" + DEFAULT_ID_DUONG_SU_QH);
    }

    @Test
    @Transactional
    void getAllQuanHeMastersByIdDuongSuQhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        // Get all the quanHeMasterList where idDuongSuQh is greater than
        defaultQuanHeMasterFiltering(
            "idDuongSuQh.greaterThan=" + SMALLER_ID_DUONG_SU_QH,
            "idDuongSuQh.greaterThan=" + DEFAULT_ID_DUONG_SU_QH
        );
    }

    private void defaultQuanHeMasterFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultQuanHeMasterShouldBeFound(shouldBeFound);
        defaultQuanHeMasterShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQuanHeMasterShouldBeFound(String filter) throws Exception {
        restQuanHeMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quanHeMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(DEFAULT_ID_DUONG_SU.intValue())))
            .andExpect(jsonPath("$.[*].idDuongSuQh").value(hasItem(DEFAULT_ID_DUONG_SU_QH.intValue())));

        // Check, that the count call also returns 1
        restQuanHeMasterMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQuanHeMasterShouldNotBeFound(String filter) throws Exception {
        restQuanHeMasterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQuanHeMasterMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingQuanHeMaster() throws Exception {
        // Get the quanHeMaster
        restQuanHeMasterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuanHeMaster() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeMaster
        QuanHeMaster updatedQuanHeMaster = quanHeMasterRepository.findById(quanHeMaster.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQuanHeMaster are not directly saved in db
        em.detach(updatedQuanHeMaster);
        updatedQuanHeMaster.idDuongSu(UPDATED_ID_DUONG_SU).idDuongSuQh(UPDATED_ID_DUONG_SU_QH);
        QuanHeMasterDTO quanHeMasterDTO = quanHeMasterMapper.toDto(updatedQuanHeMaster);

        restQuanHeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quanHeMasterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeMasterDTO))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQuanHeMasterToMatchAllProperties(updatedQuanHeMaster);
    }

    @Test
    @Transactional
    void putNonExistingQuanHeMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeMaster.setId(longCount.incrementAndGet());

        // Create the QuanHeMaster
        QuanHeMasterDTO quanHeMasterDTO = quanHeMasterMapper.toDto(quanHeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanHeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quanHeMasterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuanHeMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeMaster.setId(longCount.incrementAndGet());

        // Create the QuanHeMaster
        QuanHeMasterDTO quanHeMasterDTO = quanHeMasterMapper.toDto(quanHeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeMasterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuanHeMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeMaster.setId(longCount.incrementAndGet());

        // Create the QuanHeMaster
        QuanHeMasterDTO quanHeMasterDTO = quanHeMasterMapper.toDto(quanHeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeMasterMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeMasterDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuanHeMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuanHeMasterWithPatch() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeMaster using partial update
        QuanHeMaster partialUpdatedQuanHeMaster = new QuanHeMaster();
        partialUpdatedQuanHeMaster.setId(quanHeMaster.getId());

        partialUpdatedQuanHeMaster.idDuongSu(UPDATED_ID_DUONG_SU);

        restQuanHeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuanHeMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuanHeMaster))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeMaster in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuanHeMasterUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQuanHeMaster, quanHeMaster),
            getPersistedQuanHeMaster(quanHeMaster)
        );
    }

    @Test
    @Transactional
    void fullUpdateQuanHeMasterWithPatch() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeMaster using partial update
        QuanHeMaster partialUpdatedQuanHeMaster = new QuanHeMaster();
        partialUpdatedQuanHeMaster.setId(quanHeMaster.getId());

        partialUpdatedQuanHeMaster.idDuongSu(UPDATED_ID_DUONG_SU).idDuongSuQh(UPDATED_ID_DUONG_SU_QH);

        restQuanHeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuanHeMaster.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuanHeMaster))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeMaster in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuanHeMasterUpdatableFieldsEquals(partialUpdatedQuanHeMaster, getPersistedQuanHeMaster(partialUpdatedQuanHeMaster));
    }

    @Test
    @Transactional
    void patchNonExistingQuanHeMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeMaster.setId(longCount.incrementAndGet());

        // Create the QuanHeMaster
        QuanHeMasterDTO quanHeMasterDTO = quanHeMasterMapper.toDto(quanHeMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanHeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, quanHeMasterDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(quanHeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuanHeMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeMaster.setId(longCount.incrementAndGet());

        // Create the QuanHeMaster
        QuanHeMasterDTO quanHeMasterDTO = quanHeMasterMapper.toDto(quanHeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeMasterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(quanHeMasterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuanHeMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeMaster.setId(longCount.incrementAndGet());

        // Create the QuanHeMaster
        QuanHeMasterDTO quanHeMasterDTO = quanHeMasterMapper.toDto(quanHeMaster);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeMasterMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(quanHeMasterDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuanHeMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuanHeMaster() throws Exception {
        // Initialize the database
        insertedQuanHeMaster = quanHeMasterRepository.saveAndFlush(quanHeMaster);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the quanHeMaster
        restQuanHeMasterMockMvc
            .perform(delete(ENTITY_API_URL_ID, quanHeMaster.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return quanHeMasterRepository.count();
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

    protected QuanHeMaster getPersistedQuanHeMaster(QuanHeMaster quanHeMaster) {
        return quanHeMasterRepository.findById(quanHeMaster.getId()).orElseThrow();
    }

    protected void assertPersistedQuanHeMasterToMatchAllProperties(QuanHeMaster expectedQuanHeMaster) {
        assertQuanHeMasterAllPropertiesEquals(expectedQuanHeMaster, getPersistedQuanHeMaster(expectedQuanHeMaster));
    }

    protected void assertPersistedQuanHeMasterToMatchUpdatableProperties(QuanHeMaster expectedQuanHeMaster) {
        assertQuanHeMasterAllUpdatablePropertiesEquals(expectedQuanHeMaster, getPersistedQuanHeMaster(expectedQuanHeMaster));
    }
}
