package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucDauSoCmndAsserts.*;
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
import vn.vnpt.domain.DanhMucDauSoCmnd;
import vn.vnpt.repository.DanhMucDauSoCmndRepository;
import vn.vnpt.service.dto.DanhMucDauSoCmndDTO;
import vn.vnpt.service.mapper.DanhMucDauSoCmndMapper;

/**
 * Integration tests for the {@link DanhMucDauSoCmndResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucDauSoCmndResourceIT {

    private static final String DEFAULT_DAU_SO = "AAAAAAAAAA";
    private static final String UPDATED_DAU_SO = "BBBBBBBBBB";

    private static final String DEFAULT_TINH_THANH = "AAAAAAAAAA";
    private static final String UPDATED_TINH_THANH = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_LOAI = 1L;
    private static final Long UPDATED_ID_LOAI = 2L;
    private static final Long SMALLER_ID_LOAI = 1L - 1L;

    private static final String ENTITY_API_URL = "/api/danh-muc-dau-so-cmnds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idDauSo}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucDauSoCmndRepository danhMucDauSoCmndRepository;

    @Autowired
    private DanhMucDauSoCmndMapper danhMucDauSoCmndMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucDauSoCmndMockMvc;

    private DanhMucDauSoCmnd danhMucDauSoCmnd;

    private DanhMucDauSoCmnd insertedDanhMucDauSoCmnd;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucDauSoCmnd createEntity() {
        return new DanhMucDauSoCmnd().dauSo(DEFAULT_DAU_SO).tinhThanh(DEFAULT_TINH_THANH).idLoai(DEFAULT_ID_LOAI);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucDauSoCmnd createUpdatedEntity() {
        return new DanhMucDauSoCmnd().dauSo(UPDATED_DAU_SO).tinhThanh(UPDATED_TINH_THANH).idLoai(UPDATED_ID_LOAI);
    }

    @BeforeEach
    public void initTest() {
        danhMucDauSoCmnd = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucDauSoCmnd != null) {
            danhMucDauSoCmndRepository.delete(insertedDanhMucDauSoCmnd);
            insertedDanhMucDauSoCmnd = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucDauSoCmnd() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucDauSoCmnd
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO = danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);
        var returnedDanhMucDauSoCmndDTO = om.readValue(
            restDanhMucDauSoCmndMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucDauSoCmndDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucDauSoCmndDTO.class
        );

        // Validate the DanhMucDauSoCmnd in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucDauSoCmnd = danhMucDauSoCmndMapper.toEntity(returnedDanhMucDauSoCmndDTO);
        assertDanhMucDauSoCmndUpdatableFieldsEquals(returnedDanhMucDauSoCmnd, getPersistedDanhMucDauSoCmnd(returnedDanhMucDauSoCmnd));

        insertedDanhMucDauSoCmnd = returnedDanhMucDauSoCmnd;
    }

    @Test
    @Transactional
    void createDanhMucDauSoCmndWithExistingId() throws Exception {
        // Create the DanhMucDauSoCmnd with an existing ID
        danhMucDauSoCmnd.setIdDauSo(1L);
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO = danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucDauSoCmndMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucDauSoCmndDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucDauSoCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmnds() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList
        restDanhMucDauSoCmndMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDauSo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idDauSo").value(hasItem(danhMucDauSoCmnd.getIdDauSo().intValue())))
            .andExpect(jsonPath("$.[*].dauSo").value(hasItem(DEFAULT_DAU_SO)))
            .andExpect(jsonPath("$.[*].tinhThanh").value(hasItem(DEFAULT_TINH_THANH)))
            .andExpect(jsonPath("$.[*].idLoai").value(hasItem(DEFAULT_ID_LOAI.intValue())));
    }

    @Test
    @Transactional
    void getDanhMucDauSoCmnd() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get the danhMucDauSoCmnd
        restDanhMucDauSoCmndMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucDauSoCmnd.getIdDauSo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idDauSo").value(danhMucDauSoCmnd.getIdDauSo().intValue()))
            .andExpect(jsonPath("$.dauSo").value(DEFAULT_DAU_SO))
            .andExpect(jsonPath("$.tinhThanh").value(DEFAULT_TINH_THANH))
            .andExpect(jsonPath("$.idLoai").value(DEFAULT_ID_LOAI.intValue()));
    }

    @Test
    @Transactional
    void getDanhMucDauSoCmndsByIdFiltering() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        Long id = danhMucDauSoCmnd.getIdDauSo();

        defaultDanhMucDauSoCmndFiltering("idDauSo.equals=" + id, "idDauSo.notEquals=" + id);

        defaultDanhMucDauSoCmndFiltering("idDauSo.greaterThanOrEqual=" + id, "idDauSo.greaterThan=" + id);

        defaultDanhMucDauSoCmndFiltering("idDauSo.lessThanOrEqual=" + id, "idDauSo.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByDauSoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where dauSo equals to
        defaultDanhMucDauSoCmndFiltering("dauSo.equals=" + DEFAULT_DAU_SO, "dauSo.equals=" + UPDATED_DAU_SO);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByDauSoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where dauSo in
        defaultDanhMucDauSoCmndFiltering("dauSo.in=" + DEFAULT_DAU_SO + "," + UPDATED_DAU_SO, "dauSo.in=" + UPDATED_DAU_SO);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByDauSoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where dauSo is not null
        defaultDanhMucDauSoCmndFiltering("dauSo.specified=true", "dauSo.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByDauSoContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where dauSo contains
        defaultDanhMucDauSoCmndFiltering("dauSo.contains=" + DEFAULT_DAU_SO, "dauSo.contains=" + UPDATED_DAU_SO);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByDauSoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where dauSo does not contain
        defaultDanhMucDauSoCmndFiltering("dauSo.doesNotContain=" + UPDATED_DAU_SO, "dauSo.doesNotContain=" + DEFAULT_DAU_SO);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByTinhThanhIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where tinhThanh equals to
        defaultDanhMucDauSoCmndFiltering("tinhThanh.equals=" + DEFAULT_TINH_THANH, "tinhThanh.equals=" + UPDATED_TINH_THANH);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByTinhThanhIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where tinhThanh in
        defaultDanhMucDauSoCmndFiltering(
            "tinhThanh.in=" + DEFAULT_TINH_THANH + "," + UPDATED_TINH_THANH,
            "tinhThanh.in=" + UPDATED_TINH_THANH
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByTinhThanhIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where tinhThanh is not null
        defaultDanhMucDauSoCmndFiltering("tinhThanh.specified=true", "tinhThanh.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByTinhThanhContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where tinhThanh contains
        defaultDanhMucDauSoCmndFiltering("tinhThanh.contains=" + DEFAULT_TINH_THANH, "tinhThanh.contains=" + UPDATED_TINH_THANH);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByTinhThanhNotContainsSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where tinhThanh does not contain
        defaultDanhMucDauSoCmndFiltering(
            "tinhThanh.doesNotContain=" + UPDATED_TINH_THANH,
            "tinhThanh.doesNotContain=" + DEFAULT_TINH_THANH
        );
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByIdLoaiIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where idLoai equals to
        defaultDanhMucDauSoCmndFiltering("idLoai.equals=" + DEFAULT_ID_LOAI, "idLoai.equals=" + UPDATED_ID_LOAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByIdLoaiIsInShouldWork() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where idLoai in
        defaultDanhMucDauSoCmndFiltering("idLoai.in=" + DEFAULT_ID_LOAI + "," + UPDATED_ID_LOAI, "idLoai.in=" + UPDATED_ID_LOAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByIdLoaiIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where idLoai is not null
        defaultDanhMucDauSoCmndFiltering("idLoai.specified=true", "idLoai.specified=false");
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByIdLoaiIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where idLoai is greater than or equal to
        defaultDanhMucDauSoCmndFiltering("idLoai.greaterThanOrEqual=" + DEFAULT_ID_LOAI, "idLoai.greaterThanOrEqual=" + UPDATED_ID_LOAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByIdLoaiIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where idLoai is less than or equal to
        defaultDanhMucDauSoCmndFiltering("idLoai.lessThanOrEqual=" + DEFAULT_ID_LOAI, "idLoai.lessThanOrEqual=" + SMALLER_ID_LOAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByIdLoaiIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where idLoai is less than
        defaultDanhMucDauSoCmndFiltering("idLoai.lessThan=" + UPDATED_ID_LOAI, "idLoai.lessThan=" + DEFAULT_ID_LOAI);
    }

    @Test
    @Transactional
    void getAllDanhMucDauSoCmndsByIdLoaiIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        // Get all the danhMucDauSoCmndList where idLoai is greater than
        defaultDanhMucDauSoCmndFiltering("idLoai.greaterThan=" + SMALLER_ID_LOAI, "idLoai.greaterThan=" + DEFAULT_ID_LOAI);
    }

    private void defaultDanhMucDauSoCmndFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultDanhMucDauSoCmndShouldBeFound(shouldBeFound);
        defaultDanhMucDauSoCmndShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDanhMucDauSoCmndShouldBeFound(String filter) throws Exception {
        restDanhMucDauSoCmndMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDauSo,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idDauSo").value(hasItem(danhMucDauSoCmnd.getIdDauSo().intValue())))
            .andExpect(jsonPath("$.[*].dauSo").value(hasItem(DEFAULT_DAU_SO)))
            .andExpect(jsonPath("$.[*].tinhThanh").value(hasItem(DEFAULT_TINH_THANH)))
            .andExpect(jsonPath("$.[*].idLoai").value(hasItem(DEFAULT_ID_LOAI.intValue())));

        // Check, that the count call also returns 1
        restDanhMucDauSoCmndMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idDauSo,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDanhMucDauSoCmndShouldNotBeFound(String filter) throws Exception {
        restDanhMucDauSoCmndMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDauSo,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDanhMucDauSoCmndMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=idDauSo,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucDauSoCmnd() throws Exception {
        // Get the danhMucDauSoCmnd
        restDanhMucDauSoCmndMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucDauSoCmnd() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucDauSoCmnd
        DanhMucDauSoCmnd updatedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.findById(danhMucDauSoCmnd.getIdDauSo()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucDauSoCmnd are not directly saved in db
        em.detach(updatedDanhMucDauSoCmnd);
        updatedDanhMucDauSoCmnd.dauSo(UPDATED_DAU_SO).tinhThanh(UPDATED_TINH_THANH).idLoai(UPDATED_ID_LOAI);
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO = danhMucDauSoCmndMapper.toDto(updatedDanhMucDauSoCmnd);

        restDanhMucDauSoCmndMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucDauSoCmndDTO.getIdDauSo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucDauSoCmndDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucDauSoCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucDauSoCmndToMatchAllProperties(updatedDanhMucDauSoCmnd);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucDauSoCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDauSoCmnd.setIdDauSo(longCount.incrementAndGet());

        // Create the DanhMucDauSoCmnd
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO = danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucDauSoCmndMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucDauSoCmndDTO.getIdDauSo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucDauSoCmndDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucDauSoCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucDauSoCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDauSoCmnd.setIdDauSo(longCount.incrementAndGet());

        // Create the DanhMucDauSoCmnd
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO = danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucDauSoCmndMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucDauSoCmndDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucDauSoCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucDauSoCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDauSoCmnd.setIdDauSo(longCount.incrementAndGet());

        // Create the DanhMucDauSoCmnd
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO = danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucDauSoCmndMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucDauSoCmndDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucDauSoCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucDauSoCmndWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucDauSoCmnd using partial update
        DanhMucDauSoCmnd partialUpdatedDanhMucDauSoCmnd = new DanhMucDauSoCmnd();
        partialUpdatedDanhMucDauSoCmnd.setIdDauSo(danhMucDauSoCmnd.getIdDauSo());

        partialUpdatedDanhMucDauSoCmnd.idLoai(UPDATED_ID_LOAI);

        restDanhMucDauSoCmndMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucDauSoCmnd.getIdDauSo())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucDauSoCmnd))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucDauSoCmnd in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucDauSoCmndUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucDauSoCmnd, danhMucDauSoCmnd),
            getPersistedDanhMucDauSoCmnd(danhMucDauSoCmnd)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucDauSoCmndWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucDauSoCmnd using partial update
        DanhMucDauSoCmnd partialUpdatedDanhMucDauSoCmnd = new DanhMucDauSoCmnd();
        partialUpdatedDanhMucDauSoCmnd.setIdDauSo(danhMucDauSoCmnd.getIdDauSo());

        partialUpdatedDanhMucDauSoCmnd.dauSo(UPDATED_DAU_SO).tinhThanh(UPDATED_TINH_THANH).idLoai(UPDATED_ID_LOAI);

        restDanhMucDauSoCmndMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucDauSoCmnd.getIdDauSo())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucDauSoCmnd))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucDauSoCmnd in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucDauSoCmndUpdatableFieldsEquals(
            partialUpdatedDanhMucDauSoCmnd,
            getPersistedDanhMucDauSoCmnd(partialUpdatedDanhMucDauSoCmnd)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucDauSoCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDauSoCmnd.setIdDauSo(longCount.incrementAndGet());

        // Create the DanhMucDauSoCmnd
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO = danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucDauSoCmndMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucDauSoCmndDTO.getIdDauSo())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucDauSoCmndDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucDauSoCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucDauSoCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDauSoCmnd.setIdDauSo(longCount.incrementAndGet());

        // Create the DanhMucDauSoCmnd
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO = danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucDauSoCmndMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucDauSoCmndDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucDauSoCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucDauSoCmnd() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucDauSoCmnd.setIdDauSo(longCount.incrementAndGet());

        // Create the DanhMucDauSoCmnd
        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO = danhMucDauSoCmndMapper.toDto(danhMucDauSoCmnd);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucDauSoCmndMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucDauSoCmndDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucDauSoCmnd in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucDauSoCmnd() throws Exception {
        // Initialize the database
        insertedDanhMucDauSoCmnd = danhMucDauSoCmndRepository.saveAndFlush(danhMucDauSoCmnd);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucDauSoCmnd
        restDanhMucDauSoCmndMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucDauSoCmnd.getIdDauSo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucDauSoCmndRepository.count();
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

    protected DanhMucDauSoCmnd getPersistedDanhMucDauSoCmnd(DanhMucDauSoCmnd danhMucDauSoCmnd) {
        return danhMucDauSoCmndRepository.findById(danhMucDauSoCmnd.getIdDauSo()).orElseThrow();
    }

    protected void assertPersistedDanhMucDauSoCmndToMatchAllProperties(DanhMucDauSoCmnd expectedDanhMucDauSoCmnd) {
        assertDanhMucDauSoCmndAllPropertiesEquals(expectedDanhMucDauSoCmnd, getPersistedDanhMucDauSoCmnd(expectedDanhMucDauSoCmnd));
    }

    protected void assertPersistedDanhMucDauSoCmndToMatchUpdatableProperties(DanhMucDauSoCmnd expectedDanhMucDauSoCmnd) {
        assertDanhMucDauSoCmndAllUpdatablePropertiesEquals(
            expectedDanhMucDauSoCmnd,
            getPersistedDanhMucDauSoCmnd(expectedDanhMucDauSoCmnd)
        );
    }
}
