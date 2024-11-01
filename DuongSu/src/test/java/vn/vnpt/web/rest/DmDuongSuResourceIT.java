package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DmDuongSuAsserts.*;
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
import vn.vnpt.domain.DmDuongSu;
import vn.vnpt.repository.DmDuongSuRepository;
import vn.vnpt.service.dto.DmDuongSuDTO;
import vn.vnpt.service.mapper.DmDuongSuMapper;

/**
 * Integration tests for the {@link DmDuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DmDuongSuResourceIT {

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

    private static final Long DEFAULT_ID_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_ID_LOAI_NGAN_CHAN = 2L;
    private static final Long SMALLER_ID_LOAI_NGAN_CHAN = 1L - 1L;

    private static final String ENTITY_API_URL = "/api/dm-duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idDuongSu}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DmDuongSuRepository dmDuongSuRepository;

    @Autowired
    private DmDuongSuMapper dmDuongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDmDuongSuMockMvc;

    private DmDuongSu dmDuongSu;

    private DmDuongSu insertedDmDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmDuongSu createEntity() {
        return new DmDuongSu()
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
            .idLoaiNganChan(DEFAULT_ID_LOAI_NGAN_CHAN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmDuongSu createUpdatedEntity() {
        return new DmDuongSu()
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
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);
    }

    @BeforeEach
    public void initTest() {
        dmDuongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDmDuongSu != null) {
            dmDuongSuRepository.delete(insertedDmDuongSu);
            insertedDmDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createDmDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);
        var returnedDmDuongSuDTO = om.readValue(
            restDmDuongSuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmDuongSuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DmDuongSuDTO.class
        );

        // Validate the DmDuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDmDuongSu = dmDuongSuMapper.toEntity(returnedDmDuongSuDTO);
        assertDmDuongSuUpdatableFieldsEquals(returnedDmDuongSu, getPersistedDmDuongSu(returnedDmDuongSu));

        insertedDmDuongSu = returnedDmDuongSu;
    }

    @Test
    @Transactional
    void createDmDuongSuWithExistingId() throws Exception {
        // Create the DmDuongSu with an existing ID
        dmDuongSu.setIdDuongSu(1L);
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmDuongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDmDuongSus() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList
        restDmDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDuongSu,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(dmDuongSu.getIdDuongSu().intValue())))
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
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())));
    }

    @Test
    @Transactional
    void getDmDuongSu() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get the dmDuongSu
        restDmDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, dmDuongSu.getIdDuongSu()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idDuongSu").value(dmDuongSu.getIdDuongSu().intValue()))
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
            .andExpect(jsonPath("$.idLoaiNganChan").value(DEFAULT_ID_LOAI_NGAN_CHAN.intValue()));
    }

    @Test
    @Transactional
    void getDmDuongSusByIdFiltering() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        Long id = dmDuongSu.getIdDuongSu();

        defaultDmDuongSuFiltering("idDuongSu.equals=" + id, "idDuongSu.notEquals=" + id);

        defaultDmDuongSuFiltering("idDuongSu.greaterThanOrEqual=" + id, "idDuongSu.greaterThan=" + id);

        defaultDmDuongSuFiltering("idDuongSu.lessThanOrEqual=" + id, "idDuongSu.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTenDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where tenDuongSu equals to
        defaultDmDuongSuFiltering("tenDuongSu.equals=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.equals=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTenDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where tenDuongSu in
        defaultDmDuongSuFiltering(
            "tenDuongSu.in=" + DEFAULT_TEN_DUONG_SU + "," + UPDATED_TEN_DUONG_SU,
            "tenDuongSu.in=" + UPDATED_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTenDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where tenDuongSu is not null
        defaultDmDuongSuFiltering("tenDuongSu.specified=true", "tenDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTenDuongSuContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where tenDuongSu contains
        defaultDmDuongSuFiltering("tenDuongSu.contains=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.contains=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTenDuongSuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where tenDuongSu does not contain
        defaultDmDuongSuFiltering("tenDuongSu.doesNotContain=" + UPDATED_TEN_DUONG_SU, "tenDuongSu.doesNotContain=" + DEFAULT_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByDiaChiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where diaChi equals to
        defaultDmDuongSuFiltering("diaChi.equals=" + DEFAULT_DIA_CHI, "diaChi.equals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByDiaChiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where diaChi in
        defaultDmDuongSuFiltering("diaChi.in=" + DEFAULT_DIA_CHI + "," + UPDATED_DIA_CHI, "diaChi.in=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByDiaChiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where diaChi is not null
        defaultDmDuongSuFiltering("diaChi.specified=true", "diaChi.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByDiaChiContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where diaChi contains
        defaultDmDuongSuFiltering("diaChi.contains=" + DEFAULT_DIA_CHI, "diaChi.contains=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByDiaChiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where diaChi does not contain
        defaultDmDuongSuFiltering("diaChi.doesNotContain=" + UPDATED_DIA_CHI, "diaChi.doesNotContain=" + DEFAULT_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where trangThai equals to
        defaultDmDuongSuFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where trangThai in
        defaultDmDuongSuFiltering("trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI, "trangThai.in=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where trangThai is not null
        defaultDmDuongSuFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where trangThai is greater than or equal to
        defaultDmDuongSuFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + (DEFAULT_TRANG_THAI + 1)
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where trangThai is less than or equal to
        defaultDmDuongSuFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where trangThai is less than
        defaultDmDuongSuFiltering("trangThai.lessThan=" + (DEFAULT_TRANG_THAI + 1), "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where trangThai is greater than
        defaultDmDuongSuFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByThongTinDsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where thongTinDs equals to
        defaultDmDuongSuFiltering("thongTinDs.equals=" + DEFAULT_THONG_TIN_DS, "thongTinDs.equals=" + UPDATED_THONG_TIN_DS);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByThongTinDsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where thongTinDs in
        defaultDmDuongSuFiltering(
            "thongTinDs.in=" + DEFAULT_THONG_TIN_DS + "," + UPDATED_THONG_TIN_DS,
            "thongTinDs.in=" + UPDATED_THONG_TIN_DS
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByThongTinDsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where thongTinDs is not null
        defaultDmDuongSuFiltering("thongTinDs.specified=true", "thongTinDs.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByThongTinDsContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where thongTinDs contains
        defaultDmDuongSuFiltering("thongTinDs.contains=" + DEFAULT_THONG_TIN_DS, "thongTinDs.contains=" + UPDATED_THONG_TIN_DS);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByThongTinDsNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where thongTinDs does not contain
        defaultDmDuongSuFiltering("thongTinDs.doesNotContain=" + UPDATED_THONG_TIN_DS, "thongTinDs.doesNotContain=" + DEFAULT_THONG_TIN_DS);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where ngayThaoTac equals to
        defaultDmDuongSuFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where ngayThaoTac in
        defaultDmDuongSuFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where ngayThaoTac is not null
        defaultDmDuongSuFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where ngayThaoTac is greater than or equal to
        defaultDmDuongSuFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where ngayThaoTac is less than or equal to
        defaultDmDuongSuFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where ngayThaoTac is less than
        defaultDmDuongSuFiltering("ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC, "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where ngayThaoTac is greater than
        defaultDmDuongSuFiltering("ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC, "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where nguoiThaoTac equals to
        defaultDmDuongSuFiltering("nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC, "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where nguoiThaoTac in
        defaultDmDuongSuFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where nguoiThaoTac is not null
        defaultDmDuongSuFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where nguoiThaoTac is greater than or equal to
        defaultDmDuongSuFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where nguoiThaoTac is less than or equal to
        defaultDmDuongSuFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where nguoiThaoTac is less than
        defaultDmDuongSuFiltering("nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC, "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where nguoiThaoTac is greater than
        defaultDmDuongSuFiltering(
            "nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDsGocIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDsGoc equals to
        defaultDmDuongSuFiltering("idDsGoc.equals=" + DEFAULT_ID_DS_GOC, "idDsGoc.equals=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDsGocIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDsGoc in
        defaultDmDuongSuFiltering("idDsGoc.in=" + DEFAULT_ID_DS_GOC + "," + UPDATED_ID_DS_GOC, "idDsGoc.in=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDsGocIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDsGoc is not null
        defaultDmDuongSuFiltering("idDsGoc.specified=true", "idDsGoc.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDsGocIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDsGoc is greater than or equal to
        defaultDmDuongSuFiltering("idDsGoc.greaterThanOrEqual=" + DEFAULT_ID_DS_GOC, "idDsGoc.greaterThanOrEqual=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDsGocIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDsGoc is less than or equal to
        defaultDmDuongSuFiltering("idDsGoc.lessThanOrEqual=" + DEFAULT_ID_DS_GOC, "idDsGoc.lessThanOrEqual=" + SMALLER_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDsGocIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDsGoc is less than
        defaultDmDuongSuFiltering("idDsGoc.lessThan=" + UPDATED_ID_DS_GOC, "idDsGoc.lessThan=" + DEFAULT_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDsGocIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDsGoc is greater than
        defaultDmDuongSuFiltering("idDsGoc.greaterThan=" + SMALLER_ID_DS_GOC, "idDsGoc.greaterThan=" + DEFAULT_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdMasterIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idMaster equals to
        defaultDmDuongSuFiltering("idMaster.equals=" + DEFAULT_ID_MASTER, "idMaster.equals=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdMasterIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idMaster in
        defaultDmDuongSuFiltering("idMaster.in=" + DEFAULT_ID_MASTER + "," + UPDATED_ID_MASTER, "idMaster.in=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdMasterIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idMaster is not null
        defaultDmDuongSuFiltering("idMaster.specified=true", "idMaster.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdMasterContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idMaster contains
        defaultDmDuongSuFiltering("idMaster.contains=" + DEFAULT_ID_MASTER, "idMaster.contains=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdMasterNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idMaster does not contain
        defaultDmDuongSuFiltering("idMaster.doesNotContain=" + UPDATED_ID_MASTER, "idMaster.doesNotContain=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDonVi equals to
        defaultDmDuongSuFiltering("idDonVi.equals=" + DEFAULT_ID_DON_VI, "idDonVi.equals=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDonVi in
        defaultDmDuongSuFiltering("idDonVi.in=" + DEFAULT_ID_DON_VI + "," + UPDATED_ID_DON_VI, "idDonVi.in=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDonVi is not null
        defaultDmDuongSuFiltering("idDonVi.specified=true", "idDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDonViIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDonVi is greater than or equal to
        defaultDmDuongSuFiltering("idDonVi.greaterThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.greaterThanOrEqual=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDonViIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDonVi is less than or equal to
        defaultDmDuongSuFiltering("idDonVi.lessThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.lessThanOrEqual=" + SMALLER_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDonViIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDonVi is less than
        defaultDmDuongSuFiltering("idDonVi.lessThan=" + UPDATED_ID_DON_VI, "idDonVi.lessThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdDonViIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idDonVi is greater than
        defaultDmDuongSuFiltering("idDonVi.greaterThan=" + SMALLER_ID_DON_VI, "idDonVi.greaterThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByStrSearchIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where strSearch equals to
        defaultDmDuongSuFiltering("strSearch.equals=" + DEFAULT_STR_SEARCH, "strSearch.equals=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByStrSearchIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where strSearch in
        defaultDmDuongSuFiltering("strSearch.in=" + DEFAULT_STR_SEARCH + "," + UPDATED_STR_SEARCH, "strSearch.in=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByStrSearchIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where strSearch is not null
        defaultDmDuongSuFiltering("strSearch.specified=true", "strSearch.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByStrSearchContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where strSearch contains
        defaultDmDuongSuFiltering("strSearch.contains=" + DEFAULT_STR_SEARCH, "strSearch.contains=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByStrSearchNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where strSearch does not contain
        defaultDmDuongSuFiltering("strSearch.doesNotContain=" + UPDATED_STR_SEARCH, "strSearch.doesNotContain=" + DEFAULT_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDmDuongSusBySoGiayToIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where soGiayTo equals to
        defaultDmDuongSuFiltering("soGiayTo.equals=" + DEFAULT_SO_GIAY_TO, "soGiayTo.equals=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDmDuongSusBySoGiayToIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where soGiayTo in
        defaultDmDuongSuFiltering("soGiayTo.in=" + DEFAULT_SO_GIAY_TO + "," + UPDATED_SO_GIAY_TO, "soGiayTo.in=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDmDuongSusBySoGiayToIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where soGiayTo is not null
        defaultDmDuongSuFiltering("soGiayTo.specified=true", "soGiayTo.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusBySoGiayToContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where soGiayTo contains
        defaultDmDuongSuFiltering("soGiayTo.contains=" + DEFAULT_SO_GIAY_TO, "soGiayTo.contains=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDmDuongSusBySoGiayToNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where soGiayTo does not contain
        defaultDmDuongSuFiltering("soGiayTo.doesNotContain=" + UPDATED_SO_GIAY_TO, "soGiayTo.doesNotContain=" + DEFAULT_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idLoaiNganChan equals to
        defaultDmDuongSuFiltering(
            "idLoaiNganChan.equals=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.equals=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idLoaiNganChan in
        defaultDmDuongSuFiltering(
            "idLoaiNganChan.in=" + DEFAULT_ID_LOAI_NGAN_CHAN + "," + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.in=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idLoaiNganChan is not null
        defaultDmDuongSuFiltering("idLoaiNganChan.specified=true", "idLoaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idLoaiNganChan is greater than or equal to
        defaultDmDuongSuFiltering(
            "idLoaiNganChan.greaterThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThanOrEqual=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idLoaiNganChan is less than or equal to
        defaultDmDuongSuFiltering(
            "idLoaiNganChan.lessThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThanOrEqual=" + SMALLER_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idLoaiNganChan is less than
        defaultDmDuongSuFiltering(
            "idLoaiNganChan.lessThan=" + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDmDuongSusByIdLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        // Get all the dmDuongSuList where idLoaiNganChan is greater than
        defaultDmDuongSuFiltering(
            "idLoaiNganChan.greaterThan=" + SMALLER_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    private void defaultDmDuongSuFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDmDuongSuShouldBeFound(shouldBeFound);
        defaultDmDuongSuShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDmDuongSuShouldBeFound(String filter) throws Exception {
        restDmDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDuongSu,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(dmDuongSu.getIdDuongSu().intValue())))
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
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())));

        // Check, that the count call also returns 1
        restDmDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idDuongSu,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDmDuongSuShouldNotBeFound(String filter) throws Exception {
        restDmDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDuongSu,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDmDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idDuongSu,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDmDuongSu() throws Exception {
        // Get the dmDuongSu
        restDmDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDmDuongSu() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmDuongSu
        DmDuongSu updatedDmDuongSu = dmDuongSuRepository.findById(dmDuongSu.getIdDuongSu()).orElseThrow();
        // Disconnect from session so that the updates on updatedDmDuongSu are not directly saved in db
        em.detach(updatedDmDuongSu);
        updatedDmDuongSu
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
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(updatedDmDuongSu);

        restDmDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmDuongSuDTO.getIdDuongSu())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmDuongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDmDuongSuToMatchAllProperties(updatedDmDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmDuongSuDTO.getIdDuongSu())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDmDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmDuongSu using partial update
        DmDuongSu partialUpdatedDmDuongSu = new DmDuongSu();
        partialUpdatedDmDuongSu.setIdDuongSu(dmDuongSu.getIdDuongSu());

        partialUpdatedDmDuongSu
            .diaChi(UPDATED_DIA_CHI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);

        restDmDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmDuongSu.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DmDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmDuongSuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDmDuongSu, dmDuongSu),
            getPersistedDmDuongSu(dmDuongSu)
        );
    }

    @Test
    @Transactional
    void fullUpdateDmDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmDuongSu using partial update
        DmDuongSu partialUpdatedDmDuongSu = new DmDuongSu();
        partialUpdatedDmDuongSu.setIdDuongSu(dmDuongSu.getIdDuongSu());

        partialUpdatedDmDuongSu
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
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);

        restDmDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmDuongSu.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DmDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmDuongSuUpdatableFieldsEquals(partialUpdatedDmDuongSu, getPersistedDmDuongSu(partialUpdatedDmDuongSu));
    }

    @Test
    @Transactional
    void patchNonExistingDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dmDuongSuDTO.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDmDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmDuongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DmDuongSu
        DmDuongSuDTO dmDuongSuDTO = dmDuongSuMapper.toDto(dmDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmDuongSuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dmDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDmDuongSu() throws Exception {
        // Initialize the database
        insertedDmDuongSu = dmDuongSuRepository.saveAndFlush(dmDuongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dmDuongSu
        restDmDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, dmDuongSu.getIdDuongSu()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dmDuongSuRepository.count();
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

    protected DmDuongSu getPersistedDmDuongSu(DmDuongSu dmDuongSu) {
        return dmDuongSuRepository.findById(dmDuongSu.getIdDuongSu()).orElseThrow();
    }

    protected void assertPersistedDmDuongSuToMatchAllProperties(DmDuongSu expectedDmDuongSu) {
        assertDmDuongSuAllPropertiesEquals(expectedDmDuongSu, getPersistedDmDuongSu(expectedDmDuongSu));
    }

    protected void assertPersistedDmDuongSuToMatchUpdatableProperties(DmDuongSu expectedDmDuongSu) {
        assertDmDuongSuAllUpdatablePropertiesEquals(expectedDmDuongSu, getPersistedDmDuongSu(expectedDmDuongSu));
    }
}
