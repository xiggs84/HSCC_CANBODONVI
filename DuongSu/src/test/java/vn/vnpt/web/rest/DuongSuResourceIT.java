package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DuongSuAsserts.*;
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
import vn.vnpt.domain.LoaiDuongSu;
import vn.vnpt.domain.LoaiGiayTo;
import vn.vnpt.repository.DuongSuRepository;
import vn.vnpt.service.dto.DuongSuDTO;
import vn.vnpt.service.mapper.DuongSuMapper;

/**
 * Integration tests for the {@link DuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DuongSuResourceIT {

    private static final String DEFAULT_TEN_DUONG_SU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DUONG_SU = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

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

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_ID_LOAI_NGAN_CHAN = 2L;
    private static final Long SMALLER_ID_LOAI_NGAN_CHAN = 1L - 1L;

    private static final Integer DEFAULT_SYNC_STATUS = 0;
    private static final Integer UPDATED_SYNC_STATUS = 1;
    private static final Integer SMALLER_SYNC_STATUS = 0 - 1;

    private static final String ENTITY_API_URL = "/api/duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idDuongSu}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DuongSuRepository duongSuRepository;

    @Autowired
    private DuongSuMapper duongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDuongSuMockMvc;

    private DuongSu duongSu;

    private DuongSu insertedDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuongSu createEntity() {
        return new DuongSu()
            .tenDuongSu(DEFAULT_TEN_DUONG_SU)
            .diaChi(DEFAULT_DIA_CHI)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI)
            .email(DEFAULT_EMAIL)
            .fax(DEFAULT_FAX)
            .website(DEFAULT_WEBSITE)
            .trangThai(DEFAULT_TRANG_THAI)
            .thongTinDs(DEFAULT_THONG_TIN_DS)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idDsGoc(DEFAULT_ID_DS_GOC)
            .idMaster(DEFAULT_ID_MASTER)
            .idDonVi(DEFAULT_ID_DON_VI)
            .strSearch(DEFAULT_STR_SEARCH)
            .soGiayTo(DEFAULT_SO_GIAY_TO)
            .ghiChu(DEFAULT_GHI_CHU)
            .idLoaiNganChan(DEFAULT_ID_LOAI_NGAN_CHAN)
            .syncStatus(DEFAULT_SYNC_STATUS);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DuongSu createUpdatedEntity() {
        return new DuongSu()
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .website(UPDATED_WEBSITE)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .ghiChu(UPDATED_GHI_CHU)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .syncStatus(UPDATED_SYNC_STATUS);
    }

    @BeforeEach
    public void initTest() {
        duongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDuongSu != null) {
            duongSuRepository.delete(insertedDuongSu);
            insertedDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);
        var returnedDuongSuDTO = om.readValue(
            restDuongSuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DuongSuDTO.class
        );

        // Validate the DuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDuongSu = duongSuMapper.toEntity(returnedDuongSuDTO);
        assertDuongSuUpdatableFieldsEquals(returnedDuongSu, getPersistedDuongSu(returnedDuongSu));

        insertedDuongSu = returnedDuongSu;
    }

    @Test
    @Transactional
    void createDuongSuWithExistingId() throws Exception {
        // Create the DuongSu with an existing ID
        duongSu.setIdDuongSu(1L);
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDuongSus() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList
        restDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDuongSu,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(duongSu.getIdDuongSu().intValue())))
            .andExpect(jsonPath("$.[*].tenDuongSu").value(hasItem(DEFAULT_TEN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].thongTinDs").value(hasItem(DEFAULT_THONG_TIN_DS.toString())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDsGoc").value(hasItem(DEFAULT_ID_DS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].soGiayTo").value(hasItem(DEFAULT_SO_GIAY_TO)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())))
            .andExpect(jsonPath("$.[*].syncStatus").value(hasItem(DEFAULT_SYNC_STATUS)));
    }

    @Test
    @Transactional
    void getDuongSu() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get the duongSu
        restDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, duongSu.getIdDuongSu()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idDuongSu").value(duongSu.getIdDuongSu().intValue()))
            .andExpect(jsonPath("$.tenDuongSu").value(DEFAULT_TEN_DUONG_SU))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI))
            .andExpect(jsonPath("$.thongTinDs").value(DEFAULT_THONG_TIN_DS.toString()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idDsGoc").value(DEFAULT_ID_DS_GOC.intValue()))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.soGiayTo").value(DEFAULT_SO_GIAY_TO))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU))
            .andExpect(jsonPath("$.idLoaiNganChan").value(DEFAULT_ID_LOAI_NGAN_CHAN.intValue()))
            .andExpect(jsonPath("$.syncStatus").value(DEFAULT_SYNC_STATUS));
    }

    @Test
    @Transactional
    void getDuongSusByIdFiltering() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        Long id = duongSu.getIdDuongSu();

        defaultDuongSuFiltering("idDuongSu.equals=" + id, "idDuongSu.notEquals=" + id);

        defaultDuongSuFiltering("idDuongSu.greaterThanOrEqual=" + id, "idDuongSu.greaterThan=" + id);

        defaultDuongSuFiltering("idDuongSu.lessThanOrEqual=" + id, "idDuongSu.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDuongSusByTenDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where tenDuongSu equals to
        defaultDuongSuFiltering("tenDuongSu.equals=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.equals=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDuongSusByTenDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where tenDuongSu in
        defaultDuongSuFiltering(
            "tenDuongSu.in=" + DEFAULT_TEN_DUONG_SU + "," + UPDATED_TEN_DUONG_SU,
            "tenDuongSu.in=" + UPDATED_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByTenDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where tenDuongSu is not null
        defaultDuongSuFiltering("tenDuongSu.specified=true", "tenDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByTenDuongSuContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where tenDuongSu contains
        defaultDuongSuFiltering("tenDuongSu.contains=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.contains=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDuongSusByTenDuongSuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where tenDuongSu does not contain
        defaultDuongSuFiltering("tenDuongSu.doesNotContain=" + UPDATED_TEN_DUONG_SU, "tenDuongSu.doesNotContain=" + DEFAULT_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllDuongSusByDiaChiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where diaChi equals to
        defaultDuongSuFiltering("diaChi.equals=" + DEFAULT_DIA_CHI, "diaChi.equals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSusByDiaChiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where diaChi in
        defaultDuongSuFiltering("diaChi.in=" + DEFAULT_DIA_CHI + "," + UPDATED_DIA_CHI, "diaChi.in=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSusByDiaChiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where diaChi is not null
        defaultDuongSuFiltering("diaChi.specified=true", "diaChi.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByDiaChiContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where diaChi contains
        defaultDuongSuFiltering("diaChi.contains=" + DEFAULT_DIA_CHI, "diaChi.contains=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSusByDiaChiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where diaChi does not contain
        defaultDuongSuFiltering("diaChi.doesNotContain=" + UPDATED_DIA_CHI, "diaChi.doesNotContain=" + DEFAULT_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDuongSusBySoDienThoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where soDienThoai equals to
        defaultDuongSuFiltering("soDienThoai.equals=" + DEFAULT_SO_DIEN_THOAI, "soDienThoai.equals=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllDuongSusBySoDienThoaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where soDienThoai in
        defaultDuongSuFiltering(
            "soDienThoai.in=" + DEFAULT_SO_DIEN_THOAI + "," + UPDATED_SO_DIEN_THOAI,
            "soDienThoai.in=" + UPDATED_SO_DIEN_THOAI
        );
    }

    @Test
    @Transactional
    void getAllDuongSusBySoDienThoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where soDienThoai is not null
        defaultDuongSuFiltering("soDienThoai.specified=true", "soDienThoai.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusBySoDienThoaiContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where soDienThoai contains
        defaultDuongSuFiltering("soDienThoai.contains=" + DEFAULT_SO_DIEN_THOAI, "soDienThoai.contains=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllDuongSusBySoDienThoaiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where soDienThoai does not contain
        defaultDuongSuFiltering(
            "soDienThoai.doesNotContain=" + UPDATED_SO_DIEN_THOAI,
            "soDienThoai.doesNotContain=" + DEFAULT_SO_DIEN_THOAI
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where email equals to
        defaultDuongSuFiltering("email.equals=" + DEFAULT_EMAIL, "email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllDuongSusByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where email in
        defaultDuongSuFiltering("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL, "email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllDuongSusByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where email is not null
        defaultDuongSuFiltering("email.specified=true", "email.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByEmailContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where email contains
        defaultDuongSuFiltering("email.contains=" + DEFAULT_EMAIL, "email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllDuongSusByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where email does not contain
        defaultDuongSuFiltering("email.doesNotContain=" + UPDATED_EMAIL, "email.doesNotContain=" + DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    void getAllDuongSusByFaxIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where fax equals to
        defaultDuongSuFiltering("fax.equals=" + DEFAULT_FAX, "fax.equals=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    void getAllDuongSusByFaxIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where fax in
        defaultDuongSuFiltering("fax.in=" + DEFAULT_FAX + "," + UPDATED_FAX, "fax.in=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    void getAllDuongSusByFaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where fax is not null
        defaultDuongSuFiltering("fax.specified=true", "fax.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByFaxContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where fax contains
        defaultDuongSuFiltering("fax.contains=" + DEFAULT_FAX, "fax.contains=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    void getAllDuongSusByFaxNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where fax does not contain
        defaultDuongSuFiltering("fax.doesNotContain=" + UPDATED_FAX, "fax.doesNotContain=" + DEFAULT_FAX);
    }

    @Test
    @Transactional
    void getAllDuongSusByWebsiteIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where website equals to
        defaultDuongSuFiltering("website.equals=" + DEFAULT_WEBSITE, "website.equals=" + UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    void getAllDuongSusByWebsiteIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where website in
        defaultDuongSuFiltering("website.in=" + DEFAULT_WEBSITE + "," + UPDATED_WEBSITE, "website.in=" + UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    void getAllDuongSusByWebsiteIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where website is not null
        defaultDuongSuFiltering("website.specified=true", "website.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByWebsiteContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where website contains
        defaultDuongSuFiltering("website.contains=" + DEFAULT_WEBSITE, "website.contains=" + UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    void getAllDuongSusByWebsiteNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where website does not contain
        defaultDuongSuFiltering("website.doesNotContain=" + UPDATED_WEBSITE, "website.doesNotContain=" + DEFAULT_WEBSITE);
    }

    @Test
    @Transactional
    void getAllDuongSusByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where trangThai equals to
        defaultDuongSuFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSusByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where trangThai in
        defaultDuongSuFiltering("trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI, "trangThai.in=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSusByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where trangThai is not null
        defaultDuongSuFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where trangThai is greater than or equal to
        defaultDuongSuFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + (DEFAULT_TRANG_THAI + 1)
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where trangThai is less than or equal to
        defaultDuongSuFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSusByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where trangThai is less than
        defaultDuongSuFiltering("trangThai.lessThan=" + (DEFAULT_TRANG_THAI + 1), "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSusByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where trangThai is greater than
        defaultDuongSuFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDuongSusByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ngayThaoTac equals to
        defaultDuongSuFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDuongSusByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ngayThaoTac in
        defaultDuongSuFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ngayThaoTac is not null
        defaultDuongSuFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ngayThaoTac is greater than or equal to
        defaultDuongSuFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ngayThaoTac is less than or equal to
        defaultDuongSuFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ngayThaoTac is less than
        defaultDuongSuFiltering("ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC, "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDuongSusByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ngayThaoTac is greater than
        defaultDuongSuFiltering("ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC, "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDuongSusByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where nguoiThaoTac equals to
        defaultDuongSuFiltering("nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC, "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDuongSusByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where nguoiThaoTac in
        defaultDuongSuFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where nguoiThaoTac is not null
        defaultDuongSuFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where nguoiThaoTac is greater than or equal to
        defaultDuongSuFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where nguoiThaoTac is less than or equal to
        defaultDuongSuFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where nguoiThaoTac is less than
        defaultDuongSuFiltering("nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC, "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDuongSusByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where nguoiThaoTac is greater than
        defaultDuongSuFiltering("nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC, "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDsGocIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDsGoc equals to
        defaultDuongSuFiltering("idDsGoc.equals=" + DEFAULT_ID_DS_GOC, "idDsGoc.equals=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDsGocIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDsGoc in
        defaultDuongSuFiltering("idDsGoc.in=" + DEFAULT_ID_DS_GOC + "," + UPDATED_ID_DS_GOC, "idDsGoc.in=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDsGocIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDsGoc is not null
        defaultDuongSuFiltering("idDsGoc.specified=true", "idDsGoc.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDsGocIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDsGoc is greater than or equal to
        defaultDuongSuFiltering("idDsGoc.greaterThanOrEqual=" + DEFAULT_ID_DS_GOC, "idDsGoc.greaterThanOrEqual=" + UPDATED_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDsGocIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDsGoc is less than or equal to
        defaultDuongSuFiltering("idDsGoc.lessThanOrEqual=" + DEFAULT_ID_DS_GOC, "idDsGoc.lessThanOrEqual=" + SMALLER_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDsGocIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDsGoc is less than
        defaultDuongSuFiltering("idDsGoc.lessThan=" + UPDATED_ID_DS_GOC, "idDsGoc.lessThan=" + DEFAULT_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDsGocIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDsGoc is greater than
        defaultDuongSuFiltering("idDsGoc.greaterThan=" + SMALLER_ID_DS_GOC, "idDsGoc.greaterThan=" + DEFAULT_ID_DS_GOC);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdMasterIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idMaster equals to
        defaultDuongSuFiltering("idMaster.equals=" + DEFAULT_ID_MASTER, "idMaster.equals=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdMasterIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idMaster in
        defaultDuongSuFiltering("idMaster.in=" + DEFAULT_ID_MASTER + "," + UPDATED_ID_MASTER, "idMaster.in=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdMasterIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idMaster is not null
        defaultDuongSuFiltering("idMaster.specified=true", "idMaster.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByIdMasterContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idMaster contains
        defaultDuongSuFiltering("idMaster.contains=" + DEFAULT_ID_MASTER, "idMaster.contains=" + UPDATED_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdMasterNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idMaster does not contain
        defaultDuongSuFiltering("idMaster.doesNotContain=" + UPDATED_ID_MASTER, "idMaster.doesNotContain=" + DEFAULT_ID_MASTER);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDonVi equals to
        defaultDuongSuFiltering("idDonVi.equals=" + DEFAULT_ID_DON_VI, "idDonVi.equals=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDonVi in
        defaultDuongSuFiltering("idDonVi.in=" + DEFAULT_ID_DON_VI + "," + UPDATED_ID_DON_VI, "idDonVi.in=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDonVi is not null
        defaultDuongSuFiltering("idDonVi.specified=true", "idDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDonViIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDonVi is greater than or equal to
        defaultDuongSuFiltering("idDonVi.greaterThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.greaterThanOrEqual=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDonViIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDonVi is less than or equal to
        defaultDuongSuFiltering("idDonVi.lessThanOrEqual=" + DEFAULT_ID_DON_VI, "idDonVi.lessThanOrEqual=" + SMALLER_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDonViIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDonVi is less than
        defaultDuongSuFiltering("idDonVi.lessThan=" + UPDATED_ID_DON_VI, "idDonVi.lessThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdDonViIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idDonVi is greater than
        defaultDuongSuFiltering("idDonVi.greaterThan=" + SMALLER_ID_DON_VI, "idDonVi.greaterThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllDuongSusByStrSearchIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where strSearch equals to
        defaultDuongSuFiltering("strSearch.equals=" + DEFAULT_STR_SEARCH, "strSearch.equals=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDuongSusByStrSearchIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where strSearch in
        defaultDuongSuFiltering("strSearch.in=" + DEFAULT_STR_SEARCH + "," + UPDATED_STR_SEARCH, "strSearch.in=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDuongSusByStrSearchIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where strSearch is not null
        defaultDuongSuFiltering("strSearch.specified=true", "strSearch.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByStrSearchContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where strSearch contains
        defaultDuongSuFiltering("strSearch.contains=" + DEFAULT_STR_SEARCH, "strSearch.contains=" + UPDATED_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDuongSusByStrSearchNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where strSearch does not contain
        defaultDuongSuFiltering("strSearch.doesNotContain=" + UPDATED_STR_SEARCH, "strSearch.doesNotContain=" + DEFAULT_STR_SEARCH);
    }

    @Test
    @Transactional
    void getAllDuongSusBySoGiayToIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where soGiayTo equals to
        defaultDuongSuFiltering("soGiayTo.equals=" + DEFAULT_SO_GIAY_TO, "soGiayTo.equals=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDuongSusBySoGiayToIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where soGiayTo in
        defaultDuongSuFiltering("soGiayTo.in=" + DEFAULT_SO_GIAY_TO + "," + UPDATED_SO_GIAY_TO, "soGiayTo.in=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDuongSusBySoGiayToIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where soGiayTo is not null
        defaultDuongSuFiltering("soGiayTo.specified=true", "soGiayTo.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusBySoGiayToContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where soGiayTo contains
        defaultDuongSuFiltering("soGiayTo.contains=" + DEFAULT_SO_GIAY_TO, "soGiayTo.contains=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDuongSusBySoGiayToNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where soGiayTo does not contain
        defaultDuongSuFiltering("soGiayTo.doesNotContain=" + UPDATED_SO_GIAY_TO, "soGiayTo.doesNotContain=" + DEFAULT_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllDuongSusByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ghiChu equals to
        defaultDuongSuFiltering("ghiChu.equals=" + DEFAULT_GHI_CHU, "ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllDuongSusByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ghiChu in
        defaultDuongSuFiltering("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU, "ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllDuongSusByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ghiChu is not null
        defaultDuongSuFiltering("ghiChu.specified=true", "ghiChu.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByGhiChuContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ghiChu contains
        defaultDuongSuFiltering("ghiChu.contains=" + DEFAULT_GHI_CHU, "ghiChu.contains=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllDuongSusByGhiChuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where ghiChu does not contain
        defaultDuongSuFiltering("ghiChu.doesNotContain=" + UPDATED_GHI_CHU, "ghiChu.doesNotContain=" + DEFAULT_GHI_CHU);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idLoaiNganChan equals to
        defaultDuongSuFiltering("idLoaiNganChan.equals=" + DEFAULT_ID_LOAI_NGAN_CHAN, "idLoaiNganChan.equals=" + UPDATED_ID_LOAI_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllDuongSusByIdLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idLoaiNganChan in
        defaultDuongSuFiltering(
            "idLoaiNganChan.in=" + DEFAULT_ID_LOAI_NGAN_CHAN + "," + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.in=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByIdLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idLoaiNganChan is not null
        defaultDuongSuFiltering("idLoaiNganChan.specified=true", "idLoaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusByIdLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idLoaiNganChan is greater than or equal to
        defaultDuongSuFiltering(
            "idLoaiNganChan.greaterThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThanOrEqual=" + UPDATED_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByIdLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idLoaiNganChan is less than or equal to
        defaultDuongSuFiltering(
            "idLoaiNganChan.lessThanOrEqual=" + DEFAULT_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThanOrEqual=" + SMALLER_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByIdLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idLoaiNganChan is less than
        defaultDuongSuFiltering(
            "idLoaiNganChan.lessThan=" + UPDATED_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.lessThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDuongSusByIdLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where idLoaiNganChan is greater than
        defaultDuongSuFiltering(
            "idLoaiNganChan.greaterThan=" + SMALLER_ID_LOAI_NGAN_CHAN,
            "idLoaiNganChan.greaterThan=" + DEFAULT_ID_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllDuongSusBySyncStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where syncStatus equals to
        defaultDuongSuFiltering("syncStatus.equals=" + DEFAULT_SYNC_STATUS, "syncStatus.equals=" + UPDATED_SYNC_STATUS);
    }

    @Test
    @Transactional
    void getAllDuongSusBySyncStatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where syncStatus in
        defaultDuongSuFiltering("syncStatus.in=" + DEFAULT_SYNC_STATUS + "," + UPDATED_SYNC_STATUS, "syncStatus.in=" + UPDATED_SYNC_STATUS);
    }

    @Test
    @Transactional
    void getAllDuongSusBySyncStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where syncStatus is not null
        defaultDuongSuFiltering("syncStatus.specified=true", "syncStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllDuongSusBySyncStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where syncStatus is greater than or equal to
        defaultDuongSuFiltering(
            "syncStatus.greaterThanOrEqual=" + DEFAULT_SYNC_STATUS,
            "syncStatus.greaterThanOrEqual=" + (DEFAULT_SYNC_STATUS + 1)
        );
    }

    @Test
    @Transactional
    void getAllDuongSusBySyncStatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where syncStatus is less than or equal to
        defaultDuongSuFiltering("syncStatus.lessThanOrEqual=" + DEFAULT_SYNC_STATUS, "syncStatus.lessThanOrEqual=" + SMALLER_SYNC_STATUS);
    }

    @Test
    @Transactional
    void getAllDuongSusBySyncStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where syncStatus is less than
        defaultDuongSuFiltering("syncStatus.lessThan=" + (DEFAULT_SYNC_STATUS + 1), "syncStatus.lessThan=" + DEFAULT_SYNC_STATUS);
    }

    @Test
    @Transactional
    void getAllDuongSusBySyncStatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        // Get all the duongSuList where syncStatus is greater than
        defaultDuongSuFiltering("syncStatus.greaterThan=" + SMALLER_SYNC_STATUS, "syncStatus.greaterThan=" + DEFAULT_SYNC_STATUS);
    }

    @Test
    @Transactional
    void getAllDuongSusByLoaiDuongSuIsEqualToSomething() throws Exception {
        LoaiDuongSu loaiDuongSu;
        if (TestUtil.findAll(em, LoaiDuongSu.class).isEmpty()) {
            duongSuRepository.saveAndFlush(duongSu);
            loaiDuongSu = LoaiDuongSuResourceIT.createEntity();
        } else {
            loaiDuongSu = TestUtil.findAll(em, LoaiDuongSu.class).get(0);
        }
        em.persist(loaiDuongSu);
        em.flush();
        duongSu.setLoaiDuongSu(loaiDuongSu);
        duongSuRepository.saveAndFlush(duongSu);
        String loaiDuongSuId = loaiDuongSu.getIdLoaiDuongSu();
        // Get all the duongSuList where loaiDuongSu equals to loaiDuongSuId
        defaultDuongSuShouldBeFound("loaiDuongSuId.equals=" + loaiDuongSuId);

        // Get all the duongSuList where loaiDuongSu equals to "invalid-id"
        defaultDuongSuShouldNotBeFound("loaiDuongSuId.equals=" + "invalid-id");
    }

    @Test
    @Transactional
    void getAllDuongSusByLoaiGiayToIsEqualToSomething() throws Exception {
        LoaiGiayTo loaiGiayTo;
        if (TestUtil.findAll(em, LoaiGiayTo.class).isEmpty()) {
            duongSuRepository.saveAndFlush(duongSu);
            loaiGiayTo = LoaiGiayToResourceIT.createEntity();
        } else {
            loaiGiayTo = TestUtil.findAll(em, LoaiGiayTo.class).get(0);
        }
        em.persist(loaiGiayTo);
        em.flush();
        duongSu.setLoaiGiayTo(loaiGiayTo);
        duongSuRepository.saveAndFlush(duongSu);
        String loaiGiayToId = loaiGiayTo.getIdLoaiGiayTo();
        // Get all the duongSuList where loaiGiayTo equals to loaiGiayToId
        defaultDuongSuShouldBeFound("loaiGiayToId.equals=" + loaiGiayToId);

        // Get all the duongSuList where loaiGiayTo equals to "invalid-id"
        defaultDuongSuShouldNotBeFound("loaiGiayToId.equals=" + "invalid-id");
    }

    private void defaultDuongSuFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDuongSuShouldBeFound(shouldBeFound);
        defaultDuongSuShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDuongSuShouldBeFound(String filter) throws Exception {
        restDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDuongSu,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(duongSu.getIdDuongSu().intValue())))
            .andExpect(jsonPath("$.[*].tenDuongSu").value(hasItem(DEFAULT_TEN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].thongTinDs").value(hasItem(DEFAULT_THONG_TIN_DS.toString())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDsGoc").value(hasItem(DEFAULT_ID_DS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].soGiayTo").value(hasItem(DEFAULT_SO_GIAY_TO)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())))
            .andExpect(jsonPath("$.[*].syncStatus").value(hasItem(DEFAULT_SYNC_STATUS)));

        // Check, that the count call also returns 1
        restDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idDuongSu,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDuongSuShouldNotBeFound(String filter) throws Exception {
        restDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDuongSu,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idDuongSu,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDuongSu() throws Exception {
        // Get the duongSu
        restDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDuongSu() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSu
        DuongSu updatedDuongSu = duongSuRepository.findById(duongSu.getIdDuongSu()).orElseThrow();
        // Disconnect from session so that the updates on updatedDuongSu are not directly saved in db
        em.detach(updatedDuongSu);
        updatedDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .website(UPDATED_WEBSITE)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .ghiChu(UPDATED_GHI_CHU)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .syncStatus(UPDATED_SYNC_STATUS);
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(updatedDuongSu);

        restDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, duongSuDTO.getIdDuongSu())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDuongSuToMatchAllProperties(updatedDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, duongSuDTO.getIdDuongSu())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(duongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(duongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSu using partial update
        DuongSu partialUpdatedDuongSu = new DuongSu();
        partialUpdatedDuongSu.setIdDuongSu(duongSu.getIdDuongSu());

        partialUpdatedDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .fax(UPDATED_FAX)
            .website(UPDATED_WEBSITE)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDonVi(UPDATED_ID_DON_VI)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN);

        restDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDuongSu.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDuongSuUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedDuongSu, duongSu), getPersistedDuongSu(duongSu));
    }

    @Test
    @Transactional
    void fullUpdateDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the duongSu using partial update
        DuongSu partialUpdatedDuongSu = new DuongSu();
        partialUpdatedDuongSu.setIdDuongSu(duongSu.getIdDuongSu());

        partialUpdatedDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .diaChi(UPDATED_DIA_CHI)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .website(UPDATED_WEBSITE)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinDs(UPDATED_THONG_TIN_DS)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDsGoc(UPDATED_ID_DS_GOC)
            .idMaster(UPDATED_ID_MASTER)
            .idDonVi(UPDATED_ID_DON_VI)
            .strSearch(UPDATED_STR_SEARCH)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .ghiChu(UPDATED_GHI_CHU)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .syncStatus(UPDATED_SYNC_STATUS);

        restDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDuongSu.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the DuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDuongSuUpdatableFieldsEquals(partialUpdatedDuongSu, getPersistedDuongSu(partialUpdatedDuongSu));
    }

    @Test
    @Transactional
    void patchNonExistingDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, duongSuDTO.getIdDuongSu())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(duongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(duongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        duongSu.setIdDuongSu(longCount.incrementAndGet());

        // Create the DuongSu
        DuongSuDTO duongSuDTO = duongSuMapper.toDto(duongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDuongSuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(duongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDuongSu() throws Exception {
        // Initialize the database
        insertedDuongSu = duongSuRepository.saveAndFlush(duongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the duongSu
        restDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, duongSu.getIdDuongSu()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return duongSuRepository.count();
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

    protected DuongSu getPersistedDuongSu(DuongSu duongSu) {
        return duongSuRepository.findById(duongSu.getIdDuongSu()).orElseThrow();
    }

    protected void assertPersistedDuongSuToMatchAllProperties(DuongSu expectedDuongSu) {
        assertDuongSuAllPropertiesEquals(expectedDuongSu, getPersistedDuongSu(expectedDuongSu));
    }

    protected void assertPersistedDuongSuToMatchUpdatableProperties(DuongSu expectedDuongSu) {
        assertDuongSuAllUpdatablePropertiesEquals(expectedDuongSu, getPersistedDuongSu(expectedDuongSu));
    }
}
