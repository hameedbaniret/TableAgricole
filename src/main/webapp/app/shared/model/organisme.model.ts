import { IDemande } from 'app/shared/model/demande.model';

export interface IOrganisme {
  id?: number;
  idOrganisme?: number;
  nomOrganisme?: string;
  adresseOrganisme?: string;
  phoneOrganisme?: string;
  courrielOrganisme?: string;
  demandes?: IDemande[];
  territoireId?: number;
}

export class Organisme implements IOrganisme {
  constructor(
    public id?: number,
    public idOrganisme?: number,
    public nomOrganisme?: string,
    public adresseOrganisme?: string,
    public phoneOrganisme?: string,
    public courrielOrganisme?: string,
    public demandes?: IDemande[],
    public territoireId?: number
  ) {}
}
