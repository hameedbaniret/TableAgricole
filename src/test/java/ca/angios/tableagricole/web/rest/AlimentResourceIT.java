package ca.angios.tableagricole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ca.angios.tableagricole.TableAgricoleApp;
import ca.angios.tableagricole.domain.Aliment;
import ca.angios.tableagricole.repository.AlimentRepository;
import ca.angios.tableagricole.service.AlimentService;
import ca.angios.tableagricole.service.dto.AlimentDTO;
import ca.angios.tableagricole.service.mapper.AlimentMapper;
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
 * Integration tests for the {@link AlimentResource} REST controller.
 */
@SpringBootTest(classes = TableAgricoleApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AlimentResourceIT {
    private static final Long DEFAULT_ID_ALIMENT = 1L;
    private static final Long UPDATED_ID_ALIMENT = 2L;

    private static final String DEFAULT_DSC_ALIMENT = "AAAAAAAAAA";
    private static final String UPDATED_DSC_ALIMENT = "BBBBBBBBBB";

    @Autowired
    private AlimentRepository alimentRepository;

    @Autowired
    private AlimentMapper alimentMapper;

    @Autowired
    private AlimentService alimentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAlimentMockMvc;

    private Aliment aliment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aliment createEntity(EntityManager em) {
        Aliment aliment = new Aliment().idAliment(DEFAULT_ID_ALIMENT).dscAliment(DEFAULT_DSC_ALIMENT);
        return aliment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aliment createUpdatedEntity(EntityManager em) {
        Aliment aliment = new Aliment().idAliment(UPDATED_ID_ALIMENT).dscAliment(UPDATED_DSC_ALIMENT);
        return aliment;
    }

    @BeforeEach
    public void initTest() {
        aliment = createEntity(em);
    }

    @Test
    @Transactional
    public void createAliment() throws Exception {
        int databaseSizeBeforeCreate = alimentRepository.findAll().size();
        // Create the Aliment
        AlimentDTO alimentDTO = alimentMapper.toDto(aliment);
        restAlimentMockMvc
            .perform(post("/api/aliments").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alimentDTO)))
            .andExpect(status().isCreated());

        // Validate the Aliment in the database
        List<Aliment> alimentList = alimentRepository.findAll();
        assertThat(alimentList).hasSize(databaseSizeBeforeCreate + 1);
        Aliment testAliment = alimentList.get(alimentList.size() - 1);
        assertThat(testAliment.getIdAliment()).isEqualTo(DEFAULT_ID_ALIMENT);
        assertThat(testAliment.getDscAliment()).isEqualTo(DEFAULT_DSC_ALIMENT);
    }

    @Test
    @Transactional
    public void createAlimentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alimentRepository.findAll().size();

        // Create the Aliment with an existing ID
        aliment.setId(1L);
        AlimentDTO alimentDTO = alimentMapper.toDto(aliment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlimentMockMvc
            .perform(post("/api/aliments").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alimentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Aliment in the database
        List<Aliment> alimentList = alimentRepository.findAll();
        assertThat(alimentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDscAlimentIsRequired() throws Exception {
        int databaseSizeBeforeTest = alimentRepository.findAll().size();
        // set the field null
        aliment.setDscAliment(null);

        // Create the Aliment, which fails.
        AlimentDTO alimentDTO = alimentMapper.toDto(aliment);

        restAlimentMockMvc
            .perform(post("/api/aliments").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alimentDTO)))
            .andExpect(status().isBadRequest());

        List<Aliment> alimentList = alimentRepository.findAll();
        assertThat(alimentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAliments() throws Exception {
        // Initialize the database
        alimentRepository.saveAndFlush(aliment);

        // Get all the alimentList
        restAlimentMockMvc
            .perform(get("/api/aliments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aliment.getId().intValue())))
            .andExpect(jsonPath("$.[*].idAliment").value(hasItem(DEFAULT_ID_ALIMENT.intValue())))
            .andExpect(jsonPath("$.[*].dscAliment").value(hasItem(DEFAULT_DSC_ALIMENT)));
    }

    @Test
    @Transactional
    public void getAliment() throws Exception {
        // Initialize the database
        alimentRepository.saveAndFlush(aliment);

        // Get the aliment
        restAlimentMockMvc
            .perform(get("/api/aliments/{id}", aliment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(aliment.getId().intValue()))
            .andExpect(jsonPath("$.idAliment").value(DEFAULT_ID_ALIMENT.intValue()))
            .andExpect(jsonPath("$.dscAliment").value(DEFAULT_DSC_ALIMENT));
    }

    @Test
    @Transactional
    public void getNonExistingAliment() throws Exception {
        // Get the aliment
        restAlimentMockMvc.perform(get("/api/aliments/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAliment() throws Exception {
        // Initialize the database
        alimentRepository.saveAndFlush(aliment);

        int databaseSizeBeforeUpdate = alimentRepository.findAll().size();

        // Update the aliment
        Aliment updatedAliment = alimentRepository.findById(aliment.getId()).get();
        // Disconnect from session so that the updates on updatedAliment are not directly saved in db
        em.detach(updatedAliment);
        updatedAliment.idAliment(UPDATED_ID_ALIMENT).dscAliment(UPDATED_DSC_ALIMENT);
        AlimentDTO alimentDTO = alimentMapper.toDto(updatedAliment);

        restAlimentMockMvc
            .perform(put("/api/aliments").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alimentDTO)))
            .andExpect(status().isOk());

        // Validate the Aliment in the database
        List<Aliment> alimentList = alimentRepository.findAll();
        assertThat(alimentList).hasSize(databaseSizeBeforeUpdate);
        Aliment testAliment = alimentList.get(alimentList.size() - 1);
        assertThat(testAliment.getIdAliment()).isEqualTo(UPDATED_ID_ALIMENT);
        assertThat(testAliment.getDscAliment()).isEqualTo(UPDATED_DSC_ALIMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingAliment() throws Exception {
        int databaseSizeBeforeUpdate = alimentRepository.findAll().size();

        // Create the Aliment
        AlimentDTO alimentDTO = alimentMapper.toDto(aliment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlimentMockMvc
            .perform(put("/api/aliments").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alimentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Aliment in the database
        List<Aliment> alimentList = alimentRepository.findAll();
        assertThat(alimentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAliment() throws Exception {
        // Initialize the database
        alimentRepository.saveAndFlush(aliment);

        int databaseSizeBeforeDelete = alimentRepository.findAll().size();

        // Delete the aliment
        restAlimentMockMvc
            .perform(delete("/api/aliments/{id}", aliment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Aliment> alimentList = alimentRepository.findAll();
        assertThat(alimentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
