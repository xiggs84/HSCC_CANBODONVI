package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DmTaiSanAsserts.*;
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
import vn.vnpt.domain.DmTaiSan;
import vn.vnpt.repository.DmTaiSanRepository;
import vn.vnpt.service.dto.DmTaiSanDTO;
import vn.vnpt.service.mapper.DmTaiSanMapper;

/**
 * Integration tests for the {@link DmTaiSanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DmTaiSanResourceIT {

    private static final String DEFAULT_TEN_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TAI_SAN = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String DEFAULT_THONG_TIN_TS = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_TS = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final Long DEFAULT_ID_DUONG_SU = 1L;
    private static final Long UPDATED_ID_DUONG_SU = 2L;

    private static final Long DEFAULT_ID_TS_GOC = 1L;
    private static final Long UPDATED_ID_TS_GOC = 2L;

    private static final String DEFAULT_MA_TAI_SAN = "AAAAAAAAAA";
    private static final String UPDATED_MA_TAI_SAN = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_ID_LOAI_NGAN_CHAN = 2L;

    private static final LocalDate DEFAULT_NGAY_BD_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_BD_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_NGAY_KT_NGAN_CHAN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_KT_NGAN_CHAN = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_MASTER = 1L;
    private static final Long UPDATED_ID_MASTER = 2L;

    private static final String DEFAULT_STR_SEARCH = "AAAAAAAAAA";
    private static final String UPDATED_STR_SEARCH = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final Long DEFAULT_SO_HS_CV = 1L;
    private static final Long UPDATED_SO_HS_CV = 2L;

    private static final Long DEFAULT_SO_CC = 1L;
    private static final Long UPDATED_SO_CC = 2L;

    private static final Long DEFAULT_SO_VAO_SO = 1L;
    private static final Long UPDATED_SO_VAO_SO = 2L;

    private static final String DEFAULT_MO_TA = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA = "BBBBBBBBBB";

    private static final Long DEFAULT_LOAI_NGAN_CHAN = 1L;
    private static final Long UPDATED_LOAI_NGAN_CHAN = 2L;

    private static final String ENTITY_API_URL = "/api/dm-tai-sans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idTaiSan}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DmTaiSanRepository dmTaiSanRepository;

    @Autowired
    private DmTaiSanMapper dmTaiSanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDmTaiSanMockMvc;

    private DmTaiSan dmTaiSan;

    private DmTaiSan insertedDmTaiSan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmTaiSan createEntity() {
        return new DmTaiSan()
            .tenTaiSan(DEFAULT_TEN_TAI_SAN)
            .trangThai(DEFAULT_TRANG_THAI)
            .thongTinTs(DEFAULT_THONG_TIN_TS)
            .ghiChu(DEFAULT_GHI_CHU)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .idDuongSu(DEFAULT_ID_DUONG_SU)
            .idTsGoc(DEFAULT_ID_TS_GOC)
            .maTaiSan(DEFAULT_MA_TAI_SAN)
            .idLoaiNganChan(DEFAULT_ID_LOAI_NGAN_CHAN)
            .ngayBdNganChan(DEFAULT_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(DEFAULT_NGAY_KT_NGAN_CHAN)
            .idMaster(DEFAULT_ID_MASTER)
            .strSearch(DEFAULT_STR_SEARCH)
            .idDonVi(DEFAULT_ID_DON_VI)
            .soHsCv(DEFAULT_SO_HS_CV)
            .soCc(DEFAULT_SO_CC)
            .soVaoSo(DEFAULT_SO_VAO_SO)
            .moTa(DEFAULT_MO_TA)
            .loaiNganChan(DEFAULT_LOAI_NGAN_CHAN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmTaiSan createUpdatedEntity() {
        return new DmTaiSan()
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .maTaiSan(UPDATED_MA_TAI_SAN)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .idMaster(UPDATED_ID_MASTER)
            .strSearch(UPDATED_STR_SEARCH)
            .idDonVi(UPDATED_ID_DON_VI)
            .soHsCv(UPDATED_SO_HS_CV)
            .soCc(UPDATED_SO_CC)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN);
    }

    @BeforeEach
    public void initTest() {
        dmTaiSan = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDmTaiSan != null) {
            dmTaiSanRepository.delete(insertedDmTaiSan);
            insertedDmTaiSan = null;
        }
    }

    @Test
    @Transactional
    void createDmTaiSan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DmTaiSan
        DmTaiSanDTO dmTaiSanDTO = dmTaiSanMapper.toDto(dmTaiSan);
        var returnedDmTaiSanDTO = om.readValue(
            restDmTaiSanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmTaiSanDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DmTaiSanDTO.class
        );

        // Validate the DmTaiSan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDmTaiSan = dmTaiSanMapper.toEntity(returnedDmTaiSanDTO);
        assertDmTaiSanUpdatableFieldsEquals(returnedDmTaiSan, getPersistedDmTaiSan(returnedDmTaiSan));

        insertedDmTaiSan = returnedDmTaiSan;
    }

    @Test
    @Transactional
    void createDmTaiSanWithExistingId() throws Exception {
        // Create the DmTaiSan with an existing ID
        dmTaiSan.setIdTaiSan(1L);
        DmTaiSanDTO dmTaiSanDTO = dmTaiSanMapper.toDto(dmTaiSan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmTaiSanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmTaiSanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DmTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDmTaiSans() throws Exception {
        // Initialize the database
        insertedDmTaiSan = dmTaiSanRepository.saveAndFlush(dmTaiSan);

        // Get all the dmTaiSanList
        restDmTaiSanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idTaiSan,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idTaiSan").value(hasItem(dmTaiSan.getIdTaiSan().intValue())))
            .andExpect(jsonPath("$.[*].tenTaiSan").value(hasItem(DEFAULT_TEN_TAI_SAN)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].thongTinTs").value(hasItem(DEFAULT_THONG_TIN_TS)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].idDuongSu").value(hasItem(DEFAULT_ID_DUONG_SU.intValue())))
            .andExpect(jsonPath("$.[*].idTsGoc").value(hasItem(DEFAULT_ID_TS_GOC.intValue())))
            .andExpect(jsonPath("$.[*].maTaiSan").value(hasItem(DEFAULT_MA_TAI_SAN)))
            .andExpect(jsonPath("$.[*].idLoaiNganChan").value(hasItem(DEFAULT_ID_LOAI_NGAN_CHAN.intValue())))
            .andExpect(jsonPath("$.[*].ngayBdNganChan").value(hasItem(DEFAULT_NGAY_BD_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].ngayKtNganChan").value(hasItem(DEFAULT_NGAY_KT_NGAN_CHAN.toString())))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER.intValue())))
            .andExpect(jsonPath("$.[*].strSearch").value(hasItem(DEFAULT_STR_SEARCH)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].soHsCv").value(hasItem(DEFAULT_SO_HS_CV.intValue())))
            .andExpect(jsonPath("$.[*].soCc").value(hasItem(DEFAULT_SO_CC.intValue())))
            .andExpect(jsonPath("$.[*].soVaoSo").value(hasItem(DEFAULT_SO_VAO_SO.intValue())))
            .andExpect(jsonPath("$.[*].moTa").value(hasItem(DEFAULT_MO_TA)))
            .andExpect(jsonPath("$.[*].loaiNganChan").value(hasItem(DEFAULT_LOAI_NGAN_CHAN.intValue())));
    }

    @Test
    @Transactional
    void getDmTaiSan() throws Exception {
        // Initialize the database
        insertedDmTaiSan = dmTaiSanRepository.saveAndFlush(dmTaiSan);

        // Get the dmTaiSan
        restDmTaiSanMockMvc
            .perform(get(ENTITY_API_URL_ID, dmTaiSan.getIdTaiSan()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idTaiSan").value(dmTaiSan.getIdTaiSan().intValue()))
            .andExpect(jsonPath("$.tenTaiSan").value(DEFAULT_TEN_TAI_SAN))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.thongTinTs").value(DEFAULT_THONG_TIN_TS))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.idDuongSu").value(DEFAULT_ID_DUONG_SU.intValue()))
            .andExpect(jsonPath("$.idTsGoc").value(DEFAULT_ID_TS_GOC.intValue()))
            .andExpect(jsonPath("$.maTaiSan").value(DEFAULT_MA_TAI_SAN))
            .andExpect(jsonPath("$.idLoaiNganChan").value(DEFAULT_ID_LOAI_NGAN_CHAN.intValue()))
            .andExpect(jsonPath("$.ngayBdNganChan").value(DEFAULT_NGAY_BD_NGAN_CHAN.toString()))
            .andExpect(jsonPath("$.ngayKtNganChan").value(DEFAULT_NGAY_KT_NGAN_CHAN.toString()))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER.intValue()))
            .andExpect(jsonPath("$.strSearch").value(DEFAULT_STR_SEARCH))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.soHsCv").value(DEFAULT_SO_HS_CV.intValue()))
            .andExpect(jsonPath("$.soCc").value(DEFAULT_SO_CC.intValue()))
            .andExpect(jsonPath("$.soVaoSo").value(DEFAULT_SO_VAO_SO.intValue()))
            .andExpect(jsonPath("$.moTa").value(DEFAULT_MO_TA))
            .andExpect(jsonPath("$.loaiNganChan").value(DEFAULT_LOAI_NGAN_CHAN.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDmTaiSan() throws Exception {
        // Get the dmTaiSan
        restDmTaiSanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDmTaiSan() throws Exception {
        // Initialize the database
        insertedDmTaiSan = dmTaiSanRepository.saveAndFlush(dmTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmTaiSan
        DmTaiSan updatedDmTaiSan = dmTaiSanRepository.findById(dmTaiSan.getIdTaiSan()).orElseThrow();
        // Disconnect from session so that the updates on updatedDmTaiSan are not directly saved in db
        em.detach(updatedDmTaiSan);
        updatedDmTaiSan
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .maTaiSan(UPDATED_MA_TAI_SAN)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .idMaster(UPDATED_ID_MASTER)
            .strSearch(UPDATED_STR_SEARCH)
            .idDonVi(UPDATED_ID_DON_VI)
            .soHsCv(UPDATED_SO_HS_CV)
            .soCc(UPDATED_SO_CC)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN);
        DmTaiSanDTO dmTaiSanDTO = dmTaiSanMapper.toDto(updatedDmTaiSan);

        restDmTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmTaiSanDTO.getIdTaiSan())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmTaiSanDTO))
            )
            .andExpect(status().isOk());

        // Validate the DmTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDmTaiSanToMatchAllProperties(updatedDmTaiSan);
    }

    @Test
    @Transactional
    void putNonExistingDmTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTaiSan.setIdTaiSan(longCount.incrementAndGet());

        // Create the DmTaiSan
        DmTaiSanDTO dmTaiSanDTO = dmTaiSanMapper.toDto(dmTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dmTaiSanDTO.getIdTaiSan())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDmTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTaiSan.setIdTaiSan(longCount.incrementAndGet());

        // Create the DmTaiSan
        DmTaiSanDTO dmTaiSanDTO = dmTaiSanMapper.toDto(dmTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmTaiSanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(dmTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDmTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTaiSan.setIdTaiSan(longCount.incrementAndGet());

        // Create the DmTaiSan
        DmTaiSanDTO dmTaiSanDTO = dmTaiSanMapper.toDto(dmTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmTaiSanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(dmTaiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDmTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedDmTaiSan = dmTaiSanRepository.saveAndFlush(dmTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmTaiSan using partial update
        DmTaiSan partialUpdatedDmTaiSan = new DmTaiSan();
        partialUpdatedDmTaiSan.setIdTaiSan(dmTaiSan.getIdTaiSan());

        partialUpdatedDmTaiSan
            .trangThai(UPDATED_TRANG_THAI)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .idMaster(UPDATED_ID_MASTER);

        restDmTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmTaiSan.getIdTaiSan())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the DmTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmTaiSanUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedDmTaiSan, dmTaiSan), getPersistedDmTaiSan(dmTaiSan));
    }

    @Test
    @Transactional
    void fullUpdateDmTaiSanWithPatch() throws Exception {
        // Initialize the database
        insertedDmTaiSan = dmTaiSanRepository.saveAndFlush(dmTaiSan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the dmTaiSan using partial update
        DmTaiSan partialUpdatedDmTaiSan = new DmTaiSan();
        partialUpdatedDmTaiSan.setIdTaiSan(dmTaiSan.getIdTaiSan());

        partialUpdatedDmTaiSan
            .tenTaiSan(UPDATED_TEN_TAI_SAN)
            .trangThai(UPDATED_TRANG_THAI)
            .thongTinTs(UPDATED_THONG_TIN_TS)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .idDuongSu(UPDATED_ID_DUONG_SU)
            .idTsGoc(UPDATED_ID_TS_GOC)
            .maTaiSan(UPDATED_MA_TAI_SAN)
            .idLoaiNganChan(UPDATED_ID_LOAI_NGAN_CHAN)
            .ngayBdNganChan(UPDATED_NGAY_BD_NGAN_CHAN)
            .ngayKtNganChan(UPDATED_NGAY_KT_NGAN_CHAN)
            .idMaster(UPDATED_ID_MASTER)
            .strSearch(UPDATED_STR_SEARCH)
            .idDonVi(UPDATED_ID_DON_VI)
            .soHsCv(UPDATED_SO_HS_CV)
            .soCc(UPDATED_SO_CC)
            .soVaoSo(UPDATED_SO_VAO_SO)
            .moTa(UPDATED_MO_TA)
            .loaiNganChan(UPDATED_LOAI_NGAN_CHAN);

        restDmTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDmTaiSan.getIdTaiSan())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDmTaiSan))
            )
            .andExpect(status().isOk());

        // Validate the DmTaiSan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDmTaiSanUpdatableFieldsEquals(partialUpdatedDmTaiSan, getPersistedDmTaiSan(partialUpdatedDmTaiSan));
    }

    @Test
    @Transactional
    void patchNonExistingDmTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTaiSan.setIdTaiSan(longCount.incrementAndGet());

        // Create the DmTaiSan
        DmTaiSanDTO dmTaiSanDTO = dmTaiSanMapper.toDto(dmTaiSan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDmTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dmTaiSanDTO.getIdTaiSan())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDmTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTaiSan.setIdTaiSan(longCount.incrementAndGet());

        // Create the DmTaiSan
        DmTaiSanDTO dmTaiSanDTO = dmTaiSanMapper.toDto(dmTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmTaiSanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(dmTaiSanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DmTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDmTaiSan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        dmTaiSan.setIdTaiSan(longCount.incrementAndGet());

        // Create the DmTaiSan
        DmTaiSanDTO dmTaiSanDTO = dmTaiSanMapper.toDto(dmTaiSan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDmTaiSanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(dmTaiSanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DmTaiSan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDmTaiSan() throws Exception {
        // Initialize the database
        insertedDmTaiSan = dmTaiSanRepository.saveAndFlush(dmTaiSan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the dmTaiSan
        restDmTaiSanMockMvc
            .perform(delete(ENTITY_API_URL_ID, dmTaiSan.getIdTaiSan()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return dmTaiSanRepository.count();
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

    protected DmTaiSan getPersistedDmTaiSan(DmTaiSan dmTaiSan) {
        return dmTaiSanRepository.findById(dmTaiSan.getIdTaiSan()).orElseThrow();
    }

    protected void assertPersistedDmTaiSanToMatchAllProperties(DmTaiSan expectedDmTaiSan) {
        assertDmTaiSanAllPropertiesEquals(expectedDmTaiSan, getPersistedDmTaiSan(expectedDmTaiSan));
    }

    protected void assertPersistedDmTaiSanToMatchUpdatableProperties(DmTaiSan expectedDmTaiSan) {
        assertDmTaiSanAllUpdatablePropertiesEquals(expectedDmTaiSan, getPersistedDmTaiSan(expectedDmTaiSan));
    }
}
