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
