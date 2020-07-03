import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrganisme } from 'app/shared/model/organisme.model';

type EntityResponseType = HttpResponse<IOrganisme>;
type EntityArrayResponseType = HttpResponse<IOrganisme[]>;

@Injectable({ providedIn: 'root' })
export class OrganismeService {
  public resourceUrl = SERVER_API_URL + 'api/organismes';

  constructor(protected http: HttpClient) {}

  create(organisme: IOrganisme): Observable<EntityResponseType> {
    return this.http.post<IOrganisme>(this.resourceUrl, organisme, { observe: 'response' });
  }

  update(organisme: IOrganisme): Observable<EntityResponseType> {
    return this.http.put<IOrganisme>(this.resourceUrl, organisme, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrganisme>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrganisme[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
