import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDistanceVelo } from 'app/shared/model/distance-velo.model';

type EntityResponseType = HttpResponse<IDistanceVelo>;
type EntityArrayResponseType = HttpResponse<IDistanceVelo[]>;

@Injectable({ providedIn: 'root' })
export class DistanceVeloService {
  public resourceUrl = SERVER_API_URL + 'api/distance-velos';

  constructor(protected http: HttpClient) {}

  create(distanceVelo: IDistanceVelo): Observable<EntityResponseType> {
    return this.http.post<IDistanceVelo>(this.resourceUrl, distanceVelo, { observe: 'response' });
  }

  update(distanceVelo: IDistanceVelo): Observable<EntityResponseType> {
    return this.http.put<IDistanceVelo>(this.resourceUrl, distanceVelo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDistanceVelo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDistanceVelo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
