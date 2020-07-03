import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeAliment } from 'app/shared/model/type-aliment.model';

type EntityResponseType = HttpResponse<ITypeAliment>;
type EntityArrayResponseType = HttpResponse<ITypeAliment[]>;

@Injectable({ providedIn: 'root' })
export class TypeAlimentService {
  public resourceUrl = SERVER_API_URL + 'api/type-aliments';

  constructor(protected http: HttpClient) {}

  create(typeAliment: ITypeAliment): Observable<EntityResponseType> {
    return this.http.post<ITypeAliment>(this.resourceUrl, typeAliment, { observe: 'response' });
  }

  update(typeAliment: ITypeAliment): Observable<EntityResponseType> {
    return this.http.put<ITypeAliment>(this.resourceUrl, typeAliment, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeAliment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeAliment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
