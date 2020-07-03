import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TableAgricoleTestModule } from '../../../test.module';
import { TerritoireComponent } from 'app/entities/territoire/territoire.component';
import { TerritoireService } from 'app/entities/territoire/territoire.service';
import { Territoire } from 'app/shared/model/territoire.model';

describe('Component Tests', () => {
  describe('Territoire Management Component', () => {
    let comp: TerritoireComponent;
    let fixture: ComponentFixture<TerritoireComponent>;
    let service: TerritoireService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [TerritoireComponent],
      })
        .overrideTemplate(TerritoireComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TerritoireComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TerritoireService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Territoire(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.territoires && comp.territoires[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
