import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TableAgricoleTestModule } from '../../../test.module';
import { TypeAlimentComponent } from 'app/entities/type-aliment/type-aliment.component';
import { TypeAlimentService } from 'app/entities/type-aliment/type-aliment.service';
import { TypeAliment } from 'app/shared/model/type-aliment.model';

describe('Component Tests', () => {
  describe('TypeAliment Management Component', () => {
    let comp: TypeAlimentComponent;
    let fixture: ComponentFixture<TypeAlimentComponent>;
    let service: TypeAlimentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [TypeAlimentComponent],
      })
        .overrideTemplate(TypeAlimentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeAlimentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeAlimentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TypeAliment(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.typeAliments && comp.typeAliments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
