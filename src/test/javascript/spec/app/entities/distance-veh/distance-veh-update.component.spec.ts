import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TableAgricoleTestModule } from '../../../test.module';
import { DistanceVehUpdateComponent } from 'app/entities/distance-veh/distance-veh-update.component';
import { DistanceVehService } from 'app/entities/distance-veh/distance-veh.service';
import { DistanceVeh } from 'app/shared/model/distance-veh.model';

describe('Component Tests', () => {
  describe('DistanceVeh Management Update Component', () => {
    let comp: DistanceVehUpdateComponent;
    let fixture: ComponentFixture<DistanceVehUpdateComponent>;
    let service: DistanceVehService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [DistanceVehUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DistanceVehUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DistanceVehUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DistanceVehService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DistanceVeh(123);
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
        const entity = new DistanceVeh();
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
