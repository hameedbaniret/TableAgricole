package ca.angios.tableagricole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ca.angios.tableagricole.TableAgricoleApp;
import ca.angios.tableagricole.domain.TypeAliment;
import ca.angios.tableagricole.repository.TypeAlimentRepository;
import ca.angios.tableagricole.service.TypeAlimentService;
import ca.angios.tableagricole.service.dto.TypeAlimentDTO;
import ca.angios.tableagricole.service.mapper.TypeAlimentMapper;
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
 * Integration tests for the {@link TypeAlimentResource} REST controller.
 */
@SpringBootTest(classes = TableAgricoleApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeAlimentResourceIT {
    private static final Long DEFAULT_ID_TYPE_ALIMENT = 1L;
    private static final Long UPDATED_ID_TYPE_ALIMENT = 2L;

    private static final String DEFAULT_DSC_TYPE_ALIMENT = "AAAAAAAAAA";
    private static final String UPDATED_DSC_TYPE_ALIMENT = "BBBBBBBBBB";

    @Autowired
    private TypeAlimentRepository typeAlimentRepository;

    @Autowired
    private TypeAlimentMapper typeAlimentMapper;

    @Autowired
    private TypeAlimentService typeAlimentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeAlimentMockMvc;

    private TypeAliment typeAliment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAliment createEntity(EntityManager em) {
        TypeAliment typeAliment = new TypeAliment().idTypeAliment(DEFAULT_ID_TYPE_ALIMENT).dscTypeAliment(DEFAULT_DSC_TYPE_ALIMENT);
        return typeAliment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAliment createUpdatedEntity(EntityManager em) {
        TypeAliment typeAliment = new TypeAliment().idTypeAliment(UPDATED_ID_TYPE_ALIMENT).dscTypeAliment(UPDATED_DSC_TYPE_ALIMENT);
        return typeAliment;
    }

    @BeforeEach
    public void initTest() {
        typeAliment = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeAliment() throws Exception {
        int databaseSizeBeforeCreate = typeAlimentRepository.findAll().size();
        // Create the TypeAliment
        TypeAlimentDTO typeAlimentDTO = typeAlimentMapper.toDto(typeAliment);
        restTypeAlimentMockMvc
            .perform(
                post("/api/type-aliments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typeAlimentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TypeAliment in the database
        List<TypeAliment> typeAlimentList = typeAlimentRepository.findAll();
        assertThat(typeAlimentList).hasSize(databaseSizeBeforeCreate + 1);
        TypeAliment testTypeAliment = typeAlimentList.get(typeAlimentList.size() - 1);
        assertThat(testTypeAliment.getIdTypeAliment()).isEqualTo(DEFAULT_ID_TYPE_ALIMENT);
        assertThat(testTypeAliment.getDscTypeAliment()).isEqualTo(DEFAULT_DSC_TYPE_ALIMENT);
    }

    @Test
    @Transactional
    public void createTypeAlimentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeAlimentRepository.findAll().size();

        // Create the TypeAliment with an existing ID
        typeAliment.setId(1L);
        TypeAlimentDTO typeAlimentDTO = typeAlimentMapper.toDto(typeAliment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeAlimentMockMvc
            .perform(
                post("/api/type-aliments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typeAlimentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypeAliment in the database
        List<TypeAliment> typeAlimentList = typeAlimentRepository.findAll();
        assertThat(typeAlimentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDscTypeAlimentIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeAlimentRepository.findAll().size();
        // set the field null
        typeAliment.setDscTypeAliment(null);

        // Create the TypeAliment, which fails.
        TypeAlimentDTO typeAlimentDTO = typeAlimentMapper.toDto(typeAliment);

        restTypeAlimentMockMvc
            .perform(
                post("/api/type-aliments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typeAlimentDTO))
            )
            .andExpect(status().isBadRequest());

        List<TypeAliment> typeAlimentList = typeAlimentRepository.findAll();
        assertThat(typeAlimentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeAliments() throws Exception {
        // Initialize the database
        typeAlimentRepository.saveAndFlush(typeAliment);

        // Get all the typeAlimentList
        restTypeAlimentMockMvc
            .perform(get("/api/type-aliments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeAliment.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTypeAliment").value(hasItem(DEFAULT_ID_TYPE_ALIMENT.intValue())))
            .andExpect(jsonPath("$.[*].dscTypeAliment").value(hasItem(DEFAULT_DSC_TYPE_ALIMENT)));
    }

    @Test
    @Transactional
    public void getTypeAliment() throws Exception {
        // Initialize the database
        typeAlimentRepository.saveAndFlush(typeAliment);

        // Get the typeAliment
        restTypeAlimentMockMvc
            .perform(get("/api/type-aliments/{id}", typeAliment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeAliment.getId().intValue()))
            .andExpect(jsonPath("$.idTypeAliment").value(DEFAULT_ID_TYPE_ALIMENT.intValue()))
            .andExpect(jsonPath("$.dscTypeAliment").value(DEFAULT_DSC_TYPE_ALIMENT));
    }

    @Test
    @Transactional
    public void getNonExistingTypeAliment() throws Exception {
        // Get the typeAliment
        restTypeAlimentMockMvc.perform(get("/api/type-aliments/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeAliment() throws Exception {
        // Initialize the database
        typeAlimentRepository.saveAndFlush(typeAliment);

        int databaseSizeBeforeUpdate = typeAlimentRepository.findAll().size();

        // Update the typeAliment
        TypeAliment updatedTypeAliment = typeAlimentRepository.findById(typeAliment.getId()).get();
        // Disconnect from session so that the updates on updatedTypeAliment are not directly saved in db
        em.detach(updatedTypeAliment);
        updatedTypeAliment.idTypeAliment(UPDATED_ID_TYPE_ALIMENT).dscTypeAliment(UPDATED_DSC_TYPE_ALIMENT);
        TypeAlimentDTO typeAlimentDTO = typeAlimentMapper.toDto(updatedTypeAliment);

        restTypeAlimentMockMvc
            .perform(
                put("/api/type-aliments").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(typeAlimentDTO))
            )
            .andExpect(status().isOk());

        // Validate the TypeAliment in the database
        List<TypeAliment> typeAlimentList = typeAlimentRepository.findAll();
        assertThat(typeAlimentList).hasSize(databaseSizeBeforeUpdate);
        TypeAliment testTypeAliment = typeAlimentList.get(typeAlimentList.size() - 1);
        assertThat(testTypeAliment.getIdTypeAliment()).isEqualTo(UPDATED_ID_TYPE_ALIMENT);
        assertThat(testTypeAliment.getDscTypeAliment()).isEqualTo(UPDATED_DSC_TYPE_ALIMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeAliment() throws Exception {
        int databaseSizeBeforeUpdate = typeAlimentRepository.findAll().size();

        // Create the TypeAliment
        TypeAlimentDTO typeAlimentDTO = typeAlimentMapper.toDto(typeAliment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeAlimentMockMvc
            .perform(
                put("/api/type-aliments").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(typeAlimentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypeAliment in the database
        List<TypeAliment> typeAlimentList = typeAlimentRepository.findAll();
        assertThat(typeAlimentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeAliment() throws Exception {
        // Initialize the database
        typeAlimentRepository.saveAndFlush(typeAliment);

        int databaseSizeBeforeDelete = typeAlimentRepository.findAll().size();

        // Delete the typeAliment
        restTypeAlimentMockMvc
            .perform(delete("/api/type-aliments/{id}", typeAliment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeAliment> typeAlimentList = typeAlimentRepository.findAll();
        assertThat(typeAlimentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
