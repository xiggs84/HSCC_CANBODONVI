package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.QuanHeDuongSuAsserts.*;
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
import vn.vnpt.domain.DuongSu;
import vn.vnpt.domain.QuanHeDuongSu;
import vn.vnpt.repository.QuanHeDuongSuRepository;
import vn.vnpt.service.dto.QuanHeDuongSuDTO;
import vn.vnpt.service.mapper.QuanHeDuongSuMapper;

/**
 * Integration tests for the {@link QuanHeDuongSuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuanHeDuongSuResourceIT {

    private static final Long DEFAULT_ID_DUONG_SU_QH = 1L;
    private static final Long UPDATED_ID_DUONG_SU_QH = 2L;
    private static final Long SMALLER_ID_DUONG_SU_QH = 1L - 1L;

    private static final String DEFAULT_THONG_TIN_QUAN_HE = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_QUAN_HE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANG_THAI = 0;
    private static final Integer UPDATED_TRANG_THAI = 1;
    private static final Integer SMALLER_TRANG_THAI = 0 - 1;

    private static final String ENTITY_API_URL = "/api/quan-he-duong-sus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idQuanHe}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QuanHeDuongSuRepository quanHeDuongSuRepository;

    @Autowired
    private QuanHeDuongSuMapper quanHeDuongSuMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuanHeDuongSuMockMvc;

    private QuanHeDuongSu quanHeDuongSu;

    private QuanHeDuongSu insertedQuanHeDuongSu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuanHeDuongSu createEntity() {
        return new QuanHeDuongSu()
            .idDuongSuQh(DEFAULT_ID_DUONG_SU_QH)
            .thongTinQuanHe(DEFAULT_THONG_TIN_QUAN_HE)
            .trangThai(DEFAULT_TRANG_THAI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuanHeDuongSu createUpdatedEntity() {
        return new QuanHeDuongSu()
            .idDuongSuQh(UPDATED_ID_DUONG_SU_QH)
            .thongTinQuanHe(UPDATED_THONG_TIN_QUAN_HE)
            .trangThai(UPDATED_TRANG_THAI);
    }

    @BeforeEach
    public void initTest() {
        quanHeDuongSu = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedQuanHeDuongSu != null) {
            quanHeDuongSuRepository.delete(insertedQuanHeDuongSu);
            insertedQuanHeDuongSu = null;
        }
    }

    @Test
    @Transactional
    void createQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);
        var returnedQuanHeDuongSuDTO = om.readValue(
            restQuanHeDuongSuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeDuongSuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QuanHeDuongSuDTO.class
        );

        // Validate the QuanHeDuongSu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedQuanHeDuongSu = quanHeDuongSuMapper.toEntity(returnedQuanHeDuongSuDTO);
        assertQuanHeDuongSuUpdatableFieldsEquals(returnedQuanHeDuongSu, getPersistedQuanHeDuongSu(returnedQuanHeDuongSu));

        insertedQuanHeDuongSu = returnedQuanHeDuongSu;
    }

    @Test
    @Transactional
    void createQuanHeDuongSuWithExistingId() throws Exception {
        // Create the QuanHeDuongSu with an existing ID
        quanHeDuongSu.setIdQuanHe(1L);
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuanHeDuongSuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeDuongSuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSus() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList
        restQuanHeDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idQuanHe,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idQuanHe").value(hasItem(quanHeDuongSu.getIdQuanHe().intValue())))
            .andExpect(jsonPath("$.[*].idDuongSuQh").value(hasItem(DEFAULT_ID_DUONG_SU_QH.intValue())))
            .andExpect(jsonPath("$.[*].thongTinQuanHe").value(hasItem(DEFAULT_THONG_TIN_QUAN_HE.toString())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)));
    }

    @Test
    @Transactional
    void getQuanHeDuongSu() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get the quanHeDuongSu
        restQuanHeDuongSuMockMvc
            .perform(get(ENTITY_API_URL_ID, quanHeDuongSu.getIdQuanHe()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idQuanHe").value(quanHeDuongSu.getIdQuanHe().intValue()))
            .andExpect(jsonPath("$.idDuongSuQh").value(DEFAULT_ID_DUONG_SU_QH.intValue()))
            .andExpect(jsonPath("$.thongTinQuanHe").value(DEFAULT_THONG_TIN_QUAN_HE.toString()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI));
    }

    @Test
    @Transactional
    void getQuanHeDuongSusByIdFiltering() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        Long id = quanHeDuongSu.getIdQuanHe();

        defaultQuanHeDuongSuFiltering("idQuanHe.equals=" + id, "idQuanHe.notEquals=" + id);

        defaultQuanHeDuongSuFiltering("idQuanHe.greaterThanOrEqual=" + id, "idQuanHe.greaterThan=" + id);

        defaultQuanHeDuongSuFiltering("idQuanHe.lessThanOrEqual=" + id, "idQuanHe.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByIdDuongSuQhIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where idDuongSuQh equals to
        defaultQuanHeDuongSuFiltering("idDuongSuQh.equals=" + DEFAULT_ID_DUONG_SU_QH, "idDuongSuQh.equals=" + UPDATED_ID_DUONG_SU_QH);
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByIdDuongSuQhIsInShouldWork() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where idDuongSuQh in
        defaultQuanHeDuongSuFiltering(
            "idDuongSuQh.in=" + DEFAULT_ID_DUONG_SU_QH + "," + UPDATED_ID_DUONG_SU_QH,
            "idDuongSuQh.in=" + UPDATED_ID_DUONG_SU_QH
        );
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByIdDuongSuQhIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where idDuongSuQh is not null
        defaultQuanHeDuongSuFiltering("idDuongSuQh.specified=true", "idDuongSuQh.specified=false");
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByIdDuongSuQhIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where idDuongSuQh is greater than or equal to
        defaultQuanHeDuongSuFiltering(
            "idDuongSuQh.greaterThanOrEqual=" + DEFAULT_ID_DUONG_SU_QH,
            "idDuongSuQh.greaterThanOrEqual=" + UPDATED_ID_DUONG_SU_QH
        );
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByIdDuongSuQhIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where idDuongSuQh is less than or equal to
        defaultQuanHeDuongSuFiltering(
            "idDuongSuQh.lessThanOrEqual=" + DEFAULT_ID_DUONG_SU_QH,
            "idDuongSuQh.lessThanOrEqual=" + SMALLER_ID_DUONG_SU_QH
        );
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByIdDuongSuQhIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where idDuongSuQh is less than
        defaultQuanHeDuongSuFiltering("idDuongSuQh.lessThan=" + UPDATED_ID_DUONG_SU_QH, "idDuongSuQh.lessThan=" + DEFAULT_ID_DUONG_SU_QH);
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByIdDuongSuQhIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where idDuongSuQh is greater than
        defaultQuanHeDuongSuFiltering(
            "idDuongSuQh.greaterThan=" + SMALLER_ID_DUONG_SU_QH,
            "idDuongSuQh.greaterThan=" + DEFAULT_ID_DUONG_SU_QH
        );
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByTrangThaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where trangThai equals to
        defaultQuanHeDuongSuFiltering("trangThai.equals=" + DEFAULT_TRANG_THAI, "trangThai.equals=" + UPDATED_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByTrangThaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where trangThai in
        defaultQuanHeDuongSuFiltering(
            "trangThai.in=" + DEFAULT_TRANG_THAI + "," + UPDATED_TRANG_THAI,
            "trangThai.in=" + UPDATED_TRANG_THAI
        );
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByTrangThaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where trangThai is not null
        defaultQuanHeDuongSuFiltering("trangThai.specified=true", "trangThai.specified=false");
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByTrangThaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where trangThai is greater than or equal to
        defaultQuanHeDuongSuFiltering(
            "trangThai.greaterThanOrEqual=" + DEFAULT_TRANG_THAI,
            "trangThai.greaterThanOrEqual=" + (DEFAULT_TRANG_THAI + 1)
        );
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByTrangThaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where trangThai is less than or equal to
        defaultQuanHeDuongSuFiltering("trangThai.lessThanOrEqual=" + DEFAULT_TRANG_THAI, "trangThai.lessThanOrEqual=" + SMALLER_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByTrangThaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where trangThai is less than
        defaultQuanHeDuongSuFiltering("trangThai.lessThan=" + (DEFAULT_TRANG_THAI + 1), "trangThai.lessThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByTrangThaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        // Get all the quanHeDuongSuList where trangThai is greater than
        defaultQuanHeDuongSuFiltering("trangThai.greaterThan=" + SMALLER_TRANG_THAI, "trangThai.greaterThan=" + DEFAULT_TRANG_THAI);
    }

    @Test
    @Transactional
    void getAllQuanHeDuongSusByDuongSuIsEqualToSomething() throws Exception {
        DuongSu duongSu;
        if (TestUtil.findAll(em, DuongSu.class).isEmpty()) {
            quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);
            duongSu = DuongSuResourceIT.createEntity();
        } else {
            duongSu = TestUtil.findAll(em, DuongSu.class).get(0);
        }
        em.persist(duongSu);
        em.flush();
        quanHeDuongSu.setDuongSu(duongSu);
        quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);
        Long duongSuId = duongSu.getIdDuongSu();
        // Get all the quanHeDuongSuList where duongSu equals to duongSuId
        defaultQuanHeDuongSuShouldBeFound("duongSuId.equals=" + duongSuId);

        // Get all the quanHeDuongSuList where duongSu equals to (duongSuId + 1)
        defaultQuanHeDuongSuShouldNotBeFound("duongSuId.equals=" + (duongSuId + 1));
    }

    private void defaultQuanHeDuongSuFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultQuanHeDuongSuShouldBeFound(shouldBeFound);
        defaultQuanHeDuongSuShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultQuanHeDuongSuShouldBeFound(String filter) throws Exception {
        restQuanHeDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idQuanHe,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idQuanHe").value(hasItem(quanHeDuongSu.getIdQuanHe().intValue())))
            .andExpect(jsonPath("$.[*].idDuongSuQh").value(hasItem(DEFAULT_ID_DUONG_SU_QH.intValue())))
            .andExpect(jsonPath("$.[*].thongTinQuanHe").value(hasItem(DEFAULT_THONG_TIN_QUAN_HE.toString())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI)));

        // Check, that the count call also returns 1
        restQuanHeDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idQuanHe,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultQuanHeDuongSuShouldNotBeFound(String filter) throws Exception {
        restQuanHeDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idQuanHe,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restQuanHeDuongSuMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idQuanHe,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingQuanHeDuongSu() throws Exception {
        // Get the quanHeDuongSu
        restQuanHeDuongSuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuanHeDuongSu() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeDuongSu
        QuanHeDuongSu updatedQuanHeDuongSu = quanHeDuongSuRepository.findById(quanHeDuongSu.getIdQuanHe()).orElseThrow();
        // Disconnect from session so that the updates on updatedQuanHeDuongSu are not directly saved in db
        em.detach(updatedQuanHeDuongSu);
        updatedQuanHeDuongSu.idDuongSuQh(UPDATED_ID_DUONG_SU_QH).thongTinQuanHe(UPDATED_THONG_TIN_QUAN_HE).trangThai(UPDATED_TRANG_THAI);
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(updatedQuanHeDuongSu);

        restQuanHeDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quanHeDuongSuDTO.getIdQuanHe())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeDuongSuDTO))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQuanHeDuongSuToMatchAllProperties(updatedQuanHeDuongSu);
    }

    @Test
    @Transactional
    void putNonExistingQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quanHeDuongSuDTO.getIdQuanHe())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(quanHeDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(quanHeDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuanHeDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeDuongSu using partial update
        QuanHeDuongSu partialUpdatedQuanHeDuongSu = new QuanHeDuongSu();
        partialUpdatedQuanHeDuongSu.setIdQuanHe(quanHeDuongSu.getIdQuanHe());

        partialUpdatedQuanHeDuongSu.idDuongSuQh(UPDATED_ID_DUONG_SU_QH);

        restQuanHeDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuanHeDuongSu.getIdQuanHe())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuanHeDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuanHeDuongSuUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQuanHeDuongSu, quanHeDuongSu),
            getPersistedQuanHeDuongSu(quanHeDuongSu)
        );
    }

    @Test
    @Transactional
    void fullUpdateQuanHeDuongSuWithPatch() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quanHeDuongSu using partial update
        QuanHeDuongSu partialUpdatedQuanHeDuongSu = new QuanHeDuongSu();
        partialUpdatedQuanHeDuongSu.setIdQuanHe(quanHeDuongSu.getIdQuanHe());

        partialUpdatedQuanHeDuongSu
            .idDuongSuQh(UPDATED_ID_DUONG_SU_QH)
            .thongTinQuanHe(UPDATED_THONG_TIN_QUAN_HE)
            .trangThai(UPDATED_TRANG_THAI);

        restQuanHeDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuanHeDuongSu.getIdQuanHe())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuanHeDuongSu))
            )
            .andExpect(status().isOk());

        // Validate the QuanHeDuongSu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuanHeDuongSuUpdatableFieldsEquals(partialUpdatedQuanHeDuongSu, getPersistedQuanHeDuongSu(partialUpdatedQuanHeDuongSu));
    }

    @Test
    @Transactional
    void patchNonExistingQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, quanHeDuongSuDTO.getIdQuanHe())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(quanHeDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(quanHeDuongSuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuanHeDuongSu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quanHeDuongSu.setIdQuanHe(longCount.incrementAndGet());

        // Create the QuanHeDuongSu
        QuanHeDuongSuDTO quanHeDuongSuDTO = quanHeDuongSuMapper.toDto(quanHeDuongSu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuanHeDuongSuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(quanHeDuongSuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuanHeDuongSu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuanHeDuongSu() throws Exception {
        // Initialize the database
        insertedQuanHeDuongSu = quanHeDuongSuRepository.saveAndFlush(quanHeDuongSu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the quanHeDuongSu
        restQuanHeDuongSuMockMvc
            .perform(delete(ENTITY_API_URL_ID, quanHeDuongSu.getIdQuanHe()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return quanHeDuongSuRepository.count();
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

    protected QuanHeDuongSu getPersistedQuanHeDuongSu(QuanHeDuongSu quanHeDuongSu) {
        return quanHeDuongSuRepository.findById(quanHeDuongSu.getIdQuanHe()).orElseThrow();
    }

    protected void assertPersistedQuanHeDuongSuToMatchAllProperties(QuanHeDuongSu expectedQuanHeDuongSu) {
        assertQuanHeDuongSuAllPropertiesEquals(expectedQuanHeDuongSu, getPersistedQuanHeDuongSu(expectedQuanHeDuongSu));
    }

    protected void assertPersistedQuanHeDuongSuToMatchUpdatableProperties(QuanHeDuongSu expectedQuanHeDuongSu) {
        assertQuanHeDuongSuAllUpdatablePropertiesEquals(expectedQuanHeDuongSu, getPersistedQuanHeDuongSu(expectedQuanHeDuongSu));
    }
}
