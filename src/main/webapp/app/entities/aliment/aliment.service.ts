import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAliment } from 'app/shared/model/aliment.model';

type EntityResponseType = HttpResponse<IAliment>;
type EntityArrayResponseType = HttpResponse<IAliment[]>;

@Injectable({ providedIn: 'root' })
export class AlimentService {
  public resourceUrl = SERVER_API_URL + 'api/aliments';

  constructor(protected http: HttpClient) {}

  create(aliment: IAliment): Observable<EntityResponseType> {
    return this.http.post<IAliment>(this.resourceUrl, aliment, { observe: 'response' });
  }

  update(aliment: IAliment): Observable<EntityResponseType> {
    return this.http.put<IAliment>(this.resourceUrl, aliment, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAliment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAliment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
