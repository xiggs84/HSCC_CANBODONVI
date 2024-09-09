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
import vn.vnpt.domain.enumeration.GioiTinh;
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

    private static final GioiTinh DEFAULT_GIOI_TINH = GioiTinh.Nam;
    private static final GioiTinh UPDATED_GIOI_TINH = GioiTinh.Nu;

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
        return new QuanHeNhanThan().dienGiai(DEFAULT_DIEN_GIAI).idQuanHeDoiUng(DEFAULT_ID_QUAN_HE_DOI_UNG).gioiTinh(DEFAULT_GIOI_TINH);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuanHeNhanThan createUpdatedEntity() {
        return new QuanHeNhanThan().dienGiai(UPDATED_DIEN_GIAI).idQuanHeDoiUng(UPDATED_ID_QUAN_HE_DOI_UNG).gioiTinh(UPDATED_GIOI_TINH);
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
            .andExpect(jsonPath("$.[*].idQuanHeDoiUng").value(hasItem(DEFAULT_ID_QUAN_HE_DOI_UNG.intValue())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH.toString())));
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
            .andExpect(jsonPath("$.idQuanHeDoiUng").value(DEFAULT_ID_QUAN_HE_DOI_UNG.intValue()))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH.toString()));
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
        updatedQuanHeNhanThan.dienGiai(UPDATED_DIEN_GIAI).idQuanHeDoiUng(UPDATED_ID_QUAN_HE_DOI_UNG).gioiTinh(UPDATED_GIOI_TINH);
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

        partialUpdatedQuanHeNhanThan.dienGiai(UPDATED_DIEN_GIAI).idQuanHeDoiUng(UPDATED_ID_QUAN_HE_DOI_UNG).gioiTinh(UPDATED_GIOI_TINH);

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
