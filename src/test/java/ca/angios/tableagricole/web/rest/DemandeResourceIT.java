package ca.angios.tableagricole.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ca.angios.tableagricole.TableAgricoleApp;
import ca.angios.tableagricole.domain.Demande;
import ca.angios.tableagricole.domain.enumeration.Frequence;
import ca.angios.tableagricole.repository.DemandeRepository;
import ca.angios.tableagricole.service.DemandeService;
import ca.angios.tableagricole.service.dto.DemandeDTO;
import ca.angios.tableagricole.service.mapper.DemandeMapper;
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
 * Integration tests for the {@link DemandeResource} REST controller.
 */
@SpringBootTest(classes = TableAgricoleApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DemandeResourceIT {
    private static final Long DEFAULT_ID_DEMANDE = 1L;
    private static final Long UPDATED_ID_DEMANDE = 2L;

    private static final Frequence DEFAULT_FREQUENCE = Frequence.QUOTIDIEN;
    private static final Frequence UPDATED_FREQUENCE = Frequence.NFOISSEMAINE;

    private static final Boolean DEFAULT_FLAG_TYPE_DEP_DEPANNAGE_URGENCE = false;
    private static final Boolean UPDATED_FLAG_TYPE_DEP_DEPANNAGE_URGENCE = true;

    private static final Boolean DEFAULT_FLAG_TYPE_DEP_DEPANNAGE_REGULIER = false;
    private static final Boolean UPDATED_FLAG_TYPE_DEP_DEPANNAGE_REGULIER = true;

    private static final Boolean DEFAULT_FLAG_TYPE_DEP_POPOTE_ROULANTE = false;
    private static final Boolean UPDATED_FLAG_TYPE_DEP_POPOTE_ROULANTE = true;

    private static final Boolean DEFAULT_FLAG_TYPE_DEP_MARCHE_ABORDABLE = false;
    private static final Boolean UPDATED_FLAG_TYPE_DEP_MARCHE_ABORDABLE = true;

    private static final Boolean DEFAULT_FLAG_TYPE_DEP_ENTRAIDE_ALIMENTAIRE = false;
    private static final Boolean UPDATED_FLAG_TYPE_DEP_ENTRAIDE_ALIMENTAIRE = true;

    private static final Boolean DEFAULT_FLAG_TYPE_DEP_FRIGO_PARTAGE = false;
    private static final Boolean UPDATED_FLAG_TYPE_DEP_FRIGO_PARTAGE = true;

    private static final Boolean DEFAULT_FLAG_TYPE_DEP_AUTRE = false;
    private static final Boolean UPDATED_FLAG_TYPE_DEP_AUTRE = true;

    private static final String DEFAULT_BESOIN_TECHNIQUE = "AAAAAAAAAA";
    private static final String UPDATED_BESOIN_TECHNIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_BESOIN_URGENT = "AAAAAAAAAA";
    private static final String UPDATED_BESOIN_URGENT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FLAG_CAMION_REGRIGERE = false;
    private static final Boolean UPDATED_FLAG_CAMION_REGRIGERE = true;

    private static final Boolean DEFAULT_FLAG_PAS_CONGELATEUR = false;
    private static final Boolean UPDATED_FLAG_PAS_CONGELATEUR = true;

    private static final Boolean DEFAULT_FLAG_CONGELATEUR_IND = false;
    private static final Boolean UPDATED_FLAG_CONGELATEUR_IND = true;

    private static final Boolean DEFAULT_FLAG_CONGELATEUR_PLAIN_PIED = false;
    private static final Boolean UPDATED_FLAG_CONGELATEUR_PLAIN_PIED = true;

    private static final Boolean DEFAULT_FLAG_CONGELATEUR_RESIDENTIEL = false;
    private static final Boolean UPDATED_FLAG_CONGELATEUR_RESIDENTIEL = true;

    private static final Boolean DEFAULT_FLAG_PAS_REFRIGERATEUR = false;
    private static final Boolean UPDATED_FLAG_PAS_REFRIGERATEUR = true;

    private static final Boolean DEFAULT_FLAG_REFRIGERATEUR_IND = false;
    private static final Boolean UPDATED_FLAG_REFRIGERATEUR_IND = true;

    private static final Boolean DEFAULT_FLAG_REFRIGERATEUR_PLAIN_PIED = false;
    private static final Boolean UPDATED_FLAG_REFRIGERATEUR_PLAIN_PIED = true;

    private static final Boolean DEFAULT_FLAG_REFRIGERATEUR_RESIDENTIEL = false;
    private static final Boolean UPDATED_FLAG_REFRIGERATEUR_RESIDENTIEL = true;

    private static final Boolean DEFAULT_FLAG_ACCES_GLACIERE = false;
    private static final Boolean UPDATED_FLAG_ACCES_GLACIERE = true;

    private static final Boolean DEFAULT_FLAG_ACCES_CUISINE = false;
    private static final Boolean UPDATED_FLAG_ACCES_CUISINE = true;

    private static final Boolean DEFAULT_FLAG_ACCES_VEHICULE = false;
    private static final Boolean UPDATED_FLAG_ACCES_VEHICULE = true;

    private static final Instant DEFAULT_DATE_INSPECTION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_INSPECTION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_NOMBRE_BENEFICIAIRE = 1L;
    private static final Long UPDATED_NOMBRE_BENEFICIAIRE = 2L;

    private static final Long DEFAULT_PRC_AUGMENTATION_NB_BENEF = 1L;
    private static final Long UPDATED_PRC_AUGMENTATION_NB_BENEF = 2L;

    private static final Long DEFAULT_PRC_RH_PERDUE = 1L;
    private static final Long UPDATED_PRC_RH_PERDUE = 2L;

    private static final Long DEFAULT_PRC_BENEVOLE_PERDU = 1L;
    private static final Long UPDATED_PRC_BENEVOLE_PERDU = 2L;

    private static final Boolean DEFAULT_FLAG_DENREE_SUFFISANTE = false;
    private static final Boolean UPDATED_FLAG_DENREE_SUFFISANTE = true;

    private static final Boolean DEFAULT_FLAG_RH_SUFFISANT = false;
    private static final Boolean UPDATED_FLAG_RH_SUFFISANT = true;

    private static final Boolean DEFAULT_FLAG_VEGETARIEN = false;
    private static final Boolean UPDATED_FLAG_VEGETARIEN = true;

    private static final Boolean DEFAULT_FLAG_VEGETALIEN = false;
    private static final Boolean UPDATED_FLAG_VEGETALIEN = true;

    private static final Boolean DEFAULT_FLAG_ITEM_SANS_ETIQUETTE = false;
    private static final Boolean UPDATED_FLAG_ITEM_SANS_ETIQUETTE = true;

    private static final Boolean DEFAULT_FLAG_ITEM_DATE = false;
    private static final Boolean UPDATED_FLAG_ITEM_DATE = true;

    private static final Boolean DEFAULT_FLAG_HALAL = false;
    private static final Boolean UPDATED_FLAG_HALAL = true;

    private static final Boolean DEFAULT_FLAG_KASHER = false;
    private static final Boolean UPDATED_FLAG_KASHER = true;

    private static final String DEFAULT_BOITE_SUGGESTION = "AAAAAAAAAA";
    private static final String UPDATED_BOITE_SUGGESTION = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_RESSOURCE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_RESSOURCE = "BBBBBBBBBB";

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DemandeMapper demandeMapper;

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandeMockMvc;

    private Demande demande;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demande createEntity(EntityManager em) {
        Demande demande = new Demande()
            .idDemande(DEFAULT_ID_DEMANDE)
            .frequence(DEFAULT_FREQUENCE)
            .flagTypeDepDepannageUrgence(DEFAULT_FLAG_TYPE_DEP_DEPANNAGE_URGENCE)
            .flagTypeDepDepannageRegulier(DEFAULT_FLAG_TYPE_DEP_DEPANNAGE_REGULIER)
            .flagTypeDepPopoteRoulante(DEFAULT_FLAG_TYPE_DEP_POPOTE_ROULANTE)
            .flagTypeDepMarcheAbordable(DEFAULT_FLAG_TYPE_DEP_MARCHE_ABORDABLE)
            .flagTypeDepEntraideAlimentaire(DEFAULT_FLAG_TYPE_DEP_ENTRAIDE_ALIMENTAIRE)
            .flagTypeDepFrigoPartage(DEFAULT_FLAG_TYPE_DEP_FRIGO_PARTAGE)
            .flagTypeDepAutre(DEFAULT_FLAG_TYPE_DEP_AUTRE)
            .besoinTechnique(DEFAULT_BESOIN_TECHNIQUE)
            .besoinUrgent(DEFAULT_BESOIN_URGENT)
            .flagCamionRegrigere(DEFAULT_FLAG_CAMION_REGRIGERE)
            .flagPasCongelateur(DEFAULT_FLAG_PAS_CONGELATEUR)
            .flagCongelateurInd(DEFAULT_FLAG_CONGELATEUR_IND)
            .flagCongelateurPlainPied(DEFAULT_FLAG_CONGELATEUR_PLAIN_PIED)
            .flagCongelateurResidentiel(DEFAULT_FLAG_CONGELATEUR_RESIDENTIEL)
            .flagPasRefrigerateur(DEFAULT_FLAG_PAS_REFRIGERATEUR)
            .flagRefrigerateurInd(DEFAULT_FLAG_REFRIGERATEUR_IND)
            .flagRefrigerateurPlainPied(DEFAULT_FLAG_REFRIGERATEUR_PLAIN_PIED)
            .flagRefrigerateurResidentiel(DEFAULT_FLAG_REFRIGERATEUR_RESIDENTIEL)
            .flagAccesGlaciere(DEFAULT_FLAG_ACCES_GLACIERE)
            .flagAccesCuisine(DEFAULT_FLAG_ACCES_CUISINE)
            .flagAccesVehicule(DEFAULT_FLAG_ACCES_VEHICULE)
            .dateInspection(DEFAULT_DATE_INSPECTION)
            .nombreBeneficiaire(DEFAULT_NOMBRE_BENEFICIAIRE)
            .prcAugmentationNbBenef(DEFAULT_PRC_AUGMENTATION_NB_BENEF)
            .prcRHPerdue(DEFAULT_PRC_RH_PERDUE)
            .prcBenevolePerdu(DEFAULT_PRC_BENEVOLE_PERDU)
            .flagDenreeSuffisante(DEFAULT_FLAG_DENREE_SUFFISANTE)
            .flagRHSuffisant(DEFAULT_FLAG_RH_SUFFISANT)
            .flagVegetarien(DEFAULT_FLAG_VEGETARIEN)
            .flagVegetalien(DEFAULT_FLAG_VEGETALIEN)
            .flagItemSansEtiquette(DEFAULT_FLAG_ITEM_SANS_ETIQUETTE)
            .flagItemDate(DEFAULT_FLAG_ITEM_DATE)
            .flagHalal(DEFAULT_FLAG_HALAL)
            .flagKasher(DEFAULT_FLAG_KASHER)
            .boiteSuggestion(DEFAULT_BOITE_SUGGESTION)
            .autreRessource(DEFAULT_AUTRE_RESSOURCE);
        return demande;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demande createUpdatedEntity(EntityManager em) {
        Demande demande = new Demande()
            .idDemande(UPDATED_ID_DEMANDE)
            .frequence(UPDATED_FREQUENCE)
            .flagTypeDepDepannageUrgence(UPDATED_FLAG_TYPE_DEP_DEPANNAGE_URGENCE)
            .flagTypeDepDepannageRegulier(UPDATED_FLAG_TYPE_DEP_DEPANNAGE_REGULIER)
            .flagTypeDepPopoteRoulante(UPDATED_FLAG_TYPE_DEP_POPOTE_ROULANTE)
            .flagTypeDepMarcheAbordable(UPDATED_FLAG_TYPE_DEP_MARCHE_ABORDABLE)
            .flagTypeDepEntraideAlimentaire(UPDATED_FLAG_TYPE_DEP_ENTRAIDE_ALIMENTAIRE)
            .flagTypeDepFrigoPartage(UPDATED_FLAG_TYPE_DEP_FRIGO_PARTAGE)
            .flagTypeDepAutre(UPDATED_FLAG_TYPE_DEP_AUTRE)
            .besoinTechnique(UPDATED_BESOIN_TECHNIQUE)
            .besoinUrgent(UPDATED_BESOIN_URGENT)
            .flagCamionRegrigere(UPDATED_FLAG_CAMION_REGRIGERE)
            .flagPasCongelateur(UPDATED_FLAG_PAS_CONGELATEUR)
            .flagCongelateurInd(UPDATED_FLAG_CONGELATEUR_IND)
            .flagCongelateurPlainPied(UPDATED_FLAG_CONGELATEUR_PLAIN_PIED)
            .flagCongelateurResidentiel(UPDATED_FLAG_CONGELATEUR_RESIDENTIEL)
            .flagPasRefrigerateur(UPDATED_FLAG_PAS_REFRIGERATEUR)
            .flagRefrigerateurInd(UPDATED_FLAG_REFRIGERATEUR_IND)
            .flagRefrigerateurPlainPied(UPDATED_FLAG_REFRIGERATEUR_PLAIN_PIED)
            .flagRefrigerateurResidentiel(UPDATED_FLAG_REFRIGERATEUR_RESIDENTIEL)
            .flagAccesGlaciere(UPDATED_FLAG_ACCES_GLACIERE)
            .flagAccesCuisine(UPDATED_FLAG_ACCES_CUISINE)
            .flagAccesVehicule(UPDATED_FLAG_ACCES_VEHICULE)
            .dateInspection(UPDATED_DATE_INSPECTION)
            .nombreBeneficiaire(UPDATED_NOMBRE_BENEFICIAIRE)
            .prcAugmentationNbBenef(UPDATED_PRC_AUGMENTATION_NB_BENEF)
            .prcRHPerdue(UPDATED_PRC_RH_PERDUE)
            .prcBenevolePerdu(UPDATED_PRC_BENEVOLE_PERDU)
            .flagDenreeSuffisante(UPDATED_FLAG_DENREE_SUFFISANTE)
            .flagRHSuffisant(UPDATED_FLAG_RH_SUFFISANT)
            .flagVegetarien(UPDATED_FLAG_VEGETARIEN)
            .flagVegetalien(UPDATED_FLAG_VEGETALIEN)
            .flagItemSansEtiquette(UPDATED_FLAG_ITEM_SANS_ETIQUETTE)
            .flagItemDate(UPDATED_FLAG_ITEM_DATE)
            .flagHalal(UPDATED_FLAG_HALAL)
            .flagKasher(UPDATED_FLAG_KASHER)
            .boiteSuggestion(UPDATED_BOITE_SUGGESTION)
            .autreRessource(UPDATED_AUTRE_RESSOURCE);
        return demande;
    }

    @BeforeEach
    public void initTest() {
        demande = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemande() throws Exception {
        int databaseSizeBeforeCreate = demandeRepository.findAll().size();
        // Create the Demande
        DemandeDTO demandeDTO = demandeMapper.toDto(demande);
        restDemandeMockMvc
            .perform(post("/api/demandes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeDTO)))
            .andExpect(status().isCreated());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeCreate + 1);
        Demande testDemande = demandeList.get(demandeList.size() - 1);
        assertThat(testDemande.getIdDemande()).isEqualTo(DEFAULT_ID_DEMANDE);
        assertThat(testDemande.getFrequence()).isEqualTo(DEFAULT_FREQUENCE);
        assertThat(testDemande.isFlagTypeDepDepannageUrgence()).isEqualTo(DEFAULT_FLAG_TYPE_DEP_DEPANNAGE_URGENCE);
        assertThat(testDemande.isFlagTypeDepDepannageRegulier()).isEqualTo(DEFAULT_FLAG_TYPE_DEP_DEPANNAGE_REGULIER);
        assertThat(testDemande.isFlagTypeDepPopoteRoulante()).isEqualTo(DEFAULT_FLAG_TYPE_DEP_POPOTE_ROULANTE);
        assertThat(testDemande.isFlagTypeDepMarcheAbordable()).isEqualTo(DEFAULT_FLAG_TYPE_DEP_MARCHE_ABORDABLE);
        assertThat(testDemande.isFlagTypeDepEntraideAlimentaire()).isEqualTo(DEFAULT_FLAG_TYPE_DEP_ENTRAIDE_ALIMENTAIRE);
        assertThat(testDemande.isFlagTypeDepFrigoPartage()).isEqualTo(DEFAULT_FLAG_TYPE_DEP_FRIGO_PARTAGE);
        assertThat(testDemande.isFlagTypeDepAutre()).isEqualTo(DEFAULT_FLAG_TYPE_DEP_AUTRE);
        assertThat(testDemande.getBesoinTechnique()).isEqualTo(DEFAULT_BESOIN_TECHNIQUE);
        assertThat(testDemande.getBesoinUrgent()).isEqualTo(DEFAULT_BESOIN_URGENT);
        assertThat(testDemande.isFlagCamionRegrigere()).isEqualTo(DEFAULT_FLAG_CAMION_REGRIGERE);
        assertThat(testDemande.isFlagPasCongelateur()).isEqualTo(DEFAULT_FLAG_PAS_CONGELATEUR);
        assertThat(testDemande.isFlagCongelateurInd()).isEqualTo(DEFAULT_FLAG_CONGELATEUR_IND);
        assertThat(testDemande.isFlagCongelateurPlainPied()).isEqualTo(DEFAULT_FLAG_CONGELATEUR_PLAIN_PIED);
        assertThat(testDemande.isFlagCongelateurResidentiel()).isEqualTo(DEFAULT_FLAG_CONGELATEUR_RESIDENTIEL);
        assertThat(testDemande.isFlagPasRefrigerateur()).isEqualTo(DEFAULT_FLAG_PAS_REFRIGERATEUR);
        assertThat(testDemande.isFlagRefrigerateurInd()).isEqualTo(DEFAULT_FLAG_REFRIGERATEUR_IND);
        assertThat(testDemande.isFlagRefrigerateurPlainPied()).isEqualTo(DEFAULT_FLAG_REFRIGERATEUR_PLAIN_PIED);
        assertThat(testDemande.isFlagRefrigerateurResidentiel()).isEqualTo(DEFAULT_FLAG_REFRIGERATEUR_RESIDENTIEL);
        assertThat(testDemande.isFlagAccesGlaciere()).isEqualTo(DEFAULT_FLAG_ACCES_GLACIERE);
        assertThat(testDemande.isFlagAccesCuisine()).isEqualTo(DEFAULT_FLAG_ACCES_CUISINE);
        assertThat(testDemande.isFlagAccesVehicule()).isEqualTo(DEFAULT_FLAG_ACCES_VEHICULE);
        assertThat(testDemande.getDateInspection()).isEqualTo(DEFAULT_DATE_INSPECTION);
        assertThat(testDemande.getNombreBeneficiaire()).isEqualTo(DEFAULT_NOMBRE_BENEFICIAIRE);
        assertThat(testDemande.getPrcAugmentationNbBenef()).isEqualTo(DEFAULT_PRC_AUGMENTATION_NB_BENEF);
        assertThat(testDemande.getPrcRHPerdue()).isEqualTo(DEFAULT_PRC_RH_PERDUE);
        assertThat(testDemande.getPrcBenevolePerdu()).isEqualTo(DEFAULT_PRC_BENEVOLE_PERDU);
        assertThat(testDemande.isFlagDenreeSuffisante()).isEqualTo(DEFAULT_FLAG_DENREE_SUFFISANTE);
        assertThat(testDemande.isFlagRHSuffisant()).isEqualTo(DEFAULT_FLAG_RH_SUFFISANT);
        assertThat(testDemande.isFlagVegetarien()).isEqualTo(DEFAULT_FLAG_VEGETARIEN);
        assertThat(testDemande.isFlagVegetalien()).isEqualTo(DEFAULT_FLAG_VEGETALIEN);
        assertThat(testDemande.isFlagItemSansEtiquette()).isEqualTo(DEFAULT_FLAG_ITEM_SANS_ETIQUETTE);
        assertThat(testDemande.isFlagItemDate()).isEqualTo(DEFAULT_FLAG_ITEM_DATE);
        assertThat(testDemande.isFlagHalal()).isEqualTo(DEFAULT_FLAG_HALAL);
        assertThat(testDemande.isFlagKasher()).isEqualTo(DEFAULT_FLAG_KASHER);
        assertThat(testDemande.getBoiteSuggestion()).isEqualTo(DEFAULT_BOITE_SUGGESTION);
        assertThat(testDemande.getAutreRessource()).isEqualTo(DEFAULT_AUTRE_RESSOURCE);
    }

    @Test
    @Transactional
    public void createDemandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demandeRepository.findAll().size();

        // Create the Demande with an existing ID
        demande.setId(1L);
        DemandeDTO demandeDTO = demandeMapper.toDto(demande);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeMockMvc
            .perform(post("/api/demandes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDemandes() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        // Get all the demandeList
        restDemandeMockMvc
            .perform(get("/api/demandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demande.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDemande").value(hasItem(DEFAULT_ID_DEMANDE.intValue())))
            .andExpect(jsonPath("$.[*].frequence").value(hasItem(DEFAULT_FREQUENCE.toString())))
            .andExpect(jsonPath("$.[*].flagTypeDepDepannageUrgence").value(hasItem(DEFAULT_FLAG_TYPE_DEP_DEPANNAGE_URGENCE.booleanValue())))
            .andExpect(
                jsonPath("$.[*].flagTypeDepDepannageRegulier").value(hasItem(DEFAULT_FLAG_TYPE_DEP_DEPANNAGE_REGULIER.booleanValue()))
            )
            .andExpect(jsonPath("$.[*].flagTypeDepPopoteRoulante").value(hasItem(DEFAULT_FLAG_TYPE_DEP_POPOTE_ROULANTE.booleanValue())))
            .andExpect(jsonPath("$.[*].flagTypeDepMarcheAbordable").value(hasItem(DEFAULT_FLAG_TYPE_DEP_MARCHE_ABORDABLE.booleanValue())))
            .andExpect(
                jsonPath("$.[*].flagTypeDepEntraideAlimentaire").value(hasItem(DEFAULT_FLAG_TYPE_DEP_ENTRAIDE_ALIMENTAIRE.booleanValue()))
            )
            .andExpect(jsonPath("$.[*].flagTypeDepFrigoPartage").value(hasItem(DEFAULT_FLAG_TYPE_DEP_FRIGO_PARTAGE.booleanValue())))
            .andExpect(jsonPath("$.[*].flagTypeDepAutre").value(hasItem(DEFAULT_FLAG_TYPE_DEP_AUTRE.booleanValue())))
            .andExpect(jsonPath("$.[*].besoinTechnique").value(hasItem(DEFAULT_BESOIN_TECHNIQUE)))
            .andExpect(jsonPath("$.[*].besoinUrgent").value(hasItem(DEFAULT_BESOIN_URGENT)))
            .andExpect(jsonPath("$.[*].flagCamionRegrigere").value(hasItem(DEFAULT_FLAG_CAMION_REGRIGERE.booleanValue())))
            .andExpect(jsonPath("$.[*].flagPasCongelateur").value(hasItem(DEFAULT_FLAG_PAS_CONGELATEUR.booleanValue())))
            .andExpect(jsonPath("$.[*].flagCongelateurInd").value(hasItem(DEFAULT_FLAG_CONGELATEUR_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].flagCongelateurPlainPied").value(hasItem(DEFAULT_FLAG_CONGELATEUR_PLAIN_PIED.booleanValue())))
            .andExpect(jsonPath("$.[*].flagCongelateurResidentiel").value(hasItem(DEFAULT_FLAG_CONGELATEUR_RESIDENTIEL.booleanValue())))
            .andExpect(jsonPath("$.[*].flagPasRefrigerateur").value(hasItem(DEFAULT_FLAG_PAS_REFRIGERATEUR.booleanValue())))
            .andExpect(jsonPath("$.[*].flagRefrigerateurInd").value(hasItem(DEFAULT_FLAG_REFRIGERATEUR_IND.booleanValue())))
            .andExpect(jsonPath("$.[*].flagRefrigerateurPlainPied").value(hasItem(DEFAULT_FLAG_REFRIGERATEUR_PLAIN_PIED.booleanValue())))
            .andExpect(jsonPath("$.[*].flagRefrigerateurResidentiel").value(hasItem(DEFAULT_FLAG_REFRIGERATEUR_RESIDENTIEL.booleanValue())))
            .andExpect(jsonPath("$.[*].flagAccesGlaciere").value(hasItem(DEFAULT_FLAG_ACCES_GLACIERE.booleanValue())))
            .andExpect(jsonPath("$.[*].flagAccesCuisine").value(hasItem(DEFAULT_FLAG_ACCES_CUISINE.booleanValue())))
            .andExpect(jsonPath("$.[*].flagAccesVehicule").value(hasItem(DEFAULT_FLAG_ACCES_VEHICULE.booleanValue())))
            .andExpect(jsonPath("$.[*].dateInspection").value(hasItem(DEFAULT_DATE_INSPECTION.toString())))
            .andExpect(jsonPath("$.[*].nombreBeneficiaire").value(hasItem(DEFAULT_NOMBRE_BENEFICIAIRE.intValue())))
            .andExpect(jsonPath("$.[*].prcAugmentationNbBenef").value(hasItem(DEFAULT_PRC_AUGMENTATION_NB_BENEF.intValue())))
            .andExpect(jsonPath("$.[*].prcRHPerdue").value(hasItem(DEFAULT_PRC_RH_PERDUE.intValue())))
            .andExpect(jsonPath("$.[*].prcBenevolePerdu").value(hasItem(DEFAULT_PRC_BENEVOLE_PERDU.intValue())))
            .andExpect(jsonPath("$.[*].flagDenreeSuffisante").value(hasItem(DEFAULT_FLAG_DENREE_SUFFISANTE.booleanValue())))
            .andExpect(jsonPath("$.[*].flagRHSuffisant").value(hasItem(DEFAULT_FLAG_RH_SUFFISANT.booleanValue())))
            .andExpect(jsonPath("$.[*].flagVegetarien").value(hasItem(DEFAULT_FLAG_VEGETARIEN.booleanValue())))
            .andExpect(jsonPath("$.[*].flagVegetalien").value(hasItem(DEFAULT_FLAG_VEGETALIEN.booleanValue())))
            .andExpect(jsonPath("$.[*].flagItemSansEtiquette").value(hasItem(DEFAULT_FLAG_ITEM_SANS_ETIQUETTE.booleanValue())))
            .andExpect(jsonPath("$.[*].flagItemDate").value(hasItem(DEFAULT_FLAG_ITEM_DATE.booleanValue())))
            .andExpect(jsonPath("$.[*].flagHalal").value(hasItem(DEFAULT_FLAG_HALAL.booleanValue())))
            .andExpect(jsonPath("$.[*].flagKasher").value(hasItem(DEFAULT_FLAG_KASHER.booleanValue())))
            .andExpect(jsonPath("$.[*].boiteSuggestion").value(hasItem(DEFAULT_BOITE_SUGGESTION)))
            .andExpect(jsonPath("$.[*].autreRessource").value(hasItem(DEFAULT_AUTRE_RESSOURCE)));
    }

    @Test
    @Transactional
    public void getDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        // Get the demande
        restDemandeMockMvc
            .perform(get("/api/demandes/{id}", demande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demande.getId().intValue()))
            .andExpect(jsonPath("$.idDemande").value(DEFAULT_ID_DEMANDE.intValue()))
            .andExpect(jsonPath("$.frequence").value(DEFAULT_FREQUENCE.toString()))
            .andExpect(jsonPath("$.flagTypeDepDepannageUrgence").value(DEFAULT_FLAG_TYPE_DEP_DEPANNAGE_URGENCE.booleanValue()))
            .andExpect(jsonPath("$.flagTypeDepDepannageRegulier").value(DEFAULT_FLAG_TYPE_DEP_DEPANNAGE_REGULIER.booleanValue()))
            .andExpect(jsonPath("$.flagTypeDepPopoteRoulante").value(DEFAULT_FLAG_TYPE_DEP_POPOTE_ROULANTE.booleanValue()))
            .andExpect(jsonPath("$.flagTypeDepMarcheAbordable").value(DEFAULT_FLAG_TYPE_DEP_MARCHE_ABORDABLE.booleanValue()))
            .andExpect(jsonPath("$.flagTypeDepEntraideAlimentaire").value(DEFAULT_FLAG_TYPE_DEP_ENTRAIDE_ALIMENTAIRE.booleanValue()))
            .andExpect(jsonPath("$.flagTypeDepFrigoPartage").value(DEFAULT_FLAG_TYPE_DEP_FRIGO_PARTAGE.booleanValue()))
            .andExpect(jsonPath("$.flagTypeDepAutre").value(DEFAULT_FLAG_TYPE_DEP_AUTRE.booleanValue()))
            .andExpect(jsonPath("$.besoinTechnique").value(DEFAULT_BESOIN_TECHNIQUE))
            .andExpect(jsonPath("$.besoinUrgent").value(DEFAULT_BESOIN_URGENT))
            .andExpect(jsonPath("$.flagCamionRegrigere").value(DEFAULT_FLAG_CAMION_REGRIGERE.booleanValue()))
            .andExpect(jsonPath("$.flagPasCongelateur").value(DEFAULT_FLAG_PAS_CONGELATEUR.booleanValue()))
            .andExpect(jsonPath("$.flagCongelateurInd").value(DEFAULT_FLAG_CONGELATEUR_IND.booleanValue()))
            .andExpect(jsonPath("$.flagCongelateurPlainPied").value(DEFAULT_FLAG_CONGELATEUR_PLAIN_PIED.booleanValue()))
            .andExpect(jsonPath("$.flagCongelateurResidentiel").value(DEFAULT_FLAG_CONGELATEUR_RESIDENTIEL.booleanValue()))
            .andExpect(jsonPath("$.flagPasRefrigerateur").value(DEFAULT_FLAG_PAS_REFRIGERATEUR.booleanValue()))
            .andExpect(jsonPath("$.flagRefrigerateurInd").value(DEFAULT_FLAG_REFRIGERATEUR_IND.booleanValue()))
            .andExpect(jsonPath("$.flagRefrigerateurPlainPied").value(DEFAULT_FLAG_REFRIGERATEUR_PLAIN_PIED.booleanValue()))
            .andExpect(jsonPath("$.flagRefrigerateurResidentiel").value(DEFAULT_FLAG_REFRIGERATEUR_RESIDENTIEL.booleanValue()))
            .andExpect(jsonPath("$.flagAccesGlaciere").value(DEFAULT_FLAG_ACCES_GLACIERE.booleanValue()))
            .andExpect(jsonPath("$.flagAccesCuisine").value(DEFAULT_FLAG_ACCES_CUISINE.booleanValue()))
            .andExpect(jsonPath("$.flagAccesVehicule").value(DEFAULT_FLAG_ACCES_VEHICULE.booleanValue()))
            .andExpect(jsonPath("$.dateInspection").value(DEFAULT_DATE_INSPECTION.toString()))
            .andExpect(jsonPath("$.nombreBeneficiaire").value(DEFAULT_NOMBRE_BENEFICIAIRE.intValue()))
            .andExpect(jsonPath("$.prcAugmentationNbBenef").value(DEFAULT_PRC_AUGMENTATION_NB_BENEF.intValue()))
            .andExpect(jsonPath("$.prcRHPerdue").value(DEFAULT_PRC_RH_PERDUE.intValue()))
            .andExpect(jsonPath("$.prcBenevolePerdu").value(DEFAULT_PRC_BENEVOLE_PERDU.intValue()))
            .andExpect(jsonPath("$.flagDenreeSuffisante").value(DEFAULT_FLAG_DENREE_SUFFISANTE.booleanValue()))
            .andExpect(jsonPath("$.flagRHSuffisant").value(DEFAULT_FLAG_RH_SUFFISANT.booleanValue()))
            .andExpect(jsonPath("$.flagVegetarien").value(DEFAULT_FLAG_VEGETARIEN.booleanValue()))
            .andExpect(jsonPath("$.flagVegetalien").value(DEFAULT_FLAG_VEGETALIEN.booleanValue()))
            .andExpect(jsonPath("$.flagItemSansEtiquette").value(DEFAULT_FLAG_ITEM_SANS_ETIQUETTE.booleanValue()))
            .andExpect(jsonPath("$.flagItemDate").value(DEFAULT_FLAG_ITEM_DATE.booleanValue()))
            .andExpect(jsonPath("$.flagHalal").value(DEFAULT_FLAG_HALAL.booleanValue()))
            .andExpect(jsonPath("$.flagKasher").value(DEFAULT_FLAG_KASHER.booleanValue()))
            .andExpect(jsonPath("$.boiteSuggestion").value(DEFAULT_BOITE_SUGGESTION))
            .andExpect(jsonPath("$.autreRessource").value(DEFAULT_AUTRE_RESSOURCE));
    }

    @Test
    @Transactional
    public void getNonExistingDemande() throws Exception {
        // Get the demande
        restDemandeMockMvc.perform(get("/api/demandes/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        int databaseSizeBeforeUpdate = demandeRepository.findAll().size();

        // Update the demande
        Demande updatedDemande = demandeRepository.findById(demande.getId()).get();
        // Disconnect from session so that the updates on updatedDemande are not directly saved in db
        em.detach(updatedDemande);
        updatedDemande
            .idDemande(UPDATED_ID_DEMANDE)
            .frequence(UPDATED_FREQUENCE)
            .flagTypeDepDepannageUrgence(UPDATED_FLAG_TYPE_DEP_DEPANNAGE_URGENCE)
            .flagTypeDepDepannageRegulier(UPDATED_FLAG_TYPE_DEP_DEPANNAGE_REGULIER)
            .flagTypeDepPopoteRoulante(UPDATED_FLAG_TYPE_DEP_POPOTE_ROULANTE)
            .flagTypeDepMarcheAbordable(UPDATED_FLAG_TYPE_DEP_MARCHE_ABORDABLE)
            .flagTypeDepEntraideAlimentaire(UPDATED_FLAG_TYPE_DEP_ENTRAIDE_ALIMENTAIRE)
            .flagTypeDepFrigoPartage(UPDATED_FLAG_TYPE_DEP_FRIGO_PARTAGE)
            .flagTypeDepAutre(UPDATED_FLAG_TYPE_DEP_AUTRE)
            .besoinTechnique(UPDATED_BESOIN_TECHNIQUE)
            .besoinUrgent(UPDATED_BESOIN_URGENT)
            .flagCamionRegrigere(UPDATED_FLAG_CAMION_REGRIGERE)
            .flagPasCongelateur(UPDATED_FLAG_PAS_CONGELATEUR)
            .flagCongelateurInd(UPDATED_FLAG_CONGELATEUR_IND)
            .flagCongelateurPlainPied(UPDATED_FLAG_CONGELATEUR_PLAIN_PIED)
            .flagCongelateurResidentiel(UPDATED_FLAG_CONGELATEUR_RESIDENTIEL)
            .flagPasRefrigerateur(UPDATED_FLAG_PAS_REFRIGERATEUR)
            .flagRefrigerateurInd(UPDATED_FLAG_REFRIGERATEUR_IND)
            .flagRefrigerateurPlainPied(UPDATED_FLAG_REFRIGERATEUR_PLAIN_PIED)
            .flagRefrigerateurResidentiel(UPDATED_FLAG_REFRIGERATEUR_RESIDENTIEL)
            .flagAccesGlaciere(UPDATED_FLAG_ACCES_GLACIERE)
            .flagAccesCuisine(UPDATED_FLAG_ACCES_CUISINE)
            .flagAccesVehicule(UPDATED_FLAG_ACCES_VEHICULE)
            .dateInspection(UPDATED_DATE_INSPECTION)
            .nombreBeneficiaire(UPDATED_NOMBRE_BENEFICIAIRE)
            .prcAugmentationNbBenef(UPDATED_PRC_AUGMENTATION_NB_BENEF)
            .prcRHPerdue(UPDATED_PRC_RH_PERDUE)
            .prcBenevolePerdu(UPDATED_PRC_BENEVOLE_PERDU)
            .flagDenreeSuffisante(UPDATED_FLAG_DENREE_SUFFISANTE)
            .flagRHSuffisant(UPDATED_FLAG_RH_SUFFISANT)
            .flagVegetarien(UPDATED_FLAG_VEGETARIEN)
            .flagVegetalien(UPDATED_FLAG_VEGETALIEN)
            .flagItemSansEtiquette(UPDATED_FLAG_ITEM_SANS_ETIQUETTE)
            .flagItemDate(UPDATED_FLAG_ITEM_DATE)
            .flagHalal(UPDATED_FLAG_HALAL)
            .flagKasher(UPDATED_FLAG_KASHER)
            .boiteSuggestion(UPDATED_BOITE_SUGGESTION)
            .autreRessource(UPDATED_AUTRE_RESSOURCE);
        DemandeDTO demandeDTO = demandeMapper.toDto(updatedDemande);

        restDemandeMockMvc
            .perform(put("/api/demandes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeDTO)))
            .andExpect(status().isOk());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeUpdate);
        Demande testDemande = demandeList.get(demandeList.size() - 1);
        assertThat(testDemande.getIdDemande()).isEqualTo(UPDATED_ID_DEMANDE);
        assertThat(testDemande.getFrequence()).isEqualTo(UPDATED_FREQUENCE);
        assertThat(testDemande.isFlagTypeDepDepannageUrgence()).isEqualTo(UPDATED_FLAG_TYPE_DEP_DEPANNAGE_URGENCE);
        assertThat(testDemande.isFlagTypeDepDepannageRegulier()).isEqualTo(UPDATED_FLAG_TYPE_DEP_DEPANNAGE_REGULIER);
        assertThat(testDemande.isFlagTypeDepPopoteRoulante()).isEqualTo(UPDATED_FLAG_TYPE_DEP_POPOTE_ROULANTE);
        assertThat(testDemande.isFlagTypeDepMarcheAbordable()).isEqualTo(UPDATED_FLAG_TYPE_DEP_MARCHE_ABORDABLE);
        assertThat(testDemande.isFlagTypeDepEntraideAlimentaire()).isEqualTo(UPDATED_FLAG_TYPE_DEP_ENTRAIDE_ALIMENTAIRE);
        assertThat(testDemande.isFlagTypeDepFrigoPartage()).isEqualTo(UPDATED_FLAG_TYPE_DEP_FRIGO_PARTAGE);
        assertThat(testDemande.isFlagTypeDepAutre()).isEqualTo(UPDATED_FLAG_TYPE_DEP_AUTRE);
        assertThat(testDemande.getBesoinTechnique()).isEqualTo(UPDATED_BESOIN_TECHNIQUE);
        assertThat(testDemande.getBesoinUrgent()).isEqualTo(UPDATED_BESOIN_URGENT);
        assertThat(testDemande.isFlagCamionRegrigere()).isEqualTo(UPDATED_FLAG_CAMION_REGRIGERE);
        assertThat(testDemande.isFlagPasCongelateur()).isEqualTo(UPDATED_FLAG_PAS_CONGELATEUR);
        assertThat(testDemande.isFlagCongelateurInd()).isEqualTo(UPDATED_FLAG_CONGELATEUR_IND);
        assertThat(testDemande.isFlagCongelateurPlainPied()).isEqualTo(UPDATED_FLAG_CONGELATEUR_PLAIN_PIED);
        assertThat(testDemande.isFlagCongelateurResidentiel()).isEqualTo(UPDATED_FLAG_CONGELATEUR_RESIDENTIEL);
        assertThat(testDemande.isFlagPasRefrigerateur()).isEqualTo(UPDATED_FLAG_PAS_REFRIGERATEUR);
        assertThat(testDemande.isFlagRefrigerateurInd()).isEqualTo(UPDATED_FLAG_REFRIGERATEUR_IND);
        assertThat(testDemande.isFlagRefrigerateurPlainPied()).isEqualTo(UPDATED_FLAG_REFRIGERATEUR_PLAIN_PIED);
        assertThat(testDemande.isFlagRefrigerateurResidentiel()).isEqualTo(UPDATED_FLAG_REFRIGERATEUR_RESIDENTIEL);
        assertThat(testDemande.isFlagAccesGlaciere()).isEqualTo(UPDATED_FLAG_ACCES_GLACIERE);
        assertThat(testDemande.isFlagAccesCuisine()).isEqualTo(UPDATED_FLAG_ACCES_CUISINE);
        assertThat(testDemande.isFlagAccesVehicule()).isEqualTo(UPDATED_FLAG_ACCES_VEHICULE);
        assertThat(testDemande.getDateInspection()).isEqualTo(UPDATED_DATE_INSPECTION);
        assertThat(testDemande.getNombreBeneficiaire()).isEqualTo(UPDATED_NOMBRE_BENEFICIAIRE);
        assertThat(testDemande.getPrcAugmentationNbBenef()).isEqualTo(UPDATED_PRC_AUGMENTATION_NB_BENEF);
        assertThat(testDemande.getPrcRHPerdue()).isEqualTo(UPDATED_PRC_RH_PERDUE);
        assertThat(testDemande.getPrcBenevolePerdu()).isEqualTo(UPDATED_PRC_BENEVOLE_PERDU);
        assertThat(testDemande.isFlagDenreeSuffisante()).isEqualTo(UPDATED_FLAG_DENREE_SUFFISANTE);
        assertThat(testDemande.isFlagRHSuffisant()).isEqualTo(UPDATED_FLAG_RH_SUFFISANT);
        assertThat(testDemande.isFlagVegetarien()).isEqualTo(UPDATED_FLAG_VEGETARIEN);
        assertThat(testDemande.isFlagVegetalien()).isEqualTo(UPDATED_FLAG_VEGETALIEN);
        assertThat(testDemande.isFlagItemSansEtiquette()).isEqualTo(UPDATED_FLAG_ITEM_SANS_ETIQUETTE);
        assertThat(testDemande.isFlagItemDate()).isEqualTo(UPDATED_FLAG_ITEM_DATE);
        assertThat(testDemande.isFlagHalal()).isEqualTo(UPDATED_FLAG_HALAL);
        assertThat(testDemande.isFlagKasher()).isEqualTo(UPDATED_FLAG_KASHER);
        assertThat(testDemande.getBoiteSuggestion()).isEqualTo(UPDATED_BOITE_SUGGESTION);
        assertThat(testDemande.getAutreRessource()).isEqualTo(UPDATED_AUTRE_RESSOURCE);
    }

    @Test
    @Transactional
    public void updateNonExistingDemande() throws Exception {
        int databaseSizeBeforeUpdate = demandeRepository.findAll().size();

        // Create the Demande
        DemandeDTO demandeDTO = demandeMapper.toDto(demande);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeMockMvc
            .perform(put("/api/demandes").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        int databaseSizeBeforeDelete = demandeRepository.findAll().size();

        // Delete the demande
        restDemandeMockMvc
            .perform(delete("/api/demandes/{id}", demande.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
