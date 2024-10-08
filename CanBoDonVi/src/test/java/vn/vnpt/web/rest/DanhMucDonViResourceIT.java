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
import vn.vnpt.domain.CapQuanLy;
import vn.vnpt.domain.DanhMucDonVi;
import vn.vnpt.domain.LoaiDonVi;
import vn.vnpt.domain.NhiemVu;
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
    private static final Long SMALLER_ID_DON_VI_QL = 1L - 1L;

    private static final LocalDate DEFAULT_NGAY_KHAI_BAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KHAI_BAO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_KHAI_BAO = LocalDate.ofEpochDay(-1L);

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;
    private static final Long SMALLER_TRANG_THAI = 1L - 1L;

    private static final String DEFAULT_SO_NHA = "AAAAAAAAAA";
    private static final String UPDATED_SO_NHA = "BBBBBBBBBB";

    private static final String DEFAULT_MA_SO_THUE = "AAAAAAAAAA";
    private static final String UPDATED_MA_SO_THUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_HOA_DON_DT = 1;
    private static final Integer UPDATED_HOA_DON_DT = 2;
    private static final Integer SMALLER_HOA_DON_DT = 1 - 1;

    private static final String DEFAULT_MA_DON_VI_IGATE = "AAAAAAAAAA";
    private static final String UPDATED_MA_DON_VI_IGATE = "BBBBBBBBBB";

    private static final String DEFAULT_MA_CO_QUAN_IGATE = "AAAAAAAAAA";
    private static final String UPDATED_MA_CO_QUAN_IGATE = "BBBBBBBBBB";

    private static final Long DEFAULT_KY_SO = 1L;
    private static final Long UPDATED_KY_SO = 2L;
    private static final Long SMALLER_KY_SO = 1L - 1L;

    private static final Long DEFAULT_QR_SCAN = 1L;
    private static final Long UPDATED_QR_SCAN = 2L;
    private static final Long SMALLER_QR_SCAN = 1L - 1L;

    private static final Long DEFAULT_VERIFY_ID_CARD = 1L;
    private static final Long UPDATED_VERIFY_ID_CARD = 2L;
    private static final Long SMALLER_VERIFY_ID_CARD = 1L - 1L;

    private static final Long DEFAULT_IS_VERIFY_FACE = 1L;
    private static final Long UPDATED_IS_VERIFY_FACE = 2L;
    private static final Long SMALLER_IS_VERIFY_FACE = 1L - 1L;

    private static final Long DEFAULT_IS_ELASTIC = 1L;
    private static final Long UPDATED_IS_ELASTIC = 2L;
    private static final Long SMALLER_IS_ELASTIC = 1L - 1L;

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
    void getDanhMucDonVisByIdFiltering() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        Long id = danhMucDonVi.getIdDonVi();

        defaultDanhMucDonViFiltering("idDonVi.equals=" + id, "idDonVi.notEquals=" + id);

        defaultDanhMucDonViFiltering("idDonVi.greaterThanOrEqual=" + id, "idDonVi.greaterThan=" + id);

        defaultDanhMucDonViFiltering("idDonVi.lessThanOrEqual=" + id, "idDonVi.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTenDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where tenDonVi equals to
        defaultDanhMucDonViFiltering("tenDonVi.equals=" + DEFAULT_TEN_DON_VI, "tenDonVi.equals=" + UPDATED_TEN_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTenDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where tenDonVi in
        defaultDanhMucDonViFiltering("tenDonVi.in=" + DEFAULT_TEN_DON_VI + "," + UPDATED_TEN_DON_VI, "tenDonVi.in=" + UPDATED_TEN_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTenDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where tenDonVi is not null
        defaultDanhMucDonViFiltering("tenDonVi.specified=true", "tenDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTenDonViContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where tenDonVi contains
        defaultDanhMucDonViFiltering("tenDonVi.contains=" + DEFAULT_TEN_DON_VI, "tenDonVi.contains=" + UPDATED_TEN_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTenDonViNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where tenDonVi does not contain
        defaultDanhMucDonViFiltering("tenDonVi.doesNotContain=" + UPDATED_TEN_DON_VI, "tenDonVi.doesNotContain=" + DEFAULT_TEN_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByDiaChiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where diaChi equals to
        defaultDanhMucDonViFiltering("diaChi.equals=" + DEFAULT_DIA_CHI, "diaChi.equals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByDiaChiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where diaChi in
        defaultDanhMucDonViFiltering("diaChi.in=" + DEFAULT_DIA_CHI + "," + UPDATED_DIA_CHI, "diaChi.in=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByDiaChiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where diaChi is not null
        defaultDanhMucDonViFiltering("diaChi.specified=true", "diaChi.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByDiaChiContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where diaChi contains
        defaultDanhMucDonViFiltering("diaChi.contains=" + DEFAULT_DIA_CHI, "diaChi.contains=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByDiaChiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where diaChi does not contain
        defaultDanhMucDonViFiltering("diaChi.doesNotContain=" + UPDATED_DIA_CHI, "diaChi.doesNotContain=" + DEFAULT_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNguoiDaiDienIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where nguoiDaiDien equals to
        defaultDanhMucDonViFiltering("nguoiDaiDien.equals=" + DEFAULT_NGUOI_DAI_DIEN, "nguoiDaiDien.equals=" + UPDATED_NGUOI_DAI_DIEN);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNguoiDaiDienIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where nguoiDaiDien in
        defaultDanhMucDonViFiltering(
            "nguoiDaiDien.in=" + DEFAULT_NGUOI_DAI_DIEN + "," + UPDATED_NGUOI_DAI_DIEN,
            "nguoiDaiDien.in=" + UPDATED_NGUOI_DAI_DIEN
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNguoiDaiDienIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where nguoiDaiDien is not null
        defaultDanhMucDonViFiltering("nguoiDaiDien.specified=true", "nguoiDaiDien.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNguoiDaiDienContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where nguoiDaiDien contains
        defaultDanhMucDonViFiltering("nguoiDaiDien.contains=" + DEFAULT_NGUOI_DAI_DIEN, "nguoiDaiDien.contains=" + UPDATED_NGUOI_DAI_DIEN);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNguoiDaiDienNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where nguoiDaiDien does not contain
        defaultDanhMucDonViFiltering(
            "nguoiDaiDien.doesNotContain=" + UPDATED_NGUOI_DAI_DIEN,
            "nguoiDaiDien.doesNotContain=" + DEFAULT_NGUOI_DAI_DIEN
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisBySoDienThoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where soDienThoai equals to
        defaultDanhMucDonViFiltering("soDienThoai.equals=" + DEFAULT_SO_DIEN_THOAI, "soDienThoai.equals=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisBySoDienThoaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where soDienThoai in
        defaultDanhMucDonViFiltering(
            "soDienThoai.in=" + DEFAULT_SO_DIEN_THOAI + "," + UPDATED_SO_DIEN_THOAI,
            "soDienThoai.in=" + UPDATED_SO_DIEN_THOAI
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisBySoDienThoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where soDienThoai is not null
        defaultDanhMucDonViFiltering("soDienThoai.specified=true", "soDienThoai.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisBySoDienThoaiContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where soDienThoai contains
        defaultDanhMucDonViFiltering("soDienThoai.contains=" + DEFAULT_SO_DIEN_THOAI, "soDienThoai.contains=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisBySoDienThoaiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where soDienThoai does not contain
        defaultDanhMucDonViFiltering(
            "soDienThoai.doesNotContain=" + UPDATED_SO_DIEN_THOAI,
            "soDienThoai.doesNotContain=" + DEFAULT_SO_DIEN_THOAI
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdDonViQlIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idDonViQl equals to
        defaultDanhMucDonViFiltering("idDonViQl.equals=" + DEFAULT_ID_DON_VI_QL, "idDonViQl.equals=" + UPDATED_ID_DON_VI_QL);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdDonViQlIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idDonViQl in
        defaultDanhMucDonViFiltering(
            "idDonViQl.in=" + DEFAULT_ID_DON_VI_QL + "," + UPDATED_ID_DON_VI_QL,
            "idDonViQl.in=" + UPDATED_ID_DON_VI_QL
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdDonViQlIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idDonViQl is not null
        defaultDanhMucDonViFiltering("idDonViQl.specified=true", "idDonViQl.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdDonViQlIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idDonViQl is greater than or equal to
        defaultDanhMucDonViFiltering(
            "idDonViQl.greaterThanOrEqual=" + DEFAULT_ID_DON_VI_QL,
            "idDonViQl.greaterThanOrEqual=" + UPDATED_ID_DON_VI_QL
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdDonViQlIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idDonViQl is less than or equal to
        defaultDanhMucDonViFiltering(
            "idDonViQl.lessThanOrEqual=" + DEFAULT_ID_DON_VI_QL,
            "idDonViQl.lessThanOrEqual=" + SMALLER_ID_DON_VI_QL
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdDonViQlIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idDonViQl is less than
        defaultDanhMucDonViFiltering("idDonViQl.lessThan=" + UPDATED_ID_DON_VI_QL, "idDonViQl.lessThan=" + DEFAULT_ID_DON_VI_QL);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdDonViQlIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idDonViQl is greater than
        defaultDanhMucDonViFiltering("idDonViQl.greaterThan=" + SMALLER_ID_DON_VI_QL, "idDonViQl.greaterThan=" + DEFAULT_ID_DON_VI_QL);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNgayKhaiBaoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where ngayKhaiBao equals to
        defaultDanhMucDonViFiltering("ngayKhaiBao.equals=" + DEFAULT_NGAY_KHAI_BAO, "ngayKhaiBao.equals=" + UPDATED_NGAY_KHAI_BAO);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNgayKhaiBaoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where ngayKhaiBao in
        defaultDanhMucDonViFiltering(
            "ngayKhaiBao.in=" + DEFAULT_NGAY_KHAI_BAO + "," + UPDATED_NGAY_KHAI_BAO,
            "ngayKhaiBao.in=" + UPDATED_NGAY_KHAI_BAO
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNgayKhaiBaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where ngayKhaiBao is not null
        defaultDanhMucDonViFiltering("ngayKhaiBao.specified=true", "ngayKhaiBao.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNgayKhaiBaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where ngayKhaiBao is greater than or equal to
        defaultDanhMucDonViFiltering(
            "ngayKhaiBao.greaterThanOrEqual=" + DEFAULT_NGAY_KHAI_BAO,
            "ngayKhaiBao.greaterThanOrEqual=" + UPDATED_NGAY_KHAI_BAO
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNgayKhaiBaoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where ngayKhaiBao is less than or equal to
        defaultDanhMucDonViFiltering(
            "ngayKhaiBao.lessThanOrEqual=" + DEFAULT_NGAY_KHAI_BAO,
            "ngayKhaiBao.lessThanOrEqual=" + SMALLER_NGAY_KHAI_BAO
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNgayKhaiBaoIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where ngayKhaiBao is less than
        defaultDanhMucDonViFiltering("ngayKhaiBao.lessThan=" + UPDATED_NGAY_KHAI_BAO, "ngayKhaiBao.lessThan=" + DEFAULT_NGAY_KHAI_BAO);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNgayKhaiBaoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where ngayKhaiBao is greater than
        defaultDanhMucDonViFiltering(
            "ngayKhaiBao.greaterThan=" + SMALLER_NGAY_KHAI_BAO,
            "ngayKhaiBao.greaterThan=" + DEFAULT_NGAY_KHAI_BAO
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where trangThai equals to
        defaultDanhMucDonViFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where trangThai in
        defaultDanhMucDonViFiltering("trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI, "trangThai.in=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where trangThai is not null
        defaultDanhMucDonViFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where trangThai is greater than or equal to
        defaultDanhMucDonViFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where trangThai is less than or equal to
        defaultDanhMucDonViFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where trangThai is less than
        defaultDanhMucDonViFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where trangThai is greater than
        defaultDanhMucDonViFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisBySoNhaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where soNha equals to
        defaultDanhMucDonViFiltering("soNha.equals=" + DEFAULT_SO_NHA, "soNha.equals=" + UPDATED_SO_NHA);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisBySoNhaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where soNha in
        defaultDanhMucDonViFiltering("soNha.in=" + DEFAULT_SO_NHA + "," + UPDATED_SO_NHA, "soNha.in=" + UPDATED_SO_NHA);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisBySoNhaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where soNha is not null
        defaultDanhMucDonViFiltering("soNha.specified=true", "soNha.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisBySoNhaContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where soNha contains
        defaultDanhMucDonViFiltering("soNha.contains=" + DEFAULT_SO_NHA, "soNha.contains=" + UPDATED_SO_NHA);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisBySoNhaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where soNha does not contain
        defaultDanhMucDonViFiltering("soNha.doesNotContain=" + UPDATED_SO_NHA, "soNha.doesNotContain=" + DEFAULT_SO_NHA);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaSoThueIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maSoThue equals to
        defaultDanhMucDonViFiltering("maSoThue.equals=" + DEFAULT_MA_SO_THUE, "maSoThue.equals=" + UPDATED_MA_SO_THUE);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaSoThueIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maSoThue in
        defaultDanhMucDonViFiltering("maSoThue.in=" + DEFAULT_MA_SO_THUE + "," + UPDATED_MA_SO_THUE, "maSoThue.in=" + UPDATED_MA_SO_THUE);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaSoThueIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maSoThue is not null
        defaultDanhMucDonViFiltering("maSoThue.specified=true", "maSoThue.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaSoThueContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maSoThue contains
        defaultDanhMucDonViFiltering("maSoThue.contains=" + DEFAULT_MA_SO_THUE, "maSoThue.contains=" + UPDATED_MA_SO_THUE);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaSoThueNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maSoThue does not contain
        defaultDanhMucDonViFiltering("maSoThue.doesNotContain=" + UPDATED_MA_SO_THUE, "maSoThue.doesNotContain=" + DEFAULT_MA_SO_THUE);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByHoaDonDtIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where hoaDonDt equals to
        defaultDanhMucDonViFiltering("hoaDonDt.equals=" + DEFAULT_HOA_DON_DT, "hoaDonDt.equals=" + UPDATED_HOA_DON_DT);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByHoaDonDtIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where hoaDonDt in
        defaultDanhMucDonViFiltering("hoaDonDt.in=" + DEFAULT_HOA_DON_DT + "," + UPDATED_HOA_DON_DT, "hoaDonDt.in=" + UPDATED_HOA_DON_DT);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByHoaDonDtIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where hoaDonDt is not null
        defaultDanhMucDonViFiltering("hoaDonDt.specified=true", "hoaDonDt.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByHoaDonDtIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where hoaDonDt is greater than or equal to
        defaultDanhMucDonViFiltering(
            "hoaDonDt.greaterThanOrEqual=" + DEFAULT_HOA_DON_DT,
            "hoaDonDt.greaterThanOrEqual=" + UPDATED_HOA_DON_DT
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByHoaDonDtIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where hoaDonDt is less than or equal to
        defaultDanhMucDonViFiltering("hoaDonDt.lessThanOrEqual=" + DEFAULT_HOA_DON_DT, "hoaDonDt.lessThanOrEqual=" + SMALLER_HOA_DON_DT);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByHoaDonDtIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where hoaDonDt is less than
        defaultDanhMucDonViFiltering("hoaDonDt.lessThan=" + UPDATED_HOA_DON_DT, "hoaDonDt.lessThan=" + DEFAULT_HOA_DON_DT);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByHoaDonDtIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where hoaDonDt is greater than
        defaultDanhMucDonViFiltering("hoaDonDt.greaterThan=" + SMALLER_HOA_DON_DT, "hoaDonDt.greaterThan=" + DEFAULT_HOA_DON_DT);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaDonViIgateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maDonViIgate equals to
        defaultDanhMucDonViFiltering("maDonViIgate.equals=" + DEFAULT_MA_DON_VI_IGATE, "maDonViIgate.equals=" + UPDATED_MA_DON_VI_IGATE);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaDonViIgateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maDonViIgate in
        defaultDanhMucDonViFiltering(
            "maDonViIgate.in=" + DEFAULT_MA_DON_VI_IGATE + "," + UPDATED_MA_DON_VI_IGATE,
            "maDonViIgate.in=" + UPDATED_MA_DON_VI_IGATE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaDonViIgateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maDonViIgate is not null
        defaultDanhMucDonViFiltering("maDonViIgate.specified=true", "maDonViIgate.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaDonViIgateContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maDonViIgate contains
        defaultDanhMucDonViFiltering(
            "maDonViIgate.contains=" + DEFAULT_MA_DON_VI_IGATE,
            "maDonViIgate.contains=" + UPDATED_MA_DON_VI_IGATE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaDonViIgateNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maDonViIgate does not contain
        defaultDanhMucDonViFiltering(
            "maDonViIgate.doesNotContain=" + UPDATED_MA_DON_VI_IGATE,
            "maDonViIgate.doesNotContain=" + DEFAULT_MA_DON_VI_IGATE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaCoQuanIgateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maCoQuanIgate equals to
        defaultDanhMucDonViFiltering(
            "maCoQuanIgate.equals=" + DEFAULT_MA_CO_QUAN_IGATE,
            "maCoQuanIgate.equals=" + UPDATED_MA_CO_QUAN_IGATE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaCoQuanIgateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maCoQuanIgate in
        defaultDanhMucDonViFiltering(
            "maCoQuanIgate.in=" + DEFAULT_MA_CO_QUAN_IGATE + "," + UPDATED_MA_CO_QUAN_IGATE,
            "maCoQuanIgate.in=" + UPDATED_MA_CO_QUAN_IGATE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaCoQuanIgateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maCoQuanIgate is not null
        defaultDanhMucDonViFiltering("maCoQuanIgate.specified=true", "maCoQuanIgate.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaCoQuanIgateContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maCoQuanIgate contains
        defaultDanhMucDonViFiltering(
            "maCoQuanIgate.contains=" + DEFAULT_MA_CO_QUAN_IGATE,
            "maCoQuanIgate.contains=" + UPDATED_MA_CO_QUAN_IGATE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByMaCoQuanIgateNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where maCoQuanIgate does not contain
        defaultDanhMucDonViFiltering(
            "maCoQuanIgate.doesNotContain=" + UPDATED_MA_CO_QUAN_IGATE,
            "maCoQuanIgate.doesNotContain=" + DEFAULT_MA_CO_QUAN_IGATE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByKySoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where kySo equals to
        defaultDanhMucDonViFiltering("kySo.equals=" + DEFAULT_KY_SO, "kySo.equals=" + UPDATED_KY_SO);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByKySoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where kySo in
        defaultDanhMucDonViFiltering("kySo.in=" + DEFAULT_KY_SO + "," + UPDATED_KY_SO, "kySo.in=" + UPDATED_KY_SO);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByKySoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where kySo is not null
        defaultDanhMucDonViFiltering("kySo.specified=true", "kySo.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByKySoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where kySo is greater than or equal to
        defaultDanhMucDonViFiltering("kySo.greaterThanOrEqual=" + DEFAULT_KY_SO, "kySo.greaterThanOrEqual=" + UPDATED_KY_SO);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByKySoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where kySo is less than or equal to
        defaultDanhMucDonViFiltering("kySo.lessThanOrEqual=" + DEFAULT_KY_SO, "kySo.lessThanOrEqual=" + SMALLER_KY_SO);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByKySoIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where kySo is less than
        defaultDanhMucDonViFiltering("kySo.lessThan=" + UPDATED_KY_SO, "kySo.lessThan=" + DEFAULT_KY_SO);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByKySoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where kySo is greater than
        defaultDanhMucDonViFiltering("kySo.greaterThan=" + SMALLER_KY_SO, "kySo.greaterThan=" + DEFAULT_KY_SO);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByQrScanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where qrScan equals to
        defaultDanhMucDonViFiltering("qrScan.equals=" + DEFAULT_QR_SCAN, "qrScan.equals=" + UPDATED_QR_SCAN);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByQrScanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where qrScan in
        defaultDanhMucDonViFiltering("qrScan.in=" + DEFAULT_QR_SCAN + "," + UPDATED_QR_SCAN, "qrScan.in=" + UPDATED_QR_SCAN);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByQrScanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where qrScan is not null
        defaultDanhMucDonViFiltering("qrScan.specified=true", "qrScan.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByQrScanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where qrScan is greater than or equal to
        defaultDanhMucDonViFiltering("qrScan.greaterThanOrEqual=" + DEFAULT_QR_SCAN, "qrScan.greaterThanOrEqual=" + UPDATED_QR_SCAN);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByQrScanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where qrScan is less than or equal to
        defaultDanhMucDonViFiltering("qrScan.lessThanOrEqual=" + DEFAULT_QR_SCAN, "qrScan.lessThanOrEqual=" + SMALLER_QR_SCAN);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByQrScanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where qrScan is less than
        defaultDanhMucDonViFiltering("qrScan.lessThan=" + UPDATED_QR_SCAN, "qrScan.lessThan=" + DEFAULT_QR_SCAN);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByQrScanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where qrScan is greater than
        defaultDanhMucDonViFiltering("qrScan.greaterThan=" + SMALLER_QR_SCAN, "qrScan.greaterThan=" + DEFAULT_QR_SCAN);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyIdCardIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyIdCard equals to
        defaultDanhMucDonViFiltering("verifyIdCard.equals=" + DEFAULT_VERIFY_ID_CARD, "verifyIdCard.equals=" + UPDATED_VERIFY_ID_CARD);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyIdCardIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyIdCard in
        defaultDanhMucDonViFiltering(
            "verifyIdCard.in=" + DEFAULT_VERIFY_ID_CARD + "," + UPDATED_VERIFY_ID_CARD,
            "verifyIdCard.in=" + UPDATED_VERIFY_ID_CARD
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyIdCardIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyIdCard is not null
        defaultDanhMucDonViFiltering("verifyIdCard.specified=true", "verifyIdCard.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyIdCardIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyIdCard is greater than or equal to
        defaultDanhMucDonViFiltering(
            "verifyIdCard.greaterThanOrEqual=" + DEFAULT_VERIFY_ID_CARD,
            "verifyIdCard.greaterThanOrEqual=" + UPDATED_VERIFY_ID_CARD
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyIdCardIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyIdCard is less than or equal to
        defaultDanhMucDonViFiltering(
            "verifyIdCard.lessThanOrEqual=" + DEFAULT_VERIFY_ID_CARD,
            "verifyIdCard.lessThanOrEqual=" + SMALLER_VERIFY_ID_CARD
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyIdCardIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyIdCard is less than
        defaultDanhMucDonViFiltering("verifyIdCard.lessThan=" + UPDATED_VERIFY_ID_CARD, "verifyIdCard.lessThan=" + DEFAULT_VERIFY_ID_CARD);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyIdCardIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyIdCard is greater than
        defaultDanhMucDonViFiltering(
            "verifyIdCard.greaterThan=" + SMALLER_VERIFY_ID_CARD,
            "verifyIdCard.greaterThan=" + DEFAULT_VERIFY_ID_CARD
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsVerifyFaceIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isVerifyFace equals to
        defaultDanhMucDonViFiltering("isVerifyFace.equals=" + DEFAULT_IS_VERIFY_FACE, "isVerifyFace.equals=" + UPDATED_IS_VERIFY_FACE);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsVerifyFaceIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isVerifyFace in
        defaultDanhMucDonViFiltering(
            "isVerifyFace.in=" + DEFAULT_IS_VERIFY_FACE + "," + UPDATED_IS_VERIFY_FACE,
            "isVerifyFace.in=" + UPDATED_IS_VERIFY_FACE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsVerifyFaceIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isVerifyFace is not null
        defaultDanhMucDonViFiltering("isVerifyFace.specified=true", "isVerifyFace.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsVerifyFaceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isVerifyFace is greater than or equal to
        defaultDanhMucDonViFiltering(
            "isVerifyFace.greaterThanOrEqual=" + DEFAULT_IS_VERIFY_FACE,
            "isVerifyFace.greaterThanOrEqual=" + UPDATED_IS_VERIFY_FACE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsVerifyFaceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isVerifyFace is less than or equal to
        defaultDanhMucDonViFiltering(
            "isVerifyFace.lessThanOrEqual=" + DEFAULT_IS_VERIFY_FACE,
            "isVerifyFace.lessThanOrEqual=" + SMALLER_IS_VERIFY_FACE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsVerifyFaceIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isVerifyFace is less than
        defaultDanhMucDonViFiltering("isVerifyFace.lessThan=" + UPDATED_IS_VERIFY_FACE, "isVerifyFace.lessThan=" + DEFAULT_IS_VERIFY_FACE);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsVerifyFaceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isVerifyFace is greater than
        defaultDanhMucDonViFiltering(
            "isVerifyFace.greaterThan=" + SMALLER_IS_VERIFY_FACE,
            "isVerifyFace.greaterThan=" + DEFAULT_IS_VERIFY_FACE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsElasticIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isElastic equals to
        defaultDanhMucDonViFiltering("isElastic.equals=" + DEFAULT_IS_ELASTIC, "isElastic.equals=" + UPDATED_IS_ELASTIC);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsElasticIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isElastic in
        defaultDanhMucDonViFiltering("isElastic.in=" + DEFAULT_IS_ELASTIC + "," + UPDATED_IS_ELASTIC, "isElastic.in=" + UPDATED_IS_ELASTIC);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsElasticIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isElastic is not null
        defaultDanhMucDonViFiltering("isElastic.specified=true", "isElastic.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsElasticIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isElastic is greater than or equal to
        defaultDanhMucDonViFiltering(
            "isElastic.greaterThanOrEqual=" + DEFAULT_IS_ELASTIC,
            "isElastic.greaterThanOrEqual=" + UPDATED_IS_ELASTIC
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsElasticIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isElastic is less than or equal to
        defaultDanhMucDonViFiltering("isElastic.lessThanOrEqual=" + DEFAULT_IS_ELASTIC, "isElastic.lessThanOrEqual=" + SMALLER_IS_ELASTIC);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsElasticIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isElastic is less than
        defaultDanhMucDonViFiltering("isElastic.lessThan=" + UPDATED_IS_ELASTIC, "isElastic.lessThan=" + DEFAULT_IS_ELASTIC);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIsElasticIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where isElastic is greater than
        defaultDanhMucDonViFiltering("isElastic.greaterThan=" + SMALLER_IS_ELASTIC, "isElastic.greaterThan=" + DEFAULT_IS_ELASTIC);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByApikeyCccdIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where apikeyCccd equals to
        defaultDanhMucDonViFiltering("apikeyCccd.equals=" + DEFAULT_APIKEY_CCCD, "apikeyCccd.equals=" + UPDATED_APIKEY_CCCD);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByApikeyCccdIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where apikeyCccd in
        defaultDanhMucDonViFiltering(
            "apikeyCccd.in=" + DEFAULT_APIKEY_CCCD + "," + UPDATED_APIKEY_CCCD,
            "apikeyCccd.in=" + UPDATED_APIKEY_CCCD
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByApikeyCccdIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where apikeyCccd is not null
        defaultDanhMucDonViFiltering("apikeyCccd.specified=true", "apikeyCccd.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByApikeyCccdContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where apikeyCccd contains
        defaultDanhMucDonViFiltering("apikeyCccd.contains=" + DEFAULT_APIKEY_CCCD, "apikeyCccd.contains=" + UPDATED_APIKEY_CCCD);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByApikeyCccdNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where apikeyCccd does not contain
        defaultDanhMucDonViFiltering(
            "apikeyCccd.doesNotContain=" + UPDATED_APIKEY_CCCD,
            "apikeyCccd.doesNotContain=" + DEFAULT_APIKEY_CCCD
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByApikeyFaceIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where apikeyFace equals to
        defaultDanhMucDonViFiltering("apikeyFace.equals=" + DEFAULT_APIKEY_FACE, "apikeyFace.equals=" + UPDATED_APIKEY_FACE);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByApikeyFaceIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where apikeyFace in
        defaultDanhMucDonViFiltering(
            "apikeyFace.in=" + DEFAULT_APIKEY_FACE + "," + UPDATED_APIKEY_FACE,
            "apikeyFace.in=" + UPDATED_APIKEY_FACE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByApikeyFaceIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where apikeyFace is not null
        defaultDanhMucDonViFiltering("apikeyFace.specified=true", "apikeyFace.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByApikeyFaceContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where apikeyFace contains
        defaultDanhMucDonViFiltering("apikeyFace.contains=" + DEFAULT_APIKEY_FACE, "apikeyFace.contains=" + UPDATED_APIKEY_FACE);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByApikeyFaceNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where apikeyFace does not contain
        defaultDanhMucDonViFiltering(
            "apikeyFace.doesNotContain=" + UPDATED_APIKEY_FACE,
            "apikeyFace.doesNotContain=" + DEFAULT_APIKEY_FACE
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyCodeCccdIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyCodeCccd equals to
        defaultDanhMucDonViFiltering(
            "verifyCodeCccd.equals=" + DEFAULT_VERIFY_CODE_CCCD,
            "verifyCodeCccd.equals=" + UPDATED_VERIFY_CODE_CCCD
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyCodeCccdIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyCodeCccd in
        defaultDanhMucDonViFiltering(
            "verifyCodeCccd.in=" + DEFAULT_VERIFY_CODE_CCCD + "," + UPDATED_VERIFY_CODE_CCCD,
            "verifyCodeCccd.in=" + UPDATED_VERIFY_CODE_CCCD
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyCodeCccdIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyCodeCccd is not null
        defaultDanhMucDonViFiltering("verifyCodeCccd.specified=true", "verifyCodeCccd.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyCodeCccdContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyCodeCccd contains
        defaultDanhMucDonViFiltering(
            "verifyCodeCccd.contains=" + DEFAULT_VERIFY_CODE_CCCD,
            "verifyCodeCccd.contains=" + UPDATED_VERIFY_CODE_CCCD
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByVerifyCodeCccdNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where verifyCodeCccd does not contain
        defaultDanhMucDonViFiltering(
            "verifyCodeCccd.doesNotContain=" + UPDATED_VERIFY_CODE_CCCD,
            "verifyCodeCccd.doesNotContain=" + DEFAULT_VERIFY_CODE_CCCD
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByUsernameElasticIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where usernameElastic equals to
        defaultDanhMucDonViFiltering(
            "usernameElastic.equals=" + DEFAULT_USERNAME_ELASTIC,
            "usernameElastic.equals=" + UPDATED_USERNAME_ELASTIC
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByUsernameElasticIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where usernameElastic in
        defaultDanhMucDonViFiltering(
            "usernameElastic.in=" + DEFAULT_USERNAME_ELASTIC + "," + UPDATED_USERNAME_ELASTIC,
            "usernameElastic.in=" + UPDATED_USERNAME_ELASTIC
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByUsernameElasticIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where usernameElastic is not null
        defaultDanhMucDonViFiltering("usernameElastic.specified=true", "usernameElastic.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByUsernameElasticContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where usernameElastic contains
        defaultDanhMucDonViFiltering(
            "usernameElastic.contains=" + DEFAULT_USERNAME_ELASTIC,
            "usernameElastic.contains=" + UPDATED_USERNAME_ELASTIC
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByUsernameElasticNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where usernameElastic does not contain
        defaultDanhMucDonViFiltering(
            "usernameElastic.doesNotContain=" + UPDATED_USERNAME_ELASTIC,
            "usernameElastic.doesNotContain=" + DEFAULT_USERNAME_ELASTIC
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByPasswordElasticIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where passwordElastic equals to
        defaultDanhMucDonViFiltering(
            "passwordElastic.equals=" + DEFAULT_PASSWORD_ELASTIC,
            "passwordElastic.equals=" + UPDATED_PASSWORD_ELASTIC
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByPasswordElasticIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where passwordElastic in
        defaultDanhMucDonViFiltering(
            "passwordElastic.in=" + DEFAULT_PASSWORD_ELASTIC + "," + UPDATED_PASSWORD_ELASTIC,
            "passwordElastic.in=" + UPDATED_PASSWORD_ELASTIC
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByPasswordElasticIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where passwordElastic is not null
        defaultDanhMucDonViFiltering("passwordElastic.specified=true", "passwordElastic.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByPasswordElasticContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where passwordElastic contains
        defaultDanhMucDonViFiltering(
            "passwordElastic.contains=" + DEFAULT_PASSWORD_ELASTIC,
            "passwordElastic.contains=" + UPDATED_PASSWORD_ELASTIC
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByPasswordElasticNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where passwordElastic does not contain
        defaultDanhMucDonViFiltering(
            "passwordElastic.doesNotContain=" + UPDATED_PASSWORD_ELASTIC,
            "passwordElastic.doesNotContain=" + DEFAULT_PASSWORD_ELASTIC
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdNhiemVuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idNhiemVu equals to
        defaultDanhMucDonViFiltering("idNhiemVu.equals=" + DEFAULT_ID_NHIEM_VU, "idNhiemVu.equals=" + UPDATED_ID_NHIEM_VU);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdNhiemVuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idNhiemVu in
        defaultDanhMucDonViFiltering(
            "idNhiemVu.in=" + DEFAULT_ID_NHIEM_VU + "," + UPDATED_ID_NHIEM_VU,
            "idNhiemVu.in=" + UPDATED_ID_NHIEM_VU
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdNhiemVuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idNhiemVu is not null
        defaultDanhMucDonViFiltering("idNhiemVu.specified=true", "idNhiemVu.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdNhiemVuContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idNhiemVu contains
        defaultDanhMucDonViFiltering("idNhiemVu.contains=" + DEFAULT_ID_NHIEM_VU, "idNhiemVu.contains=" + UPDATED_ID_NHIEM_VU);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdNhiemVuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idNhiemVu does not contain
        defaultDanhMucDonViFiltering("idNhiemVu.doesNotContain=" + UPDATED_ID_NHIEM_VU, "idNhiemVu.doesNotContain=" + DEFAULT_ID_NHIEM_VU);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdLoaiDvIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idLoaiDv equals to
        defaultDanhMucDonViFiltering("idLoaiDv.equals=" + DEFAULT_ID_LOAI_DV, "idLoaiDv.equals=" + UPDATED_ID_LOAI_DV);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdLoaiDvIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idLoaiDv in
        defaultDanhMucDonViFiltering("idLoaiDv.in=" + DEFAULT_ID_LOAI_DV + "," + UPDATED_ID_LOAI_DV, "idLoaiDv.in=" + UPDATED_ID_LOAI_DV);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdLoaiDvIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idLoaiDv is not null
        defaultDanhMucDonViFiltering("idLoaiDv.specified=true", "idLoaiDv.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdLoaiDvContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idLoaiDv contains
        defaultDanhMucDonViFiltering("idLoaiDv.contains=" + DEFAULT_ID_LOAI_DV, "idLoaiDv.contains=" + UPDATED_ID_LOAI_DV);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdLoaiDvNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idLoaiDv does not contain
        defaultDanhMucDonViFiltering("idLoaiDv.doesNotContain=" + UPDATED_ID_LOAI_DV, "idLoaiDv.doesNotContain=" + DEFAULT_ID_LOAI_DV);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdCapQlIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idCapQl equals to
        defaultDanhMucDonViFiltering("idCapQl.equals=" + DEFAULT_ID_CAP_QL, "idCapQl.equals=" + UPDATED_ID_CAP_QL);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdCapQlIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idCapQl in
        defaultDanhMucDonViFiltering("idCapQl.in=" + DEFAULT_ID_CAP_QL + "," + UPDATED_ID_CAP_QL, "idCapQl.in=" + UPDATED_ID_CAP_QL);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdCapQlIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idCapQl is not null
        defaultDanhMucDonViFiltering("idCapQl.specified=true", "idCapQl.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdCapQlContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idCapQl contains
        defaultDanhMucDonViFiltering("idCapQl.contains=" + DEFAULT_ID_CAP_QL, "idCapQl.contains=" + UPDATED_ID_CAP_QL);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByIdCapQlNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDonVi = danhMucDonViRepository.saveAndFlush(danhMucDonVi);

        // Get all the danhMucDonViList where idCapQl does not contain
        defaultDanhMucDonViFiltering("idCapQl.doesNotContain=" + UPDATED_ID_CAP_QL, "idCapQl.doesNotContain=" + DEFAULT_ID_CAP_QL);
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByCapQuanLyIsEqualToSomething() throws Exception {
        CapQuanLy capQuanLy;
        if (TestUtil.findAll(em, CapQuanLy.class).isEmpty()) {
            danhMucDonViRepository.saveAndFlush(danhMucDonVi);
            capQuanLy = CapQuanLyResourceIT.createEntity();
        } else {
            capQuanLy = TestUtil.findAll(em, CapQuanLy.class).get(0);
        }
        em.persist(capQuanLy);
        em.flush();
        danhMucDonVi.setCapQuanLy(capQuanLy);
        danhMucDonViRepository.saveAndFlush(danhMucDonVi);
        String capQuanLyId = capQuanLy.getIdCapQl();
        // Get all the danhMucDonViList where capQuanLy equals to capQuanLyId
        defaultDanhMucDonViShouldBeFound("capQuanLyId.equals=" + capQuanLyId);

        // Get all the danhMucDonViList where capQuanLy equals to "invalid-id"
        defaultDanhMucDonViShouldNotBeFound("capQuanLyId.equals=" + "invalid-id");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByLoaiDonViIsEqualToSomething() throws Exception {
        LoaiDonVi loaiDonVi;
        if (TestUtil.findAll(em, LoaiDonVi.class).isEmpty()) {
            danhMucDonViRepository.saveAndFlush(danhMucDonVi);
            loaiDonVi = LoaiDonViResourceIT.createEntity();
        } else {
            loaiDonVi = TestUtil.findAll(em, LoaiDonVi.class).get(0);
        }
        em.persist(loaiDonVi);
        em.flush();
        danhMucDonVi.setLoaiDonVi(loaiDonVi);
        danhMucDonViRepository.saveAndFlush(danhMucDonVi);
        String loaiDonViId = loaiDonVi.getIdLoaiDv();
        // Get all the danhMucDonViList where loaiDonVi equals to loaiDonViId
        defaultDanhMucDonViShouldBeFound("loaiDonViId.equals=" + loaiDonViId);

        // Get all the danhMucDonViList where loaiDonVi equals to "invalid-id"
        defaultDanhMucDonViShouldNotBeFound("loaiDonViId.equals=" + "invalid-id");
    }

    @Test
    @Transactional
    void getAllDanhMucDonVisByNhiemVuIsEqualToSomething() throws Exception {
        NhiemVu nhiemVu;
        if (TestUtil.findAll(em, NhiemVu.class).isEmpty()) {
            danhMucDonViRepository.saveAndFlush(danhMucDonVi);
            nhiemVu = NhiemVuResourceIT.createEntity();
        } else {
            nhiemVu = TestUtil.findAll(em, NhiemVu.class).get(0);
        }
        em.persist(nhiemVu);
        em.flush();
        danhMucDonVi.setNhiemVu(nhiemVu);
        danhMucDonViRepository.saveAndFlush(danhMucDonVi);
        String nhiemVuId = nhiemVu.getIdNhiemVu();
        // Get all the danhMucDonViList where nhiemVu equals to nhiemVuId
        defaultDanhMucDonViShouldBeFound("nhiemVuId.equals=" + nhiemVuId);

        // Get all the danhMucDonViList where nhiemVu equals to "invalid-id"
        defaultDanhMucDonViShouldNotBeFound("nhiemVuId.equals=" + "invalid-id");
    }

    private void defaultDanhMucDonViFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDanhMucDonViShouldBeFound(shouldBeFound);
        defaultDanhMucDonViShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhMucDonViShouldBeFound(String filter) throws Exception {
        restDanhMucDonViMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDonVi,desc&" + filter))
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

        // Check, that the count call also returns 1
        restDanhMucDonViMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idDonVi,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhMucDonViShouldNotBeFound(String filter) throws Exception {
        restDanhMucDonViMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDonVi,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhMucDonViMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idDonVi,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
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
