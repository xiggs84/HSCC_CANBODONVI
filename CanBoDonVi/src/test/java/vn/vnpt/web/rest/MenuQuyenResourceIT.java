package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.MenuQuyenAsserts.*;
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
import vn.vnpt.domain.MenuQuyen;
import vn.vnpt.repository.MenuQuyenRepository;
import vn.vnpt.service.dto.MenuQuyenDTO;
import vn.vnpt.service.mapper.MenuQuyenMapper;

/**
 * Integration tests for the {@link MenuQuyenResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MenuQuyenResourceIT {

    private static final String DEFAULT_LIST_MENU = "AAAAAAAAAA";
    private static final String UPDATED_LIST_MENU = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/menu-quyens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MenuQuyenRepository menuQuyenRepository;

    @Autowired
    private MenuQuyenMapper menuQuyenMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMenuQuyenMockMvc;

    private MenuQuyen menuQuyen;

    private MenuQuyen insertedMenuQuyen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuQuyen createEntity() {
        return new MenuQuyen().listMenu(DEFAULT_LIST_MENU);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuQuyen createUpdatedEntity() {
        return new MenuQuyen().listMenu(UPDATED_LIST_MENU);
    }

    @BeforeEach
    public void initTest() {
        menuQuyen = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedMenuQuyen != null) {
            menuQuyenRepository.delete(insertedMenuQuyen);
            insertedMenuQuyen = null;
        }
    }

    @Test
    @Transactional
    void createMenuQuyen() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the MenuQuyen
        MenuQuyenDTO menuQuyenDTO = menuQuyenMapper.toDto(menuQuyen);
        var returnedMenuQuyenDTO = om.readValue(
            restMenuQuyenMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(menuQuyenDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MenuQuyenDTO.class
        );

        // Validate the MenuQuyen in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedMenuQuyen = menuQuyenMapper.toEntity(returnedMenuQuyenDTO);
        assertMenuQuyenUpdatableFieldsEquals(returnedMenuQuyen, getPersistedMenuQuyen(returnedMenuQuyen));

        insertedMenuQuyen = returnedMenuQuyen;
    }

    @Test
    @Transactional
    void createMenuQuyenWithExistingId() throws Exception {
        // Create the MenuQuyen with an existing ID
        menuQuyen.setId(1L);
        MenuQuyenDTO menuQuyenDTO = menuQuyenMapper.toDto(menuQuyen);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuQuyenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(menuQuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMenuQuyens() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        // Get all the menuQuyenList
        restMenuQuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menuQuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].listMenu").value(hasItem(DEFAULT_LIST_MENU)));
    }

    @Test
    @Transactional
    void getMenuQuyen() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        // Get the menuQuyen
        restMenuQuyenMockMvc
            .perform(get(ENTITY_API_URL_ID, menuQuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(menuQuyen.getId().intValue()))
            .andExpect(jsonPath("$.listMenu").value(DEFAULT_LIST_MENU));
    }

    @Test
    @Transactional
    void getMenuQuyensByIdFiltering() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        Long id = menuQuyen.getId();

        defaultMenuQuyenFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultMenuQuyenFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultMenuQuyenFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllMenuQuyensByListMenuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        // Get all the menuQuyenList where listMenu equals to
        defaultMenuQuyenFiltering("listMenu.equals=" + DEFAULT_LIST_MENU, "listMenu.equals=" + UPDATED_LIST_MENU);
    }

    @Test
    @Transactional
    void getAllMenuQuyensByListMenuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        // Get all the menuQuyenList where listMenu in
        defaultMenuQuyenFiltering("listMenu.in=" + DEFAULT_LIST_MENU + "," + UPDATED_LIST_MENU, "listMenu.in=" + UPDATED_LIST_MENU);
    }

    @Test
    @Transactional
    void getAllMenuQuyensByListMenuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        // Get all the menuQuyenList where listMenu is not null
        defaultMenuQuyenFiltering("listMenu.specified=true", "listMenu.specified=false");
    }

    @Test
    @Transactional
    void getAllMenuQuyensByListMenuContainsSomething() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        // Get all the menuQuyenList where listMenu contains
        defaultMenuQuyenFiltering("listMenu.contains=" + DEFAULT_LIST_MENU, "listMenu.contains=" + UPDATED_LIST_MENU);
    }

    @Test
    @Transactional
    void getAllMenuQuyensByListMenuNotContainsSomething() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        // Get all the menuQuyenList where listMenu does not contain
        defaultMenuQuyenFiltering("listMenu.doesNotContain=" + UPDATED_LIST_MENU, "listMenu.doesNotContain=" + DEFAULT_LIST_MENU);
    }

    private void defaultMenuQuyenFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultMenuQuyenShouldBeFound(shouldBeFound);
        defaultMenuQuyenShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMenuQuyenShouldBeFound(String filter) throws Exception {
        restMenuQuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menuQuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].listMenu").value(hasItem(DEFAULT_LIST_MENU)));

        // Check, that the count call also returns 1
        restMenuQuyenMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMenuQuyenShouldNotBeFound(String filter) throws Exception {
        restMenuQuyenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMenuQuyenMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingMenuQuyen() throws Exception {
        // Get the menuQuyen
        restMenuQuyenMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMenuQuyen() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the menuQuyen
        MenuQuyen updatedMenuQuyen = menuQuyenRepository.findById(menuQuyen.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMenuQuyen are not directly saved in db
        em.detach(updatedMenuQuyen);
        updatedMenuQuyen.listMenu(UPDATED_LIST_MENU);
        MenuQuyenDTO menuQuyenDTO = menuQuyenMapper.toDto(updatedMenuQuyen);

        restMenuQuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, menuQuyenDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(menuQuyenDTO))
            )
            .andExpect(status().isOk());

        // Validate the MenuQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMenuQuyenToMatchAllProperties(updatedMenuQuyen);
    }

    @Test
    @Transactional
    void putNonExistingMenuQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menuQuyen.setId(longCount.incrementAndGet());

        // Create the MenuQuyen
        MenuQuyenDTO menuQuyenDTO = menuQuyenMapper.toDto(menuQuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuQuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, menuQuyenDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(menuQuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MenuQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMenuQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menuQuyen.setId(longCount.incrementAndGet());

        // Create the MenuQuyen
        MenuQuyenDTO menuQuyenDTO = menuQuyenMapper.toDto(menuQuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuQuyenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(menuQuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MenuQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMenuQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menuQuyen.setId(longCount.incrementAndGet());

        // Create the MenuQuyen
        MenuQuyenDTO menuQuyenDTO = menuQuyenMapper.toDto(menuQuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuQuyenMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(menuQuyenDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MenuQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMenuQuyenWithPatch() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the menuQuyen using partial update
        MenuQuyen partialUpdatedMenuQuyen = new MenuQuyen();
        partialUpdatedMenuQuyen.setId(menuQuyen.getId());

        restMenuQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMenuQuyen.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMenuQuyen))
            )
            .andExpect(status().isOk());

        // Validate the MenuQuyen in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMenuQuyenUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedMenuQuyen, menuQuyen),
            getPersistedMenuQuyen(menuQuyen)
        );
    }

    @Test
    @Transactional
    void fullUpdateMenuQuyenWithPatch() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the menuQuyen using partial update
        MenuQuyen partialUpdatedMenuQuyen = new MenuQuyen();
        partialUpdatedMenuQuyen.setId(menuQuyen.getId());

        partialUpdatedMenuQuyen.listMenu(UPDATED_LIST_MENU);

        restMenuQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMenuQuyen.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMenuQuyen))
            )
            .andExpect(status().isOk());

        // Validate the MenuQuyen in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMenuQuyenUpdatableFieldsEquals(partialUpdatedMenuQuyen, getPersistedMenuQuyen(partialUpdatedMenuQuyen));
    }

    @Test
    @Transactional
    void patchNonExistingMenuQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menuQuyen.setId(longCount.incrementAndGet());

        // Create the MenuQuyen
        MenuQuyenDTO menuQuyenDTO = menuQuyenMapper.toDto(menuQuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, menuQuyenDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(menuQuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MenuQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMenuQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menuQuyen.setId(longCount.incrementAndGet());

        // Create the MenuQuyen
        MenuQuyenDTO menuQuyenDTO = menuQuyenMapper.toDto(menuQuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuQuyenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(menuQuyenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MenuQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMenuQuyen() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menuQuyen.setId(longCount.incrementAndGet());

        // Create the MenuQuyen
        MenuQuyenDTO menuQuyenDTO = menuQuyenMapper.toDto(menuQuyen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuQuyenMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(menuQuyenDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MenuQuyen in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMenuQuyen() throws Exception {
        // Initialize the database
        insertedMenuQuyen = menuQuyenRepository.saveAndFlush(menuQuyen);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the menuQuyen
        restMenuQuyenMockMvc
            .perform(delete(ENTITY_API_URL_ID, menuQuyen.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return menuQuyenRepository.count();
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

    protected MenuQuyen getPersistedMenuQuyen(MenuQuyen menuQuyen) {
        return menuQuyenRepository.findById(menuQuyen.getId()).orElseThrow();
    }

    protected void assertPersistedMenuQuyenToMatchAllProperties(MenuQuyen expectedMenuQuyen) {
        assertMenuQuyenAllPropertiesEquals(expectedMenuQuyen, getPersistedMenuQuyen(expectedMenuQuyen));
    }

    protected void assertPersistedMenuQuyenToMatchUpdatableProperties(MenuQuyen expectedMenuQuyen) {
        assertMenuQuyenAllUpdatablePropertiesEquals(expectedMenuQuyen, getPersistedMenuQuyen(expectedMenuQuyen));
    }
}
