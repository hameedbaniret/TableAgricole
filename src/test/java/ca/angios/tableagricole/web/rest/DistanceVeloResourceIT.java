package ca.angios.tableagricole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ca.angios.tableagricole.TableAgricoleApp;
import ca.angios.tableagricole.domain.DistanceVelo;
import ca.angios.tableagricole.repository.DistanceVeloRepository;
import ca.angios.tableagricole.service.DistanceVeloService;
import ca.angios.tableagricole.service.dto.DistanceVeloDTO;
import ca.angios.tableagricole.service.mapper.DistanceVeloMapper;
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
 * Integration tests for the {@link DistanceVeloResource} REST controller.
 */
@SpringBootTest(classes = TableAgricoleApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DistanceVeloResourceIT {
    private static final Long DEFAULT_ID_DISTANCE_VELO = 1L;
    private static final Long UPDATED_ID_DISTANCE_VELO = 2L;

    private static final String DEFAULT_DSC_DISTANCE_VELO = "AAAAAAAAAA";
    private static final String UPDATED_DSC_DISTANCE_VELO = "BBBBBBBBBB";

    @Autowired
    private DistanceVeloRepository distanceVeloRepository;

    @Autowired
    private DistanceVeloMapper distanceVeloMapper;

    @Autowired
    private DistanceVeloService distanceVeloService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDistanceVeloMockMvc;

    private DistanceVelo distanceVelo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DistanceVelo createEntity(EntityManager em) {
        DistanceVelo distanceVelo = new DistanceVelo().idDistanceVelo(DEFAULT_ID_DISTANCE_VELO).dscDistanceVelo(DEFAULT_DSC_DISTANCE_VELO);
        return distanceVelo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DistanceVelo createUpdatedEntity(EntityManager em) {
        DistanceVelo distanceVelo = new DistanceVelo().idDistanceVelo(UPDATED_ID_DISTANCE_VELO).dscDistanceVelo(UPDATED_DSC_DISTANCE_VELO);
        return distanceVelo;
    }

    @BeforeEach
    public void initTest() {
        distanceVelo = createEntity(em);
    }

    @Test
    @Transactional
    public void createDistanceVelo() throws Exception {
        int databaseSizeBeforeCreate = distanceVeloRepository.findAll().size();
        // Create the DistanceVelo
        DistanceVeloDTO distanceVeloDTO = distanceVeloMapper.toDto(distanceVelo);
        restDistanceVeloMockMvc
            .perform(
                post("/api/distance-velos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(distanceVeloDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DistanceVelo in the database
        List<DistanceVelo> distanceVeloList = distanceVeloRepository.findAll();
        assertThat(distanceVeloList).hasSize(databaseSizeBeforeCreate + 1);
        DistanceVelo testDistanceVelo = distanceVeloList.get(distanceVeloList.size() - 1);
        assertThat(testDistanceVelo.getIdDistanceVelo()).isEqualTo(DEFAULT_ID_DISTANCE_VELO);
        assertThat(testDistanceVelo.getDscDistanceVelo()).isEqualTo(DEFAULT_DSC_DISTANCE_VELO);
    }

    @Test
    @Transactional
    public void createDistanceVeloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = distanceVeloRepository.findAll().size();

        // Create the DistanceVelo with an existing ID
        distanceVelo.setId(1L);
        DistanceVeloDTO distanceVeloDTO = distanceVeloMapper.toDto(distanceVelo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDistanceVeloMockMvc
            .perform(
                post("/api/distance-velos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(distanceVeloDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DistanceVelo in the database
        List<DistanceVelo> distanceVeloList = distanceVeloRepository.findAll();
        assertThat(distanceVeloList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDscDistanceVeloIsRequired() throws Exception {
        int databaseSizeBeforeTest = distanceVeloRepository.findAll().size();
        // set the field null
        distanceVelo.setDscDistanceVelo(null);

        // Create the DistanceVelo, which fails.
        DistanceVeloDTO distanceVeloDTO = distanceVeloMapper.toDto(distanceVelo);

        restDistanceVeloMockMvc
            .perform(
                post("/api/distance-velos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(distanceVeloDTO))
            )
            .andExpect(status().isBadRequest());

        List<DistanceVelo> distanceVeloList = distanceVeloRepository.findAll();
        assertThat(distanceVeloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDistanceVelos() throws Exception {
        // Initialize the database
        distanceVeloRepository.saveAndFlush(distanceVelo);

        // Get all the distanceVeloList
        restDistanceVeloMockMvc
            .perform(get("/api/distance-velos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(distanceVelo.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDistanceVelo").value(hasItem(DEFAULT_ID_DISTANCE_VELO.intValue())))
            .andExpect(jsonPath("$.[*].dscDistanceVelo").value(hasItem(DEFAULT_DSC_DISTANCE_VELO)));
    }

    @Test
    @Transactional
    public void getDistanceVelo() throws Exception {
        // Initialize the database
        distanceVeloRepository.saveAndFlush(distanceVelo);

        // Get the distanceVelo
        restDistanceVeloMockMvc
            .perform(get("/api/distance-velos/{id}", distanceVelo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(distanceVelo.getId().intValue()))
            .andExpect(jsonPath("$.idDistanceVelo").value(DEFAULT_ID_DISTANCE_VELO.intValue()))
            .andExpect(jsonPath("$.dscDistanceVelo").value(DEFAULT_DSC_DISTANCE_VELO));
    }

    @Test
    @Transactional
    public void getNonExistingDistanceVelo() throws Exception {
        // Get the distanceVelo
        restDistanceVeloMockMvc.perform(get("/api/distance-velos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDistanceVelo() throws Exception {
        // Initialize the database
        distanceVeloRepository.saveAndFlush(distanceVelo);

        int databaseSizeBeforeUpdate = distanceVeloRepository.findAll().size();

        // Update the distanceVelo
        DistanceVelo updatedDistanceVelo = distanceVeloRepository.findById(distanceVelo.getId()).get();
        // Disconnect from session so that the updates on updatedDistanceVelo are not directly saved in db
        em.detach(updatedDistanceVelo);
        updatedDistanceVelo.idDistanceVelo(UPDATED_ID_DISTANCE_VELO).dscDistanceVelo(UPDATED_DSC_DISTANCE_VELO);
        DistanceVeloDTO distanceVeloDTO = distanceVeloMapper.toDto(updatedDistanceVelo);

        restDistanceVeloMockMvc
            .perform(
                put("/api/distance-velos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(distanceVeloDTO))
            )
            .andExpect(status().isOk());

        // Validate the DistanceVelo in the database
        List<DistanceVelo> distanceVeloList = distanceVeloRepository.findAll();
        assertThat(distanceVeloList).hasSize(databaseSizeBeforeUpdate);
        DistanceVelo testDistanceVelo = distanceVeloList.get(distanceVeloList.size() - 1);
        assertThat(testDistanceVelo.getIdDistanceVelo()).isEqualTo(UPDATED_ID_DISTANCE_VELO);
        assertThat(testDistanceVelo.getDscDistanceVelo()).isEqualTo(UPDATED_DSC_DISTANCE_VELO);
    }

    @Test
    @Transactional
    public void updateNonExistingDistanceVelo() throws Exception {
        int databaseSizeBeforeUpdate = distanceVeloRepository.findAll().size();

        // Create the DistanceVelo
        DistanceVeloDTO distanceVeloDTO = distanceVeloMapper.toDto(distanceVelo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDistanceVeloMockMvc
            .perform(
                put("/api/distance-velos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(distanceVeloDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DistanceVelo in the database
        List<DistanceVelo> distanceVeloList = distanceVeloRepository.findAll();
        assertThat(distanceVeloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDistanceVelo() throws Exception {
        // Initialize the database
        distanceVeloRepository.saveAndFlush(distanceVelo);

        int databaseSizeBeforeDelete = distanceVeloRepository.findAll().size();

        // Delete the distanceVelo
        restDistanceVeloMockMvc
            .perform(delete("/api/distance-velos/{id}", distanceVelo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DistanceVelo> distanceVeloList = distanceVeloRepository.findAll();
        assertThat(distanceVeloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
