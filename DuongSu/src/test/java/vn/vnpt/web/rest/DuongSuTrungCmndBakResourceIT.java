package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DuongSuTrungCmndBakAsserts.*;
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
import vn.vnpt.domain.DuongSuTrungCmndBak;
import vn.vnpt.repository.DuongSuTrungCmndBakRepository;
import vn.vnpt.service.dto.DuongSuTrungCmndBakDTO;
import vn.vnpt.service.mapper.DuongSuTrungCmndBakMapper;

/**
 * Integration tests for the {@link DuongSuTrungCmndBakResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DuongSuTrungCmndBakResourceIT {

    private static final String DEFAULT_TEN_DUONG_SU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DUONG_SU = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

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

    private static final String DEFAULT_SO_GIAY_TO = "AAAAAAAAAA";
    private static final String UPDATED_SO_GIAY_TO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/duong-su-trung-cmnd-baks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DuongSuTrungCmndBakRepository duongSuTrungCmndBakRepository;

    @Autowired
    private DuongSuTrungCmndBakMapper duongSuTrungCmndBakMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDuongSuTrungCmndBakMockMvc;

    private DuongSuTrungCmndBak duongSuTrungCmndBak;

    private DuongSuTrungCmndBak insertedDuongSuTrungCmndBak;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuongSuTrungCmndBak createEntity() {
        return new DuongSuTrungCmndBak()
            .tenDuongSu(DEFAULT_TEN_DUONG_SU)
            .diaChi(DEFAULT_DIA_CHI)
            .trangThai(DEFAULT_TRANG_THAI)
            .thongTinDs(DEFAULT_THONG_TIN_DS)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idDsGoc(DEFAULT_ID_DS_GOC)
            .idMaster(DEFAULT_ID_MASTER)
            .idDonVi(DEFAULT_ID_DON_VI)
            .strSearch(DEFAULT_STR_SEARCH)
            .soGiayTo(DEFAULT_SO_GIAY_TO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuongSuTrungCmndBak createUpdatedEntity() {
        return new DuongSuTrungCmndBak()
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO);
    }

    @BeforeEach
    public void initTest() {
        duongSuTrungCmndBak = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDuongSuTrungCmndBak != null) {
            duongSuTrungCmndBakRepository.delete(insertedDuongSuTrungCmndBak);
            insertedDuongSuTrungCmndBak = null;
        }
    }

    @Test
    @Transactional
    void createDuongSuTrungCmndBak() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DuongSuTrungCmndBak
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO = duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);
        var returnedDuongSuTrungCmndBakDTO = om.readValue(
            restDuongSuTrungCmndBakMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuTrungCmndBakDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DuongSuTrungCmndBakDTO.class
        );

        // Validate the DuongSuTrungCmndBak in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDuongSuTrungCmndBak = duongSuTrungCmndBakMapper.toEntity(returnedDuongSuTrungCmndBakDTO);
        assertDuongSuTrungCmndBakUpdatableFieldsEquals(
            returnedDuongSuTrungCmndBak,
            getPersistedDuongSuTrungCmndBak(returnedDuongSuTrungCmndBak)
        );

        insertedDuongSuTrungCmndBak = returnedDuongSuTrungCmndBak;
    }

    @Test
    @Transactional
    void createDuongSuTrungCmndBakWithExistingId() throws Exception {
        // Create the DuongSuTrungCmndBak with an existing ID
        duongSuTrungCmndBak.setId(1L);
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO = duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDuongSuTrungCmndBakMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuTrungCmndBakDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DuongSuTrungCmndBak in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaks() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList
        restDuongSuTrungCmndBakMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duongSuTrungCmndBak.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenDuongSu").value(hasItem(DEFAULT_TEN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].thongTinDs").value(hasItem(DEFAULT_THONG_TIN_DS)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDsGoc").value(hasItem(DEFAULT_ID_DS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].soGiayTo").value(hasItem(DEFAULT_SO_GIAY_TO)));
    }

    @Test
    @Transactional
    void getDuongSuTrungCmndBak() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get the duongSuTrungCmndBak
        restDuongSuTrungCmndBakMockMvc
            .perform(get(ENTITY_API_URL_ID, duongSuTrungCmndBak.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(duongSuTrungCmndBak.getId().intValue()))
            .andExpect(jsonPath("$.tenDuongSu").value(DEFAULT_TEN_DUONG_SU))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI))
            .andExpect(jsonPath("$.thongTinDs").value(DEFAULT_THONG_TIN_DS))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idDsGoc").value(DEFAULT_ID_DS_GOC.intValue()))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.soGiayTo").value(DEFAULT_SO_GIAY_TO));
    }

    @Test
    @Transactional
    void getNonExistingDuongSuTrungCmndBak() throws Exception {
        // Get the duongSuTrungCmndBak
        restDuongSuTrungCmndBakMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDuongSuTrungCmndBak() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSuTrungCmndBak
        DuongSuTrungCmndBak updatedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.findById(duongSuTrungCmndBak.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDuongSuTrungCmndBak are not directly saved in db
        em.detach(updatedDuongSuTrungCmndBak);
        updatedDuongSuTrungCmndBak
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO);
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO = duongSuTrungCmndBakMapper.toDto(updatedDuongSuTrungCmndBak);

        restDuongSuTrungCmndBakMockMvc
            .perform(
                put(ENTITY_API_URL_ID, duongSuTrungCmndBakDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuTrungCmndBakDTO))
            )
            .andExpect(status().isOk());

        // Validate the DuongSuTrungCmndBak in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDuongSuTrungCmndBakToMatchAllProperties(updatedDuongSuTrungCmndBak);
    }

    @Test
    @Transactional
    void putNonExistingDuongSuTrungCmndBak() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmndBak.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmndBak
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO = duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndBakMockMvc
            .perform(
                put(ENTITY_API_URL_ID, duongSuTrungCmndBakDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuTrungCmndBakDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSuTrungCmndBak in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDuongSuTrungCmndBak() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmndBak.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmndBak
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO = duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndBakMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuTrungCmndBakDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSuTrungCmndBak in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDuongSuTrungCmndBak() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmndBak.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmndBak
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO = duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndBakMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuTrungCmndBakDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DuongSuTrungCmndBak in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDuongSuTrungCmndBakWithPatch() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSuTrungCmndBak using partial update
        DuongSuTrungCmndBak partialUpdatedDuongSuTrungCmndBak = new DuongSuTrungCmndBak();
        partialUpdatedDuongSuTrungCmndBak.setId(duongSuTrungCmndBak.getId());

        partialUpdatedDuongSuTrungCmndBak
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO);

        restDuongSuTrungCmndBakMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDuongSuTrungCmndBak.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDuongSuTrungCmndBak))
            )
            .andExpect(status().isOk());

        // Validate the DuongSuTrungCmndBak in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDuongSuTrungCmndBakUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDuongSuTrungCmndBak, duongSuTrungCmndBak),
            getPersistedDuongSuTrungCmndBak(duongSuTrungCmndBak)
        );
    }

    @Test
    @Transactional
    void fullUpdateDuongSuTrungCmndBakWithPatch() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSuTrungCmndBak using partial update
        DuongSuTrungCmndBak partialUpdatedDuongSuTrungCmndBak = new DuongSuTrungCmndBak();
        partialUpdatedDuongSuTrungCmndBak.setId(duongSuTrungCmndBak.getId());

        partialUpdatedDuongSuTrungCmndBak
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO);

        restDuongSuTrungCmndBakMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDuongSuTrungCmndBak.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDuongSuTrungCmndBak))
            )
            .andExpect(status().isOk());

        // Validate the DuongSuTrungCmndBak in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDuongSuTrungCmndBakUpdatableFieldsEquals(
            partialUpdatedDuongSuTrungCmndBak,
            getPersistedDuongSuTrungCmndBak(partialUpdatedDuongSuTrungCmndBak)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDuongSuTrungCmndBak() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmndBak.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmndBak
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO = duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndBakMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, duongSuTrungCmndBakDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(duongSuTrungCmndBakDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSuTrungCmndBak in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDuongSuTrungCmndBak() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmndBak.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmndBak
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO = duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndBakMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(duongSuTrungCmndBakDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSuTrungCmndBak in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDuongSuTrungCmndBak() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmndBak.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmndBak
        DuongSuTrungCmndBakDTO duongSuTrungCmndBakDTO = duongSuTrungCmndBakMapper.toDto(duongSuTrungCmndBak);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndBakMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(duongSuTrungCmndBakDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DuongSuTrungCmndBak in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDuongSuTrungCmndBak() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the duongSuTrungCmndBak
        restDuongSuTrungCmndBakMockMvc
            .perform(delete(ENTITY_API_URL_ID, duongSuTrungCmndBak.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return duongSuTrungCmndBakRepository.count();
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

    protected DuongSuTrungCmndBak getPersistedDuongSuTrungCmndBak(DuongSuTrungCmndBak duongSuTrungCmndBak) {
        return duongSuTrungCmndBakRepository.findById(duongSuTrungCmndBak.getId()).orElseThrow();
    }

    protected void assertPersistedDuongSuTrungCmndBakToMatchAllProperties(DuongSuTrungCmndBak expectedDuongSuTrungCmndBak) {
        assertDuongSuTrungCmndBakAllPropertiesEquals(
            expectedDuongSuTrungCmndBak,
            getPersistedDuongSuTrungCmndBak(expectedDuongSuTrungCmndBak)
        );
    }

    protected void assertPersistedDuongSuTrungCmndBakToMatchUpdatableProperties(DuongSuTrungCmndBak expectedDuongSuTrungCmndBak) {
        assertDuongSuTrungCmndBakAllUpdatablePropertiesEquals(
            expectedDuongSuTrungCmndBak,
            getPersistedDuongSuTrungCmndBak(expectedDuongSuTrungCmndBak)
        );
    }
}
