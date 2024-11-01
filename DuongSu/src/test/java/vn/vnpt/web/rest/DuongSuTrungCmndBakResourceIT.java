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
import vn.vnpt.domain.DuongSu;
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
    void getDuongSuTrungCmndBaksByIdFiltering() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        Long id = duongSuTrungCmndBak.getId();

        defaultDuongSuTrungCmndBakFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDuongSuTrungCmndBakFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDuongSuTrungCmndBakFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTenDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where tenDuongSu equals to
        defaultDuongSuTrungCmndBakFiltering("tenDuongSu.equals=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.equals=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTenDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where tenDuongSu in
        defaultDuongSuTrungCmndBakFiltering(
            "tenDuongSu.in=" + DEFAULT_TEN_DUONG_SU + "," + UPDATED_TEN_DUONG_SU,
            "tenDuongSu.in=" + UPDATED_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTenDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where tenDuongSu is not null
        defaultDuongSuTrungCmndBakFiltering("tenDuongSu.specified=true", "tenDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTenDuongSuContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where tenDuongSu contains
        defaultDuongSuTrungCmndBakFiltering("tenDuongSu.contains=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.contains=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTenDuongSuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where tenDuongSu does not contain
        defaultDuongSuTrungCmndBakFiltering(
            "tenDuongSu.doesNotContain=" + UPDATED_TEN_DUONG_SU,
            "tenDuongSu.doesNotContain=" + DEFAULT_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByDiaChiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where diaChi equals to
        defaultDuongSuTrungCmndBakFiltering("diaChi.equals=" + DEFAULT_DIA_CHI, "diaChi.equals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByDiaChiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where diaChi in
        defaultDuongSuTrungCmndBakFiltering("diaChi.in=" + DEFAULT_DIA_CHI + "," + UPDATED_DIA_CHI, "diaChi.in=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByDiaChiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where diaChi is not null
        defaultDuongSuTrungCmndBakFiltering("diaChi.specified=true", "diaChi.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByDiaChiContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where diaChi contains
        defaultDuongSuTrungCmndBakFiltering("diaChi.contains=" + DEFAULT_DIA_CHI, "diaChi.contains=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByDiaChiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where diaChi does not contain
        defaultDuongSuTrungCmndBakFiltering("diaChi.doesNotContain=" + UPDATED_DIA_CHI, "diaChi.doesNotContain=" + DEFAULT_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where trangThai equals to
        defaultDuongSuTrungCmndBakFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where trangThai in
        defaultDuongSuTrungCmndBakFiltering(
            "trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI,
            "trangThai.in=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where trangThai is not null
        defaultDuongSuTrungCmndBakFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where trangThai is greater than or equal to
        defaultDuongSuTrungCmndBakFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + (DEFAULT_TRANG_THAI + 1)
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where trangThai is less than or equal to
        defaultDuongSuTrungCmndBakFiltering(
            "trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where trangThai is less than
        defaultDuongSuTrungCmndBakFiltering("trangThai.lessThan=" + (DEFAULT_TRANG_THAI + 1), "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where trangThai is greater than
        defaultDuongSuTrungCmndBakFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByThongTinDsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where thongTinDs equals to
        defaultDuongSuTrungCmndBakFiltering("thongTinDs.equals=" + DEFAULT_THONG_TIN_DS, "thongTinDs.equals=" + UPDATED_THONG_TIN_DS);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByThongTinDsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where thongTinDs in
        defaultDuongSuTrungCmndBakFiltering(
            "thongTinDs.in=" + DEFAULT_THONG_TIN_DS + "," + UPDATED_THONG_TIN_DS,
            "thongTinDs.in=" + UPDATED_THONG_TIN_DS
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByThongTinDsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where thongTinDs is not null
        defaultDuongSuTrungCmndBakFiltering("thongTinDs.specified=true", "thongTinDs.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByThongTinDsContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where thongTinDs contains
        defaultDuongSuTrungCmndBakFiltering("thongTinDs.contains=" + DEFAULT_THONG_TIN_DS, "thongTinDs.contains=" + UPDATED_THONG_TIN_DS);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByThongTinDsNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where thongTinDs does not contain
        defaultDuongSuTrungCmndBakFiltering(
            "thongTinDs.doesNotContain=" + UPDATED_THONG_TIN_DS,
            "thongTinDs.doesNotContain=" + DEFAULT_THONG_TIN_DS
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where ngayThaoTac equals to
        defaultDuongSuTrungCmndBakFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where ngayThaoTac in
        defaultDuongSuTrungCmndBakFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where ngayThaoTac is not null
        defaultDuongSuTrungCmndBakFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where ngayThaoTac is greater than or equal to
        defaultDuongSuTrungCmndBakFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where ngayThaoTac is less than or equal to
        defaultDuongSuTrungCmndBakFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where ngayThaoTac is less than
        defaultDuongSuTrungCmndBakFiltering(
            "ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where ngayThaoTac is greater than
        defaultDuongSuTrungCmndBakFiltering(
            "ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where nguoiThaoTac equals to
        defaultDuongSuTrungCmndBakFiltering(
            "nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where nguoiThaoTac in
        defaultDuongSuTrungCmndBakFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where nguoiThaoTac is not null
        defaultDuongSuTrungCmndBakFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where nguoiThaoTac is greater than or equal to
        defaultDuongSuTrungCmndBakFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where nguoiThaoTac is less than or equal to
        defaultDuongSuTrungCmndBakFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where nguoiThaoTac is less than
        defaultDuongSuTrungCmndBakFiltering(
            "nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where nguoiThaoTac is greater than
        defaultDuongSuTrungCmndBakFiltering(
            "nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDsGocIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDsGoc equals to
        defaultDuongSuTrungCmndBakFiltering("idDsGoc.equals=" + DEFAULT_ID_DS_GOC, "idDsGoc.equals=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDsGocIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDsGoc in
        defaultDuongSuTrungCmndBakFiltering("idDsGoc.in=" + DEFAULT_ID_DS_GOC + "," + UPDATED_ID_DS_GOC, "idDsGoc.in=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDsGocIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDsGoc is not null
        defaultDuongSuTrungCmndBakFiltering("idDsGoc.specified=true", "idDsGoc.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDsGocIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDsGoc is greater than or equal to
        defaultDuongSuTrungCmndBakFiltering(
            "idDsGoc.greaterThanOrEqual=" + DEFAULT_ID_DS_GOC,
            "idDsGoc.greaterThanOrEqual=" + UPDATED_ID_DS_GOC
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDsGocIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDsGoc is less than or equal to
        defaultDuongSuTrungCmndBakFiltering("idDsGoc.lessThanOrEqual=" + DEFAULT_ID_DS_GOC, "idDsGoc.lessThanOrEqual=" + SMALLER_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDsGocIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDsGoc is less than
        defaultDuongSuTrungCmndBakFiltering("idDsGoc.lessThan=" + UPDATED_ID_DS_GOC, "idDsGoc.lessThan=" + DEFAULT_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDsGocIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDsGoc is greater than
        defaultDuongSuTrungCmndBakFiltering("idDsGoc.greaterThan=" + SMALLER_ID_DS_GOC, "idDsGoc.greaterThan=" + DEFAULT_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdMasterIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idMaster equals to
        defaultDuongSuTrungCmndBakFiltering("idMaster.equals=" + DEFAULT_ID_MASTER, "idMaster.equals=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdMasterIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idMaster in
        defaultDuongSuTrungCmndBakFiltering(
            "idMaster.in=" + DEFAULT_ID_MASTER + "," + UPDATED_ID_MASTER,
            "idMaster.in=" + UPDATED_ID_MASTER
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdMasterIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idMaster is not null
        defaultDuongSuTrungCmndBakFiltering("idMaster.specified=true", "idMaster.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdMasterContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idMaster contains
        defaultDuongSuTrungCmndBakFiltering("idMaster.contains=" + DEFAULT_ID_MASTER, "idMaster.contains=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdMasterNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idMaster does not contain
        defaultDuongSuTrungCmndBakFiltering("idMaster.doesNotContain=" + UPDATED_ID_MASTER, "idMaster.doesNotContain=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDonVi equals to
        defaultDuongSuTrungCmndBakFiltering("idDonVi.equals=" + DEFAULT_ID_DON_VI, "idDonVi.equals=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDonVi in
        defaultDuongSuTrungCmndBakFiltering("idDonVi.in=" + DEFAULT_ID_DON_VI + "," + UPDATED_ID_DON_VI, "idDonVi.in=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDonVi is not null
        defaultDuongSuTrungCmndBakFiltering("idDonVi.specified=true", "idDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDonViIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDonVi is greater than or equal to
        defaultDuongSuTrungCmndBakFiltering(
            "idDonVi.greaterThanOrEqual=" + DEFAULT_ID_DON_VI,
            "idDonVi.greaterThanOrEqual=" + UPDATED_ID_DON_VI
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDonViIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDonVi is less than or equal to
        defaultDuongSuTrungCmndBakFiltering("idDonVi.lessThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.lessThanOrEqual=" + SMALLER_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDonViIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDonVi is less than
        defaultDuongSuTrungCmndBakFiltering("idDonVi.lessThan=" + UPDATED_ID_DON_VI, "idDonVi.lessThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByIdDonViIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where idDonVi is greater than
        defaultDuongSuTrungCmndBakFiltering("idDonVi.greaterThan=" + SMALLER_ID_DON_VI, "idDonVi.greaterThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByStrSearchIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where strSearch equals to
        defaultDuongSuTrungCmndBakFiltering("strSearch.equals=" + DEFAULT_STR_SEARCH, "strSearch.equals=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByStrSearchIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where strSearch in
        defaultDuongSuTrungCmndBakFiltering(
            "strSearch.in=" + DEFAULT_STR_SEARCH + "," + UPDATED_STR_SEARCH,
            "strSearch.in=" + UPDATED_STR_SEARCH
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByStrSearchIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where strSearch is not null
        defaultDuongSuTrungCmndBakFiltering("strSearch.specified=true", "strSearch.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByStrSearchContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where strSearch contains
        defaultDuongSuTrungCmndBakFiltering("strSearch.contains=" + DEFAULT_STR_SEARCH, "strSearch.contains=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByStrSearchNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where strSearch does not contain
        defaultDuongSuTrungCmndBakFiltering(
            "strSearch.doesNotContain=" + UPDATED_STR_SEARCH,
            "strSearch.doesNotContain=" + DEFAULT_STR_SEARCH
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksBySoGiayToIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where soGiayTo equals to
        defaultDuongSuTrungCmndBakFiltering("soGiayTo.equals=" + DEFAULT_SO_GIAY_TO, "soGiayTo.equals=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksBySoGiayToIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where soGiayTo in
        defaultDuongSuTrungCmndBakFiltering(
            "soGiayTo.in=" + DEFAULT_SO_GIAY_TO + "," + UPDATED_SO_GIAY_TO,
            "soGiayTo.in=" + UPDATED_SO_GIAY_TO
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksBySoGiayToIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where soGiayTo is not null
        defaultDuongSuTrungCmndBakFiltering("soGiayTo.specified=true", "soGiayTo.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksBySoGiayToContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where soGiayTo contains
        defaultDuongSuTrungCmndBakFiltering("soGiayTo.contains=" + DEFAULT_SO_GIAY_TO, "soGiayTo.contains=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksBySoGiayToNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSuTrungCmndBak = duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);

        // Get all the duongSuTrungCmndBakList where soGiayTo does not contain
        defaultDuongSuTrungCmndBakFiltering(
            "soGiayTo.doesNotContain=" + UPDATED_SO_GIAY_TO,
            "soGiayTo.doesNotContain=" + DEFAULT_SO_GIAY_TO
        );
    }

    @Test
    @Transactional
    void getAllDuongSuTrungCmndBaksByDuongSuIsEqualToSomething() throws Exception {
        DuongSu duongSu;
        if (TestUtil.findAll(em, DuongSu.class).isEmpty()) {
            duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);
            duongSu = DuongSuResourceIT.createEntity();
        } else {
            duongSu = TestUtil.findAll(em, DuongSu.class).get(0);
        }
        em.persist(duongSu);
        em.flush();
        duongSuTrungCmndBak.setDuongSu(duongSu);
        duongSuTrungCmndBakRepository.saveAndFlush(duongSuTrungCmndBak);
        Long duongSuId = duongSu.getIdDuongSu();
        // Get all the duongSuTrungCmndBakList where duongSu equals to duongSuId
        defaultDuongSuTrungCmndBakShouldBeFound("duongSuId.equals=" + duongSuId);

        // Get all the duongSuTrungCmndBakList where duongSu equals to (duongSuId + 1)
        defaultDuongSuTrungCmndBakShouldNotBeFound("duongSuId.equals=" + (duongSuId + 1));
    }

    private void defaultDuongSuTrungCmndBakFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDuongSuTrungCmndBakShouldBeFound(shouldBeFound);
        defaultDuongSuTrungCmndBakShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDuongSuTrungCmndBakShouldBeFound(String filter) throws Exception {
        restDuongSuTrungCmndBakMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
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

        // Check, that the count call also returns 1
        restDuongSuTrungCmndBakMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDuongSuTrungCmndBakShouldNotBeFound(String filter) throws Exception {
        restDuongSuTrungCmndBakMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDuongSuTrungCmndBakMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
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
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
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
