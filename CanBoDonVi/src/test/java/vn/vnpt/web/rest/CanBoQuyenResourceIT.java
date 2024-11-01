package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.CanBoQuyenAsserts.*;
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
import vn.vnpt.domain.CanBoQuyen;
import vn.vnpt.domain.DanhMucDonVi;
import vn.vnpt.repository.CanBoQuyenRepository;
import vn.vnpt.service.dto.CanBoQuyenDTO;
import vn.vnpt.service.mapper.CanBoQuyenMapper;

/**
 * Integration tests for the {@link CanBoQuyenResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CanBoQuyenResourceIT {

    private static final String ENTITY_API_URL = "/api/can-bo-quyens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CanBoQuyenRepository canBoQuyenRepository;

    @Autowired
    private CanBoQuyenMapper canBoQuyenMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCanBoQuyenMockMvc;

    private CanBoQuyen canBoQuyen;

    private CanBoQuyen insertedCanBoQuyen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CanBoQuyen createEntity() {
        return new CanBoQuyen();
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CanBoQuyen createUpdatedEntity() {
        return new CanBoQuyen();
    }

    @BeforeEach
    public void initTest() {
        canBoQuyen = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCanBoQuyen != null) {
            canBoQuyenRepository.delete(insertedCanBoQuyen);
            insertedCanBoQuyen = null;
        }
    }

    @Test
    @Transactional
    void createCanBoQuyen() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CanBoQuyen
        CanBoQuyenDTO canBoQuyenDTO = canBoQuyenMapper.toDto(canBoQuyen);
        var returnedCanBoQuyenDTO = om.readValue(
            restCanBoQuyenMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(canBoQuyenDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CanBoQuyenDTO.class
        );

        // Validate the CanBoQuyen in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCanBoQuyen = canBoQuyenMapper.toEntity(returnedCanBoQuyenDTO);
        assertCanBoQuyenUpdatableFieldsEquals(returnedCanBoQuyen, getPersistedCanBoQuyen(returnedCanBoQuyen));

        insertedCanBoQuyen = returnedCanBoQuyen;
    }

    @Test
    @Transactional
    void createCanBoQuyenWithExistingId() throws Exception {
        // Create the CanBoQuyen with an existing ID
        canBoQuyen.setId(1L);
        CanBoQuyenDTO canBoQuyenDTO = canBoQuyenMapper.toDto(canBoQuyen);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCanBoQuyenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(canBoQuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CanBoQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCanBoQuyens() throws Exception {
        // Initialize the database
        insertedCanBoQuyen = canBoQuyenRepository.saveAndFlush(canBoQuyen);

        // Get all the canBoQuyenList
        restCanBoQuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(canBoQuyen.getId().intValue())));
    }

    @Test
    @Transactional
    void getCanBoQuyen() throws Exception {
        // Initialize the database
        insertedCanBoQuyen = canBoQuyenRepository.saveAndFlush(canBoQuyen);

        // Get the canBoQuyen
        restCanBoQuyenMockMvc
            .perform(get(ENTITY_API_URL_ID, canBoQuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(canBoQuyen.getId().intValue()));
    }

    @Test
    @Transactional
    void getCanBoQuyensByIdFiltering() throws Exception {
        // Initialize the database
        insertedCanBoQuyen = canBoQuyenRepository.saveAndFlush(canBoQuyen);

        Long id = canBoQuyen.getId();

        defaultCanBoQuyenFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultCanBoQuyenFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultCanBoQuyenFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCanBoQuyensByDanhMucDonViIsEqualToSomething() throws Exception {
        DanhMucDonVi danhMucDonVi;
        if (TestUtil.findAll(em, DanhMucDonVi.class).isEmpty()) {
            canBoQuyenRepository.saveAndFlush(canBoQuyen);
            danhMucDonVi = DanhMucDonViResourceIT.createEntity();
        } else {
            danhMucDonVi = TestUtil.findAll(em, DanhMucDonVi.class).get(0);
        }
        em.persist(danhMucDonVi);
        em.flush();
        canBoQuyen.setDanhMucDonVi(danhMucDonVi);
        canBoQuyenRepository.saveAndFlush(canBoQuyen);
        Long danhMucDonViId = danhMucDonVi.getIdDonVi();
        // Get all the canBoQuyenList where danhMucDonVi equals to danhMucDonViId
        defaultCanBoQuyenShouldBeFound("danhMucDonViId.equals=" + danhMucDonViId);

        // Get all the canBoQuyenList where danhMucDonVi equals to (danhMucDonViId + 1)
        defaultCanBoQuyenShouldNotBeFound("danhMucDonViId.equals=" + (danhMucDonViId + 1));
    }

    private void defaultCanBoQuyenFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultCanBoQuyenShouldBeFound(shouldBeFound);
        defaultCanBoQuyenShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCanBoQuyenShouldBeFound(String filter) throws Exception {
        restCanBoQuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(canBoQuyen.getId().intValue())));

        // Check, that the count call also returns 1
        restCanBoQuyenMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCanBoQuyenShouldNotBeFound(String filter) throws Exception {
        restCanBoQuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCanBoQuyenMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCanBoQuyen() throws Exception {
        // Get the canBoQuyen
        restCanBoQuyenMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCanBoQuyen() throws Exception {
        // Initialize the database
        insertedCanBoQuyen = canBoQuyenRepository.saveAndFlush(canBoQuyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the canBoQuyen
        CanBoQuyen updatedCanBoQuyen = canBoQuyenRepository.findById(canBoQuyen.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCanBoQuyen are not directly saved in db
        em.detach(updatedCanBoQuyen);
        CanBoQuyenDTO canBoQuyenDTO = canBoQuyenMapper.toDto(updatedCanBoQuyen);

        restCanBoQuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, canBoQuyenDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(canBoQuyenDTO))
            )
            .andExpect(status().isOk());

        // Validate the CanBoQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCanBoQuyenToMatchAllProperties(updatedCanBoQuyen);
    }

    @Test
    @Transactional
    void putNonExistingCanBoQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        canBoQuyen.setId(longCount.incrementAndGet());

        // Create the CanBoQuyen
        CanBoQuyenDTO canBoQuyenDTO = canBoQuyenMapper.toDto(canBoQuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCanBoQuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, canBoQuyenDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(canBoQuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CanBoQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCanBoQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        canBoQuyen.setId(longCount.incrementAndGet());

        // Create the CanBoQuyen
        CanBoQuyenDTO canBoQuyenDTO = canBoQuyenMapper.toDto(canBoQuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCanBoQuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(canBoQuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CanBoQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCanBoQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        canBoQuyen.setId(longCount.incrementAndGet());

        // Create the CanBoQuyen
        CanBoQuyenDTO canBoQuyenDTO = canBoQuyenMapper.toDto(canBoQuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCanBoQuyenMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(canBoQuyenDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CanBoQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCanBoQuyenWithPatch() throws Exception {
        // Initialize the database
        insertedCanBoQuyen = canBoQuyenRepository.saveAndFlush(canBoQuyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the canBoQuyen using partial update
        CanBoQuyen partialUpdatedCanBoQuyen = new CanBoQuyen();
        partialUpdatedCanBoQuyen.setId(canBoQuyen.getId());

        restCanBoQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCanBoQuyen.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCanBoQuyen))
            )
            .andExpect(status().isOk());

        // Validate the CanBoQuyen in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCanBoQuyenUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCanBoQuyen, canBoQuyen),
            getPersistedCanBoQuyen(canBoQuyen)
        );
    }

    @Test
    @Transactional
    void fullUpdateCanBoQuyenWithPatch() throws Exception {
        // Initialize the database
        insertedCanBoQuyen = canBoQuyenRepository.saveAndFlush(canBoQuyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the canBoQuyen using partial update
        CanBoQuyen partialUpdatedCanBoQuyen = new CanBoQuyen();
        partialUpdatedCanBoQuyen.setId(canBoQuyen.getId());

        restCanBoQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCanBoQuyen.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCanBoQuyen))
            )
            .andExpect(status().isOk());

        // Validate the CanBoQuyen in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCanBoQuyenUpdatableFieldsEquals(partialUpdatedCanBoQuyen, getPersistedCanBoQuyen(partialUpdatedCanBoQuyen));
    }

    @Test
    @Transactional
    void patchNonExistingCanBoQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        canBoQuyen.setId(longCount.incrementAndGet());

        // Create the CanBoQuyen
        CanBoQuyenDTO canBoQuyenDTO = canBoQuyenMapper.toDto(canBoQuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCanBoQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, canBoQuyenDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(canBoQuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CanBoQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCanBoQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        canBoQuyen.setId(longCount.incrementAndGet());

        // Create the CanBoQuyen
        CanBoQuyenDTO canBoQuyenDTO = canBoQuyenMapper.toDto(canBoQuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCanBoQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(canBoQuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CanBoQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCanBoQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        canBoQuyen.setId(longCount.incrementAndGet());

        // Create the CanBoQuyen
        CanBoQuyenDTO canBoQuyenDTO = canBoQuyenMapper.toDto(canBoQuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCanBoQuyenMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(canBoQuyenDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CanBoQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCanBoQuyen() throws Exception {
        // Initialize the database
        insertedCanBoQuyen = canBoQuyenRepository.saveAndFlush(canBoQuyen);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the canBoQuyen
        restCanBoQuyenMockMvc
            .perform(delete(ENTITY_API_URL_ID, canBoQuyen.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return canBoQuyenRepository.count();
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

    protected CanBoQuyen getPersistedCanBoQuyen(CanBoQuyen canBoQuyen) {
        return canBoQuyenRepository.findById(canBoQuyen.getId()).orElseThrow();
    }

    protected void assertPersistedCanBoQuyenToMatchAllProperties(CanBoQuyen expectedCanBoQuyen) {
        assertCanBoQuyenAllPropertiesEquals(expectedCanBoQuyen, getPersistedCanBoQuyen(expectedCanBoQuyen));
    }

    protected void assertPersistedCanBoQuyenToMatchUpdatableProperties(CanBoQuyen expectedCanBoQuyen) {
        assertCanBoQuyenAllUpdatablePropertiesEquals(expectedCanBoQuyen, getPersistedCanBoQuyen(expectedCanBoQuyen));
    }
}
