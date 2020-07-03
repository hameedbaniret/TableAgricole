import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { TypeAlimentDetailComponent } from 'app/entities/type-aliment/type-aliment-detail.component';
import { TypeAliment } from 'app/shared/model/type-aliment.model';

describe('Component Tests', () => {
  describe('TypeAliment Management Detail Component', () => {
    let comp: TypeAlimentDetailComponent;
    let fixture: ComponentFixture<TypeAlimentDetailComponent>;
    const route = ({ data: of({ typeAliment: new TypeAliment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [TypeAlimentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeAlimentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeAlimentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeAliment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeAliment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
