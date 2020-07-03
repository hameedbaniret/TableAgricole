import { Moment } from 'moment';
import { BesoinFreq } from 'app/shared/model/enumerations/besoin-freq.model';
import { TypeBesoin } from 'app/shared/model/enumerations/type-besoin.model';

export interface IBesoin {
  id?: number;
  idBesoin?: number;
  besoinFrequence?: BesoinFreq;
  typeBesoin?: TypeBesoin;
  quantite?: number;
  datePeremption?: Moment;
  dateBesoin?: Moment;
  titreEmploi?: string;
  tachePrincipale?: string;
  nbHeure?: number;
  dureeContrat?: number;
  dateEntree?: Moment;
  nbBeneficiaire?: number;
  serviceSouhaite?: string;
  dateRecuperation?: Moment;
  typeAlimentId?: number;
  alimentId?: number;
  demandeId?: number;
}

export class Besoin implements IBesoin {
  constructor(
    public id?: number,
    public idBesoin?: number,
    public besoinFrequence?: BesoinFreq,
    public typeBesoin?: TypeBesoin,
    public quantite?: number,
    public datePeremption?: Moment,
    public dateBesoin?: Moment,
    public titreEmploi?: string,
    public tachePrincipale?: string,
    public nbHeure?: number,
    public dureeContrat?: number,
    public dateEntree?: Moment,
    public nbBeneficiaire?: number,
    public serviceSouhaite?: string,
    public dateRecuperation?: Moment,
    public typeAlimentId?: number,
    public alimentId?: number,
    public demandeId?: number
  ) {}
}
