package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhSachChungThucAsserts.*;
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
import vn.vnpt.domain.DanhSachChungThuc;
import vn.vnpt.repository.DanhSachChungThucRepository;
import vn.vnpt.service.dto.DanhSachChungThucDTO;
import vn.vnpt.service.mapper.DanhSachChungThucMapper;

/**
 * Integration tests for the {@link DanhSachChungThucResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhSachChungThucResourceIT {

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final Long DEFAULT_NGUOI_CHUNG_THUC = 1L;
    private static final Long UPDATED_NGUOI_CHUNG_THUC = 2L;

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final LocalDate DEFAULT_NGAY_CHUNG_THUC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CHUNG_THUC = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final Long DEFAULT_QUYEN_SO = 1L;
    private static final Long UPDATED_QUYEN_SO = 2L;

    private static final String DEFAULT_SRC_CHUNG_THUC = "AAAAAAAAAA";
    private static final String UPDATED_SRC_CHUNG_THUC = "BBBBBBBBBB";

    private static final Long DEFAULT_CHU_KY_NGOAI_TRU_SO = 1L;
    private static final Long UPDATED_CHU_KY_NGOAI_TRU_SO = 2L;

    private static final String DEFAULT_NGAY_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_STR_SEARCH = "AAAAAAAAAA";
    private static final String UPDATED_STR_SEARCH = "BBBBBBBBBB";

    private static final Long DEFAULT_SO_TIEN_THU = 1L;
    private static final Long UPDATED_SO_TIEN_THU = 2L;

    private static final Long DEFAULT_LD_PHE_DUYET = 1L;
    private static final Long UPDATED_LD_PHE_DUYET = 2L;

    private static final String ENTITY_API_URL = "/api/danh-sach-chung-thucs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhSachChungThucRepository danhSachChungThucRepository;

    @Autowired
    private DanhSachChungThucMapper danhSachChungThucMapper;

    @Autowired
    private MockMvc restDanhSachChungThucMockMvc;

    private DanhSachChungThuc danhSachChungThuc;

    private DanhSachChungThuc insertedDanhSachChungThuc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhSachChungThuc createEntity() {
        return new DanhSachChungThuc()
            .idDonVi(DEFAULT_ID_DON_VI)
            .nguoiChungThuc(DEFAULT_NGUOI_CHUNG_THUC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .ngayChungThuc(DEFAULT_NGAY_CHUNG_THUC)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .trangThai(DEFAULT_TRANG_THAI)
            .quyenSo(DEFAULT_QUYEN_SO)
            .srcChungThuc(DEFAULT_SRC_CHUNG_THUC)
            .chuKyNgoaiTruSo(DEFAULT_CHU_KY_NGOAI_TRU_SO)
            .ngayText(DEFAULT_NGAY_TEXT)
            .strSearch(DEFAULT_STR_SEARCH)
            .soTienThu(DEFAULT_SO_TIEN_THU)
            .ldPheDuyet(DEFAULT_LD_PHE_DUYET);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhSachChungThuc createUpdatedEntity() {
        return new DanhSachChungThuc()
            .idDonVi(UPDATED_ID_DON_VI)
            .nguoiChungThuc(UPDATED_NGUOI_CHUNG_THUC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .ngayChungThuc(UPDATED_NGAY_CHUNG_THUC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .quyenSo(UPDATED_QUYEN_SO)
            .srcChungThuc(UPDATED_SRC_CHUNG_THUC)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .ngayText(UPDATED_NGAY_TEXT)
            .strSearch(UPDATED_STR_SEARCH)
            .soTienThu(UPDATED_SO_TIEN_THU)
            .ldPheDuyet(UPDATED_LD_PHE_DUYET);
    }

    @BeforeEach
    public void initTest() {
        danhSachChungThuc = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhSachChungThuc != null) {
            danhSachChungThucRepository.delete(insertedDanhSachChungThuc);
            insertedDanhSachChungThuc = null;
        }
    }

    @Test
    void createDanhSachChungThuc() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhSachChungThuc
        DanhSachChungThucDTO danhSachChungThucDTO = danhSachChungThucMapper.toDto(danhSachChungThuc);
        var returnedDanhSachChungThucDTO = om.readValue(
            restDanhSachChungThucMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachChungThucDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhSachChungThucDTO.class
        );

        // Validate the DanhSachChungThuc in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhSachChungThuc = danhSachChungThucMapper.toEntity(returnedDanhSachChungThucDTO);
        assertDanhSachChungThucUpdatableFieldsEquals(returnedDanhSachChungThuc, getPersistedDanhSachChungThuc(returnedDanhSachChungThuc));

        insertedDanhSachChungThuc = returnedDanhSachChungThuc;
    }

    @Test
    void createDanhSachChungThucWithExistingId() throws Exception {
        // Create the DanhSachChungThuc with an existing ID
        danhSachChungThuc.setId("existing_id");
        DanhSachChungThucDTO danhSachChungThucDTO = danhSachChungThucMapper.toDto(danhSachChungThuc);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhSachChungThucMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachChungThucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhSachChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDanhSachChungThucs() throws Exception {
        // Initialize the database
        insertedDanhSachChungThuc = danhSachChungThucRepository.save(danhSachChungThuc);

        // Get all the danhSachChungThucList
        restDanhSachChungThucMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhSachChungThuc.getId())))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].nguoiChungThuc").value(hasItem(DEFAULT_NGUOI_CHUNG_THUC.intValue())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].ngayChungThuc").value(hasItem(DEFAULT_NGAY_CHUNG_THUC.toString())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].quyenSo").value(hasItem(DEFAULT_QUYEN_SO.intValue())))
            .andExpect(jsonPath("$.[*].srcChungThuc").value(hasItem(DEFAULT_SRC_CHUNG_THUC)))
            .andExpect(jsonPath("$.[*].chuKyNgoaiTruSo").value(hasItem(DEFAULT_CHU_KY_NGOAI_TRU_SO.intValue())))
            .andExpect(jsonPath("$.[*].ngayText").value(hasItem(DEFAULT_NGAY_TEXT)))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].soTienThu").value(hasItem(DEFAULT_SO_TIEN_THU.intValue())))
            .andExpect(jsonPath("$.[*].ldPheDuyet").value(hasItem(DEFAULT_LD_PHE_DUYET.intValue())));
    }

    @Test
    void getDanhSachChungThuc() throws Exception {
        // Initialize the database
        insertedDanhSachChungThuc = danhSachChungThucRepository.save(danhSachChungThuc);

        // Get the danhSachChungThuc
        restDanhSachChungThucMockMvc
            .perform(get(ENTITY_API_URL_ID, danhSachChungThuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhSachChungThuc.getId()))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.nguoiChungThuc").value(DEFAULT_NGUOI_CHUNG_THUC.intValue()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.ngayChungThuc").value(DEFAULT_NGAY_CHUNG_THUC.toString()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.quyenSo").value(DEFAULT_QUYEN_SO.intValue()))
            .andExpect(jsonPath("$.srcChungThuc").value(DEFAULT_SRC_CHUNG_THUC))
            .andExpect(jsonPath("$.chuKyNgoaiTruSo").value(DEFAULT_CHU_KY_NGOAI_TRU_SO.intValue()))
            .andExpect(jsonPath("$.ngayText").value(DEFAULT_NGAY_TEXT))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.soTienThu").value(DEFAULT_SO_TIEN_THU.intValue()))
            .andExpect(jsonPath("$.ldPheDuyet").value(DEFAULT_LD_PHE_DUYET.intValue()));
    }

    @Test
    void getNonExistingDanhSachChungThuc() throws Exception {
        // Get the danhSachChungThuc
        restDanhSachChungThucMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDanhSachChungThuc() throws Exception {
        // Initialize the database
        insertedDanhSachChungThuc = danhSachChungThucRepository.save(danhSachChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachChungThuc
        DanhSachChungThuc updatedDanhSachChungThuc = danhSachChungThucRepository.findById(danhSachChungThuc.getId()).orElseThrow();
        updatedDanhSachChungThuc
            .idDonVi(UPDATED_ID_DON_VI)
            .nguoiChungThuc(UPDATED_NGUOI_CHUNG_THUC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .ngayChungThuc(UPDATED_NGAY_CHUNG_THUC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .quyenSo(UPDATED_QUYEN_SO)
            .srcChungThuc(UPDATED_SRC_CHUNG_THUC)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .ngayText(UPDATED_NGAY_TEXT)
            .strSearch(UPDATED_STR_SEARCH)
            .soTienThu(UPDATED_SO_TIEN_THU)
            .ldPheDuyet(UPDATED_LD_PHE_DUYET);
        DanhSachChungThucDTO danhSachChungThucDTO = danhSachChungThucMapper.toDto(updatedDanhSachChungThuc);

        restDanhSachChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhSachChungThucDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachChungThucDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhSachChungThucToMatchAllProperties(updatedDanhSachChungThuc);
    }

    @Test
    void putNonExistingDanhSachChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhSachChungThuc
        DanhSachChungThucDTO danhSachChungThucDTO = danhSachChungThucMapper.toDto(danhSachChungThuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhSachChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhSachChungThucDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDanhSachChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhSachChungThuc
        DanhSachChungThucDTO danhSachChungThucDTO = danhSachChungThucMapper.toDto(danhSachChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDanhSachChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhSachChungThuc
        DanhSachChungThucDTO danhSachChungThucDTO = danhSachChungThucMapper.toDto(danhSachChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachChungThucMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachChungThucDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhSachChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDanhSachChungThucWithPatch() throws Exception {
        // Initialize the database
        insertedDanhSachChungThuc = danhSachChungThucRepository.save(danhSachChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachChungThuc using partial update
        DanhSachChungThuc partialUpdatedDanhSachChungThuc = new DanhSachChungThuc();
        partialUpdatedDanhSachChungThuc.setId(danhSachChungThuc.getId());

        partialUpdatedDanhSachChungThuc
            .nguoiChungThuc(UPDATED_NGUOI_CHUNG_THUC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .quyenSo(UPDATED_QUYEN_SO)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .strSearch(UPDATED_STR_SEARCH);

        restDanhSachChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhSachChungThuc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhSachChungThuc))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachChungThuc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhSachChungThucUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhSachChungThuc, danhSachChungThuc),
            getPersistedDanhSachChungThuc(danhSachChungThuc)
        );
    }

    @Test
    void fullUpdateDanhSachChungThucWithPatch() throws Exception {
        // Initialize the database
        insertedDanhSachChungThuc = danhSachChungThucRepository.save(danhSachChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachChungThuc using partial update
        DanhSachChungThuc partialUpdatedDanhSachChungThuc = new DanhSachChungThuc();
        partialUpdatedDanhSachChungThuc.setId(danhSachChungThuc.getId());

        partialUpdatedDanhSachChungThuc
            .idDonVi(UPDATED_ID_DON_VI)
            .nguoiChungThuc(UPDATED_NGUOI_CHUNG_THUC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .ngayChungThuc(UPDATED_NGAY_CHUNG_THUC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .quyenSo(UPDATED_QUYEN_SO)
            .srcChungThuc(UPDATED_SRC_CHUNG_THUC)
            .chuKyNgoaiTruSo(UPDATED_CHU_KY_NGOAI_TRU_SO)
            .ngayText(UPDATED_NGAY_TEXT)
            .strSearch(UPDATED_STR_SEARCH)
            .soTienThu(UPDATED_SO_TIEN_THU)
            .ldPheDuyet(UPDATED_LD_PHE_DUYET);

        restDanhSachChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhSachChungThuc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhSachChungThuc))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachChungThuc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhSachChungThucUpdatableFieldsEquals(
            partialUpdatedDanhSachChungThuc,
            getPersistedDanhSachChungThuc(partialUpdatedDanhSachChungThuc)
        );
    }

    @Test
    void patchNonExistingDanhSachChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhSachChungThuc
        DanhSachChungThucDTO danhSachChungThucDTO = danhSachChungThucMapper.toDto(danhSachChungThuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhSachChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhSachChungThucDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhSachChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDanhSachChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhSachChungThuc
        DanhSachChungThucDTO danhSachChungThucDTO = danhSachChungThucMapper.toDto(danhSachChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhSachChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDanhSachChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachChungThuc.setId(UUID.randomUUID().toString());

        // Create the DanhSachChungThuc
        DanhSachChungThucDTO danhSachChungThucDTO = danhSachChungThucMapper.toDto(danhSachChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachChungThucMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhSachChungThucDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhSachChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDanhSachChungThuc() throws Exception {
        // Initialize the database
        insertedDanhSachChungThuc = danhSachChungThucRepository.save(danhSachChungThuc);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhSachChungThuc
        restDanhSachChungThucMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhSachChungThuc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhSachChungThucRepository.count();
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

    protected DanhSachChungThuc getPersistedDanhSachChungThuc(DanhSachChungThuc danhSachChungThuc) {
        return danhSachChungThucRepository.findById(danhSachChungThuc.getId()).orElseThrow();
    }

    protected void assertPersistedDanhSachChungThucToMatchAllProperties(DanhSachChungThuc expectedDanhSachChungThuc) {
        assertDanhSachChungThucAllPropertiesEquals(expectedDanhSachChungThuc, getPersistedDanhSachChungThuc(expectedDanhSachChungThuc));
    }

    protected void assertPersistedDanhSachChungThucToMatchUpdatableProperties(DanhSachChungThuc expectedDanhSachChungThuc) {
        assertDanhSachChungThucAllUpdatablePropertiesEquals(
            expectedDanhSachChungThuc,
            getPersistedDanhSachChungThuc(expectedDanhSachChungThuc)
        );
    }
}
