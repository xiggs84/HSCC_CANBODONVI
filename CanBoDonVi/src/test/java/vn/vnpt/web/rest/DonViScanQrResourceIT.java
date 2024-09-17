package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DonViScanQrAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
import vn.vnpt.domain.DonViScanQr;
import vn.vnpt.repository.DonViScanQrRepository;
import vn.vnpt.service.dto.DonViScanQrDTO;
import vn.vnpt.service.mapper.DonViScanQrMapper;

/**
 * Integration tests for the {@link DonViScanQrResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DonViScanQrResourceIT {

    private static final Long DEFAULT_ID_CONG_DAN = 1L;
    private static final Long UPDATED_ID_CONG_DAN = 2L;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/don-vi-scan-qrs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idLuotQuet}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DonViScanQrRepository donViScanQrRepository;

    @Autowired
    private DonViScanQrMapper donViScanQrMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDonViScanQrMockMvc;

    private DonViScanQr donViScanQr;

    private DonViScanQr insertedDonViScanQr;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DonViScanQr createEntity() {
        return new DonViScanQr().idCongDan(DEFAULT_ID_CONG_DAN).ngayThaoTac(DEFAULT_NGAY_THAO_TAC);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DonViScanQr createUpdatedEntity() {
        return new DonViScanQr().idCongDan(UPDATED_ID_CONG_DAN).ngayThaoTac(UPDATED_NGAY_THAO_TAC);
    }

    @BeforeEach
    public void initTest() {
        donViScanQr = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDonViScanQr != null) {
            donViScanQrRepository.delete(insertedDonViScanQr);
            insertedDonViScanQr = null;
        }
    }

    @Test
    @Transactional
    void createDonViScanQr() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DonViScanQr
        DonViScanQrDTO donViScanQrDTO = donViScanQrMapper.toDto(donViScanQr);
        var returnedDonViScanQrDTO = om.readValue(
            restDonViScanQrMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(donViScanQrDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DonViScanQrDTO.class
        );

        // Validate the DonViScanQr in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDonViScanQr = donViScanQrMapper.toEntity(returnedDonViScanQrDTO);
        assertDonViScanQrUpdatableFieldsEquals(returnedDonViScanQr, getPersistedDonViScanQr(returnedDonViScanQr));

        insertedDonViScanQr = returnedDonViScanQr;
    }

    @Test
    @Transactional
    void createDonViScanQrWithExistingId() throws Exception {
        // Create the DonViScanQr with an existing ID
        donViScanQr.setIdLuotQuet(1L);
        DonViScanQrDTO donViScanQrDTO = donViScanQrMapper.toDto(donViScanQr);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDonViScanQrMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(donViScanQrDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DonViScanQr in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDonViScanQrs() throws Exception {
        // Initialize the database
        insertedDonViScanQr = donViScanQrRepository.saveAndFlush(donViScanQr);

        // Get all the donViScanQrList
        restDonViScanQrMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idLuotQuet,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idLuotQuet").value(hasItem(donViScanQr.getIdLuotQuet().intValue())))
            .andExpect(jsonPath("$.[*].idCongDan").value(hasItem(DEFAULT_ID_CONG_DAN.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())));
    }

    @Test
    @Transactional
    void getDonViScanQr() throws Exception {
        // Initialize the database
        insertedDonViScanQr = donViScanQrRepository.saveAndFlush(donViScanQr);

        // Get the donViScanQr
        restDonViScanQrMockMvc
            .perform(get(ENTITY_API_URL_ID, donViScanQr.getIdLuotQuet()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idLuotQuet").value(donViScanQr.getIdLuotQuet().intValue()))
            .andExpect(jsonPath("$.idCongDan").value(DEFAULT_ID_CONG_DAN.intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDonViScanQr() throws Exception {
        // Get the donViScanQr
        restDonViScanQrMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDonViScanQr() throws Exception {
        // Initialize the database
        insertedDonViScanQr = donViScanQrRepository.saveAndFlush(donViScanQr);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the donViScanQr
        DonViScanQr updatedDonViScanQr = donViScanQrRepository.findById(donViScanQr.getIdLuotQuet()).orElseThrow();
        // Disconnect from session so that the updates on updatedDonViScanQr are not directly saved in db
        em.detach(updatedDonViScanQr);
        updatedDonViScanQr.idCongDan(UPDATED_ID_CONG_DAN).ngayThaoTac(UPDATED_NGAY_THAO_TAC);
        DonViScanQrDTO donViScanQrDTO = donViScanQrMapper.toDto(updatedDonViScanQr);

        restDonViScanQrMockMvc
            .perform(
                put(ENTITY_API_URL_ID, donViScanQrDTO.getIdLuotQuet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(donViScanQrDTO))
            )
            .andExpect(status().isOk());

        // Validate the DonViScanQr in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDonViScanQrToMatchAllProperties(updatedDonViScanQr);
    }

    @Test
    @Transactional
    void putNonExistingDonViScanQr() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        donViScanQr.setIdLuotQuet(longCount.incrementAndGet());

        // Create the DonViScanQr
        DonViScanQrDTO donViScanQrDTO = donViScanQrMapper.toDto(donViScanQr);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonViScanQrMockMvc
            .perform(
                put(ENTITY_API_URL_ID, donViScanQrDTO.getIdLuotQuet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(donViScanQrDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DonViScanQr in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDonViScanQr() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        donViScanQr.setIdLuotQuet(longCount.incrementAndGet());

        // Create the DonViScanQr
        DonViScanQrDTO donViScanQrDTO = donViScanQrMapper.toDto(donViScanQr);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDonViScanQrMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(donViScanQrDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DonViScanQr in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDonViScanQr() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        donViScanQr.setIdLuotQuet(longCount.incrementAndGet());

        // Create the DonViScanQr
        DonViScanQrDTO donViScanQrDTO = donViScanQrMapper.toDto(donViScanQr);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDonViScanQrMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(donViScanQrDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DonViScanQr in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDonViScanQrWithPatch() throws Exception {
        // Initialize the database
        insertedDonViScanQr = donViScanQrRepository.saveAndFlush(donViScanQr);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the donViScanQr using partial update
        DonViScanQr partialUpdatedDonViScanQr = new DonViScanQr();
        partialUpdatedDonViScanQr.setIdLuotQuet(donViScanQr.getIdLuotQuet());

        partialUpdatedDonViScanQr.idCongDan(UPDATED_ID_CONG_DAN);

        restDonViScanQrMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDonViScanQr.getIdLuotQuet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDonViScanQr))
            )
            .andExpect(status().isOk());

        // Validate the DonViScanQr in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDonViScanQrUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDonViScanQr, donViScanQr),
            getPersistedDonViScanQr(donViScanQr)
        );
    }

    @Test
    @Transactional
    void fullUpdateDonViScanQrWithPatch() throws Exception {
        // Initialize the database
        insertedDonViScanQr = donViScanQrRepository.saveAndFlush(donViScanQr);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the donViScanQr using partial update
        DonViScanQr partialUpdatedDonViScanQr = new DonViScanQr();
        partialUpdatedDonViScanQr.setIdLuotQuet(donViScanQr.getIdLuotQuet());

        partialUpdatedDonViScanQr.idCongDan(UPDATED_ID_CONG_DAN).ngayThaoTac(UPDATED_NGAY_THAO_TAC);

        restDonViScanQrMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDonViScanQr.getIdLuotQuet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDonViScanQr))
            )
            .andExpect(status().isOk());

        // Validate the DonViScanQr in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDonViScanQrUpdatableFieldsEquals(partialUpdatedDonViScanQr, getPersistedDonViScanQr(partialUpdatedDonViScanQr));
    }

    @Test
    @Transactional
    void patchNonExistingDonViScanQr() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        donViScanQr.setIdLuotQuet(longCount.incrementAndGet());

        // Create the DonViScanQr
        DonViScanQrDTO donViScanQrDTO = donViScanQrMapper.toDto(donViScanQr);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonViScanQrMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, donViScanQrDTO.getIdLuotQuet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(donViScanQrDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DonViScanQr in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDonViScanQr() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        donViScanQr.setIdLuotQuet(longCount.incrementAndGet());

        // Create the DonViScanQr
        DonViScanQrDTO donViScanQrDTO = donViScanQrMapper.toDto(donViScanQr);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDonViScanQrMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(donViScanQrDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DonViScanQr in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDonViScanQr() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        donViScanQr.setIdLuotQuet(longCount.incrementAndGet());

        // Create the DonViScanQr
        DonViScanQrDTO donViScanQrDTO = donViScanQrMapper.toDto(donViScanQr);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDonViScanQrMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(donViScanQrDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DonViScanQr in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDonViScanQr() throws Exception {
        // Initialize the database
        insertedDonViScanQr = donViScanQrRepository.saveAndFlush(donViScanQr);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the donViScanQr
        restDonViScanQrMockMvc
            .perform(delete(ENTITY_API_URL_ID, donViScanQr.getIdLuotQuet()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return donViScanQrRepository.count();
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

    protected DonViScanQr getPersistedDonViScanQr(DonViScanQr donViScanQr) {
        return donViScanQrRepository.findById(donViScanQr.getIdLuotQuet()).orElseThrow();
    }

    protected void assertPersistedDonViScanQrToMatchAllProperties(DonViScanQr expectedDonViScanQr) {
        assertDonViScanQrAllPropertiesEquals(expectedDonViScanQr, getPersistedDonViScanQr(expectedDonViScanQr));
    }

    protected void assertPersistedDonViScanQrToMatchUpdatableProperties(DonViScanQr expectedDonViScanQr) {
        assertDonViScanQrAllUpdatablePropertiesEquals(expectedDonViScanQr, getPersistedDonViScanQr(expectedDonViScanQr));
    }
}
