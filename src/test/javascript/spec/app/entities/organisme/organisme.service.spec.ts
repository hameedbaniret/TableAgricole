import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { OrganismeService } from 'app/entities/organisme/organisme.service';
import { IOrganisme, Organisme } from 'app/shared/model/organisme.model';

describe('Service Tests', () => {
  describe('Organisme Service', () => {
    let injector: TestBed;
    let service: OrganismeService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrganisme;
    let expectedResult: IOrganisme | IOrganisme[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrganismeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Organisme(0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Organisme', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Organisme()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Organisme', () => {
        const returnedFromService = Object.assign(
          {
            idOrganisme: 1,
            nomOrganisme: 'BBBBBB',
            adresseOrganisme: 'BBBBBB',
            phoneOrganisme: 'BBBBBB',
            courrielOrganisme: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Organisme', () => {
        const returnedFromService = Object.assign(
          {
            idOrganisme: 1,
            nomOrganisme: 'BBBBBB',
            adresseOrganisme: 'BBBBBB',
            phoneOrganisme: 'BBBBBB',
            courrielOrganisme: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Organisme', () => {
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
