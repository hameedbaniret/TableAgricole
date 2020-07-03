import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { TypeAlimentUpdateComponent } from 'app/entities/type-aliment/type-aliment-update.component';
import { TypeAlimentService } from 'app/entities/type-aliment/type-aliment.service';
import { TypeAliment } from 'app/shared/model/type-aliment.model';

describe('Component Tests', () => {
  describe('TypeAliment Management Update Component', () => {
    let comp: TypeAlimentUpdateComponent;
    let fixture: ComponentFixture<TypeAlimentUpdateComponent>;
    let service: TypeAlimentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [TypeAlimentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeAlimentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeAlimentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeAlimentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeAliment(123);
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
        const entity = new TypeAliment();
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
