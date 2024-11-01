package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.LoaiChungThucAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
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
import vn.vnpt.domain.LoaiChungThuc;
import vn.vnpt.repository.LoaiChungThucRepository;
import vn.vnpt.service.dto.LoaiChungThucDTO;
import vn.vnpt.service.mapper.LoaiChungThucMapper;

/**
 * Integration tests for the {@link LoaiChungThucResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LoaiChungThucResourceIT {

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_KHUNG_GIA = 1L;
    private static final Long UPDATED_KHUNG_GIA = 2L;

    private static final Long DEFAULT_HAS_BEN_B = 1L;
    private static final Long UPDATED_HAS_BEN_B = 2L;

    private static final Long DEFAULT_HAS_TAI_SAN = 1L;
    private static final Long UPDATED_HAS_TAI_SAN = 2L;

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String DEFAULT_FILE_CHUNG_THUC = "AAAAAAAAAA";
    private static final String UPDATED_FILE_CHUNG_THUC = "BBBBBBBBBB";

    private static final byte[] DEFAULT_SRC_CHUNG_THUC = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_SRC_CHUNG_THUC = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_SRC_CHUNG_THUC_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SRC_CHUNG_THUC_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final String DEFAULT_ID_LOAI_SO = "AAAAAAAAAA";
    private static final String UPDATED_ID_LOAI_SO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/loai-chung-thucs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LoaiChungThucRepository loaiChungThucRepository;

    @Autowired
    private LoaiChungThucMapper loaiChungThucMapper;

    @Autowired
    private MockMvc restLoaiChungThucMockMvc;

    private LoaiChungThuc loaiChungThuc;

    private LoaiChungThuc insertedLoaiChungThuc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiChungThuc createEntity() {
        return new LoaiChungThuc()
            .dienGiai(DEFAULT_DIEN_GIAI)
            .khungGia(DEFAULT_KHUNG_GIA)
            .hasBenB(DEFAULT_HAS_BEN_B)
            .hasTaiSan(DEFAULT_HAS_TAI_SAN)
            .trangThai(DEFAULT_TRANG_THAI)
            .fileChungThuc(DEFAULT_FILE_CHUNG_THUC)
            .srcChungThuc(DEFAULT_SRC_CHUNG_THUC)
            .srcChungThucContentType(DEFAULT_SRC_CHUNG_THUC_CONTENT_TYPE)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idDonVi(DEFAULT_ID_DON_VI)
            .idLoaiSo(DEFAULT_ID_LOAI_SO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiChungThuc createUpdatedEntity() {
        return new LoaiChungThuc()
            .dienGiai(UPDATED_DIEN_GIAI)
            .khungGia(UPDATED_KHUNG_GIA)
            .hasBenB(UPDATED_HAS_BEN_B)
            .hasTaiSan(UPDATED_HAS_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .fileChungThuc(UPDATED_FILE_CHUNG_THUC)
            .srcChungThuc(UPDATED_SRC_CHUNG_THUC)
            .srcChungThucContentType(UPDATED_SRC_CHUNG_THUC_CONTENT_TYPE)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDonVi(UPDATED_ID_DON_VI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);
    }

    @BeforeEach
    public void initTest() {
        loaiChungThuc = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLoaiChungThuc != null) {
            loaiChungThucRepository.delete(insertedLoaiChungThuc);
            insertedLoaiChungThuc = null;
        }
    }

    @Test
    void createLoaiChungThuc() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LoaiChungThuc
        LoaiChungThucDTO loaiChungThucDTO = loaiChungThucMapper.toDto(loaiChungThuc);
        var returnedLoaiChungThucDTO = om.readValue(
            restLoaiChungThucMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiChungThucDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LoaiChungThucDTO.class
        );

        // Validate the LoaiChungThuc in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLoaiChungThuc = loaiChungThucMapper.toEntity(returnedLoaiChungThucDTO);
        assertLoaiChungThucUpdatableFieldsEquals(returnedLoaiChungThuc, getPersistedLoaiChungThuc(returnedLoaiChungThuc));

        insertedLoaiChungThuc = returnedLoaiChungThuc;
    }

    @Test
    void createLoaiChungThucWithExistingId() throws Exception {
        // Create the LoaiChungThuc with an existing ID
        loaiChungThuc.setId("existing_id");
        LoaiChungThucDTO loaiChungThucDTO = loaiChungThucMapper.toDto(loaiChungThuc);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiChungThucMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiChungThucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllLoaiChungThucs() throws Exception {
        // Initialize the database
        insertedLoaiChungThuc = loaiChungThucRepository.save(loaiChungThuc);

        // Get all the loaiChungThucList
        restLoaiChungThucMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiChungThuc.getId())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].khungGia").value(hasItem(DEFAULT_KHUNG_GIA.intValue())))
            .andExpect(jsonPath("$.[*].hasBenB").value(hasItem(DEFAULT_HAS_BEN_B.intValue())))
            .andExpect(jsonPath("$.[*].hasTaiSan").value(hasItem(DEFAULT_HAS_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].fileChungThuc").value(hasItem(DEFAULT_FILE_CHUNG_THUC)))
            .andExpect(jsonPath("$.[*].srcChungThucContentType").value(hasItem(DEFAULT_SRC_CHUNG_THUC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].srcChungThuc").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_SRC_CHUNG_THUC))))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiSo").value(hasItem(DEFAULT_ID_LOAI_SO)));
    }

    @Test
    void getLoaiChungThuc() throws Exception {
        // Initialize the database
        insertedLoaiChungThuc = loaiChungThucRepository.save(loaiChungThuc);

        // Get the loaiChungThuc
        restLoaiChungThucMockMvc
            .perform(get(ENTITY_API_URL_ID, loaiChungThuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loaiChungThuc.getId()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.khungGia").value(DEFAULT_KHUNG_GIA.intValue()))
            .andExpect(jsonPath("$.hasBenB").value(DEFAULT_HAS_BEN_B.intValue()))
            .andExpect(jsonPath("$.hasTaiSan").value(DEFAULT_HAS_TAI_SAN.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.fileChungThuc").value(DEFAULT_FILE_CHUNG_THUC))
            .andExpect(jsonPath("$.srcChungThucContentType").value(DEFAULT_SRC_CHUNG_THUC_CONTENT_TYPE))
            .andExpect(jsonPath("$.srcChungThuc").value(Base64.getEncoder().encodeToString(DEFAULT_SRC_CHUNG_THUC)))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.idLoaiSo").value(DEFAULT_ID_LOAI_SO));
    }

    @Test
    void getNonExistingLoaiChungThuc() throws Exception {
        // Get the loaiChungThuc
        restLoaiChungThucMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingLoaiChungThuc() throws Exception {
        // Initialize the database
        insertedLoaiChungThuc = loaiChungThucRepository.save(loaiChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiChungThuc
        LoaiChungThuc updatedLoaiChungThuc = loaiChungThucRepository.findById(loaiChungThuc.getId()).orElseThrow();
        updatedLoaiChungThuc
            .dienGiai(UPDATED_DIEN_GIAI)
            .khungGia(UPDATED_KHUNG_GIA)
            .hasBenB(UPDATED_HAS_BEN_B)
            .hasTaiSan(UPDATED_HAS_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .fileChungThuc(UPDATED_FILE_CHUNG_THUC)
            .srcChungThuc(UPDATED_SRC_CHUNG_THUC)
            .srcChungThucContentType(UPDATED_SRC_CHUNG_THUC_CONTENT_TYPE)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDonVi(UPDATED_ID_DON_VI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);
        LoaiChungThucDTO loaiChungThucDTO = loaiChungThucMapper.toDto(updatedLoaiChungThuc);

        restLoaiChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiChungThucDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiChungThucDTO))
            )
            .andExpect(status().isOk());

        // Validate the LoaiChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLoaiChungThucToMatchAllProperties(updatedLoaiChungThuc);
    }

    @Test
    void putNonExistingLoaiChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiChungThuc.setId(UUID.randomUUID().toString());

        // Create the LoaiChungThuc
        LoaiChungThucDTO loaiChungThucDTO = loaiChungThucMapper.toDto(loaiChungThuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiChungThucDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchLoaiChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiChungThuc.setId(UUID.randomUUID().toString());

        // Create the LoaiChungThuc
        LoaiChungThucDTO loaiChungThucDTO = loaiChungThucMapper.toDto(loaiChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiChungThucMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamLoaiChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiChungThuc.setId(UUID.randomUUID().toString());

        // Create the LoaiChungThuc
        LoaiChungThucDTO loaiChungThucDTO = loaiChungThucMapper.toDto(loaiChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiChungThucMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiChungThucDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateLoaiChungThucWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiChungThuc = loaiChungThucRepository.save(loaiChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiChungThuc using partial update
        LoaiChungThuc partialUpdatedLoaiChungThuc = new LoaiChungThuc();
        partialUpdatedLoaiChungThuc.setId(loaiChungThuc.getId());

        partialUpdatedLoaiChungThuc
            .hasBenB(UPDATED_HAS_BEN_B)
            .hasTaiSan(UPDATED_HAS_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC);

        restLoaiChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiChungThuc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiChungThuc))
            )
            .andExpect(status().isOk());

        // Validate the LoaiChungThuc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiChungThucUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLoaiChungThuc, loaiChungThuc),
            getPersistedLoaiChungThuc(loaiChungThuc)
        );
    }

    @Test
    void fullUpdateLoaiChungThucWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiChungThuc = loaiChungThucRepository.save(loaiChungThuc);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiChungThuc using partial update
        LoaiChungThuc partialUpdatedLoaiChungThuc = new LoaiChungThuc();
        partialUpdatedLoaiChungThuc.setId(loaiChungThuc.getId());

        partialUpdatedLoaiChungThuc
            .dienGiai(UPDATED_DIEN_GIAI)
            .khungGia(UPDATED_KHUNG_GIA)
            .hasBenB(UPDATED_HAS_BEN_B)
            .hasTaiSan(UPDATED_HAS_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .fileChungThuc(UPDATED_FILE_CHUNG_THUC)
            .srcChungThuc(UPDATED_SRC_CHUNG_THUC)
            .srcChungThucContentType(UPDATED_SRC_CHUNG_THUC_CONTENT_TYPE)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDonVi(UPDATED_ID_DON_VI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);

        restLoaiChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiChungThuc.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiChungThuc))
            )
            .andExpect(status().isOk());

        // Validate the LoaiChungThuc in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiChungThucUpdatableFieldsEquals(partialUpdatedLoaiChungThuc, getPersistedLoaiChungThuc(partialUpdatedLoaiChungThuc));
    }

    @Test
    void patchNonExistingLoaiChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiChungThuc.setId(UUID.randomUUID().toString());

        // Create the LoaiChungThuc
        LoaiChungThucDTO loaiChungThucDTO = loaiChungThucMapper.toDto(loaiChungThuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, loaiChungThucDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchLoaiChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiChungThuc.setId(UUID.randomUUID().toString());

        // Create the LoaiChungThuc
        LoaiChungThucDTO loaiChungThucDTO = loaiChungThucMapper.toDto(loaiChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiChungThucMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiChungThucDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamLoaiChungThuc() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiChungThuc.setId(UUID.randomUUID().toString());

        // Create the LoaiChungThuc
        LoaiChungThucDTO loaiChungThucDTO = loaiChungThucMapper.toDto(loaiChungThuc);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiChungThucMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(loaiChungThucDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiChungThuc in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteLoaiChungThuc() throws Exception {
        // Initialize the database
        insertedLoaiChungThuc = loaiChungThucRepository.save(loaiChungThuc);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the loaiChungThuc
        restLoaiChungThucMockMvc
            .perform(delete(ENTITY_API_URL_ID, loaiChungThuc.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return loaiChungThucRepository.count();
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

    protected LoaiChungThuc getPersistedLoaiChungThuc(LoaiChungThuc loaiChungThuc) {
        return loaiChungThucRepository.findById(loaiChungThuc.getId()).orElseThrow();
    }

    protected void assertPersistedLoaiChungThucToMatchAllProperties(LoaiChungThuc expectedLoaiChungThuc) {
        assertLoaiChungThucAllPropertiesEquals(expectedLoaiChungThuc, getPersistedLoaiChungThuc(expectedLoaiChungThuc));
    }

    protected void assertPersistedLoaiChungThucToMatchUpdatableProperties(LoaiChungThuc expectedLoaiChungThuc) {
        assertLoaiChungThucAllUpdatablePropertiesEquals(expectedLoaiChungThuc, getPersistedLoaiChungThuc(expectedLoaiChungThuc));
    }
}
