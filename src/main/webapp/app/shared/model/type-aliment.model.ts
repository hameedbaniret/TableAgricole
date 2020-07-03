import { IAliment } from 'app/shared/model/aliment.model';
import { IBesoin } from 'app/shared/model/besoin.model';

export interface ITypeAliment {
  id?: number;
  idTypeAliment?: number;
  dscTypeAliment?: string;
  aliments?: IAliment[];
  besoins?: IBesoin[];
}

export class TypeAliment implements ITypeAliment {
  constructor(
    public id?: number,
    public idTypeAliment?: number,
    public dscTypeAliment?: string,
    public aliments?: IAliment[],
    public besoins?: IBesoin[]
  ) {}
}
