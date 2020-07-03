import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBesoin } from 'app/shared/model/besoin.model';

type EntityResponseType = HttpResponse<IBesoin>;
type EntityArrayResponseType = HttpResponse<IBesoin[]>;

@Injectable({ providedIn: 'root' })
export class BesoinService {
  public resourceUrl = SERVER_API_URL + 'api/besoins';

  constructor(protected http: HttpClient) {}

  create(besoin: IBesoin): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(besoin);
    return this.http
      .post<IBesoin>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(besoin: IBesoin): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(besoin);
    return this.http
      .put<IBesoin>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBesoin>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBesoin[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(besoin: IBesoin): IBesoin {
    const copy: IBesoin = Object.assign({}, besoin, {
      datePeremption: besoin.datePeremption && besoin.datePeremption.isValid() ? besoin.datePeremption.toJSON() : undefined,
      dateBesoin: besoin.dateBesoin && besoin.dateBesoin.isValid() ? besoin.dateBesoin.toJSON() : undefined,
      dateEntree: besoin.dateEntree && besoin.dateEntree.isValid() ? besoin.dateEntree.toJSON() : undefined,
      dateRecuperation: besoin.dateRecuperation && besoin.dateRecuperation.isValid() ? besoin.dateRecuperation.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datePeremption = res.body.datePeremption ? moment(res.body.datePeremption) : undefined;
      res.body.dateBesoin = res.body.dateBesoin ? moment(res.body.dateBesoin) : undefined;
      res.body.dateEntree = res.body.dateEntree ? moment(res.body.dateEntree) : undefined;
      res.body.dateRecuperation = res.body.dateRecuperation ? moment(res.body.dateRecuperation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((besoin: IBesoin) => {
        besoin.datePeremption = besoin.datePeremption ? moment(besoin.datePeremption) : undefined;
        besoin.dateBesoin = besoin.dateBesoin ? moment(besoin.dateBesoin) : undefined;
        besoin.dateEntree = besoin.dateEntree ? moment(besoin.dateEntree) : undefined;
        besoin.dateRecuperation = besoin.dateRecuperation ? moment(besoin.dateRecuperation) : undefined;
      });
    }
    return res;
  }
}
