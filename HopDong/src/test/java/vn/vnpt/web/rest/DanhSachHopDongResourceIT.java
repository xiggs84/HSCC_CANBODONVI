package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhSachHopDongAsserts.*;
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
import vn.vnpt.domain.DanhSachHopDong;
import vn.vnpt.repository.DanhSachHopDongRepository;
import vn.vnpt.service.dto.DanhSachHopDongDTO;
import vn.vnpt.service.mapper.DanhSachHopDongMapper;

/**
 * Integration tests for the {@link DanhSachHopDongResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhSachHopDongResourceIT {

    private static final LocalDate DEFAULT_NGAY_LAP_HD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_LAP_HD = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_LAP_HD = 1L;
    private static final Long UPDATED_NGUOI_LAP_HD = 2L;

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String DEFAULT_ID_LOAI_HD = "AAAAAAAAAA";
    private static final String UPDATED_ID_LOAI_HD = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final String DEFAULT_SRC_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_SRC_HOP_DONG = "BBBBBBBBBB";

    private static final String DEFAULT_ID_SO_CONG_CHUNG = "AAAAAAAAAA";
    private static final String UPDATED_ID_SO_CONG_CHUNG = "BBBBBBBBBB";

    private static final String DEFAULT_SO_CONG_CHUNG = "AAAAAAAAAA";
    private static final String UPDATED_SO_CONG_CHUNG = "BBBBBBBBBB";

    private static final Long DEFAULT_CONG_CHUNG_VIEN = 1L;
    private static final Long UPDATED_CONG_CHUNG_VIEN = 2L;

    private static final Long DEFAULT_SO_TIEN_RUT_TRICH = 1L;
    private static final Long UPDATED_SO_TIEN_RUT_TRICH = 2L;

    private static final Long DEFAULT_HD_THU_CONG = 1L;
    private static final Long UPDATED_HD_THU_CONG = 2L;

    private static final Long DEFAULT_TRANG_THAI_RUT_TRICH = 1L;
    private static final Long UPDATED_TRANG_THAI_RUT_TRICH = 2L;

    private static final Long DEFAULT_CHU_KY_NGOAI_TRU_SO = 1L;
    private static final Long UPDATED_CHU_KY_NGOAI_TRU_SO = 2L;

    private static final String DEFAULT_STR_SEARCH = "AAAAAAAAAA";
    private static final String UPDATED_STR_SEARCH = "BBBBBBBBBB";

    private static final String DEFAULT_NGAY_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_NGAY_RUT_TRICH_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_RUT_TRICH_TEXT = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/danh-sach-hop-dongs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhSachHopDongRepository danhSachHopDongRepository;

    @Autowired
    private DanhSachHopDongMapper danhSachHopDongMapper;

    @Autowired
    private MockMvc restDanhSachHopDongMockMvc;

    private DanhSachHopDong danhSachHopDong;

    private DanhSachHopDong insertedDanhSachHopDong;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhSachHopDong createEntity() {
        return new DanhSachHopDong()
            .ngayLapHd(DEFAULT_NGAY_LAP_HD)
            .nguoiLapHd(DEFAULT_NGUOI_LAP_HD)
            .trangThai(DEFAULT_TRANG_THAI)
            .idLoaiHd(DEFAULT_ID_LOAI_HD)
            .idDonVi(DEFAULT_ID_DON_VI)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .srcHopDong(DEFAULT_SRC_HOP_DONG)
            .idSoCongChung(DEFAULT_ID_SO_CONG_CHUNG)
            .soCongChung(DEFAULT_SO_CONG_CHUNG)
            .congChungVien(DEFAULT_CONG_CHUNG_VIEN)
            .soTienRutTrich(DEFAULT_SO_TIEN_RUT_TRICH)
            .hdThuCong(DEFAULT_HD_THU_CONG)
            .trangThaiRutTrich(DEFAULT_TRANG_THAI_RUT_TRICH)
            .chuKyNgoaiTruSo(DEFAULT_CHU_KY_NGOAI_TRU_SO)
            .strSearch(DEFAULT_STR_SEARCH)
            .ngayText(DEFAULT_NGAY_TEXT)
            .ngayRutTrichText(DEFAULT_NGAY_RUT_TRICH_TEXT)
            .ngayThaoTacRutTrich(DEFAULT_NGAY_THAO_TAC_RUT_TRICH)
            .thuLaoCongChung(DEFAULT_THU_LAO_CONG_CHUNG)
            .quyenLaiSt(DEFAULT_QUYEN_LAI_ST)
            .soLaiSt(DEFAULT_SO_LAI_ST)
            .quyenLaiTl(DEFAULT_QUYEN_LAI_TL)
            .soLaiTl(DEFAULT_SO_LAI_TL)
            .srcKySoPdf(DEFAULT_SRC_KY_SO_PDF)
            .srcKySoPdfSigned(DEFAULT_SRC_KY_SO_PDF_SIGNED);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhSachHopDong createUpdatedEntity() {
        return new DanhSachHopDong()
            .ngayLapHd(UPDATED_NGAY_LAP_HD)
            .nguoiLapHd(UPDATED_NGUOI_LAP_HD)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .idDonVi(UPDATED_ID_DON_VI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .srcHopDong(UPDATED_SRC_HOP_DONG)
            .idSoCongChung(UPDATED_ID_SO_CONG_CHUNG)
            .soCongChung(UPDATED_SO_CONG_CHUNG)
            .congChungVien(UPDATED_CONG_CHUNG_VIEN)
            .soTienRutTrich(UPDATED_SO_TIEN_RUT_TRICH)
            .hdThuCong(UPDATED_HD_THU_CONG)
            .trangThaiRutTrich(UPDATED_TRANG_THAI_RUT_TRICH)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .strSearch(UPDATED_STR_SEARCH)
            .ngayText(UPDATED_NGAY_TEXT)
            .ngayRutTrichText(UPDATED_NGAY_RUT_TRICH_TEXT)
            .ngayThaoTacRutTrich(UPDATED_NGAY_THAO_TAC_RUT_TRICH)
            .thuLaoCongChung(UPDATED_THU_LAO_CONG_CHUNG)
            .quyenLaiSt(UPDATED_QUYEN_LAI_ST)
            .soLaiSt(UPDATED_SO_LAI_ST)
            .quyenLaiTl(UPDATED_QUYEN_LAI_TL)
            .soLaiTl(UPDATED_SO_LAI_TL)
            .srcKySoPdf(UPDATED_SRC_KY_SO_PDF)
            .srcKySoPdfSigned(UPDATED_SRC_KY_SO_PDF_SIGNED);
    }

    @BeforeEach
    public void initTest() {
        danhSachHopDong = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhSachHopDong != null) {
            danhSachHopDongRepository.delete(insertedDanhSachHopDong);
            insertedDanhSachHopDong = null;
        }
    }

    @Test
    void createDanhSachHopDong() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhSachHopDong
        DanhSachHopDongDTO danhSachHopDongDTO = danhSachHopDongMapper.toDto(danhSachHopDong);
        var returnedDanhSachHopDongDTO = om.readValue(
            restDanhSachHopDongMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachHopDongDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhSachHopDongDTO.class
        );

        // Validate the DanhSachHopDong in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhSachHopDong = danhSachHopDongMapper.toEntity(returnedDanhSachHopDongDTO);
        assertDanhSachHopDongUpdatableFieldsEquals(returnedDanhSachHopDong, getPersistedDanhSachHopDong(returnedDanhSachHopDong));

        insertedDanhSachHopDong = returnedDanhSachHopDong;
    }

    @Test
    void createDanhSachHopDongWithExistingId() throws Exception {
        // Create the DanhSachHopDong with an existing ID
        danhSachHopDong.setId("existing_id");
        DanhSachHopDongDTO danhSachHopDongDTO = danhSachHopDongMapper.toDto(danhSachHopDong);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhSachHopDongMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachHopDongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhSachHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDanhSachHopDongs() throws Exception {
        // Initialize the database
        insertedDanhSachHopDong = danhSachHopDongRepository.save(danhSachHopDong);

        // Get all the danhSachHopDongList
        restDanhSachHopDongMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhSachHopDong.getId())))
            .andExpect(jsonPath("$.[*].ngayLapHd").value(hasItem(DEFAULT_NGAY_LAP_HD.toString())))
            .andExpect(jsonPath("$.[*].nguoiLapHd").value(hasItem(DEFAULT_NGUOI_LAP_HD.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiHd").value(hasItem(DEFAULT_ID_LOAI_HD)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].srcHopDong").value(hasItem(DEFAULT_SRC_HOP_DONG)))
            .andExpect(jsonPath("$.[*].idSoCongChung").value(hasItem(DEFAULT_ID_SO_CONG_CHUNG)))
            .andExpect(jsonPath("$.[*].soCongChung").value(hasItem(DEFAULT_SO_CONG_CHUNG)))
            .andExpect(jsonPath("$.[*].congChungVien").value(hasItem(DEFAULT_CONG_CHUNG_VIEN.intValue())))
            .andExpect(jsonPath("$.[*].soTienRutTrich").value(hasItem(DEFAULT_SO_TIEN_RUT_TRICH.intValue())))
            .andExpect(jsonPath("$.[*].hdThuCong").value(hasItem(DEFAULT_HD_THU_CONG.intValue())))
            .andExpect(jsonPath("$.[*].trangThaiRutTrich").value(hasItem(DEFAULT_TRANG_THAI_RUT_TRICH.intValue())))
            .andExpect(jsonPath("$.[*].chuKyNgoaiTruSo").value(hasItem(DEFAULT_CHU_KY_NGOAI_TRU_SO.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].ngayText").value(hasItem(DEFAULT_NGAY_TEXT)))
            .andExpect(jsonPath("$.[*].ngayRutTrichText").value(hasItem(DEFAULT_NGAY_RUT_TRICH_TEXT)))
            .andExpect(jsonPath("$.[*].ngayThaoTacRutTrich").value(hasItem(DEFAULT_NGAY_THAO_TAC_RUT_TRICH.toString())))
            .andExpect(jsonPath("$.[*].thuLaoCongChung").value(hasItem(DEFAULT_THU_LAO_CONG_CHUNG.intValue())))
            .andExpect(jsonPath("$.[*].quyenLaiSt").value(hasItem(DEFAULT_QUYEN_LAI_ST)))
            .andExpect(jsonPath("$.[*].soLaiSt").value(hasItem(DEFAULT_SO_LAI_ST)))
            .andExpect(jsonPath("$.[*].quyenLaiTl").value(hasItem(DEFAULT_QUYEN_LAI_TL)))
            .andExpect(jsonPath("$.[*].soLaiTl").value(hasItem(DEFAULT_SO_LAI_TL)))
            .andExpect(jsonPath("$.[*].srcKySoPdf").value(hasItem(DEFAULT_SRC_KY_SO_PDF)))
            .andExpect(jsonPath("$.[*].srcKySoPdfSigned").value(hasItem(DEFAULT_SRC_KY_SO_PDF_SIGNED)));
    }

    @Test
    void getDanhSachHopDong() throws Exception {
        // Initialize the database
        insertedDanhSachHopDong = danhSachHopDongRepository.save(danhSachHopDong);

        // Get the danhSachHopDong
        restDanhSachHopDongMockMvc
            .perform(get(ENTITY_API_URL_ID, danhSachHopDong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhSachHopDong.getId()))
            .andExpect(jsonPath("$.ngayLapHd").value(DEFAULT_NGAY_LAP_HD.toString()))
            .andExpect(jsonPath("$.nguoiLapHd").value(DEFAULT_NGUOI_LAP_HD.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.idLoaiHd").value(DEFAULT_ID_LOAI_HD))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.srcHopDong").value(DEFAULT_SRC_HOP_DONG))
            .andExpect(jsonPath("$.idSoCongChung").value(DEFAULT_ID_SO_CONG_CHUNG))
            .andExpect(jsonPath("$.soCongChung").value(DEFAULT_SO_CONG_CHUNG))
            .andExpect(jsonPath("$.congChungVien").value(DEFAULT_CONG_CHUNG_VIEN.intValue()))
            .andExpect(jsonPath("$.soTienRutTrich").value(DEFAULT_SO_TIEN_RUT_TRICH.intValue()))
            .andExpect(jsonPath("$.hdThuCong").value(DEFAULT_HD_THU_CONG.intValue()))
            .andExpect(jsonPath("$.trangThaiRutTrich").value(DEFAULT_TRANG_THAI_RUT_TRICH.intValue()))
            .andExpect(jsonPath("$.chuKyNgoaiTruSo").value(DEFAULT_CHU_KY_NGOAI_TRU_SO.intValue()))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.ngayText").value(DEFAULT_NGAY_TEXT))
            .andExpect(jsonPath("$.ngayRutTrichText").value(DEFAULT_NGAY_RUT_TRICH_TEXT))
            .andExpect(jsonPath("$.ngayThaoTacRutTrich").value(DEFAULT_NGAY_THAO_TAC_RUT_TRICH.toString()))
            .andExpect(jsonPath("$.thuLaoCongChung").value(DEFAULT_THU_LAO_CONG_CHUNG.intValue()))
            .andExpect(jsonPath("$.quyenLaiSt").value(DEFAULT_QUYEN_LAI_ST))
            .andExpect(jsonPath("$.soLaiSt").value(DEFAULT_SO_LAI_ST))
            .andExpect(jsonPath("$.quyenLaiTl").value(DEFAULT_QUYEN_LAI_TL))
            .andExpect(jsonPath("$.soLaiTl").value(DEFAULT_SO_LAI_TL))
            .andExpect(jsonPath("$.srcKySoPdf").value(DEFAULT_SRC_KY_SO_PDF))
            .andExpect(jsonPath("$.srcKySoPdfSigned").value(DEFAULT_SRC_KY_SO_PDF_SIGNED));
    }

    @Test
    void getNonExistingDanhSachHopDong() throws Exception {
        // Get the danhSachHopDong
        restDanhSachHopDongMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDanhSachHopDong() throws Exception {
        // Initialize the database
        insertedDanhSachHopDong = danhSachHopDongRepository.save(danhSachHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachHopDong
        DanhSachHopDong updatedDanhSachHopDong = danhSachHopDongRepository.findById(danhSachHopDong.getId()).orElseThrow();
        updatedDanhSachHopDong
            .ngayLapHd(UPDATED_NGAY_LAP_HD)
            .nguoiLapHd(UPDATED_NGUOI_LAP_HD)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .idDonVi(UPDATED_ID_DON_VI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .srcHopDong(UPDATED_SRC_HOP_DONG)
            .idSoCongChung(UPDATED_ID_SO_CONG_CHUNG)
            .soCongChung(UPDATED_SO_CONG_CHUNG)
            .congChungVien(UPDATED_CONG_CHUNG_VIEN)
            .soTienRutTrich(UPDATED_SO_TIEN_RUT_TRICH)
            .hdThuCong(UPDATED_HD_THU_CONG)
            .trangThaiRutTrich(UPDATED_TRANG_THAI_RUT_TRICH)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .strSearch(UPDATED_STR_SEARCH)
            .ngayText(UPDATED_NGAY_TEXT)
            .ngayRutTrichText(UPDATED_NGAY_RUT_TRICH_TEXT)
            .ngayThaoTacRutTrich(UPDATED_NGAY_THAO_TAC_RUT_TRICH)
            .thuLaoCongChung(UPDATED_THU_LAO_CONG_CHUNG)
            .quyenLaiSt(UPDATED_QUYEN_LAI_ST)
            .soLaiSt(UPDATED_SO_LAI_ST)
            .quyenLaiTl(UPDATED_QUYEN_LAI_TL)
            .soLaiTl(UPDATED_SO_LAI_TL)
            .srcKySoPdf(UPDATED_SRC_KY_SO_PDF)
            .srcKySoPdfSigned(UPDATED_SRC_KY_SO_PDF_SIGNED);
        DanhSachHopDongDTO danhSachHopDongDTO = danhSachHopDongMapper.toDto(updatedDanhSachHopDong);

        restDanhSachHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhSachHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachHopDongDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhSachHopDongToMatchAllProperties(updatedDanhSachHopDong);
    }

    @Test
    void putNonExistingDanhSachHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachHopDong.setId(UUID.randomUUID().toString());

        // Create the DanhSachHopDong
        DanhSachHopDongDTO danhSachHopDongDTO = danhSachHopDongMapper.toDto(danhSachHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhSachHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhSachHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDanhSachHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachHopDong.setId(UUID.randomUUID().toString());

        // Create the DanhSachHopDong
        DanhSachHopDongDTO danhSachHopDongDTO = danhSachHopDongMapper.toDto(danhSachHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDanhSachHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachHopDong.setId(UUID.randomUUID().toString());

        // Create the DanhSachHopDong
        DanhSachHopDongDTO danhSachHopDongDTO = danhSachHopDongMapper.toDto(danhSachHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachHopDongMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhSachHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDanhSachHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedDanhSachHopDong = danhSachHopDongRepository.save(danhSachHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachHopDong using partial update
        DanhSachHopDong partialUpdatedDanhSachHopDong = new DanhSachHopDong();
        partialUpdatedDanhSachHopDong.setId(danhSachHopDong.getId());

        partialUpdatedDanhSachHopDong
            .nguoiLapHd(UPDATED_NGUOI_LAP_HD)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idSoCongChung(UPDATED_ID_SO_CONG_CHUNG)
            .soTienRutTrich(UPDATED_SO_TIEN_RUT_TRICH)
            .hdThuCong(UPDATED_HD_THU_CONG)
            .strSearch(UPDATED_STR_SEARCH)
            .ngayRutTrichText(UPDATED_NGAY_RUT_TRICH_TEXT)
            .ngayThaoTacRutTrich(UPDATED_NGAY_THAO_TAC_RUT_TRICH)
            .thuLaoCongChung(UPDATED_THU_LAO_CONG_CHUNG)
            .quyenLaiSt(UPDATED_QUYEN_LAI_ST)
            .quyenLaiTl(UPDATED_QUYEN_LAI_TL)
            .soLaiTl(UPDATED_SO_LAI_TL)
            .srcKySoPdf(UPDATED_SRC_KY_SO_PDF);

        restDanhSachHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhSachHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhSachHopDong))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhSachHopDongUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhSachHopDong, danhSachHopDong),
            getPersistedDanhSachHopDong(danhSachHopDong)
        );
    }

    @Test
    void fullUpdateDanhSachHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedDanhSachHopDong = danhSachHopDongRepository.save(danhSachHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachHopDong using partial update
        DanhSachHopDong partialUpdatedDanhSachHopDong = new DanhSachHopDong();
        partialUpdatedDanhSachHopDong.setId(danhSachHopDong.getId());

        partialUpdatedDanhSachHopDong
            .ngayLapHd(UPDATED_NGAY_LAP_HD)
            .nguoiLapHd(UPDATED_NGUOI_LAP_HD)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .idDonVi(UPDATED_ID_DON_VI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .srcHopDong(UPDATED_SRC_HOP_DONG)
            .idSoCongChung(UPDATED_ID_SO_CONG_CHUNG)
            .soCongChung(UPDATED_SO_CONG_CHUNG)
            .congChungVien(UPDATED_CONG_CHUNG_VIEN)
            .soTienRutTrich(UPDATED_SO_TIEN_RUT_TRICH)
            .hdThuCong(UPDATED_HD_THU_CONG)
            .trangThaiRutTrich(UPDATED_TRANG_THAI_RUT_TRICH)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .strSearch(UPDATED_STR_SEARCH)
            .ngayText(UPDATED_NGAY_TEXT)
            .ngayRutTrichText(UPDATED_NGAY_RUT_TRICH_TEXT)
            .ngayThaoTacRutTrich(UPDATED_NGAY_THAO_TAC_RUT_TRICH)
            .thuLaoCongChung(UPDATED_THU_LAO_CONG_CHUNG)
            .quyenLaiSt(UPDATED_QUYEN_LAI_ST)
            .soLaiSt(UPDATED_SO_LAI_ST)
            .quyenLaiTl(UPDATED_QUYEN_LAI_TL)
            .soLaiTl(UPDATED_SO_LAI_TL)
            .srcKySoPdf(UPDATED_SRC_KY_SO_PDF)
            .srcKySoPdfSigned(UPDATED_SRC_KY_SO_PDF_SIGNED);

        restDanhSachHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhSachHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhSachHopDong))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhSachHopDongUpdatableFieldsEquals(
            partialUpdatedDanhSachHopDong,
            getPersistedDanhSachHopDong(partialUpdatedDanhSachHopDong)
        );
    }

    @Test
    void patchNonExistingDanhSachHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachHopDong.setId(UUID.randomUUID().toString());

        // Create the DanhSachHopDong
        DanhSachHopDongDTO danhSachHopDongDTO = danhSachHopDongMapper.toDto(danhSachHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhSachHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhSachHopDongDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhSachHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDanhSachHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachHopDong.setId(UUID.randomUUID().toString());

        // Create the DanhSachHopDong
        DanhSachHopDongDTO danhSachHopDongDTO = danhSachHopDongMapper.toDto(danhSachHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhSachHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDanhSachHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachHopDong.setId(UUID.randomUUID().toString());

        // Create the DanhSachHopDong
        DanhSachHopDongDTO danhSachHopDongDTO = danhSachHopDongMapper.toDto(danhSachHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachHopDongMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhSachHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhSachHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDanhSachHopDong() throws Exception {
        // Initialize the database
        insertedDanhSachHopDong = danhSachHopDongRepository.save(danhSachHopDong);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhSachHopDong
        restDanhSachHopDongMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhSachHopDong.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhSachHopDongRepository.count();
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

    protected DanhSachHopDong getPersistedDanhSachHopDong(DanhSachHopDong danhSachHopDong) {
        return danhSachHopDongRepository.findById(danhSachHopDong.getId()).orElseThrow();
    }

    protected void assertPersistedDanhSachHopDongToMatchAllProperties(DanhSachHopDong expectedDanhSachHopDong) {
        assertDanhSachHopDongAllPropertiesEquals(expectedDanhSachHopDong, getPersistedDanhSachHopDong(expectedDanhSachHopDong));
    }

    protected void assertPersistedDanhSachHopDongToMatchUpdatableProperties(DanhSachHopDong expectedDanhSachHopDong) {
        assertDanhSachHopDongAllUpdatablePropertiesEquals(expectedDanhSachHopDong, getPersistedDanhSachHopDong(expectedDanhSachHopDong));
    }
}
