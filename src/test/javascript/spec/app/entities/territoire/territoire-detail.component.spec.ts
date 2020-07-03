import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { TerritoireDetailComponent } from 'app/entities/territoire/territoire-detail.component';
import { Territoire } from 'app/shared/model/territoire.model';

describe('Component Tests', () => {
  describe('Territoire Management Detail Component', () => {
    let comp: TerritoireDetailComponent;
    let fixture: ComponentFixture<TerritoireDetailComponent>;
    const route = ({ data: of({ territoire: new Territoire(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [TerritoireDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TerritoireDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TerritoireDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load territoire on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.territoire).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
