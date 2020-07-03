import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TableAgricoleTestModule } from '../../../test.module';
import { DistanceVehComponent } from 'app/entities/distance-veh/distance-veh.component';
import { DistanceVehService } from 'app/entities/distance-veh/distance-veh.service';
import { DistanceVeh } from 'app/shared/model/distance-veh.model';

describe('Component Tests', () => {
  describe('DistanceVeh Management Component', () => {
    let comp: DistanceVehComponent;
    let fixture: ComponentFixture<DistanceVehComponent>;
    let service: DistanceVehService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [DistanceVehComponent],
      })
        .overrideTemplate(DistanceVehComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DistanceVehComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DistanceVehService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DistanceVeh(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.distanceVehs && comp.distanceVehs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
