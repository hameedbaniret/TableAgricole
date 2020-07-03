import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { TerritoireUpdateComponent } from 'app/entities/territoire/territoire-update.component';
import { TerritoireService } from 'app/entities/territoire/territoire.service';
import { Territoire } from 'app/shared/model/territoire.model';

describe('Component Tests', () => {
  describe('Territoire Management Update Component', () => {
    let comp: TerritoireUpdateComponent;
    let fixture: ComponentFixture<TerritoireUpdateComponent>;
    let service: TerritoireService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [TerritoireUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TerritoireUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TerritoireUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TerritoireService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Territoire(123);
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
        const entity = new Territoire();
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
