import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { DistanceVeloDetailComponent } from 'app/entities/distance-velo/distance-velo-detail.component';
import { DistanceVelo } from 'app/shared/model/distance-velo.model';

describe('Component Tests', () => {
  describe('DistanceVelo Management Detail Component', () => {
    let comp: DistanceVeloDetailComponent;
    let fixture: ComponentFixture<DistanceVeloDetailComponent>;
    const route = ({ data: of({ distanceVelo: new DistanceVelo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [DistanceVeloDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DistanceVeloDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DistanceVeloDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load distanceVelo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.distanceVelo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
