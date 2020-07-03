import { IBesoin } from 'app/shared/model/besoin.model';

export interface IAliment {
  id?: number;
  idAliment?: number;
  dscAliment?: string;
  besoins?: IBesoin[];
  typeAlimentId?: number;
}

export class Aliment implements IAliment {
  constructor(
    public id?: number,
    public idAliment?: number,
    public dscAliment?: string,
    public besoins?: IBesoin[],
    public typeAlimentId?: number
  ) {}
}
