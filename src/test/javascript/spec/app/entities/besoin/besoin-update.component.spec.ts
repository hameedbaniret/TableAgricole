import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { BesoinUpdateComponent } from 'app/entities/besoin/besoin-update.component';
import { BesoinService } from 'app/entities/besoin/besoin.service';
import { Besoin } from 'app/shared/model/besoin.model';

describe('Component Tests', () => {
  describe('Besoin Management Update Component', () => {
    let comp: BesoinUpdateComponent;
    let fixture: ComponentFixture<BesoinUpdateComponent>;
    let service: BesoinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [BesoinUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BesoinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BesoinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BesoinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Besoin(123);
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
        const entity = new Besoin();
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
