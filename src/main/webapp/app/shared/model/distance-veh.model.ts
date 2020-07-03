import { IDemande } from 'app/shared/model/demande.model';

export interface IDistanceVeh {
  id?: number;
  idDistanceVeh?: number;
  dscDistanceVeh?: string;
  demandes?: IDemande[];
}

export class DistanceVeh implements IDistanceVeh {
  constructor(public id?: number, public idDistanceVeh?: number, public dscDistanceVeh?: string, public demandes?: IDemande[]) {}
}
