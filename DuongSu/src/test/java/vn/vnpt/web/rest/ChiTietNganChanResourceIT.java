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

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_LOAI_DOI_TUONG = 1L;
    private static final Long UPDATED_LOAI_DOI_TUONG = 2L;

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

    private static final LocalDate DEFAULT_NGAY_BD_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_BD_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NGAY_KT_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KT_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final Long DEFAULT_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_LOAI_NGAN_CHAN = 2L;

    private static final LocalDate DEFAULT_NGAY_CONG_VAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_CONG_VAN = LocalDate.now(ZoneId.systemDefault());

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
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .trangThai(UPDATED_TRANG_THAI);

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
