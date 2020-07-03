import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TableAgricoleTestModule } from '../../../test.module';
import { EtatFrigoComponent } from 'app/entities/etat-frigo/etat-frigo.component';
import { EtatFrigoService } from 'app/entities/etat-frigo/etat-frigo.service';
import { EtatFrigo } from 'app/shared/model/etat-frigo.model';

describe('Component Tests', () => {
  describe('EtatFrigo Management Component', () => {
    let comp: EtatFrigoComponent;
    let fixture: ComponentFixture<EtatFrigoComponent>;
    let service: EtatFrigoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [EtatFrigoComponent],
      })
        .overrideTemplate(EtatFrigoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EtatFrigoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EtatFrigoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EtatFrigo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.etatFrigos && comp.etatFrigos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
