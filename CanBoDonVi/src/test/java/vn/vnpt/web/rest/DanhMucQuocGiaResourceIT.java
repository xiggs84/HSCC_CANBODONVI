package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucQuocGiaAsserts.*;
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
import vn.vnpt.domain.DanhMucQuocGia;
import vn.vnpt.repository.DanhMucQuocGiaRepository;
import vn.vnpt.service.dto.DanhMucQuocGiaDTO;
import vn.vnpt.service.mapper.DanhMucQuocGiaMapper;

/**
 * Integration tests for the {@link DanhMucQuocGiaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucQuocGiaResourceIT {

    private static final String DEFAULT_TEN_QUOC_GIA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_QUOC_GIA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_TIENG_ANH = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TIENG_ANH = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-quoc-gias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idQuocGia}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucQuocGiaRepository danhMucQuocGiaRepository;

    @Autowired
    private DanhMucQuocGiaMapper danhMucQuocGiaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucQuocGiaMockMvc;

    private DanhMucQuocGia danhMucQuocGia;

    private DanhMucQuocGia insertedDanhMucQuocGia;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucQuocGia createEntity() {
        return new DanhMucQuocGia().tenQuocGia(DEFAULT_TEN_QUOC_GIA).tenTiengAnh(DEFAULT_TEN_TIENG_ANH);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucQuocGia createUpdatedEntity() {
        return new DanhMucQuocGia().tenQuocGia(UPDATED_TEN_QUOC_GIA).tenTiengAnh(UPDATED_TEN_TIENG_ANH);
    }

    @BeforeEach
    public void initTest() {
        danhMucQuocGia = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucQuocGia != null) {
            danhMucQuocGiaRepository.delete(insertedDanhMucQuocGia);
            insertedDanhMucQuocGia = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucQuocGia() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucQuocGia
        DanhMucQuocGiaDTO danhMucQuocGiaDTO = danhMucQuocGiaMapper.toDto(danhMucQuocGia);
        var returnedDanhMucQuocGiaDTO = om.readValue(
            restDanhMucQuocGiaMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucQuocGiaDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucQuocGiaDTO.class
        );

        // Validate the DanhMucQuocGia in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucQuocGia = danhMucQuocGiaMapper.toEntity(returnedDanhMucQuocGiaDTO);
        assertDanhMucQuocGiaUpdatableFieldsEquals(returnedDanhMucQuocGia, getPersistedDanhMucQuocGia(returnedDanhMucQuocGia));

        insertedDanhMucQuocGia = returnedDanhMucQuocGia;
    }

    @Test
    @Transactional
    void createDanhMucQuocGiaWithExistingId() throws Exception {
        // Create the DanhMucQuocGia with an existing ID
        danhMucQuocGia.setIdQuocGia(1L);
        DanhMucQuocGiaDTO danhMucQuocGiaDTO = danhMucQuocGiaMapper.toDto(danhMucQuocGia);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucQuocGiaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucQuocGiaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucQuocGia in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucQuocGias() throws Exception {
        // Initialize the database
        insertedDanhMucQuocGia = danhMucQuocGiaRepository.saveAndFlush(danhMucQuocGia);

        // Get all the danhMucQuocGiaList
        restDanhMucQuocGiaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idQuocGia,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idQuocGia").value(hasItem(danhMucQuocGia.getIdQuocGia().intValue())))
            .andExpect(jsonPath("$.[*].tenQuocGia").value(hasItem(DEFAULT_TEN_QUOC_GIA)))
            .andExpect(jsonPath("$.[*].tenTiengAnh").value(hasItem(DEFAULT_TEN_TIENG_ANH)));
    }

    @Test
    @Transactional
    void getDanhMucQuocGia() throws Exception {
        // Initialize the database
        insertedDanhMucQuocGia = danhMucQuocGiaRepository.saveAndFlush(danhMucQuocGia);

        // Get the danhMucQuocGia
        restDanhMucQuocGiaMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucQuocGia.getIdQuocGia()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idQuocGia").value(danhMucQuocGia.getIdQuocGia().intValue()))
            .andExpect(jsonPath("$.tenQuocGia").value(DEFAULT_TEN_QUOC_GIA))
            .andExpect(jsonPath("$.tenTiengAnh").value(DEFAULT_TEN_TIENG_ANH));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucQuocGia() throws Exception {
        // Get the danhMucQuocGia
        restDanhMucQuocGiaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucQuocGia() throws Exception {
        // Initialize the database
        insertedDanhMucQuocGia = danhMucQuocGiaRepository.saveAndFlush(danhMucQuocGia);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucQuocGia
        DanhMucQuocGia updatedDanhMucQuocGia = danhMucQuocGiaRepository.findById(danhMucQuocGia.getIdQuocGia()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucQuocGia are not directly saved in db
        em.detach(updatedDanhMucQuocGia);
        updatedDanhMucQuocGia.tenQuocGia(UPDATED_TEN_QUOC_GIA).tenTiengAnh(UPDATED_TEN_TIENG_ANH);
        DanhMucQuocGiaDTO danhMucQuocGiaDTO = danhMucQuocGiaMapper.toDto(updatedDanhMucQuocGia);

        restDanhMucQuocGiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucQuocGiaDTO.getIdQuocGia())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucQuocGiaDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucQuocGia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucQuocGiaToMatchAllProperties(updatedDanhMucQuocGia);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucQuocGia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucQuocGia.setIdQuocGia(longCount.incrementAndGet());

        // Create the DanhMucQuocGia
        DanhMucQuocGiaDTO danhMucQuocGiaDTO = danhMucQuocGiaMapper.toDto(danhMucQuocGia);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucQuocGiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucQuocGiaDTO.getIdQuocGia())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucQuocGiaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucQuocGia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucQuocGia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucQuocGia.setIdQuocGia(longCount.incrementAndGet());

        // Create the DanhMucQuocGia
        DanhMucQuocGiaDTO danhMucQuocGiaDTO = danhMucQuocGiaMapper.toDto(danhMucQuocGia);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucQuocGiaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucQuocGiaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucQuocGia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucQuocGia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucQuocGia.setIdQuocGia(longCount.incrementAndGet());

        // Create the DanhMucQuocGia
        DanhMucQuocGiaDTO danhMucQuocGiaDTO = danhMucQuocGiaMapper.toDto(danhMucQuocGia);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucQuocGiaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucQuocGiaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucQuocGia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucQuocGiaWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucQuocGia = danhMucQuocGiaRepository.saveAndFlush(danhMucQuocGia);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucQuocGia using partial update
        DanhMucQuocGia partialUpdatedDanhMucQuocGia = new DanhMucQuocGia();
        partialUpdatedDanhMucQuocGia.setIdQuocGia(danhMucQuocGia.getIdQuocGia());

        partialUpdatedDanhMucQuocGia.tenTiengAnh(UPDATED_TEN_TIENG_ANH);

        restDanhMucQuocGiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucQuocGia.getIdQuocGia())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucQuocGia))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucQuocGia in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucQuocGiaUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucQuocGia, danhMucQuocGia),
            getPersistedDanhMucQuocGia(danhMucQuocGia)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucQuocGiaWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucQuocGia = danhMucQuocGiaRepository.saveAndFlush(danhMucQuocGia);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucQuocGia using partial update
        DanhMucQuocGia partialUpdatedDanhMucQuocGia = new DanhMucQuocGia();
        partialUpdatedDanhMucQuocGia.setIdQuocGia(danhMucQuocGia.getIdQuocGia());

        partialUpdatedDanhMucQuocGia.tenQuocGia(UPDATED_TEN_QUOC_GIA).tenTiengAnh(UPDATED_TEN_TIENG_ANH);

        restDanhMucQuocGiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucQuocGia.getIdQuocGia())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucQuocGia))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucQuocGia in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucQuocGiaUpdatableFieldsEquals(partialUpdatedDanhMucQuocGia, getPersistedDanhMucQuocGia(partialUpdatedDanhMucQuocGia));
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucQuocGia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucQuocGia.setIdQuocGia(longCount.incrementAndGet());

        // Create the DanhMucQuocGia
        DanhMucQuocGiaDTO danhMucQuocGiaDTO = danhMucQuocGiaMapper.toDto(danhMucQuocGia);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucQuocGiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucQuocGiaDTO.getIdQuocGia())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucQuocGiaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucQuocGia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucQuocGia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucQuocGia.setIdQuocGia(longCount.incrementAndGet());

        // Create the DanhMucQuocGia
        DanhMucQuocGiaDTO danhMucQuocGiaDTO = danhMucQuocGiaMapper.toDto(danhMucQuocGia);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucQuocGiaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucQuocGiaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucQuocGia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucQuocGia() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucQuocGia.setIdQuocGia(longCount.incrementAndGet());

        // Create the DanhMucQuocGia
        DanhMucQuocGiaDTO danhMucQuocGiaDTO = danhMucQuocGiaMapper.toDto(danhMucQuocGia);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucQuocGiaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucQuocGiaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucQuocGia in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucQuocGia() throws Exception {
        // Initialize the database
        insertedDanhMucQuocGia = danhMucQuocGiaRepository.saveAndFlush(danhMucQuocGia);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucQuocGia
        restDanhMucQuocGiaMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucQuocGia.getIdQuocGia()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucQuocGiaRepository.count();
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

    protected DanhMucQuocGia getPersistedDanhMucQuocGia(DanhMucQuocGia danhMucQuocGia) {
        return danhMucQuocGiaRepository.findById(danhMucQuocGia.getIdQuocGia()).orElseThrow();
    }

    protected void assertPersistedDanhMucQuocGiaToMatchAllProperties(DanhMucQuocGia expectedDanhMucQuocGia) {
        assertDanhMucQuocGiaAllPropertiesEquals(expectedDanhMucQuocGia, getPersistedDanhMucQuocGia(expectedDanhMucQuocGia));
    }

    protected void assertPersistedDanhMucQuocGiaToMatchUpdatableProperties(DanhMucQuocGia expectedDanhMucQuocGia) {
        assertDanhMucQuocGiaAllUpdatablePropertiesEquals(expectedDanhMucQuocGia, getPersistedDanhMucQuocGia(expectedDanhMucQuocGia));
    }
}
