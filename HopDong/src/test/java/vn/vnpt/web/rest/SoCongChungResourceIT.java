package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.SoCongChungAsserts.*;
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
import vn.vnpt.domain.SoCongChung;
import vn.vnpt.repository.SoCongChungRepository;
import vn.vnpt.service.dto.SoCongChungDTO;
import vn.vnpt.service.mapper.SoCongChungMapper;

/**
 * Integration tests for the {@link SoCongChungResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SoCongChungResourceIT {

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final String DEFAULT_TEN_SO = "AAAAAAAAAA";
    private static final String UPDATED_TEN_SO = "BBBBBBBBBB";

    private static final Long DEFAULT_GIA_TRI = 1L;
    private static final Long UPDATED_GIA_TRI = 2L;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String DEFAULT_ID_LOAI_SO = "AAAAAAAAAA";
    private static final String UPDATED_ID_LOAI_SO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/so-cong-chungs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SoCongChungRepository soCongChungRepository;

    @Autowired
    private SoCongChungMapper soCongChungMapper;

    @Autowired
    private MockMvc restSoCongChungMockMvc;

    private SoCongChung soCongChung;

    private SoCongChung insertedSoCongChung;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoCongChung createEntity() {
        return new SoCongChung()
            .idDonVi(DEFAULT_ID_DON_VI)
            .tenSo(DEFAULT_TEN_SO)
            .giaTri(DEFAULT_GIA_TRI)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .trangThai(DEFAULT_TRANG_THAI)
            .idLoaiSo(DEFAULT_ID_LOAI_SO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoCongChung createUpdatedEntity() {
        return new SoCongChung()
            .idDonVi(UPDATED_ID_DON_VI)
            .tenSo(UPDATED_TEN_SO)
            .giaTri(UPDATED_GIA_TRI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);
    }

    @BeforeEach
    public void initTest() {
        soCongChung = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedSoCongChung != null) {
            soCongChungRepository.delete(insertedSoCongChung);
            insertedSoCongChung = null;
        }
    }

    @Test
    void createSoCongChung() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);
        var returnedSoCongChungDTO = om.readValue(
            restSoCongChungMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soCongChungDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SoCongChungDTO.class
        );

        // Validate the SoCongChung in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedSoCongChung = soCongChungMapper.toEntity(returnedSoCongChungDTO);
        assertSoCongChungUpdatableFieldsEquals(returnedSoCongChung, getPersistedSoCongChung(returnedSoCongChung));

        insertedSoCongChung = returnedSoCongChung;
    }

    @Test
    void createSoCongChungWithExistingId() throws Exception {
        // Create the SoCongChung with an existing ID
        soCongChung.setId("existing_id");
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSoCongChungMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soCongChungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSoCongChungs() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.save(soCongChung);

        // Get all the soCongChungList
        restSoCongChungMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(soCongChung.getId())))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].tenSo").value(hasItem(DEFAULT_TEN_SO)))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiSo").value(hasItem(DEFAULT_ID_LOAI_SO)));
    }

    @Test
    void getSoCongChung() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.save(soCongChung);

        // Get the soCongChung
        restSoCongChungMockMvc
            .perform(get(ENTITY_API_URL_ID, soCongChung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(soCongChung.getId()))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.tenSo").value(DEFAULT_TEN_SO))
            .andExpect(jsonPath("$.giaTri").value(DEFAULT_GIA_TRI.intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.idLoaiSo").value(DEFAULT_ID_LOAI_SO));
    }

    @Test
    void getNonExistingSoCongChung() throws Exception {
        // Get the soCongChung
        restSoCongChungMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingSoCongChung() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.save(soCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soCongChung
        SoCongChung updatedSoCongChung = soCongChungRepository.findById(soCongChung.getId()).orElseThrow();
        updatedSoCongChung
            .idDonVi(UPDATED_ID_DON_VI)
            .tenSo(UPDATED_TEN_SO)
            .giaTri(UPDATED_GIA_TRI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(updatedSoCongChung);

        restSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soCongChungDTO))
            )
            .andExpect(status().isOk());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSoCongChungToMatchAllProperties(updatedSoCongChung);
    }

    @Test
    void putNonExistingSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(UUID.randomUUID().toString());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(UUID.randomUUID().toString());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(UUID.randomUUID().toString());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soCongChungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSoCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.save(soCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soCongChung using partial update
        SoCongChung partialUpdatedSoCongChung = new SoCongChung();
        partialUpdatedSoCongChung.setId(soCongChung.getId());

        partialUpdatedSoCongChung.idDonVi(UPDATED_ID_DON_VI).tenSo(UPDATED_TEN_SO).giaTri(UPDATED_GIA_TRI).idLoaiSo(UPDATED_ID_LOAI_SO);

        restSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSoCongChung))
            )
            .andExpect(status().isOk());

        // Validate the SoCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSoCongChungUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSoCongChung, soCongChung),
            getPersistedSoCongChung(soCongChung)
        );
    }

    @Test
    void fullUpdateSoCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.save(soCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soCongChung using partial update
        SoCongChung partialUpdatedSoCongChung = new SoCongChung();
        partialUpdatedSoCongChung.setId(soCongChung.getId());

        partialUpdatedSoCongChung
            .idDonVi(UPDATED_ID_DON_VI)
            .tenSo(UPDATED_TEN_SO)
            .giaTri(UPDATED_GIA_TRI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);

        restSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSoCongChung))
            )
            .andExpect(status().isOk());

        // Validate the SoCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSoCongChungUpdatableFieldsEquals(partialUpdatedSoCongChung, getPersistedSoCongChung(partialUpdatedSoCongChung));
    }

    @Test
    void patchNonExistingSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(UUID.randomUUID().toString());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, soCongChungDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(soCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(UUID.randomUUID().toString());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(soCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(UUID.randomUUID().toString());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(soCongChungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSoCongChung() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.save(soCongChung);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the soCongChung
        restSoCongChungMockMvc
            .perform(delete(ENTITY_API_URL_ID, soCongChung.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return soCongChungRepository.count();
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

    protected SoCongChung getPersistedSoCongChung(SoCongChung soCongChung) {
        return soCongChungRepository.findById(soCongChung.getId()).orElseThrow();
    }

    protected void assertPersistedSoCongChungToMatchAllProperties(SoCongChung expectedSoCongChung) {
        assertSoCongChungAllPropertiesEquals(expectedSoCongChung, getPersistedSoCongChung(expectedSoCongChung));
    }

    protected void assertPersistedSoCongChungToMatchUpdatableProperties(SoCongChung expectedSoCongChung) {
        assertSoCongChungAllUpdatablePropertiesEquals(expectedSoCongChung, getPersistedSoCongChung(expectedSoCongChung));
    }
}
