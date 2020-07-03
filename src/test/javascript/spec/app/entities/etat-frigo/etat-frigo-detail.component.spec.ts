import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { EtatFrigoDetailComponent } from 'app/entities/etat-frigo/etat-frigo-detail.component';
import { EtatFrigo } from 'app/shared/model/etat-frigo.model';

describe('Component Tests', () => {
  describe('EtatFrigo Management Detail Component', () => {
    let comp: EtatFrigoDetailComponent;
    let fixture: ComponentFixture<EtatFrigoDetailComponent>;
    const route = ({ data: of({ etatFrigo: new EtatFrigo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [EtatFrigoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EtatFrigoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EtatFrigoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load etatFrigo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.etatFrigo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
