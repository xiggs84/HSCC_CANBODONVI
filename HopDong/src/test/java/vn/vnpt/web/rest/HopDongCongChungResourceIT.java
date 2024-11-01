package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.HopDongCongChungAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import vn.vnpt.IntegrationTest;
import vn.vnpt.domain.HopDongCongChung;
import vn.vnpt.repository.HopDongCongChungRepository;
import vn.vnpt.service.dto.HopDongCongChungDTO;
import vn.vnpt.service.mapper.HopDongCongChungMapper;

/**
 * Integration tests for the {@link HopDongCongChungResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HopDongCongChungResourceIT {

    private static final LocalDate DEFAULT_NGAY_LAP_HD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_LAP_HD = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_LAP_HD = 1L;
    private static final Long UPDATED_NGUOI_LAP_HD = 2L;

    private static final String DEFAULT_THONG_TIN_DUONG_SU = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_DUONG_SU = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_TAI_SAN = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_VAN_BAN = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_VAN_BAN = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String DEFAULT_ID_LOAI_HD = "AAAAAAAAAA";
    private static final String UPDATED_ID_LOAI_HD = "BBBBBBBBBB";

    private static final String DEFAULT_DIEU_KHOAN_HD = "AAAAAAAAAA";
    private static final String UPDATED_DIEU_KHOAN_HD = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final String DEFAULT_ID_HD_GOC = "AAAAAAAAAA";
    private static final String UPDATED_ID_HD_GOC = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_CHUYEN_NHUONG = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_CHUYEN_NHUONG = "BBBBBBBBBB";

    private static final String DEFAULT_MA_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_MA_HOP_DONG = "BBBBBBBBBB";

    private static final String DEFAULT_SRC_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_SRC_HOP_DONG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_HEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_HEN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ID_SO_CONG_CHUNG = "AAAAAAAAAA";
    private static final String UPDATED_ID_SO_CONG_CHUNG = "BBBBBBBBBB";

    private static final String DEFAULT_SO_CONG_CHUNG = "AAAAAAAAAA";
    private static final String UPDATED_SO_CONG_CHUNG = "BBBBBBBBBB";

    private static final Long DEFAULT_CONG_CHUNG_VIEN = 1L;
    private static final Long UPDATED_CONG_CHUNG_VIEN = 2L;

    private static final LocalDate DEFAULT_NGAY_KY_HD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KY_HD = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_RUT_TRICH = 1L;
    private static final Long UPDATED_NGUOI_RUT_TRICH = 2L;

    private static final Long DEFAULT_SO_TIEN_RUT_TRICH = 1L;
    private static final Long UPDATED_SO_TIEN_RUT_TRICH = 2L;

    private static final LocalDate DEFAULT_NGAY_RUT_TRICH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_RUT_TRICH = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_HD_THU_CONG = 1L;
    private static final Long UPDATED_HD_THU_CONG = 2L;

    private static final Long DEFAULT_TRANG_THAI_RUT_TRICH = 1L;
    private static final Long UPDATED_TRANG_THAI_RUT_TRICH = 2L;

    private static final Long DEFAULT_CHU_KY_NGOAI_TRU_SO = 1L;
    private static final Long UPDATED_CHU_KY_NGOAI_TRU_SO = 2L;

    private static final String DEFAULT_STR_SEARCH = "AAAAAAAAAA";
    private static final String UPDATED_STR_SEARCH = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_MASTER = 1L;
    private static final Long UPDATED_ID_MASTER = 2L;

    private static final Long DEFAULT_ID_HD_SD_HB = 1L;
    private static final Long UPDATED_ID_HD_SD_HB = 2L;

    private static final String DEFAULT_SRC_DM_MASTER = "AAAAAAAAAA";
    private static final String UPDATED_SRC_DM_MASTER = "BBBBBBBBBB";

    private static final Long DEFAULT_REP_REF_UNIQUE = 1L;
    private static final Long UPDATED_REP_REF_UNIQUE = 2L;

    private static final String DEFAULT_NGAY_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_TEXT = "BBBBBBBBBB";

    private static final Long DEFAULT_NGAY_NUM = 1L;
    private static final Long UPDATED_NGAY_NUM = 2L;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC_RUT_TRICH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC_RUT_TRICH = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_THU_LAO_CONG_CHUNG = 1L;
    private static final Long UPDATED_THU_LAO_CONG_CHUNG = 2L;

    private static final String DEFAULT_QUYEN_LAI_ST = "AAAAAAAAAA";
    private static final String UPDATED_QUYEN_LAI_ST = "BBBBBBBBBB";

    private static final String DEFAULT_SO_LAI_ST = "AAAAAAAAAA";
    private static final String UPDATED_SO_LAI_ST = "BBBBBBBBBB";

    private static final String DEFAULT_QUYEN_LAI_TL = "AAAAAAAAAA";
    private static final String UPDATED_QUYEN_LAI_TL = "BBBBBBBBBB";

    private static final String DEFAULT_SO_LAI_TL = "AAAAAAAAAA";
    private static final String UPDATED_SO_LAI_TL = "BBBBBBBBBB";

    private static final String DEFAULT_SRC_KY_SO_PDF = "AAAAAAAAAA";
    private static final String UPDATED_SRC_KY_SO_PDF = "BBBBBBBBBB";

    private static final String DEFAULT_SRC_KY_SO_PDF_SIGNED = "AAAAAAAAAA";
    private static final String UPDATED_SRC_KY_SO_PDF_SIGNED = "BBBBBBBBBB";

    private static final Long DEFAULT_SYNC_STATUS = 1L;
    private static final Long UPDATED_SYNC_STATUS = 2L;

    private static final String DEFAULT_NGAY_RUT_TRICH_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_RUT_TRICH_TEXT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/hop-dong-cong-chungs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HopDongCongChungRepository hopDongCongChungRepository;

    @Autowired
    private HopDongCongChungMapper hopDongCongChungMapper;

    @Autowired
    private MockMvc restHopDongCongChungMockMvc;

    private HopDongCongChung hopDongCongChung;

    private HopDongCongChung insertedHopDongCongChung;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HopDongCongChung createEntity() {
        return new HopDongCongChung()
            .ngayLapHd(DEFAULT_NGAY_LAP_HD)
            .nguoiLapHd(DEFAULT_NGUOI_LAP_HD)
            .thongTinDuongSu(DEFAULT_THONG_TIN_DUONG_SU)
            .thongTinTaiSan(DEFAULT_THONG_TIN_TAI_SAN)
            .thongTinVanBan(DEFAULT_THONG_TIN_VAN_BAN)
            .trangThai(DEFAULT_TRANG_THAI)
            .idLoaiHd(DEFAULT_ID_LOAI_HD)
            .dieuKhoanHd(DEFAULT_DIEU_KHOAN_HD)
            .idDonVi(DEFAULT_ID_DON_VI)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idHdGoc(DEFAULT_ID_HD_GOC)
            .thongTinChuyenNhuong(DEFAULT_THONG_TIN_CHUYEN_NHUONG)
            .maHopDong(DEFAULT_MA_HOP_DONG)
            .srcHopDong(DEFAULT_SRC_HOP_DONG)
            .ngayHen(DEFAULT_NGAY_HEN)
            .idSoCongChung(DEFAULT_ID_SO_CONG_CHUNG)
            .soCongChung(DEFAULT_SO_CONG_CHUNG)
            .congChungVien(DEFAULT_CONG_CHUNG_VIEN)
            .ngayKyHd(DEFAULT_NGAY_KY_HD)
            .nguoiRutTrich(DEFAULT_NGUOI_RUT_TRICH)
            .soTienRutTrich(DEFAULT_SO_TIEN_RUT_TRICH)
            .ngayRutTrich(DEFAULT_NGAY_RUT_TRICH)
            .hdThuCong(DEFAULT_HD_THU_CONG)
            .trangThaiRutTrich(DEFAULT_TRANG_THAI_RUT_TRICH)
            .chuKyNgoaiTruSo(DEFAULT_CHU_KY_NGOAI_TRU_SO)
            .strSearch(DEFAULT_STR_SEARCH)
            .idMaster(DEFAULT_ID_MASTER)
            .idHdSdHb(DEFAULT_ID_HD_SD_HB)
            .srcDmMaster(DEFAULT_SRC_DM_MASTER)
            .repRefUnique(DEFAULT_REP_REF_UNIQUE)
            .ngayText(DEFAULT_NGAY_TEXT)
            .ngayNum(DEFAULT_NGAY_NUM)
            .ngayThaoTacRutTrich(DEFAULT_NGAY_THAO_TAC_RUT_TRICH)
            .thuLaoCongChung(DEFAULT_THU_LAO_CONG_CHUNG)
            .quyenLaiSt(DEFAULT_QUYEN_LAI_ST)
            .soLaiSt(DEFAULT_SO_LAI_ST)
            .quyenLaiTl(DEFAULT_QUYEN_LAI_TL)
            .soLaiTl(DEFAULT_SO_LAI_TL)
            .srcKySoPdf(DEFAULT_SRC_KY_SO_PDF)
            .srcKySoPdfSigned(DEFAULT_SRC_KY_SO_PDF_SIGNED)
            .syncStatus(DEFAULT_SYNC_STATUS)
            .ngayRutTrichText(DEFAULT_NGAY_RUT_TRICH_TEXT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HopDongCongChung createUpdatedEntity() {
        return new HopDongCongChung()
            .ngayLapHd(UPDATED_NGAY_LAP_HD)
            .nguoiLapHd(UPDATED_NGUOI_LAP_HD)
            .thongTinDuongSu(UPDATED_THONG_TIN_DUONG_SU)
            .thongTinTaiSan(UPDATED_THONG_TIN_TAI_SAN)
            .thongTinVanBan(UPDATED_THONG_TIN_VAN_BAN)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .dieuKhoanHd(UPDATED_DIEU_KHOAN_HD)
            .idDonVi(UPDATED_ID_DON_VI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idHdGoc(UPDATED_ID_HD_GOC)
            .thongTinChuyenNhuong(UPDATED_THONG_TIN_CHUYEN_NHUONG)
            .maHopDong(UPDATED_MA_HOP_DONG)
            .srcHopDong(UPDATED_SRC_HOP_DONG)
            .ngayHen(UPDATED_NGAY_HEN)
            .idSoCongChung(UPDATED_ID_SO_CONG_CHUNG)
            .soCongChung(UPDATED_SO_CONG_CHUNG)
            .congChungVien(UPDATED_CONG_CHUNG_VIEN)
            .ngayKyHd(UPDATED_NGAY_KY_HD)
            .nguoiRutTrich(UPDATED_NGUOI_RUT_TRICH)
            .soTienRutTrich(UPDATED_SO_TIEN_RUT_TRICH)
            .ngayRutTrich(UPDATED_NGAY_RUT_TRICH)
            .hdThuCong(UPDATED_HD_THU_CONG)
            .trangThaiRutTrich(UPDATED_TRANG_THAI_RUT_TRICH)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .strSearch(UPDATED_STR_SEARCH)
            .idMaster(UPDATED_ID_MASTER)
            .idHdSdHb(UPDATED_ID_HD_SD_HB)
            .srcDmMaster(UPDATED_SRC_DM_MASTER)
            .repRefUnique(UPDATED_REP_REF_UNIQUE)
            .ngayText(UPDATED_NGAY_TEXT)
            .ngayNum(UPDATED_NGAY_NUM)
            .ngayThaoTacRutTrich(UPDATED_NGAY_THAO_TAC_RUT_TRICH)
            .thuLaoCongChung(UPDATED_THU_LAO_CONG_CHUNG)
            .quyenLaiSt(UPDATED_QUYEN_LAI_ST)
            .soLaiSt(UPDATED_SO_LAI_ST)
            .quyenLaiTl(UPDATED_QUYEN_LAI_TL)
            .soLaiTl(UPDATED_SO_LAI_TL)
            .srcKySoPdf(UPDATED_SRC_KY_SO_PDF)
            .srcKySoPdfSigned(UPDATED_SRC_KY_SO_PDF_SIGNED)
            .syncStatus(UPDATED_SYNC_STATUS)
            .ngayRutTrichText(UPDATED_NGAY_RUT_TRICH_TEXT);
    }

    @BeforeEach
    public void initTest() {
        hopDongCongChung = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedHopDongCongChung != null) {
            hopDongCongChungRepository.delete(insertedHopDongCongChung);
            insertedHopDongCongChung = null;
        }
    }

    @Test
    void createHopDongCongChung() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the HopDongCongChung
        HopDongCongChungDTO hopDongCongChungDTO = hopDongCongChungMapper.toDto(hopDongCongChung);
        var returnedHopDongCongChungDTO = om.readValue(
            restHopDongCongChungMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hopDongCongChungDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            HopDongCongChungDTO.class
        );

        // Validate the HopDongCongChung in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedHopDongCongChung = hopDongCongChungMapper.toEntity(returnedHopDongCongChungDTO);
        assertHopDongCongChungUpdatableFieldsEquals(returnedHopDongCongChung, getPersistedHopDongCongChung(returnedHopDongCongChung));

        insertedHopDongCongChung = returnedHopDongCongChung;
    }

    @Test
    void createHopDongCongChungWithExistingId() throws Exception {
        // Create the HopDongCongChung with an existing ID
        hopDongCongChung.setId("existing_id");
        HopDongCongChungDTO hopDongCongChungDTO = hopDongCongChungMapper.toDto(hopDongCongChung);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHopDongCongChungMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hopDongCongChungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllHopDongCongChungs() throws Exception {
        // Initialize the database
        insertedHopDongCongChung = hopDongCongChungRepository.save(hopDongCongChung);

        // Get all the hopDongCongChungList
        restHopDongCongChungMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hopDongCongChung.getId())))
            .andExpect(jsonPath("$.[*].ngayLapHd").value(hasItem(DEFAULT_NGAY_LAP_HD.toString())))
            .andExpect(jsonPath("$.[*].nguoiLapHd").value(hasItem(DEFAULT_NGUOI_LAP_HD.intValue())))
            .andExpect(jsonPath("$.[*].thongTinDuongSu").value(hasItem(DEFAULT_THONG_TIN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].thongTinTaiSan").value(hasItem(DEFAULT_THONG_TIN_TAI_SAN)))
            .andExpect(jsonPath("$.[*].thongTinVanBan").value(hasItem(DEFAULT_THONG_TIN_VAN_BAN)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiHd").value(hasItem(DEFAULT_ID_LOAI_HD)))
            .andExpect(jsonPath("$.[*].dieuKhoanHd").value(hasItem(DEFAULT_DIEU_KHOAN_HD)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idHdGoc").value(hasItem(DEFAULT_ID_HD_GOC)))
            .andExpect(jsonPath("$.[*].thongTinChuyenNhuong").value(hasItem(DEFAULT_THONG_TIN_CHUYEN_NHUONG)))
            .andExpect(jsonPath("$.[*].maHopDong").value(hasItem(DEFAULT_MA_HOP_DONG)))
            .andExpect(jsonPath("$.[*].srcHopDong").value(hasItem(DEFAULT_SRC_HOP_DONG)))
            .andExpect(jsonPath("$.[*].ngayHen").value(hasItem(DEFAULT_NGAY_HEN.toString())))
            .andExpect(jsonPath("$.[*].idSoCongChung").value(hasItem(DEFAULT_ID_SO_CONG_CHUNG)))
            .andExpect(jsonPath("$.[*].soCongChung").value(hasItem(DEFAULT_SO_CONG_CHUNG)))
            .andExpect(jsonPath("$.[*].congChungVien").value(hasItem(DEFAULT_CONG_CHUNG_VIEN.intValue())))
            .andExpect(jsonPath("$.[*].ngayKyHd").value(hasItem(DEFAULT_NGAY_KY_HD.toString())))
            .andExpect(jsonPath("$.[*].nguoiRutTrich").value(hasItem(DEFAULT_NGUOI_RUT_TRICH.intValue())))
            .andExpect(jsonPath("$.[*].soTienRutTrich").value(hasItem(DEFAULT_SO_TIEN_RUT_TRICH.intValue())))
            .andExpect(jsonPath("$.[*].ngayRutTrich").value(hasItem(DEFAULT_NGAY_RUT_TRICH.toString())))
            .andExpect(jsonPath("$.[*].hdThuCong").value(hasItem(DEFAULT_HD_THU_CONG.intValue())))
            .andExpect(jsonPath("$.[*].trangThaiRutTrich").value(hasItem(DEFAULT_TRANG_THAI_RUT_TRICH.intValue())))
            .andExpect(jsonPath("$.[*].chuKyNgoaiTruSo").value(hasItem(DEFAULT_CHU_KY_NGOAI_TRU_SO.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER.intValue())))
            .andExpect(jsonPath("$.[*].idHdSdHb").value(hasItem(DEFAULT_ID_HD_SD_HB.intValue())))
            .andExpect(jsonPath("$.[*].srcDmMaster").value(hasItem(DEFAULT_SRC_DM_MASTER)))
            .andExpect(jsonPath("$.[*].repRefUnique").value(hasItem(DEFAULT_REP_REF_UNIQUE.intValue())))
            .andExpect(jsonPath("$.[*].ngayText").value(hasItem(DEFAULT_NGAY_TEXT)))
            .andExpect(jsonPath("$.[*].ngayNum").value(hasItem(DEFAULT_NGAY_NUM.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTacRutTrich").value(hasItem(DEFAULT_NGAY_THAO_TAC_RUT_TRICH.toString())))
            .andExpect(jsonPath("$.[*].thuLaoCongChung").value(hasItem(DEFAULT_THU_LAO_CONG_CHUNG.intValue())))
            .andExpect(jsonPath("$.[*].quyenLaiSt").value(hasItem(DEFAULT_QUYEN_LAI_ST)))
            .andExpect(jsonPath("$.[*].soLaiSt").value(hasItem(DEFAULT_SO_LAI_ST)))
            .andExpect(jsonPath("$.[*].quyenLaiTl").value(hasItem(DEFAULT_QUYEN_LAI_TL)))
            .andExpect(jsonPath("$.[*].soLaiTl").value(hasItem(DEFAULT_SO_LAI_TL)))
            .andExpect(jsonPath("$.[*].srcKySoPdf").value(hasItem(DEFAULT_SRC_KY_SO_PDF)))
            .andExpect(jsonPath("$.[*].srcKySoPdfSigned").value(hasItem(DEFAULT_SRC_KY_SO_PDF_SIGNED)))
            .andExpect(jsonPath("$.[*].syncStatus").value(hasItem(DEFAULT_SYNC_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].ngayRutTrichText").value(hasItem(DEFAULT_NGAY_RUT_TRICH_TEXT)));
    }

    @Test
    void getHopDongCongChung() throws Exception {
        // Initialize the database
        insertedHopDongCongChung = hopDongCongChungRepository.save(hopDongCongChung);

        // Get the hopDongCongChung
        restHopDongCongChungMockMvc
            .perform(get(ENTITY_API_URL_ID, hopDongCongChung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hopDongCongChung.getId()))
            .andExpect(jsonPath("$.ngayLapHd").value(DEFAULT_NGAY_LAP_HD.toString()))
            .andExpect(jsonPath("$.nguoiLapHd").value(DEFAULT_NGUOI_LAP_HD.intValue()))
            .andExpect(jsonPath("$.thongTinDuongSu").value(DEFAULT_THONG_TIN_DUONG_SU))
            .andExpect(jsonPath("$.thongTinTaiSan").value(DEFAULT_THONG_TIN_TAI_SAN))
            .andExpect(jsonPath("$.thongTinVanBan").value(DEFAULT_THONG_TIN_VAN_BAN))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.idLoaiHd").value(DEFAULT_ID_LOAI_HD))
            .andExpect(jsonPath("$.dieuKhoanHd").value(DEFAULT_DIEU_KHOAN_HD))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idHdGoc").value(DEFAULT_ID_HD_GOC))
            .andExpect(jsonPath("$.thongTinChuyenNhuong").value(DEFAULT_THONG_TIN_CHUYEN_NHUONG))
            .andExpect(jsonPath("$.maHopDong").value(DEFAULT_MA_HOP_DONG))
            .andExpect(jsonPath("$.srcHopDong").value(DEFAULT_SRC_HOP_DONG))
            .andExpect(jsonPath("$.ngayHen").value(DEFAULT_NGAY_HEN.toString()))
            .andExpect(jsonPath("$.idSoCongChung").value(DEFAULT_ID_SO_CONG_CHUNG))
            .andExpect(jsonPath("$.soCongChung").value(DEFAULT_SO_CONG_CHUNG))
            .andExpect(jsonPath("$.congChungVien").value(DEFAULT_CONG_CHUNG_VIEN.intValue()))
            .andExpect(jsonPath("$.ngayKyHd").value(DEFAULT_NGAY_KY_HD.toString()))
            .andExpect(jsonPath("$.nguoiRutTrich").value(DEFAULT_NGUOI_RUT_TRICH.intValue()))
            .andExpect(jsonPath("$.soTienRutTrich").value(DEFAULT_SO_TIEN_RUT_TRICH.intValue()))
            .andExpect(jsonPath("$.ngayRutTrich").value(DEFAULT_NGAY_RUT_TRICH.toString()))
            .andExpect(jsonPath("$.hdThuCong").value(DEFAULT_HD_THU_CONG.intValue()))
            .andExpect(jsonPath("$.trangThaiRutTrich").value(DEFAULT_TRANG_THAI_RUT_TRICH.intValue()))
            .andExpect(jsonPath("$.chuKyNgoaiTruSo").value(DEFAULT_CHU_KY_NGOAI_TRU_SO.intValue()))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER.intValue()))
            .andExpect(jsonPath("$.idHdSdHb").value(DEFAULT_ID_HD_SD_HB.intValue()))
            .andExpect(jsonPath("$.srcDmMaster").value(DEFAULT_SRC_DM_MASTER))
            .andExpect(jsonPath("$.repRefUnique").value(DEFAULT_REP_REF_UNIQUE.intValue()))
            .andExpect(jsonPath("$.ngayText").value(DEFAULT_NGAY_TEXT))
            .andExpect(jsonPath("$.ngayNum").value(DEFAULT_NGAY_NUM.intValue()))
            .andExpect(jsonPath("$.ngayThaoTacRutTrich").value(DEFAULT_NGAY_THAO_TAC_RUT_TRICH.toString()))
            .andExpect(jsonPath("$.thuLaoCongChung").value(DEFAULT_THU_LAO_CONG_CHUNG.intValue()))
            .andExpect(jsonPath("$.quyenLaiSt").value(DEFAULT_QUYEN_LAI_ST))
            .andExpect(jsonPath("$.soLaiSt").value(DEFAULT_SO_LAI_ST))
            .andExpect(jsonPath("$.quyenLaiTl").value(DEFAULT_QUYEN_LAI_TL))
            .andExpect(jsonPath("$.soLaiTl").value(DEFAULT_SO_LAI_TL))
            .andExpect(jsonPath("$.srcKySoPdf").value(DEFAULT_SRC_KY_SO_PDF))
            .andExpect(jsonPath("$.srcKySoPdfSigned").value(DEFAULT_SRC_KY_SO_PDF_SIGNED))
            .andExpect(jsonPath("$.syncStatus").value(DEFAULT_SYNC_STATUS.intValue()))
            .andExpect(jsonPath("$.ngayRutTrichText").value(DEFAULT_NGAY_RUT_TRICH_TEXT));
    }

    @Test
    void getNonExistingHopDongCongChung() throws Exception {
        // Get the hopDongCongChung
        restHopDongCongChungMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingHopDongCongChung() throws Exception {
        // Initialize the database
        insertedHopDongCongChung = hopDongCongChungRepository.save(hopDongCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hopDongCongChung
        HopDongCongChung updatedHopDongCongChung = hopDongCongChungRepository.findById(hopDongCongChung.getId()).orElseThrow();
        updatedHopDongCongChung
            .ngayLapHd(UPDATED_NGAY_LAP_HD)
            .nguoiLapHd(UPDATED_NGUOI_LAP_HD)
            .thongTinDuongSu(UPDATED_THONG_TIN_DUONG_SU)
            .thongTinTaiSan(UPDATED_THONG_TIN_TAI_SAN)
            .thongTinVanBan(UPDATED_THONG_TIN_VAN_BAN)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .dieuKhoanHd(UPDATED_DIEU_KHOAN_HD)
            .idDonVi(UPDATED_ID_DON_VI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idHdGoc(UPDATED_ID_HD_GOC)
            .thongTinChuyenNhuong(UPDATED_THONG_TIN_CHUYEN_NHUONG)
            .maHopDong(UPDATED_MA_HOP_DONG)
            .srcHopDong(UPDATED_SRC_HOP_DONG)
            .ngayHen(UPDATED_NGAY_HEN)
            .idSoCongChung(UPDATED_ID_SO_CONG_CHUNG)
            .soCongChung(UPDATED_SO_CONG_CHUNG)
            .congChungVien(UPDATED_CONG_CHUNG_VIEN)
            .ngayKyHd(UPDATED_NGAY_KY_HD)
            .nguoiRutTrich(UPDATED_NGUOI_RUT_TRICH)
            .soTienRutTrich(UPDATED_SO_TIEN_RUT_TRICH)
            .ngayRutTrich(UPDATED_NGAY_RUT_TRICH)
            .hdThuCong(UPDATED_HD_THU_CONG)
            .trangThaiRutTrich(UPDATED_TRANG_THAI_RUT_TRICH)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .strSearch(UPDATED_STR_SEARCH)
            .idMaster(UPDATED_ID_MASTER)
            .idHdSdHb(UPDATED_ID_HD_SD_HB)
            .srcDmMaster(UPDATED_SRC_DM_MASTER)
            .repRefUnique(UPDATED_REP_REF_UNIQUE)
            .ngayText(UPDATED_NGAY_TEXT)
            .ngayNum(UPDATED_NGAY_NUM)
            .ngayThaoTacRutTrich(UPDATED_NGAY_THAO_TAC_RUT_TRICH)
            .thuLaoCongChung(UPDATED_THU_LAO_CONG_CHUNG)
            .quyenLaiSt(UPDATED_QUYEN_LAI_ST)
            .soLaiSt(UPDATED_SO_LAI_ST)
            .quyenLaiTl(UPDATED_QUYEN_LAI_TL)
            .soLaiTl(UPDATED_SO_LAI_TL)
            .srcKySoPdf(UPDATED_SRC_KY_SO_PDF)
            .srcKySoPdfSigned(UPDATED_SRC_KY_SO_PDF_SIGNED)
            .syncStatus(UPDATED_SYNC_STATUS)
            .ngayRutTrichText(UPDATED_NGAY_RUT_TRICH_TEXT);
        HopDongCongChungDTO hopDongCongChungDTO = hopDongCongChungMapper.toDto(updatedHopDongCongChung);

        restHopDongCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hopDongCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hopDongCongChungDTO))
            )
            .andExpect(status().isOk());

        // Validate the HopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHopDongCongChungToMatchAllProperties(updatedHopDongCongChung);
    }

    @Test
    void putNonExistingHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the HopDongCongChung
        HopDongCongChungDTO hopDongCongChungDTO = hopDongCongChungMapper.toDto(hopDongCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHopDongCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hopDongCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the HopDongCongChung
        HopDongCongChungDTO hopDongCongChungDTO = hopDongCongChungMapper.toDto(hopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHopDongCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the HopDongCongChung
        HopDongCongChungDTO hopDongCongChungDTO = hopDongCongChungMapper.toDto(hopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHopDongCongChungMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hopDongCongChungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateHopDongCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedHopDongCongChung = hopDongCongChungRepository.save(hopDongCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hopDongCongChung using partial update
        HopDongCongChung partialUpdatedHopDongCongChung = new HopDongCongChung();
        partialUpdatedHopDongCongChung.setId(hopDongCongChung.getId());

        partialUpdatedHopDongCongChung
            .thongTinVanBan(UPDATED_THONG_TIN_VAN_BAN)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .thongTinChuyenNhuong(UPDATED_THONG_TIN_CHUYEN_NHUONG)
            .soCongChung(UPDATED_SO_CONG_CHUNG)
            .congChungVien(UPDATED_CONG_CHUNG_VIEN)
            .ngayRutTrich(UPDATED_NGAY_RUT_TRICH)
            .hdThuCong(UPDATED_HD_THU_CONG)
            .trangThaiRutTrich(UPDATED_TRANG_THAI_RUT_TRICH)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .strSearch(UPDATED_STR_SEARCH)
            .idMaster(UPDATED_ID_MASTER)
            .idHdSdHb(UPDATED_ID_HD_SD_HB)
            .repRefUnique(UPDATED_REP_REF_UNIQUE)
            .ngayThaoTacRutTrich(UPDATED_NGAY_THAO_TAC_RUT_TRICH)
            .thuLaoCongChung(UPDATED_THU_LAO_CONG_CHUNG)
            .quyenLaiSt(UPDATED_QUYEN_LAI_ST)
            .soLaiSt(UPDATED_SO_LAI_ST)
            .srcKySoPdfSigned(UPDATED_SRC_KY_SO_PDF_SIGNED)
            .ngayRutTrichText(UPDATED_NGAY_RUT_TRICH_TEXT);

        restHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHopDongCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHopDongCongChung))
            )
            .andExpect(status().isOk());

        // Validate the HopDongCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHopDongCongChungUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHopDongCongChung, hopDongCongChung),
            getPersistedHopDongCongChung(hopDongCongChung)
        );
    }

    @Test
    void fullUpdateHopDongCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedHopDongCongChung = hopDongCongChungRepository.save(hopDongCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hopDongCongChung using partial update
        HopDongCongChung partialUpdatedHopDongCongChung = new HopDongCongChung();
        partialUpdatedHopDongCongChung.setId(hopDongCongChung.getId());

        partialUpdatedHopDongCongChung
            .ngayLapHd(UPDATED_NGAY_LAP_HD)
            .nguoiLapHd(UPDATED_NGUOI_LAP_HD)
            .thongTinDuongSu(UPDATED_THONG_TIN_DUONG_SU)
            .thongTinTaiSan(UPDATED_THONG_TIN_TAI_SAN)
            .thongTinVanBan(UPDATED_THONG_TIN_VAN_BAN)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .dieuKhoanHd(UPDATED_DIEU_KHOAN_HD)
            .idDonVi(UPDATED_ID_DON_VI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idHdGoc(UPDATED_ID_HD_GOC)
            .thongTinChuyenNhuong(UPDATED_THONG_TIN_CHUYEN_NHUONG)
            .maHopDong(UPDATED_MA_HOP_DONG)
            .srcHopDong(UPDATED_SRC_HOP_DONG)
            .ngayHen(UPDATED_NGAY_HEN)
            .idSoCongChung(UPDATED_ID_SO_CONG_CHUNG)
            .soCongChung(UPDATED_SO_CONG_CHUNG)
            .congChungVien(UPDATED_CONG_CHUNG_VIEN)
            .ngayKyHd(UPDATED_NGAY_KY_HD)
            .nguoiRutTrich(UPDATED_NGUOI_RUT_TRICH)
            .soTienRutTrich(UPDATED_SO_TIEN_RUT_TRICH)
            .ngayRutTrich(UPDATED_NGAY_RUT_TRICH)
            .hdThuCong(UPDATED_HD_THU_CONG)
            .trangThaiRutTrich(UPDATED_TRANG_THAI_RUT_TRICH)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .strSearch(UPDATED_STR_SEARCH)
            .idMaster(UPDATED_ID_MASTER)
            .idHdSdHb(UPDATED_ID_HD_SD_HB)
            .srcDmMaster(UPDATED_SRC_DM_MASTER)
            .repRefUnique(UPDATED_REP_REF_UNIQUE)
            .ngayText(UPDATED_NGAY_TEXT)
            .ngayNum(UPDATED_NGAY_NUM)
            .ngayThaoTacRutTrich(UPDATED_NGAY_THAO_TAC_RUT_TRICH)
            .thuLaoCongChung(UPDATED_THU_LAO_CONG_CHUNG)
            .quyenLaiSt(UPDATED_QUYEN_LAI_ST)
            .soLaiSt(UPDATED_SO_LAI_ST)
            .quyenLaiTl(UPDATED_QUYEN_LAI_TL)
            .soLaiTl(UPDATED_SO_LAI_TL)
            .srcKySoPdf(UPDATED_SRC_KY_SO_PDF)
            .srcKySoPdfSigned(UPDATED_SRC_KY_SO_PDF_SIGNED)
            .syncStatus(UPDATED_SYNC_STATUS)
            .ngayRutTrichText(UPDATED_NGAY_RUT_TRICH_TEXT);

        restHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHopDongCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHopDongCongChung))
            )
            .andExpect(status().isOk());

        // Validate the HopDongCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHopDongCongChungUpdatableFieldsEquals(
            partialUpdatedHopDongCongChung,
            getPersistedHopDongCongChung(partialUpdatedHopDongCongChung)
        );
    }

    @Test
    void patchNonExistingHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the HopDongCongChung
        HopDongCongChungDTO hopDongCongChungDTO = hopDongCongChungMapper.toDto(hopDongCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hopDongCongChungDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the HopDongCongChung
        HopDongCongChungDTO hopDongCongChungDTO = hopDongCongChungMapper.toDto(hopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopDongCongChung.setId(UUID.randomUUID().toString());

        // Create the HopDongCongChung
        HopDongCongChungDTO hopDongCongChungDTO = hopDongCongChungMapper.toDto(hopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHopDongCongChungMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(hopDongCongChungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteHopDongCongChung() throws Exception {
        // Initialize the database
        insertedHopDongCongChung = hopDongCongChungRepository.save(hopDongCongChung);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the hopDongCongChung
        restHopDongCongChungMockMvc
            .perform(delete(ENTITY_API_URL_ID, hopDongCongChung.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return hopDongCongChungRepository.count();
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

    protected HopDongCongChung getPersistedHopDongCongChung(HopDongCongChung hopDongCongChung) {
        return hopDongCongChungRepository.findById(hopDongCongChung.getId()).orElseThrow();
    }

    protected void assertPersistedHopDongCongChungToMatchAllProperties(HopDongCongChung expectedHopDongCongChung) {
        assertHopDongCongChungAllPropertiesEquals(expectedHopDongCongChung, getPersistedHopDongCongChung(expectedHopDongCongChung));
    }

    protected void assertPersistedHopDongCongChungToMatchUpdatableProperties(HopDongCongChung expectedHopDongCongChung) {
        assertHopDongCongChungAllUpdatablePropertiesEquals(
            expectedHopDongCongChung,
            getPersistedHopDongCongChung(expectedHopDongCongChung)
        );
    }
}
