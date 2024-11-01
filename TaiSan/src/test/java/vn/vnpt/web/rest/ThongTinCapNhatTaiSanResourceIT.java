package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.ThongTinCapNhatTaiSanAsserts.*;
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
import vn.vnpt.domain.DanhMucLoaiTaiSan;
import vn.vnpt.domain.TaiSan;
import vn.vnpt.domain.ThongTinCapNhatTaiSan;
import vn.vnpt.repository.ThongTinCapNhatTaiSanRepository;
import vn.vnpt.service.dto.ThongTinCapNhatTaiSanDTO;
import vn.vnpt.service.mapper.ThongTinCapNhatTaiSanMapper;

/**
 * Integration tests for the {@link ThongTinCapNhatTaiSanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ThongTinCapNhatTaiSanResourceIT {

    private static final String DEFAULT_TEN_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TAI_SAN = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_TAI_SAN = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_CAP_NHAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CAP_NHAT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_CAP_NHAT = LocalDate.ofEpochDay(-1L);

    private static final String ENTITY_API_URL = "/api/thong-tin-cap-nhat-tai-sans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCapNhat}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ThongTinCapNhatTaiSanRepository thongTinCapNhatTaiSanRepository;

    @Autowired
    private ThongTinCapNhatTaiSanMapper thongTinCapNhatTaiSanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restThongTinCapNhatTaiSanMockMvc;

    private ThongTinCapNhatTaiSan thongTinCapNhatTaiSan;

    private ThongTinCapNhatTaiSan insertedThongTinCapNhatTaiSan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThongTinCapNhatTaiSan createEntity() {
        return new ThongTinCapNhatTaiSan()
            .tenTaiSan(DEFAULT_TEN_TAI_SAN)
            .thongTinTaiSan(DEFAULT_THONG_TIN_TAI_SAN)
            .ngayCapNhat(DEFAULT_NGAY_CAP_NHAT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThongTinCapNhatTaiSan createUpdatedEntity() {
        return new ThongTinCapNhatTaiSan()
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .thongTinTaiSan(UPDATED_THONG_TIN_TAI_SAN)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);
    }

    @BeforeEach
    public void initTest() {
        thongTinCapNhatTaiSan = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedThongTinCapNhatTaiSan != null) {
            thongTinCapNhatTaiSanRepository.delete(insertedThongTinCapNhatTaiSan);
            insertedThongTinCapNhatTaiSan = null;
        }
    }

    @Test
    @Transactional
    void createThongTinCapNhatTaiSan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ThongTinCapNhatTaiSan
        ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSan);
        var returnedThongTinCapNhatTaiSanDTO = om.readValue(
            restThongTinCapNhatTaiSanMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thongTinCapNhatTaiSanDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ThongTinCapNhatTaiSanDTO.class
        );

        // Validate the ThongTinCapNhatTaiSan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanMapper.toEntity(returnedThongTinCapNhatTaiSanDTO);
        assertThongTinCapNhatTaiSanUpdatableFieldsEquals(
            returnedThongTinCapNhatTaiSan,
            getPersistedThongTinCapNhatTaiSan(returnedThongTinCapNhatTaiSan)
        );

        insertedThongTinCapNhatTaiSan = returnedThongTinCapNhatTaiSan;
    }

    @Test
    @Transactional
    void createThongTinCapNhatTaiSanWithExistingId() throws Exception {
        // Create the ThongTinCapNhatTaiSan with an existing ID
        thongTinCapNhatTaiSan.setIdCapNhat(1L);
        ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restThongTinCapNhatTaiSanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thongTinCapNhatTaiSanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongTinCapNhatTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSans() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList
        restThongTinCapNhatTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCapNhat,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCapNhat").value(hasItem(thongTinCapNhatTaiSan.getIdCapNhat().intValue())))
            .andExpect(jsonPath("$.[*].tenTaiSan").value(hasItem(DEFAULT_TEN_TAI_SAN)))
            .andExpect(jsonPath("$.[*].thongTinTaiSan").value(hasItem(DEFAULT_THONG_TIN_TAI_SAN.toString())))
            .andExpect(jsonPath("$.[*].ngayCapNhat").value(hasItem(DEFAULT_NGAY_CAP_NHAT.toString())));
    }

    @Test
    @Transactional
    void getThongTinCapNhatTaiSan() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get the thongTinCapNhatTaiSan
        restThongTinCapNhatTaiSanMockMvc
            .perform(get(ENTITY_API_URL_ID, thongTinCapNhatTaiSan.getIdCapNhat()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCapNhat").value(thongTinCapNhatTaiSan.getIdCapNhat().intValue()))
            .andExpect(jsonPath("$.tenTaiSan").value(DEFAULT_TEN_TAI_SAN))
            .andExpect(jsonPath("$.thongTinTaiSan").value(DEFAULT_THONG_TIN_TAI_SAN.toString()))
            .andExpect(jsonPath("$.ngayCapNhat").value(DEFAULT_NGAY_CAP_NHAT.toString()));
    }

    @Test
    @Transactional
    void getThongTinCapNhatTaiSansByIdFiltering() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        Long id = thongTinCapNhatTaiSan.getIdCapNhat();

        defaultThongTinCapNhatTaiSanFiltering("idCapNhat.equals=" + id, "idCapNhat.notEquals=" + id);

        defaultThongTinCapNhatTaiSanFiltering("idCapNhat.greaterThanOrEqual=" + id, "idCapNhat.greaterThan=" + id);

        defaultThongTinCapNhatTaiSanFiltering("idCapNhat.lessThanOrEqual=" + id, "idCapNhat.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByTenTaiSanIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where tenTaiSan equals to
        defaultThongTinCapNhatTaiSanFiltering("tenTaiSan.equals=" + DEFAULT_TEN_TAI_SAN, "tenTaiSan.equals=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByTenTaiSanIsInShouldWork() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where tenTaiSan in
        defaultThongTinCapNhatTaiSanFiltering(
            "tenTaiSan.in=" + DEFAULT_TEN_TAI_SAN + "," + UPDATED_TEN_TAI_SAN,
            "tenTaiSan.in=" + UPDATED_TEN_TAI_SAN
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByTenTaiSanIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where tenTaiSan is not null
        defaultThongTinCapNhatTaiSanFiltering("tenTaiSan.specified=true", "tenTaiSan.specified=false");
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByTenTaiSanContainsSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where tenTaiSan contains
        defaultThongTinCapNhatTaiSanFiltering("tenTaiSan.contains=" + DEFAULT_TEN_TAI_SAN, "tenTaiSan.contains=" + UPDATED_TEN_TAI_SAN);
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByTenTaiSanNotContainsSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where tenTaiSan does not contain
        defaultThongTinCapNhatTaiSanFiltering(
            "tenTaiSan.doesNotContain=" + UPDATED_TEN_TAI_SAN,
            "tenTaiSan.doesNotContain=" + DEFAULT_TEN_TAI_SAN
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByNgayCapNhatIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where ngayCapNhat equals to
        defaultThongTinCapNhatTaiSanFiltering("ngayCapNhat.equals=" + DEFAULT_NGAY_CAP_NHAT, "ngayCapNhat.equals=" + UPDATED_NGAY_CAP_NHAT);
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByNgayCapNhatIsInShouldWork() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where ngayCapNhat in
        defaultThongTinCapNhatTaiSanFiltering(
            "ngayCapNhat.in=" + DEFAULT_NGAY_CAP_NHAT + "," + UPDATED_NGAY_CAP_NHAT,
            "ngayCapNhat.in=" + UPDATED_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByNgayCapNhatIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where ngayCapNhat is not null
        defaultThongTinCapNhatTaiSanFiltering("ngayCapNhat.specified=true", "ngayCapNhat.specified=false");
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByNgayCapNhatIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where ngayCapNhat is greater than or equal to
        defaultThongTinCapNhatTaiSanFiltering(
            "ngayCapNhat.greaterThanOrEqual=" + DEFAULT_NGAY_CAP_NHAT,
            "ngayCapNhat.greaterThanOrEqual=" + UPDATED_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByNgayCapNhatIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where ngayCapNhat is less than or equal to
        defaultThongTinCapNhatTaiSanFiltering(
            "ngayCapNhat.lessThanOrEqual=" + DEFAULT_NGAY_CAP_NHAT,
            "ngayCapNhat.lessThanOrEqual=" + SMALLER_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByNgayCapNhatIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where ngayCapNhat is less than
        defaultThongTinCapNhatTaiSanFiltering(
            "ngayCapNhat.lessThan=" + UPDATED_NGAY_CAP_NHAT,
            "ngayCapNhat.lessThan=" + DEFAULT_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByNgayCapNhatIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        // Get all the thongTinCapNhatTaiSanList where ngayCapNhat is greater than
        defaultThongTinCapNhatTaiSanFiltering(
            "ngayCapNhat.greaterThan=" + SMALLER_NGAY_CAP_NHAT,
            "ngayCapNhat.greaterThan=" + DEFAULT_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByTaiSanIsEqualToSomething() throws Exception {
        TaiSan taiSan;
        if (TestUtil.findAll(em, TaiSan.class).isEmpty()) {
            thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);
            taiSan = TaiSanResourceIT.createEntity();
        } else {
            taiSan = TestUtil.findAll(em, TaiSan.class).get(0);
        }
        em.persist(taiSan);
        em.flush();
        thongTinCapNhatTaiSan.setTaiSan(taiSan);
        thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);
        Long taiSanId = taiSan.getIdTaiSan();
        // Get all the thongTinCapNhatTaiSanList where taiSan equals to taiSanId
        defaultThongTinCapNhatTaiSanShouldBeFound("taiSanId.equals=" + taiSanId);

        // Get all the thongTinCapNhatTaiSanList where taiSan equals to (taiSanId + 1)
        defaultThongTinCapNhatTaiSanShouldNotBeFound("taiSanId.equals=" + (taiSanId + 1));
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatTaiSansByDanhMucLoaiTaiSanIsEqualToSomething() throws Exception {
        DanhMucLoaiTaiSan danhMucLoaiTaiSan;
        if (TestUtil.findAll(em, DanhMucLoaiTaiSan.class).isEmpty()) {
            thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);
            danhMucLoaiTaiSan = DanhMucLoaiTaiSanResourceIT.createEntity();
        } else {
            danhMucLoaiTaiSan = TestUtil.findAll(em, DanhMucLoaiTaiSan.class).get(0);
        }
        em.persist(danhMucLoaiTaiSan);
        em.flush();
        thongTinCapNhatTaiSan.setDanhMucLoaiTaiSan(danhMucLoaiTaiSan);
        thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);
        Long danhMucLoaiTaiSanId = danhMucLoaiTaiSan.getIdLoaiTs();
        // Get all the thongTinCapNhatTaiSanList where danhMucLoaiTaiSan equals to danhMucLoaiTaiSanId
        defaultThongTinCapNhatTaiSanShouldBeFound("danhMucLoaiTaiSanId.equals=" + danhMucLoaiTaiSanId);

        // Get all the thongTinCapNhatTaiSanList where danhMucLoaiTaiSan equals to (danhMucLoaiTaiSanId + 1)
        defaultThongTinCapNhatTaiSanShouldNotBeFound("danhMucLoaiTaiSanId.equals=" + (danhMucLoaiTaiSanId + 1));
    }

    private void defaultThongTinCapNhatTaiSanFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultThongTinCapNhatTaiSanShouldBeFound(shouldBeFound);
        defaultThongTinCapNhatTaiSanShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultThongTinCapNhatTaiSanShouldBeFound(String filter) throws Exception {
        restThongTinCapNhatTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCapNhat,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCapNhat").value(hasItem(thongTinCapNhatTaiSan.getIdCapNhat().intValue())))
            .andExpect(jsonPath("$.[*].tenTaiSan").value(hasItem(DEFAULT_TEN_TAI_SAN)))
            .andExpect(jsonPath("$.[*].thongTinTaiSan").value(hasItem(DEFAULT_THONG_TIN_TAI_SAN.toString())))
            .andExpect(jsonPath("$.[*].ngayCapNhat").value(hasItem(DEFAULT_NGAY_CAP_NHAT.toString())));

        // Check, that the count call also returns 1
        restThongTinCapNhatTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idCapNhat,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultThongTinCapNhatTaiSanShouldNotBeFound(String filter) throws Exception {
        restThongTinCapNhatTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCapNhat,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restThongTinCapNhatTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idCapNhat,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingThongTinCapNhatTaiSan() throws Exception {
        // Get the thongTinCapNhatTaiSan
        restThongTinCapNhatTaiSanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingThongTinCapNhatTaiSan() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thongTinCapNhatTaiSan
        ThongTinCapNhatTaiSan updatedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository
            .findById(thongTinCapNhatTaiSan.getIdCapNhat())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedThongTinCapNhatTaiSan are not directly saved in db
        em.detach(updatedThongTinCapNhatTaiSan);
        updatedThongTinCapNhatTaiSan
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .thongTinTaiSan(UPDATED_THONG_TIN_TAI_SAN)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);
        ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanMapper.toDto(updatedThongTinCapNhatTaiSan);

        restThongTinCapNhatTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, thongTinCapNhatTaiSanDTO.getIdCapNhat())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thongTinCapNhatTaiSanDTO))
            )
            .andExpect(status().isOk());

        // Validate the ThongTinCapNhatTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedThongTinCapNhatTaiSanToMatchAllProperties(updatedThongTinCapNhatTaiSan);
    }

    @Test
    @Transactional
    void putNonExistingThongTinCapNhatTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatTaiSan.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatTaiSan
        ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongTinCapNhatTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, thongTinCapNhatTaiSanDTO.getIdCapNhat())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thongTinCapNhatTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinCapNhatTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchThongTinCapNhatTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatTaiSan.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatTaiSan
        ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinCapNhatTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thongTinCapNhatTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinCapNhatTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamThongTinCapNhatTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatTaiSan.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatTaiSan
        ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinCapNhatTaiSanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thongTinCapNhatTaiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ThongTinCapNhatTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateThongTinCapNhatTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thongTinCapNhatTaiSan using partial update
        ThongTinCapNhatTaiSan partialUpdatedThongTinCapNhatTaiSan = new ThongTinCapNhatTaiSan();
        partialUpdatedThongTinCapNhatTaiSan.setIdCapNhat(thongTinCapNhatTaiSan.getIdCapNhat());

        partialUpdatedThongTinCapNhatTaiSan.thongTinTaiSan(UPDATED_THONG_TIN_TAI_SAN);

        restThongTinCapNhatTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedThongTinCapNhatTaiSan.getIdCapNhat())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedThongTinCapNhatTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the ThongTinCapNhatTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertThongTinCapNhatTaiSanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedThongTinCapNhatTaiSan, thongTinCapNhatTaiSan),
            getPersistedThongTinCapNhatTaiSan(thongTinCapNhatTaiSan)
        );
    }

    @Test
    @Transactional
    void fullUpdateThongTinCapNhatTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thongTinCapNhatTaiSan using partial update
        ThongTinCapNhatTaiSan partialUpdatedThongTinCapNhatTaiSan = new ThongTinCapNhatTaiSan();
        partialUpdatedThongTinCapNhatTaiSan.setIdCapNhat(thongTinCapNhatTaiSan.getIdCapNhat());

        partialUpdatedThongTinCapNhatTaiSan
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .thongTinTaiSan(UPDATED_THONG_TIN_TAI_SAN)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);

        restThongTinCapNhatTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedThongTinCapNhatTaiSan.getIdCapNhat())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedThongTinCapNhatTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the ThongTinCapNhatTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertThongTinCapNhatTaiSanUpdatableFieldsEquals(
            partialUpdatedThongTinCapNhatTaiSan,
            getPersistedThongTinCapNhatTaiSan(partialUpdatedThongTinCapNhatTaiSan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingThongTinCapNhatTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatTaiSan.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatTaiSan
        ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongTinCapNhatTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, thongTinCapNhatTaiSanDTO.getIdCapNhat())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(thongTinCapNhatTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinCapNhatTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchThongTinCapNhatTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatTaiSan.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatTaiSan
        ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinCapNhatTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(thongTinCapNhatTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinCapNhatTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamThongTinCapNhatTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatTaiSan.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatTaiSan
        ThongTinCapNhatTaiSanDTO thongTinCapNhatTaiSanDTO = thongTinCapNhatTaiSanMapper.toDto(thongTinCapNhatTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinCapNhatTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(thongTinCapNhatTaiSanDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ThongTinCapNhatTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteThongTinCapNhatTaiSan() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatTaiSan = thongTinCapNhatTaiSanRepository.saveAndFlush(thongTinCapNhatTaiSan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the thongTinCapNhatTaiSan
        restThongTinCapNhatTaiSanMockMvc
            .perform(delete(ENTITY_API_URL_ID, thongTinCapNhatTaiSan.getIdCapNhat()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return thongTinCapNhatTaiSanRepository.count();
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

    protected ThongTinCapNhatTaiSan getPersistedThongTinCapNhatTaiSan(ThongTinCapNhatTaiSan thongTinCapNhatTaiSan) {
        return thongTinCapNhatTaiSanRepository.findById(thongTinCapNhatTaiSan.getIdCapNhat()).orElseThrow();
    }

    protected void assertPersistedThongTinCapNhatTaiSanToMatchAllProperties(ThongTinCapNhatTaiSan expectedThongTinCapNhatTaiSan) {
        assertThongTinCapNhatTaiSanAllPropertiesEquals(
            expectedThongTinCapNhatTaiSan,
            getPersistedThongTinCapNhatTaiSan(expectedThongTinCapNhatTaiSan)
        );
    }

    protected void assertPersistedThongTinCapNhatTaiSanToMatchUpdatableProperties(ThongTinCapNhatTaiSan expectedThongTinCapNhatTaiSan) {
        assertThongTinCapNhatTaiSanAllUpdatablePropertiesEquals(
            expectedThongTinCapNhatTaiSan,
            getPersistedThongTinCapNhatTaiSan(expectedThongTinCapNhatTaiSan)
        );
    }
}
