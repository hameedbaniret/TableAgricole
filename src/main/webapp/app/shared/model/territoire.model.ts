import { IOrganisme } from 'app/shared/model/organisme.model';

export interface ITerritoire {
  id?: number;
  idTerritoire?: number;
  dscTerritoire?: string;
  organismes?: IOrganisme[];
}

export class Territoire implements ITerritoire {
  constructor(public id?: number, public idTerritoire?: number, public dscTerritoire?: string, public organismes?: IOrganisme[]) {}
}
