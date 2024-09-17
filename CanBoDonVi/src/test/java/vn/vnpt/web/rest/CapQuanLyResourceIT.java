package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.CapQuanLyAsserts.*;
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
import vn.vnpt.domain.CapQuanLy;
import vn.vnpt.repository.CapQuanLyRepository;
import vn.vnpt.service.dto.CapQuanLyDTO;
import vn.vnpt.service.mapper.CapQuanLyMapper;

/**
 * Integration tests for the {@link CapQuanLyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CapQuanLyResourceIT {

    private static final String DEFAULT_TEN_CAP_QL = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CAP_QL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cap-quan-lies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCapQl}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CapQuanLyRepository capQuanLyRepository;

    @Autowired
    private CapQuanLyMapper capQuanLyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCapQuanLyMockMvc;

    private CapQuanLy capQuanLy;

    private CapQuanLy insertedCapQuanLy;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CapQuanLy createEntity() {
        return new CapQuanLy().idCapQl(UUID.randomUUID().toString()).tenCapQl(DEFAULT_TEN_CAP_QL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CapQuanLy createUpdatedEntity() {
        return new CapQuanLy().idCapQl(UUID.randomUUID().toString()).tenCapQl(UPDATED_TEN_CAP_QL);
    }

    @BeforeEach
    public void initTest() {
        capQuanLy = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCapQuanLy != null) {
            capQuanLyRepository.delete(insertedCapQuanLy);
            insertedCapQuanLy = null;
        }
    }

    @Test
    @Transactional
    void createCapQuanLy() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CapQuanLy
        CapQuanLyDTO capQuanLyDTO = capQuanLyMapper.toDto(capQuanLy);
        var returnedCapQuanLyDTO = om.readValue(
            restCapQuanLyMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(capQuanLyDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CapQuanLyDTO.class
        );

        // Validate the CapQuanLy in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCapQuanLy = capQuanLyMapper.toEntity(returnedCapQuanLyDTO);
        assertCapQuanLyUpdatableFieldsEquals(returnedCapQuanLy, getPersistedCapQuanLy(returnedCapQuanLy));

        insertedCapQuanLy = returnedCapQuanLy;
    }

    @Test
    @Transactional
    void createCapQuanLyWithExistingId() throws Exception {
        // Create the CapQuanLy with an existing ID
        insertedCapQuanLy = capQuanLyRepository.saveAndFlush(capQuanLy);
        CapQuanLyDTO capQuanLyDTO = capQuanLyMapper.toDto(capQuanLy);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCapQuanLyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(capQuanLyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCapQuanLies() throws Exception {
        // Initialize the database
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());
        insertedCapQuanLy = capQuanLyRepository.saveAndFlush(capQuanLy);

        // Get all the capQuanLyList
        restCapQuanLyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCapQl,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCapQl").value(hasItem(capQuanLy.getIdCapQl())))
            .andExpect(jsonPath("$.[*].tenCapQl").value(hasItem(DEFAULT_TEN_CAP_QL)));
    }

    @Test
    @Transactional
    void getCapQuanLy() throws Exception {
        // Initialize the database
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());
        insertedCapQuanLy = capQuanLyRepository.saveAndFlush(capQuanLy);

        // Get the capQuanLy
        restCapQuanLyMockMvc
            .perform(get(ENTITY_API_URL_ID, capQuanLy.getIdCapQl()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCapQl").value(capQuanLy.getIdCapQl()))
            .andExpect(jsonPath("$.tenCapQl").value(DEFAULT_TEN_CAP_QL));
    }

    @Test
    @Transactional
    void getNonExistingCapQuanLy() throws Exception {
        // Get the capQuanLy
        restCapQuanLyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCapQuanLy() throws Exception {
        // Initialize the database
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());
        insertedCapQuanLy = capQuanLyRepository.saveAndFlush(capQuanLy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the capQuanLy
        CapQuanLy updatedCapQuanLy = capQuanLyRepository.findById(capQuanLy.getIdCapQl()).orElseThrow();
        // Disconnect from session so that the updates on updatedCapQuanLy are not directly saved in db
        em.detach(updatedCapQuanLy);
        updatedCapQuanLy.tenCapQl(UPDATED_TEN_CAP_QL);
        CapQuanLyDTO capQuanLyDTO = capQuanLyMapper.toDto(updatedCapQuanLy);

        restCapQuanLyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, capQuanLyDTO.getIdCapQl())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(capQuanLyDTO))
            )
            .andExpect(status().isOk());

        // Validate the CapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCapQuanLyToMatchAllProperties(updatedCapQuanLy);
    }

    @Test
    @Transactional
    void putNonExistingCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());

        // Create the CapQuanLy
        CapQuanLyDTO capQuanLyDTO = capQuanLyMapper.toDto(capQuanLy);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCapQuanLyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, capQuanLyDTO.getIdCapQl())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(capQuanLyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());

        // Create the CapQuanLy
        CapQuanLyDTO capQuanLyDTO = capQuanLyMapper.toDto(capQuanLy);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCapQuanLyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(capQuanLyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());

        // Create the CapQuanLy
        CapQuanLyDTO capQuanLyDTO = capQuanLyMapper.toDto(capQuanLy);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCapQuanLyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(capQuanLyDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCapQuanLyWithPatch() throws Exception {
        // Initialize the database
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());
        insertedCapQuanLy = capQuanLyRepository.saveAndFlush(capQuanLy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the capQuanLy using partial update
        CapQuanLy partialUpdatedCapQuanLy = new CapQuanLy();
        partialUpdatedCapQuanLy.setIdCapQl(capQuanLy.getIdCapQl());

        restCapQuanLyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCapQuanLy.getIdCapQl())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCapQuanLy))
            )
            .andExpect(status().isOk());

        // Validate the CapQuanLy in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCapQuanLyUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCapQuanLy, capQuanLy),
            getPersistedCapQuanLy(capQuanLy)
        );
    }

    @Test
    @Transactional
    void fullUpdateCapQuanLyWithPatch() throws Exception {
        // Initialize the database
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());
        insertedCapQuanLy = capQuanLyRepository.saveAndFlush(capQuanLy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the capQuanLy using partial update
        CapQuanLy partialUpdatedCapQuanLy = new CapQuanLy();
        partialUpdatedCapQuanLy.setIdCapQl(capQuanLy.getIdCapQl());

        partialUpdatedCapQuanLy.tenCapQl(UPDATED_TEN_CAP_QL);

        restCapQuanLyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCapQuanLy.getIdCapQl())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCapQuanLy))
            )
            .andExpect(status().isOk());

        // Validate the CapQuanLy in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCapQuanLyUpdatableFieldsEquals(partialUpdatedCapQuanLy, getPersistedCapQuanLy(partialUpdatedCapQuanLy));
    }

    @Test
    @Transactional
    void patchNonExistingCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());

        // Create the CapQuanLy
        CapQuanLyDTO capQuanLyDTO = capQuanLyMapper.toDto(capQuanLy);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCapQuanLyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, capQuanLyDTO.getIdCapQl())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(capQuanLyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());

        // Create the CapQuanLy
        CapQuanLyDTO capQuanLyDTO = capQuanLyMapper.toDto(capQuanLy);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCapQuanLyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(capQuanLyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCapQuanLy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());

        // Create the CapQuanLy
        CapQuanLyDTO capQuanLyDTO = capQuanLyMapper.toDto(capQuanLy);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCapQuanLyMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(capQuanLyDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CapQuanLy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCapQuanLy() throws Exception {
        // Initialize the database
        capQuanLy.setIdCapQl(UUID.randomUUID().toString());
        insertedCapQuanLy = capQuanLyRepository.saveAndFlush(capQuanLy);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the capQuanLy
        restCapQuanLyMockMvc
            .perform(delete(ENTITY_API_URL_ID, capQuanLy.getIdCapQl()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return capQuanLyRepository.count();
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

    protected CapQuanLy getPersistedCapQuanLy(CapQuanLy capQuanLy) {
        return capQuanLyRepository.findById(capQuanLy.getIdCapQl()).orElseThrow();
    }

    protected void assertPersistedCapQuanLyToMatchAllProperties(CapQuanLy expectedCapQuanLy) {
        assertCapQuanLyAllPropertiesEquals(expectedCapQuanLy, getPersistedCapQuanLy(expectedCapQuanLy));
    }

    protected void assertPersistedCapQuanLyToMatchUpdatableProperties(CapQuanLy expectedCapQuanLy) {
        assertCapQuanLyAllUpdatablePropertiesEquals(expectedCapQuanLy, getPersistedCapQuanLy(expectedCapQuanLy));
    }
}
