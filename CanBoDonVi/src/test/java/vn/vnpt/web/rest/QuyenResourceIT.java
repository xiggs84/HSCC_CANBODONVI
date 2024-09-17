package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.QuyenAsserts.*;
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
import vn.vnpt.domain.Quyen;
import vn.vnpt.repository.QuyenRepository;
import vn.vnpt.service.dto.QuyenDTO;
import vn.vnpt.service.mapper.QuyenMapper;

/**
 * Integration tests for the {@link QuyenResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuyenResourceIT {

    private static final String DEFAULT_TEN_QUYEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_QUYEN = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/quyens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idQuyen}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QuyenRepository quyenRepository;

    @Autowired
    private QuyenMapper quyenMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuyenMockMvc;

    private Quyen quyen;

    private Quyen insertedQuyen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quyen createEntity() {
        return new Quyen().tenQuyen(DEFAULT_TEN_QUYEN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quyen createUpdatedEntity() {
        return new Quyen().tenQuyen(UPDATED_TEN_QUYEN);
    }

    @BeforeEach
    public void initTest() {
        quyen = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedQuyen != null) {
            quyenRepository.delete(insertedQuyen);
            insertedQuyen = null;
        }
    }

    @Test
    @Transactional
    void createQuyen() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Quyen
        QuyenDTO quyenDTO = quyenMapper.toDto(quyen);
        var returnedQuyenDTO = om.readValue(
            restQuyenMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quyenDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QuyenDTO.class
        );

        // Validate the Quyen in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedQuyen = quyenMapper.toEntity(returnedQuyenDTO);
        assertQuyenUpdatableFieldsEquals(returnedQuyen, getPersistedQuyen(returnedQuyen));

        insertedQuyen = returnedQuyen;
    }

    @Test
    @Transactional
    void createQuyenWithExistingId() throws Exception {
        // Create the Quyen with an existing ID
        quyen.setIdQuyen(1L);
        QuyenDTO quyenDTO = quyenMapper.toDto(quyen);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuyenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Quyen in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuyens() throws Exception {
        // Initialize the database
        insertedQuyen = quyenRepository.saveAndFlush(quyen);

        // Get all the quyenList
        restQuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idQuyen,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idQuyen").value(hasItem(quyen.getIdQuyen().intValue())))
            .andExpect(jsonPath("$.[*].tenQuyen").value(hasItem(DEFAULT_TEN_QUYEN)));
    }

    @Test
    @Transactional
    void getQuyen() throws Exception {
        // Initialize the database
        insertedQuyen = quyenRepository.saveAndFlush(quyen);

        // Get the quyen
        restQuyenMockMvc
            .perform(get(ENTITY_API_URL_ID, quyen.getIdQuyen()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idQuyen").value(quyen.getIdQuyen().intValue()))
            .andExpect(jsonPath("$.tenQuyen").value(DEFAULT_TEN_QUYEN));
    }

    @Test
    @Transactional
    void getNonExistingQuyen() throws Exception {
        // Get the quyen
        restQuyenMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuyen() throws Exception {
        // Initialize the database
        insertedQuyen = quyenRepository.saveAndFlush(quyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quyen
        Quyen updatedQuyen = quyenRepository.findById(quyen.getIdQuyen()).orElseThrow();
        // Disconnect from session so that the updates on updatedQuyen are not directly saved in db
        em.detach(updatedQuyen);
        updatedQuyen.tenQuyen(UPDATED_TEN_QUYEN);
        QuyenDTO quyenDTO = quyenMapper.toDto(updatedQuyen);

        restQuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quyenDTO.getIdQuyen())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quyenDTO))
            )
            .andExpect(status().isOk());

        // Validate the Quyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQuyenToMatchAllProperties(updatedQuyen);
    }

    @Test
    @Transactional
    void putNonExistingQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quyen.setIdQuyen(longCount.incrementAndGet());

        // Create the Quyen
        QuyenDTO quyenDTO = quyenMapper.toDto(quyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quyenDTO.getIdQuyen())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Quyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quyen.setIdQuyen(longCount.incrementAndGet());

        // Create the Quyen
        QuyenDTO quyenDTO = quyenMapper.toDto(quyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Quyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quyen.setIdQuyen(longCount.incrementAndGet());

        // Create the Quyen
        QuyenDTO quyenDTO = quyenMapper.toDto(quyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuyenMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quyenDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Quyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuyenWithPatch() throws Exception {
        // Initialize the database
        insertedQuyen = quyenRepository.saveAndFlush(quyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quyen using partial update
        Quyen partialUpdatedQuyen = new Quyen();
        partialUpdatedQuyen.setIdQuyen(quyen.getIdQuyen());

        restQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuyen.getIdQuyen())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuyen))
            )
            .andExpect(status().isOk());

        // Validate the Quyen in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuyenUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedQuyen, quyen), getPersistedQuyen(quyen));
    }

    @Test
    @Transactional
    void fullUpdateQuyenWithPatch() throws Exception {
        // Initialize the database
        insertedQuyen = quyenRepository.saveAndFlush(quyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quyen using partial update
        Quyen partialUpdatedQuyen = new Quyen();
        partialUpdatedQuyen.setIdQuyen(quyen.getIdQuyen());

        partialUpdatedQuyen.tenQuyen(UPDATED_TEN_QUYEN);

        restQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuyen.getIdQuyen())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuyen))
            )
            .andExpect(status().isOk());

        // Validate the Quyen in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuyenUpdatableFieldsEquals(partialUpdatedQuyen, getPersistedQuyen(partialUpdatedQuyen));
    }

    @Test
    @Transactional
    void patchNonExistingQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quyen.setIdQuyen(longCount.incrementAndGet());

        // Create the Quyen
        QuyenDTO quyenDTO = quyenMapper.toDto(quyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, quyenDTO.getIdQuyen())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(quyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Quyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quyen.setIdQuyen(longCount.incrementAndGet());

        // Create the Quyen
        QuyenDTO quyenDTO = quyenMapper.toDto(quyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(quyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Quyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quyen.setIdQuyen(longCount.incrementAndGet());

        // Create the Quyen
        QuyenDTO quyenDTO = quyenMapper.toDto(quyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuyenMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(quyenDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Quyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuyen() throws Exception {
        // Initialize the database
        insertedQuyen = quyenRepository.saveAndFlush(quyen);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the quyen
        restQuyenMockMvc
            .perform(delete(ENTITY_API_URL_ID, quyen.getIdQuyen()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return quyenRepository.count();
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

    protected Quyen getPersistedQuyen(Quyen quyen) {
        return quyenRepository.findById(quyen.getIdQuyen()).orElseThrow();
    }

    protected void assertPersistedQuyenToMatchAllProperties(Quyen expectedQuyen) {
        assertQuyenAllPropertiesEquals(expectedQuyen, getPersistedQuyen(expectedQuyen));
    }

    protected void assertPersistedQuyenToMatchUpdatableProperties(Quyen expectedQuyen) {
        assertQuyenAllUpdatablePropertiesEquals(expectedQuyen, getPersistedQuyen(expectedQuyen));
    }
}
