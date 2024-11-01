package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.ChungThucAsserts.*;
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
import vn.vnpt.domain.ChungThuc;
import vn.vnpt.repository.ChungThucRepository;
import vn.vnpt.service.dto.ChungThucDTO;
import vn.vnpt.service.mapper.ChungThucMapper;

/**
 * Integration tests for the {@link ChungThucResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChungThucResourceIT {

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final String DEFAULT_NGUOI_YEU_CAU = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_YEU_CAU = "BBBBBBBBBB";

    private static final Long DEFAULT_NGUOI_CHUNG_THUC = 1L;
    private static final Long UPDATED_NGUOI_CHUNG_THUC = 2L;

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final LocalDate DEFAULT_NGAY_CHUNG_THUC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CHUNG_THUC = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_SO_TIEN_THU = 1L;
    private static final Long UPDATED_SO_TIEN_THU = 2L;

    private static final Long DEFAULT_SO_BAN_SAO = 1L;
    private static final Long UPDATED_SO_BAN_SAO = 2L;

    private static final String DEFAULT_VAN_BAN = "AAAAAAAAAA";
    private static final String UPDATED_VAN_BAN = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final Long DEFAULT_QUYEN_SO = 1L;
    private static final Long UPDATED_QUYEN_SO = 2L;

    private static final String DEFAULT_DUONG_SU = "AAAAAAAAAA";
    private static final String UPDATED_DUONG_SU = "BBBBBBBBBB";

    private static final String DEFAULT_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_TAI_SAN = "BBBBBBBBBB";

    private static final String DEFAULT_STR_SEARCH = "AAAAAAAAAA";
    private static final String UPDATED_STR_SEARCH = "BBBBBBBBBB";

    private static final String DEFAULT_SRC_CHUNG_THUC = "AAAAAAAAAA";
    private static final String UPDATED_SRC_CHUNG_THUC = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_CHUNG_THUC = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_CHUNG_THUC = "BBBBBBBBBB";

    private static final Long DEFAULT_CHU_KY_NGOAI_TRU_SO = 1L;
    private static final Long UPDATED_CHU_KY_NGOAI_TRU_SO = 2L;

    private static final Long DEFAULT_ID_CT_GOC = 1L;
    private static final Long UPDATED_ID_CT_GOC = 2L;

    private static final String DEFAULT_NGAY_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CHUC_DANH_CAN_BO = "AAAAAAAAAA";
    private static final String UPDATED_CHUC_DANH_CAN_BO = "BBBBBBBBBB";

    private static final Long DEFAULT_LD_PHE_DUYET = 1L;
    private static final Long UPDATED_LD_PHE_DUYET = 2L;

    private static final String DEFAULT_CHUC_DANH_LD = "AAAAAAAAAA";
    private static final String UPDATED_CHUC_DANH_LD = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/chung-thucs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ChungThucRepository chungThucRepository;

    @Autowired
    private ChungThucMapper chungThucMapper;

    @Autowired
    private MockMvc restChungThucMockMvc;

    private ChungThuc chungThuc;

    private ChungThuc insertedChungThuc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChungThuc createEntity() {
        return new ChungThuc()
            .idDonVi(DEFAULT_ID_DON_VI)
            .nguoiYeuCau(DEFAULT_NGUOI_YEU_CAU)
            .nguoiChungThuc(DEFAULT_NGUOI_CHUNG_THUC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .ngayChungThuc(DEFAULT_NGAY_CHUNG_THUC)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .soTienThu(DEFAULT_SO_TIEN_THU)
            .soBanSao(DEFAULT_SO_BAN_SAO)
            .vanBan(DEFAULT_VAN_BAN)
            .trangThai(DEFAULT_TRANG_THAI)
            .quyenSo(DEFAULT_QUYEN_SO)
            .duongSu(DEFAULT_DUONG_SU)
            .taiSan(DEFAULT_TAI_SAN)
            .strSearch(DEFAULT_STR_SEARCH)
            .srcChungThuc(DEFAULT_SRC_CHUNG_THUC)
            .thongTinChungThuc(DEFAULT_THONG_TIN_CHUNG_THUC)
            .chuKyNgoaiTruSo(DEFAULT_CHU_KY_NGOAI_TRU_SO)
            .idCtGoc(DEFAULT_ID_CT_GOC)
            .ngayText(DEFAULT_NGAY_TEXT)
            .chucDanhCanBo(DEFAULT_CHUC_DANH_CAN_BO)
            .ldPheDuyet(DEFAULT_LD_PHE_DUYET)
            .chucDanhLd(DEFAULT_CHUC_DANH_LD);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChungThuc createUpdatedEntity() {
        return new ChungThuc()
            .idDonVi(UPDATED_ID_DON_VI)
            .nguoiYeuCau(UPDATED_NGUOI_YEU_CAU)
            .nguoiChungThuc(UPDATED_NGUOI_CHUNG_THUC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .ngayChungThuc(UPDATED_NGAY_CHUNG_THUC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .soTienThu(UPDATED_SO_TIEN_THU)
            .soBanSao(UPDATED_SO_BAN_SAO)
            .vanBan(UPDATED_VAN_BAN)
            .trangThai(UPDATED_TRANG_THAI)
            .quyenSo(UPDATED_QUYEN_SO)
            .duongSu(UPDATED_DUONG_SU)
            .taiSan(UPDATED_TAI_SAN)
            .strSearch(UPDATED_STR_SEARCH)
            .srcChungThuc(UPDATED_SRC_CHUNG_THUC)
            .thongTinChungThuc(UPDATED_THONG_TIN_CHUNG_THUC)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .idCtGoc(UPDATED_ID_CT_GOC)
            .ngayText(UPDATED_NGAY_TEXT)
            .chucDanhCanBo(UPDATED_CHUC_DANH_CAN_BO)
            .ldPheDuyet(UPDATED_LD_PHE_DUYET)
            .chucDanhLd(UPDATED_CHUC_DANH_LD);
    }

    @BeforeEach
    public void initTest() {
        chungThuc = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedChungThuc != null) {
            chungThucRepository.delete(insertedChungThuc);
            insertedChungThuc = null;
        }
    }

    @Test
    void createChungThuc() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ChungThuc
        ChungThucDTO chungThucDTO = chungThucMapper.toDto(chungThuc);
        var returnedChungThucDTO = om.readValue(
            restChungThucMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chungThucDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ChungThucDTO.class
        );

        // Validate the ChungThuc in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedChungThuc = chungThucMapper.toEntity(returnedChungThucDTO);
        assertChungThucUpdatableFieldsEquals(returnedChungThuc, getPersistedChungThuc(returnedChungThuc));

        insertedChungThuc = returnedChungThuc;
    }

    @Test
    void createChungThucWithExistingId() throws Exception {
        // Create the ChungThuc with an existing ID
        chungThuc.setId("existing_id");
        ChungThucDTO chungThucDTO = chungThucMapper.toDto(chungThuc);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChungThucMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chungThucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllChungThucs() throws Exception {
        // Initialize the database
        insertedChungThuc = chungThucRepository.save(chungThuc);

        // Get all the chungThucList
        restChungThucMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chungThuc.getId())))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].nguoiYeuCau").value(hasItem(DEFAULT_NGUOI_YEU_CAU)))
            .andExpect(jsonPath("$.[*].nguoiChungThuc").value(hasItem(DEFAULT_NGUOI_CHUNG_THUC.intValue())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].ngayChungThuc").value(hasItem(DEFAULT_NGAY_CHUNG_THUC.toString())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].soTienThu").value(hasItem(DEFAULT_SO_TIEN_THU.intValue())))
            .andExpect(jsonPath("$.[*].soBanSao").value(hasItem(DEFAULT_SO_BAN_SAO.intValue())))
            .andExpect(jsonPath("$.[*].vanBan").value(hasItem(DEFAULT_VAN_BAN)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].quyenSo").value(hasItem(DEFAULT_QUYEN_SO.intValue())))
            .andExpect(jsonPath("$.[*].duongSu").value(hasItem(DEFAULT_DUONG_SU)))
            .andExpect(jsonPath("$.[*].taiSan").value(hasItem(DEFAULT_TAI_SAN)))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].srcChungThuc").value(hasItem(DEFAULT_SRC_CHUNG_THUC)))
            .andExpect(jsonPath("$.[*].thongTinChungThuc").value(hasItem(DEFAULT_THONG_TIN_CHUNG_THUC)))
            .andExpect(jsonPath("$.[*].chuKyNgoaiTruSo").value(hasItem(DEFAULT_CHU_KY_NGOAI_TRU_SO.intValue())))
            .andExpect(jsonPath("$.[*].idCtGoc").value(hasItem(DEFAULT_ID_CT_GOC.intValue())))
            .andExpect(jsonPath("$.[*].ngayText").value(hasItem(DEFAULT_NGAY_TEXT)))
            .andExpect(jsonPath("$.[*].chucDanhCanBo").value(hasItem(DEFAULT_CHUC_DANH_CAN_BO)))
            .andExpect(jsonPath("$.[*].ldPheDuyet").value(hasItem(DEFAULT_LD_PHE_DUYET.intValue())))
            .andExpect(jsonPath("$.[*].chucDanhLd").value(hasItem(DEFAULT_CHUC_DANH_LD)));
    }

    @Test
    void getChungThuc() throws Exception {
        // Initialize the database
        insertedChungThuc = chungThucRepository.save(chungThuc);

        // Get the chungThuc
        restChungThucMockMvc
            .perform(get(ENTITY_API_URL_ID, chungThuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chungThuc.getId()))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.nguoiYeuCau").value(DEFAULT_NGUOI_YEU_CAU))
            .andExpect(jsonPath("$.nguoiChungThuc").value(DEFAULT_NGUOI_CHUNG_THUC.intValue()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.ngayChungThuc").value(DEFAULT_NGAY_CHUNG_THUC.toString()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.soTienThu").value(DEFAULT_SO_TIEN_THU.intValue()))
            .andExpect(jsonPath("$.soBanSao").value(DEFAULT_SO_BAN_SAO.intValue()))
            .andExpect(jsonPath("$.vanBan").value(DEFAULT_VAN_BAN))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.quyenSo").value(DEFAULT_QUYEN_SO.intValue()))
            .andExpect(jsonPath("$.duongSu").value(DEFAULT_DUONG_SU))
            .andExpect(jsonPath("$.taiSan").value(DEFAULT_TAI_SAN))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.srcChungThuc").value(DEFAULT_SRC_CHUNG_THUC))
            .andExpect(jsonPath("$.thongTinChungThuc").value(DEFAULT_THONG_TIN_CHUNG_THUC))
            .andExpect(jsonPath("$.chuKyNgoaiTruSo").value(DEFAULT_CHU_KY_NGOAI_TRU_SO.intValue()))
            .andExpect(jsonPath("$.idCtGoc").value(DEFAULT_ID_CT_GOC.intValue()))
            .andExpect(jsonPath("$.ngayText").value(DEFAULT_NGAY_TEXT))
            .andExpect(jsonPath("$.chucDanhCanBo").value(DEFAULT_CHUC_DANH_CAN_BO))
            .andExpect(jsonPath("$.ldPheDuyet").value(DEFAULT_LD_PHE_DUYET.intValue()))
            .andExpect(jsonPath("$.chucDanhLd").value(DEFAULT_CHUC_DANH_LD));
    }

    @Test
    void getNonExistingChungThuc() throws Exception {
        // Get the chungThuc
        restChungThucMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingChungThuc() throws Exception {
        // Initialize the database
        insertedChungThuc = chungThucRepository.save(chungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chungThuc
        ChungThuc updatedChungThuc = chungThucRepository.findById(chungThuc.getId()).orElseThrow();
        updatedChungThuc
            .idDonVi(UPDATED_ID_DON_VI)
            .nguoiYeuCau(UPDATED_NGUOI_YEU_CAU)
            .nguoiChungThuc(UPDATED_NGUOI_CHUNG_THUC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .ngayChungThuc(UPDATED_NGAY_CHUNG_THUC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .soTienThu(UPDATED_SO_TIEN_THU)
            .soBanSao(UPDATED_SO_BAN_SAO)
            .vanBan(UPDATED_VAN_BAN)
            .trangThai(UPDATED_TRANG_THAI)
            .quyenSo(UPDATED_QUYEN_SO)
            .duongSu(UPDATED_DUONG_SU)
            .taiSan(UPDATED_TAI_SAN)
            .strSearch(UPDATED_STR_SEARCH)
            .srcChungThuc(UPDATED_SRC_CHUNG_THUC)
            .thongTinChungThuc(UPDATED_THONG_TIN_CHUNG_THUC)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .idCtGoc(UPDATED_ID_CT_GOC)
            .ngayText(UPDATED_NGAY_TEXT)
            .chucDanhCanBo(UPDATED_CHUC_DANH_CAN_BO)
            .ldPheDuyet(UPDATED_LD_PHE_DUYET)
            .chucDanhLd(UPDATED_CHUC_DANH_LD);
        ChungThucDTO chungThucDTO = chungThucMapper.toDto(updatedChungThuc);

        restChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chungThucDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chungThucDTO))
            )
            .andExpect(status().isOk());

        // Validate the ChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedChungThucToMatchAllProperties(updatedChungThuc);
    }

    @Test
    void putNonExistingChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chungThuc.setId(UUID.randomUUID().toString());

        // Create the ChungThuc
        ChungThucDTO chungThucDTO = chungThucMapper.toDto(chungThuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chungThucDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chungThuc.setId(UUID.randomUUID().toString());

        // Create the ChungThuc
        ChungThucDTO chungThucDTO = chungThucMapper.toDto(chungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chungThuc.setId(UUID.randomUUID().toString());

        // Create the ChungThuc
        ChungThucDTO chungThucDTO = chungThucMapper.toDto(chungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChungThucMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chungThucDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateChungThucWithPatch() throws Exception {
        // Initialize the database
        insertedChungThuc = chungThucRepository.save(chungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chungThuc using partial update
        ChungThuc partialUpdatedChungThuc = new ChungThuc();
        partialUpdatedChungThuc.setId(chungThuc.getId());

        partialUpdatedChungThuc
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .soTienThu(UPDATED_SO_TIEN_THU)
            .vanBan(UPDATED_VAN_BAN)
            .trangThai(UPDATED_TRANG_THAI)
            .duongSu(UPDATED_DUONG_SU)
            .strSearch(UPDATED_STR_SEARCH)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .idCtGoc(UPDATED_ID_CT_GOC)
            .chucDanhCanBo(UPDATED_CHUC_DANH_CAN_BO)
            .ldPheDuyet(UPDATED_LD_PHE_DUYET);

        restChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChungThuc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChungThuc))
            )
            .andExpect(status().isOk());

        // Validate the ChungThuc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChungThucUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedChungThuc, chungThuc),
            getPersistedChungThuc(chungThuc)
        );
    }

    @Test
    void fullUpdateChungThucWithPatch() throws Exception {
        // Initialize the database
        insertedChungThuc = chungThucRepository.save(chungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chungThuc using partial update
        ChungThuc partialUpdatedChungThuc = new ChungThuc();
        partialUpdatedChungThuc.setId(chungThuc.getId());

        partialUpdatedChungThuc
            .idDonVi(UPDATED_ID_DON_VI)
            .nguoiYeuCau(UPDATED_NGUOI_YEU_CAU)
            .nguoiChungThuc(UPDATED_NGUOI_CHUNG_THUC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .ngayChungThuc(UPDATED_NGAY_CHUNG_THUC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .soTienThu(UPDATED_SO_TIEN_THU)
            .soBanSao(UPDATED_SO_BAN_SAO)
            .vanBan(UPDATED_VAN_BAN)
            .trangThai(UPDATED_TRANG_THAI)
            .quyenSo(UPDATED_QUYEN_SO)
            .duongSu(UPDATED_DUONG_SU)
            .taiSan(UPDATED_TAI_SAN)
            .strSearch(UPDATED_STR_SEARCH)
            .srcChungThuc(UPDATED_SRC_CHUNG_THUC)
            .thongTinChungThuc(UPDATED_THONG_TIN_CHUNG_THUC)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .idCtGoc(UPDATED_ID_CT_GOC)
            .ngayText(UPDATED_NGAY_TEXT)
            .chucDanhCanBo(UPDATED_CHUC_DANH_CAN_BO)
            .ldPheDuyet(UPDATED_LD_PHE_DUYET)
            .chucDanhLd(UPDATED_CHUC_DANH_LD);

        restChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChungThuc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChungThuc))
            )
            .andExpect(status().isOk());

        // Validate the ChungThuc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChungThucUpdatableFieldsEquals(partialUpdatedChungThuc, getPersistedChungThuc(partialUpdatedChungThuc));
    }

    @Test
    void patchNonExistingChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chungThuc.setId(UUID.randomUUID().toString());

        // Create the ChungThuc
        ChungThucDTO chungThucDTO = chungThucMapper.toDto(chungThuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, chungThucDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chungThuc.setId(UUID.randomUUID().toString());

        // Create the ChungThuc
        ChungThucDTO chungThucDTO = chungThucMapper.toDto(chungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chungThuc.setId(UUID.randomUUID().toString());

        // Create the ChungThuc
        ChungThucDTO chungThucDTO = chungThucMapper.toDto(chungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChungThucMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(chungThucDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteChungThuc() throws Exception {
        // Initialize the database
        insertedChungThuc = chungThucRepository.save(chungThuc);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the chungThuc
        restChungThucMockMvc
            .perform(delete(ENTITY_API_URL_ID, chungThuc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return chungThucRepository.count();
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

    protected ChungThuc getPersistedChungThuc(ChungThuc chungThuc) {
        return chungThucRepository.findById(chungThuc.getId()).orElseThrow();
    }

    protected void assertPersistedChungThucToMatchAllProperties(ChungThuc expectedChungThuc) {
        assertChungThucAllPropertiesEquals(expectedChungThuc, getPersistedChungThuc(expectedChungThuc));
    }

    protected void assertPersistedChungThucToMatchUpdatableProperties(ChungThuc expectedChungThuc) {
        assertChungThucAllUpdatablePropertiesEquals(expectedChungThuc, getPersistedChungThuc(expectedChungThuc));
    }
}
