package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.ChiTietNganChanAsserts.*;
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
import vn.vnpt.domain.ChiTietNganChan;
import vn.vnpt.repository.ChiTietNganChanRepository;
import vn.vnpt.service.dto.ChiTietNganChanDTO;
import vn.vnpt.service.mapper.ChiTietNganChanMapper;

/**
 * Integration tests for the {@link ChiTietNganChanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChiTietNganChanResourceIT {

    private static final Long DEFAULT_ID_DOI_TUONG = 1L;
    private static final Long UPDATED_ID_DOI_TUONG = 2L;
    private static final Long SMALLER_ID_DOI_TUONG = 1L - 1L;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_THAO_TAC = LocalDate.ofEpochDay(-1L);

    private static final Long DEFAULT_LOAI_DOI_TUONG = 1L;
    private static final Long UPDATED_LOAI_DOI_TUONG = 2L;
    private static final Long SMALLER_LOAI_DOI_TUONG = 1L - 1L;

    private static final String DEFAULT_SO_HS_CV = "AAAAAAAAAA";
    private static final String UPDATED_SO_HS_CV = "BBBBBBBBBB";

    private static final String DEFAULT_SO_CC = "AAAAAAAAAA";
    private static final String UPDATED_SO_CC = "BBBBBBBBBB";

    private static final String DEFAULT_SO_VAO_SO = "AAAAAAAAAA";
    private static final String UPDATED_SO_VAO_SO = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_NGAN_CHAN = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NGAY_BD_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_BD_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_BD_NGAN_CHAN = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NGAY_KT_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KT_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_KT_NGAN_CHAN = LocalDate.ofEpochDay(-1L);

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;
    private static final Long SMALLER_TRANG_THAI = 1L - 1L;

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;
    private static final Long SMALLER_NGUOI_THAO_TAC = 1L - 1L;

    private static final Long DEFAULT_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_LOAI_NGAN_CHAN = 2L;
    private static final Long SMALLER_LOAI_NGAN_CHAN = 1L - 1L;

    private static final LocalDate DEFAULT_NGAY_CONG_VAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CONG_VAN = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_CONG_VAN = LocalDate.ofEpochDay(-1L);

    private static final String ENTITY_API_URL = "/api/chi-tiet-ngan-chans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ChiTietNganChanRepository chiTietNganChanRepository;

    @Autowired
    private ChiTietNganChanMapper chiTietNganChanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChiTietNganChanMockMvc;

    private ChiTietNganChan chiTietNganChan;

    private ChiTietNganChan insertedChiTietNganChan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiTietNganChan createEntity() {
        return new ChiTietNganChan()
            .idDoiTuong(DEFAULT_ID_DOI_TUONG)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .loaiDoiTuong(DEFAULT_LOAI_DOI_TUONG)
            .soHsCv(DEFAULT_SO_HS_CV)
            .soCc(DEFAULT_SO_CC)
            .soVaoSo(DEFAULT_SO_VAO_SO)
            .moTa(DEFAULT_MO_TA)
            .ngayNganChan(DEFAULT_NGAY_NGAN_CHAN)
            .ngayBdNganChan(DEFAULT_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(DEFAULT_NGAY_KT_NGAN_CHAN)
            .trangThai(DEFAULT_TRANG_THAI)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .loaiNganChan(DEFAULT_LOAI_NGAN_CHAN)
            .ngayCongVan(DEFAULT_NGAY_CONG_VAN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiTietNganChan createUpdatedEntity() {
        return new ChiTietNganChan()
            .idDoiTuong(UPDATED_ID_DOI_TUONG)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .loaiDoiTuong(UPDATED_LOAI_DOI_TUONG)
            .soHsCv(UPDATED_SO_HS_CV)
            .soCc(UPDATED_SO_CC)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA)
            .ngayNganChan(UPDATED_NGAY_NGAN_CHAN)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .trangThai(UPDATED_TRANG_THAI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN)
            .ngayCongVan(UPDATED_NGAY_CONG_VAN);
    }

    @BeforeEach
    public void initTest() {
        chiTietNganChan = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedChiTietNganChan != null) {
            chiTietNganChanRepository.delete(insertedChiTietNganChan);
            insertedChiTietNganChan = null;
        }
    }

    @Test
    @Transactional
    void createChiTietNganChan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ChiTietNganChan
        ChiTietNganChanDTO chiTietNganChanDTO = chiTietNganChanMapper.toDto(chiTietNganChan);
        var returnedChiTietNganChanDTO = om.readValue(
            restChiTietNganChanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chiTietNganChanDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ChiTietNganChanDTO.class
        );

        // Validate the ChiTietNganChan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedChiTietNganChan = chiTietNganChanMapper.toEntity(returnedChiTietNganChanDTO);
        assertChiTietNganChanUpdatableFieldsEquals(returnedChiTietNganChan, getPersistedChiTietNganChan(returnedChiTietNganChan));

        insertedChiTietNganChan = returnedChiTietNganChan;
    }

    @Test
    @Transactional
    void createChiTietNganChanWithExistingId() throws Exception {
        // Create the ChiTietNganChan with an existing ID
        chiTietNganChan.setId(1L);
        ChiTietNganChanDTO chiTietNganChanDTO = chiTietNganChanMapper.toDto(chiTietNganChan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChiTietNganChanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chiTietNganChanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChiTietNganChan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllChiTietNganChans() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList
        restChiTietNganChanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chiTietNganChan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDoiTuong").value(hasItem(DEFAULT_ID_DOI_TUONG.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].loaiDoiTuong").value(hasItem(DEFAULT_LOAI_DOI_TUONG.intValue())))
            .andExpect(jsonPath("$.[*].soHsCv").value(hasItem(DEFAULT_SO_HS_CV)))
            .andExpect(jsonPath("$.[*].soCc").value(hasItem(DEFAULT_SO_CC)))
            .andExpect(jsonPath("$.[*].soVaoSo").value(hasItem(DEFAULT_SO_VAO_SO)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].ngayNganChan").value(hasItem(DEFAULT_NGAY_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].ngayBdNganChan").value(hasItem(DEFAULT_NGAY_BD_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].ngayKtNganChan").value(hasItem(DEFAULT_NGAY_KT_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].loaiNganChan").value(hasItem(DEFAULT_LOAI_NGAN_CHAN.intValue())))
            .andExpect(jsonPath("$.[*].ngayCongVan").value(hasItem(DEFAULT_NGAY_CONG_VAN.toString())));
    }

    @Test
    @Transactional
    void getChiTietNganChan() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get the chiTietNganChan
        restChiTietNganChanMockMvc
            .perform(get(ENTITY_API_URL_ID, chiTietNganChan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chiTietNganChan.getId().intValue()))
            .andExpect(jsonPath("$.idDoiTuong").value(DEFAULT_ID_DOI_TUONG.intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.loaiDoiTuong").value(DEFAULT_LOAI_DOI_TUONG.intValue()))
            .andExpect(jsonPath("$.soHsCv").value(DEFAULT_SO_HS_CV))
            .andExpect(jsonPath("$.soCc").value(DEFAULT_SO_CC))
            .andExpect(jsonPath("$.soVaoSo").value(DEFAULT_SO_VAO_SO))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.ngayNganChan").value(DEFAULT_NGAY_NGAN_CHAN.toString()))
            .andExpect(jsonPath("$.ngayBdNganChan").value(DEFAULT_NGAY_BD_NGAN_CHAN.toString()))
            .andExpect(jsonPath("$.ngayKtNganChan").value(DEFAULT_NGAY_KT_NGAN_CHAN.toString()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.loaiNganChan").value(DEFAULT_LOAI_NGAN_CHAN.intValue()))
            .andExpect(jsonPath("$.ngayCongVan").value(DEFAULT_NGAY_CONG_VAN.toString()));
    }

    @Test
    @Transactional
    void getChiTietNganChansByIdFiltering() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        Long id = chiTietNganChan.getId();

        defaultChiTietNganChanFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultChiTietNganChanFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultChiTietNganChanFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByIdDoiTuongIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where idDoiTuong equals to
        defaultChiTietNganChanFiltering("idDoiTuong.equals=" + DEFAULT_ID_DOI_TUONG, "idDoiTuong.equals=" + UPDATED_ID_DOI_TUONG);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByIdDoiTuongIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where idDoiTuong in
        defaultChiTietNganChanFiltering(
            "idDoiTuong.in=" + DEFAULT_ID_DOI_TUONG + "," + UPDATED_ID_DOI_TUONG,
            "idDoiTuong.in=" + UPDATED_ID_DOI_TUONG
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByIdDoiTuongIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where idDoiTuong is not null
        defaultChiTietNganChanFiltering("idDoiTuong.specified=true", "idDoiTuong.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByIdDoiTuongIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where idDoiTuong is greater than or equal to
        defaultChiTietNganChanFiltering(
            "idDoiTuong.greaterThanOrEqual=" + DEFAULT_ID_DOI_TUONG,
            "idDoiTuong.greaterThanOrEqual=" + UPDATED_ID_DOI_TUONG
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByIdDoiTuongIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where idDoiTuong is less than or equal to
        defaultChiTietNganChanFiltering(
            "idDoiTuong.lessThanOrEqual=" + DEFAULT_ID_DOI_TUONG,
            "idDoiTuong.lessThanOrEqual=" + SMALLER_ID_DOI_TUONG
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByIdDoiTuongIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where idDoiTuong is less than
        defaultChiTietNganChanFiltering("idDoiTuong.lessThan=" + UPDATED_ID_DOI_TUONG, "idDoiTuong.lessThan=" + DEFAULT_ID_DOI_TUONG);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByIdDoiTuongIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where idDoiTuong is greater than
        defaultChiTietNganChanFiltering("idDoiTuong.greaterThan=" + SMALLER_ID_DOI_TUONG, "idDoiTuong.greaterThan=" + DEFAULT_ID_DOI_TUONG);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayThaoTac equals to
        defaultChiTietNganChanFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayThaoTac in
        defaultChiTietNganChanFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayThaoTac is not null
        defaultChiTietNganChanFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayThaoTac is greater than or equal to
        defaultChiTietNganChanFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayThaoTac is less than or equal to
        defaultChiTietNganChanFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayThaoTac is less than
        defaultChiTietNganChanFiltering("ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC, "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayThaoTac is greater than
        defaultChiTietNganChanFiltering(
            "ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiDoiTuongIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiDoiTuong equals to
        defaultChiTietNganChanFiltering("loaiDoiTuong.equals=" + DEFAULT_LOAI_DOI_TUONG, "loaiDoiTuong.equals=" + UPDATED_LOAI_DOI_TUONG);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiDoiTuongIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiDoiTuong in
        defaultChiTietNganChanFiltering(
            "loaiDoiTuong.in=" + DEFAULT_LOAI_DOI_TUONG + "," + UPDATED_LOAI_DOI_TUONG,
            "loaiDoiTuong.in=" + UPDATED_LOAI_DOI_TUONG
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiDoiTuongIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiDoiTuong is not null
        defaultChiTietNganChanFiltering("loaiDoiTuong.specified=true", "loaiDoiTuong.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiDoiTuongIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiDoiTuong is greater than or equal to
        defaultChiTietNganChanFiltering(
            "loaiDoiTuong.greaterThanOrEqual=" + DEFAULT_LOAI_DOI_TUONG,
            "loaiDoiTuong.greaterThanOrEqual=" + UPDATED_LOAI_DOI_TUONG
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiDoiTuongIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiDoiTuong is less than or equal to
        defaultChiTietNganChanFiltering(
            "loaiDoiTuong.lessThanOrEqual=" + DEFAULT_LOAI_DOI_TUONG,
            "loaiDoiTuong.lessThanOrEqual=" + SMALLER_LOAI_DOI_TUONG
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiDoiTuongIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiDoiTuong is less than
        defaultChiTietNganChanFiltering(
            "loaiDoiTuong.lessThan=" + UPDATED_LOAI_DOI_TUONG,
            "loaiDoiTuong.lessThan=" + DEFAULT_LOAI_DOI_TUONG
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiDoiTuongIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiDoiTuong is greater than
        defaultChiTietNganChanFiltering(
            "loaiDoiTuong.greaterThan=" + SMALLER_LOAI_DOI_TUONG,
            "loaiDoiTuong.greaterThan=" + DEFAULT_LOAI_DOI_TUONG
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoHsCvIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soHsCv equals to
        defaultChiTietNganChanFiltering("soHsCv.equals=" + DEFAULT_SO_HS_CV, "soHsCv.equals=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoHsCvIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soHsCv in
        defaultChiTietNganChanFiltering("soHsCv.in=" + DEFAULT_SO_HS_CV + "," + UPDATED_SO_HS_CV, "soHsCv.in=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoHsCvIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soHsCv is not null
        defaultChiTietNganChanFiltering("soHsCv.specified=true", "soHsCv.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoHsCvContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soHsCv contains
        defaultChiTietNganChanFiltering("soHsCv.contains=" + DEFAULT_SO_HS_CV, "soHsCv.contains=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoHsCvNotContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soHsCv does not contain
        defaultChiTietNganChanFiltering("soHsCv.doesNotContain=" + UPDATED_SO_HS_CV, "soHsCv.doesNotContain=" + DEFAULT_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoCcIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soCc equals to
        defaultChiTietNganChanFiltering("soCc.equals=" + DEFAULT_SO_CC, "soCc.equals=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoCcIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soCc in
        defaultChiTietNganChanFiltering("soCc.in=" + DEFAULT_SO_CC + "," + UPDATED_SO_CC, "soCc.in=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoCcIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soCc is not null
        defaultChiTietNganChanFiltering("soCc.specified=true", "soCc.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoCcContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soCc contains
        defaultChiTietNganChanFiltering("soCc.contains=" + DEFAULT_SO_CC, "soCc.contains=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoCcNotContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soCc does not contain
        defaultChiTietNganChanFiltering("soCc.doesNotContain=" + UPDATED_SO_CC, "soCc.doesNotContain=" + DEFAULT_SO_CC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoVaoSoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soVaoSo equals to
        defaultChiTietNganChanFiltering("soVaoSo.equals=" + DEFAULT_SO_VAO_SO, "soVaoSo.equals=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoVaoSoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soVaoSo in
        defaultChiTietNganChanFiltering("soVaoSo.in=" + DEFAULT_SO_VAO_SO + "," + UPDATED_SO_VAO_SO, "soVaoSo.in=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoVaoSoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soVaoSo is not null
        defaultChiTietNganChanFiltering("soVaoSo.specified=true", "soVaoSo.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoVaoSoContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soVaoSo contains
        defaultChiTietNganChanFiltering("soVaoSo.contains=" + DEFAULT_SO_VAO_SO, "soVaoSo.contains=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansBySoVaoSoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where soVaoSo does not contain
        defaultChiTietNganChanFiltering("soVaoSo.doesNotContain=" + UPDATED_SO_VAO_SO, "soVaoSo.doesNotContain=" + DEFAULT_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByMoTaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where moTa equals to
        defaultChiTietNganChanFiltering("moTa.equals=" + DEFAULT_MO_TA, "moTa.equals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByMoTaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where moTa in
        defaultChiTietNganChanFiltering("moTa.in=" + DEFAULT_MO_TA + "," + UPDATED_MO_TA, "moTa.in=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByMoTaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where moTa is not null
        defaultChiTietNganChanFiltering("moTa.specified=true", "moTa.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByMoTaContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where moTa contains
        defaultChiTietNganChanFiltering("moTa.contains=" + DEFAULT_MO_TA, "moTa.contains=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByMoTaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where moTa does not contain
        defaultChiTietNganChanFiltering("moTa.doesNotContain=" + UPDATED_MO_TA, "moTa.doesNotContain=" + DEFAULT_MO_TA);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayNganChan equals to
        defaultChiTietNganChanFiltering("ngayNganChan.equals=" + DEFAULT_NGAY_NGAN_CHAN, "ngayNganChan.equals=" + UPDATED_NGAY_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayNganChan in
        defaultChiTietNganChanFiltering(
            "ngayNganChan.in=" + DEFAULT_NGAY_NGAN_CHAN + "," + UPDATED_NGAY_NGAN_CHAN,
            "ngayNganChan.in=" + UPDATED_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayNganChan is not null
        defaultChiTietNganChanFiltering("ngayNganChan.specified=true", "ngayNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayNganChan is greater than or equal to
        defaultChiTietNganChanFiltering(
            "ngayNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_NGAN_CHAN,
            "ngayNganChan.greaterThanOrEqual=" + UPDATED_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayNganChan is less than or equal to
        defaultChiTietNganChanFiltering(
            "ngayNganChan.lessThanOrEqual=" + DEFAULT_NGAY_NGAN_CHAN,
            "ngayNganChan.lessThanOrEqual=" + SMALLER_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayNganChan is less than
        defaultChiTietNganChanFiltering(
            "ngayNganChan.lessThan=" + UPDATED_NGAY_NGAN_CHAN,
            "ngayNganChan.lessThan=" + DEFAULT_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayNganChan is greater than
        defaultChiTietNganChanFiltering(
            "ngayNganChan.greaterThan=" + SMALLER_NGAY_NGAN_CHAN,
            "ngayNganChan.greaterThan=" + DEFAULT_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayBdNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayBdNganChan equals to
        defaultChiTietNganChanFiltering(
            "ngayBdNganChan.equals=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.equals=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayBdNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayBdNganChan in
        defaultChiTietNganChanFiltering(
            "ngayBdNganChan.in=" + DEFAULT_NGAY_BD_NGAN_CHAN + "," + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.in=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayBdNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayBdNganChan is not null
        defaultChiTietNganChanFiltering("ngayBdNganChan.specified=true", "ngayBdNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayBdNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayBdNganChan is greater than or equal to
        defaultChiTietNganChanFiltering(
            "ngayBdNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThanOrEqual=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayBdNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayBdNganChan is less than or equal to
        defaultChiTietNganChanFiltering(
            "ngayBdNganChan.lessThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThanOrEqual=" + SMALLER_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayBdNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayBdNganChan is less than
        defaultChiTietNganChanFiltering(
            "ngayBdNganChan.lessThan=" + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayBdNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayBdNganChan is greater than
        defaultChiTietNganChanFiltering(
            "ngayBdNganChan.greaterThan=" + SMALLER_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayKtNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayKtNganChan equals to
        defaultChiTietNganChanFiltering(
            "ngayKtNganChan.equals=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.equals=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayKtNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayKtNganChan in
        defaultChiTietNganChanFiltering(
            "ngayKtNganChan.in=" + DEFAULT_NGAY_KT_NGAN_CHAN + "," + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.in=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayKtNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayKtNganChan is not null
        defaultChiTietNganChanFiltering("ngayKtNganChan.specified=true", "ngayKtNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayKtNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayKtNganChan is greater than or equal to
        defaultChiTietNganChanFiltering(
            "ngayKtNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThanOrEqual=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayKtNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayKtNganChan is less than or equal to
        defaultChiTietNganChanFiltering(
            "ngayKtNganChan.lessThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThanOrEqual=" + SMALLER_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayKtNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayKtNganChan is less than
        defaultChiTietNganChanFiltering(
            "ngayKtNganChan.lessThan=" + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayKtNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayKtNganChan is greater than
        defaultChiTietNganChanFiltering(
            "ngayKtNganChan.greaterThan=" + SMALLER_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where trangThai equals to
        defaultChiTietNganChanFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where trangThai in
        defaultChiTietNganChanFiltering(
            "trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI,
            "trangThai.in=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where trangThai is not null
        defaultChiTietNganChanFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where trangThai is greater than or equal to
        defaultChiTietNganChanFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where trangThai is less than or equal to
        defaultChiTietNganChanFiltering(
            "trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where trangThai is less than
        defaultChiTietNganChanFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where trangThai is greater than
        defaultChiTietNganChanFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where nguoiThaoTac equals to
        defaultChiTietNganChanFiltering("nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC, "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where nguoiThaoTac in
        defaultChiTietNganChanFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where nguoiThaoTac is not null
        defaultChiTietNganChanFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where nguoiThaoTac is greater than or equal to
        defaultChiTietNganChanFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where nguoiThaoTac is less than or equal to
        defaultChiTietNganChanFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where nguoiThaoTac is less than
        defaultChiTietNganChanFiltering(
            "nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where nguoiThaoTac is greater than
        defaultChiTietNganChanFiltering(
            "nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiNganChan equals to
        defaultChiTietNganChanFiltering("loaiNganChan.equals=" + DEFAULT_LOAI_NGAN_CHAN, "loaiNganChan.equals=" + UPDATED_LOAI_NGAN_CHAN);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiNganChan in
        defaultChiTietNganChanFiltering(
            "loaiNganChan.in=" + DEFAULT_LOAI_NGAN_CHAN + "," + UPDATED_LOAI_NGAN_CHAN,
            "loaiNganChan.in=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiNganChan is not null
        defaultChiTietNganChanFiltering("loaiNganChan.specified=true", "loaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiNganChan is greater than or equal to
        defaultChiTietNganChanFiltering(
            "loaiNganChan.greaterThanOrEqual=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.greaterThanOrEqual=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiNganChan is less than or equal to
        defaultChiTietNganChanFiltering(
            "loaiNganChan.lessThanOrEqual=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.lessThanOrEqual=" + SMALLER_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiNganChan is less than
        defaultChiTietNganChanFiltering(
            "loaiNganChan.lessThan=" + UPDATED_LOAI_NGAN_CHAN,
            "loaiNganChan.lessThan=" + DEFAULT_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where loaiNganChan is greater than
        defaultChiTietNganChanFiltering(
            "loaiNganChan.greaterThan=" + SMALLER_LOAI_NGAN_CHAN,
            "loaiNganChan.greaterThan=" + DEFAULT_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayCongVanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayCongVan equals to
        defaultChiTietNganChanFiltering("ngayCongVan.equals=" + DEFAULT_NGAY_CONG_VAN, "ngayCongVan.equals=" + UPDATED_NGAY_CONG_VAN);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayCongVanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayCongVan in
        defaultChiTietNganChanFiltering(
            "ngayCongVan.in=" + DEFAULT_NGAY_CONG_VAN + "," + UPDATED_NGAY_CONG_VAN,
            "ngayCongVan.in=" + UPDATED_NGAY_CONG_VAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayCongVanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayCongVan is not null
        defaultChiTietNganChanFiltering("ngayCongVan.specified=true", "ngayCongVan.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayCongVanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayCongVan is greater than or equal to
        defaultChiTietNganChanFiltering(
            "ngayCongVan.greaterThanOrEqual=" + DEFAULT_NGAY_CONG_VAN,
            "ngayCongVan.greaterThanOrEqual=" + UPDATED_NGAY_CONG_VAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayCongVanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayCongVan is less than or equal to
        defaultChiTietNganChanFiltering(
            "ngayCongVan.lessThanOrEqual=" + DEFAULT_NGAY_CONG_VAN,
            "ngayCongVan.lessThanOrEqual=" + SMALLER_NGAY_CONG_VAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayCongVanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayCongVan is less than
        defaultChiTietNganChanFiltering("ngayCongVan.lessThan=" + UPDATED_NGAY_CONG_VAN, "ngayCongVan.lessThan=" + DEFAULT_NGAY_CONG_VAN);
    }

    @Test
    @Transactional
    void getAllChiTietNganChansByNgayCongVanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        // Get all the chiTietNganChanList where ngayCongVan is greater than
        defaultChiTietNganChanFiltering(
            "ngayCongVan.greaterThan=" + SMALLER_NGAY_CONG_VAN,
            "ngayCongVan.greaterThan=" + DEFAULT_NGAY_CONG_VAN
        );
    }

    private void defaultChiTietNganChanFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultChiTietNganChanShouldBeFound(shouldBeFound);
        defaultChiTietNganChanShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultChiTietNganChanShouldBeFound(String filter) throws Exception {
        restChiTietNganChanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chiTietNganChan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDoiTuong").value(hasItem(DEFAULT_ID_DOI_TUONG.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].loaiDoiTuong").value(hasItem(DEFAULT_LOAI_DOI_TUONG.intValue())))
            .andExpect(jsonPath("$.[*].soHsCv").value(hasItem(DEFAULT_SO_HS_CV)))
            .andExpect(jsonPath("$.[*].soCc").value(hasItem(DEFAULT_SO_CC)))
            .andExpect(jsonPath("$.[*].soVaoSo").value(hasItem(DEFAULT_SO_VAO_SO)))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].ngayNganChan").value(hasItem(DEFAULT_NGAY_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].ngayBdNganChan").value(hasItem(DEFAULT_NGAY_BD_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].ngayKtNganChan").value(hasItem(DEFAULT_NGAY_KT_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].loaiNganChan").value(hasItem(DEFAULT_LOAI_NGAN_CHAN.intValue())))
            .andExpect(jsonPath("$.[*].ngayCongVan").value(hasItem(DEFAULT_NGAY_CONG_VAN.toString())));

        // Check, that the count call also returns 1
        restChiTietNganChanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultChiTietNganChanShouldNotBeFound(String filter) throws Exception {
        restChiTietNganChanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restChiTietNganChanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingChiTietNganChan() throws Exception {
        // Get the chiTietNganChan
        restChiTietNganChanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingChiTietNganChan() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chiTietNganChan
        ChiTietNganChan updatedChiTietNganChan = chiTietNganChanRepository.findById(chiTietNganChan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedChiTietNganChan are not directly saved in db
        em.detach(updatedChiTietNganChan);
        updatedChiTietNganChan
            .idDoiTuong(UPDATED_ID_DOI_TUONG)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .loaiDoiTuong(UPDATED_LOAI_DOI_TUONG)
            .soHsCv(UPDATED_SO_HS_CV)
            .soCc(UPDATED_SO_CC)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA)
            .ngayNganChan(UPDATED_NGAY_NGAN_CHAN)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .trangThai(UPDATED_TRANG_THAI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN)
            .ngayCongVan(UPDATED_NGAY_CONG_VAN);
        ChiTietNganChanDTO chiTietNganChanDTO = chiTietNganChanMapper.toDto(updatedChiTietNganChan);

        restChiTietNganChanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chiTietNganChanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chiTietNganChanDTO))
            )
            .andExpect(status().isOk());

        // Validate the ChiTietNganChan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedChiTietNganChanToMatchAllProperties(updatedChiTietNganChan);
    }

    @Test
    @Transactional
    void putNonExistingChiTietNganChan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChan.setId(longCount.incrementAndGet());

        // Create the ChiTietNganChan
        ChiTietNganChanDTO chiTietNganChanDTO = chiTietNganChanMapper.toDto(chiTietNganChan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiTietNganChanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chiTietNganChanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chiTietNganChanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietNganChan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchChiTietNganChan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChan.setId(longCount.incrementAndGet());

        // Create the ChiTietNganChan
        ChiTietNganChanDTO chiTietNganChanDTO = chiTietNganChanMapper.toDto(chiTietNganChan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietNganChanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chiTietNganChanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietNganChan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamChiTietNganChan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChan.setId(longCount.incrementAndGet());

        // Create the ChiTietNganChan
        ChiTietNganChanDTO chiTietNganChanDTO = chiTietNganChanMapper.toDto(chiTietNganChan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietNganChanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chiTietNganChanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChiTietNganChan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateChiTietNganChanWithPatch() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chiTietNganChan using partial update
        ChiTietNganChan partialUpdatedChiTietNganChan = new ChiTietNganChan();
        partialUpdatedChiTietNganChan.setId(chiTietNganChan.getId());

        partialUpdatedChiTietNganChan
            .loaiDoiTuong(UPDATED_LOAI_DOI_TUONG)
            .soHsCv(UPDATED_SO_HS_CV)
            .moTa(UPDATED_MO_TA)
            .ngayNganChan(UPDATED_NGAY_NGAN_CHAN)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .trangThai(UPDATED_TRANG_THAI)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN)
            .ngayCongVan(UPDATED_NGAY_CONG_VAN);

        restChiTietNganChanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChiTietNganChan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChiTietNganChan))
            )
            .andExpect(status().isOk());

        // Validate the ChiTietNganChan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChiTietNganChanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedChiTietNganChan, chiTietNganChan),
            getPersistedChiTietNganChan(chiTietNganChan)
        );
    }

    @Test
    @Transactional
    void fullUpdateChiTietNganChanWithPatch() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chiTietNganChan using partial update
        ChiTietNganChan partialUpdatedChiTietNganChan = new ChiTietNganChan();
        partialUpdatedChiTietNganChan.setId(chiTietNganChan.getId());

        partialUpdatedChiTietNganChan
            .idDoiTuong(UPDATED_ID_DOI_TUONG)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .loaiDoiTuong(UPDATED_LOAI_DOI_TUONG)
            .soHsCv(UPDATED_SO_HS_CV)
            .soCc(UPDATED_SO_CC)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA)
            .ngayNganChan(UPDATED_NGAY_NGAN_CHAN)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .trangThai(UPDATED_TRANG_THAI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN)
            .ngayCongVan(UPDATED_NGAY_CONG_VAN);

        restChiTietNganChanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChiTietNganChan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChiTietNganChan))
            )
            .andExpect(status().isOk());

        // Validate the ChiTietNganChan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChiTietNganChanUpdatableFieldsEquals(
            partialUpdatedChiTietNganChan,
            getPersistedChiTietNganChan(partialUpdatedChiTietNganChan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingChiTietNganChan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChan.setId(longCount.incrementAndGet());

        // Create the ChiTietNganChan
        ChiTietNganChanDTO chiTietNganChanDTO = chiTietNganChanMapper.toDto(chiTietNganChan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiTietNganChanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, chiTietNganChanDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chiTietNganChanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietNganChan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchChiTietNganChan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChan.setId(longCount.incrementAndGet());

        // Create the ChiTietNganChan
        ChiTietNganChanDTO chiTietNganChanDTO = chiTietNganChanMapper.toDto(chiTietNganChan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietNganChanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chiTietNganChanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietNganChan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamChiTietNganChan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChan.setId(longCount.incrementAndGet());

        // Create the ChiTietNganChan
        ChiTietNganChanDTO chiTietNganChanDTO = chiTietNganChanMapper.toDto(chiTietNganChan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietNganChanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(chiTietNganChanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChiTietNganChan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteChiTietNganChan() throws Exception {
        // Initialize the database
        insertedChiTietNganChan = chiTietNganChanRepository.saveAndFlush(chiTietNganChan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the chiTietNganChan
        restChiTietNganChanMockMvc
            .perform(delete(ENTITY_API_URL_ID, chiTietNganChan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return chiTietNganChanRepository.count();
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

    protected ChiTietNganChan getPersistedChiTietNganChan(ChiTietNganChan chiTietNganChan) {
        return chiTietNganChanRepository.findById(chiTietNganChan.getId()).orElseThrow();
    }

    protected void assertPersistedChiTietNganChanToMatchAllProperties(ChiTietNganChan expectedChiTietNganChan) {
        assertChiTietNganChanAllPropertiesEquals(expectedChiTietNganChan, getPersistedChiTietNganChan(expectedChiTietNganChan));
    }

    protected void assertPersistedChiTietNganChanToMatchUpdatableProperties(ChiTietNganChan expectedChiTietNganChan) {
        assertChiTietNganChanAllUpdatablePropertiesEquals(expectedChiTietNganChan, getPersistedChiTietNganChan(expectedChiTietNganChan));
    }
}
