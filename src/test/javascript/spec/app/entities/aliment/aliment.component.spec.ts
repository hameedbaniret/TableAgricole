import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TableAgricoleTestModule } from '../../../test.module';
import { AlimentComponent } from 'app/entities/aliment/aliment.component';
import { AlimentService } from 'app/entities/aliment/aliment.service';
import { Aliment } from 'app/shared/model/aliment.model';

describe('Component Tests', () => {
  describe('Aliment Management Component', () => {
    let comp: AlimentComponent;
    let fixture: ComponentFixture<AlimentComponent>;
    let service: AlimentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [AlimentComponent],
      })
        .overrideTemplate(AlimentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AlimentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlimentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Aliment(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.aliments && comp.aliments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
