package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.CauHinhThongTinDuongSuAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
import vn.vnpt.domain.CauHinhThongTinDuongSu;
import vn.vnpt.repository.CauHinhThongTinDuongSuRepository;
import vn.vnpt.service.dto.CauHinhThongTinDuongSuDTO;
import vn.vnpt.service.mapper.CauHinhThongTinDuongSuMapper;

/**
 * Integration tests for the {@link CauHinhThongTinDuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CauHinhThongTinDuongSuResourceIT {

    private static final String DEFAULT_NOI_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG = "BBBBBBBBBB";

    private static final String DEFAULT_JAVASCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_JAVASCRIPT = "BBBBBBBBBB";

    private static final String DEFAULT_CSS = "AAAAAAAAAA";
    private static final String UPDATED_CSS = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_LOAI_DS = 1L;
    private static final Long UPDATED_ID_LOAI_DS = 2L;
    private static final Long SMALLER_ID_LOAI_DS = 1L - 1L;

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;
    private static final Long SMALLER_ID_DON_VI = 1L - 1L;

    private static final Integer DEFAULT_TRANG_THAI = 0;
    private static final Integer UPDATED_TRANG_THAI = 1;
    private static final Integer SMALLER_TRANG_THAI = 0 - 1;

    private static final String ENTITY_API_URL = "/api/cau-hinh-thong-tin-duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCauHinh}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CauHinhThongTinDuongSuRepository cauHinhThongTinDuongSuRepository;

    @Autowired
    private CauHinhThongTinDuongSuMapper cauHinhThongTinDuongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCauHinhThongTinDuongSuMockMvc;

    private CauHinhThongTinDuongSu cauHinhThongTinDuongSu;

    private CauHinhThongTinDuongSu insertedCauHinhThongTinDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHinhThongTinDuongSu createEntity() {
        return new CauHinhThongTinDuongSu()
            .noiDung(DEFAULT_NOI_DUNG)
            .javascript(DEFAULT_JAVASCRIPT)
            .css(DEFAULT_CSS)
            .idLoaiDs(DEFAULT_ID_LOAI_DS)
            .idDonVi(DEFAULT_ID_DON_VI)
            .trangThai(DEFAULT_TRANG_THAI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHinhThongTinDuongSu createUpdatedEntity() {
        return new CauHinhThongTinDuongSu()
            .noiDung(UPDATED_NOI_DUNG)
            .javascript(UPDATED_JAVASCRIPT)
            .css(UPDATED_CSS)
            .idLoaiDs(UPDATED_ID_LOAI_DS)
            .idDonVi(UPDATED_ID_DON_VI)
            .trangThai(UPDATED_TRANG_THAI);
    }

    @BeforeEach
    public void initTest() {
        cauHinhThongTinDuongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCauHinhThongTinDuongSu != null) {
            cauHinhThongTinDuongSuRepository.delete(insertedCauHinhThongTinDuongSu);
            insertedCauHinhThongTinDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createCauHinhThongTinDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CauHinhThongTinDuongSu
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);
        var returnedCauHinhThongTinDuongSuDTO = om.readValue(
            restCauHinhThongTinDuongSuMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhThongTinDuongSuDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CauHinhThongTinDuongSuDTO.class
        );

        // Validate the CauHinhThongTinDuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuMapper.toEntity(returnedCauHinhThongTinDuongSuDTO);
        assertCauHinhThongTinDuongSuUpdatableFieldsEquals(
            returnedCauHinhThongTinDuongSu,
            getPersistedCauHinhThongTinDuongSu(returnedCauHinhThongTinDuongSu)
        );

        insertedCauHinhThongTinDuongSu = returnedCauHinhThongTinDuongSu;
    }

    @Test
    @Transactional
    void createCauHinhThongTinDuongSuWithExistingId() throws Exception {
        // Create the CauHinhThongTinDuongSu with an existing ID
        cauHinhThongTinDuongSu.setIdCauHinh(1L);
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCauHinhThongTinDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhThongTinDuongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CauHinhThongTinDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSus() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList
        restCauHinhThongTinDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCauHinh,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCauHinh").value(hasItem(cauHinhThongTinDuongSu.getIdCauHinh().intValue())))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.[*].javascript").value(hasItem(DEFAULT_JAVASCRIPT)))
            .andExpect(jsonPath("$.[*].css").value(hasItem(DEFAULT_CSS)))
            .andExpect(jsonPath("$.[*].idLoaiDs").value(hasItem(DEFAULT_ID_LOAI_DS.intValue())))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)));
    }

    @Test
    @Transactional
    void getCauHinhThongTinDuongSu() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get the cauHinhThongTinDuongSu
        restCauHinhThongTinDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, cauHinhThongTinDuongSu.getIdCauHinh()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCauHinh").value(cauHinhThongTinDuongSu.getIdCauHinh().intValue()))
            .andExpect(jsonPath("$.noiDung").value(DEFAULT_NOI_DUNG))
            .andExpect(jsonPath("$.javascript").value(DEFAULT_JAVASCRIPT))
            .andExpect(jsonPath("$.css").value(DEFAULT_CSS))
            .andExpect(jsonPath("$.idLoaiDs").value(DEFAULT_ID_LOAI_DS.intValue()))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI));
    }

    @Test
    @Transactional
    void getCauHinhThongTinDuongSusByIdFiltering() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        Long id = cauHinhThongTinDuongSu.getIdCauHinh();

        defaultCauHinhThongTinDuongSuFiltering("idCauHinh.equals=" + id, "idCauHinh.notEquals=" + id);

        defaultCauHinhThongTinDuongSuFiltering("idCauHinh.greaterThanOrEqual=" + id, "idCauHinh.greaterThan=" + id);

        defaultCauHinhThongTinDuongSuFiltering("idCauHinh.lessThanOrEqual=" + id, "idCauHinh.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByNoiDungIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where noiDung equals to
        defaultCauHinhThongTinDuongSuFiltering("noiDung.equals=" + DEFAULT_NOI_DUNG, "noiDung.equals=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByNoiDungIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where noiDung in
        defaultCauHinhThongTinDuongSuFiltering("noiDung.in=" + DEFAULT_NOI_DUNG + "," + UPDATED_NOI_DUNG, "noiDung.in=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByNoiDungIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where noiDung is not null
        defaultCauHinhThongTinDuongSuFiltering("noiDung.specified=true", "noiDung.specified=false");
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByNoiDungContainsSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where noiDung contains
        defaultCauHinhThongTinDuongSuFiltering("noiDung.contains=" + DEFAULT_NOI_DUNG, "noiDung.contains=" + UPDATED_NOI_DUNG);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByNoiDungNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where noiDung does not contain
        defaultCauHinhThongTinDuongSuFiltering("noiDung.doesNotContain=" + UPDATED_NOI_DUNG, "noiDung.doesNotContain=" + DEFAULT_NOI_DUNG);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByJavascriptIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where javascript equals to
        defaultCauHinhThongTinDuongSuFiltering("javascript.equals=" + DEFAULT_JAVASCRIPT, "javascript.equals=" + UPDATED_JAVASCRIPT);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByJavascriptIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where javascript in
        defaultCauHinhThongTinDuongSuFiltering(
            "javascript.in=" + DEFAULT_JAVASCRIPT + "," + UPDATED_JAVASCRIPT,
            "javascript.in=" + UPDATED_JAVASCRIPT
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByJavascriptIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where javascript is not null
        defaultCauHinhThongTinDuongSuFiltering("javascript.specified=true", "javascript.specified=false");
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByJavascriptContainsSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where javascript contains
        defaultCauHinhThongTinDuongSuFiltering("javascript.contains=" + DEFAULT_JAVASCRIPT, "javascript.contains=" + UPDATED_JAVASCRIPT);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByJavascriptNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where javascript does not contain
        defaultCauHinhThongTinDuongSuFiltering(
            "javascript.doesNotContain=" + UPDATED_JAVASCRIPT,
            "javascript.doesNotContain=" + DEFAULT_JAVASCRIPT
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByCssIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where css equals to
        defaultCauHinhThongTinDuongSuFiltering("css.equals=" + DEFAULT_CSS, "css.equals=" + UPDATED_CSS);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByCssIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where css in
        defaultCauHinhThongTinDuongSuFiltering("css.in=" + DEFAULT_CSS + "," + UPDATED_CSS, "css.in=" + UPDATED_CSS);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByCssIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where css is not null
        defaultCauHinhThongTinDuongSuFiltering("css.specified=true", "css.specified=false");
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByCssContainsSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where css contains
        defaultCauHinhThongTinDuongSuFiltering("css.contains=" + DEFAULT_CSS, "css.contains=" + UPDATED_CSS);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByCssNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where css does not contain
        defaultCauHinhThongTinDuongSuFiltering("css.doesNotContain=" + UPDATED_CSS, "css.doesNotContain=" + DEFAULT_CSS);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdLoaiDsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idLoaiDs equals to
        defaultCauHinhThongTinDuongSuFiltering("idLoaiDs.equals=" + DEFAULT_ID_LOAI_DS, "idLoaiDs.equals=" + UPDATED_ID_LOAI_DS);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdLoaiDsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idLoaiDs in
        defaultCauHinhThongTinDuongSuFiltering(
            "idLoaiDs.in=" + DEFAULT_ID_LOAI_DS + "," + UPDATED_ID_LOAI_DS,
            "idLoaiDs.in=" + UPDATED_ID_LOAI_DS
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdLoaiDsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idLoaiDs is not null
        defaultCauHinhThongTinDuongSuFiltering("idLoaiDs.specified=true", "idLoaiDs.specified=false");
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdLoaiDsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idLoaiDs is greater than or equal to
        defaultCauHinhThongTinDuongSuFiltering(
            "idLoaiDs.greaterThanOrEqual=" + DEFAULT_ID_LOAI_DS,
            "idLoaiDs.greaterThanOrEqual=" + UPDATED_ID_LOAI_DS
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdLoaiDsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idLoaiDs is less than or equal to
        defaultCauHinhThongTinDuongSuFiltering(
            "idLoaiDs.lessThanOrEqual=" + DEFAULT_ID_LOAI_DS,
            "idLoaiDs.lessThanOrEqual=" + SMALLER_ID_LOAI_DS
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdLoaiDsIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idLoaiDs is less than
        defaultCauHinhThongTinDuongSuFiltering("idLoaiDs.lessThan=" + UPDATED_ID_LOAI_DS, "idLoaiDs.lessThan=" + DEFAULT_ID_LOAI_DS);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdLoaiDsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idLoaiDs is greater than
        defaultCauHinhThongTinDuongSuFiltering("idLoaiDs.greaterThan=" + SMALLER_ID_LOAI_DS, "idLoaiDs.greaterThan=" + DEFAULT_ID_LOAI_DS);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdDonViIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idDonVi equals to
        defaultCauHinhThongTinDuongSuFiltering("idDonVi.equals=" + DEFAULT_ID_DON_VI, "idDonVi.equals=" + UPDATED_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdDonViIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idDonVi in
        defaultCauHinhThongTinDuongSuFiltering(
            "idDonVi.in=" + DEFAULT_ID_DON_VI + "," + UPDATED_ID_DON_VI,
            "idDonVi.in=" + UPDATED_ID_DON_VI
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdDonViIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idDonVi is not null
        defaultCauHinhThongTinDuongSuFiltering("idDonVi.specified=true", "idDonVi.specified=false");
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdDonViIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idDonVi is greater than or equal to
        defaultCauHinhThongTinDuongSuFiltering(
            "idDonVi.greaterThanOrEqual=" + DEFAULT_ID_DON_VI,
            "idDonVi.greaterThanOrEqual=" + UPDATED_ID_DON_VI
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdDonViIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idDonVi is less than or equal to
        defaultCauHinhThongTinDuongSuFiltering(
            "idDonVi.lessThanOrEqual=" + DEFAULT_ID_DON_VI,
            "idDonVi.lessThanOrEqual=" + SMALLER_ID_DON_VI
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdDonViIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idDonVi is less than
        defaultCauHinhThongTinDuongSuFiltering("idDonVi.lessThan=" + UPDATED_ID_DON_VI, "idDonVi.lessThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByIdDonViIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where idDonVi is greater than
        defaultCauHinhThongTinDuongSuFiltering("idDonVi.greaterThan=" + SMALLER_ID_DON_VI, "idDonVi.greaterThan=" + DEFAULT_ID_DON_VI);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where trangThai equals to
        defaultCauHinhThongTinDuongSuFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where trangThai in
        defaultCauHinhThongTinDuongSuFiltering(
            "trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI,
            "trangThai.in=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where trangThai is not null
        defaultCauHinhThongTinDuongSuFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where trangThai is greater than or equal to
        defaultCauHinhThongTinDuongSuFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + (DEFAULT_TRANG_THAI + 1)
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where trangThai is less than or equal to
        defaultCauHinhThongTinDuongSuFiltering(
            "trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where trangThai is less than
        defaultCauHinhThongTinDuongSuFiltering(
            "trangThai.lessThan=" + (DEFAULT_TRANG_THAI + 1),
            "trangThai.lessThan=" + DEFAULT_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllCauHinhThongTinDuongSusByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        // Get all the cauHinhThongTinDuongSuList where trangThai is greater than
        defaultCauHinhThongTinDuongSuFiltering(
            "trangThai.greaterThan=" + SMALLER_TRANG_THAI,
            "trangThai.greaterThan=" + DEFAULT_TRANG_THAI
        );
    }

    private void defaultCauHinhThongTinDuongSuFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultCauHinhThongTinDuongSuShouldBeFound(shouldBeFound);
        defaultCauHinhThongTinDuongSuShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCauHinhThongTinDuongSuShouldBeFound(String filter) throws Exception {
        restCauHinhThongTinDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCauHinh,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCauHinh").value(hasItem(cauHinhThongTinDuongSu.getIdCauHinh().intValue())))
            .andExpect(jsonPath("$.[*].noiDung").value(hasItem(DEFAULT_NOI_DUNG)))
            .andExpect(jsonPath("$.[*].javascript").value(hasItem(DEFAULT_JAVASCRIPT)))
            .andExpect(jsonPath("$.[*].css").value(hasItem(DEFAULT_CSS)))
            .andExpect(jsonPath("$.[*].idLoaiDs").value(hasItem(DEFAULT_ID_LOAI_DS.intValue())))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)));

        // Check, that the count call also returns 1
        restCauHinhThongTinDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idCauHinh,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCauHinhThongTinDuongSuShouldNotBeFound(String filter) throws Exception {
        restCauHinhThongTinDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCauHinh,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCauHinhThongTinDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idCauHinh,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCauHinhThongTinDuongSu() throws Exception {
        // Get the cauHinhThongTinDuongSu
        restCauHinhThongTinDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCauHinhThongTinDuongSu() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhThongTinDuongSu
        CauHinhThongTinDuongSu updatedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository
            .findById(cauHinhThongTinDuongSu.getIdCauHinh())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedCauHinhThongTinDuongSu are not directly saved in db
        em.detach(updatedCauHinhThongTinDuongSu);
        updatedCauHinhThongTinDuongSu
            .noiDung(UPDATED_NOI_DUNG)
            .javascript(UPDATED_JAVASCRIPT)
            .css(UPDATED_CSS)
            .idLoaiDs(UPDATED_ID_LOAI_DS)
            .idDonVi(UPDATED_ID_DON_VI)
            .trangThai(UPDATED_TRANG_THAI);
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuMapper.toDto(updatedCauHinhThongTinDuongSu);

        restCauHinhThongTinDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cauHinhThongTinDuongSuDTO.getIdCauHinh())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhThongTinDuongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhThongTinDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCauHinhThongTinDuongSuToMatchAllProperties(updatedCauHinhThongTinDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingCauHinhThongTinDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinDuongSu.setIdCauHinh(longCount.incrementAndGet());

        // Create the CauHinhThongTinDuongSu
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCauHinhThongTinDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cauHinhThongTinDuongSuDTO.getIdCauHinh())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhThongTinDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhThongTinDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCauHinhThongTinDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinDuongSu.setIdCauHinh(longCount.incrementAndGet());

        // Create the CauHinhThongTinDuongSu
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhThongTinDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhThongTinDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhThongTinDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCauHinhThongTinDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinDuongSu.setIdCauHinh(longCount.incrementAndGet());

        // Create the CauHinhThongTinDuongSu
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhThongTinDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhThongTinDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CauHinhThongTinDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCauHinhThongTinDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhThongTinDuongSu using partial update
        CauHinhThongTinDuongSu partialUpdatedCauHinhThongTinDuongSu = new CauHinhThongTinDuongSu();
        partialUpdatedCauHinhThongTinDuongSu.setIdCauHinh(cauHinhThongTinDuongSu.getIdCauHinh());

        partialUpdatedCauHinhThongTinDuongSu.css(UPDATED_CSS).trangThai(UPDATED_TRANG_THAI);

        restCauHinhThongTinDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCauHinhThongTinDuongSu.getIdCauHinh())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCauHinhThongTinDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhThongTinDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCauHinhThongTinDuongSuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCauHinhThongTinDuongSu, cauHinhThongTinDuongSu),
            getPersistedCauHinhThongTinDuongSu(cauHinhThongTinDuongSu)
        );
    }

    @Test
    @Transactional
    void fullUpdateCauHinhThongTinDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhThongTinDuongSu using partial update
        CauHinhThongTinDuongSu partialUpdatedCauHinhThongTinDuongSu = new CauHinhThongTinDuongSu();
        partialUpdatedCauHinhThongTinDuongSu.setIdCauHinh(cauHinhThongTinDuongSu.getIdCauHinh());

        partialUpdatedCauHinhThongTinDuongSu
            .noiDung(UPDATED_NOI_DUNG)
            .javascript(UPDATED_JAVASCRIPT)
            .css(UPDATED_CSS)
            .idLoaiDs(UPDATED_ID_LOAI_DS)
            .idDonVi(UPDATED_ID_DON_VI)
            .trangThai(UPDATED_TRANG_THAI);

        restCauHinhThongTinDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCauHinhThongTinDuongSu.getIdCauHinh())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCauHinhThongTinDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhThongTinDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCauHinhThongTinDuongSuUpdatableFieldsEquals(
            partialUpdatedCauHinhThongTinDuongSu,
            getPersistedCauHinhThongTinDuongSu(partialUpdatedCauHinhThongTinDuongSu)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCauHinhThongTinDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinDuongSu.setIdCauHinh(longCount.incrementAndGet());

        // Create the CauHinhThongTinDuongSu
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCauHinhThongTinDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cauHinhThongTinDuongSuDTO.getIdCauHinh())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cauHinhThongTinDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhThongTinDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCauHinhThongTinDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinDuongSu.setIdCauHinh(longCount.incrementAndGet());

        // Create the CauHinhThongTinDuongSu
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhThongTinDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cauHinhThongTinDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhThongTinDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCauHinhThongTinDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhThongTinDuongSu.setIdCauHinh(longCount.incrementAndGet());

        // Create the CauHinhThongTinDuongSu
        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO = cauHinhThongTinDuongSuMapper.toDto(cauHinhThongTinDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhThongTinDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cauHinhThongTinDuongSuDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CauHinhThongTinDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCauHinhThongTinDuongSu() throws Exception {
        // Initialize the database
        insertedCauHinhThongTinDuongSu = cauHinhThongTinDuongSuRepository.saveAndFlush(cauHinhThongTinDuongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cauHinhThongTinDuongSu
        restCauHinhThongTinDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, cauHinhThongTinDuongSu.getIdCauHinh()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cauHinhThongTinDuongSuRepository.count();
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

    protected CauHinhThongTinDuongSu getPersistedCauHinhThongTinDuongSu(CauHinhThongTinDuongSu cauHinhThongTinDuongSu) {
        return cauHinhThongTinDuongSuRepository.findById(cauHinhThongTinDuongSu.getIdCauHinh()).orElseThrow();
    }

    protected void assertPersistedCauHinhThongTinDuongSuToMatchAllProperties(CauHinhThongTinDuongSu expectedCauHinhThongTinDuongSu) {
        assertCauHinhThongTinDuongSuAllPropertiesEquals(
            expectedCauHinhThongTinDuongSu,
            getPersistedCauHinhThongTinDuongSu(expectedCauHinhThongTinDuongSu)
        );
    }

    protected void assertPersistedCauHinhThongTinDuongSuToMatchUpdatableProperties(CauHinhThongTinDuongSu expectedCauHinhThongTinDuongSu) {
        assertCauHinhThongTinDuongSuAllUpdatablePropertiesEquals(
            expectedCauHinhThongTinDuongSu,
            getPersistedCauHinhThongTinDuongSu(expectedCauHinhThongTinDuongSu)
        );
    }
}
