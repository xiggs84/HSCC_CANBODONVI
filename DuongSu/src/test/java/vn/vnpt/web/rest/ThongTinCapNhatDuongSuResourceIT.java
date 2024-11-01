package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.ThongTinCapNhatDuongSuAsserts.*;
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
import vn.vnpt.domain.ThongTinCapNhatDuongSu;
import vn.vnpt.repository.ThongTinCapNhatDuongSuRepository;
import vn.vnpt.service.dto.ThongTinCapNhatDuongSuDTO;
import vn.vnpt.service.mapper.ThongTinCapNhatDuongSuMapper;

/**
 * Integration tests for the {@link ThongTinCapNhatDuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ThongTinCapNhatDuongSuResourceIT {

    private static final String DEFAULT_TEN_DUONG_SU = "AAAAAAAAAA";
    private static final String UPDATED_TEN_DUONG_SU = "BBBBBBBBBB";

    private static final String DEFAULT_SO_GIAY_TO = "AAAAAAAAAA";
    private static final String UPDATED_SO_GIAY_TO = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_DUONG_SU = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_DUONG_SU = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_CAP_NHAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CAP_NHAT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NGAY_CAP_NHAT = LocalDate.ofEpochDay(-1L);

    private static final String ENTITY_API_URL = "/api/thong-tin-cap-nhat-duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCapNhat}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ThongTinCapNhatDuongSuRepository thongTinCapNhatDuongSuRepository;

    @Autowired
    private ThongTinCapNhatDuongSuMapper thongTinCapNhatDuongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restThongTinCapNhatDuongSuMockMvc;

    private ThongTinCapNhatDuongSu thongTinCapNhatDuongSu;

    private ThongTinCapNhatDuongSu insertedThongTinCapNhatDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThongTinCapNhatDuongSu createEntity() {
        return new ThongTinCapNhatDuongSu()
            .tenDuongSu(DEFAULT_TEN_DUONG_SU)
            .soGiayTo(DEFAULT_SO_GIAY_TO)
            .thongTinDuongSu(DEFAULT_THONG_TIN_DUONG_SU)
            .ngayCapNhat(DEFAULT_NGAY_CAP_NHAT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThongTinCapNhatDuongSu createUpdatedEntity() {
        return new ThongTinCapNhatDuongSu()
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .thongTinDuongSu(UPDATED_THONG_TIN_DUONG_SU)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);
    }

    @BeforeEach
    public void initTest() {
        thongTinCapNhatDuongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedThongTinCapNhatDuongSu != null) {
            thongTinCapNhatDuongSuRepository.delete(insertedThongTinCapNhatDuongSu);
            insertedThongTinCapNhatDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createThongTinCapNhatDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ThongTinCapNhatDuongSu
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuMapper.toDto(thongTinCapNhatDuongSu);
        var returnedThongTinCapNhatDuongSuDTO = om.readValue(
            restThongTinCapNhatDuongSuMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thongTinCapNhatDuongSuDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ThongTinCapNhatDuongSuDTO.class
        );

        // Validate the ThongTinCapNhatDuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuMapper.toEntity(returnedThongTinCapNhatDuongSuDTO);
        assertThongTinCapNhatDuongSuUpdatableFieldsEquals(
            returnedThongTinCapNhatDuongSu,
            getPersistedThongTinCapNhatDuongSu(returnedThongTinCapNhatDuongSu)
        );

        insertedThongTinCapNhatDuongSu = returnedThongTinCapNhatDuongSu;
    }

    @Test
    @Transactional
    void createThongTinCapNhatDuongSuWithExistingId() throws Exception {
        // Create the ThongTinCapNhatDuongSu with an existing ID
        thongTinCapNhatDuongSu.setIdCapNhat(1L);
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuMapper.toDto(thongTinCapNhatDuongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restThongTinCapNhatDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thongTinCapNhatDuongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongTinCapNhatDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSus() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList
        restThongTinCapNhatDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCapNhat,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCapNhat").value(hasItem(thongTinCapNhatDuongSu.getIdCapNhat().intValue())))
            .andExpect(jsonPath("$.[*].tenDuongSu").value(hasItem(DEFAULT_TEN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].soGiayTo").value(hasItem(DEFAULT_SO_GIAY_TO)))
            .andExpect(jsonPath("$.[*].thongTinDuongSu").value(hasItem(DEFAULT_THONG_TIN_DUONG_SU.toString())))
            .andExpect(jsonPath("$.[*].ngayCapNhat").value(hasItem(DEFAULT_NGAY_CAP_NHAT.toString())));
    }

    @Test
    @Transactional
    void getThongTinCapNhatDuongSu() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get the thongTinCapNhatDuongSu
        restThongTinCapNhatDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, thongTinCapNhatDuongSu.getIdCapNhat()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCapNhat").value(thongTinCapNhatDuongSu.getIdCapNhat().intValue()))
            .andExpect(jsonPath("$.tenDuongSu").value(DEFAULT_TEN_DUONG_SU))
            .andExpect(jsonPath("$.soGiayTo").value(DEFAULT_SO_GIAY_TO))
            .andExpect(jsonPath("$.thongTinDuongSu").value(DEFAULT_THONG_TIN_DUONG_SU.toString()))
            .andExpect(jsonPath("$.ngayCapNhat").value(DEFAULT_NGAY_CAP_NHAT.toString()));
    }

    @Test
    @Transactional
    void getThongTinCapNhatDuongSusByIdFiltering() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        Long id = thongTinCapNhatDuongSu.getIdCapNhat();

        defaultThongTinCapNhatDuongSuFiltering("idCapNhat.equals=" + id, "idCapNhat.notEquals=" + id);

        defaultThongTinCapNhatDuongSuFiltering("idCapNhat.greaterThanOrEqual=" + id, "idCapNhat.greaterThan=" + id);

        defaultThongTinCapNhatDuongSuFiltering("idCapNhat.lessThanOrEqual=" + id, "idCapNhat.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByTenDuongSuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where tenDuongSu equals to
        defaultThongTinCapNhatDuongSuFiltering("tenDuongSu.equals=" + DEFAULT_TEN_DUONG_SU, "tenDuongSu.equals=" + UPDATED_TEN_DUONG_SU);
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByTenDuongSuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where tenDuongSu in
        defaultThongTinCapNhatDuongSuFiltering(
            "tenDuongSu.in=" + DEFAULT_TEN_DUONG_SU + "," + UPDATED_TEN_DUONG_SU,
            "tenDuongSu.in=" + UPDATED_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByTenDuongSuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where tenDuongSu is not null
        defaultThongTinCapNhatDuongSuFiltering("tenDuongSu.specified=true", "tenDuongSu.specified=false");
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByTenDuongSuContainsSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where tenDuongSu contains
        defaultThongTinCapNhatDuongSuFiltering(
            "tenDuongSu.contains=" + DEFAULT_TEN_DUONG_SU,
            "tenDuongSu.contains=" + UPDATED_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByTenDuongSuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where tenDuongSu does not contain
        defaultThongTinCapNhatDuongSuFiltering(
            "tenDuongSu.doesNotContain=" + UPDATED_TEN_DUONG_SU,
            "tenDuongSu.doesNotContain=" + DEFAULT_TEN_DUONG_SU
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusBySoGiayToIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where soGiayTo equals to
        defaultThongTinCapNhatDuongSuFiltering("soGiayTo.equals=" + DEFAULT_SO_GIAY_TO, "soGiayTo.equals=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusBySoGiayToIsInShouldWork() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where soGiayTo in
        defaultThongTinCapNhatDuongSuFiltering(
            "soGiayTo.in=" + DEFAULT_SO_GIAY_TO + "," + UPDATED_SO_GIAY_TO,
            "soGiayTo.in=" + UPDATED_SO_GIAY_TO
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusBySoGiayToIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where soGiayTo is not null
        defaultThongTinCapNhatDuongSuFiltering("soGiayTo.specified=true", "soGiayTo.specified=false");
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusBySoGiayToContainsSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where soGiayTo contains
        defaultThongTinCapNhatDuongSuFiltering("soGiayTo.contains=" + DEFAULT_SO_GIAY_TO, "soGiayTo.contains=" + UPDATED_SO_GIAY_TO);
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusBySoGiayToNotContainsSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where soGiayTo does not contain
        defaultThongTinCapNhatDuongSuFiltering(
            "soGiayTo.doesNotContain=" + UPDATED_SO_GIAY_TO,
            "soGiayTo.doesNotContain=" + DEFAULT_SO_GIAY_TO
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByNgayCapNhatIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where ngayCapNhat equals to
        defaultThongTinCapNhatDuongSuFiltering(
            "ngayCapNhat.equals=" + DEFAULT_NGAY_CAP_NHAT,
            "ngayCapNhat.equals=" + UPDATED_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByNgayCapNhatIsInShouldWork() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where ngayCapNhat in
        defaultThongTinCapNhatDuongSuFiltering(
            "ngayCapNhat.in=" + DEFAULT_NGAY_CAP_NHAT + "," + UPDATED_NGAY_CAP_NHAT,
            "ngayCapNhat.in=" + UPDATED_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByNgayCapNhatIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where ngayCapNhat is not null
        defaultThongTinCapNhatDuongSuFiltering("ngayCapNhat.specified=true", "ngayCapNhat.specified=false");
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByNgayCapNhatIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where ngayCapNhat is greater than or equal to
        defaultThongTinCapNhatDuongSuFiltering(
            "ngayCapNhat.greaterThanOrEqual=" + DEFAULT_NGAY_CAP_NHAT,
            "ngayCapNhat.greaterThanOrEqual=" + UPDATED_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByNgayCapNhatIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where ngayCapNhat is less than or equal to
        defaultThongTinCapNhatDuongSuFiltering(
            "ngayCapNhat.lessThanOrEqual=" + DEFAULT_NGAY_CAP_NHAT,
            "ngayCapNhat.lessThanOrEqual=" + SMALLER_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByNgayCapNhatIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where ngayCapNhat is less than
        defaultThongTinCapNhatDuongSuFiltering(
            "ngayCapNhat.lessThan=" + UPDATED_NGAY_CAP_NHAT,
            "ngayCapNhat.lessThan=" + DEFAULT_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByNgayCapNhatIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        // Get all the thongTinCapNhatDuongSuList where ngayCapNhat is greater than
        defaultThongTinCapNhatDuongSuFiltering(
            "ngayCapNhat.greaterThan=" + SMALLER_NGAY_CAP_NHAT,
            "ngayCapNhat.greaterThan=" + DEFAULT_NGAY_CAP_NHAT
        );
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByLoaiDuongSuIsEqualToSomething() throws Exception {
        LoaiDuongSu loaiDuongSu;
        if (TestUtil.findAll(em, LoaiDuongSu.class).isEmpty()) {
            thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);
            loaiDuongSu = LoaiDuongSuResourceIT.createEntity();
        } else {
            loaiDuongSu = TestUtil.findAll(em, LoaiDuongSu.class).get(0);
        }
        em.persist(loaiDuongSu);
        em.flush();
        thongTinCapNhatDuongSu.setLoaiDuongSu(loaiDuongSu);
        thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);
        String loaiDuongSuId = loaiDuongSu.getIdLoaiDuongSu();
        // Get all the thongTinCapNhatDuongSuList where loaiDuongSu equals to loaiDuongSuId
        defaultThongTinCapNhatDuongSuShouldBeFound("loaiDuongSuId.equals=" + loaiDuongSuId);

        // Get all the thongTinCapNhatDuongSuList where loaiDuongSu equals to "invalid-id"
        defaultThongTinCapNhatDuongSuShouldNotBeFound("loaiDuongSuId.equals=" + "invalid-id");
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByLoaiGiayToIsEqualToSomething() throws Exception {
        LoaiGiayTo loaiGiayTo;
        if (TestUtil.findAll(em, LoaiGiayTo.class).isEmpty()) {
            thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);
            loaiGiayTo = LoaiGiayToResourceIT.createEntity();
        } else {
            loaiGiayTo = TestUtil.findAll(em, LoaiGiayTo.class).get(0);
        }
        em.persist(loaiGiayTo);
        em.flush();
        thongTinCapNhatDuongSu.setLoaiGiayTo(loaiGiayTo);
        thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);
        String loaiGiayToId = loaiGiayTo.getIdLoaiGiayTo();
        // Get all the thongTinCapNhatDuongSuList where loaiGiayTo equals to loaiGiayToId
        defaultThongTinCapNhatDuongSuShouldBeFound("loaiGiayToId.equals=" + loaiGiayToId);

        // Get all the thongTinCapNhatDuongSuList where loaiGiayTo equals to "invalid-id"
        defaultThongTinCapNhatDuongSuShouldNotBeFound("loaiGiayToId.equals=" + "invalid-id");
    }

    @Test
    @Transactional
    void getAllThongTinCapNhatDuongSusByDuongSuIsEqualToSomething() throws Exception {
        DuongSu duongSu;
        if (TestUtil.findAll(em, DuongSu.class).isEmpty()) {
            thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);
            duongSu = DuongSuResourceIT.createEntity();
        } else {
            duongSu = TestUtil.findAll(em, DuongSu.class).get(0);
        }
        em.persist(duongSu);
        em.flush();
        thongTinCapNhatDuongSu.setDuongSu(duongSu);
        thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);
        Long duongSuId = duongSu.getIdDuongSu();
        // Get all the thongTinCapNhatDuongSuList where duongSu equals to duongSuId
        defaultThongTinCapNhatDuongSuShouldBeFound("duongSuId.equals=" + duongSuId);

        // Get all the thongTinCapNhatDuongSuList where duongSu equals to (duongSuId + 1)
        defaultThongTinCapNhatDuongSuShouldNotBeFound("duongSuId.equals=" + (duongSuId + 1));
    }

    private void defaultThongTinCapNhatDuongSuFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultThongTinCapNhatDuongSuShouldBeFound(shouldBeFound);
        defaultThongTinCapNhatDuongSuShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultThongTinCapNhatDuongSuShouldBeFound(String filter) throws Exception {
        restThongTinCapNhatDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCapNhat,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCapNhat").value(hasItem(thongTinCapNhatDuongSu.getIdCapNhat().intValue())))
            .andExpect(jsonPath("$.[*].tenDuongSu").value(hasItem(DEFAULT_TEN_DUONG_SU)))
            .andExpect(jsonPath("$.[*].soGiayTo").value(hasItem(DEFAULT_SO_GIAY_TO)))
            .andExpect(jsonPath("$.[*].thongTinDuongSu").value(hasItem(DEFAULT_THONG_TIN_DUONG_SU.toString())))
            .andExpect(jsonPath("$.[*].ngayCapNhat").value(hasItem(DEFAULT_NGAY_CAP_NHAT.toString())));

        // Check, that the count call also returns 1
        restThongTinCapNhatDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idCapNhat,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultThongTinCapNhatDuongSuShouldNotBeFound(String filter) throws Exception {
        restThongTinCapNhatDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCapNhat,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restThongTinCapNhatDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idCapNhat,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingThongTinCapNhatDuongSu() throws Exception {
        // Get the thongTinCapNhatDuongSu
        restThongTinCapNhatDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingThongTinCapNhatDuongSu() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thongTinCapNhatDuongSu
        ThongTinCapNhatDuongSu updatedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository
            .findById(thongTinCapNhatDuongSu.getIdCapNhat())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedThongTinCapNhatDuongSu are not directly saved in db
        em.detach(updatedThongTinCapNhatDuongSu);
        updatedThongTinCapNhatDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .thongTinDuongSu(UPDATED_THONG_TIN_DUONG_SU)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuMapper.toDto(updatedThongTinCapNhatDuongSu);

        restThongTinCapNhatDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, thongTinCapNhatDuongSuDTO.getIdCapNhat())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thongTinCapNhatDuongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the ThongTinCapNhatDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedThongTinCapNhatDuongSuToMatchAllProperties(updatedThongTinCapNhatDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingThongTinCapNhatDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatDuongSu.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatDuongSu
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuMapper.toDto(thongTinCapNhatDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongTinCapNhatDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, thongTinCapNhatDuongSuDTO.getIdCapNhat())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thongTinCapNhatDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinCapNhatDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchThongTinCapNhatDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatDuongSu.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatDuongSu
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuMapper.toDto(thongTinCapNhatDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinCapNhatDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(thongTinCapNhatDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinCapNhatDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamThongTinCapNhatDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatDuongSu.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatDuongSu
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuMapper.toDto(thongTinCapNhatDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinCapNhatDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(thongTinCapNhatDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ThongTinCapNhatDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateThongTinCapNhatDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thongTinCapNhatDuongSu using partial update
        ThongTinCapNhatDuongSu partialUpdatedThongTinCapNhatDuongSu = new ThongTinCapNhatDuongSu();
        partialUpdatedThongTinCapNhatDuongSu.setIdCapNhat(thongTinCapNhatDuongSu.getIdCapNhat());

        partialUpdatedThongTinCapNhatDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .thongTinDuongSu(UPDATED_THONG_TIN_DUONG_SU)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);

        restThongTinCapNhatDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedThongTinCapNhatDuongSu.getIdCapNhat())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedThongTinCapNhatDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the ThongTinCapNhatDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertThongTinCapNhatDuongSuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedThongTinCapNhatDuongSu, thongTinCapNhatDuongSu),
            getPersistedThongTinCapNhatDuongSu(thongTinCapNhatDuongSu)
        );
    }

    @Test
    @Transactional
    void fullUpdateThongTinCapNhatDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the thongTinCapNhatDuongSu using partial update
        ThongTinCapNhatDuongSu partialUpdatedThongTinCapNhatDuongSu = new ThongTinCapNhatDuongSu();
        partialUpdatedThongTinCapNhatDuongSu.setIdCapNhat(thongTinCapNhatDuongSu.getIdCapNhat());

        partialUpdatedThongTinCapNhatDuongSu
            .tenDuongSu(UPDATED_TEN_DUONG_SU)
            .soGiayTo(UPDATED_SO_GIAY_TO)
            .thongTinDuongSu(UPDATED_THONG_TIN_DUONG_SU)
            .ngayCapNhat(UPDATED_NGAY_CAP_NHAT);

        restThongTinCapNhatDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedThongTinCapNhatDuongSu.getIdCapNhat())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedThongTinCapNhatDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the ThongTinCapNhatDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertThongTinCapNhatDuongSuUpdatableFieldsEquals(
            partialUpdatedThongTinCapNhatDuongSu,
            getPersistedThongTinCapNhatDuongSu(partialUpdatedThongTinCapNhatDuongSu)
        );
    }

    @Test
    @Transactional
    void patchNonExistingThongTinCapNhatDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatDuongSu.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatDuongSu
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuMapper.toDto(thongTinCapNhatDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongTinCapNhatDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, thongTinCapNhatDuongSuDTO.getIdCapNhat())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(thongTinCapNhatDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinCapNhatDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchThongTinCapNhatDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatDuongSu.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatDuongSu
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuMapper.toDto(thongTinCapNhatDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinCapNhatDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(thongTinCapNhatDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ThongTinCapNhatDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamThongTinCapNhatDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        thongTinCapNhatDuongSu.setIdCapNhat(longCount.incrementAndGet());

        // Create the ThongTinCapNhatDuongSu
        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO = thongTinCapNhatDuongSuMapper.toDto(thongTinCapNhatDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restThongTinCapNhatDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(thongTinCapNhatDuongSuDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ThongTinCapNhatDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteThongTinCapNhatDuongSu() throws Exception {
        // Initialize the database
        insertedThongTinCapNhatDuongSu = thongTinCapNhatDuongSuRepository.saveAndFlush(thongTinCapNhatDuongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the thongTinCapNhatDuongSu
        restThongTinCapNhatDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, thongTinCapNhatDuongSu.getIdCapNhat()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return thongTinCapNhatDuongSuRepository.count();
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

    protected ThongTinCapNhatDuongSu getPersistedThongTinCapNhatDuongSu(ThongTinCapNhatDuongSu thongTinCapNhatDuongSu) {
        return thongTinCapNhatDuongSuRepository.findById(thongTinCapNhatDuongSu.getIdCapNhat()).orElseThrow();
    }

    protected void assertPersistedThongTinCapNhatDuongSuToMatchAllProperties(ThongTinCapNhatDuongSu expectedThongTinCapNhatDuongSu) {
        assertThongTinCapNhatDuongSuAllPropertiesEquals(
            expectedThongTinCapNhatDuongSu,
            getPersistedThongTinCapNhatDuongSu(expectedThongTinCapNhatDuongSu)
        );
    }

    protected void assertPersistedThongTinCapNhatDuongSuToMatchUpdatableProperties(ThongTinCapNhatDuongSu expectedThongTinCapNhatDuongSu) {
        assertThongTinCapNhatDuongSuAllUpdatablePropertiesEquals(
            expectedThongTinCapNhatDuongSu,
            getPersistedThongTinCapNhatDuongSu(expectedThongTinCapNhatDuongSu)
        );
    }
}
