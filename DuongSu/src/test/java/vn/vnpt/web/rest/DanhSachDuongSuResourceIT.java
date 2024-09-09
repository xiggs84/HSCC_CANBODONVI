package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhSachDuongSuAsserts.*;
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
import vn.vnpt.domain.DanhSachDuongSu;
import vn.vnpt.repository.DanhSachDuongSuRepository;
import vn.vnpt.service.dto.DanhSachDuongSuDTO;
import vn.vnpt.service.mapper.DanhSachDuongSuMapper;

/**
 * Integration tests for the {@link DanhSachDuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhSachDuongSuResourceIT {

    private static final String DEFAULT_TEN_DUONG_SU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DUONG_SU = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANG_THAI = 0;
    private static final Integer UPDATED_TRANG_THAI = 1;

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

    private static final String ENTITY_API_URL = "/api/danh-sach-duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhSachDuongSuRepository danhSachDuongSuRepository;

    @Autowired
    private DanhSachDuongSuMapper danhSachDuongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhSachDuongSuMockMvc;

    private DanhSachDuongSu danhSachDuongSu;

    private DanhSachDuongSu insertedDanhSachDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhSachDuongSu createEntity() {
        return new DanhSachDuongSu()
            .tenDuongSu(DEFAULT_TEN_DUONG_SU)
            .diaChi(DEFAULT_DIA_CHI)
            .trangThai(DEFAULT_TRANG_THAI)
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
    public static DanhSachDuongSu createUpdatedEntity() {
        return new DanhSachDuongSu()
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
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
        danhSachDuongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhSachDuongSu != null) {
            danhSachDuongSuRepository.delete(insertedDanhSachDuongSu);
            insertedDanhSachDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);
        var returnedDanhSachDuongSuDTO = om.readValue(
            restDanhSachDuongSuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachDuongSuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhSachDuongSuDTO.class
        );

        // Validate the DanhSachDuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhSachDuongSu = danhSachDuongSuMapper.toEntity(returnedDanhSachDuongSuDTO);
        assertDanhSachDuongSuUpdatableFieldsEquals(returnedDanhSachDuongSu, getPersistedDanhSachDuongSu(returnedDanhSachDuongSu));

        insertedDanhSachDuongSu = returnedDanhSachDuongSu;
    }

    @Test
    @Transactional
    void createDanhSachDuongSuWithExistingId() throws Exception {
        // Create the DanhSachDuongSu with an existing ID
        danhSachDuongSu.setId(1L);
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhSachDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachDuongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSus() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList
        restDanhSachDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhSachDuongSu.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenDuongSu").value(hasItem(DEFAULT_TEN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
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
    void getDanhSachDuongSu() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get the danhSachDuongSu
        restDanhSachDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, danhSachDuongSu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhSachDuongSu.getId().intValue()))
            .andExpect(jsonPath("$.tenDuongSu").value(DEFAULT_TEN_DUONG_SU))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI))
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
    void getNonExistingDanhSachDuongSu() throws Exception {
        // Get the danhSachDuongSu
        restDanhSachDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhSachDuongSu() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachDuongSu
        DanhSachDuongSu updatedDanhSachDuongSu = danhSachDuongSuRepository.findById(danhSachDuongSu.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhSachDuongSu are not directly saved in db
        em.detach(updatedDanhSachDuongSu);
        updatedDanhSachDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(updatedDanhSachDuongSu);

        restDanhSachDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhSachDuongSuDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachDuongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhSachDuongSuToMatchAllProperties(updatedDanhSachDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhSachDuongSuDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhSachDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachDuongSu using partial update
        DanhSachDuongSu partialUpdatedDanhSachDuongSu = new DanhSachDuongSu();
        partialUpdatedDanhSachDuongSu.setId(danhSachDuongSu.getId());

        partialUpdatedDanhSachDuongSu
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO);

        restDanhSachDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhSachDuongSu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhSachDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhSachDuongSuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhSachDuongSu, danhSachDuongSu),
            getPersistedDanhSachDuongSu(danhSachDuongSu)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhSachDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachDuongSu using partial update
        DanhSachDuongSu partialUpdatedDanhSachDuongSu = new DanhSachDuongSu();
        partialUpdatedDanhSachDuongSu.setId(danhSachDuongSu.getId());

        partialUpdatedDanhSachDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);

        restDanhSachDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhSachDuongSu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhSachDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhSachDuongSuUpdatableFieldsEquals(
            partialUpdatedDanhSachDuongSu,
            getPersistedDanhSachDuongSu(partialUpdatedDanhSachDuongSu)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhSachDuongSuDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhSachDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhSachDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhSachDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhSachDuongSu() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhSachDuongSu
        restDanhSachDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhSachDuongSu.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhSachDuongSuRepository.count();
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

    protected DanhSachDuongSu getPersistedDanhSachDuongSu(DanhSachDuongSu danhSachDuongSu) {
        return danhSachDuongSuRepository.findById(danhSachDuongSu.getId()).orElseThrow();
    }

    protected void assertPersistedDanhSachDuongSuToMatchAllProperties(DanhSachDuongSu expectedDanhSachDuongSu) {
        assertDanhSachDuongSuAllPropertiesEquals(expectedDanhSachDuongSu, getPersistedDanhSachDuongSu(expectedDanhSachDuongSu));
    }

    protected void assertPersistedDanhSachDuongSuToMatchUpdatableProperties(DanhSachDuongSu expectedDanhSachDuongSu) {
        assertDanhSachDuongSuAllUpdatablePropertiesEquals(expectedDanhSachDuongSu, getPersistedDanhSachDuongSu(expectedDanhSachDuongSu));
    }
}
