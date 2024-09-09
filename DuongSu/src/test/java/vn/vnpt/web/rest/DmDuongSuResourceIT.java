package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DmDuongSuAsserts.*;
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
import vn.vnpt.domain.DmDuongSu;
import vn.vnpt.repository.DmDuongSuRepository;
import vn.vnpt.service.dto.DmDuongSuDTO;
import vn.vnpt.service.mapper.DmDuongSuMapper;

/**
 * Integration tests for the {@link DmDuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DmDuongSuResourceIT {

    private static final String DEFAULT_TEN_DUONG_SU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DUONG_SU = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANG_THAI = 0;
    private static final Integer UPDATED_TRANG_THAI = 1;

    private static final String DEFAULT_THONG_TIN_DS = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_DS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final Long DEFAULT_ID_DS_GOC = 1L;
    private static final Long UPDATED_ID_DS_GOC = 2L;

    private static final String DEFAULT_ID_MASTER = "AAAAAAAAAA";
    private static final String UPDATED_ID_MASTER = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final String DEFAULT_STR_SEARCH = "AAAAAAAAAA";
    private static final String UPDATED_STR_SEARCH = "BBBBBBBBBB";

    private static final String DEFAULT_SO_GIAY_TO = "AAAAAAAAAA";
    private static final String UPDATED_SO_GIAY_TO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_ID_LOAI_NGAN_CHAN = 2L;

    private static final String ENTITY_API_URL = "/api/dm-duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idDuongSu}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DmDuongSuRepository dmDuongSuRepository;

    @Autowired
    private DmDuongSuMapper dmDuongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDmDuongSuMockMvc;

    private DmDuongSu dmDuongSu;

    private DmDuongSu insertedDmDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmDuongSu createEntity() {
        return new DmDuongSu()
            .tenDuongSu(DEFAULT_TEN_DUONG_SU)
            .diaChi(DEFAULT_DIA_CHI)
            .trangThai(DEFAULT_TRANG_THAI)
            .thongTinDs(DEFAULT_THONG_TIN_DS)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idDsGoc(DEFAULT_ID_DS_GOC)
            .idMaster(DEFAULT_ID_MASTER)
            .idDonVi(DEFAULT_ID_DON_VI)
            .strSearch(DEFAULT_STR_SEARCH)
            .soGiayTo(DEFAULT_SO_GIAY_TO)
            .idLoaiNganChan(DEFAULT_ID_LOAI_NGAN_CHAN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmDuongSu createUpdatedEntity() {
        return new DmDuongSu()
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);
    }

    @BeforeEach
    public void initTest() {
        dmDuongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDmDuongSu != null) {
            dmDuongSuRepository.delete(insertedDmDuongSu);
            insertedDmDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createDmDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);
        var returnedDmDuongSuDTO = om.readValue(
            restDmDuongSuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmDuongSuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DmDuongSuDTO.class
        );

        // Validate the DmDuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDmDuongSu = dmDuongSuMapper.toEntity(returnedDmDuongSuDTO);
        assertDmDuongSuUpdatableFieldsEquals(returnedDmDuongSu, getPersistedDmDuongSu(returnedDmDuongSu));

        insertedDmDuongSu = returnedDmDuongSu;
    }

    @Test
    @Transactional
    void createDmDuongSuWithExistingId() throws Exception {
        // Create the DmDuongSu with an existing ID
        dmDuongSu.setIdDuongSu(1L);
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmDuongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDmDuongSus() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList
        restDmDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDuongSu,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(dmDuongSu.getIdDuongSu().intValue())))
            .andExpect(jsonPath("$.[*].tenDuongSu").value(hasItem(DEFAULT_TEN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].thongTinDs").value(hasItem(DEFAULT_THONG_TIN_DS)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDsGoc").value(hasItem(DEFAULT_ID_DS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].soGiayTo").value(hasItem(DEFAULT_SO_GIAY_TO)))
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())));
    }

    @Test
    @Transactional
    void getDmDuongSu() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get the dmDuongSu
        restDmDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, dmDuongSu.getIdDuongSu()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idDuongSu").value(dmDuongSu.getIdDuongSu().intValue()))
            .andExpect(jsonPath("$.tenDuongSu").value(DEFAULT_TEN_DUONG_SU))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI))
            .andExpect(jsonPath("$.thongTinDs").value(DEFAULT_THONG_TIN_DS))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idDsGoc").value(DEFAULT_ID_DS_GOC.intValue()))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.soGiayTo").value(DEFAULT_SO_GIAY_TO))
            .andExpect(jsonPath("$.idLoaiNganChan").value(DEFAULT_ID_LOAI_NGAN_CHAN.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDmDuongSu() throws Exception {
        // Get the dmDuongSu
        restDmDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDmDuongSu() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmDuongSu
        DmDuongSu updatedDmDuongSu = dmDuongSuRepository.findById(dmDuongSu.getIdDuongSu()).orElseThrow();
        // Disconnect from session so that the updates on updatedDmDuongSu are not directly saved in db
        em.detach(updatedDmDuongSu);
        updatedDmDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(updatedDmDuongSu);

        restDmDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmDuongSuDTO.getIdDuongSu())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmDuongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDmDuongSuToMatchAllProperties(updatedDmDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmDuongSuDTO.getIdDuongSu())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDmDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmDuongSu using partial update
        DmDuongSu partialUpdatedDmDuongSu = new DmDuongSu();
        partialUpdatedDmDuongSu.setIdDuongSu(dmDuongSu.getIdDuongSu());

        partialUpdatedDmDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO);

        restDmDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmDuongSu.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DmDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmDuongSuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDmDuongSu, dmDuongSu),
            getPersistedDmDuongSu(dmDuongSu)
        );
    }

    @Test
    @Transactional
    void fullUpdateDmDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmDuongSu using partial update
        DmDuongSu partialUpdatedDmDuongSu = new DmDuongSu();
        partialUpdatedDmDuongSu.setIdDuongSu(dmDuongSu.getIdDuongSu());

        partialUpdatedDmDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);

        restDmDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmDuongSu.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DmDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmDuongSuUpdatableFieldsEquals(partialUpdatedDmDuongSu, getPersistedDmDuongSu(partialUpdatedDmDuongSu));
    }

    @Test
    @Transactional
    void patchNonExistingDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dmDuongSuDTO.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dmDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDmDuongSu() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dmDuongSu
        restDmDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, dmDuongSu.getIdDuongSu()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dmDuongSuRepository.count();
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

    protected DmDuongSu getPersistedDmDuongSu(DmDuongSu dmDuongSu) {
        return dmDuongSuRepository.findById(dmDuongSu.getIdDuongSu()).orElseThrow();
    }

    protected void assertPersistedDmDuongSuToMatchAllProperties(DmDuongSu expectedDmDuongSu) {
        assertDmDuongSuAllPropertiesEquals(expectedDmDuongSu, getPersistedDmDuongSu(expectedDmDuongSu));
    }

    protected void assertPersistedDmDuongSuToMatchUpdatableProperties(DmDuongSu expectedDmDuongSu) {
        assertDmDuongSuAllUpdatablePropertiesEquals(expectedDmDuongSu, getPersistedDmDuongSu(expectedDmDuongSu));
    }
}
