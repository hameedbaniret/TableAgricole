import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { EtatFrigoUpdateComponent } from 'app/entities/etat-frigo/etat-frigo-update.component';
import { EtatFrigoService } from 'app/entities/etat-frigo/etat-frigo.service';
import { EtatFrigo } from 'app/shared/model/etat-frigo.model';

describe('Component Tests', () => {
  describe('EtatFrigo Management Update Component', () => {
    let comp: EtatFrigoUpdateComponent;
    let fixture: ComponentFixture<EtatFrigoUpdateComponent>;
    let service: EtatFrigoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [EtatFrigoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EtatFrigoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EtatFrigoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EtatFrigoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EtatFrigo(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EtatFrigo();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
