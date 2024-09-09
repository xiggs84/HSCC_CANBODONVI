package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DuongSuAsserts.*;
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
import vn.vnpt.domain.DuongSu;
import vn.vnpt.domain.enumeration.LoaiDuongSu;
import vn.vnpt.domain.enumeration.LoaiGiayTo;
import vn.vnpt.repository.DuongSuRepository;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.service.mapper.DuongSuMapper;

/**
 * Integration tests for the {@link DuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DuongSuResourceIT {

    private static final String DEFAULT_TEN_DUONG_SU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DUONG_SU = "BBBBBBBBBB";

    private static final LoaiDuongSu DEFAULT_LOAI_DUONG_SU = LoaiDuongSu.CaNhan;
    private static final LoaiDuongSu UPDATED_LOAI_DUONG_SU = LoaiDuongSu.ToChuc;

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

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

    private static final LoaiGiayTo DEFAULT_LOAI_GIAY_TO = LoaiGiayTo.Cmnd;
    private static final LoaiGiayTo UPDATED_LOAI_GIAY_TO = LoaiGiayTo.Cccd;

    private static final String DEFAULT_SO_GIAY_TO = "AAAAAAAAAA";
    private static final String UPDATED_SO_GIAY_TO = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_ID_LOAI_NGAN_CHAN = 2L;

    private static final Integer DEFAULT_SYNC_STATUS = 0;
    private static final Integer UPDATED_SYNC_STATUS = 1;

    private static final String ENTITY_API_URL = "/api/duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idDuongSu}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DuongSuRepository duongSuRepository;

    @Autowired
    private DuongSuMapper duongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDuongSuMockMvc;

    private DuongSu duongSu;

    private DuongSu insertedDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuongSu createEntity() {
        return new DuongSu()
            .tenDuongSu(DEFAULT_TEN_DUONG_SU)
            .loaiDuongSu(DEFAULT_LOAI_DUONG_SU)
            .diaChi(DEFAULT_DIA_CHI)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI)
            .email(DEFAULT_EMAIL)
            .fax(DEFAULT_FAX)
            .website(DEFAULT_WEBSITE)
            .trangThai(DEFAULT_TRANG_THAI)
            .thongTinDs(DEFAULT_THONG_TIN_DS)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idDsGoc(DEFAULT_ID_DS_GOC)
            .idMaster(DEFAULT_ID_MASTER)
            .idDonVi(DEFAULT_ID_DON_VI)
            .strSearch(DEFAULT_STR_SEARCH)
            .loaiGiayTo(DEFAULT_LOAI_GIAY_TO)
            .soGiayTo(DEFAULT_SO_GIAY_TO)
            .ghiChu(DEFAULT_GHI_CHU)
            .idLoaiNganChan(DEFAULT_ID_LOAI_NGAN_CHAN)
            .syncStatus(DEFAULT_SYNC_STATUS);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuongSu createUpdatedEntity() {
        return new DuongSu()
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .loaiDuongSu(UPDATED_LOAI_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .website(UPDATED_WEBSITE)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .loaiGiayTo(UPDATED_LOAI_GIAY_TO)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .ghiChu(UPDATED_GHI_CHU)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .syncStatus(UPDATED_SYNC_STATUS);
    }

    @BeforeEach
    public void initTest() {
        duongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDuongSu != null) {
            duongSuRepository.delete(insertedDuongSu);
            insertedDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);
        var returnedDuongSuDTO = om.readValue(
            restDuongSuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DuongSuDTO.class
        );

        // Validate the DuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDuongSu = duongSuMapper.toEntity(returnedDuongSuDTO);
        assertDuongSuUpdatableFieldsEquals(returnedDuongSu, getPersistedDuongSu(returnedDuongSu));

        insertedDuongSu = returnedDuongSu;
    }

    @Test
    @Transactional
    void createDuongSuWithExistingId() throws Exception {
        // Create the DuongSu with an existing ID
        duongSu.setIdDuongSu(1L);
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDuongSus() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList
        restDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDuongSu,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(duongSu.getIdDuongSu().intValue())))
            .andExpect(jsonPath("$.[*].tenDuongSu").value(hasItem(DEFAULT_TEN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].loaiDuongSu").value(hasItem(DEFAULT_LOAI_DUONG_SU.toString())))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].thongTinDs").value(hasItem(DEFAULT_THONG_TIN_DS.toString())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDsGoc").value(hasItem(DEFAULT_ID_DS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].loaiGiayTo").value(hasItem(DEFAULT_LOAI_GIAY_TO.toString())))
            .andExpect(jsonPath("$.[*].soGiayTo").value(hasItem(DEFAULT_SO_GIAY_TO)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())))
            .andExpect(jsonPath("$.[*].syncStatus").value(hasItem(DEFAULT_SYNC_STATUS)));
    }

    @Test
    @Transactional
    void getDuongSu() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get the duongSu
        restDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, duongSu.getIdDuongSu()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idDuongSu").value(duongSu.getIdDuongSu().intValue()))
            .andExpect(jsonPath("$.tenDuongSu").value(DEFAULT_TEN_DUONG_SU))
            .andExpect(jsonPath("$.loaiDuongSu").value(DEFAULT_LOAI_DUONG_SU.toString()))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI))
            .andExpect(jsonPath("$.thongTinDs").value(DEFAULT_THONG_TIN_DS.toString()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idDsGoc").value(DEFAULT_ID_DS_GOC.intValue()))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.loaiGiayTo").value(DEFAULT_LOAI_GIAY_TO.toString()))
            .andExpect(jsonPath("$.soGiayTo").value(DEFAULT_SO_GIAY_TO))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU))
            .andExpect(jsonPath("$.idLoaiNganChan").value(DEFAULT_ID_LOAI_NGAN_CHAN.intValue()))
            .andExpect(jsonPath("$.syncStatus").value(DEFAULT_SYNC_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingDuongSu() throws Exception {
        // Get the duongSu
        restDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDuongSu() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSu
        DuongSu updatedDuongSu = duongSuRepository.findById(duongSu.getIdDuongSu()).orElseThrow();
        // Disconnect from session so that the updates on updatedDuongSu are not directly saved in db
        em.detach(updatedDuongSu);
        updatedDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .loaiDuongSu(UPDATED_LOAI_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .website(UPDATED_WEBSITE)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .loaiGiayTo(UPDATED_LOAI_GIAY_TO)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .ghiChu(UPDATED_GHI_CHU)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .syncStatus(UPDATED_SYNC_STATUS);
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(updatedDuongSu);

        restDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, duongSuDTO.getIdDuongSu())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDuongSuToMatchAllProperties(updatedDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, duongSuDTO.getIdDuongSu())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSu using partial update
        DuongSu partialUpdatedDuongSu = new DuongSu();
        partialUpdatedDuongSu.setIdDuongSu(duongSu.getIdDuongSu());

        partialUpdatedDuongSu
            .loaiDuongSu(UPDATED_LOAI_DUONG_SU)
            .email(UPDATED_EMAIL)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .loaiGiayTo(UPDATED_LOAI_GIAY_TO)
            .ghiChu(UPDATED_GHI_CHU);

        restDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDuongSu.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDuongSuUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedDuongSu, duongSu), getPersistedDuongSu(duongSu));
    }

    @Test
    @Transactional
    void fullUpdateDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSu using partial update
        DuongSu partialUpdatedDuongSu = new DuongSu();
        partialUpdatedDuongSu.setIdDuongSu(duongSu.getIdDuongSu());

        partialUpdatedDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .loaiDuongSu(UPDATED_LOAI_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .website(UPDATED_WEBSITE)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .loaiGiayTo(UPDATED_LOAI_GIAY_TO)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .ghiChu(UPDATED_GHI_CHU)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .syncStatus(UPDATED_SYNC_STATUS);

        restDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDuongSu.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDuongSuUpdatableFieldsEquals(partialUpdatedDuongSu, getPersistedDuongSu(partialUpdatedDuongSu));
    }

    @Test
    @Transactional
    void patchNonExistingDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, duongSuDTO.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(duongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(duongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(duongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDuongSu() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the duongSu
        restDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, duongSu.getIdDuongSu()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return duongSuRepository.count();
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

    protected DuongSu getPersistedDuongSu(DuongSu duongSu) {
        return duongSuRepository.findById(duongSu.getIdDuongSu()).orElseThrow();
    }

    protected void assertPersistedDuongSuToMatchAllProperties(DuongSu expectedDuongSu) {
        assertDuongSuAllPropertiesEquals(expectedDuongSu, getPersistedDuongSu(expectedDuongSu));
    }

    protected void assertPersistedDuongSuToMatchUpdatableProperties(DuongSu expectedDuongSu) {
        assertDuongSuAllUpdatablePropertiesEquals(expectedDuongSu, getPersistedDuongSu(expectedDuongSu));
    }
}
