package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhSachDuongSuAsserts.*;
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
import vn.vnpt.domain.DanhSachDuongSu;
import vn.vnpt.domain.DuongSu;
import vn.vnpt.repository.DanhSachDuongSuRepository;
import vn.vnpt.service.dto.DanhSachDuongSuDTO;
import vn.vnpt.service.mapper.DanhSachDuongSuMapper;

/**
 * Integration tests for the {@link DanhSachDuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhSachDuongSuResourceIT {

    private static final String DEFAULT_TEN_DUONG_SU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DUONG_SU = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANG_THAI = 0;
    private static final Integer UPDATED_TRANG_THAI = 1;
    private static final Integer SMALLER_TRANG_THAI = 0 - 1;

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

    private static final Long DEFAULT_ID_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_ID_LOAI_NGAN_CHAN = 2L;
    private static final Long SMALLER_ID_LOAI_NGAN_CHAN = 1L - 1L;

    private static final String ENTITY_API_URL = "/api/danh-sach-duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhSachDuongSuRepository danhSachDuongSuRepository;

    @Autowired
    private DanhSachDuongSuMapper danhSachDuongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhSachDuongSuMockMvc;

    private DanhSachDuongSu danhSachDuongSu;

    private DanhSachDuongSu insertedDanhSachDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhSachDuongSu createEntity() {
        return new DanhSachDuongSu()
            .tenDuongSu(DEFAULT_TEN_DUONG_SU)
            .diaChi(DEFAULT_DIA_CHI)
            .trangThai(DEFAULT_TRANG_THAI)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idDsGoc(DEFAULT_ID_DS_GOC)
            .idMaster(DEFAULT_ID_MASTER)
            .idDonVi(DEFAULT_ID_DON_VI)
            .strSearch(DEFAULT_STR_SEARCH)
            .soGiayTo(DEFAULT_SO_GIAY_TO)
            .idLoaiNganChan(DEFAULT_ID_LOAI_NGAN_CHAN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhSachDuongSu createUpdatedEntity() {
        return new DanhSachDuongSu()
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);
    }

    @BeforeEach
    public void initTest() {
        danhSachDuongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhSachDuongSu != null) {
            danhSachDuongSuRepository.delete(insertedDanhSachDuongSu);
            insertedDanhSachDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);
        var returnedDanhSachDuongSuDTO = om.readValue(
            restDanhSachDuongSuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachDuongSuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhSachDuongSuDTO.class
        );

        // Validate the DanhSachDuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhSachDuongSu = danhSachDuongSuMapper.toEntity(returnedDanhSachDuongSuDTO);
        assertDanhSachDuongSuUpdatableFieldsEquals(returnedDanhSachDuongSu, getPersistedDanhSachDuongSu(returnedDanhSachDuongSu));

        insertedDanhSachDuongSu = returnedDanhSachDuongSu;
    }

    @Test
    @Transactional
    void createDanhSachDuongSuWithExistingId() throws Exception {
        // Create the DanhSachDuongSu with an existing ID
        danhSachDuongSu.setId(1L);
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhSachDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachDuongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSus() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList
        restDanhSachDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhSachDuongSu.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenDuongSu").value(hasItem(DEFAULT_TEN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDsGoc").value(hasItem(DEFAULT_ID_DS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].soGiayTo").value(hasItem(DEFAULT_SO_GIAY_TO)))
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())));
    }

    @Test
    @Transactional
    void getDanhSachDuongSu() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get the danhSachDuongSu
        restDanhSachDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, danhSachDuongSu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhSachDuongSu.getId().intValue()))
            .andExpect(jsonPath("$.tenDuongSu").value(DEFAULT_TEN_DUONG_SU))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idDsGoc").value(DEFAULT_ID_DS_GOC.intValue()))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.soGiayTo").value(DEFAULT_SO_GIAY_TO))
            .andExpect(jsonPath("$.idLoaiNganChan").value(DEFAULT_ID_LOAI_NGAN_CHAN.intValue()));
    }

    @Test
    @Transactional
    void getDanhSachDuongSusByIdFiltering() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        Long id = danhSachDuongSu.getId();

        defaultDanhSachDuongSuFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultDanhSachDuongSuFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultDanhSachDuongSuFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTenDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where tenDuongSu equals to
        defaultDanhSachDuongSuFiltering("tenDuongSu.equals=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.equals=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTenDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where tenDuongSu in
        defaultDanhSachDuongSuFiltering(
            "tenDuongSu.in=" + DEFAULT_TEN_DUONG_SU + "," + UPDATED_TEN_DUONG_SU,
            "tenDuongSu.in=" + UPDATED_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTenDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where tenDuongSu is not null
        defaultDanhSachDuongSuFiltering("tenDuongSu.specified=true", "tenDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTenDuongSuContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where tenDuongSu contains
        defaultDanhSachDuongSuFiltering("tenDuongSu.contains=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.contains=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTenDuongSuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where tenDuongSu does not contain
        defaultDanhSachDuongSuFiltering(
            "tenDuongSu.doesNotContain=" + UPDATED_TEN_DUONG_SU,
            "tenDuongSu.doesNotContain=" + DEFAULT_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByDiaChiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where diaChi equals to
        defaultDanhSachDuongSuFiltering("diaChi.equals=" + DEFAULT_DIA_CHI, "diaChi.equals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByDiaChiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where diaChi in
        defaultDanhSachDuongSuFiltering("diaChi.in=" + DEFAULT_DIA_CHI + "," + UPDATED_DIA_CHI, "diaChi.in=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByDiaChiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where diaChi is not null
        defaultDanhSachDuongSuFiltering("diaChi.specified=true", "diaChi.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByDiaChiContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where diaChi contains
        defaultDanhSachDuongSuFiltering("diaChi.contains=" + DEFAULT_DIA_CHI, "diaChi.contains=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByDiaChiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where diaChi does not contain
        defaultDanhSachDuongSuFiltering("diaChi.doesNotContain=" + UPDATED_DIA_CHI, "diaChi.doesNotContain=" + DEFAULT_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where trangThai equals to
        defaultDanhSachDuongSuFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where trangThai in
        defaultDanhSachDuongSuFiltering(
            "trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI,
            "trangThai.in=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where trangThai is not null
        defaultDanhSachDuongSuFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where trangThai is greater than or equal to
        defaultDanhSachDuongSuFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + (DEFAULT_TRANG_THAI + 1)
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where trangThai is less than or equal to
        defaultDanhSachDuongSuFiltering(
            "trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where trangThai is less than
        defaultDanhSachDuongSuFiltering("trangThai.lessThan=" + (DEFAULT_TRANG_THAI + 1), "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where trangThai is greater than
        defaultDanhSachDuongSuFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where ngayThaoTac equals to
        defaultDanhSachDuongSuFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where ngayThaoTac in
        defaultDanhSachDuongSuFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where ngayThaoTac is not null
        defaultDanhSachDuongSuFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where ngayThaoTac is greater than or equal to
        defaultDanhSachDuongSuFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where ngayThaoTac is less than or equal to
        defaultDanhSachDuongSuFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where ngayThaoTac is less than
        defaultDanhSachDuongSuFiltering("ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC, "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where ngayThaoTac is greater than
        defaultDanhSachDuongSuFiltering(
            "ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where nguoiThaoTac equals to
        defaultDanhSachDuongSuFiltering("nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC, "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where nguoiThaoTac in
        defaultDanhSachDuongSuFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where nguoiThaoTac is not null
        defaultDanhSachDuongSuFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where nguoiThaoTac is greater than or equal to
        defaultDanhSachDuongSuFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where nguoiThaoTac is less than or equal to
        defaultDanhSachDuongSuFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where nguoiThaoTac is less than
        defaultDanhSachDuongSuFiltering(
            "nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where nguoiThaoTac is greater than
        defaultDanhSachDuongSuFiltering(
            "nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDsGocIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDsGoc equals to
        defaultDanhSachDuongSuFiltering("idDsGoc.equals=" + DEFAULT_ID_DS_GOC, "idDsGoc.equals=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDsGocIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDsGoc in
        defaultDanhSachDuongSuFiltering("idDsGoc.in=" + DEFAULT_ID_DS_GOC + "," + UPDATED_ID_DS_GOC, "idDsGoc.in=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDsGocIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDsGoc is not null
        defaultDanhSachDuongSuFiltering("idDsGoc.specified=true", "idDsGoc.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDsGocIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDsGoc is greater than or equal to
        defaultDanhSachDuongSuFiltering(
            "idDsGoc.greaterThanOrEqual=" + DEFAULT_ID_DS_GOC,
            "idDsGoc.greaterThanOrEqual=" + UPDATED_ID_DS_GOC
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDsGocIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDsGoc is less than or equal to
        defaultDanhSachDuongSuFiltering("idDsGoc.lessThanOrEqual=" + DEFAULT_ID_DS_GOC, "idDsGoc.lessThanOrEqual=" + SMALLER_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDsGocIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDsGoc is less than
        defaultDanhSachDuongSuFiltering("idDsGoc.lessThan=" + UPDATED_ID_DS_GOC, "idDsGoc.lessThan=" + DEFAULT_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDsGocIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDsGoc is greater than
        defaultDanhSachDuongSuFiltering("idDsGoc.greaterThan=" + SMALLER_ID_DS_GOC, "idDsGoc.greaterThan=" + DEFAULT_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdMasterIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idMaster equals to
        defaultDanhSachDuongSuFiltering("idMaster.equals=" + DEFAULT_ID_MASTER, "idMaster.equals=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdMasterIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idMaster in
        defaultDanhSachDuongSuFiltering("idMaster.in=" + DEFAULT_ID_MASTER + "," + UPDATED_ID_MASTER, "idMaster.in=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdMasterIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idMaster is not null
        defaultDanhSachDuongSuFiltering("idMaster.specified=true", "idMaster.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdMasterContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idMaster contains
        defaultDanhSachDuongSuFiltering("idMaster.contains=" + DEFAULT_ID_MASTER, "idMaster.contains=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdMasterNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idMaster does not contain
        defaultDanhSachDuongSuFiltering("idMaster.doesNotContain=" + UPDATED_ID_MASTER, "idMaster.doesNotContain=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDonVi equals to
        defaultDanhSachDuongSuFiltering("idDonVi.equals=" + DEFAULT_ID_DON_VI, "idDonVi.equals=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDonVi in
        defaultDanhSachDuongSuFiltering("idDonVi.in=" + DEFAULT_ID_DON_VI + "," + UPDATED_ID_DON_VI, "idDonVi.in=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDonVi is not null
        defaultDanhSachDuongSuFiltering("idDonVi.specified=true", "idDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDonViIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDonVi is greater than or equal to
        defaultDanhSachDuongSuFiltering(
            "idDonVi.greaterThanOrEqual=" + DEFAULT_ID_DON_VI,
            "idDonVi.greaterThanOrEqual=" + UPDATED_ID_DON_VI
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDonViIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDonVi is less than or equal to
        defaultDanhSachDuongSuFiltering("idDonVi.lessThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.lessThanOrEqual=" + SMALLER_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDonViIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDonVi is less than
        defaultDanhSachDuongSuFiltering("idDonVi.lessThan=" + UPDATED_ID_DON_VI, "idDonVi.lessThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdDonViIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idDonVi is greater than
        defaultDanhSachDuongSuFiltering("idDonVi.greaterThan=" + SMALLER_ID_DON_VI, "idDonVi.greaterThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByStrSearchIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where strSearch equals to
        defaultDanhSachDuongSuFiltering("strSearch.equals=" + DEFAULT_STR_SEARCH, "strSearch.equals=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByStrSearchIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where strSearch in
        defaultDanhSachDuongSuFiltering(
            "strSearch.in=" + DEFAULT_STR_SEARCH + "," + UPDATED_STR_SEARCH,
            "strSearch.in=" + UPDATED_STR_SEARCH
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByStrSearchIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where strSearch is not null
        defaultDanhSachDuongSuFiltering("strSearch.specified=true", "strSearch.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByStrSearchContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where strSearch contains
        defaultDanhSachDuongSuFiltering("strSearch.contains=" + DEFAULT_STR_SEARCH, "strSearch.contains=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByStrSearchNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where strSearch does not contain
        defaultDanhSachDuongSuFiltering("strSearch.doesNotContain=" + UPDATED_STR_SEARCH, "strSearch.doesNotContain=" + DEFAULT_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusBySoGiayToIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where soGiayTo equals to
        defaultDanhSachDuongSuFiltering("soGiayTo.equals=" + DEFAULT_SO_GIAY_TO, "soGiayTo.equals=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusBySoGiayToIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where soGiayTo in
        defaultDanhSachDuongSuFiltering(
            "soGiayTo.in=" + DEFAULT_SO_GIAY_TO + "," + UPDATED_SO_GIAY_TO,
            "soGiayTo.in=" + UPDATED_SO_GIAY_TO
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusBySoGiayToIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where soGiayTo is not null
        defaultDanhSachDuongSuFiltering("soGiayTo.specified=true", "soGiayTo.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusBySoGiayToContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where soGiayTo contains
        defaultDanhSachDuongSuFiltering("soGiayTo.contains=" + DEFAULT_SO_GIAY_TO, "soGiayTo.contains=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusBySoGiayToNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where soGiayTo does not contain
        defaultDanhSachDuongSuFiltering("soGiayTo.doesNotContain=" + UPDATED_SO_GIAY_TO, "soGiayTo.doesNotContain=" + DEFAULT_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idLoaiNganChan equals to
        defaultDanhSachDuongSuFiltering(
            "idLoaiNganChan.equals=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.equals=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idLoaiNganChan in
        defaultDanhSachDuongSuFiltering(
            "idLoaiNganChan.in=" + DEFAULT_ID_LOAI_NGAN_CHAN + "," + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.in=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idLoaiNganChan is not null
        defaultDanhSachDuongSuFiltering("idLoaiNganChan.specified=true", "idLoaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idLoaiNganChan is greater than or equal to
        defaultDanhSachDuongSuFiltering(
            "idLoaiNganChan.greaterThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThanOrEqual=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idLoaiNganChan is less than or equal to
        defaultDanhSachDuongSuFiltering(
            "idLoaiNganChan.lessThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThanOrEqual=" + SMALLER_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idLoaiNganChan is less than
        defaultDanhSachDuongSuFiltering(
            "idLoaiNganChan.lessThan=" + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByIdLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        // Get all the danhSachDuongSuList where idLoaiNganChan is greater than
        defaultDanhSachDuongSuFiltering(
            "idLoaiNganChan.greaterThan=" + SMALLER_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDanhSachDuongSusByDuongSuIsEqualToSomething() throws Exception {
        DuongSu duongSu;
        if (TestUtil.findAll(em, DuongSu.class).isEmpty()) {
            danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);
            duongSu = DuongSuResourceIT.createEntity();
        } else {
            duongSu = TestUtil.findAll(em, DuongSu.class).get(0);
        }
        em.persist(duongSu);
        em.flush();
        danhSachDuongSu.setDuongSu(duongSu);
        danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);
        Long duongSuId = duongSu.getIdDuongSu();
        // Get all the danhSachDuongSuList where duongSu equals to duongSuId
        defaultDanhSachDuongSuShouldBeFound("duongSuId.equals=" + duongSuId);

        // Get all the danhSachDuongSuList where duongSu equals to (duongSuId + 1)
        defaultDanhSachDuongSuShouldNotBeFound("duongSuId.equals=" + (duongSuId + 1));
    }

    private void defaultDanhSachDuongSuFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDanhSachDuongSuShouldBeFound(shouldBeFound);
        defaultDanhSachDuongSuShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhSachDuongSuShouldBeFound(String filter) throws Exception {
        restDanhSachDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhSachDuongSu.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenDuongSu").value(hasItem(DEFAULT_TEN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDsGoc").value(hasItem(DEFAULT_ID_DS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].soGiayTo").value(hasItem(DEFAULT_SO_GIAY_TO)))
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())));

        // Check, that the count call also returns 1
        restDanhSachDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhSachDuongSuShouldNotBeFound(String filter) throws Exception {
        restDanhSachDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhSachDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDanhSachDuongSu() throws Exception {
        // Get the danhSachDuongSu
        restDanhSachDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhSachDuongSu() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachDuongSu
        DanhSachDuongSu updatedDanhSachDuongSu = danhSachDuongSuRepository.findById(danhSachDuongSu.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhSachDuongSu are not directly saved in db
        em.detach(updatedDanhSachDuongSu);
        updatedDanhSachDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(updatedDanhSachDuongSu);

        restDanhSachDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhSachDuongSuDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachDuongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhSachDuongSuToMatchAllProperties(updatedDanhSachDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhSachDuongSuDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhSachDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhSachDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhSachDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachDuongSu using partial update
        DanhSachDuongSu partialUpdatedDanhSachDuongSu = new DanhSachDuongSu();
        partialUpdatedDanhSachDuongSu.setId(danhSachDuongSu.getId());

        partialUpdatedDanhSachDuongSu
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI);

        restDanhSachDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhSachDuongSu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhSachDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhSachDuongSuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhSachDuongSu, danhSachDuongSu),
            getPersistedDanhSachDuongSu(danhSachDuongSu)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhSachDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhSachDuongSu using partial update
        DanhSachDuongSu partialUpdatedDanhSachDuongSu = new DanhSachDuongSu();
        partialUpdatedDanhSachDuongSu.setId(danhSachDuongSu.getId());

        partialUpdatedDanhSachDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);

        restDanhSachDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhSachDuongSu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhSachDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DanhSachDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhSachDuongSuUpdatableFieldsEquals(
            partialUpdatedDanhSachDuongSu,
            getPersistedDanhSachDuongSu(partialUpdatedDanhSachDuongSu)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhSachDuongSuDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhSachDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhSachDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhSachDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhSachDuongSu.setId(longCount.incrementAndGet());

        // Create the DanhSachDuongSu
        DanhSachDuongSuDTO danhSachDuongSuDTO = danhSachDuongSuMapper.toDto(danhSachDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhSachDuongSuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhSachDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhSachDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhSachDuongSu() throws Exception {
        // Initialize the database
        insertedDanhSachDuongSu = danhSachDuongSuRepository.saveAndFlush(danhSachDuongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhSachDuongSu
        restDanhSachDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhSachDuongSu.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhSachDuongSuRepository.count();
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

    protected DanhSachDuongSu getPersistedDanhSachDuongSu(DanhSachDuongSu danhSachDuongSu) {
        return danhSachDuongSuRepository.findById(danhSachDuongSu.getId()).orElseThrow();
    }

    protected void assertPersistedDanhSachDuongSuToMatchAllProperties(DanhSachDuongSu expectedDanhSachDuongSu) {
        assertDanhSachDuongSuAllPropertiesEquals(expectedDanhSachDuongSu, getPersistedDanhSachDuongSu(expectedDanhSachDuongSu));
    }

    protected void assertPersistedDanhSachDuongSuToMatchUpdatableProperties(DanhSachDuongSu expectedDanhSachDuongSu) {
        assertDanhSachDuongSuAllUpdatablePropertiesEquals(expectedDanhSachDuongSu, getPersistedDanhSachDuongSu(expectedDanhSachDuongSu));
    }
}
