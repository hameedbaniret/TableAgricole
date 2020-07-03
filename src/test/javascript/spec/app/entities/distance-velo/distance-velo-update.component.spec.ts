import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { DistanceVeloUpdateComponent } from 'app/entities/distance-velo/distance-velo-update.component';
import { DistanceVeloService } from 'app/entities/distance-velo/distance-velo.service';
import { DistanceVelo } from 'app/shared/model/distance-velo.model';

describe('Component Tests', () => {
  describe('DistanceVelo Management Update Component', () => {
    let comp: DistanceVeloUpdateComponent;
    let fixture: ComponentFixture<DistanceVeloUpdateComponent>;
    let service: DistanceVeloService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [DistanceVeloUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DistanceVeloUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DistanceVeloUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DistanceVeloService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DistanceVelo(123);
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
        const entity = new DistanceVelo();
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
