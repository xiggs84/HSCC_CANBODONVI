package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucCanBoAsserts.*;
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
import vn.vnpt.domain.DanhMucCanBo;
import vn.vnpt.repository.DanhMucCanBoRepository;
import vn.vnpt.service.dto.DanhMucCanBoDTO;
import vn.vnpt.service.mapper.DanhMucCanBoMapper;

/**
 * Integration tests for the {@link DanhMucCanBoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucCanBoResourceIT {

    private static final String DEFAULT_TEN_CAN_BO = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CAN_BO = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NAM_SINH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NAM_SINH = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NAM_SINH = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SO_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_SO_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_SO_CMND = "AAAAAAAAAA";
    private static final String UPDATED_SO_CMND = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_DANG_NHAP = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DANG_NHAP = "BBBBBBBBBB";

    private static final String DEFAULT_MAT_KHAU = "AAAAAAAAAA";
    private static final String UPDATED_MAT_KHAU = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;
    private static final Long SMALLER_TRANG_THAI = 1L - 1L;

    private static final String DEFAULT_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_SECRET = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_SECRET = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME_KYSO = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME_KYSO = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD_KYSO = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD_KYSO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-can-bos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCanBo}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucCanBoRepository danhMucCanBoRepository;

    @Autowired
    private DanhMucCanBoMapper danhMucCanBoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucCanBoMockMvc;

    private DanhMucCanBo danhMucCanBo;

    private DanhMucCanBo insertedDanhMucCanBo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucCanBo createEntity() {
        return new DanhMucCanBo()
            .tenCanBo(DEFAULT_TEN_CAN_BO)
            .diaChi(DEFAULT_DIA_CHI)
            .namSinh(DEFAULT_NAM_SINH)
            .email(DEFAULT_EMAIL)
            .soDienThoai(DEFAULT_SO_DIEN_THOAI)
            .soCmnd(DEFAULT_SO_CMND)
            .tenDangNhap(DEFAULT_TEN_DANG_NHAP)
            .matKhau(DEFAULT_MAT_KHAU)
            .trangThai(DEFAULT_TRANG_THAI)
            .clientId(DEFAULT_CLIENT_ID)
            .clientSecret(DEFAULT_CLIENT_SECRET)
            .usernameKyso(DEFAULT_USERNAME_KYSO)
            .passwordKyso(DEFAULT_PASSWORD_KYSO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucCanBo createUpdatedEntity() {
        return new DanhMucCanBo()
            .tenCanBo(UPDATED_TEN_CAN_BO)
            .diaChi(UPDATED_DIA_CHI)
            .namSinh(UPDATED_NAM_SINH)
            .email(UPDATED_EMAIL)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .soCmnd(UPDATED_SO_CMND)
            .tenDangNhap(UPDATED_TEN_DANG_NHAP)
            .matKhau(UPDATED_MAT_KHAU)
            .trangThai(UPDATED_TRANG_THAI)
            .clientId(UPDATED_CLIENT_ID)
            .clientSecret(UPDATED_CLIENT_SECRET)
            .usernameKyso(UPDATED_USERNAME_KYSO)
            .passwordKyso(UPDATED_PASSWORD_KYSO);
    }

    @BeforeEach
    public void initTest() {
        danhMucCanBo = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucCanBo != null) {
            danhMucCanBoRepository.delete(insertedDanhMucCanBo);
            insertedDanhMucCanBo = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucCanBo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucCanBo
        DanhMucCanBoDTO danhMucCanBoDTO = danhMucCanBoMapper.toDto(danhMucCanBo);
        var returnedDanhMucCanBoDTO = om.readValue(
            restDanhMucCanBoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucCanBoDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucCanBoDTO.class
        );

        // Validate the DanhMucCanBo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucCanBo = danhMucCanBoMapper.toEntity(returnedDanhMucCanBoDTO);
        assertDanhMucCanBoUpdatableFieldsEquals(returnedDanhMucCanBo, getPersistedDanhMucCanBo(returnedDanhMucCanBo));

        insertedDanhMucCanBo = returnedDanhMucCanBo;
    }

    @Test
    @Transactional
    void createDanhMucCanBoWithExistingId() throws Exception {
        // Create the DanhMucCanBo with an existing ID
        danhMucCanBo.setIdCanBo(1L);
        DanhMucCanBoDTO danhMucCanBoDTO = danhMucCanBoMapper.toDto(danhMucCanBo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucCanBoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucCanBoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucCanBo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBos() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList
        restDanhMucCanBoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCanBo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCanBo").value(hasItem(danhMucCanBo.getIdCanBo().intValue())))
            .andExpect(jsonPath("$.[*].tenCanBo").value(hasItem(DEFAULT_TEN_CAN_BO)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].namSinh").value(hasItem(DEFAULT_NAM_SINH.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].soCmnd").value(hasItem(DEFAULT_SO_CMND)))
            .andExpect(jsonPath("$.[*].tenDangNhap").value(hasItem(DEFAULT_TEN_DANG_NHAP)))
            .andExpect(jsonPath("$.[*].matKhau").value(hasItem(DEFAULT_MAT_KHAU)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].clientSecret").value(hasItem(DEFAULT_CLIENT_SECRET)))
            .andExpect(jsonPath("$.[*].usernameKyso").value(hasItem(DEFAULT_USERNAME_KYSO)))
            .andExpect(jsonPath("$.[*].passwordKyso").value(hasItem(DEFAULT_PASSWORD_KYSO)));
    }

    @Test
    @Transactional
    void getDanhMucCanBo() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get the danhMucCanBo
        restDanhMucCanBoMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucCanBo.getIdCanBo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCanBo").value(danhMucCanBo.getIdCanBo().intValue()))
            .andExpect(jsonPath("$.tenCanBo").value(DEFAULT_TEN_CAN_BO))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.namSinh").value(DEFAULT_NAM_SINH.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.soDienThoai").value(DEFAULT_SO_DIEN_THOAI))
            .andExpect(jsonPath("$.soCmnd").value(DEFAULT_SO_CMND))
            .andExpect(jsonPath("$.tenDangNhap").value(DEFAULT_TEN_DANG_NHAP))
            .andExpect(jsonPath("$.matKhau").value(DEFAULT_MAT_KHAU))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID))
            .andExpect(jsonPath("$.clientSecret").value(DEFAULT_CLIENT_SECRET))
            .andExpect(jsonPath("$.usernameKyso").value(DEFAULT_USERNAME_KYSO))
            .andExpect(jsonPath("$.passwordKyso").value(DEFAULT_PASSWORD_KYSO));
    }

    @Test
    @Transactional
    void getDanhMucCanBosByIdFiltering() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        Long id = danhMucCanBo.getIdCanBo();

        defaultDanhMucCanBoFiltering("idCanBo.equals=" + id, "idCanBo.notEquals=" + id);

        defaultDanhMucCanBoFiltering("idCanBo.greaterThanOrEqual=" + id, "idCanBo.greaterThan=" + id);

        defaultDanhMucCanBoFiltering("idCanBo.lessThanOrEqual=" + id, "idCanBo.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTenCanBoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where tenCanBo equals to
        defaultDanhMucCanBoFiltering("tenCanBo.equals=" + DEFAULT_TEN_CAN_BO, "tenCanBo.equals=" + UPDATED_TEN_CAN_BO);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTenCanBoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where tenCanBo in
        defaultDanhMucCanBoFiltering("tenCanBo.in=" + DEFAULT_TEN_CAN_BO + "," + UPDATED_TEN_CAN_BO, "tenCanBo.in=" + UPDATED_TEN_CAN_BO);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTenCanBoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where tenCanBo is not null
        defaultDanhMucCanBoFiltering("tenCanBo.specified=true", "tenCanBo.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTenCanBoContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where tenCanBo contains
        defaultDanhMucCanBoFiltering("tenCanBo.contains=" + DEFAULT_TEN_CAN_BO, "tenCanBo.contains=" + UPDATED_TEN_CAN_BO);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTenCanBoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where tenCanBo does not contain
        defaultDanhMucCanBoFiltering("tenCanBo.doesNotContain=" + UPDATED_TEN_CAN_BO, "tenCanBo.doesNotContain=" + DEFAULT_TEN_CAN_BO);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByDiaChiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where diaChi equals to
        defaultDanhMucCanBoFiltering("diaChi.equals=" + DEFAULT_DIA_CHI, "diaChi.equals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByDiaChiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where diaChi in
        defaultDanhMucCanBoFiltering("diaChi.in=" + DEFAULT_DIA_CHI + "," + UPDATED_DIA_CHI, "diaChi.in=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByDiaChiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where diaChi is not null
        defaultDanhMucCanBoFiltering("diaChi.specified=true", "diaChi.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByDiaChiContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where diaChi contains
        defaultDanhMucCanBoFiltering("diaChi.contains=" + DEFAULT_DIA_CHI, "diaChi.contains=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByDiaChiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where diaChi does not contain
        defaultDanhMucCanBoFiltering("diaChi.doesNotContain=" + UPDATED_DIA_CHI, "diaChi.doesNotContain=" + DEFAULT_DIA_CHI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByNamSinhIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where namSinh equals to
        defaultDanhMucCanBoFiltering("namSinh.equals=" + DEFAULT_NAM_SINH, "namSinh.equals=" + UPDATED_NAM_SINH);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByNamSinhIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where namSinh in
        defaultDanhMucCanBoFiltering("namSinh.in=" + DEFAULT_NAM_SINH + "," + UPDATED_NAM_SINH, "namSinh.in=" + UPDATED_NAM_SINH);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByNamSinhIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where namSinh is not null
        defaultDanhMucCanBoFiltering("namSinh.specified=true", "namSinh.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByNamSinhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where namSinh is greater than or equal to
        defaultDanhMucCanBoFiltering("namSinh.greaterThanOrEqual=" + DEFAULT_NAM_SINH, "namSinh.greaterThanOrEqual=" + UPDATED_NAM_SINH);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByNamSinhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where namSinh is less than or equal to
        defaultDanhMucCanBoFiltering("namSinh.lessThanOrEqual=" + DEFAULT_NAM_SINH, "namSinh.lessThanOrEqual=" + SMALLER_NAM_SINH);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByNamSinhIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where namSinh is less than
        defaultDanhMucCanBoFiltering("namSinh.lessThan=" + UPDATED_NAM_SINH, "namSinh.lessThan=" + DEFAULT_NAM_SINH);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByNamSinhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where namSinh is greater than
        defaultDanhMucCanBoFiltering("namSinh.greaterThan=" + SMALLER_NAM_SINH, "namSinh.greaterThan=" + DEFAULT_NAM_SINH);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where email equals to
        defaultDanhMucCanBoFiltering("email.equals=" + DEFAULT_EMAIL, "email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where email in
        defaultDanhMucCanBoFiltering("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL, "email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where email is not null
        defaultDanhMucCanBoFiltering("email.specified=true", "email.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByEmailContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where email contains
        defaultDanhMucCanBoFiltering("email.contains=" + DEFAULT_EMAIL, "email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where email does not contain
        defaultDanhMucCanBoFiltering("email.doesNotContain=" + UPDATED_EMAIL, "email.doesNotContain=" + DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosBySoDienThoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where soDienThoai equals to
        defaultDanhMucCanBoFiltering("soDienThoai.equals=" + DEFAULT_SO_DIEN_THOAI, "soDienThoai.equals=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosBySoDienThoaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where soDienThoai in
        defaultDanhMucCanBoFiltering(
            "soDienThoai.in=" + DEFAULT_SO_DIEN_THOAI + "," + UPDATED_SO_DIEN_THOAI,
            "soDienThoai.in=" + UPDATED_SO_DIEN_THOAI
        );
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosBySoDienThoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where soDienThoai is not null
        defaultDanhMucCanBoFiltering("soDienThoai.specified=true", "soDienThoai.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosBySoDienThoaiContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where soDienThoai contains
        defaultDanhMucCanBoFiltering("soDienThoai.contains=" + DEFAULT_SO_DIEN_THOAI, "soDienThoai.contains=" + UPDATED_SO_DIEN_THOAI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosBySoDienThoaiNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where soDienThoai does not contain
        defaultDanhMucCanBoFiltering(
            "soDienThoai.doesNotContain=" + UPDATED_SO_DIEN_THOAI,
            "soDienThoai.doesNotContain=" + DEFAULT_SO_DIEN_THOAI
        );
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosBySoCmndIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where soCmnd equals to
        defaultDanhMucCanBoFiltering("soCmnd.equals=" + DEFAULT_SO_CMND, "soCmnd.equals=" + UPDATED_SO_CMND);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosBySoCmndIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where soCmnd in
        defaultDanhMucCanBoFiltering("soCmnd.in=" + DEFAULT_SO_CMND + "," + UPDATED_SO_CMND, "soCmnd.in=" + UPDATED_SO_CMND);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosBySoCmndIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where soCmnd is not null
        defaultDanhMucCanBoFiltering("soCmnd.specified=true", "soCmnd.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosBySoCmndContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where soCmnd contains
        defaultDanhMucCanBoFiltering("soCmnd.contains=" + DEFAULT_SO_CMND, "soCmnd.contains=" + UPDATED_SO_CMND);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosBySoCmndNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where soCmnd does not contain
        defaultDanhMucCanBoFiltering("soCmnd.doesNotContain=" + UPDATED_SO_CMND, "soCmnd.doesNotContain=" + DEFAULT_SO_CMND);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTenDangNhapIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where tenDangNhap equals to
        defaultDanhMucCanBoFiltering("tenDangNhap.equals=" + DEFAULT_TEN_DANG_NHAP, "tenDangNhap.equals=" + UPDATED_TEN_DANG_NHAP);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTenDangNhapIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where tenDangNhap in
        defaultDanhMucCanBoFiltering(
            "tenDangNhap.in=" + DEFAULT_TEN_DANG_NHAP + "," + UPDATED_TEN_DANG_NHAP,
            "tenDangNhap.in=" + UPDATED_TEN_DANG_NHAP
        );
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTenDangNhapIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where tenDangNhap is not null
        defaultDanhMucCanBoFiltering("tenDangNhap.specified=true", "tenDangNhap.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTenDangNhapContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where tenDangNhap contains
        defaultDanhMucCanBoFiltering("tenDangNhap.contains=" + DEFAULT_TEN_DANG_NHAP, "tenDangNhap.contains=" + UPDATED_TEN_DANG_NHAP);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTenDangNhapNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where tenDangNhap does not contain
        defaultDanhMucCanBoFiltering(
            "tenDangNhap.doesNotContain=" + UPDATED_TEN_DANG_NHAP,
            "tenDangNhap.doesNotContain=" + DEFAULT_TEN_DANG_NHAP
        );
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByMatKhauIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where matKhau equals to
        defaultDanhMucCanBoFiltering("matKhau.equals=" + DEFAULT_MAT_KHAU, "matKhau.equals=" + UPDATED_MAT_KHAU);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByMatKhauIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where matKhau in
        defaultDanhMucCanBoFiltering("matKhau.in=" + DEFAULT_MAT_KHAU + "," + UPDATED_MAT_KHAU, "matKhau.in=" + UPDATED_MAT_KHAU);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByMatKhauIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where matKhau is not null
        defaultDanhMucCanBoFiltering("matKhau.specified=true", "matKhau.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByMatKhauContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where matKhau contains
        defaultDanhMucCanBoFiltering("matKhau.contains=" + DEFAULT_MAT_KHAU, "matKhau.contains=" + UPDATED_MAT_KHAU);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByMatKhauNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where matKhau does not contain
        defaultDanhMucCanBoFiltering("matKhau.doesNotContain=" + UPDATED_MAT_KHAU, "matKhau.doesNotContain=" + DEFAULT_MAT_KHAU);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where trangThai equals to
        defaultDanhMucCanBoFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where trangThai in
        defaultDanhMucCanBoFiltering("trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI, "trangThai.in=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where trangThai is not null
        defaultDanhMucCanBoFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where trangThai is greater than or equal to
        defaultDanhMucCanBoFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where trangThai is less than or equal to
        defaultDanhMucCanBoFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where trangThai is less than
        defaultDanhMucCanBoFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where trangThai is greater than
        defaultDanhMucCanBoFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByClientIdIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where clientId equals to
        defaultDanhMucCanBoFiltering("clientId.equals=" + DEFAULT_CLIENT_ID, "clientId.equals=" + UPDATED_CLIENT_ID);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByClientIdIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where clientId in
        defaultDanhMucCanBoFiltering("clientId.in=" + DEFAULT_CLIENT_ID + "," + UPDATED_CLIENT_ID, "clientId.in=" + UPDATED_CLIENT_ID);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByClientIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where clientId is not null
        defaultDanhMucCanBoFiltering("clientId.specified=true", "clientId.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByClientIdContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where clientId contains
        defaultDanhMucCanBoFiltering("clientId.contains=" + DEFAULT_CLIENT_ID, "clientId.contains=" + UPDATED_CLIENT_ID);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByClientIdNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where clientId does not contain
        defaultDanhMucCanBoFiltering("clientId.doesNotContain=" + UPDATED_CLIENT_ID, "clientId.doesNotContain=" + DEFAULT_CLIENT_ID);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByClientSecretIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where clientSecret equals to
        defaultDanhMucCanBoFiltering("clientSecret.equals=" + DEFAULT_CLIENT_SECRET, "clientSecret.equals=" + UPDATED_CLIENT_SECRET);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByClientSecretIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where clientSecret in
        defaultDanhMucCanBoFiltering(
            "clientSecret.in=" + DEFAULT_CLIENT_SECRET + "," + UPDATED_CLIENT_SECRET,
            "clientSecret.in=" + UPDATED_CLIENT_SECRET
        );
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByClientSecretIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where clientSecret is not null
        defaultDanhMucCanBoFiltering("clientSecret.specified=true", "clientSecret.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByClientSecretContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where clientSecret contains
        defaultDanhMucCanBoFiltering("clientSecret.contains=" + DEFAULT_CLIENT_SECRET, "clientSecret.contains=" + UPDATED_CLIENT_SECRET);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByClientSecretNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where clientSecret does not contain
        defaultDanhMucCanBoFiltering(
            "clientSecret.doesNotContain=" + UPDATED_CLIENT_SECRET,
            "clientSecret.doesNotContain=" + DEFAULT_CLIENT_SECRET
        );
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByUsernameKysoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where usernameKyso equals to
        defaultDanhMucCanBoFiltering("usernameKyso.equals=" + DEFAULT_USERNAME_KYSO, "usernameKyso.equals=" + UPDATED_USERNAME_KYSO);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByUsernameKysoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where usernameKyso in
        defaultDanhMucCanBoFiltering(
            "usernameKyso.in=" + DEFAULT_USERNAME_KYSO + "," + UPDATED_USERNAME_KYSO,
            "usernameKyso.in=" + UPDATED_USERNAME_KYSO
        );
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByUsernameKysoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where usernameKyso is not null
        defaultDanhMucCanBoFiltering("usernameKyso.specified=true", "usernameKyso.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByUsernameKysoContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where usernameKyso contains
        defaultDanhMucCanBoFiltering("usernameKyso.contains=" + DEFAULT_USERNAME_KYSO, "usernameKyso.contains=" + UPDATED_USERNAME_KYSO);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByUsernameKysoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where usernameKyso does not contain
        defaultDanhMucCanBoFiltering(
            "usernameKyso.doesNotContain=" + UPDATED_USERNAME_KYSO,
            "usernameKyso.doesNotContain=" + DEFAULT_USERNAME_KYSO
        );
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByPasswordKysoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where passwordKyso equals to
        defaultDanhMucCanBoFiltering("passwordKyso.equals=" + DEFAULT_PASSWORD_KYSO, "passwordKyso.equals=" + UPDATED_PASSWORD_KYSO);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByPasswordKysoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where passwordKyso in
        defaultDanhMucCanBoFiltering(
            "passwordKyso.in=" + DEFAULT_PASSWORD_KYSO + "," + UPDATED_PASSWORD_KYSO,
            "passwordKyso.in=" + UPDATED_PASSWORD_KYSO
        );
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByPasswordKysoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where passwordKyso is not null
        defaultDanhMucCanBoFiltering("passwordKyso.specified=true", "passwordKyso.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByPasswordKysoContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where passwordKyso contains
        defaultDanhMucCanBoFiltering("passwordKyso.contains=" + DEFAULT_PASSWORD_KYSO, "passwordKyso.contains=" + UPDATED_PASSWORD_KYSO);
    }

    @Test
    @Transactional
    void getAllDanhMucCanBosByPasswordKysoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        // Get all the danhMucCanBoList where passwordKyso does not contain
        defaultDanhMucCanBoFiltering(
            "passwordKyso.doesNotContain=" + UPDATED_PASSWORD_KYSO,
            "passwordKyso.doesNotContain=" + DEFAULT_PASSWORD_KYSO
        );
    }

    private void defaultDanhMucCanBoFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDanhMucCanBoShouldBeFound(shouldBeFound);
        defaultDanhMucCanBoShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhMucCanBoShouldBeFound(String filter) throws Exception {
        restDanhMucCanBoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCanBo,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCanBo").value(hasItem(danhMucCanBo.getIdCanBo().intValue())))
            .andExpect(jsonPath("$.[*].tenCanBo").value(hasItem(DEFAULT_TEN_CAN_BO)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].namSinh").value(hasItem(DEFAULT_NAM_SINH.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].soDienThoai").value(hasItem(DEFAULT_SO_DIEN_THOAI)))
            .andExpect(jsonPath("$.[*].soCmnd").value(hasItem(DEFAULT_SO_CMND)))
            .andExpect(jsonPath("$.[*].tenDangNhap").value(hasItem(DEFAULT_TEN_DANG_NHAP)))
            .andExpect(jsonPath("$.[*].matKhau").value(hasItem(DEFAULT_MAT_KHAU)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].clientSecret").value(hasItem(DEFAULT_CLIENT_SECRET)))
            .andExpect(jsonPath("$.[*].usernameKyso").value(hasItem(DEFAULT_USERNAME_KYSO)))
            .andExpect(jsonPath("$.[*].passwordKyso").value(hasItem(DEFAULT_PASSWORD_KYSO)));

        // Check, that the count call also returns 1
        restDanhMucCanBoMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idCanBo,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhMucCanBoShouldNotBeFound(String filter) throws Exception {
        restDanhMucCanBoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCanBo,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhMucCanBoMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idCanBo,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucCanBo() throws Exception {
        // Get the danhMucCanBo
        restDanhMucCanBoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucCanBo() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucCanBo
        DanhMucCanBo updatedDanhMucCanBo = danhMucCanBoRepository.findById(danhMucCanBo.getIdCanBo()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucCanBo are not directly saved in db
        em.detach(updatedDanhMucCanBo);
        updatedDanhMucCanBo
            .tenCanBo(UPDATED_TEN_CAN_BO)
            .diaChi(UPDATED_DIA_CHI)
            .namSinh(UPDATED_NAM_SINH)
            .email(UPDATED_EMAIL)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .soCmnd(UPDATED_SO_CMND)
            .tenDangNhap(UPDATED_TEN_DANG_NHAP)
            .matKhau(UPDATED_MAT_KHAU)
            .trangThai(UPDATED_TRANG_THAI)
            .clientId(UPDATED_CLIENT_ID)
            .clientSecret(UPDATED_CLIENT_SECRET)
            .usernameKyso(UPDATED_USERNAME_KYSO)
            .passwordKyso(UPDATED_PASSWORD_KYSO);
        DanhMucCanBoDTO danhMucCanBoDTO = danhMucCanBoMapper.toDto(updatedDanhMucCanBo);

        restDanhMucCanBoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucCanBoDTO.getIdCanBo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucCanBoDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucCanBo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucCanBoToMatchAllProperties(updatedDanhMucCanBo);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucCanBo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCanBo.setIdCanBo(longCount.incrementAndGet());

        // Create the DanhMucCanBo
        DanhMucCanBoDTO danhMucCanBoDTO = danhMucCanBoMapper.toDto(danhMucCanBo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucCanBoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucCanBoDTO.getIdCanBo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucCanBoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucCanBo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucCanBo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCanBo.setIdCanBo(longCount.incrementAndGet());

        // Create the DanhMucCanBo
        DanhMucCanBoDTO danhMucCanBoDTO = danhMucCanBoMapper.toDto(danhMucCanBo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucCanBoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucCanBoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucCanBo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucCanBo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCanBo.setIdCanBo(longCount.incrementAndGet());

        // Create the DanhMucCanBo
        DanhMucCanBoDTO danhMucCanBoDTO = danhMucCanBoMapper.toDto(danhMucCanBo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucCanBoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucCanBoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucCanBo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucCanBoWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucCanBo using partial update
        DanhMucCanBo partialUpdatedDanhMucCanBo = new DanhMucCanBo();
        partialUpdatedDanhMucCanBo.setIdCanBo(danhMucCanBo.getIdCanBo());

        partialUpdatedDanhMucCanBo
            .diaChi(UPDATED_DIA_CHI)
            .email(UPDATED_EMAIL)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .trangThai(UPDATED_TRANG_THAI)
            .clientId(UPDATED_CLIENT_ID)
            .clientSecret(UPDATED_CLIENT_SECRET)
            .usernameKyso(UPDATED_USERNAME_KYSO);

        restDanhMucCanBoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucCanBo.getIdCanBo())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucCanBo))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucCanBo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucCanBoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucCanBo, danhMucCanBo),
            getPersistedDanhMucCanBo(danhMucCanBo)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucCanBoWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucCanBo using partial update
        DanhMucCanBo partialUpdatedDanhMucCanBo = new DanhMucCanBo();
        partialUpdatedDanhMucCanBo.setIdCanBo(danhMucCanBo.getIdCanBo());

        partialUpdatedDanhMucCanBo
            .tenCanBo(UPDATED_TEN_CAN_BO)
            .diaChi(UPDATED_DIA_CHI)
            .namSinh(UPDATED_NAM_SINH)
            .email(UPDATED_EMAIL)
            .soDienThoai(UPDATED_SO_DIEN_THOAI)
            .soCmnd(UPDATED_SO_CMND)
            .tenDangNhap(UPDATED_TEN_DANG_NHAP)
            .matKhau(UPDATED_MAT_KHAU)
            .trangThai(UPDATED_TRANG_THAI)
            .clientId(UPDATED_CLIENT_ID)
            .clientSecret(UPDATED_CLIENT_SECRET)
            .usernameKyso(UPDATED_USERNAME_KYSO)
            .passwordKyso(UPDATED_PASSWORD_KYSO);

        restDanhMucCanBoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucCanBo.getIdCanBo())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucCanBo))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucCanBo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucCanBoUpdatableFieldsEquals(partialUpdatedDanhMucCanBo, getPersistedDanhMucCanBo(partialUpdatedDanhMucCanBo));
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucCanBo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCanBo.setIdCanBo(longCount.incrementAndGet());

        // Create the DanhMucCanBo
        DanhMucCanBoDTO danhMucCanBoDTO = danhMucCanBoMapper.toDto(danhMucCanBo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucCanBoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucCanBoDTO.getIdCanBo())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucCanBoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucCanBo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucCanBo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCanBo.setIdCanBo(longCount.incrementAndGet());

        // Create the DanhMucCanBo
        DanhMucCanBoDTO danhMucCanBoDTO = danhMucCanBoMapper.toDto(danhMucCanBo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucCanBoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucCanBoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucCanBo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucCanBo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucCanBo.setIdCanBo(longCount.incrementAndGet());

        // Create the DanhMucCanBo
        DanhMucCanBoDTO danhMucCanBoDTO = danhMucCanBoMapper.toDto(danhMucCanBo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucCanBoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucCanBoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucCanBo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucCanBo() throws Exception {
        // Initialize the database
        insertedDanhMucCanBo = danhMucCanBoRepository.saveAndFlush(danhMucCanBo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucCanBo
        restDanhMucCanBoMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucCanBo.getIdCanBo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucCanBoRepository.count();
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

    protected DanhMucCanBo getPersistedDanhMucCanBo(DanhMucCanBo danhMucCanBo) {
        return danhMucCanBoRepository.findById(danhMucCanBo.getIdCanBo()).orElseThrow();
    }

    protected void assertPersistedDanhMucCanBoToMatchAllProperties(DanhMucCanBo expectedDanhMucCanBo) {
        assertDanhMucCanBoAllPropertiesEquals(expectedDanhMucCanBo, getPersistedDanhMucCanBo(expectedDanhMucCanBo));
    }

    protected void assertPersistedDanhMucCanBoToMatchUpdatableProperties(DanhMucCanBo expectedDanhMucCanBo) {
        assertDanhMucCanBoAllUpdatablePropertiesEquals(expectedDanhMucCanBo, getPersistedDanhMucCanBo(expectedDanhMucCanBo));
    }
}
