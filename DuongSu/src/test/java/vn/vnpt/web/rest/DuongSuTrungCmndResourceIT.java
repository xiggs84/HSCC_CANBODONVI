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
import vn.vnpt.domain.DuongSu;
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
    private static final Integer SMALLER_TRANG_THAI = 0 - 1;

    private static final String DEFAULT_THONG_TIN_DS = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_DS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_THAO_TAC = LocalDate.ofEpochDay(-1L);

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;
    private static final Long SMALLER_NGUOI_THAO_TAC = 1L - 1L;

    private static final Long DEFAULT_ID_DS_GOC = 1L;
    private static final Long UPDATED_ID_DS_GOC = 2L;
    private static final Long SMALLER_ID_DS_GOC = 1L - 1L;

    private static final String DEFAULT_ID_MASTER = "AAAAAAAAAA";
    private static final String UPDATED_ID_MASTER = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;
    private static final Long SMALLER_ID_DON_VI = 1L - 1L;

    private static final String DEFAULT_STR_SEARCH = "AAAAAAAAAA";
    private static final String UPDATED_STR_SEARCH = "BBBBBBBBBB";

    private static final String DEFAULT_SO_GIAY_TO = "AAAAAAAAAA";
    private static final String UPDATED_SO_GIAY_TO = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DUONG_SU_MIN = 1L;
    private static final Long UPDATED_ID_DUONG_SU_MIN = 2L;
    private static final Long SMALLER_ID_DUONG_SU_MIN = 1L - 1L;

    private static final Long DEFAULT_ID_MASTER_MIN = 1L;
    private static final Long UPDATED_ID_MASTER_MIN = 2L;
    private static final Long SMALLER_ID_MASTER_MIN = 1L - 1L;

    private static final Long DEFAULT_ID_DUONG_SU_MAX = 1L;
    private static final Long UPDATED_ID_DUONG_SU_MAX = 2L;
    private static final Long SMALLER_ID_DUONG_SU_MAX = 1L - 1L;

    private static final Long DEFAULT_ID_MASTER_MAX = 1L;
    private static final Long UPDATED_ID_MASTER_MAX = 2L;
    private static final Long SMALLER_ID_MASTER_MAX = 1L - 1L;

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
    void getDuongSuTrungCmndsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        Long id = duongSuTrungCmnd.getId();

        defaultDuongSuTrungCmndFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDuongSuTrungCmndFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDuongSuTrungCmndFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTenDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where tenDuongSu equals to
        defaultDuongSuTrungCmndFiltering("tenDuongSu.equals=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.equals=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTenDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where tenDuongSu in
        defaultDuongSuTrungCmndFiltering(
            "tenDuongSu.in=" + DEFAULT_TEN_DUONG_SU + "," + UPDATED_TEN_DUONG_SU,
            "tenDuongSu.in=" + UPDATED_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTenDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where tenDuongSu is not null
        defaultDuongSuTrungCmndFiltering("tenDuongSu.specified=true", "tenDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTenDuongSuContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where tenDuongSu contains
        defaultDuongSuTrungCmndFiltering("tenDuongSu.contains=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.contains=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTenDuongSuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where tenDuongSu does not contain
        defaultDuongSuTrungCmndFiltering(
            "tenDuongSu.doesNotContain=" + UPDATED_TEN_DUONG_SU,
            "tenDuongSu.doesNotContain=" + DEFAULT_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByDiaChiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where diaChi equals to
        defaultDuongSuTrungCmndFiltering("diaChi.equals=" + DEFAULT_DIA_CHI, "diaChi.equals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByDiaChiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where diaChi in
        defaultDuongSuTrungCmndFiltering("diaChi.in=" + DEFAULT_DIA_CHI + "," + UPDATED_DIA_CHI, "diaChi.in=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByDiaChiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where diaChi is not null
        defaultDuongSuTrungCmndFiltering("diaChi.specified=true", "diaChi.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByDiaChiContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where diaChi contains
        defaultDuongSuTrungCmndFiltering("diaChi.contains=" + DEFAULT_DIA_CHI, "diaChi.contains=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByDiaChiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where diaChi does not contain
        defaultDuongSuTrungCmndFiltering("diaChi.doesNotContain=" + UPDATED_DIA_CHI, "diaChi.doesNotContain=" + DEFAULT_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where trangThai equals to
        defaultDuongSuTrungCmndFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where trangThai in
        defaultDuongSuTrungCmndFiltering(
            "trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI,
            "trangThai.in=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where trangThai is not null
        defaultDuongSuTrungCmndFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where trangThai is greater than or equal to
        defaultDuongSuTrungCmndFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + (DEFAULT_TRANG_THAI + 1)
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where trangThai is less than or equal to
        defaultDuongSuTrungCmndFiltering(
            "trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where trangThai is less than
        defaultDuongSuTrungCmndFiltering("trangThai.lessThan=" + (DEFAULT_TRANG_THAI + 1), "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where trangThai is greater than
        defaultDuongSuTrungCmndFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByThongTinDsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where thongTinDs equals to
        defaultDuongSuTrungCmndFiltering("thongTinDs.equals=" + DEFAULT_THONG_TIN_DS, "thongTinDs.equals=" + UPDATED_THONG_TIN_DS);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByThongTinDsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where thongTinDs in
        defaultDuongSuTrungCmndFiltering(
            "thongTinDs.in=" + DEFAULT_THONG_TIN_DS + "," + UPDATED_THONG_TIN_DS,
            "thongTinDs.in=" + UPDATED_THONG_TIN_DS
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByThongTinDsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where thongTinDs is not null
        defaultDuongSuTrungCmndFiltering("thongTinDs.specified=true", "thongTinDs.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByThongTinDsContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where thongTinDs contains
        defaultDuongSuTrungCmndFiltering("thongTinDs.contains=" + DEFAULT_THONG_TIN_DS, "thongTinDs.contains=" + UPDATED_THONG_TIN_DS);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByThongTinDsNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where thongTinDs does not contain
        defaultDuongSuTrungCmndFiltering(
            "thongTinDs.doesNotContain=" + UPDATED_THONG_TIN_DS,
            "thongTinDs.doesNotContain=" + DEFAULT_THONG_TIN_DS
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where ngayThaoTac equals to
        defaultDuongSuTrungCmndFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where ngayThaoTac in
        defaultDuongSuTrungCmndFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where ngayThaoTac is not null
        defaultDuongSuTrungCmndFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where ngayThaoTac is greater than or equal to
        defaultDuongSuTrungCmndFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where ngayThaoTac is less than or equal to
        defaultDuongSuTrungCmndFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where ngayThaoTac is less than
        defaultDuongSuTrungCmndFiltering("ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC, "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where ngayThaoTac is greater than
        defaultDuongSuTrungCmndFiltering(
            "ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where nguoiThaoTac equals to
        defaultDuongSuTrungCmndFiltering("nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC, "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where nguoiThaoTac in
        defaultDuongSuTrungCmndFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where nguoiThaoTac is not null
        defaultDuongSuTrungCmndFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where nguoiThaoTac is greater than or equal to
        defaultDuongSuTrungCmndFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where nguoiThaoTac is less than or equal to
        defaultDuongSuTrungCmndFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where nguoiThaoTac is less than
        defaultDuongSuTrungCmndFiltering(
            "nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where nguoiThaoTac is greater than
        defaultDuongSuTrungCmndFiltering(
            "nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDsGocIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDsGoc equals to
        defaultDuongSuTrungCmndFiltering("idDsGoc.equals=" + DEFAULT_ID_DS_GOC, "idDsGoc.equals=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDsGocIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDsGoc in
        defaultDuongSuTrungCmndFiltering("idDsGoc.in=" + DEFAULT_ID_DS_GOC + "," + UPDATED_ID_DS_GOC, "idDsGoc.in=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDsGocIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDsGoc is not null
        defaultDuongSuTrungCmndFiltering("idDsGoc.specified=true", "idDsGoc.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDsGocIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDsGoc is greater than or equal to
        defaultDuongSuTrungCmndFiltering(
            "idDsGoc.greaterThanOrEqual=" + DEFAULT_ID_DS_GOC,
            "idDsGoc.greaterThanOrEqual=" + UPDATED_ID_DS_GOC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDsGocIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDsGoc is less than or equal to
        defaultDuongSuTrungCmndFiltering("idDsGoc.lessThanOrEqual=" + DEFAULT_ID_DS_GOC, "idDsGoc.lessThanOrEqual=" + SMALLER_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDsGocIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDsGoc is less than
        defaultDuongSuTrungCmndFiltering("idDsGoc.lessThan=" + UPDATED_ID_DS_GOC, "idDsGoc.lessThan=" + DEFAULT_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDsGocIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDsGoc is greater than
        defaultDuongSuTrungCmndFiltering("idDsGoc.greaterThan=" + SMALLER_ID_DS_GOC, "idDsGoc.greaterThan=" + DEFAULT_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMaster equals to
        defaultDuongSuTrungCmndFiltering("idMaster.equals=" + DEFAULT_ID_MASTER, "idMaster.equals=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMaster in
        defaultDuongSuTrungCmndFiltering("idMaster.in=" + DEFAULT_ID_MASTER + "," + UPDATED_ID_MASTER, "idMaster.in=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMaster is not null
        defaultDuongSuTrungCmndFiltering("idMaster.specified=true", "idMaster.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMaster contains
        defaultDuongSuTrungCmndFiltering("idMaster.contains=" + DEFAULT_ID_MASTER, "idMaster.contains=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMaster does not contain
        defaultDuongSuTrungCmndFiltering("idMaster.doesNotContain=" + UPDATED_ID_MASTER, "idMaster.doesNotContain=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDonVi equals to
        defaultDuongSuTrungCmndFiltering("idDonVi.equals=" + DEFAULT_ID_DON_VI, "idDonVi.equals=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDonVi in
        defaultDuongSuTrungCmndFiltering("idDonVi.in=" + DEFAULT_ID_DON_VI + "," + UPDATED_ID_DON_VI, "idDonVi.in=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDonVi is not null
        defaultDuongSuTrungCmndFiltering("idDonVi.specified=true", "idDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDonViIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDonVi is greater than or equal to
        defaultDuongSuTrungCmndFiltering(
            "idDonVi.greaterThanOrEqual=" + DEFAULT_ID_DON_VI,
            "idDonVi.greaterThanOrEqual=" + UPDATED_ID_DON_VI
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDonViIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDonVi is less than or equal to
        defaultDuongSuTrungCmndFiltering("idDonVi.lessThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.lessThanOrEqual=" + SMALLER_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDonViIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDonVi is less than
        defaultDuongSuTrungCmndFiltering("idDonVi.lessThan=" + UPDATED_ID_DON_VI, "idDonVi.lessThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDonViIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDonVi is greater than
        defaultDuongSuTrungCmndFiltering("idDonVi.greaterThan=" + SMALLER_ID_DON_VI, "idDonVi.greaterThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByStrSearchIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where strSearch equals to
        defaultDuongSuTrungCmndFiltering("strSearch.equals=" + DEFAULT_STR_SEARCH, "strSearch.equals=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByStrSearchIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where strSearch in
        defaultDuongSuTrungCmndFiltering(
            "strSearch.in=" + DEFAULT_STR_SEARCH + "," + UPDATED_STR_SEARCH,
            "strSearch.in=" + UPDATED_STR_SEARCH
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByStrSearchIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where strSearch is not null
        defaultDuongSuTrungCmndFiltering("strSearch.specified=true", "strSearch.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByStrSearchContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where strSearch contains
        defaultDuongSuTrungCmndFiltering("strSearch.contains=" + DEFAULT_STR_SEARCH, "strSearch.contains=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByStrSearchNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where strSearch does not contain
        defaultDuongSuTrungCmndFiltering(
            "strSearch.doesNotContain=" + UPDATED_STR_SEARCH,
            "strSearch.doesNotContain=" + DEFAULT_STR_SEARCH
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsBySoGiayToIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where soGiayTo equals to
        defaultDuongSuTrungCmndFiltering("soGiayTo.equals=" + DEFAULT_SO_GIAY_TO, "soGiayTo.equals=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsBySoGiayToIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where soGiayTo in
        defaultDuongSuTrungCmndFiltering(
            "soGiayTo.in=" + DEFAULT_SO_GIAY_TO + "," + UPDATED_SO_GIAY_TO,
            "soGiayTo.in=" + UPDATED_SO_GIAY_TO
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsBySoGiayToIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where soGiayTo is not null
        defaultDuongSuTrungCmndFiltering("soGiayTo.specified=true", "soGiayTo.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsBySoGiayToContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where soGiayTo contains
        defaultDuongSuTrungCmndFiltering("soGiayTo.contains=" + DEFAULT_SO_GIAY_TO, "soGiayTo.contains=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsBySoGiayToNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where soGiayTo does not contain
        defaultDuongSuTrungCmndFiltering("soGiayTo.doesNotContain=" + UPDATED_SO_GIAY_TO, "soGiayTo.doesNotContain=" + DEFAULT_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMinIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMin equals to
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMin.equals=" + DEFAULT_ID_DUONG_SU_MIN,
            "idDuongSuMin.equals=" + UPDATED_ID_DUONG_SU_MIN
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMinIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMin in
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMin.in=" + DEFAULT_ID_DUONG_SU_MIN + "," + UPDATED_ID_DUONG_SU_MIN,
            "idDuongSuMin.in=" + UPDATED_ID_DUONG_SU_MIN
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMin is not null
        defaultDuongSuTrungCmndFiltering("idDuongSuMin.specified=true", "idDuongSuMin.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMin is greater than or equal to
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMin.greaterThanOrEqual=" + DEFAULT_ID_DUONG_SU_MIN,
            "idDuongSuMin.greaterThanOrEqual=" + UPDATED_ID_DUONG_SU_MIN
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMinIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMin is less than or equal to
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMin.lessThanOrEqual=" + DEFAULT_ID_DUONG_SU_MIN,
            "idDuongSuMin.lessThanOrEqual=" + SMALLER_ID_DUONG_SU_MIN
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMinIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMin is less than
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMin.lessThan=" + UPDATED_ID_DUONG_SU_MIN,
            "idDuongSuMin.lessThan=" + DEFAULT_ID_DUONG_SU_MIN
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMinIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMin is greater than
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMin.greaterThan=" + SMALLER_ID_DUONG_SU_MIN,
            "idDuongSuMin.greaterThan=" + DEFAULT_ID_DUONG_SU_MIN
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMinIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMin equals to
        defaultDuongSuTrungCmndFiltering("idMasterMin.equals=" + DEFAULT_ID_MASTER_MIN, "idMasterMin.equals=" + UPDATED_ID_MASTER_MIN);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMinIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMin in
        defaultDuongSuTrungCmndFiltering(
            "idMasterMin.in=" + DEFAULT_ID_MASTER_MIN + "," + UPDATED_ID_MASTER_MIN,
            "idMasterMin.in=" + UPDATED_ID_MASTER_MIN
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMin is not null
        defaultDuongSuTrungCmndFiltering("idMasterMin.specified=true", "idMasterMin.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMin is greater than or equal to
        defaultDuongSuTrungCmndFiltering(
            "idMasterMin.greaterThanOrEqual=" + DEFAULT_ID_MASTER_MIN,
            "idMasterMin.greaterThanOrEqual=" + UPDATED_ID_MASTER_MIN
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMinIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMin is less than or equal to
        defaultDuongSuTrungCmndFiltering(
            "idMasterMin.lessThanOrEqual=" + DEFAULT_ID_MASTER_MIN,
            "idMasterMin.lessThanOrEqual=" + SMALLER_ID_MASTER_MIN
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMinIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMin is less than
        defaultDuongSuTrungCmndFiltering("idMasterMin.lessThan=" + UPDATED_ID_MASTER_MIN, "idMasterMin.lessThan=" + DEFAULT_ID_MASTER_MIN);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMinIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMin is greater than
        defaultDuongSuTrungCmndFiltering(
            "idMasterMin.greaterThan=" + SMALLER_ID_MASTER_MIN,
            "idMasterMin.greaterThan=" + DEFAULT_ID_MASTER_MIN
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMax equals to
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMax.equals=" + DEFAULT_ID_DUONG_SU_MAX,
            "idDuongSuMax.equals=" + UPDATED_ID_DUONG_SU_MAX
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMaxIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMax in
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMax.in=" + DEFAULT_ID_DUONG_SU_MAX + "," + UPDATED_ID_DUONG_SU_MAX,
            "idDuongSuMax.in=" + UPDATED_ID_DUONG_SU_MAX
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMax is not null
        defaultDuongSuTrungCmndFiltering("idDuongSuMax.specified=true", "idDuongSuMax.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMax is greater than or equal to
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMax.greaterThanOrEqual=" + DEFAULT_ID_DUONG_SU_MAX,
            "idDuongSuMax.greaterThanOrEqual=" + UPDATED_ID_DUONG_SU_MAX
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMaxIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMax is less than or equal to
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMax.lessThanOrEqual=" + DEFAULT_ID_DUONG_SU_MAX,
            "idDuongSuMax.lessThanOrEqual=" + SMALLER_ID_DUONG_SU_MAX
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMax is less than
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMax.lessThan=" + UPDATED_ID_DUONG_SU_MAX,
            "idDuongSuMax.lessThan=" + DEFAULT_ID_DUONG_SU_MAX
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdDuongSuMaxIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idDuongSuMax is greater than
        defaultDuongSuTrungCmndFiltering(
            "idDuongSuMax.greaterThan=" + SMALLER_ID_DUONG_SU_MAX,
            "idDuongSuMax.greaterThan=" + DEFAULT_ID_DUONG_SU_MAX
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMax equals to
        defaultDuongSuTrungCmndFiltering("idMasterMax.equals=" + DEFAULT_ID_MASTER_MAX, "idMasterMax.equals=" + UPDATED_ID_MASTER_MAX);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMaxIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMax in
        defaultDuongSuTrungCmndFiltering(
            "idMasterMax.in=" + DEFAULT_ID_MASTER_MAX + "," + UPDATED_ID_MASTER_MAX,
            "idMasterMax.in=" + UPDATED_ID_MASTER_MAX
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMax is not null
        defaultDuongSuTrungCmndFiltering("idMasterMax.specified=true", "idMasterMax.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMax is greater than or equal to
        defaultDuongSuTrungCmndFiltering(
            "idMasterMax.greaterThanOrEqual=" + DEFAULT_ID_MASTER_MAX,
            "idMasterMax.greaterThanOrEqual=" + UPDATED_ID_MASTER_MAX
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMaxIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMax is less than or equal to
        defaultDuongSuTrungCmndFiltering(
            "idMasterMax.lessThanOrEqual=" + DEFAULT_ID_MASTER_MAX,
            "idMasterMax.lessThanOrEqual=" + SMALLER_ID_MASTER_MAX
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMax is less than
        defaultDuongSuTrungCmndFiltering("idMasterMax.lessThan=" + UPDATED_ID_MASTER_MAX, "idMasterMax.lessThan=" + DEFAULT_ID_MASTER_MAX);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByIdMasterMaxIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmnd = duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);

        // Get all the duongSuTrungCmndList where idMasterMax is greater than
        defaultDuongSuTrungCmndFiltering(
            "idMasterMax.greaterThan=" + SMALLER_ID_MASTER_MAX,
            "idMasterMax.greaterThan=" + DEFAULT_ID_MASTER_MAX
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndsByDuongSuIsEqualToSomething() throws Exception {
        DuongSu duongSu;
        if (TestUtil.findAll(em, DuongSu.class).isEmpty()) {
            duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);
            duongSu = DuongSuResourceIT.createEntity();
        } else {
            duongSu = TestUtil.findAll(em, DuongSu.class).get(0);
        }
        em.persist(duongSu);
        em.flush();
        duongSuTrungCmnd.setDuongSu(duongSu);
        duongSuTrungCmndRepository.saveAndFlush(duongSuTrungCmnd);
        Long duongSuId = duongSu.getIdDuongSu();
        // Get all the duongSuTrungCmndList where duongSu equals to duongSuId
        defaultDuongSuTrungCmndShouldBeFound("duongSuId.equals=" + duongSuId);

        // Get all the duongSuTrungCmndList where duongSu equals to (duongSuId + 1)
        defaultDuongSuTrungCmndShouldNotBeFound("duongSuId.equals=" + (duongSuId + 1));
    }

    private void defaultDuongSuTrungCmndFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDuongSuTrungCmndShouldBeFound(shouldBeFound);
        defaultDuongSuTrungCmndShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDuongSuTrungCmndShouldBeFound(String filter) throws Exception {
        restDuongSuTrungCmndMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
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

        // Check, that the count call also returns 1
        restDuongSuTrungCmndMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDuongSuTrungCmndShouldNotBeFound(String filter) throws Exception {
        restDuongSuTrungCmndMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDuongSuTrungCmndMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
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
            .diaChi(UPDATED_DIA_CHI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDonVi(UPDATED_ID_DON_VI)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idDuongSuMin(UPDATED_ID_DUONG_SU_MIN);

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
