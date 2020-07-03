package ca.angios.tableagricole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ca.angios.tableagricole.TableAgricoleApp;
import ca.angios.tableagricole.domain.DistanceVeh;
import ca.angios.tableagricole.repository.DistanceVehRepository;
import ca.angios.tableagricole.service.DistanceVehService;
import ca.angios.tableagricole.service.dto.DistanceVehDTO;
import ca.angios.tableagricole.service.mapper.DistanceVehMapper;
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
 * Integration tests for the {@link DistanceVehResource} REST controller.
 */
@SpringBootTest(classes = TableAgricoleApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DistanceVehResourceIT {
    private static final Long DEFAULT_ID_DISTANCE_VEH = 1L;
    private static final Long UPDATED_ID_DISTANCE_VEH = 2L;

    private static final String DEFAULT_DSC_DISTANCE_VEH = "AAAAAAAAAA";
    private static final String UPDATED_DSC_DISTANCE_VEH = "BBBBBBBBBB";

    @Autowired
    private DistanceVehRepository distanceVehRepository;

    @Autowired
    private DistanceVehMapper distanceVehMapper;

    @Autowired
    private DistanceVehService distanceVehService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDistanceVehMockMvc;

    private DistanceVeh distanceVeh;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DistanceVeh createEntity(EntityManager em) {
        DistanceVeh distanceVeh = new DistanceVeh().idDistanceVeh(DEFAULT_ID_DISTANCE_VEH).dscDistanceVeh(DEFAULT_DSC_DISTANCE_VEH);
        return distanceVeh;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DistanceVeh createUpdatedEntity(EntityManager em) {
        DistanceVeh distanceVeh = new DistanceVeh().idDistanceVeh(UPDATED_ID_DISTANCE_VEH).dscDistanceVeh(UPDATED_DSC_DISTANCE_VEH);
        return distanceVeh;
    }

    @BeforeEach
    public void initTest() {
        distanceVeh = createEntity(em);
    }

    @Test
    @Transactional
    public void createDistanceVeh() throws Exception {
        int databaseSizeBeforeCreate = distanceVehRepository.findAll().size();
        // Create the DistanceVeh
        DistanceVehDTO distanceVehDTO = distanceVehMapper.toDto(distanceVeh);
        restDistanceVehMockMvc
            .perform(
                post("/api/distance-vehs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(distanceVehDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DistanceVeh in the database
        List<DistanceVeh> distanceVehList = distanceVehRepository.findAll();
        assertThat(distanceVehList).hasSize(databaseSizeBeforeCreate + 1);
        DistanceVeh testDistanceVeh = distanceVehList.get(distanceVehList.size() - 1);
        assertThat(testDistanceVeh.getIdDistanceVeh()).isEqualTo(DEFAULT_ID_DISTANCE_VEH);
        assertThat(testDistanceVeh.getDscDistanceVeh()).isEqualTo(DEFAULT_DSC_DISTANCE_VEH);
    }

    @Test
    @Transactional
    public void createDistanceVehWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = distanceVehRepository.findAll().size();

        // Create the DistanceVeh with an existing ID
        distanceVeh.setId(1L);
        DistanceVehDTO distanceVehDTO = distanceVehMapper.toDto(distanceVeh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDistanceVehMockMvc
            .perform(
                post("/api/distance-vehs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(distanceVehDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DistanceVeh in the database
        List<DistanceVeh> distanceVehList = distanceVehRepository.findAll();
        assertThat(distanceVehList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDscDistanceVehIsRequired() throws Exception {
        int databaseSizeBeforeTest = distanceVehRepository.findAll().size();
        // set the field null
        distanceVeh.setDscDistanceVeh(null);

        // Create the DistanceVeh, which fails.
        DistanceVehDTO distanceVehDTO = distanceVehMapper.toDto(distanceVeh);

        restDistanceVehMockMvc
            .perform(
                post("/api/distance-vehs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(distanceVehDTO))
            )
            .andExpect(status().isBadRequest());

        List<DistanceVeh> distanceVehList = distanceVehRepository.findAll();
        assertThat(distanceVehList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDistanceVehs() throws Exception {
        // Initialize the database
        distanceVehRepository.saveAndFlush(distanceVeh);

        // Get all the distanceVehList
        restDistanceVehMockMvc
            .perform(get("/api/distance-vehs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(distanceVeh.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDistanceVeh").value(hasItem(DEFAULT_ID_DISTANCE_VEH.intValue())))
            .andExpect(jsonPath("$.[*].dscDistanceVeh").value(hasItem(DEFAULT_DSC_DISTANCE_VEH)));
    }

    @Test
    @Transactional
    public void getDistanceVeh() throws Exception {
        // Initialize the database
        distanceVehRepository.saveAndFlush(distanceVeh);

        // Get the distanceVeh
        restDistanceVehMockMvc
            .perform(get("/api/distance-vehs/{id}", distanceVeh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(distanceVeh.getId().intValue()))
            .andExpect(jsonPath("$.idDistanceVeh").value(DEFAULT_ID_DISTANCE_VEH.intValue()))
            .andExpect(jsonPath("$.dscDistanceVeh").value(DEFAULT_DSC_DISTANCE_VEH));
    }

    @Test
    @Transactional
    public void getNonExistingDistanceVeh() throws Exception {
        // Get the distanceVeh
        restDistanceVehMockMvc.perform(get("/api/distance-vehs/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDistanceVeh() throws Exception {
        // Initialize the database
        distanceVehRepository.saveAndFlush(distanceVeh);

        int databaseSizeBeforeUpdate = distanceVehRepository.findAll().size();

        // Update the distanceVeh
        DistanceVeh updatedDistanceVeh = distanceVehRepository.findById(distanceVeh.getId()).get();
        // Disconnect from session so that the updates on updatedDistanceVeh are not directly saved in db
        em.detach(updatedDistanceVeh);
        updatedDistanceVeh.idDistanceVeh(UPDATED_ID_DISTANCE_VEH).dscDistanceVeh(UPDATED_DSC_DISTANCE_VEH);
        DistanceVehDTO distanceVehDTO = distanceVehMapper.toDto(updatedDistanceVeh);

        restDistanceVehMockMvc
            .perform(
                put("/api/distance-vehs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(distanceVehDTO))
            )
            .andExpect(status().isOk());

        // Validate the DistanceVeh in the database
        List<DistanceVeh> distanceVehList = distanceVehRepository.findAll();
        assertThat(distanceVehList).hasSize(databaseSizeBeforeUpdate);
        DistanceVeh testDistanceVeh = distanceVehList.get(distanceVehList.size() - 1);
        assertThat(testDistanceVeh.getIdDistanceVeh()).isEqualTo(UPDATED_ID_DISTANCE_VEH);
        assertThat(testDistanceVeh.getDscDistanceVeh()).isEqualTo(UPDATED_DSC_DISTANCE_VEH);
    }

    @Test
    @Transactional
    public void updateNonExistingDistanceVeh() throws Exception {
        int databaseSizeBeforeUpdate = distanceVehRepository.findAll().size();

        // Create the DistanceVeh
        DistanceVehDTO distanceVehDTO = distanceVehMapper.toDto(distanceVeh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDistanceVehMockMvc
            .perform(
                put("/api/distance-vehs").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(distanceVehDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DistanceVeh in the database
        List<DistanceVeh> distanceVehList = distanceVehRepository.findAll();
        assertThat(distanceVehList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDistanceVeh() throws Exception {
        // Initialize the database
        distanceVehRepository.saveAndFlush(distanceVeh);

        int databaseSizeBeforeDelete = distanceVehRepository.findAll().size();

        // Delete the distanceVeh
        restDistanceVehMockMvc
            .perform(delete("/api/distance-vehs/{id}", distanceVeh.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DistanceVeh> distanceVehList = distanceVehRepository.findAll();
        assertThat(distanceVehList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
