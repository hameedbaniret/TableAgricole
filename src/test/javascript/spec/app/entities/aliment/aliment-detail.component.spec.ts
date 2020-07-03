import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { AlimentDetailComponent } from 'app/entities/aliment/aliment-detail.component';
import { Aliment } from 'app/shared/model/aliment.model';

describe('Component Tests', () => {
  describe('Aliment Management Detail Component', () => {
    let comp: AlimentDetailComponent;
    let fixture: ComponentFixture<AlimentDetailComponent>;
    const route = ({ data: of({ aliment: new Aliment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [AlimentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AlimentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AlimentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load aliment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.aliment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
