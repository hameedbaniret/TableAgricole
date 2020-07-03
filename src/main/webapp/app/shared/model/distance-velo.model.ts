import { IDemande } from 'app/shared/model/demande.model';

export interface IDistanceVelo {
  id?: number;
  idDistanceVelo?: number;
  dscDistanceVelo?: string;
  demandes?: IDemande[];
}

export class DistanceVelo implements IDistanceVelo {
  constructor(public id?: number, public idDistanceVelo?: number, public dscDistanceVelo?: string, public demandes?: IDemande[]) {}
}
