import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DemandeService } from 'app/entities/demande/demande.service';
import { IDemande, Demande } from 'app/shared/model/demande.model';
import { Frequence } from 'app/shared/model/enumerations/frequence.model';

describe('Service Tests', () => {
  describe('Demande Service', () => {
    let injector: TestBed;
    let service: DemandeService;
    let httpMock: HttpTestingController;
    let elemDefault: IDemande;
    let expectedResult: IDemande | IDemande[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DemandeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Demande(
        0,
        0,
        Frequence.QUOTIDIEN,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        currentDate,
        0,
        0,
        0,
        0,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateInspection: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Demande', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateInspection: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateInspection: currentDate,
          },
          returnedFromService
        );

        service.create(new Demande()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Demande', () => {
        const returnedFromService = Object.assign(
          {
            idDemande: 1,
            frequence: 'BBBBBB',
            flagTypeDepDepannageUrgence: true,
            flagTypeDepDepannageRegulier: true,
            flagTypeDepPopoteRoulante: true,
            flagTypeDepMarcheAbordable: true,
            flagTypeDepEntraideAlimentaire: true,
            flagTypeDepFrigoPartage: true,
            flagTypeDepAutre: true,
            besoinTechnique: 'BBBBBB',
            besoinUrgent: 'BBBBBB',
            flagCamionRegrigere: true,
            flagPasCongelateur: true,
            flagCongelateurInd: true,
            flagCongelateurPlainPied: true,
            flagCongelateurResidentiel: true,
            flagPasRefrigerateur: true,
            flagRefrigerateurInd: true,
            flagRefrigerateurPlainPied: true,
            flagRefrigerateurResidentiel: true,
            flagAccesGlaciere: true,
            flagAccesCuisine: true,
            flagAccesVehicule: true,
            dateInspection: currentDate.format(DATE_TIME_FORMAT),
            nombreBeneficiaire: 1,
            prcAugmentationNbBenef: 1,
            prcRHPerdue: 1,
            prcBenevolePerdu: 1,
            flagDenreeSuffisante: true,
            flagRHSuffisant: true,
            flagVegetarien: true,
            flagVegetalien: true,
            flagItemSansEtiquette: true,
            flagItemDate: true,
            flagHalal: true,
            flagKasher: true,
            boiteSuggestion: 'BBBBBB',
            autreRessource: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateInspection: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Demande', () => {
        const returnedFromService = Object.assign(
          {
            idDemande: 1,
            frequence: 'BBBBBB',
            flagTypeDepDepannageUrgence: true,
            flagTypeDepDepannageRegulier: true,
            flagTypeDepPopoteRoulante: true,
            flagTypeDepMarcheAbordable: true,
            flagTypeDepEntraideAlimentaire: true,
            flagTypeDepFrigoPartage: true,
            flagTypeDepAutre: true,
            besoinTechnique: 'BBBBBB',
            besoinUrgent: 'BBBBBB',
            flagCamionRegrigere: true,
            flagPasCongelateur: true,
            flagCongelateurInd: true,
            flagCongelateurPlainPied: true,
            flagCongelateurResidentiel: true,
            flagPasRefrigerateur: true,
            flagRefrigerateurInd: true,
            flagRefrigerateurPlainPied: true,
            flagRefrigerateurResidentiel: true,
            flagAccesGlaciere: true,
            flagAccesCuisine: true,
            flagAccesVehicule: true,
            dateInspection: currentDate.format(DATE_TIME_FORMAT),
            nombreBeneficiaire: 1,
            prcAugmentationNbBenef: 1,
            prcRHPerdue: 1,
            prcBenevolePerdu: 1,
            flagDenreeSuffisante: true,
            flagRHSuffisant: true,
            flagVegetarien: true,
            flagVegetalien: true,
            flagItemSansEtiquette: true,
            flagItemDate: true,
            flagHalal: true,
            flagKasher: true,
            boiteSuggestion: 'BBBBBB',
            autreRessource: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateInspection: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Demande', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
