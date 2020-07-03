import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { BesoinDetailComponent } from 'app/entities/besoin/besoin-detail.component';
import { Besoin } from 'app/shared/model/besoin.model';

describe('Component Tests', () => {
  describe('Besoin Management Detail Component', () => {
    let comp: BesoinDetailComponent;
    let fixture: ComponentFixture<BesoinDetailComponent>;
    const route = ({ data: of({ besoin: new Besoin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [BesoinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BesoinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BesoinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load besoin on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.besoin).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
