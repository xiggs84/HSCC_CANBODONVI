package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DuongSuTrungCmndAsserts.*;
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
import vn.vnpt.domain.DuongSuTrungCmnd;
import vn.vnpt.repository.DuongSuTrungCmndRepository;
import vn.vnpt.service.dto.DuongSuTrungCmndDTO;
import vn.vnpt.service.mapper.DuongSuTrungCmndMapper;

/**
 * Integration tests for the {@link DuongSuTrungCmndResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DuongSuTrungCmndResourceIT {

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

    private static final Long DEFAULT_ID_DUONG_SU_MIN = 1L;
    private static final Long UPDATED_ID_DUONG_SU_MIN = 2L;

    private static final Long DEFAULT_ID_MASTER_MIN = 1L;
    private static final Long UPDATED_ID_MASTER_MIN = 2L;

    private static final Long DEFAULT_ID_DUONG_SU_MAX = 1L;
    private static final Long UPDATED_ID_DUONG_SU_MAX = 2L;

    private static final Long DEFAULT_ID_MASTER_MAX = 1L;
    private static final Long UPDATED_ID_MASTER_MAX = 2L;

    private static final String ENTITY_API_URL = "/api/duong-su-trung-cmnds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DuongSuTrungCmndRepository duongSuTrungCmndRepository;

    @Autowired
    private DuongSuTrungCmndMapper duongSuTrungCmndMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDuongSuTrungCmndMockMvc;

    private DuongSuTrungCmnd duongSuTrungCmnd;

    private DuongSuTrungCmnd insertedDuongSuTrungCmnd;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuongSuTrungCmnd createEntity() {
        return new DuongSuTrungCmnd()
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
            .soGiayTo(DEFAULT_SO_GIAY_TO)
            .idDuongSuMin(DEFAULT_ID_DUONG_SU_MIN)
            .idMasterMin(DEFAULT_ID_MASTER_MIN)
            .idDuongSuMax(DEFAULT_ID_DUONG_SU_MAX)
            .idMasterMax(DEFAULT_ID_MASTER_MAX);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuongSuTrungCmnd createUpdatedEntity() {
        return new DuongSuTrungCmnd()
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
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idDuongSuMin(UPDATED_ID_DUONG_SU_MIN)
            .idMasterMin(UPDATED_ID_MASTER_MIN)
            .idDuongSuMax(UPDATED_ID_DUONG_SU_MAX)
            .idMasterMax(UPDATED_ID_MASTER_MAX);
    }

    @BeforeEach
    public void initTest() {
        duongSuTrungCmnd = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDuongSuTrungCmnd != null) {
            duongSuTrungCmndRepository.delete(insertedDuongSuTrungCmnd);
            insertedDuongSuTrungCmnd = null;
        }
    }

    @Test
    @Transactional
    void createDuongSuTrungCmnd() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DuongSuTrungCmnd
        DuongSuTrungCmndDTO duongSuTrungCmndDTO = duongSuTrungCmndMapper.toDto(duongSuTrungCmnd);
        var returnedDuongSuTrungCmndDTO = om.readValue(
            restDuongSuTrungCmndMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuTrungCmndDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DuongSuTrungCmndDTO.class
        );

        // Validate the DuongSuTrungCmnd in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDuongSuTrungCmnd = duongSuTrungCmndMapper.toEntity(returnedDuongSuTrungCmndDTO);
        assertDuongSuTrungCmndUpdatableFieldsEquals(returnedDuongSuTrungCmnd, getPersistedDuongSuTrungCmnd(returnedDuongSuTrungCmnd));

        insertedDuongSuTrungCmnd = returnedDuongSuTrungCmnd;
    }

    @Test
    @Transactional
    void createDuongSuTrungCmndWithExistingId() throws Exception {
        // Create the DuongSuTrungCmnd with an existing ID
        duongSuTrungCmnd.setId(1L);
        DuongSuTrungCmndDTO duongSuTrungCmndDTO = duongSuTrungCmndMapper.toDto(duongSuTrungCmnd);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDuongSuTrungCmndMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuTrungCmndDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DuongSuTrungCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmnds() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList
        restDuongSuTrungCmndMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duongSuTrungCmnd.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].soGiayTo").value(hasItem(DEFAULT_SO_GIAY_TO)))
            .andExpect(jsonPath("$.[*].idDuongSuMin").value(hasItem(DEFAULT_ID_DUONG_SU_MIN.intValue())))
            .andExpect(jsonPath("$.[*].idMasterMin").value(hasItem(DEFAULT_ID_MASTER_MIN.intValue())))
            .andExpect(jsonPath("$.[*].idDuongSuMax").value(hasItem(DEFAULT_ID_DUONG_SU_MAX.intValue())))
            .andExpect(jsonPath("$.[*].idMasterMax").value(hasItem(DEFAULT_ID_MASTER_MAX.intValue())));
    }

    @Test
    @Transactional
    void getDuongSuTrungCmnd() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get the duongSuTrungCmnd
        restDuongSuTrungCmndMockMvc
            .perform(get(ENTITY_API_URL_ID, duongSuTrungCmnd.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(duongSuTrungCmnd.getId().intValue()))
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
            .andExpect(jsonPath("$.soGiayTo").value(DEFAULT_SO_GIAY_TO))
            .andExpect(jsonPath("$.idDuongSuMin").value(DEFAULT_ID_DUONG_SU_MIN.intValue()))
            .andExpect(jsonPath("$.idMasterMin").value(DEFAULT_ID_MASTER_MIN.intValue()))
            .andExpect(jsonPath("$.idDuongSuMax").value(DEFAULT_ID_DUONG_SU_MAX.intValue()))
            .andExpect(jsonPath("$.idMasterMax").value(DEFAULT_ID_MASTER_MAX.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDuongSuTrungCmnd() throws Exception {
        // Get the duongSuTrungCmnd
        restDuongSuTrungCmndMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDuongSuTrungCmnd() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSuTrungCmnd
        DuongSuTrungCmnd updatedDuongSuTrungCmnd = duongSuTrungCmndRepository.findById(duongSuTrungCmnd.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDuongSuTrungCmnd are not directly saved in db
        em.detach(updatedDuongSuTrungCmnd);
        updatedDuongSuTrungCmnd
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
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idDuongSuMin(UPDATED_ID_DUONG_SU_MIN)
            .idMasterMin(UPDATED_ID_MASTER_MIN)
            .idDuongSuMax(UPDATED_ID_DUONG_SU_MAX)
            .idMasterMax(UPDATED_ID_MASTER_MAX);
        DuongSuTrungCmndDTO duongSuTrungCmndDTO = duongSuTrungCmndMapper.toDto(updatedDuongSuTrungCmnd);

        restDuongSuTrungCmndMockMvc
            .perform(
                put(ENTITY_API_URL_ID, duongSuTrungCmndDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuTrungCmndDTO))
            )
            .andExpect(status().isOk());

        // Validate the DuongSuTrungCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDuongSuTrungCmndToMatchAllProperties(updatedDuongSuTrungCmnd);
    }

    @Test
    @Transactional
    void putNonExistingDuongSuTrungCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmnd.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmnd
        DuongSuTrungCmndDTO duongSuTrungCmndDTO = duongSuTrungCmndMapper.toDto(duongSuTrungCmnd);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndMockMvc
            .perform(
                put(ENTITY_API_URL_ID, duongSuTrungCmndDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuTrungCmndDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSuTrungCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDuongSuTrungCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmnd.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmnd
        DuongSuTrungCmndDTO duongSuTrungCmndDTO = duongSuTrungCmndMapper.toDto(duongSuTrungCmnd);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuTrungCmndDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSuTrungCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDuongSuTrungCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmnd.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmnd
        DuongSuTrungCmndDTO duongSuTrungCmndDTO = duongSuTrungCmndMapper.toDto(duongSuTrungCmnd);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuTrungCmndDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DuongSuTrungCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDuongSuTrungCmndWithPatch() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSuTrungCmnd using partial update
        DuongSuTrungCmnd partialUpdatedDuongSuTrungCmnd = new DuongSuTrungCmnd();
        partialUpdatedDuongSuTrungCmnd.setId(duongSuTrungCmnd.getId());

        partialUpdatedDuongSuTrungCmnd
            .trangThai(UPDATED_TRANG_THAI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idMaster(UPDATED_ID_MASTER)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idDuongSuMin(UPDATED_ID_DUONG_SU_MIN)
            .idMasterMin(UPDATED_ID_MASTER_MIN);

        restDuongSuTrungCmndMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDuongSuTrungCmnd.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDuongSuTrungCmnd))
            )
            .andExpect(status().isOk());

        // Validate the DuongSuTrungCmnd in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDuongSuTrungCmndUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDuongSuTrungCmnd, duongSuTrungCmnd),
            getPersistedDuongSuTrungCmnd(duongSuTrungCmnd)
        );
    }

    @Test
    @Transactional
    void fullUpdateDuongSuTrungCmndWithPatch() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSuTrungCmnd using partial update
        DuongSuTrungCmnd partialUpdatedDuongSuTrungCmnd = new DuongSuTrungCmnd();
        partialUpdatedDuongSuTrungCmnd.setId(duongSuTrungCmnd.getId());

        partialUpdatedDuongSuTrungCmnd
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
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idDuongSuMin(UPDATED_ID_DUONG_SU_MIN)
            .idMasterMin(UPDATED_ID_MASTER_MIN)
            .idDuongSuMax(UPDATED_ID_DUONG_SU_MAX)
            .idMasterMax(UPDATED_ID_MASTER_MAX);

        restDuongSuTrungCmndMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDuongSuTrungCmnd.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDuongSuTrungCmnd))
            )
            .andExpect(status().isOk());

        // Validate the DuongSuTrungCmnd in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDuongSuTrungCmndUpdatableFieldsEquals(
            partialUpdatedDuongSuTrungCmnd,
            getPersistedDuongSuTrungCmnd(partialUpdatedDuongSuTrungCmnd)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDuongSuTrungCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmnd.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmnd
        DuongSuTrungCmndDTO duongSuTrungCmndDTO = duongSuTrungCmndMapper.toDto(duongSuTrungCmnd);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, duongSuTrungCmndDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(duongSuTrungCmndDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSuTrungCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDuongSuTrungCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmnd.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmnd
        DuongSuTrungCmndDTO duongSuTrungCmndDTO = duongSuTrungCmndMapper.toDto(duongSuTrungCmnd);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(duongSuTrungCmndDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSuTrungCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDuongSuTrungCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSuTrungCmnd.setId(longCount.incrementAndGet());

        // Create the DuongSuTrungCmnd
        DuongSuTrungCmndDTO duongSuTrungCmndDTO = duongSuTrungCmndMapper.toDto(duongSuTrungCmnd);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuTrungCmndMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(duongSuTrungCmndDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DuongSuTrungCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDuongSuTrungCmnd() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the duongSuTrungCmnd
        restDuongSuTrungCmndMockMvc
            .perform(delete(ENTITY_API_URL_ID, duongSuTrungCmnd.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return duongSuTrungCmndRepository.count();
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

    protected DuongSuTrungCmnd getPersistedDuongSuTrungCmnd(DuongSuTrungCmnd duongSuTrungCmnd) {
        return duongSuTrungCmndRepository.findById(duongSuTrungCmnd.getId()).orElseThrow();
    }

    protected void assertPersistedDuongSuTrungCmndToMatchAllProperties(DuongSuTrungCmnd expectedDuongSuTrungCmnd) {
        assertDuongSuTrungCmndAllPropertiesEquals(expectedDuongSuTrungCmnd, getPersistedDuongSuTrungCmnd(expectedDuongSuTrungCmnd));
    }

    protected void assertPersistedDuongSuTrungCmndToMatchUpdatableProperties(DuongSuTrungCmnd expectedDuongSuTrungCmnd) {
        assertDuongSuTrungCmndAllUpdatablePropertiesEquals(
            expectedDuongSuTrungCmnd,
            getPersistedDuongSuTrungCmnd(expectedDuongSuTrungCmnd)
        );
    }
}
