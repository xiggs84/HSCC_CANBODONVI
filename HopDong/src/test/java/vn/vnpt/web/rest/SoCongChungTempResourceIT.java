package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.SoCongChungTempAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import vn.vnpt.IntegrationTest;
import vn.vnpt.domain.SoCongChungTemp;
import vn.vnpt.repository.SoCongChungTempRepository;
import vn.vnpt.service.dto.SoCongChungTempDTO;
import vn.vnpt.service.mapper.SoCongChungTempMapper;

/**
 * Integration tests for the {@link SoCongChungTempResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SoCongChungTempResourceIT {

    private static final String DEFAULT_ID_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_ID_HOP_DONG = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_MASTER = 1L;
    private static final Long UPDATED_ID_MASTER = 2L;

    private static final String DEFAULT_SO_CC = "AAAAAAAAAA";
    private static final String UPDATED_SO_CC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/so-cong-chung-temps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SoCongChungTempRepository soCongChungTempRepository;

    @Autowired
    private SoCongChungTempMapper soCongChungTempMapper;

    @Autowired
    private MockMvc restSoCongChungTempMockMvc;

    private SoCongChungTemp soCongChungTemp;

    private SoCongChungTemp insertedSoCongChungTemp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoCongChungTemp createEntity() {
        return new SoCongChungTemp()
            .idHopDong(DEFAULT_ID_HOP_DONG)
            .idMaster(DEFAULT_ID_MASTER)
            .soCc(DEFAULT_SO_CC)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoCongChungTemp createUpdatedEntity() {
        return new SoCongChungTemp()
            .idHopDong(UPDATED_ID_HOP_DONG)
            .idMaster(UPDATED_ID_MASTER)
            .soCc(UPDATED_SO_CC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC);
    }

    @BeforeEach
    public void initTest() {
        soCongChungTemp = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedSoCongChungTemp != null) {
            soCongChungTempRepository.delete(insertedSoCongChungTemp);
            insertedSoCongChungTemp = null;
        }
    }

    @Test
    void createSoCongChungTemp() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SoCongChungTemp
        SoCongChungTempDTO soCongChungTempDTO = soCongChungTempMapper.toDto(soCongChungTemp);
        var returnedSoCongChungTempDTO = om.readValue(
            restSoCongChungTempMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soCongChungTempDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SoCongChungTempDTO.class
        );

        // Validate the SoCongChungTemp in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedSoCongChungTemp = soCongChungTempMapper.toEntity(returnedSoCongChungTempDTO);
        assertSoCongChungTempUpdatableFieldsEquals(returnedSoCongChungTemp, getPersistedSoCongChungTemp(returnedSoCongChungTemp));

        insertedSoCongChungTemp = returnedSoCongChungTemp;
    }

    @Test
    void createSoCongChungTempWithExistingId() throws Exception {
        // Create the SoCongChungTemp with an existing ID
        soCongChungTemp.setId("existing_id");
        SoCongChungTempDTO soCongChungTempDTO = soCongChungTempMapper.toDto(soCongChungTemp);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSoCongChungTempMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soCongChungTempDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SoCongChungTemp in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllSoCongChungTemps() throws Exception {
        // Initialize the database
        insertedSoCongChungTemp = soCongChungTempRepository.save(soCongChungTemp);

        // Get all the soCongChungTempList
        restSoCongChungTempMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(soCongChungTemp.getId())))
            .andExpect(jsonPath("$.[*].idHopDong").value(hasItem(DEFAULT_ID_HOP_DONG)))
            .andExpect(jsonPath("$.[*].idMaster").value(hasItem(DEFAULT_ID_MASTER.intValue())))
            .andExpect(jsonPath("$.[*].soCc").value(hasItem(DEFAULT_SO_CC)))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())));
    }

    @Test
    void getSoCongChungTemp() throws Exception {
        // Initialize the database
        insertedSoCongChungTemp = soCongChungTempRepository.save(soCongChungTemp);

        // Get the soCongChungTemp
        restSoCongChungTempMockMvc
            .perform(get(ENTITY_API_URL_ID, soCongChungTemp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(soCongChungTemp.getId()))
            .andExpect(jsonPath("$.idHopDong").value(DEFAULT_ID_HOP_DONG))
            .andExpect(jsonPath("$.idMaster").value(DEFAULT_ID_MASTER.intValue()))
            .andExpect(jsonPath("$.soCc").value(DEFAULT_SO_CC))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()));
    }

    @Test
    void getNonExistingSoCongChungTemp() throws Exception {
        // Get the soCongChungTemp
        restSoCongChungTempMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingSoCongChungTemp() throws Exception {
        // Initialize the database
        insertedSoCongChungTemp = soCongChungTempRepository.save(soCongChungTemp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soCongChungTemp
        SoCongChungTemp updatedSoCongChungTemp = soCongChungTempRepository.findById(soCongChungTemp.getId()).orElseThrow();
        updatedSoCongChungTemp
            .idHopDong(UPDATED_ID_HOP_DONG)
            .idMaster(UPDATED_ID_MASTER)
            .soCc(UPDATED_SO_CC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC);
        SoCongChungTempDTO soCongChungTempDTO = soCongChungTempMapper.toDto(updatedSoCongChungTemp);

        restSoCongChungTempMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soCongChungTempDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soCongChungTempDTO))
            )
            .andExpect(status().isOk());

        // Validate the SoCongChungTemp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSoCongChungTempToMatchAllProperties(updatedSoCongChungTemp);
    }

    @Test
    void putNonExistingSoCongChungTemp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChungTemp.setId(UUID.randomUUID().toString());

        // Create the SoCongChungTemp
        SoCongChungTempDTO soCongChungTempDTO = soCongChungTempMapper.toDto(soCongChungTemp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoCongChungTempMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soCongChungTempDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soCongChungTempDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChungTemp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSoCongChungTemp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChungTemp.setId(UUID.randomUUID().toString());

        // Create the SoCongChungTemp
        SoCongChungTempDTO soCongChungTempDTO = soCongChungTempMapper.toDto(soCongChungTemp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungTempMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soCongChungTempDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChungTemp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSoCongChungTemp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChungTemp.setId(UUID.randomUUID().toString());

        // Create the SoCongChungTemp
        SoCongChungTempDTO soCongChungTempDTO = soCongChungTempMapper.toDto(soCongChungTemp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungTempMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soCongChungTempDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoCongChungTemp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSoCongChungTempWithPatch() throws Exception {
        // Initialize the database
        insertedSoCongChungTemp = soCongChungTempRepository.save(soCongChungTemp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soCongChungTemp using partial update
        SoCongChungTemp partialUpdatedSoCongChungTemp = new SoCongChungTemp();
        partialUpdatedSoCongChungTemp.setId(soCongChungTemp.getId());

        partialUpdatedSoCongChungTemp.idMaster(UPDATED_ID_MASTER).ngayThaoTac(UPDATED_NGAY_THAO_TAC);

        restSoCongChungTempMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoCongChungTemp.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSoCongChungTemp))
            )
            .andExpect(status().isOk());

        // Validate the SoCongChungTemp in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSoCongChungTempUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSoCongChungTemp, soCongChungTemp),
            getPersistedSoCongChungTemp(soCongChungTemp)
        );
    }

    @Test
    void fullUpdateSoCongChungTempWithPatch() throws Exception {
        // Initialize the database
        insertedSoCongChungTemp = soCongChungTempRepository.save(soCongChungTemp);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soCongChungTemp using partial update
        SoCongChungTemp partialUpdatedSoCongChungTemp = new SoCongChungTemp();
        partialUpdatedSoCongChungTemp.setId(soCongChungTemp.getId());

        partialUpdatedSoCongChungTemp
            .idHopDong(UPDATED_ID_HOP_DONG)
            .idMaster(UPDATED_ID_MASTER)
            .soCc(UPDATED_SO_CC)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC);

        restSoCongChungTempMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoCongChungTemp.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSoCongChungTemp))
            )
            .andExpect(status().isOk());

        // Validate the SoCongChungTemp in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSoCongChungTempUpdatableFieldsEquals(
            partialUpdatedSoCongChungTemp,
            getPersistedSoCongChungTemp(partialUpdatedSoCongChungTemp)
        );
    }

    @Test
    void patchNonExistingSoCongChungTemp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChungTemp.setId(UUID.randomUUID().toString());

        // Create the SoCongChungTemp
        SoCongChungTempDTO soCongChungTempDTO = soCongChungTempMapper.toDto(soCongChungTemp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoCongChungTempMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, soCongChungTempDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(soCongChungTempDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChungTemp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSoCongChungTemp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChungTemp.setId(UUID.randomUUID().toString());

        // Create the SoCongChungTemp
        SoCongChungTempDTO soCongChungTempDTO = soCongChungTempMapper.toDto(soCongChungTemp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungTempMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(soCongChungTempDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChungTemp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSoCongChungTemp() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChungTemp.setId(UUID.randomUUID().toString());

        // Create the SoCongChungTemp
        SoCongChungTempDTO soCongChungTempDTO = soCongChungTempMapper.toDto(soCongChungTemp);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungTempMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(soCongChungTempDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoCongChungTemp in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSoCongChungTemp() throws Exception {
        // Initialize the database
        insertedSoCongChungTemp = soCongChungTempRepository.save(soCongChungTemp);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the soCongChungTemp
        restSoCongChungTempMockMvc
            .perform(delete(ENTITY_API_URL_ID, soCongChungTemp.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return soCongChungTempRepository.count();
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

    protected SoCongChungTemp getPersistedSoCongChungTemp(SoCongChungTemp soCongChungTemp) {
        return soCongChungTempRepository.findById(soCongChungTemp.getId()).orElseThrow();
    }

    protected void assertPersistedSoCongChungTempToMatchAllProperties(SoCongChungTemp expectedSoCongChungTemp) {
        assertSoCongChungTempAllPropertiesEquals(expectedSoCongChungTemp, getPersistedSoCongChungTemp(expectedSoCongChungTemp));
    }

    protected void assertPersistedSoCongChungTempToMatchUpdatableProperties(SoCongChungTemp expectedSoCongChungTemp) {
        assertSoCongChungTempAllUpdatablePropertiesEquals(expectedSoCongChungTemp, getPersistedSoCongChungTemp(expectedSoCongChungTemp));
    }
}
