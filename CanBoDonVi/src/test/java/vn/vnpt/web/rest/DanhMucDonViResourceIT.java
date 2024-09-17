package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucDonViAsserts.*;
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
import vn.vnpt.domain.DanhMucDonVi;
import vn.vnpt.repository.DanhMucDonViRepository;
import vn.vnpt.service.dto.DanhMucDonViDTO;
import vn.vnpt.service.mapper.DanhMucDonViMapper;

/**
 * Integration tests for the {@link DanhMucDonViResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucDonViResourceIT {

    private static final String DEFAULT_TEN_DON_VI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DON_VI = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_NGUOI_DAI_DIEN = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_DAI_DIEN = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DON_VI_QL = 1L;
    private static final Long UPDATED_ID_DON_VI_QL = 2L;

    private static final LocalDate DEFAULT_NGAY_KHAI_BAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KHAI_BAO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String DEFAULT_SO_NHA = "AAAAAAAAAA";
    private static final String UPDATED_SO_NHA = "BBBBBBBBBB";

    private static final String DEFAULT_MA_SO_THUE = "AAAAAAAAAA";
    private static final String UPDATED_MA_SO_THUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_HOA_DON_DT = 1;
    private static final Integer UPDATED_HOA_DON_DT = 2;

    private static final String DEFAULT_MA_DON_VI_IGATE = "AAAAAAAAAA";
    private static final String UPDATED_MA_DON_VI_IGATE = "BBBBBBBBBB";

    private static final String DEFAULT_MA_CO_QUAN_IGATE = "AAAAAAAAAA";
    private static final String UPDATED_MA_CO_QUAN_IGATE = "BBBBBBBBBB";

    private static final Long DEFAULT_KY_SO = 1L;
    private static final Long UPDATED_KY_SO = 2L;

    private static final Long DEFAULT_QR_SCAN = 1L;
    private static final Long UPDATED_QR_SCAN = 2L;

    private static final Long DEFAULT_VERIFY_ID_CARD = 1L;
    private static final Long UPDATED_VERIFY_ID_CARD = 2L;

    private static final Long DEFAULT_IS_VERIFY_FACE = 1L;
    private static final Long UPDATED_IS_VERIFY_FACE = 2L;

    private static final Long DEFAULT_IS_ELASTIC = 1L;
    private static final Long UPDATED_IS_ELASTIC = 2L;

    private static final String DEFAULT_APIKEY_CCCD = "AAAAAAAAAA";
    private static final String UPDATED_APIKEY_CCCD = "BBBBBBBBBB";

    private static final String DEFAULT_APIKEY_FACE = "AAAAAAAAAA";
    private static final String UPDATED_APIKEY_FACE = "BBBBBBBBBB";

    private static final String DEFAULT_VERIFY_CODE_CCCD = "AAAAAAAAAA";
    private static final String UPDATED_VERIFY_CODE_CCCD = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME_ELASTIC = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME_ELASTIC = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD_ELASTIC = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD_ELASTIC = "BBBBBBBBBB";

    private static final String DEFAULT_ID_NHIEM_VU = "AAAAAAAAAA";
    private static final String UPDATED_ID_NHIEM_VU = "BBBBBBBBBB";

    private static final String DEFAULT_ID_LOAI_DV = "AAAAAAAAAA";
    private static final String UPDATED_ID_LOAI_DV = "BBBBBBBBBB";

    private static final String DEFAULT_ID_CAP_QL = "AAAAAAAAAA";
    private static final String UPDATED_ID_CAP_QL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-don-vis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idDonVi}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucDonViRepository danhMucDonViRepository;

    @Autowired
    private DanhMucDonViMapper danhMucDonViMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucDonViMockMvc;

    private DanhMucDonVi danhMucDonVi;

    private DanhMucDonVi insertedDanhMucDonVi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucDonVi createEntity() {
        return new DanhMucDonVi()
            .tenDonVi(DEFAULT_TEN_DON_VI)
            .diaChi(DEFAULT_DIA_CHI)
            .nguoiDaiDien(DEFAULT_NGUOI_DAI_DIEN)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI)
            .idDonViQl(DEFAULT_ID_DON_VI_QL)
            .ngayKhaiBao(DEFAULT_NGAY_KHAI_BAO)
            .trangThai(DEFAULT_TRANG_THAI)
            .soNha(DEFAULT_SO_NHA)
            .maSoThue(DEFAULT_MA_SO_THUE)
            .hoaDonDt(DEFAULT_HOA_DON_DT)
            .maDonViIgate(DEFAULT_MA_DON_VI_IGATE)
            .maCoQuanIgate(DEFAULT_MA_CO_QUAN_IGATE)
            .kySo(DEFAULT_KY_SO)
            .qrScan(DEFAULT_QR_SCAN)
            .verifyIdCard(DEFAULT_VERIFY_ID_CARD)
            .isVerifyFace(DEFAULT_IS_VERIFY_FACE)
            .isElastic(DEFAULT_IS_ELASTIC)
            .apikeyCccd(DEFAULT_APIKEY_CCCD)
            .apikeyFace(DEFAULT_APIKEY_FACE)
            .verifyCodeCccd(DEFAULT_VERIFY_CODE_CCCD)
            .usernameElastic(DEFAULT_USERNAME_ELASTIC)
            .passwordElastic(DEFAULT_PASSWORD_ELASTIC)
            .idNhiemVu(DEFAULT_ID_NHIEM_VU)
            .idLoaiDv(DEFAULT_ID_LOAI_DV)
            .idCapQl(DEFAULT_ID_CAP_QL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucDonVi createUpdatedEntity() {
        return new DanhMucDonVi()
            .tenDonVi(UPDATED_TEN_DON_VI)
            .diaChi(UPDATED_DIA_CHI)
            .nguoiDaiDien(UPDATED_NGUOI_DAI_DIEN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .idDonViQl(UPDATED_ID_DON_VI_QL)
            .ngayKhaiBao(UPDATED_NGAY_KHAI_BAO)
            .trangThai(UPDATED_TRANG_THAI)
            .soNha(UPDATED_SO_NHA)
            .maSoThue(UPDATED_MA_SO_THUE)
            .hoaDonDt(UPDATED_HOA_DON_DT)
            .maDonViIgate(UPDATED_MA_DON_VI_IGATE)
            .maCoQuanIgate(UPDATED_MA_CO_QUAN_IGATE)
            .kySo(UPDATED_KY_SO)
            .qrScan(UPDATED_QR_SCAN)
            .verifyIdCard(UPDATED_VERIFY_ID_CARD)
            .isVerifyFace(UPDATED_IS_VERIFY_FACE)
            .isElastic(UPDATED_IS_ELASTIC)
            .apikeyCccd(UPDATED_APIKEY_CCCD)
            .apikeyFace(UPDATED_APIKEY_FACE)
            .verifyCodeCccd(UPDATED_VERIFY_CODE_CCCD)
            .usernameElastic(UPDATED_USERNAME_ELASTIC)
            .passwordElastic(UPDATED_PASSWORD_ELASTIC)
            .idNhiemVu(UPDATED_ID_NHIEM_VU)
            .idLoaiDv(UPDATED_ID_LOAI_DV)
            .idCapQl(UPDATED_ID_CAP_QL);
    }

    @BeforeEach
    public void initTest() {
        danhMucDonVi = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucDonVi != null) {
            danhMucDonViRepository.delete(insertedDanhMucDonVi);
            insertedDanhMucDonVi = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucDonVi() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucDonVi
        DanhMucDonViDTO danhMucDonViDTO = danhMucDonViMapper.toDto(danhMucDonVi);
        var returnedDanhMucDonViDTO = om.readValue(
            restDanhMucDonViMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucDonViDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucDonViDTO.class
        );

        // Validate the DanhMucDonVi in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucDonVi = danhMucDonViMapper.toEntity(returnedDanhMucDonViDTO);
        assertDanhMucDonViUpdatableFieldsEquals(returnedDanhMucDonVi, getPersistedDanhMucDonVi(returnedDanhMucDonVi));

        insertedDanhMucDonVi = returnedDanhMucDonVi;
    }

    @Test
    @Transactional
    void createDanhMucDonViWithExistingId() throws Exception {
        // Create the DanhMucDonVi with an existing ID
        danhMucDonVi.setIdDonVi(1L);
        DanhMucDonViDTO danhMucDonViDTO = danhMucDonViMapper.toDto(danhMucDonVi);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucDonViMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucDonViDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVis() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList
        restDanhMucDonViMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDonVi,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(danhMucDonVi.getIdDonVi().intValue())))
            .andExpect(jsonPath("$.[*].tenDonVi").value(hasItem(DEFAULT_TEN_DON_VI)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].nguoiDaiDien").value(hasItem(DEFAULT_NGUOI_DAI_DIEN)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].idDonViQl").value(hasItem(DEFAULT_ID_DON_VI_QL.intValue())))
            .andExpect(jsonPath("$.[*].ngayKhaiBao").value(hasItem(DEFAULT_NGAY_KHAI_BAO.toString())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].soNha").value(hasItem(DEFAULT_SO_NHA)))
            .andExpect(jsonPath("$.[*].maSoThue").value(hasItem(DEFAULT_MA_SO_THUE)))
            .andExpect(jsonPath("$.[*].hoaDonDt").value(hasItem(DEFAULT_HOA_DON_DT)))
            .andExpect(jsonPath("$.[*].maDonViIgate").value(hasItem(DEFAULT_MA_DON_VI_IGATE)))
            .andExpect(jsonPath("$.[*].maCoQuanIgate").value(hasItem(DEFAULT_MA_CO_QUAN_IGATE)))
            .andExpect(jsonPath("$.[*].kySo").value(hasItem(DEFAULT_KY_SO.intValue())))
            .andExpect(jsonPath("$.[*].qrScan").value(hasItem(DEFAULT_QR_SCAN.intValue())))
            .andExpect(jsonPath("$.[*].verifyIdCard").value(hasItem(DEFAULT_VERIFY_ID_CARD.intValue())))
            .andExpect(jsonPath("$.[*].isVerifyFace").value(hasItem(DEFAULT_IS_VERIFY_FACE.intValue())))
            .andExpect(jsonPath("$.[*].isElastic").value(hasItem(DEFAULT_IS_ELASTIC.intValue())))
            .andExpect(jsonPath("$.[*].apikeyCccd").value(hasItem(DEFAULT_APIKEY_CCCD)))
            .andExpect(jsonPath("$.[*].apikeyFace").value(hasItem(DEFAULT_APIKEY_FACE)))
            .andExpect(jsonPath("$.[*].verifyCodeCccd").value(hasItem(DEFAULT_VERIFY_CODE_CCCD)))
            .andExpect(jsonPath("$.[*].usernameElastic").value(hasItem(DEFAULT_USERNAME_ELASTIC)))
            .andExpect(jsonPath("$.[*].passwordElastic").value(hasItem(DEFAULT_PASSWORD_ELASTIC)))
            .andExpect(jsonPath("$.[*].idNhiemVu").value(hasItem(DEFAULT_ID_NHIEM_VU)))
            .andExpect(jsonPath("$.[*].idLoaiDv").value(hasItem(DEFAULT_ID_LOAI_DV)))
            .andExpect(jsonPath("$.[*].idCapQl").value(hasItem(DEFAULT_ID_CAP_QL)));
    }

    @Test
    @Transactional
    void getDanhMucDonVi() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get the danhMucDonVi
        restDanhMucDonViMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucDonVi.getIdDonVi()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idDonVi").value(danhMucDonVi.getIdDonVi().intValue()))
            .andExpect(jsonPath("$.tenDonVi").value(DEFAULT_TEN_DON_VI))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.nguoiDaiDien").value(DEFAULT_NGUOI_DAI_DIEN))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI))
            .andExpect(jsonPath("$.idDonViQl").value(DEFAULT_ID_DON_VI_QL.intValue()))
            .andExpect(jsonPath("$.ngayKhaiBao").value(DEFAULT_NGAY_KHAI_BAO.toString()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.soNha").value(DEFAULT_SO_NHA))
            .andExpect(jsonPath("$.maSoThue").value(DEFAULT_MA_SO_THUE))
            .andExpect(jsonPath("$.hoaDonDt").value(DEFAULT_HOA_DON_DT))
            .andExpect(jsonPath("$.maDonViIgate").value(DEFAULT_MA_DON_VI_IGATE))
            .andExpect(jsonPath("$.maCoQuanIgate").value(DEFAULT_MA_CO_QUAN_IGATE))
            .andExpect(jsonPath("$.kySo").value(DEFAULT_KY_SO.intValue()))
            .andExpect(jsonPath("$.qrScan").value(DEFAULT_QR_SCAN.intValue()))
            .andExpect(jsonPath("$.verifyIdCard").value(DEFAULT_VERIFY_ID_CARD.intValue()))
            .andExpect(jsonPath("$.isVerifyFace").value(DEFAULT_IS_VERIFY_FACE.intValue()))
            .andExpect(jsonPath("$.isElastic").value(DEFAULT_IS_ELASTIC.intValue()))
            .andExpect(jsonPath("$.apikeyCccd").value(DEFAULT_APIKEY_CCCD))
            .andExpect(jsonPath("$.apikeyFace").value(DEFAULT_APIKEY_FACE))
            .andExpect(jsonPath("$.verifyCodeCccd").value(DEFAULT_VERIFY_CODE_CCCD))
            .andExpect(jsonPath("$.usernameElastic").value(DEFAULT_USERNAME_ELASTIC))
            .andExpect(jsonPath("$.passwordElastic").value(DEFAULT_PASSWORD_ELASTIC))
            .andExpect(jsonPath("$.idNhiemVu").value(DEFAULT_ID_NHIEM_VU))
            .andExpect(jsonPath("$.idLoaiDv").value(DEFAULT_ID_LOAI_DV))
            .andExpect(jsonPath("$.idCapQl").value(DEFAULT_ID_CAP_QL));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucDonVi() throws Exception {
        // Get the danhMucDonVi
        restDanhMucDonViMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucDonVi() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucDonVi
        DanhMucDonVi updatedDanhMucDonVi = danhMucDonViRepository.findById(danhMucDonVi.getIdDonVi()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucDonVi are not directly saved in db
        em.detach(updatedDanhMucDonVi);
        updatedDanhMucDonVi
            .tenDonVi(UPDATED_TEN_DON_VI)
            .diaChi(UPDATED_DIA_CHI)
            .nguoiDaiDien(UPDATED_NGUOI_DAI_DIEN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .idDonViQl(UPDATED_ID_DON_VI_QL)
            .ngayKhaiBao(UPDATED_NGAY_KHAI_BAO)
            .trangThai(UPDATED_TRANG_THAI)
            .soNha(UPDATED_SO_NHA)
            .maSoThue(UPDATED_MA_SO_THUE)
            .hoaDonDt(UPDATED_HOA_DON_DT)
            .maDonViIgate(UPDATED_MA_DON_VI_IGATE)
            .maCoQuanIgate(UPDATED_MA_CO_QUAN_IGATE)
            .kySo(UPDATED_KY_SO)
            .qrScan(UPDATED_QR_SCAN)
            .verifyIdCard(UPDATED_VERIFY_ID_CARD)
            .isVerifyFace(UPDATED_IS_VERIFY_FACE)
            .isElastic(UPDATED_IS_ELASTIC)
            .apikeyCccd(UPDATED_APIKEY_CCCD)
            .apikeyFace(UPDATED_APIKEY_FACE)
            .verifyCodeCccd(UPDATED_VERIFY_CODE_CCCD)
            .usernameElastic(UPDATED_USERNAME_ELASTIC)
            .passwordElastic(UPDATED_PASSWORD_ELASTIC)
            .idNhiemVu(UPDATED_ID_NHIEM_VU)
            .idLoaiDv(UPDATED_ID_LOAI_DV)
            .idCapQl(UPDATED_ID_CAP_QL);
        DanhMucDonViDTO danhMucDonViDTO = danhMucDonViMapper.toDto(updatedDanhMucDonVi);

        restDanhMucDonViMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucDonViDTO.getIdDonVi())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucDonViDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucDonViToMatchAllProperties(updatedDanhMucDonVi);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDonVi.setIdDonVi(longCount.incrementAndGet());

        // Create the DanhMucDonVi
        DanhMucDonViDTO danhMucDonViDTO = danhMucDonViMapper.toDto(danhMucDonVi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucDonViMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucDonViDTO.getIdDonVi())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucDonViDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDonVi.setIdDonVi(longCount.incrementAndGet());

        // Create the DanhMucDonVi
        DanhMucDonViDTO danhMucDonViDTO = danhMucDonViMapper.toDto(danhMucDonVi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucDonViMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucDonViDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDonVi.setIdDonVi(longCount.incrementAndGet());

        // Create the DanhMucDonVi
        DanhMucDonViDTO danhMucDonViDTO = danhMucDonViMapper.toDto(danhMucDonVi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucDonViMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucDonViDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucDonViWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucDonVi using partial update
        DanhMucDonVi partialUpdatedDanhMucDonVi = new DanhMucDonVi();
        partialUpdatedDanhMucDonVi.setIdDonVi(danhMucDonVi.getIdDonVi());

        partialUpdatedDanhMucDonVi
            .nguoiDaiDien(UPDATED_NGUOI_DAI_DIEN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .ngayKhaiBao(UPDATED_NGAY_KHAI_BAO)
            .maCoQuanIgate(UPDATED_MA_CO_QUAN_IGATE)
            .qrScan(UPDATED_QR_SCAN)
            .isVerifyFace(UPDATED_IS_VERIFY_FACE)
            .apikeyCccd(UPDATED_APIKEY_CCCD)
            .apikeyFace(UPDATED_APIKEY_FACE)
            .usernameElastic(UPDATED_USERNAME_ELASTIC)
            .idCapQl(UPDATED_ID_CAP_QL);

        restDanhMucDonViMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucDonVi.getIdDonVi())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucDonVi))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucDonVi in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucDonViUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucDonVi, danhMucDonVi),
            getPersistedDanhMucDonVi(danhMucDonVi)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucDonViWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucDonVi using partial update
        DanhMucDonVi partialUpdatedDanhMucDonVi = new DanhMucDonVi();
        partialUpdatedDanhMucDonVi.setIdDonVi(danhMucDonVi.getIdDonVi());

        partialUpdatedDanhMucDonVi
            .tenDonVi(UPDATED_TEN_DON_VI)
            .diaChi(UPDATED_DIA_CHI)
            .nguoiDaiDien(UPDATED_NGUOI_DAI_DIEN)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .idDonViQl(UPDATED_ID_DON_VI_QL)
            .ngayKhaiBao(UPDATED_NGAY_KHAI_BAO)
            .trangThai(UPDATED_TRANG_THAI)
            .soNha(UPDATED_SO_NHA)
            .maSoThue(UPDATED_MA_SO_THUE)
            .hoaDonDt(UPDATED_HOA_DON_DT)
            .maDonViIgate(UPDATED_MA_DON_VI_IGATE)
            .maCoQuanIgate(UPDATED_MA_CO_QUAN_IGATE)
            .kySo(UPDATED_KY_SO)
            .qrScan(UPDATED_QR_SCAN)
            .verifyIdCard(UPDATED_VERIFY_ID_CARD)
            .isVerifyFace(UPDATED_IS_VERIFY_FACE)
            .isElastic(UPDATED_IS_ELASTIC)
            .apikeyCccd(UPDATED_APIKEY_CCCD)
            .apikeyFace(UPDATED_APIKEY_FACE)
            .verifyCodeCccd(UPDATED_VERIFY_CODE_CCCD)
            .usernameElastic(UPDATED_USERNAME_ELASTIC)
            .passwordElastic(UPDATED_PASSWORD_ELASTIC)
            .idNhiemVu(UPDATED_ID_NHIEM_VU)
            .idLoaiDv(UPDATED_ID_LOAI_DV)
            .idCapQl(UPDATED_ID_CAP_QL);

        restDanhMucDonViMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucDonVi.getIdDonVi())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucDonVi))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucDonVi in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucDonViUpdatableFieldsEquals(partialUpdatedDanhMucDonVi, getPersistedDanhMucDonVi(partialUpdatedDanhMucDonVi));
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDonVi.setIdDonVi(longCount.incrementAndGet());

        // Create the DanhMucDonVi
        DanhMucDonViDTO danhMucDonViDTO = danhMucDonViMapper.toDto(danhMucDonVi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucDonViMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucDonViDTO.getIdDonVi())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucDonViDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDonVi.setIdDonVi(longCount.incrementAndGet());

        // Create the DanhMucDonVi
        DanhMucDonViDTO danhMucDonViDTO = danhMucDonViMapper.toDto(danhMucDonVi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucDonViMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucDonViDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucDonVi() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDonVi.setIdDonVi(longCount.incrementAndGet());

        // Create the DanhMucDonVi
        DanhMucDonViDTO danhMucDonViDTO = danhMucDonViMapper.toDto(danhMucDonVi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucDonViMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucDonViDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucDonVi in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucDonVi() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucDonVi
        restDanhMucDonViMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucDonVi.getIdDonVi()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucDonViRepository.count();
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

    protected DanhMucDonVi getPersistedDanhMucDonVi(DanhMucDonVi danhMucDonVi) {
        return danhMucDonViRepository.findById(danhMucDonVi.getIdDonVi()).orElseThrow();
    }

    protected void assertPersistedDanhMucDonViToMatchAllProperties(DanhMucDonVi expectedDanhMucDonVi) {
        assertDanhMucDonViAllPropertiesEquals(expectedDanhMucDonVi, getPersistedDanhMucDonVi(expectedDanhMucDonVi));
    }

    protected void assertPersistedDanhMucDonViToMatchUpdatableProperties(DanhMucDonVi expectedDanhMucDonVi) {
        assertDanhMucDonViAllUpdatablePropertiesEquals(expectedDanhMucDonVi, getPersistedDanhMucDonVi(expectedDanhMucDonVi));
    }
}
