import { IDemande } from 'app/shared/model/demande.model';

export interface IEtatFrigo {
  id?: number;
  idEtatFrigo?: number;
  dscEtatFrigo?: string;
  demandes?: IDemande[];
}

export class EtatFrigo implements IEtatFrigo {
  constructor(public id?: number, public idEtatFrigo?: number, public dscEtatFrigo?: string, public demandes?: IDemande[]) {}
}
