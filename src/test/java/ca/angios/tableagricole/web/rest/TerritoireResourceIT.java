package ca.angios.tableagricole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ca.angios.tableagricole.TableAgricoleApp;
import ca.angios.tableagricole.domain.Territoire;
import ca.angios.tableagricole.repository.TerritoireRepository;
import ca.angios.tableagricole.service.TerritoireService;
import ca.angios.tableagricole.service.dto.TerritoireDTO;
import ca.angios.tableagricole.service.mapper.TerritoireMapper;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TerritoireResource} REST controller.
 */
@SpringBootTest(classes = TableAgricoleApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TerritoireResourceIT {
    private static final Long DEFAULT_ID_TERRITOIRE = 1L;
    private static final Long UPDATED_ID_TERRITOIRE = 2L;

    private static final String DEFAULT_DSC_TERRITOIRE = "AAAAAAAAAA";
    private static final String UPDATED_DSC_TERRITOIRE = "BBBBBBBBBB";

    @Autowired
    private TerritoireRepository territoireRepository;

    @Autowired
    private TerritoireMapper territoireMapper;

    @Autowired
    private TerritoireService territoireService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTerritoireMockMvc;

    private Territoire territoire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Territoire createEntity(EntityManager em) {
        Territoire territoire = new Territoire().idTerritoire(DEFAULT_ID_TERRITOIRE).dscTerritoire(DEFAULT_DSC_TERRITOIRE);
        return territoire;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Territoire createUpdatedEntity(EntityManager em) {
        Territoire territoire = new Territoire().idTerritoire(UPDATED_ID_TERRITOIRE).dscTerritoire(UPDATED_DSC_TERRITOIRE);
        return territoire;
    }

    @BeforeEach
    public void initTest() {
        territoire = createEntity(em);
    }

    @Test
    @Transactional
    public void createTerritoire() throws Exception {
        int databaseSizeBeforeCreate = territoireRepository.findAll().size();
        // Create the Territoire
        TerritoireDTO territoireDTO = territoireMapper.toDto(territoire);
        restTerritoireMockMvc
            .perform(
                post("/api/territoires").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(territoireDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Territoire in the database
        List<Territoire> territoireList = territoireRepository.findAll();
        assertThat(territoireList).hasSize(databaseSizeBeforeCreate + 1);
        Territoire testTerritoire = territoireList.get(territoireList.size() - 1);
        assertThat(testTerritoire.getIdTerritoire()).isEqualTo(DEFAULT_ID_TERRITOIRE);
        assertThat(testTerritoire.getDscTerritoire()).isEqualTo(DEFAULT_DSC_TERRITOIRE);
    }

    @Test
    @Transactional
    public void createTerritoireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = territoireRepository.findAll().size();

        // Create the Territoire with an existing ID
        territoire.setId(1L);
        TerritoireDTO territoireDTO = territoireMapper.toDto(territoire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTerritoireMockMvc
            .perform(
                post("/api/territoires").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(territoireDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Territoire in the database
        List<Territoire> territoireList = territoireRepository.findAll();
        assertThat(territoireList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDscTerritoireIsRequired() throws Exception {
        int databaseSizeBeforeTest = territoireRepository.findAll().size();
        // set the field null
        territoire.setDscTerritoire(null);

        // Create the Territoire, which fails.
        TerritoireDTO territoireDTO = territoireMapper.toDto(territoire);

        restTerritoireMockMvc
            .perform(
                post("/api/territoires").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(territoireDTO))
            )
            .andExpect(status().isBadRequest());

        List<Territoire> territoireList = territoireRepository.findAll();
        assertThat(territoireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTerritoires() throws Exception {
        // Initialize the database
        territoireRepository.saveAndFlush(territoire);

        // Get all the territoireList
        restTerritoireMockMvc
            .perform(get("/api/territoires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(territoire.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTerritoire").value(hasItem(DEFAULT_ID_TERRITOIRE.intValue())))
            .andExpect(jsonPath("$.[*].dscTerritoire").value(hasItem(DEFAULT_DSC_TERRITOIRE)));
    }

    @Test
    @Transactional
    public void getTerritoire() throws Exception {
        // Initialize the database
        territoireRepository.saveAndFlush(territoire);

        // Get the territoire
        restTerritoireMockMvc
            .perform(get("/api/territoires/{id}", territoire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(territoire.getId().intValue()))
            .andExpect(jsonPath("$.idTerritoire").value(DEFAULT_ID_TERRITOIRE.intValue()))
            .andExpect(jsonPath("$.dscTerritoire").value(DEFAULT_DSC_TERRITOIRE));
    }

    @Test
    @Transactional
    public void getNonExistingTerritoire() throws Exception {
        // Get the territoire
        restTerritoireMockMvc.perform(get("/api/territoires/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTerritoire() throws Exception {
        // Initialize the database
        territoireRepository.saveAndFlush(territoire);

        int databaseSizeBeforeUpdate = territoireRepository.findAll().size();

        // Update the territoire
        Territoire updatedTerritoire = territoireRepository.findById(territoire.getId()).get();
        // Disconnect from session so that the updates on updatedTerritoire are not directly saved in db
        em.detach(updatedTerritoire);
        updatedTerritoire.idTerritoire(UPDATED_ID_TERRITOIRE).dscTerritoire(UPDATED_DSC_TERRITOIRE);
        TerritoireDTO territoireDTO = territoireMapper.toDto(updatedTerritoire);

        restTerritoireMockMvc
            .perform(
                put("/api/territoires").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(territoireDTO))
            )
            .andExpect(status().isOk());

        // Validate the Territoire in the database
        List<Territoire> territoireList = territoireRepository.findAll();
        assertThat(territoireList).hasSize(databaseSizeBeforeUpdate);
        Territoire testTerritoire = territoireList.get(territoireList.size() - 1);
        assertThat(testTerritoire.getIdTerritoire()).isEqualTo(UPDATED_ID_TERRITOIRE);
        assertThat(testTerritoire.getDscTerritoire()).isEqualTo(UPDATED_DSC_TERRITOIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingTerritoire() throws Exception {
        int databaseSizeBeforeUpdate = territoireRepository.findAll().size();

        // Create the Territoire
        TerritoireDTO territoireDTO = territoireMapper.toDto(territoire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTerritoireMockMvc
            .perform(
                put("/api/territoires").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(territoireDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Territoire in the database
        List<Territoire> territoireList = territoireRepository.findAll();
        assertThat(territoireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTerritoire() throws Exception {
        // Initialize the database
        territoireRepository.saveAndFlush(territoire);

        int databaseSizeBeforeDelete = territoireRepository.findAll().size();

        // Delete the territoire
        restTerritoireMockMvc
            .perform(delete("/api/territoires/{id}", territoire.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Territoire> territoireList = territoireRepository.findAll();
        assertThat(territoireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
