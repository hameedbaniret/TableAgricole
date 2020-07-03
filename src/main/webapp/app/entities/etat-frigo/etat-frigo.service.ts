import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEtatFrigo } from 'app/shared/model/etat-frigo.model';

type EntityResponseType = HttpResponse<IEtatFrigo>;
type EntityArrayResponseType = HttpResponse<IEtatFrigo[]>;

@Injectable({ providedIn: 'root' })
export class EtatFrigoService {
  public resourceUrl = SERVER_API_URL + 'api/etat-frigos';

  constructor(protected http: HttpClient) {}

  create(etatFrigo: IEtatFrigo): Observable<EntityResponseType> {
    return this.http.post<IEtatFrigo>(this.resourceUrl, etatFrigo, { observe: 'response' });
  }

  update(etatFrigo: IEtatFrigo): Observable<EntityResponseType> {
    return this.http.put<IEtatFrigo>(this.resourceUrl, etatFrigo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEtatFrigo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEtatFrigo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
