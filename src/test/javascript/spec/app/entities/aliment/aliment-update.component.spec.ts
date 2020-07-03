import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { AlimentUpdateComponent } from 'app/entities/aliment/aliment-update.component';
import { AlimentService } from 'app/entities/aliment/aliment.service';
import { Aliment } from 'app/shared/model/aliment.model';

describe('Component Tests', () => {
  describe('Aliment Management Update Component', () => {
    let comp: AlimentUpdateComponent;
    let fixture: ComponentFixture<AlimentUpdateComponent>;
    let service: AlimentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [AlimentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AlimentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AlimentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AlimentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Aliment(123);
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
        const entity = new Aliment();
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
