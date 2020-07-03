import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeAliment } from 'app/shared/model/type-aliment.model';

@Component({
  selector: 'jhi-type-aliment-detail',
  templateUrl: './type-aliment-detail.component.html',
})
export class TypeAlimentDetailComponent implements OnInit {
  typeAliment: ITypeAliment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeAliment }) => (this.typeAliment = typeAliment));
  }

  previousState(): void {
    window.history.back();
  }
}
