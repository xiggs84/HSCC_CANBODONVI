package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DmNoiCapGpdkxAsserts.*;
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
import vn.vnpt.domain.DmNoiCapGpdkx;
import vn.vnpt.repository.DmNoiCapGpdkxRepository;
import vn.vnpt.service.dto.DmNoiCapGpdkxDTO;
import vn.vnpt.service.mapper.DmNoiCapGpdkxMapper;

/**
 * Integration tests for the {@link DmNoiCapGpdkxResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DmNoiCapGpdkxResourceIT {

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String ENTITY_API_URL = "/api/dm-noi-cap-gpdkxes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idNoiCap}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DmNoiCapGpdkxRepository dmNoiCapGpdkxRepository;

    @Autowired
    private DmNoiCapGpdkxMapper dmNoiCapGpdkxMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDmNoiCapGpdkxMockMvc;

    private DmNoiCapGpdkx dmNoiCapGpdkx;

    private DmNoiCapGpdkx insertedDmNoiCapGpdkx;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmNoiCapGpdkx createEntity() {
        return new DmNoiCapGpdkx().dienGiai(DEFAULT_DIEN_GIAI).trangThai(DEFAULT_TRANG_THAI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmNoiCapGpdkx createUpdatedEntity() {
        return new DmNoiCapGpdkx().dienGiai(UPDATED_DIEN_GIAI).trangThai(UPDATED_TRANG_THAI);
    }

    @BeforeEach
    public void initTest() {
        dmNoiCapGpdkx = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDmNoiCapGpdkx != null) {
            dmNoiCapGpdkxRepository.delete(insertedDmNoiCapGpdkx);
            insertedDmNoiCapGpdkx = null;
        }
    }

    @Test
    @Transactional
    void createDmNoiCapGpdkx() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DmNoiCapGpdkx
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO = dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);
        var returnedDmNoiCapGpdkxDTO = om.readValue(
            restDmNoiCapGpdkxMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmNoiCapGpdkxDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DmNoiCapGpdkxDTO.class
        );

        // Validate the DmNoiCapGpdkx in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDmNoiCapGpdkx = dmNoiCapGpdkxMapper.toEntity(returnedDmNoiCapGpdkxDTO);
        assertDmNoiCapGpdkxUpdatableFieldsEquals(returnedDmNoiCapGpdkx, getPersistedDmNoiCapGpdkx(returnedDmNoiCapGpdkx));

        insertedDmNoiCapGpdkx = returnedDmNoiCapGpdkx;
    }

    @Test
    @Transactional
    void createDmNoiCapGpdkxWithExistingId() throws Exception {
        // Create the DmNoiCapGpdkx with an existing ID
        dmNoiCapGpdkx.setIdNoiCap(1L);
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO = dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmNoiCapGpdkxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmNoiCapGpdkxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmNoiCapGpdkx in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDmNoiCapGpdkxes() throws Exception {
        // Initialize the database
        insertedDmNoiCapGpdkx = dmNoiCapGpdkxRepository.saveAndFlush(dmNoiCapGpdkx);

        // Get all the dmNoiCapGpdkxList
        restDmNoiCapGpdkxMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idNoiCap,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idNoiCap").value(hasItem(dmNoiCapGpdkx.getIdNoiCap().intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));
    }

    @Test
    @Transactional
    void getDmNoiCapGpdkx() throws Exception {
        // Initialize the database
        insertedDmNoiCapGpdkx = dmNoiCapGpdkxRepository.saveAndFlush(dmNoiCapGpdkx);

        // Get the dmNoiCapGpdkx
        restDmNoiCapGpdkxMockMvc
            .perform(get(ENTITY_API_URL_ID, dmNoiCapGpdkx.getIdNoiCap()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idNoiCap").value(dmNoiCapGpdkx.getIdNoiCap().intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDmNoiCapGpdkx() throws Exception {
        // Get the dmNoiCapGpdkx
        restDmNoiCapGpdkxMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDmNoiCapGpdkx() throws Exception {
        // Initialize the database
        insertedDmNoiCapGpdkx = dmNoiCapGpdkxRepository.saveAndFlush(dmNoiCapGpdkx);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmNoiCapGpdkx
        DmNoiCapGpdkx updatedDmNoiCapGpdkx = dmNoiCapGpdkxRepository.findById(dmNoiCapGpdkx.getIdNoiCap()).orElseThrow();
        // Disconnect from session so that the updates on updatedDmNoiCapGpdkx are not directly saved in db
        em.detach(updatedDmNoiCapGpdkx);
        updatedDmNoiCapGpdkx.dienGiai(UPDATED_DIEN_GIAI).trangThai(UPDATED_TRANG_THAI);
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO = dmNoiCapGpdkxMapper.toDto(updatedDmNoiCapGpdkx);

        restDmNoiCapGpdkxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmNoiCapGpdkxDTO.getIdNoiCap())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmNoiCapGpdkxDTO))
            )
            .andExpect(status().isOk());

        // Validate the DmNoiCapGpdkx in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDmNoiCapGpdkxToMatchAllProperties(updatedDmNoiCapGpdkx);
    }

    @Test
    @Transactional
    void putNonExistingDmNoiCapGpdkx() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmNoiCapGpdkx.setIdNoiCap(longCount.incrementAndGet());

        // Create the DmNoiCapGpdkx
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO = dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmNoiCapGpdkxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmNoiCapGpdkxDTO.getIdNoiCap())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmNoiCapGpdkxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmNoiCapGpdkx in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDmNoiCapGpdkx() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmNoiCapGpdkx.setIdNoiCap(longCount.incrementAndGet());

        // Create the DmNoiCapGpdkx
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO = dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmNoiCapGpdkxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmNoiCapGpdkxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmNoiCapGpdkx in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDmNoiCapGpdkx() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmNoiCapGpdkx.setIdNoiCap(longCount.incrementAndGet());

        // Create the DmNoiCapGpdkx
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO = dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmNoiCapGpdkxMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmNoiCapGpdkxDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmNoiCapGpdkx in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDmNoiCapGpdkxWithPatch() throws Exception {
        // Initialize the database
        insertedDmNoiCapGpdkx = dmNoiCapGpdkxRepository.saveAndFlush(dmNoiCapGpdkx);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmNoiCapGpdkx using partial update
        DmNoiCapGpdkx partialUpdatedDmNoiCapGpdkx = new DmNoiCapGpdkx();
        partialUpdatedDmNoiCapGpdkx.setIdNoiCap(dmNoiCapGpdkx.getIdNoiCap());

        partialUpdatedDmNoiCapGpdkx.trangThai(UPDATED_TRANG_THAI);

        restDmNoiCapGpdkxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmNoiCapGpdkx.getIdNoiCap())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmNoiCapGpdkx))
            )
            .andExpect(status().isOk());

        // Validate the DmNoiCapGpdkx in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmNoiCapGpdkxUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDmNoiCapGpdkx, dmNoiCapGpdkx),
            getPersistedDmNoiCapGpdkx(dmNoiCapGpdkx)
        );
    }

    @Test
    @Transactional
    void fullUpdateDmNoiCapGpdkxWithPatch() throws Exception {
        // Initialize the database
        insertedDmNoiCapGpdkx = dmNoiCapGpdkxRepository.saveAndFlush(dmNoiCapGpdkx);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmNoiCapGpdkx using partial update
        DmNoiCapGpdkx partialUpdatedDmNoiCapGpdkx = new DmNoiCapGpdkx();
        partialUpdatedDmNoiCapGpdkx.setIdNoiCap(dmNoiCapGpdkx.getIdNoiCap());

        partialUpdatedDmNoiCapGpdkx.dienGiai(UPDATED_DIEN_GIAI).trangThai(UPDATED_TRANG_THAI);

        restDmNoiCapGpdkxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmNoiCapGpdkx.getIdNoiCap())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmNoiCapGpdkx))
            )
            .andExpect(status().isOk());

        // Validate the DmNoiCapGpdkx in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmNoiCapGpdkxUpdatableFieldsEquals(partialUpdatedDmNoiCapGpdkx, getPersistedDmNoiCapGpdkx(partialUpdatedDmNoiCapGpdkx));
    }

    @Test
    @Transactional
    void patchNonExistingDmNoiCapGpdkx() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmNoiCapGpdkx.setIdNoiCap(longCount.incrementAndGet());

        // Create the DmNoiCapGpdkx
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO = dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmNoiCapGpdkxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dmNoiCapGpdkxDTO.getIdNoiCap())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmNoiCapGpdkxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmNoiCapGpdkx in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDmNoiCapGpdkx() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmNoiCapGpdkx.setIdNoiCap(longCount.incrementAndGet());

        // Create the DmNoiCapGpdkx
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO = dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmNoiCapGpdkxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmNoiCapGpdkxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmNoiCapGpdkx in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDmNoiCapGpdkx() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmNoiCapGpdkx.setIdNoiCap(longCount.incrementAndGet());

        // Create the DmNoiCapGpdkx
        DmNoiCapGpdkxDTO dmNoiCapGpdkxDTO = dmNoiCapGpdkxMapper.toDto(dmNoiCapGpdkx);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmNoiCapGpdkxMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dmNoiCapGpdkxDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmNoiCapGpdkx in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDmNoiCapGpdkx() throws Exception {
        // Initialize the database
        insertedDmNoiCapGpdkx = dmNoiCapGpdkxRepository.saveAndFlush(dmNoiCapGpdkx);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dmNoiCapGpdkx
        restDmNoiCapGpdkxMockMvc
            .perform(delete(ENTITY_API_URL_ID, dmNoiCapGpdkx.getIdNoiCap()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dmNoiCapGpdkxRepository.count();
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

    protected DmNoiCapGpdkx getPersistedDmNoiCapGpdkx(DmNoiCapGpdkx dmNoiCapGpdkx) {
        return dmNoiCapGpdkxRepository.findById(dmNoiCapGpdkx.getIdNoiCap()).orElseThrow();
    }

    protected void assertPersistedDmNoiCapGpdkxToMatchAllProperties(DmNoiCapGpdkx expectedDmNoiCapGpdkx) {
        assertDmNoiCapGpdkxAllPropertiesEquals(expectedDmNoiCapGpdkx, getPersistedDmNoiCapGpdkx(expectedDmNoiCapGpdkx));
    }

    protected void assertPersistedDmNoiCapGpdkxToMatchUpdatableProperties(DmNoiCapGpdkx expectedDmNoiCapGpdkx) {
        assertDmNoiCapGpdkxAllUpdatablePropertiesEquals(expectedDmNoiCapGpdkx, getPersistedDmNoiCapGpdkx(expectedDmNoiCapGpdkx));
    }
}
