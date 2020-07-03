import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TableAgricoleTestModule } from '../../../test.module';
import { DistanceVeloComponent } from 'app/entities/distance-velo/distance-velo.component';
import { DistanceVeloService } from 'app/entities/distance-velo/distance-velo.service';
import { DistanceVelo } from 'app/shared/model/distance-velo.model';

describe('Component Tests', () => {
  describe('DistanceVelo Management Component', () => {
    let comp: DistanceVeloComponent;
    let fixture: ComponentFixture<DistanceVeloComponent>;
    let service: DistanceVeloService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [DistanceVeloComponent],
      })
        .overrideTemplate(DistanceVeloComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DistanceVeloComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DistanceVeloService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DistanceVelo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.distanceVelos && comp.distanceVelos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
