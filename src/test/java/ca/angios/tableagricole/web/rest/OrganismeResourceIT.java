package ca.angios.tableagricole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ca.angios.tableagricole.TableAgricoleApp;
import ca.angios.tableagricole.domain.Organisme;
import ca.angios.tableagricole.repository.OrganismeRepository;
import ca.angios.tableagricole.service.OrganismeService;
import ca.angios.tableagricole.service.dto.OrganismeDTO;
import ca.angios.tableagricole.service.mapper.OrganismeMapper;
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
 * Integration tests for the {@link OrganismeResource} REST controller.
 */
@SpringBootTest(classes = TableAgricoleApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrganismeResourceIT {
    private static final Long DEFAULT_ID_ORGANISME = 1L;
    private static final Long UPDATED_ID_ORGANISME = 2L;

    private static final String DEFAULT_NOM_ORGANISME = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ORGANISME = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_ORGANISME = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_ORGANISME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_ORGANISME = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_ORGANISME = "BBBBBBBBBB";

    private static final String DEFAULT_COURRIEL_ORGANISME = "AAAAAAAAAA";
    private static final String UPDATED_COURRIEL_ORGANISME = "BBBBBBBBBB";

    @Autowired
    private OrganismeRepository organismeRepository;

    @Autowired
    private OrganismeMapper organismeMapper;

    @Autowired
    private OrganismeService organismeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrganismeMockMvc;

    private Organisme organisme;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organisme createEntity(EntityManager em) {
        Organisme organisme = new Organisme()
            .idOrganisme(DEFAULT_ID_ORGANISME)
            .nomOrganisme(DEFAULT_NOM_ORGANISME)
            .adresseOrganisme(DEFAULT_ADRESSE_ORGANISME)
            .phoneOrganisme(DEFAULT_PHONE_ORGANISME)
            .courrielOrganisme(DEFAULT_COURRIEL_ORGANISME);
        return organisme;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organisme createUpdatedEntity(EntityManager em) {
        Organisme organisme = new Organisme()
            .idOrganisme(UPDATED_ID_ORGANISME)
            .nomOrganisme(UPDATED_NOM_ORGANISME)
            .adresseOrganisme(UPDATED_ADRESSE_ORGANISME)
            .phoneOrganisme(UPDATED_PHONE_ORGANISME)
            .courrielOrganisme(UPDATED_COURRIEL_ORGANISME);
        return organisme;
    }

    @BeforeEach
    public void initTest() {
        organisme = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganisme() throws Exception {
        int databaseSizeBeforeCreate = organismeRepository.findAll().size();
        // Create the Organisme
        OrganismeDTO organismeDTO = organismeMapper.toDto(organisme);
        restOrganismeMockMvc
            .perform(
                post("/api/organismes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(organismeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Organisme in the database
        List<Organisme> organismeList = organismeRepository.findAll();
        assertThat(organismeList).hasSize(databaseSizeBeforeCreate + 1);
        Organisme testOrganisme = organismeList.get(organismeList.size() - 1);
        assertThat(testOrganisme.getIdOrganisme()).isEqualTo(DEFAULT_ID_ORGANISME);
        assertThat(testOrganisme.getNomOrganisme()).isEqualTo(DEFAULT_NOM_ORGANISME);
        assertThat(testOrganisme.getAdresseOrganisme()).isEqualTo(DEFAULT_ADRESSE_ORGANISME);
        assertThat(testOrganisme.getPhoneOrganisme()).isEqualTo(DEFAULT_PHONE_ORGANISME);
        assertThat(testOrganisme.getCourrielOrganisme()).isEqualTo(DEFAULT_COURRIEL_ORGANISME);
    }

    @Test
    @Transactional
    public void createOrganismeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organismeRepository.findAll().size();

        // Create the Organisme with an existing ID
        organisme.setId(1L);
        OrganismeDTO organismeDTO = organismeMapper.toDto(organisme);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganismeMockMvc
            .perform(
                post("/api/organismes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(organismeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Organisme in the database
        List<Organisme> organismeList = organismeRepository.findAll();
        assertThat(organismeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomOrganismeIsRequired() throws Exception {
        int databaseSizeBeforeTest = organismeRepository.findAll().size();
        // set the field null
        organisme.setNomOrganisme(null);

        // Create the Organisme, which fails.
        OrganismeDTO organismeDTO = organismeMapper.toDto(organisme);

        restOrganismeMockMvc
            .perform(
                post("/api/organismes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(organismeDTO))
            )
            .andExpect(status().isBadRequest());

        List<Organisme> organismeList = organismeRepository.findAll();
        assertThat(organismeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseOrganismeIsRequired() throws Exception {
        int databaseSizeBeforeTest = organismeRepository.findAll().size();
        // set the field null
        organisme.setAdresseOrganisme(null);

        // Create the Organisme, which fails.
        OrganismeDTO organismeDTO = organismeMapper.toDto(organisme);

        restOrganismeMockMvc
            .perform(
                post("/api/organismes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(organismeDTO))
            )
            .andExpect(status().isBadRequest());

        List<Organisme> organismeList = organismeRepository.findAll();
        assertThat(organismeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrganismes() throws Exception {
        // Initialize the database
        organismeRepository.saveAndFlush(organisme);

        // Get all the organismeList
        restOrganismeMockMvc
            .perform(get("/api/organismes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organisme.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrganisme").value(hasItem(DEFAULT_ID_ORGANISME.intValue())))
            .andExpect(jsonPath("$.[*].nomOrganisme").value(hasItem(DEFAULT_NOM_ORGANISME)))
            .andExpect(jsonPath("$.[*].adresseOrganisme").value(hasItem(DEFAULT_ADRESSE_ORGANISME)))
            .andExpect(jsonPath("$.[*].phoneOrganisme").value(hasItem(DEFAULT_PHONE_ORGANISME)))
            .andExpect(jsonPath("$.[*].courrielOrganisme").value(hasItem(DEFAULT_COURRIEL_ORGANISME)));
    }

    @Test
    @Transactional
    public void getOrganisme() throws Exception {
        // Initialize the database
        organismeRepository.saveAndFlush(organisme);

        // Get the organisme
        restOrganismeMockMvc
            .perform(get("/api/organismes/{id}", organisme.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organisme.getId().intValue()))
            .andExpect(jsonPath("$.idOrganisme").value(DEFAULT_ID_ORGANISME.intValue()))
            .andExpect(jsonPath("$.nomOrganisme").value(DEFAULT_NOM_ORGANISME))
            .andExpect(jsonPath("$.adresseOrganisme").value(DEFAULT_ADRESSE_ORGANISME))
            .andExpect(jsonPath("$.phoneOrganisme").value(DEFAULT_PHONE_ORGANISME))
            .andExpect(jsonPath("$.courrielOrganisme").value(DEFAULT_COURRIEL_ORGANISME));
    }

    @Test
    @Transactional
    public void getNonExistingOrganisme() throws Exception {
        // Get the organisme
        restOrganismeMockMvc.perform(get("/api/organismes/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganisme() throws Exception {
        // Initialize the database
        organismeRepository.saveAndFlush(organisme);

        int databaseSizeBeforeUpdate = organismeRepository.findAll().size();

        // Update the organisme
        Organisme updatedOrganisme = organismeRepository.findById(organisme.getId()).get();
        // Disconnect from session so that the updates on updatedOrganisme are not directly saved in db
        em.detach(updatedOrganisme);
        updatedOrganisme
            .idOrganisme(UPDATED_ID_ORGANISME)
            .nomOrganisme(UPDATED_NOM_ORGANISME)
            .adresseOrganisme(UPDATED_ADRESSE_ORGANISME)
            .phoneOrganisme(UPDATED_PHONE_ORGANISME)
            .courrielOrganisme(UPDATED_COURRIEL_ORGANISME);
        OrganismeDTO organismeDTO = organismeMapper.toDto(updatedOrganisme);

        restOrganismeMockMvc
            .perform(
                put("/api/organismes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(organismeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Organisme in the database
        List<Organisme> organismeList = organismeRepository.findAll();
        assertThat(organismeList).hasSize(databaseSizeBeforeUpdate);
        Organisme testOrganisme = organismeList.get(organismeList.size() - 1);
        assertThat(testOrganisme.getIdOrganisme()).isEqualTo(UPDATED_ID_ORGANISME);
        assertThat(testOrganisme.getNomOrganisme()).isEqualTo(UPDATED_NOM_ORGANISME);
        assertThat(testOrganisme.getAdresseOrganisme()).isEqualTo(UPDATED_ADRESSE_ORGANISME);
        assertThat(testOrganisme.getPhoneOrganisme()).isEqualTo(UPDATED_PHONE_ORGANISME);
        assertThat(testOrganisme.getCourrielOrganisme()).isEqualTo(UPDATED_COURRIEL_ORGANISME);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganisme() throws Exception {
        int databaseSizeBeforeUpdate = organismeRepository.findAll().size();

        // Create the Organisme
        OrganismeDTO organismeDTO = organismeMapper.toDto(organisme);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganismeMockMvc
            .perform(
                put("/api/organismes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(organismeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Organisme in the database
        List<Organisme> organismeList = organismeRepository.findAll();
        assertThat(organismeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganisme() throws Exception {
        // Initialize the database
        organismeRepository.saveAndFlush(organisme);

        int databaseSizeBeforeDelete = organismeRepository.findAll().size();

        // Delete the organisme
        restOrganismeMockMvc
            .perform(delete("/api/organismes/{id}", organisme.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Organisme> organismeList = organismeRepository.findAll();
        assertThat(organismeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
