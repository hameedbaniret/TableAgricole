import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BesoinService } from 'app/entities/besoin/besoin.service';
import { IBesoin, Besoin } from 'app/shared/model/besoin.model';
import { BesoinFreq } from 'app/shared/model/enumerations/besoin-freq.model';
import { TypeBesoin } from 'app/shared/model/enumerations/type-besoin.model';

describe('Service Tests', () => {
  describe('Besoin Service', () => {
    let injector: TestBed;
    let service: BesoinService;
    let httpMock: HttpTestingController;
    let elemDefault: IBesoin;
    let expectedResult: IBesoin | IBesoin[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BesoinService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Besoin(
        0,
        0,
        BesoinFreq.PERIODIQUE,
        TypeBesoin.SURPLUSANNONCE,
        0,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        currentDate,
        0,
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            datePeremption: currentDate.format(DATE_TIME_FORMAT),
            dateBesoin: currentDate.format(DATE_TIME_FORMAT),
            dateEntree: currentDate.format(DATE_TIME_FORMAT),
            dateRecuperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Besoin', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            datePeremption: currentDate.format(DATE_TIME_FORMAT),
            dateBesoin: currentDate.format(DATE_TIME_FORMAT),
            dateEntree: currentDate.format(DATE_TIME_FORMAT),
            dateRecuperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datePeremption: currentDate,
            dateBesoin: currentDate,
            dateEntree: currentDate,
            dateRecuperation: currentDate,
          },
          returnedFromService
        );

        service.create(new Besoin()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Besoin', () => {
        const returnedFromService = Object.assign(
          {
            idBesoin: 1,
            besoinFrequence: 'BBBBBB',
            typeBesoin: 'BBBBBB',
            quantite: 1,
            datePeremption: currentDate.format(DATE_TIME_FORMAT),
            dateBesoin: currentDate.format(DATE_TIME_FORMAT),
            titreEmploi: 'BBBBBB',
            tachePrincipale: 'BBBBBB',
            nbHeure: 1,
            dureeContrat: 1,
            dateEntree: currentDate.format(DATE_TIME_FORMAT),
            nbBeneficiaire: 1,
            serviceSouhaite: 'BBBBBB',
            dateRecuperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datePeremption: currentDate,
            dateBesoin: currentDate,
            dateEntree: currentDate,
            dateRecuperation: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Besoin', () => {
        const returnedFromService = Object.assign(
          {
            idBesoin: 1,
            besoinFrequence: 'BBBBBB',
            typeBesoin: 'BBBBBB',
            quantite: 1,
            datePeremption: currentDate.format(DATE_TIME_FORMAT),
            dateBesoin: currentDate.format(DATE_TIME_FORMAT),
            titreEmploi: 'BBBBBB',
            tachePrincipale: 'BBBBBB',
            nbHeure: 1,
            dureeContrat: 1,
            dateEntree: currentDate.format(DATE_TIME_FORMAT),
            nbBeneficiaire: 1,
            serviceSouhaite: 'BBBBBB',
            dateRecuperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datePeremption: currentDate,
            dateBesoin: currentDate,
            dateEntree: currentDate,
            dateRecuperation: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Besoin', () => {
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
