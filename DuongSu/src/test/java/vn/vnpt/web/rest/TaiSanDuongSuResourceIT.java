package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.TaiSanDuongSuAsserts.*;
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
import vn.vnpt.domain.TaiSanDuongSu;
import vn.vnpt.repository.TaiSanDuongSuRepository;
import vn.vnpt.service.dto.TaiSanDuongSuDTO;
import vn.vnpt.service.mapper.TaiSanDuongSuMapper;

/**
 * Integration tests for the {@link TaiSanDuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaiSanDuongSuResourceIT {

    private static final Long DEFAULT_ID_TAI_SAN = 1L;
    private static final Long UPDATED_ID_TAI_SAN = 2L;
    private static final Long SMALLER_ID_TAI_SAN = 1L - 1L;

    private static final Integer DEFAULT_TRANG_THAI = 0;
    private static final Integer UPDATED_TRANG_THAI = 1;
    private static final Integer SMALLER_TRANG_THAI = 0 - 1;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_THAO_TAC = LocalDate.ofEpochDay(-1L);

    private static final Long DEFAULT_ID_HOP_DONG = 1L;
    private static final Long UPDATED_ID_HOP_DONG = 2L;
    private static final Long SMALLER_ID_HOP_DONG = 1L - 1L;

    private static final Long DEFAULT_ID_LOAI_HOP_DONG = 1L;
    private static final Long UPDATED_ID_LOAI_HOP_DONG = 2L;
    private static final Long SMALLER_ID_LOAI_HOP_DONG = 1L - 1L;

    private static final Long DEFAULT_ID_CHUNG_THUC = 1L;
    private static final Long UPDATED_ID_CHUNG_THUC = 2L;
    private static final Long SMALLER_ID_CHUNG_THUC = 1L - 1L;

    private static final String ENTITY_API_URL = "/api/tai-san-duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaiSanDuongSuRepository taiSanDuongSuRepository;

    @Autowired
    private TaiSanDuongSuMapper taiSanDuongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaiSanDuongSuMockMvc;

    private TaiSanDuongSu taiSanDuongSu;

    private TaiSanDuongSu insertedTaiSanDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiSanDuongSu createEntity() {
        return new TaiSanDuongSu()
            .idTaiSan(DEFAULT_ID_TAI_SAN)
            .trangThai(DEFAULT_TRANG_THAI)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .idHopDong(DEFAULT_ID_HOP_DONG)
            .idLoaiHopDong(DEFAULT_ID_LOAI_HOP_DONG)
            .idChungThuc(DEFAULT_ID_CHUNG_THUC);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaiSanDuongSu createUpdatedEntity() {
        return new TaiSanDuongSu()
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idHopDong(UPDATED_ID_HOP_DONG)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idChungThuc(UPDATED_ID_CHUNG_THUC);
    }

    @BeforeEach
    public void initTest() {
        taiSanDuongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedTaiSanDuongSu != null) {
            taiSanDuongSuRepository.delete(insertedTaiSanDuongSu);
            insertedTaiSanDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);
        var returnedTaiSanDuongSuDTO = om.readValue(
            restTaiSanDuongSuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDuongSuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TaiSanDuongSuDTO.class
        );

        // Validate the TaiSanDuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTaiSanDuongSu = taiSanDuongSuMapper.toEntity(returnedTaiSanDuongSuDTO);
        assertTaiSanDuongSuUpdatableFieldsEquals(returnedTaiSanDuongSu, getPersistedTaiSanDuongSu(returnedTaiSanDuongSu));

        insertedTaiSanDuongSu = returnedTaiSanDuongSu;
    }

    @Test
    @Transactional
    void createTaiSanDuongSuWithExistingId() throws Exception {
        // Create the TaiSanDuongSu with an existing ID
        taiSanDuongSu.setId(1L);
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaiSanDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDuongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSus() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList
        restTaiSanDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiSanDuongSu.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].idHopDong").value(hasItem(DEFAULT_ID_HOP_DONG.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiHopDong").value(hasItem(DEFAULT_ID_LOAI_HOP_DONG.intValue())))
            .andExpect(jsonPath("$.[*].idChungThuc").value(hasItem(DEFAULT_ID_CHUNG_THUC.intValue())));
    }

    @Test
    @Transactional
    void getTaiSanDuongSu() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get the taiSanDuongSu
        restTaiSanDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, taiSanDuongSu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taiSanDuongSu.getId().intValue()))
            .andExpect(jsonPath("$.idTaiSan").value(DEFAULT_ID_TAI_SAN.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.idHopDong").value(DEFAULT_ID_HOP_DONG.intValue()))
            .andExpect(jsonPath("$.idLoaiHopDong").value(DEFAULT_ID_LOAI_HOP_DONG.intValue()))
            .andExpect(jsonPath("$.idChungThuc").value(DEFAULT_ID_CHUNG_THUC.intValue()));
    }

    @Test
    @Transactional
    void getTaiSanDuongSusByIdFiltering() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        Long id = taiSanDuongSu.getId();

        defaultTaiSanDuongSuFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultTaiSanDuongSuFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultTaiSanDuongSuFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idTaiSan equals to
        defaultTaiSanDuongSuFiltering("idTaiSan.equals=" + DEFAULT_ID_TAI_SAN, "idTaiSan.equals=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idTaiSan in
        defaultTaiSanDuongSuFiltering("idTaiSan.in=" + DEFAULT_ID_TAI_SAN + "," + UPDATED_ID_TAI_SAN, "idTaiSan.in=" + UPDATED_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idTaiSan is not null
        defaultTaiSanDuongSuFiltering("idTaiSan.specified=true", "idTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdTaiSanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idTaiSan is greater than or equal to
        defaultTaiSanDuongSuFiltering(
            "idTaiSan.greaterThanOrEqual=" + DEFAULT_ID_TAI_SAN,
            "idTaiSan.greaterThanOrEqual=" + UPDATED_ID_TAI_SAN
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdTaiSanIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idTaiSan is less than or equal to
        defaultTaiSanDuongSuFiltering("idTaiSan.lessThanOrEqual=" + DEFAULT_ID_TAI_SAN, "idTaiSan.lessThanOrEqual=" + SMALLER_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdTaiSanIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idTaiSan is less than
        defaultTaiSanDuongSuFiltering("idTaiSan.lessThan=" + UPDATED_ID_TAI_SAN, "idTaiSan.lessThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdTaiSanIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idTaiSan is greater than
        defaultTaiSanDuongSuFiltering("idTaiSan.greaterThan=" + SMALLER_ID_TAI_SAN, "idTaiSan.greaterThan=" + DEFAULT_ID_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where trangThai equals to
        defaultTaiSanDuongSuFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where trangThai in
        defaultTaiSanDuongSuFiltering(
            "trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI,
            "trangThai.in=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where trangThai is not null
        defaultTaiSanDuongSuFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where trangThai is greater than or equal to
        defaultTaiSanDuongSuFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + (DEFAULT_TRANG_THAI + 1)
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where trangThai is less than or equal to
        defaultTaiSanDuongSuFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where trangThai is less than
        defaultTaiSanDuongSuFiltering("trangThai.lessThan=" + (DEFAULT_TRANG_THAI + 1), "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where trangThai is greater than
        defaultTaiSanDuongSuFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByNgayThaoTacIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where ngayThaoTac equals to
        defaultTaiSanDuongSuFiltering("ngayThaoTac.equals=" + DEFAULT_NGAY_THAO_TAC, "ngayThaoTac.equals=" + UPDATED_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByNgayThaoTacIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where ngayThaoTac in
        defaultTaiSanDuongSuFiltering(
            "ngayThaoTac.in=" + DEFAULT_NGAY_THAO_TAC + "," + UPDATED_NGAY_THAO_TAC,
            "ngayThaoTac.in=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByNgayThaoTacIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where ngayThaoTac is not null
        defaultTaiSanDuongSuFiltering("ngayThaoTac.specified=true", "ngayThaoTac.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByNgayThaoTacIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where ngayThaoTac is greater than or equal to
        defaultTaiSanDuongSuFiltering(
            "ngayThaoTac.greaterThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThanOrEqual=" + UPDATED_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByNgayThaoTacIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where ngayThaoTac is less than or equal to
        defaultTaiSanDuongSuFiltering(
            "ngayThaoTac.lessThanOrEqual=" + DEFAULT_NGAY_THAO_TAC,
            "ngayThaoTac.lessThanOrEqual=" + SMALLER_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByNgayThaoTacIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where ngayThaoTac is less than
        defaultTaiSanDuongSuFiltering("ngayThaoTac.lessThan=" + UPDATED_NGAY_THAO_TAC, "ngayThaoTac.lessThan=" + DEFAULT_NGAY_THAO_TAC);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByNgayThaoTacIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where ngayThaoTac is greater than
        defaultTaiSanDuongSuFiltering(
            "ngayThaoTac.greaterThan=" + SMALLER_NGAY_THAO_TAC,
            "ngayThaoTac.greaterThan=" + DEFAULT_NGAY_THAO_TAC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdHopDongIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idHopDong equals to
        defaultTaiSanDuongSuFiltering("idHopDong.equals=" + DEFAULT_ID_HOP_DONG, "idHopDong.equals=" + UPDATED_ID_HOP_DONG);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdHopDongIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idHopDong in
        defaultTaiSanDuongSuFiltering(
            "idHopDong.in=" + DEFAULT_ID_HOP_DONG + "," + UPDATED_ID_HOP_DONG,
            "idHopDong.in=" + UPDATED_ID_HOP_DONG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdHopDongIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idHopDong is not null
        defaultTaiSanDuongSuFiltering("idHopDong.specified=true", "idHopDong.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdHopDongIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idHopDong is greater than or equal to
        defaultTaiSanDuongSuFiltering(
            "idHopDong.greaterThanOrEqual=" + DEFAULT_ID_HOP_DONG,
            "idHopDong.greaterThanOrEqual=" + UPDATED_ID_HOP_DONG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdHopDongIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idHopDong is less than or equal to
        defaultTaiSanDuongSuFiltering(
            "idHopDong.lessThanOrEqual=" + DEFAULT_ID_HOP_DONG,
            "idHopDong.lessThanOrEqual=" + SMALLER_ID_HOP_DONG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdHopDongIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idHopDong is less than
        defaultTaiSanDuongSuFiltering("idHopDong.lessThan=" + UPDATED_ID_HOP_DONG, "idHopDong.lessThan=" + DEFAULT_ID_HOP_DONG);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdHopDongIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idHopDong is greater than
        defaultTaiSanDuongSuFiltering("idHopDong.greaterThan=" + SMALLER_ID_HOP_DONG, "idHopDong.greaterThan=" + DEFAULT_ID_HOP_DONG);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdLoaiHopDongIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idLoaiHopDong equals to
        defaultTaiSanDuongSuFiltering(
            "idLoaiHopDong.equals=" + DEFAULT_ID_LOAI_HOP_DONG,
            "idLoaiHopDong.equals=" + UPDATED_ID_LOAI_HOP_DONG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdLoaiHopDongIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idLoaiHopDong in
        defaultTaiSanDuongSuFiltering(
            "idLoaiHopDong.in=" + DEFAULT_ID_LOAI_HOP_DONG + "," + UPDATED_ID_LOAI_HOP_DONG,
            "idLoaiHopDong.in=" + UPDATED_ID_LOAI_HOP_DONG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdLoaiHopDongIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idLoaiHopDong is not null
        defaultTaiSanDuongSuFiltering("idLoaiHopDong.specified=true", "idLoaiHopDong.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdLoaiHopDongIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idLoaiHopDong is greater than or equal to
        defaultTaiSanDuongSuFiltering(
            "idLoaiHopDong.greaterThanOrEqual=" + DEFAULT_ID_LOAI_HOP_DONG,
            "idLoaiHopDong.greaterThanOrEqual=" + UPDATED_ID_LOAI_HOP_DONG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdLoaiHopDongIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idLoaiHopDong is less than or equal to
        defaultTaiSanDuongSuFiltering(
            "idLoaiHopDong.lessThanOrEqual=" + DEFAULT_ID_LOAI_HOP_DONG,
            "idLoaiHopDong.lessThanOrEqual=" + SMALLER_ID_LOAI_HOP_DONG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdLoaiHopDongIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idLoaiHopDong is less than
        defaultTaiSanDuongSuFiltering(
            "idLoaiHopDong.lessThan=" + UPDATED_ID_LOAI_HOP_DONG,
            "idLoaiHopDong.lessThan=" + DEFAULT_ID_LOAI_HOP_DONG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdLoaiHopDongIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idLoaiHopDong is greater than
        defaultTaiSanDuongSuFiltering(
            "idLoaiHopDong.greaterThan=" + SMALLER_ID_LOAI_HOP_DONG,
            "idLoaiHopDong.greaterThan=" + DEFAULT_ID_LOAI_HOP_DONG
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdChungThucIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idChungThuc equals to
        defaultTaiSanDuongSuFiltering("idChungThuc.equals=" + DEFAULT_ID_CHUNG_THUC, "idChungThuc.equals=" + UPDATED_ID_CHUNG_THUC);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdChungThucIsInShouldWork() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idChungThuc in
        defaultTaiSanDuongSuFiltering(
            "idChungThuc.in=" + DEFAULT_ID_CHUNG_THUC + "," + UPDATED_ID_CHUNG_THUC,
            "idChungThuc.in=" + UPDATED_ID_CHUNG_THUC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdChungThucIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idChungThuc is not null
        defaultTaiSanDuongSuFiltering("idChungThuc.specified=true", "idChungThuc.specified=false");
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdChungThucIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idChungThuc is greater than or equal to
        defaultTaiSanDuongSuFiltering(
            "idChungThuc.greaterThanOrEqual=" + DEFAULT_ID_CHUNG_THUC,
            "idChungThuc.greaterThanOrEqual=" + UPDATED_ID_CHUNG_THUC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdChungThucIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idChungThuc is less than or equal to
        defaultTaiSanDuongSuFiltering(
            "idChungThuc.lessThanOrEqual=" + DEFAULT_ID_CHUNG_THUC,
            "idChungThuc.lessThanOrEqual=" + SMALLER_ID_CHUNG_THUC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdChungThucIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idChungThuc is less than
        defaultTaiSanDuongSuFiltering("idChungThuc.lessThan=" + UPDATED_ID_CHUNG_THUC, "idChungThuc.lessThan=" + DEFAULT_ID_CHUNG_THUC);
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByIdChungThucIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        // Get all the taiSanDuongSuList where idChungThuc is greater than
        defaultTaiSanDuongSuFiltering(
            "idChungThuc.greaterThan=" + SMALLER_ID_CHUNG_THUC,
            "idChungThuc.greaterThan=" + DEFAULT_ID_CHUNG_THUC
        );
    }

    @Test
    @Transactional
    void getAllTaiSanDuongSusByDuongSuIsEqualToSomething() throws Exception {
        DuongSu duongSu;
        if (TestUtil.findAll(em, DuongSu.class).isEmpty()) {
            taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);
            duongSu = DuongSuResourceIT.createEntity();
        } else {
            duongSu = TestUtil.findAll(em, DuongSu.class).get(0);
        }
        em.persist(duongSu);
        em.flush();
        taiSanDuongSu.setDuongSu(duongSu);
        taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);
        Long duongSuId = duongSu.getIdDuongSu();
        // Get all the taiSanDuongSuList where duongSu equals to duongSuId
        defaultTaiSanDuongSuShouldBeFound("duongSuId.equals=" + duongSuId);

        // Get all the taiSanDuongSuList where duongSu equals to (duongSuId + 1)
        defaultTaiSanDuongSuShouldNotBeFound("duongSuId.equals=" + (duongSuId + 1));
    }

    private void defaultTaiSanDuongSuFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultTaiSanDuongSuShouldBeFound(shouldBeFound);
        defaultTaiSanDuongSuShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTaiSanDuongSuShouldBeFound(String filter) throws Exception {
        restTaiSanDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taiSanDuongSu.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(DEFAULT_ID_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].idHopDong").value(hasItem(DEFAULT_ID_HOP_DONG.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiHopDong").value(hasItem(DEFAULT_ID_LOAI_HOP_DONG.intValue())))
            .andExpect(jsonPath("$.[*].idChungThuc").value(hasItem(DEFAULT_ID_CHUNG_THUC.intValue())));

        // Check, that the count call also returns 1
        restTaiSanDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTaiSanDuongSuShouldNotBeFound(String filter) throws Exception {
        restTaiSanDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTaiSanDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingTaiSanDuongSu() throws Exception {
        // Get the taiSanDuongSu
        restTaiSanDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaiSanDuongSu() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDuongSu
        TaiSanDuongSu updatedTaiSanDuongSu = taiSanDuongSuRepository.findById(taiSanDuongSu.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTaiSanDuongSu are not directly saved in db
        em.detach(updatedTaiSanDuongSu);
        updatedTaiSanDuongSu
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idHopDong(UPDATED_ID_HOP_DONG)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idChungThuc(UPDATED_ID_CHUNG_THUC);
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(updatedTaiSanDuongSu);

        restTaiSanDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taiSanDuongSuDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDuongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTaiSanDuongSuToMatchAllProperties(updatedTaiSanDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, taiSanDuongSuDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taiSanDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taiSanDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaiSanDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDuongSu using partial update
        TaiSanDuongSu partialUpdatedTaiSanDuongSu = new TaiSanDuongSu();
        partialUpdatedTaiSanDuongSu.setId(taiSanDuongSu.getId());

        partialUpdatedTaiSanDuongSu
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idHopDong(UPDATED_ID_HOP_DONG)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG);

        restTaiSanDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaiSanDuongSu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaiSanDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaiSanDuongSuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTaiSanDuongSu, taiSanDuongSu),
            getPersistedTaiSanDuongSu(taiSanDuongSu)
        );
    }

    @Test
    @Transactional
    void fullUpdateTaiSanDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taiSanDuongSu using partial update
        TaiSanDuongSu partialUpdatedTaiSanDuongSu = new TaiSanDuongSu();
        partialUpdatedTaiSanDuongSu.setId(taiSanDuongSu.getId());

        partialUpdatedTaiSanDuongSu
            .idTaiSan(UPDATED_ID_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idHopDong(UPDATED_ID_HOP_DONG)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idChungThuc(UPDATED_ID_CHUNG_THUC);

        restTaiSanDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaiSanDuongSu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaiSanDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the TaiSanDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaiSanDuongSuUpdatableFieldsEquals(partialUpdatedTaiSanDuongSu, getPersistedTaiSanDuongSu(partialUpdatedTaiSanDuongSu));
    }

    @Test
    @Transactional
    void patchNonExistingTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taiSanDuongSuDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taiSanDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taiSanDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaiSanDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taiSanDuongSu.setId(longCount.incrementAndGet());

        // Create the TaiSanDuongSu
        TaiSanDuongSuDTO taiSanDuongSuDTO = taiSanDuongSuMapper.toDto(taiSanDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaiSanDuongSuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(taiSanDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TaiSanDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaiSanDuongSu() throws Exception {
        // Initialize the database
        insertedTaiSanDuongSu = taiSanDuongSuRepository.saveAndFlush(taiSanDuongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the taiSanDuongSu
        restTaiSanDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, taiSanDuongSu.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return taiSanDuongSuRepository.count();
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

    protected TaiSanDuongSu getPersistedTaiSanDuongSu(TaiSanDuongSu taiSanDuongSu) {
        return taiSanDuongSuRepository.findById(taiSanDuongSu.getId()).orElseThrow();
    }

    protected void assertPersistedTaiSanDuongSuToMatchAllProperties(TaiSanDuongSu expectedTaiSanDuongSu) {
        assertTaiSanDuongSuAllPropertiesEquals(expectedTaiSanDuongSu, getPersistedTaiSanDuongSu(expectedTaiSanDuongSu));
    }

    protected void assertPersistedTaiSanDuongSuToMatchUpdatableProperties(TaiSanDuongSu expectedTaiSanDuongSu) {
        assertTaiSanDuongSuAllUpdatablePropertiesEquals(expectedTaiSanDuongSu, getPersistedTaiSanDuongSu(expectedTaiSanDuongSu));
    }
}
