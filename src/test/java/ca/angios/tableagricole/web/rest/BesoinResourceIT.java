package ca.angios.tableagricole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ca.angios.tableagricole.TableAgricoleApp;
import ca.angios.tableagricole.domain.Besoin;
import ca.angios.tableagricole.domain.enumeration.BesoinFreq;
import ca.angios.tableagricole.domain.enumeration.TypeBesoin;
import ca.angios.tableagricole.repository.BesoinRepository;
import ca.angios.tableagricole.service.BesoinService;
import ca.angios.tableagricole.service.dto.BesoinDTO;
import ca.angios.tableagricole.service.mapper.BesoinMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link BesoinResource} REST controller.
 */
@SpringBootTest(classes = TableAgricoleApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BesoinResourceIT {
    private static final Long DEFAULT_ID_BESOIN = 1L;
    private static final Long UPDATED_ID_BESOIN = 2L;

    private static final BesoinFreq DEFAULT_BESOIN_FREQUENCE = BesoinFreq.PERIODIQUE;
    private static final BesoinFreq UPDATED_BESOIN_FREQUENCE = BesoinFreq.CONTINU;

    private static final TypeBesoin DEFAULT_TYPE_BESOIN = TypeBesoin.SURPLUSANNONCE;
    private static final TypeBesoin UPDATED_TYPE_BESOIN = TypeBesoin.BESOINALIMENT;

    private static final Long DEFAULT_QUANTITE = 1L;
    private static final Long UPDATED_QUANTITE = 2L;

    private static final Instant DEFAULT_DATE_PEREMPTION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_PEREMPTION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_BESOIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_BESOIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TITRE_EMPLOI = "AAAAAAAAAA";
    private static final String UPDATED_TITRE_EMPLOI = "BBBBBBBBBB";

    private static final String DEFAULT_TACHE_PRINCIPALE = "AAAAAAAAAA";
    private static final String UPDATED_TACHE_PRINCIPALE = "BBBBBBBBBB";

    private static final Long DEFAULT_NB_HEURE = 1L;
    private static final Long UPDATED_NB_HEURE = 2L;

    private static final Long DEFAULT_DUREE_CONTRAT = 1L;
    private static final Long UPDATED_DUREE_CONTRAT = 2L;

    private static final Instant DEFAULT_DATE_ENTREE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_ENTREE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_NB_BENEFICIAIRE = 1L;
    private static final Long UPDATED_NB_BENEFICIAIRE = 2L;

    private static final String DEFAULT_SERVICE_SOUHAITE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_SOUHAITE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_RECUPERATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_RECUPERATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BesoinRepository besoinRepository;

    @Autowired
    private BesoinMapper besoinMapper;

    @Autowired
    private BesoinService besoinService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBesoinMockMvc;

    private Besoin besoin;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Besoin createEntity(EntityManager em) {
        Besoin besoin = new Besoin()
            .idBesoin(DEFAULT_ID_BESOIN)
            .besoinFrequence(DEFAULT_BESOIN_FREQUENCE)
            .typeBesoin(DEFAULT_TYPE_BESOIN)
            .quantite(DEFAULT_QUANTITE)
            .datePeremption(DEFAULT_DATE_PEREMPTION)
            .dateBesoin(DEFAULT_DATE_BESOIN)
            .titreEmploi(DEFAULT_TITRE_EMPLOI)
            .tachePrincipale(DEFAULT_TACHE_PRINCIPALE)
            .nbHeure(DEFAULT_NB_HEURE)
            .dureeContrat(DEFAULT_DUREE_CONTRAT)
            .dateEntree(DEFAULT_DATE_ENTREE)
            .nbBeneficiaire(DEFAULT_NB_BENEFICIAIRE)
            .serviceSouhaite(DEFAULT_SERVICE_SOUHAITE)
            .dateRecuperation(DEFAULT_DATE_RECUPERATION);
        return besoin;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Besoin createUpdatedEntity(EntityManager em) {
        Besoin besoin = new Besoin()
            .idBesoin(UPDATED_ID_BESOIN)
            .besoinFrequence(UPDATED_BESOIN_FREQUENCE)
            .typeBesoin(UPDATED_TYPE_BESOIN)
            .quantite(UPDATED_QUANTITE)
            .datePeremption(UPDATED_DATE_PEREMPTION)
            .dateBesoin(UPDATED_DATE_BESOIN)
            .titreEmploi(UPDATED_TITRE_EMPLOI)
            .tachePrincipale(UPDATED_TACHE_PRINCIPALE)
            .nbHeure(UPDATED_NB_HEURE)
            .dureeContrat(UPDATED_DUREE_CONTRAT)
            .dateEntree(UPDATED_DATE_ENTREE)
            .nbBeneficiaire(UPDATED_NB_BENEFICIAIRE)
            .serviceSouhaite(UPDATED_SERVICE_SOUHAITE)
            .dateRecuperation(UPDATED_DATE_RECUPERATION);
        return besoin;
    }

    @BeforeEach
    public void initTest() {
        besoin = createEntity(em);
    }

    @Test
    @Transactional
    public void createBesoin() throws Exception {
        int databaseSizeBeforeCreate = besoinRepository.findAll().size();
        // Create the Besoin
        BesoinDTO besoinDTO = besoinMapper.toDto(besoin);
        restBesoinMockMvc
            .perform(post("/api/besoins").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(besoinDTO)))
            .andExpect(status().isCreated());

        // Validate the Besoin in the database
        List<Besoin> besoinList = besoinRepository.findAll();
        assertThat(besoinList).hasSize(databaseSizeBeforeCreate + 1);
        Besoin testBesoin = besoinList.get(besoinList.size() - 1);
        assertThat(testBesoin.getIdBesoin()).isEqualTo(DEFAULT_ID_BESOIN);
        assertThat(testBesoin.getBesoinFrequence()).isEqualTo(DEFAULT_BESOIN_FREQUENCE);
        assertThat(testBesoin.getTypeBesoin()).isEqualTo(DEFAULT_TYPE_BESOIN);
        assertThat(testBesoin.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
        assertThat(testBesoin.getDatePeremption()).isEqualTo(DEFAULT_DATE_PEREMPTION);
        assertThat(testBesoin.getDateBesoin()).isEqualTo(DEFAULT_DATE_BESOIN);
        assertThat(testBesoin.getTitreEmploi()).isEqualTo(DEFAULT_TITRE_EMPLOI);
        assertThat(testBesoin.getTachePrincipale()).isEqualTo(DEFAULT_TACHE_PRINCIPALE);
        assertThat(testBesoin.getNbHeure()).isEqualTo(DEFAULT_NB_HEURE);
        assertThat(testBesoin.getDureeContrat()).isEqualTo(DEFAULT_DUREE_CONTRAT);
        assertThat(testBesoin.getDateEntree()).isEqualTo(DEFAULT_DATE_ENTREE);
        assertThat(testBesoin.getNbBeneficiaire()).isEqualTo(DEFAULT_NB_BENEFICIAIRE);
        assertThat(testBesoin.getServiceSouhaite()).isEqualTo(DEFAULT_SERVICE_SOUHAITE);
        assertThat(testBesoin.getDateRecuperation()).isEqualTo(DEFAULT_DATE_RECUPERATION);
    }

    @Test
    @Transactional
    public void createBesoinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = besoinRepository.findAll().size();

        // Create the Besoin with an existing ID
        besoin.setId(1L);
        BesoinDTO besoinDTO = besoinMapper.toDto(besoin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBesoinMockMvc
            .perform(post("/api/besoins").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(besoinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Besoin in the database
        List<Besoin> besoinList = besoinRepository.findAll();
        assertThat(besoinList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBesoins() throws Exception {
        // Initialize the database
        besoinRepository.saveAndFlush(besoin);

        // Get all the besoinList
        restBesoinMockMvc
            .perform(get("/api/besoins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(besoin.getId().intValue())))
            .andExpect(jsonPath("$.[*].idBesoin").value(hasItem(DEFAULT_ID_BESOIN.intValue())))
            .andExpect(jsonPath("$.[*].besoinFrequence").value(hasItem(DEFAULT_BESOIN_FREQUENCE.toString())))
            .andExpect(jsonPath("$.[*].typeBesoin").value(hasItem(DEFAULT_TYPE_BESOIN.toString())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE.intValue())))
            .andExpect(jsonPath("$.[*].datePeremption").value(hasItem(DEFAULT_DATE_PEREMPTION.toString())))
            .andExpect(jsonPath("$.[*].dateBesoin").value(hasItem(DEFAULT_DATE_BESOIN.toString())))
            .andExpect(jsonPath("$.[*].titreEmploi").value(hasItem(DEFAULT_TITRE_EMPLOI)))
            .andExpect(jsonPath("$.[*].tachePrincipale").value(hasItem(DEFAULT_TACHE_PRINCIPALE)))
            .andExpect(jsonPath("$.[*].nbHeure").value(hasItem(DEFAULT_NB_HEURE.intValue())))
            .andExpect(jsonPath("$.[*].dureeContrat").value(hasItem(DEFAULT_DUREE_CONTRAT.intValue())))
            .andExpect(jsonPath("$.[*].dateEntree").value(hasItem(DEFAULT_DATE_ENTREE.toString())))
            .andExpect(jsonPath("$.[*].nbBeneficiaire").value(hasItem(DEFAULT_NB_BENEFICIAIRE.intValue())))
            .andExpect(jsonPath("$.[*].serviceSouhaite").value(hasItem(DEFAULT_SERVICE_SOUHAITE)))
            .andExpect(jsonPath("$.[*].dateRecuperation").value(hasItem(DEFAULT_DATE_RECUPERATION.toString())));
    }

    @Test
    @Transactional
    public void getBesoin() throws Exception {
        // Initialize the database
        besoinRepository.saveAndFlush(besoin);

        // Get the besoin
        restBesoinMockMvc
            .perform(get("/api/besoins/{id}", besoin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(besoin.getId().intValue()))
            .andExpect(jsonPath("$.idBesoin").value(DEFAULT_ID_BESOIN.intValue()))
            .andExpect(jsonPath("$.besoinFrequence").value(DEFAULT_BESOIN_FREQUENCE.toString()))
            .andExpect(jsonPath("$.typeBesoin").value(DEFAULT_TYPE_BESOIN.toString()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE.intValue()))
            .andExpect(jsonPath("$.datePeremption").value(DEFAULT_DATE_PEREMPTION.toString()))
            .andExpect(jsonPath("$.dateBesoin").value(DEFAULT_DATE_BESOIN.toString()))
            .andExpect(jsonPath("$.titreEmploi").value(DEFAULT_TITRE_EMPLOI))
            .andExpect(jsonPath("$.tachePrincipale").value(DEFAULT_TACHE_PRINCIPALE))
            .andExpect(jsonPath("$.nbHeure").value(DEFAULT_NB_HEURE.intValue()))
            .andExpect(jsonPath("$.dureeContrat").value(DEFAULT_DUREE_CONTRAT.intValue()))
            .andExpect(jsonPath("$.dateEntree").value(DEFAULT_DATE_ENTREE.toString()))
            .andExpect(jsonPath("$.nbBeneficiaire").value(DEFAULT_NB_BENEFICIAIRE.intValue()))
            .andExpect(jsonPath("$.serviceSouhaite").value(DEFAULT_SERVICE_SOUHAITE))
            .andExpect(jsonPath("$.dateRecuperation").value(DEFAULT_DATE_RECUPERATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBesoin() throws Exception {
        // Get the besoin
        restBesoinMockMvc.perform(get("/api/besoins/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBesoin() throws Exception {
        // Initialize the database
        besoinRepository.saveAndFlush(besoin);

        int databaseSizeBeforeUpdate = besoinRepository.findAll().size();

        // Update the besoin
        Besoin updatedBesoin = besoinRepository.findById(besoin.getId()).get();
        // Disconnect from session so that the updates on updatedBesoin are not directly saved in db
        em.detach(updatedBesoin);
        updatedBesoin
            .idBesoin(UPDATED_ID_BESOIN)
            .besoinFrequence(UPDATED_BESOIN_FREQUENCE)
            .typeBesoin(UPDATED_TYPE_BESOIN)
            .quantite(UPDATED_QUANTITE)
            .datePeremption(UPDATED_DATE_PEREMPTION)
            .dateBesoin(UPDATED_DATE_BESOIN)
            .titreEmploi(UPDATED_TITRE_EMPLOI)
            .tachePrincipale(UPDATED_TACHE_PRINCIPALE)
            .nbHeure(UPDATED_NB_HEURE)
            .dureeContrat(UPDATED_DUREE_CONTRAT)
            .dateEntree(UPDATED_DATE_ENTREE)
            .nbBeneficiaire(UPDATED_NB_BENEFICIAIRE)
            .serviceSouhaite(UPDATED_SERVICE_SOUHAITE)
            .dateRecuperation(UPDATED_DATE_RECUPERATION);
        BesoinDTO besoinDTO = besoinMapper.toDto(updatedBesoin);

        restBesoinMockMvc
            .perform(put("/api/besoins").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(besoinDTO)))
            .andExpect(status().isOk());

        // Validate the Besoin in the database
        List<Besoin> besoinList = besoinRepository.findAll();
        assertThat(besoinList).hasSize(databaseSizeBeforeUpdate);
        Besoin testBesoin = besoinList.get(besoinList.size() - 1);
        assertThat(testBesoin.getIdBesoin()).isEqualTo(UPDATED_ID_BESOIN);
        assertThat(testBesoin.getBesoinFrequence()).isEqualTo(UPDATED_BESOIN_FREQUENCE);
        assertThat(testBesoin.getTypeBesoin()).isEqualTo(UPDATED_TYPE_BESOIN);
        assertThat(testBesoin.getQuantite()).isEqualTo(UPDATED_QUANTITE);
        assertThat(testBesoin.getDatePeremption()).isEqualTo(UPDATED_DATE_PEREMPTION);
        assertThat(testBesoin.getDateBesoin()).isEqualTo(UPDATED_DATE_BESOIN);
        assertThat(testBesoin.getTitreEmploi()).isEqualTo(UPDATED_TITRE_EMPLOI);
        assertThat(testBesoin.getTachePrincipale()).isEqualTo(UPDATED_TACHE_PRINCIPALE);
        assertThat(testBesoin.getNbHeure()).isEqualTo(UPDATED_NB_HEURE);
        assertThat(testBesoin.getDureeContrat()).isEqualTo(UPDATED_DUREE_CONTRAT);
        assertThat(testBesoin.getDateEntree()).isEqualTo(UPDATED_DATE_ENTREE);
        assertThat(testBesoin.getNbBeneficiaire()).isEqualTo(UPDATED_NB_BENEFICIAIRE);
        assertThat(testBesoin.getServiceSouhaite()).isEqualTo(UPDATED_SERVICE_SOUHAITE);
        assertThat(testBesoin.getDateRecuperation()).isEqualTo(UPDATED_DATE_RECUPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingBesoin() throws Exception {
        int databaseSizeBeforeUpdate = besoinRepository.findAll().size();

        // Create the Besoin
        BesoinDTO besoinDTO = besoinMapper.toDto(besoin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBesoinMockMvc
            .perform(put("/api/besoins").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(besoinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Besoin in the database
        List<Besoin> besoinList = besoinRepository.findAll();
        assertThat(besoinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBesoin() throws Exception {
        // Initialize the database
        besoinRepository.saveAndFlush(besoin);

        int databaseSizeBeforeDelete = besoinRepository.findAll().size();

        // Delete the besoin
        restBesoinMockMvc
            .perform(delete("/api/besoins/{id}", besoin.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Besoin> besoinList = besoinRepository.findAll();
        assertThat(besoinList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
