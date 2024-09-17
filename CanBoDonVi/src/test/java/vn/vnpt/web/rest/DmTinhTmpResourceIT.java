package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DmTinhTmpAsserts.*;
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
import vn.vnpt.domain.DmTinhTmp;
import vn.vnpt.repository.DmTinhTmpRepository;
import vn.vnpt.service.dto.DmTinhTmpDTO;
import vn.vnpt.service.mapper.DmTinhTmpMapper;

/**
 * Integration tests for the {@link DmTinhTmpResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DmTinhTmpResourceIT {

    private static final String DEFAULT_TEN_TINH = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TINH = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/dm-tinh-tmps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{maTinh}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DmTinhTmpRepository dmTinhTmpRepository;

    @Autowired
    private DmTinhTmpMapper dmTinhTmpMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDmTinhTmpMockMvc;

    private DmTinhTmp dmTinhTmp;

    private DmTinhTmp insertedDmTinhTmp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmTinhTmp createEntity() {
        return new DmTinhTmp().tenTinh(DEFAULT_TEN_TINH);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmTinhTmp createUpdatedEntity() {
        return new DmTinhTmp().tenTinh(UPDATED_TEN_TINH);
    }

    @BeforeEach
    public void initTest() {
        dmTinhTmp = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDmTinhTmp != null) {
            dmTinhTmpRepository.delete(insertedDmTinhTmp);
            insertedDmTinhTmp = null;
        }
    }

    @Test
    @Transactional
    void createDmTinhTmp() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DmTinhTmp
        DmTinhTmpDTO dmTinhTmpDTO = dmTinhTmpMapper.toDto(dmTinhTmp);
        var returnedDmTinhTmpDTO = om.readValue(
            restDmTinhTmpMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmTinhTmpDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DmTinhTmpDTO.class
        );

        // Validate the DmTinhTmp in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDmTinhTmp = dmTinhTmpMapper.toEntity(returnedDmTinhTmpDTO);
        assertDmTinhTmpUpdatableFieldsEquals(returnedDmTinhTmp, getPersistedDmTinhTmp(returnedDmTinhTmp));

        insertedDmTinhTmp = returnedDmTinhTmp;
    }

    @Test
    @Transactional
    void createDmTinhTmpWithExistingId() throws Exception {
        // Create the DmTinhTmp with an existing ID
        dmTinhTmp.setMaTinh(1L);
        DmTinhTmpDTO dmTinhTmpDTO = dmTinhTmpMapper.toDto(dmTinhTmp);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmTinhTmpMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmTinhTmpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmTinhTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDmTinhTmps() throws Exception {
        // Initialize the database
        insertedDmTinhTmp = dmTinhTmpRepository.saveAndFlush(dmTinhTmp);

        // Get all the dmTinhTmpList
        restDmTinhTmpMockMvc
            .perform(get(ENTITY_API_URL + "?sort=maTinh,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(dmTinhTmp.getMaTinh().intValue())))
            .andExpect(jsonPath("$.[*].tenTinh").value(hasItem(DEFAULT_TEN_TINH)));
    }

    @Test
    @Transactional
    void getDmTinhTmp() throws Exception {
        // Initialize the database
        insertedDmTinhTmp = dmTinhTmpRepository.saveAndFlush(dmTinhTmp);

        // Get the dmTinhTmp
        restDmTinhTmpMockMvc
            .perform(get(ENTITY_API_URL_ID, dmTinhTmp.getMaTinh()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.maTinh").value(dmTinhTmp.getMaTinh().intValue()))
            .andExpect(jsonPath("$.tenTinh").value(DEFAULT_TEN_TINH));
    }

    @Test
    @Transactional
    void getNonExistingDmTinhTmp() throws Exception {
        // Get the dmTinhTmp
        restDmTinhTmpMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDmTinhTmp() throws Exception {
        // Initialize the database
        insertedDmTinhTmp = dmTinhTmpRepository.saveAndFlush(dmTinhTmp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmTinhTmp
        DmTinhTmp updatedDmTinhTmp = dmTinhTmpRepository.findById(dmTinhTmp.getMaTinh()).orElseThrow();
        // Disconnect from session so that the updates on updatedDmTinhTmp are not directly saved in db
        em.detach(updatedDmTinhTmp);
        updatedDmTinhTmp.tenTinh(UPDATED_TEN_TINH);
        DmTinhTmpDTO dmTinhTmpDTO = dmTinhTmpMapper.toDto(updatedDmTinhTmp);

        restDmTinhTmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmTinhTmpDTO.getMaTinh())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmTinhTmpDTO))
            )
            .andExpect(status().isOk());

        // Validate the DmTinhTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDmTinhTmpToMatchAllProperties(updatedDmTinhTmp);
    }

    @Test
    @Transactional
    void putNonExistingDmTinhTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTinhTmp.setMaTinh(longCount.incrementAndGet());

        // Create the DmTinhTmp
        DmTinhTmpDTO dmTinhTmpDTO = dmTinhTmpMapper.toDto(dmTinhTmp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmTinhTmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmTinhTmpDTO.getMaTinh())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmTinhTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmTinhTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDmTinhTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTinhTmp.setMaTinh(longCount.incrementAndGet());

        // Create the DmTinhTmp
        DmTinhTmpDTO dmTinhTmpDTO = dmTinhTmpMapper.toDto(dmTinhTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmTinhTmpMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmTinhTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmTinhTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDmTinhTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTinhTmp.setMaTinh(longCount.incrementAndGet());

        // Create the DmTinhTmp
        DmTinhTmpDTO dmTinhTmpDTO = dmTinhTmpMapper.toDto(dmTinhTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmTinhTmpMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmTinhTmpDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmTinhTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDmTinhTmpWithPatch() throws Exception {
        // Initialize the database
        insertedDmTinhTmp = dmTinhTmpRepository.saveAndFlush(dmTinhTmp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmTinhTmp using partial update
        DmTinhTmp partialUpdatedDmTinhTmp = new DmTinhTmp();
        partialUpdatedDmTinhTmp.setMaTinh(dmTinhTmp.getMaTinh());

        partialUpdatedDmTinhTmp.tenTinh(UPDATED_TEN_TINH);

        restDmTinhTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmTinhTmp.getMaTinh())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmTinhTmp))
            )
            .andExpect(status().isOk());

        // Validate the DmTinhTmp in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmTinhTmpUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDmTinhTmp, dmTinhTmp),
            getPersistedDmTinhTmp(dmTinhTmp)
        );
    }

    @Test
    @Transactional
    void fullUpdateDmTinhTmpWithPatch() throws Exception {
        // Initialize the database
        insertedDmTinhTmp = dmTinhTmpRepository.saveAndFlush(dmTinhTmp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmTinhTmp using partial update
        DmTinhTmp partialUpdatedDmTinhTmp = new DmTinhTmp();
        partialUpdatedDmTinhTmp.setMaTinh(dmTinhTmp.getMaTinh());

        partialUpdatedDmTinhTmp.tenTinh(UPDATED_TEN_TINH);

        restDmTinhTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmTinhTmp.getMaTinh())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmTinhTmp))
            )
            .andExpect(status().isOk());

        // Validate the DmTinhTmp in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmTinhTmpUpdatableFieldsEquals(partialUpdatedDmTinhTmp, getPersistedDmTinhTmp(partialUpdatedDmTinhTmp));
    }

    @Test
    @Transactional
    void patchNonExistingDmTinhTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTinhTmp.setMaTinh(longCount.incrementAndGet());

        // Create the DmTinhTmp
        DmTinhTmpDTO dmTinhTmpDTO = dmTinhTmpMapper.toDto(dmTinhTmp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmTinhTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dmTinhTmpDTO.getMaTinh())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmTinhTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmTinhTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDmTinhTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTinhTmp.setMaTinh(longCount.incrementAndGet());

        // Create the DmTinhTmp
        DmTinhTmpDTO dmTinhTmpDTO = dmTinhTmpMapper.toDto(dmTinhTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmTinhTmpMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmTinhTmpDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmTinhTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDmTinhTmp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTinhTmp.setMaTinh(longCount.incrementAndGet());

        // Create the DmTinhTmp
        DmTinhTmpDTO dmTinhTmpDTO = dmTinhTmpMapper.toDto(dmTinhTmp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmTinhTmpMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dmTinhTmpDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmTinhTmp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDmTinhTmp() throws Exception {
        // Initialize the database
        insertedDmTinhTmp = dmTinhTmpRepository.saveAndFlush(dmTinhTmp);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dmTinhTmp
        restDmTinhTmpMockMvc
            .perform(delete(ENTITY_API_URL_ID, dmTinhTmp.getMaTinh()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dmTinhTmpRepository.count();
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

    protected DmTinhTmp getPersistedDmTinhTmp(DmTinhTmp dmTinhTmp) {
        return dmTinhTmpRepository.findById(dmTinhTmp.getMaTinh()).orElseThrow();
    }

    protected void assertPersistedDmTinhTmpToMatchAllProperties(DmTinhTmp expectedDmTinhTmp) {
        assertDmTinhTmpAllPropertiesEquals(expectedDmTinhTmp, getPersistedDmTinhTmp(expectedDmTinhTmp));
    }

    protected void assertPersistedDmTinhTmpToMatchUpdatableProperties(DmTinhTmp expectedDmTinhTmp) {
        assertDmTinhTmpAllUpdatablePropertiesEquals(expectedDmTinhTmp, getPersistedDmTinhTmp(expectedDmTinhTmp));
    }
}
