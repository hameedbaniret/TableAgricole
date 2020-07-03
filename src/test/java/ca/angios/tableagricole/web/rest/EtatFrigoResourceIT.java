package ca.angios.tableagricole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ca.angios.tableagricole.TableAgricoleApp;
import ca.angios.tableagricole.domain.EtatFrigo;
import ca.angios.tableagricole.repository.EtatFrigoRepository;
import ca.angios.tableagricole.service.EtatFrigoService;
import ca.angios.tableagricole.service.dto.EtatFrigoDTO;
import ca.angios.tableagricole.service.mapper.EtatFrigoMapper;
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
 * Integration tests for the {@link EtatFrigoResource} REST controller.
 */
@SpringBootTest(classes = TableAgricoleApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EtatFrigoResourceIT {
    private static final Long DEFAULT_ID_ETAT_FRIGO = 1L;
    private static final Long UPDATED_ID_ETAT_FRIGO = 2L;

    private static final String DEFAULT_DSC_ETAT_FRIGO = "AAAAAAAAAA";
    private static final String UPDATED_DSC_ETAT_FRIGO = "BBBBBBBBBB";

    @Autowired
    private EtatFrigoRepository etatFrigoRepository;

    @Autowired
    private EtatFrigoMapper etatFrigoMapper;

    @Autowired
    private EtatFrigoService etatFrigoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEtatFrigoMockMvc;

    private EtatFrigo etatFrigo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatFrigo createEntity(EntityManager em) {
        EtatFrigo etatFrigo = new EtatFrigo().idEtatFrigo(DEFAULT_ID_ETAT_FRIGO).dscEtatFrigo(DEFAULT_DSC_ETAT_FRIGO);
        return etatFrigo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtatFrigo createUpdatedEntity(EntityManager em) {
        EtatFrigo etatFrigo = new EtatFrigo().idEtatFrigo(UPDATED_ID_ETAT_FRIGO).dscEtatFrigo(UPDATED_DSC_ETAT_FRIGO);
        return etatFrigo;
    }

    @BeforeEach
    public void initTest() {
        etatFrigo = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtatFrigo() throws Exception {
        int databaseSizeBeforeCreate = etatFrigoRepository.findAll().size();
        // Create the EtatFrigo
        EtatFrigoDTO etatFrigoDTO = etatFrigoMapper.toDto(etatFrigo);
        restEtatFrigoMockMvc
            .perform(
                post("/api/etat-frigos").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(etatFrigoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the EtatFrigo in the database
        List<EtatFrigo> etatFrigoList = etatFrigoRepository.findAll();
        assertThat(etatFrigoList).hasSize(databaseSizeBeforeCreate + 1);
        EtatFrigo testEtatFrigo = etatFrigoList.get(etatFrigoList.size() - 1);
        assertThat(testEtatFrigo.getIdEtatFrigo()).isEqualTo(DEFAULT_ID_ETAT_FRIGO);
        assertThat(testEtatFrigo.getDscEtatFrigo()).isEqualTo(DEFAULT_DSC_ETAT_FRIGO);
    }

    @Test
    @Transactional
    public void createEtatFrigoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etatFrigoRepository.findAll().size();

        // Create the EtatFrigo with an existing ID
        etatFrigo.setId(1L);
        EtatFrigoDTO etatFrigoDTO = etatFrigoMapper.toDto(etatFrigo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtatFrigoMockMvc
            .perform(
                post("/api/etat-frigos").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(etatFrigoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EtatFrigo in the database
        List<EtatFrigo> etatFrigoList = etatFrigoRepository.findAll();
        assertThat(etatFrigoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDscEtatFrigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = etatFrigoRepository.findAll().size();
        // set the field null
        etatFrigo.setDscEtatFrigo(null);

        // Create the EtatFrigo, which fails.
        EtatFrigoDTO etatFrigoDTO = etatFrigoMapper.toDto(etatFrigo);

        restEtatFrigoMockMvc
            .perform(
                post("/api/etat-frigos").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(etatFrigoDTO))
            )
            .andExpect(status().isBadRequest());

        List<EtatFrigo> etatFrigoList = etatFrigoRepository.findAll();
        assertThat(etatFrigoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtatFrigos() throws Exception {
        // Initialize the database
        etatFrigoRepository.saveAndFlush(etatFrigo);

        // Get all the etatFrigoList
        restEtatFrigoMockMvc
            .perform(get("/api/etat-frigos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etatFrigo.getId().intValue())))
            .andExpect(jsonPath("$.[*].idEtatFrigo").value(hasItem(DEFAULT_ID_ETAT_FRIGO.intValue())))
            .andExpect(jsonPath("$.[*].dscEtatFrigo").value(hasItem(DEFAULT_DSC_ETAT_FRIGO)));
    }

    @Test
    @Transactional
    public void getEtatFrigo() throws Exception {
        // Initialize the database
        etatFrigoRepository.saveAndFlush(etatFrigo);

        // Get the etatFrigo
        restEtatFrigoMockMvc
            .perform(get("/api/etat-frigos/{id}", etatFrigo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etatFrigo.getId().intValue()))
            .andExpect(jsonPath("$.idEtatFrigo").value(DEFAULT_ID_ETAT_FRIGO.intValue()))
            .andExpect(jsonPath("$.dscEtatFrigo").value(DEFAULT_DSC_ETAT_FRIGO));
    }

    @Test
    @Transactional
    public void getNonExistingEtatFrigo() throws Exception {
        // Get the etatFrigo
        restEtatFrigoMockMvc.perform(get("/api/etat-frigos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtatFrigo() throws Exception {
        // Initialize the database
        etatFrigoRepository.saveAndFlush(etatFrigo);

        int databaseSizeBeforeUpdate = etatFrigoRepository.findAll().size();

        // Update the etatFrigo
        EtatFrigo updatedEtatFrigo = etatFrigoRepository.findById(etatFrigo.getId()).get();
        // Disconnect from session so that the updates on updatedEtatFrigo are not directly saved in db
        em.detach(updatedEtatFrigo);
        updatedEtatFrigo.idEtatFrigo(UPDATED_ID_ETAT_FRIGO).dscEtatFrigo(UPDATED_DSC_ETAT_FRIGO);
        EtatFrigoDTO etatFrigoDTO = etatFrigoMapper.toDto(updatedEtatFrigo);

        restEtatFrigoMockMvc
            .perform(
                put("/api/etat-frigos").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(etatFrigoDTO))
            )
            .andExpect(status().isOk());

        // Validate the EtatFrigo in the database
        List<EtatFrigo> etatFrigoList = etatFrigoRepository.findAll();
        assertThat(etatFrigoList).hasSize(databaseSizeBeforeUpdate);
        EtatFrigo testEtatFrigo = etatFrigoList.get(etatFrigoList.size() - 1);
        assertThat(testEtatFrigo.getIdEtatFrigo()).isEqualTo(UPDATED_ID_ETAT_FRIGO);
        assertThat(testEtatFrigo.getDscEtatFrigo()).isEqualTo(UPDATED_DSC_ETAT_FRIGO);
    }

    @Test
    @Transactional
    public void updateNonExistingEtatFrigo() throws Exception {
        int databaseSizeBeforeUpdate = etatFrigoRepository.findAll().size();

        // Create the EtatFrigo
        EtatFrigoDTO etatFrigoDTO = etatFrigoMapper.toDto(etatFrigo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtatFrigoMockMvc
            .perform(
                put("/api/etat-frigos").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(etatFrigoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EtatFrigo in the database
        List<EtatFrigo> etatFrigoList = etatFrigoRepository.findAll();
        assertThat(etatFrigoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtatFrigo() throws Exception {
        // Initialize the database
        etatFrigoRepository.saveAndFlush(etatFrigo);

        int databaseSizeBeforeDelete = etatFrigoRepository.findAll().size();

        // Delete the etatFrigo
        restEtatFrigoMockMvc
            .perform(delete("/api/etat-frigos/{id}", etatFrigo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtatFrigo> etatFrigoList = etatFrigoRepository.findAll();
        assertThat(etatFrigoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
