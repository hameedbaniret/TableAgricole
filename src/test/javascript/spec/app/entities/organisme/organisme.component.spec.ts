import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TableAgricoleTestModule } from '../../../test.module';
import { OrganismeComponent } from 'app/entities/organisme/organisme.component';
import { OrganismeService } from 'app/entities/organisme/organisme.service';
import { Organisme } from 'app/shared/model/organisme.model';

describe('Component Tests', () => {
  describe('Organisme Management Component', () => {
    let comp: OrganismeComponent;
    let fixture: ComponentFixture<OrganismeComponent>;
    let service: OrganismeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TableAgricoleTestModule],
        declarations: [OrganismeComponent],
      })
        .overrideTemplate(OrganismeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrganismeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganismeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Organisme(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.organismes && comp.organismes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
