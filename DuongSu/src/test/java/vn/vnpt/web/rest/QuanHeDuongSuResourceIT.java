package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.QuanHeDuongSuAsserts.*;
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
import vn.vnpt.domain.QuanHeDuongSu;
import vn.vnpt.repository.QuanHeDuongSuRepository;
import vn.vnpt.service.dto.QuanHeDuongSuDTO;
import vn.vnpt.service.mapper.QuanHeDuongSuMapper;

/**
 * Integration tests for the {@link QuanHeDuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuanHeDuongSuResourceIT {

    private static final Long DEFAULT_ID_DUONG_SU_QH = 1L;
    private static final Long UPDATED_ID_DUONG_SU_QH = 2L;

    private static final String DEFAULT_THONG_TIN_QUAN_HE = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_QUAN_HE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANG_THAI = 0;
    private static final Integer UPDATED_TRANG_THAI = 1;

    private static final String ENTITY_API_URL = "/api/quan-he-duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idQuanHe}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QuanHeDuongSuRepository quanHeDuongSuRepository;

    @Autowired
    private QuanHeDuongSuMapper quanHeDuongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuanHeDuongSuMockMvc;

    private QuanHeDuongSu quanHeDuongSu;

    private QuanHeDuongSu insertedQuanHeDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuanHeDuongSu createEntity() {
        return new QuanHeDuongSu()
            .idDuongSuQh(DEFAULT_ID_DUONG_SU_QH)
            .thongTinQuanHe(DEFAULT_THONG_TIN_QUAN_HE)
            .trangThai(DEFAULT_TRANG_THAI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuanHeDuongSu createUpdatedEntity() {
        return new QuanHeDuongSu()
            .idDuongSuQh(UPDATED_ID_DUONG_SU_QH)
            .thongTinQuanHe(UPDATED_THONG_TIN_QUAN_HE)
            .trangThai(UPDATED_TRANG_THAI);
    }

    @BeforeEach
    public void initTest() {
        quanHeDuongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedQuanHeDuongSu != null) {
            quanHeDuongSuRepository.delete(insertedQuanHeDuongSu);
            insertedQuanHeDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);
        var returnedQuanHeDuongSuDTO = om.readValue(
            restQuanHeDuongSuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeDuongSuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QuanHeDuongSuDTO.class
        );

        // Validate the QuanHeDuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedQuanHeDuongSu = quanHeDuongSuMapper.toEntity(returnedQuanHeDuongSuDTO);
        assertQuanHeDuongSuUpdatableFieldsEquals(returnedQuanHeDuongSu, getPersistedQuanHeDuongSu(returnedQuanHeDuongSu));

        insertedQuanHeDuongSu = returnedQuanHeDuongSu;
    }

    @Test
    @Transactional
    void createQuanHeDuongSuWithExistingId() throws Exception {
        // Create the QuanHeDuongSu with an existing ID
        quanHeDuongSu.setIdQuanHe(1L);
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuanHeDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeDuongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSus() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList
        restQuanHeDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idQuanHe,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idQuanHe").value(hasItem(quanHeDuongSu.getIdQuanHe().intValue())))
            .andExpect(jsonPath("$.[*].idDuongSuQh").value(hasItem(DEFAULT_ID_DUONG_SU_QH.intValue())))
            .andExpect(jsonPath("$.[*].thongTinQuanHe").value(hasItem(DEFAULT_THONG_TIN_QUAN_HE)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)));
    }

    @Test
    @Transactional
    void getQuanHeDuongSu() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get the quanHeDuongSu
        restQuanHeDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, quanHeDuongSu.getIdQuanHe()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idQuanHe").value(quanHeDuongSu.getIdQuanHe().intValue()))
            .andExpect(jsonPath("$.idDuongSuQh").value(DEFAULT_ID_DUONG_SU_QH.intValue()))
            .andExpect(jsonPath("$.thongTinQuanHe").value(DEFAULT_THONG_TIN_QUAN_HE))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI));
    }

    @Test
    @Transactional
    void getNonExistingQuanHeDuongSu() throws Exception {
        // Get the quanHeDuongSu
        restQuanHeDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuanHeDuongSu() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeDuongSu
        QuanHeDuongSu updatedQuanHeDuongSu = quanHeDuongSuRepository.findById(quanHeDuongSu.getIdQuanHe()).orElseThrow();
        // Disconnect from session so that the updates on updatedQuanHeDuongSu are not directly saved in db
        em.detach(updatedQuanHeDuongSu);
        updatedQuanHeDuongSu.idDuongSuQh(UPDATED_ID_DUONG_SU_QH).thongTinQuanHe(UPDATED_THONG_TIN_QUAN_HE).trangThai(UPDATED_TRANG_THAI);
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(updatedQuanHeDuongSu);

        restQuanHeDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quanHeDuongSuDTO.getIdQuanHe())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeDuongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQuanHeDuongSuToMatchAllProperties(updatedQuanHeDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quanHeDuongSuDTO.getIdQuanHe())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuanHeDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeDuongSu using partial update
        QuanHeDuongSu partialUpdatedQuanHeDuongSu = new QuanHeDuongSu();
        partialUpdatedQuanHeDuongSu.setIdQuanHe(quanHeDuongSu.getIdQuanHe());

        partialUpdatedQuanHeDuongSu.idDuongSuQh(UPDATED_ID_DUONG_SU_QH).trangThai(UPDATED_TRANG_THAI);

        restQuanHeDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuanHeDuongSu.getIdQuanHe())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuanHeDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuanHeDuongSuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQuanHeDuongSu, quanHeDuongSu),
            getPersistedQuanHeDuongSu(quanHeDuongSu)
        );
    }

    @Test
    @Transactional
    void fullUpdateQuanHeDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeDuongSu using partial update
        QuanHeDuongSu partialUpdatedQuanHeDuongSu = new QuanHeDuongSu();
        partialUpdatedQuanHeDuongSu.setIdQuanHe(quanHeDuongSu.getIdQuanHe());

        partialUpdatedQuanHeDuongSu
            .idDuongSuQh(UPDATED_ID_DUONG_SU_QH)
            .thongTinQuanHe(UPDATED_THONG_TIN_QUAN_HE)
            .trangThai(UPDATED_TRANG_THAI);

        restQuanHeDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuanHeDuongSu.getIdQuanHe())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuanHeDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuanHeDuongSuUpdatableFieldsEquals(partialUpdatedQuanHeDuongSu, getPersistedQuanHeDuongSu(partialUpdatedQuanHeDuongSu));
    }

    @Test
    @Transactional
    void patchNonExistingQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, quanHeDuongSuDTO.getIdQuanHe())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(quanHeDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(quanHeDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(quanHeDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuanHeDuongSu() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the quanHeDuongSu
        restQuanHeDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, quanHeDuongSu.getIdQuanHe()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return quanHeDuongSuRepository.count();
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

    protected QuanHeDuongSu getPersistedQuanHeDuongSu(QuanHeDuongSu quanHeDuongSu) {
        return quanHeDuongSuRepository.findById(quanHeDuongSu.getIdQuanHe()).orElseThrow();
    }

    protected void assertPersistedQuanHeDuongSuToMatchAllProperties(QuanHeDuongSu expectedQuanHeDuongSu) {
        assertQuanHeDuongSuAllPropertiesEquals(expectedQuanHeDuongSu, getPersistedQuanHeDuongSu(expectedQuanHeDuongSu));
    }

    protected void assertPersistedQuanHeDuongSuToMatchUpdatableProperties(QuanHeDuongSu expectedQuanHeDuongSu) {
        assertQuanHeDuongSuAllUpdatablePropertiesEquals(expectedQuanHeDuongSu, getPersistedQuanHeDuongSu(expectedQuanHeDuongSu));
    }
}
