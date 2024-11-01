package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.ThongTinChungHopDongAsserts.*;
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
import vn.vnpt.domain.ThongTinChungHopDong;
import vn.vnpt.repository.ThongTinChungHopDongRepository;
import vn.vnpt.service.dto.ThongTinChungHopDongDTO;
import vn.vnpt.service.mapper.ThongTinChungHopDongMapper;

/**
 * Integration tests for the {@link ThongTinChungHopDongResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ThongTinChungHopDongResourceIT {

    private static final LocalDate DEFAULT_NGAY_LAP_HD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_LAP_HD = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_LAP_HD = 1L;
    private static final Long UPDATED_NGUOI_LAP_HD = 2L;

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

    private static final String DEFAULT_MA_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_MA_HOP_DONG = "BBBBBBBBBB";

    private static final String DEFAULT_SRC_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_SRC_HOP_DONG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_HEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_HEN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ID_SO_CONG_CHUNG = "AAAAAAAAAA";
    private static final String UPDATED_ID_SO_CONG_CHUNG = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_CONG_CHUNG = 1L;
    private static final Long UPDATED_SO_CONG_CHUNG = 2L;

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

    private static final String DEFAULT_THONG_TIN_CHUNG = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_CHUNG = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_CHUNG_CLOB = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_CHUNG_CLOB = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/thong-tin-chung-hop-dongs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ThongTinChungHopDongRepository thongTinChungHopDongRepository;

    @Autowired
    private ThongTinChungHopDongMapper thongTinChungHopDongMapper;

    @Autowired
    private MockMvc restThongTinChungHopDongMockMvc;

    private ThongTinChungHopDong thongTinChungHopDong;

    private ThongTinChungHopDong insertedThongTinChungHopDong;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThongTinChungHopDong createEntity() {
        return new ThongTinChungHopDong()
            .ngayLapHd(DEFAULT_NGAY_LAP_HD)
            .nguoiLapHd(DEFAULT_NGUOI_LAP_HD)
            .thongTinVanBan(DEFAULT_THONG_TIN_VAN_BAN)
            .trangThai(DEFAULT_TRANG_THAI)
            .idLoaiHd(DEFAULT_ID_LOAI_HD)
            .dieuKhoanHd(DEFAULT_DIEU_KHOAN_HD)
            .idDonVi(DEFAULT_ID_DON_VI)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idHdGoc(DEFAULT_ID_HD_GOC)
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
            .thongTinChung(DEFAULT_THONG_TIN_CHUNG)
            .thongTinChungClob(DEFAULT_THONG_TIN_CHUNG_CLOB);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThongTinChungHopDong createUpdatedEntity() {
        return new ThongTinChungHopDong()
            .ngayLapHd(UPDATED_NGAY_LAP_HD)
            .nguoiLapHd(UPDATED_NGUOI_LAP_HD)
            .thongTinVanBan(UPDATED_THONG_TIN_VAN_BAN)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .dieuKhoanHd(UPDATED_DIEU_KHOAN_HD)
            .idDonVi(UPDATED_ID_DON_VI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idHdGoc(UPDATED_ID_HD_GOC)
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
            .thongTinChung(UPDATED_THONG_TIN_CHUNG)
            .thongTinChungClob(UPDATED_THONG_TIN_CHUNG_CLOB);
    }

    @BeforeEach
    public void initTest() {
        thongTinChungHopDong = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedThongTinChungHopDong != null) {
            thongTinChungHopDongRepository.delete(insertedThongTinChungHopDong);
            insertedThongTinChungHopDong = null;
        }
    }

    @Test
    void createThongTinChungHopDong() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ThongTinChungHopDong
        ThongTinChungHopDongDTO thongTinChungHopDongDTO = thongTinChungHopDongMapper.toDto(thongTinChungHopDong);
        var returnedThongTinChungHopDongDTO = om.readValue(
            restThongTinChungHopDongMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thongTinChungHopDongDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ThongTinChungHopDongDTO.class
        );

        // Validate the ThongTinChungHopDong in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedThongTinChungHopDong = thongTinChungHopDongMapper.toEntity(returnedThongTinChungHopDongDTO);
        assertThongTinChungHopDongUpdatableFieldsEquals(
            returnedThongTinChungHopDong,
            getPersistedThongTinChungHopDong(returnedThongTinChungHopDong)
        );

        insertedThongTinChungHopDong = returnedThongTinChungHopDong;
    }

    @Test
    void createThongTinChungHopDongWithExistingId() throws Exception {
        // Create the ThongTinChungHopDong with an existing ID
        thongTinChungHopDong.setId("existing_id");
        ThongTinChungHopDongDTO thongTinChungHopDongDTO = thongTinChungHopDongMapper.toDto(thongTinChungHopDong);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restThongTinChungHopDongMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thongTinChungHopDongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongTinChungHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllThongTinChungHopDongs() throws Exception {
        // Initialize the database
        insertedThongTinChungHopDong = thongTinChungHopDongRepository.save(thongTinChungHopDong);

        // Get all the thongTinChungHopDongList
        restThongTinChungHopDongMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thongTinChungHopDong.getId())))
            .andExpect(jsonPath("$.[*].ngayLapHd").value(hasItem(DEFAULT_NGAY_LAP_HD.toString())))
            .andExpect(jsonPath("$.[*].nguoiLapHd").value(hasItem(DEFAULT_NGUOI_LAP_HD.intValue())))
            .andExpect(jsonPath("$.[*].thongTinVanBan").value(hasItem(DEFAULT_THONG_TIN_VAN_BAN)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiHd").value(hasItem(DEFAULT_ID_LOAI_HD)))
            .andExpect(jsonPath("$.[*].dieuKhoanHd").value(hasItem(DEFAULT_DIEU_KHOAN_HD)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idHdGoc").value(hasItem(DEFAULT_ID_HD_GOC)))
            .andExpect(jsonPath("$.[*].maHopDong").value(hasItem(DEFAULT_MA_HOP_DONG)))
            .andExpect(jsonPath("$.[*].srcHopDong").value(hasItem(DEFAULT_SRC_HOP_DONG)))
            .andExpect(jsonPath("$.[*].ngayHen").value(hasItem(DEFAULT_NGAY_HEN.toString())))
            .andExpect(jsonPath("$.[*].idSoCongChung").value(hasItem(DEFAULT_ID_SO_CONG_CHUNG)))
            .andExpect(jsonPath("$.[*].soCongChung").value(hasItem(DEFAULT_SO_CONG_CHUNG.intValue())))
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
            .andExpect(jsonPath("$.[*].thongTinChung").value(hasItem(DEFAULT_THONG_TIN_CHUNG)))
            .andExpect(jsonPath("$.[*].thongTinChungClob").value(hasItem(DEFAULT_THONG_TIN_CHUNG_CLOB)));
    }

    @Test
    void getThongTinChungHopDong() throws Exception {
        // Initialize the database
        insertedThongTinChungHopDong = thongTinChungHopDongRepository.save(thongTinChungHopDong);

        // Get the thongTinChungHopDong
        restThongTinChungHopDongMockMvc
            .perform(get(ENTITY_API_URL_ID, thongTinChungHopDong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(thongTinChungHopDong.getId()))
            .andExpect(jsonPath("$.ngayLapHd").value(DEFAULT_NGAY_LAP_HD.toString()))
            .andExpect(jsonPath("$.nguoiLapHd").value(DEFAULT_NGUOI_LAP_HD.intValue()))
            .andExpect(jsonPath("$.thongTinVanBan").value(DEFAULT_THONG_TIN_VAN_BAN))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.idLoaiHd").value(DEFAULT_ID_LOAI_HD))
            .andExpect(jsonPath("$.dieuKhoanHd").value(DEFAULT_DIEU_KHOAN_HD))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idHdGoc").value(DEFAULT_ID_HD_GOC))
            .andExpect(jsonPath("$.maHopDong").value(DEFAULT_MA_HOP_DONG))
            .andExpect(jsonPath("$.srcHopDong").value(DEFAULT_SRC_HOP_DONG))
            .andExpect(jsonPath("$.ngayHen").value(DEFAULT_NGAY_HEN.toString()))
            .andExpect(jsonPath("$.idSoCongChung").value(DEFAULT_ID_SO_CONG_CHUNG))
            .andExpect(jsonPath("$.soCongChung").value(DEFAULT_SO_CONG_CHUNG.intValue()))
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
            .andExpect(jsonPath("$.thongTinChung").value(DEFAULT_THONG_TIN_CHUNG))
            .andExpect(jsonPath("$.thongTinChungClob").value(DEFAULT_THONG_TIN_CHUNG_CLOB));
    }

    @Test
    void getNonExistingThongTinChungHopDong() throws Exception {
        // Get the thongTinChungHopDong
        restThongTinChungHopDongMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingThongTinChungHopDong() throws Exception {
        // Initialize the database
        insertedThongTinChungHopDong = thongTinChungHopDongRepository.save(thongTinChungHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thongTinChungHopDong
        ThongTinChungHopDong updatedThongTinChungHopDong = thongTinChungHopDongRepository
            .findById(thongTinChungHopDong.getId())
            .orElseThrow();
        updatedThongTinChungHopDong
            .ngayLapHd(UPDATED_NGAY_LAP_HD)
            .nguoiLapHd(UPDATED_NGUOI_LAP_HD)
            .thongTinVanBan(UPDATED_THONG_TIN_VAN_BAN)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .dieuKhoanHd(UPDATED_DIEU_KHOAN_HD)
            .idDonVi(UPDATED_ID_DON_VI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idHdGoc(UPDATED_ID_HD_GOC)
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
            .thongTinChung(UPDATED_THONG_TIN_CHUNG)
            .thongTinChungClob(UPDATED_THONG_TIN_CHUNG_CLOB);
        ThongTinChungHopDongDTO thongTinChungHopDongDTO = thongTinChungHopDongMapper.toDto(updatedThongTinChungHopDong);

        restThongTinChungHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, thongTinChungHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thongTinChungHopDongDTO))
            )
            .andExpect(status().isOk());

        // Validate the ThongTinChungHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedThongTinChungHopDongToMatchAllProperties(updatedThongTinChungHopDong);
    }

    @Test
    void putNonExistingThongTinChungHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinChungHopDong.setId(UUID.randomUUID().toString());

        // Create the ThongTinChungHopDong
        ThongTinChungHopDongDTO thongTinChungHopDongDTO = thongTinChungHopDongMapper.toDto(thongTinChungHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongTinChungHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, thongTinChungHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thongTinChungHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinChungHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchThongTinChungHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinChungHopDong.setId(UUID.randomUUID().toString());

        // Create the ThongTinChungHopDong
        ThongTinChungHopDongDTO thongTinChungHopDongDTO = thongTinChungHopDongMapper.toDto(thongTinChungHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinChungHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thongTinChungHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinChungHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamThongTinChungHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinChungHopDong.setId(UUID.randomUUID().toString());

        // Create the ThongTinChungHopDong
        ThongTinChungHopDongDTO thongTinChungHopDongDTO = thongTinChungHopDongMapper.toDto(thongTinChungHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinChungHopDongMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thongTinChungHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ThongTinChungHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateThongTinChungHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedThongTinChungHopDong = thongTinChungHopDongRepository.save(thongTinChungHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thongTinChungHopDong using partial update
        ThongTinChungHopDong partialUpdatedThongTinChungHopDong = new ThongTinChungHopDong();
        partialUpdatedThongTinChungHopDong.setId(thongTinChungHopDong.getId());

        partialUpdatedThongTinChungHopDong
            .ngayLapHd(UPDATED_NGAY_LAP_HD)
            .thongTinVanBan(UPDATED_THONG_TIN_VAN_BAN)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idHdGoc(UPDATED_ID_HD_GOC)
            .maHopDong(UPDATED_MA_HOP_DONG)
            .ngayHen(UPDATED_NGAY_HEN)
            .ngayKyHd(UPDATED_NGAY_KY_HD)
            .nguoiRutTrich(UPDATED_NGUOI_RUT_TRICH)
            .soTienRutTrich(UPDATED_SO_TIEN_RUT_TRICH)
            .hdThuCong(UPDATED_HD_THU_CONG)
            .trangThaiRutTrich(UPDATED_TRANG_THAI_RUT_TRICH)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .strSearch(UPDATED_STR_SEARCH)
            .ngayText(UPDATED_NGAY_TEXT)
            .thongTinChungClob(UPDATED_THONG_TIN_CHUNG_CLOB);

        restThongTinChungHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedThongTinChungHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedThongTinChungHopDong))
            )
            .andExpect(status().isOk());

        // Validate the ThongTinChungHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertThongTinChungHopDongUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedThongTinChungHopDong, thongTinChungHopDong),
            getPersistedThongTinChungHopDong(thongTinChungHopDong)
        );
    }

    @Test
    void fullUpdateThongTinChungHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedThongTinChungHopDong = thongTinChungHopDongRepository.save(thongTinChungHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thongTinChungHopDong using partial update
        ThongTinChungHopDong partialUpdatedThongTinChungHopDong = new ThongTinChungHopDong();
        partialUpdatedThongTinChungHopDong.setId(thongTinChungHopDong.getId());

        partialUpdatedThongTinChungHopDong
            .ngayLapHd(UPDATED_NGAY_LAP_HD)
            .nguoiLapHd(UPDATED_NGUOI_LAP_HD)
            .thongTinVanBan(UPDATED_THONG_TIN_VAN_BAN)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .dieuKhoanHd(UPDATED_DIEU_KHOAN_HD)
            .idDonVi(UPDATED_ID_DON_VI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idHdGoc(UPDATED_ID_HD_GOC)
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
            .thongTinChung(UPDATED_THONG_TIN_CHUNG)
            .thongTinChungClob(UPDATED_THONG_TIN_CHUNG_CLOB);

        restThongTinChungHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedThongTinChungHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedThongTinChungHopDong))
            )
            .andExpect(status().isOk());

        // Validate the ThongTinChungHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertThongTinChungHopDongUpdatableFieldsEquals(
            partialUpdatedThongTinChungHopDong,
            getPersistedThongTinChungHopDong(partialUpdatedThongTinChungHopDong)
        );
    }

    @Test
    void patchNonExistingThongTinChungHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinChungHopDong.setId(UUID.randomUUID().toString());

        // Create the ThongTinChungHopDong
        ThongTinChungHopDongDTO thongTinChungHopDongDTO = thongTinChungHopDongMapper.toDto(thongTinChungHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongTinChungHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, thongTinChungHopDongDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(thongTinChungHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinChungHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchThongTinChungHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinChungHopDong.setId(UUID.randomUUID().toString());

        // Create the ThongTinChungHopDong
        ThongTinChungHopDongDTO thongTinChungHopDongDTO = thongTinChungHopDongMapper.toDto(thongTinChungHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinChungHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(thongTinChungHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinChungHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamThongTinChungHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinChungHopDong.setId(UUID.randomUUID().toString());

        // Create the ThongTinChungHopDong
        ThongTinChungHopDongDTO thongTinChungHopDongDTO = thongTinChungHopDongMapper.toDto(thongTinChungHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinChungHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(thongTinChungHopDongDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ThongTinChungHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteThongTinChungHopDong() throws Exception {
        // Initialize the database
        insertedThongTinChungHopDong = thongTinChungHopDongRepository.save(thongTinChungHopDong);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the thongTinChungHopDong
        restThongTinChungHopDongMockMvc
            .perform(delete(ENTITY_API_URL_ID, thongTinChungHopDong.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return thongTinChungHopDongRepository.count();
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

    protected ThongTinChungHopDong getPersistedThongTinChungHopDong(ThongTinChungHopDong thongTinChungHopDong) {
        return thongTinChungHopDongRepository.findById(thongTinChungHopDong.getId()).orElseThrow();
    }

    protected void assertPersistedThongTinChungHopDongToMatchAllProperties(ThongTinChungHopDong expectedThongTinChungHopDong) {
        assertThongTinChungHopDongAllPropertiesEquals(
            expectedThongTinChungHopDong,
            getPersistedThongTinChungHopDong(expectedThongTinChungHopDong)
        );
    }

    protected void assertPersistedThongTinChungHopDongToMatchUpdatableProperties(ThongTinChungHopDong expectedThongTinChungHopDong) {
        assertThongTinChungHopDongAllUpdatablePropertiesEquals(
            expectedThongTinChungHopDong,
            getPersistedThongTinChungHopDong(expectedThongTinChungHopDong)
        );
    }
}
