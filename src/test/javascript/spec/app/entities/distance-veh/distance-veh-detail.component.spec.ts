import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { DistanceVehDetailComponent } from 'app/entities/distance-veh/distance-veh-detail.component';
import { DistanceVeh } from 'app/shared/model/distance-veh.model';

describe('Component Tests', () => {
  describe('DistanceVeh Management Detail Component', () => {
    let comp: DistanceVehDetailComponent;
    let fixture: ComponentFixture<DistanceVehDetailComponent>;
    const route = ({ data: of({ distanceVeh: new DistanceVeh(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [DistanceVehDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DistanceVehDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DistanceVehDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load distanceVeh on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.distanceVeh).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
