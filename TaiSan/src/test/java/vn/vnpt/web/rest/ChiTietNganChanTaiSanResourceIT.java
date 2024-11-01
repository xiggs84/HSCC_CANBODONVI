package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.ChiTietNganChanTaiSanAsserts.*;
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
import vn.vnpt.domain.ChiTietNganChanTaiSan;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.repository.ChiTietNganChanTaiSanRepository;
import vn.vnpt.service.dto.ChiTietNganChanTaiSanDTO;
import vn.vnpt.service.mapper.ChiTietNganChanTaiSanMapper;

/**
 * Integration tests for the {@link ChiTietNganChanTaiSanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChiTietNganChanTaiSanResourceIT {

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_THAO_TAC = LocalDate.ofEpochDay(-1L);

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

    private static final String ENTITY_API_URL = "/api/chi-tiet-ngan-chan-tai-sans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idNganChan}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ChiTietNganChanTaiSanRepository chiTietNganChanTaiSanRepository;

    @Autowired
    private ChiTietNganChanTaiSanMapper chiTietNganChanTaiSanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChiTietNganChanTaiSanMockMvc;

    private ChiTietNganChanTaiSan chiTietNganChanTaiSan;

    private ChiTietNganChanTaiSan insertedChiTietNganChanTaiSan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiTietNganChanTaiSan createEntity() {
        return new ChiTietNganChanTaiSan()
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
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
    public static ChiTietNganChanTaiSan createUpdatedEntity() {
        return new ChiTietNganChanTaiSan()
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
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
        chiTietNganChanTaiSan = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedChiTietNganChanTaiSan != null) {
            chiTietNganChanTaiSanRepository.delete(insertedChiTietNganChanTaiSan);
            insertedChiTietNganChanTaiSan = null;
        }
    }

    @Test
    @Transactional
    void createChiTietNganChanTaiSan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ChiTietNganChanTaiSan
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSan);
        var returnedChiTietNganChanTaiSanDTO = om.readValue(
            restChiTietNganChanTaiSanMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chiTietNganChanTaiSanDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ChiTietNganChanTaiSanDTO.class
        );

        // Validate the ChiTietNganChanTaiSan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedChiTietNganChanTaiSan = chiTietNganChanTaiSanMapper.toEntity(returnedChiTietNganChanTaiSanDTO);
        assertChiTietNganChanTaiSanUpdatableFieldsEquals(
            returnedChiTietNganChanTaiSan,
            getPersistedChiTietNganChanTaiSan(returnedChiTietNganChanTaiSan)
        );

        insertedChiTietNganChanTaiSan = returnedChiTietNganChanTaiSan;
    }

    @Test
    @Transactional
    void createChiTietNganChanTaiSanWithExistingId() throws Exception {
        // Create the ChiTietNganChanTaiSan with an existing ID
        chiTietNganChanTaiSan.setIdNganChan(1L);
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChiTietNganChanTaiSanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chiTietNganChanTaiSanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChiTietNganChanTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSans() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList
        restChiTietNganChanTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idNganChan,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idNganChan").value(hasItem(chiTietNganChanTaiSan.getIdNganChan().intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
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
    void getChiTietNganChanTaiSan() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get the chiTietNganChanTaiSan
        restChiTietNganChanTaiSanMockMvc
            .perform(get(ENTITY_API_URL_ID, chiTietNganChanTaiSan.getIdNganChan()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idNganChan").value(chiTietNganChanTaiSan.getIdNganChan().intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
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
    void getChiTietNganChanTaiSansByIdFiltering() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        Long id = chiTietNganChanTaiSan.getIdNganChan();

        defaultChiTietNganChanTaiSanFiltering("idNganChan.equals=" + id, "idNganChan.notEquals=" + id);

        defaultChiTietNganChanTaiSanFiltering("idNganChan.greaterThanOrEqual=" + id, "idNganChan.greaterThan=" + id);

        defaultChiTietNganChanTaiSanFiltering("idNganChan.lessThanOrEqual=" + id, "idNganChan.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayThaoTac equals to
        defaultChiTietNganChanTaiSanFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayThaoTac in
        defaultChiTietNganChanTaiSanFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayThaoTac is not null
        defaultChiTietNganChanTaiSanFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayThaoTac is greater than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayThaoTac is less than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayThaoTac is less than
        defaultChiTietNganChanTaiSanFiltering(
            "ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayThaoTac is greater than
        defaultChiTietNganChanTaiSanFiltering(
            "ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoHsCvIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soHsCv equals to
        defaultChiTietNganChanTaiSanFiltering("soHsCv.equals=" + DEFAULT_SO_HS_CV, "soHsCv.equals=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoHsCvIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soHsCv in
        defaultChiTietNganChanTaiSanFiltering("soHsCv.in=" + DEFAULT_SO_HS_CV + "," + UPDATED_SO_HS_CV, "soHsCv.in=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoHsCvIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soHsCv is not null
        defaultChiTietNganChanTaiSanFiltering("soHsCv.specified=true", "soHsCv.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoHsCvContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soHsCv contains
        defaultChiTietNganChanTaiSanFiltering("soHsCv.contains=" + DEFAULT_SO_HS_CV, "soHsCv.contains=" + UPDATED_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoHsCvNotContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soHsCv does not contain
        defaultChiTietNganChanTaiSanFiltering("soHsCv.doesNotContain=" + UPDATED_SO_HS_CV, "soHsCv.doesNotContain=" + DEFAULT_SO_HS_CV);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoCcIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soCc equals to
        defaultChiTietNganChanTaiSanFiltering("soCc.equals=" + DEFAULT_SO_CC, "soCc.equals=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoCcIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soCc in
        defaultChiTietNganChanTaiSanFiltering("soCc.in=" + DEFAULT_SO_CC + "," + UPDATED_SO_CC, "soCc.in=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoCcIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soCc is not null
        defaultChiTietNganChanTaiSanFiltering("soCc.specified=true", "soCc.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoCcContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soCc contains
        defaultChiTietNganChanTaiSanFiltering("soCc.contains=" + DEFAULT_SO_CC, "soCc.contains=" + UPDATED_SO_CC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoCcNotContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soCc does not contain
        defaultChiTietNganChanTaiSanFiltering("soCc.doesNotContain=" + UPDATED_SO_CC, "soCc.doesNotContain=" + DEFAULT_SO_CC);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoVaoSoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soVaoSo equals to
        defaultChiTietNganChanTaiSanFiltering("soVaoSo.equals=" + DEFAULT_SO_VAO_SO, "soVaoSo.equals=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoVaoSoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soVaoSo in
        defaultChiTietNganChanTaiSanFiltering(
            "soVaoSo.in=" + DEFAULT_SO_VAO_SO + "," + UPDATED_SO_VAO_SO,
            "soVaoSo.in=" + UPDATED_SO_VAO_SO
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoVaoSoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soVaoSo is not null
        defaultChiTietNganChanTaiSanFiltering("soVaoSo.specified=true", "soVaoSo.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoVaoSoContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soVaoSo contains
        defaultChiTietNganChanTaiSanFiltering("soVaoSo.contains=" + DEFAULT_SO_VAO_SO, "soVaoSo.contains=" + UPDATED_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansBySoVaoSoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where soVaoSo does not contain
        defaultChiTietNganChanTaiSanFiltering("soVaoSo.doesNotContain=" + UPDATED_SO_VAO_SO, "soVaoSo.doesNotContain=" + DEFAULT_SO_VAO_SO);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByMoTaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where moTa equals to
        defaultChiTietNganChanTaiSanFiltering("moTa.equals=" + DEFAULT_MO_TA, "moTa.equals=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByMoTaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where moTa in
        defaultChiTietNganChanTaiSanFiltering("moTa.in=" + DEFAULT_MO_TA + "," + UPDATED_MO_TA, "moTa.in=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByMoTaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where moTa is not null
        defaultChiTietNganChanTaiSanFiltering("moTa.specified=true", "moTa.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByMoTaContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where moTa contains
        defaultChiTietNganChanTaiSanFiltering("moTa.contains=" + DEFAULT_MO_TA, "moTa.contains=" + UPDATED_MO_TA);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByMoTaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where moTa does not contain
        defaultChiTietNganChanTaiSanFiltering("moTa.doesNotContain=" + UPDATED_MO_TA, "moTa.doesNotContain=" + DEFAULT_MO_TA);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayNganChan equals to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayNganChan.equals=" + DEFAULT_NGAY_NGAN_CHAN,
            "ngayNganChan.equals=" + UPDATED_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayNganChan in
        defaultChiTietNganChanTaiSanFiltering(
            "ngayNganChan.in=" + DEFAULT_NGAY_NGAN_CHAN + "," + UPDATED_NGAY_NGAN_CHAN,
            "ngayNganChan.in=" + UPDATED_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayNganChan is not null
        defaultChiTietNganChanTaiSanFiltering("ngayNganChan.specified=true", "ngayNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayNganChan is greater than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_NGAN_CHAN,
            "ngayNganChan.greaterThanOrEqual=" + UPDATED_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayNganChan is less than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayNganChan.lessThanOrEqual=" + DEFAULT_NGAY_NGAN_CHAN,
            "ngayNganChan.lessThanOrEqual=" + SMALLER_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayNganChan is less than
        defaultChiTietNganChanTaiSanFiltering(
            "ngayNganChan.lessThan=" + UPDATED_NGAY_NGAN_CHAN,
            "ngayNganChan.lessThan=" + DEFAULT_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayNganChan is greater than
        defaultChiTietNganChanTaiSanFiltering(
            "ngayNganChan.greaterThan=" + SMALLER_NGAY_NGAN_CHAN,
            "ngayNganChan.greaterThan=" + DEFAULT_NGAY_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayBdNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayBdNganChan equals to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayBdNganChan.equals=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.equals=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayBdNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayBdNganChan in
        defaultChiTietNganChanTaiSanFiltering(
            "ngayBdNganChan.in=" + DEFAULT_NGAY_BD_NGAN_CHAN + "," + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.in=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayBdNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayBdNganChan is not null
        defaultChiTietNganChanTaiSanFiltering("ngayBdNganChan.specified=true", "ngayBdNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayBdNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayBdNganChan is greater than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayBdNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThanOrEqual=" + UPDATED_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayBdNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayBdNganChan is less than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayBdNganChan.lessThanOrEqual=" + DEFAULT_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThanOrEqual=" + SMALLER_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayBdNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayBdNganChan is less than
        defaultChiTietNganChanTaiSanFiltering(
            "ngayBdNganChan.lessThan=" + UPDATED_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.lessThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayBdNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayBdNganChan is greater than
        defaultChiTietNganChanTaiSanFiltering(
            "ngayBdNganChan.greaterThan=" + SMALLER_NGAY_BD_NGAN_CHAN,
            "ngayBdNganChan.greaterThan=" + DEFAULT_NGAY_BD_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayKtNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayKtNganChan equals to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayKtNganChan.equals=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.equals=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayKtNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayKtNganChan in
        defaultChiTietNganChanTaiSanFiltering(
            "ngayKtNganChan.in=" + DEFAULT_NGAY_KT_NGAN_CHAN + "," + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.in=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayKtNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayKtNganChan is not null
        defaultChiTietNganChanTaiSanFiltering("ngayKtNganChan.specified=true", "ngayKtNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayKtNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayKtNganChan is greater than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayKtNganChan.greaterThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThanOrEqual=" + UPDATED_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayKtNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayKtNganChan is less than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayKtNganChan.lessThanOrEqual=" + DEFAULT_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThanOrEqual=" + SMALLER_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayKtNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayKtNganChan is less than
        defaultChiTietNganChanTaiSanFiltering(
            "ngayKtNganChan.lessThan=" + UPDATED_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.lessThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayKtNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayKtNganChan is greater than
        defaultChiTietNganChanTaiSanFiltering(
            "ngayKtNganChan.greaterThan=" + SMALLER_NGAY_KT_NGAN_CHAN,
            "ngayKtNganChan.greaterThan=" + DEFAULT_NGAY_KT_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where trangThai equals to
        defaultChiTietNganChanTaiSanFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where trangThai in
        defaultChiTietNganChanTaiSanFiltering(
            "trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI,
            "trangThai.in=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where trangThai is not null
        defaultChiTietNganChanTaiSanFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where trangThai is greater than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where trangThai is less than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where trangThai is less than
        defaultChiTietNganChanTaiSanFiltering("trangThai.lessThan=" + UPDATED_TRANG_THAI, "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where trangThai is greater than
        defaultChiTietNganChanTaiSanFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNguoiThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where nguoiThaoTac equals to
        defaultChiTietNganChanTaiSanFiltering(
            "nguoiThaoTac.equals=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.equals=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNguoiThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where nguoiThaoTac in
        defaultChiTietNganChanTaiSanFiltering(
            "nguoiThaoTac.in=" + DEFAULT_NGUOI_THAO_TAC + "," + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.in=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNguoiThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where nguoiThaoTac is not null
        defaultChiTietNganChanTaiSanFiltering("nguoiThaoTac.specified=true", "nguoiThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNguoiThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where nguoiThaoTac is greater than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "nguoiThaoTac.greaterThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThanOrEqual=" + UPDATED_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNguoiThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where nguoiThaoTac is less than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "nguoiThaoTac.lessThanOrEqual=" + DEFAULT_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThanOrEqual=" + SMALLER_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNguoiThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where nguoiThaoTac is less than
        defaultChiTietNganChanTaiSanFiltering(
            "nguoiThaoTac.lessThan=" + UPDATED_NGUOI_THAO_TAC,
            "nguoiThaoTac.lessThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNguoiThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where nguoiThaoTac is greater than
        defaultChiTietNganChanTaiSanFiltering(
            "nguoiThaoTac.greaterThan=" + SMALLER_NGUOI_THAO_TAC,
            "nguoiThaoTac.greaterThan=" + DEFAULT_NGUOI_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByLoaiNganChanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where loaiNganChan equals to
        defaultChiTietNganChanTaiSanFiltering(
            "loaiNganChan.equals=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.equals=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByLoaiNganChanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where loaiNganChan in
        defaultChiTietNganChanTaiSanFiltering(
            "loaiNganChan.in=" + DEFAULT_LOAI_NGAN_CHAN + "," + UPDATED_LOAI_NGAN_CHAN,
            "loaiNganChan.in=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByLoaiNganChanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where loaiNganChan is not null
        defaultChiTietNganChanTaiSanFiltering("loaiNganChan.specified=true", "loaiNganChan.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByLoaiNganChanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where loaiNganChan is greater than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "loaiNganChan.greaterThanOrEqual=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.greaterThanOrEqual=" + UPDATED_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByLoaiNganChanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where loaiNganChan is less than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "loaiNganChan.lessThanOrEqual=" + DEFAULT_LOAI_NGAN_CHAN,
            "loaiNganChan.lessThanOrEqual=" + SMALLER_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByLoaiNganChanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where loaiNganChan is less than
        defaultChiTietNganChanTaiSanFiltering(
            "loaiNganChan.lessThan=" + UPDATED_LOAI_NGAN_CHAN,
            "loaiNganChan.lessThan=" + DEFAULT_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByLoaiNganChanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where loaiNganChan is greater than
        defaultChiTietNganChanTaiSanFiltering(
            "loaiNganChan.greaterThan=" + SMALLER_LOAI_NGAN_CHAN,
            "loaiNganChan.greaterThan=" + DEFAULT_LOAI_NGAN_CHAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayCongVanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayCongVan equals to
        defaultChiTietNganChanTaiSanFiltering("ngayCongVan.equals=" + DEFAULT_NGAY_CONG_VAN, "ngayCongVan.equals=" + UPDATED_NGAY_CONG_VAN);
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayCongVanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayCongVan in
        defaultChiTietNganChanTaiSanFiltering(
            "ngayCongVan.in=" + DEFAULT_NGAY_CONG_VAN + "," + UPDATED_NGAY_CONG_VAN,
            "ngayCongVan.in=" + UPDATED_NGAY_CONG_VAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayCongVanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayCongVan is not null
        defaultChiTietNganChanTaiSanFiltering("ngayCongVan.specified=true", "ngayCongVan.specified=false");
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayCongVanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayCongVan is greater than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayCongVan.greaterThanOrEqual=" + DEFAULT_NGAY_CONG_VAN,
            "ngayCongVan.greaterThanOrEqual=" + UPDATED_NGAY_CONG_VAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayCongVanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayCongVan is less than or equal to
        defaultChiTietNganChanTaiSanFiltering(
            "ngayCongVan.lessThanOrEqual=" + DEFAULT_NGAY_CONG_VAN,
            "ngayCongVan.lessThanOrEqual=" + SMALLER_NGAY_CONG_VAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayCongVanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayCongVan is less than
        defaultChiTietNganChanTaiSanFiltering(
            "ngayCongVan.lessThan=" + UPDATED_NGAY_CONG_VAN,
            "ngayCongVan.lessThan=" + DEFAULT_NGAY_CONG_VAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByNgayCongVanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        // Get all the chiTietNganChanTaiSanList where ngayCongVan is greater than
        defaultChiTietNganChanTaiSanFiltering(
            "ngayCongVan.greaterThan=" + SMALLER_NGAY_CONG_VAN,
            "ngayCongVan.greaterThan=" + DEFAULT_NGAY_CONG_VAN
        );
    }

    @Test
    @Transactional
    void getAllChiTietNganChanTaiSansByTaiSanIsEqualToSomething() throws Exception {
        TaiSan taiSan;
        if (TestUtil.findAll(em, TaiSan.class).isEmpty()) {
            chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);
            taiSan = TaiSanResourceIT.createEntity();
        } else {
            taiSan = TestUtil.findAll(em, TaiSan.class).get(0);
        }
        em.persist(taiSan);
        em.flush();
        chiTietNganChanTaiSan.setTaiSan(taiSan);
        chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);
        Long taiSanId = taiSan.getIdTaiSan();
        // Get all the chiTietNganChanTaiSanList where taiSan equals to taiSanId
        defaultChiTietNganChanTaiSanShouldBeFound("taiSanId.equals=" + taiSanId);

        // Get all the chiTietNganChanTaiSanList where taiSan equals to (taiSanId + 1)
        defaultChiTietNganChanTaiSanShouldNotBeFound("taiSanId.equals=" + (taiSanId + 1));
    }

    private void defaultChiTietNganChanTaiSanFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultChiTietNganChanTaiSanShouldBeFound(shouldBeFound);
        defaultChiTietNganChanTaiSanShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultChiTietNganChanTaiSanShouldBeFound(String filter) throws Exception {
        restChiTietNganChanTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idNganChan,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idNganChan").value(hasItem(chiTietNganChanTaiSan.getIdNganChan().intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
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
        restChiTietNganChanTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idNganChan,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultChiTietNganChanTaiSanShouldNotBeFound(String filter) throws Exception {
        restChiTietNganChanTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idNganChan,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restChiTietNganChanTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idNganChan,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingChiTietNganChanTaiSan() throws Exception {
        // Get the chiTietNganChanTaiSan
        restChiTietNganChanTaiSanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingChiTietNganChanTaiSan() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chiTietNganChanTaiSan
        ChiTietNganChanTaiSan updatedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository
            .findById(chiTietNganChanTaiSan.getIdNganChan())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedChiTietNganChanTaiSan are not directly saved in db
        em.detach(updatedChiTietNganChanTaiSan);
        updatedChiTietNganChanTaiSan
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
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
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanMapper.toDto(updatedChiTietNganChanTaiSan);

        restChiTietNganChanTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chiTietNganChanTaiSanDTO.getIdNganChan())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chiTietNganChanTaiSanDTO))
            )
            .andExpect(status().isOk());

        // Validate the ChiTietNganChanTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedChiTietNganChanTaiSanToMatchAllProperties(updatedChiTietNganChanTaiSan);
    }

    @Test
    @Transactional
    void putNonExistingChiTietNganChanTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChanTaiSan.setIdNganChan(longCount.incrementAndGet());

        // Create the ChiTietNganChanTaiSan
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiTietNganChanTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chiTietNganChanTaiSanDTO.getIdNganChan())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chiTietNganChanTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietNganChanTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchChiTietNganChanTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChanTaiSan.setIdNganChan(longCount.incrementAndGet());

        // Create the ChiTietNganChanTaiSan
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietNganChanTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(chiTietNganChanTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietNganChanTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamChiTietNganChanTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChanTaiSan.setIdNganChan(longCount.incrementAndGet());

        // Create the ChiTietNganChanTaiSan
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietNganChanTaiSanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(chiTietNganChanTaiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChiTietNganChanTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateChiTietNganChanTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chiTietNganChanTaiSan using partial update
        ChiTietNganChanTaiSan partialUpdatedChiTietNganChanTaiSan = new ChiTietNganChanTaiSan();
        partialUpdatedChiTietNganChanTaiSan.setIdNganChan(chiTietNganChanTaiSan.getIdNganChan());

        partialUpdatedChiTietNganChanTaiSan
            .soHsCv(UPDATED_SO_HS_CV)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayCongVan(UPDATED_NGAY_CONG_VAN);

        restChiTietNganChanTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChiTietNganChanTaiSan.getIdNganChan())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChiTietNganChanTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the ChiTietNganChanTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChiTietNganChanTaiSanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedChiTietNganChanTaiSan, chiTietNganChanTaiSan),
            getPersistedChiTietNganChanTaiSan(chiTietNganChanTaiSan)
        );
    }

    @Test
    @Transactional
    void fullUpdateChiTietNganChanTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the chiTietNganChanTaiSan using partial update
        ChiTietNganChanTaiSan partialUpdatedChiTietNganChanTaiSan = new ChiTietNganChanTaiSan();
        partialUpdatedChiTietNganChanTaiSan.setIdNganChan(chiTietNganChanTaiSan.getIdNganChan());

        partialUpdatedChiTietNganChanTaiSan
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
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

        restChiTietNganChanTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChiTietNganChanTaiSan.getIdNganChan())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedChiTietNganChanTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the ChiTietNganChanTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertChiTietNganChanTaiSanUpdatableFieldsEquals(
            partialUpdatedChiTietNganChanTaiSan,
            getPersistedChiTietNganChanTaiSan(partialUpdatedChiTietNganChanTaiSan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingChiTietNganChanTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChanTaiSan.setIdNganChan(longCount.incrementAndGet());

        // Create the ChiTietNganChanTaiSan
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChiTietNganChanTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, chiTietNganChanTaiSanDTO.getIdNganChan())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chiTietNganChanTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietNganChanTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchChiTietNganChanTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChanTaiSan.setIdNganChan(longCount.incrementAndGet());

        // Create the ChiTietNganChanTaiSan
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietNganChanTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(chiTietNganChanTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChiTietNganChanTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamChiTietNganChanTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        chiTietNganChanTaiSan.setIdNganChan(longCount.incrementAndGet());

        // Create the ChiTietNganChanTaiSan
        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO = chiTietNganChanTaiSanMapper.toDto(chiTietNganChanTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChiTietNganChanTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(chiTietNganChanTaiSanDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChiTietNganChanTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteChiTietNganChanTaiSan() throws Exception {
        // Initialize the database
        insertedChiTietNganChanTaiSan = chiTietNganChanTaiSanRepository.saveAndFlush(chiTietNganChanTaiSan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the chiTietNganChanTaiSan
        restChiTietNganChanTaiSanMockMvc
            .perform(delete(ENTITY_API_URL_ID, chiTietNganChanTaiSan.getIdNganChan()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return chiTietNganChanTaiSanRepository.count();
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

    protected ChiTietNganChanTaiSan getPersistedChiTietNganChanTaiSan(ChiTietNganChanTaiSan chiTietNganChanTaiSan) {
        return chiTietNganChanTaiSanRepository.findById(chiTietNganChanTaiSan.getIdNganChan()).orElseThrow();
    }

    protected void assertPersistedChiTietNganChanTaiSanToMatchAllProperties(ChiTietNganChanTaiSan expectedChiTietNganChanTaiSan) {
        assertChiTietNganChanTaiSanAllPropertiesEquals(
            expectedChiTietNganChanTaiSan,
            getPersistedChiTietNganChanTaiSan(expectedChiTietNganChanTaiSan)
        );
    }

    protected void assertPersistedChiTietNganChanTaiSanToMatchUpdatableProperties(ChiTietNganChanTaiSan expectedChiTietNganChanTaiSan) {
        assertChiTietNganChanTaiSanAllUpdatablePropertiesEquals(
            expectedChiTietNganChanTaiSan,
            getPersistedChiTietNganChanTaiSan(expectedChiTietNganChanTaiSan)
        );
    }
}
