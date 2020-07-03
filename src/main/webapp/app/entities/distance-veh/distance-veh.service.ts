import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDistanceVeh } from 'app/shared/model/distance-veh.model';

type EntityResponseType = HttpResponse<IDistanceVeh>;
type EntityArrayResponseType = HttpResponse<IDistanceVeh[]>;

@Injectable({ providedIn: 'root' })
export class DistanceVehService {
  public resourceUrl = SERVER_API_URL + 'api/distance-vehs';

  constructor(protected http: HttpClient) {}

  create(distanceVeh: IDistanceVeh): Observable<EntityResponseType> {
    return this.http.post<IDistanceVeh>(this.resourceUrl, distanceVeh, { observe: 'response' });
  }

  update(distanceVeh: IDistanceVeh): Observable<EntityResponseType> {
    return this.http.put<IDistanceVeh>(this.resourceUrl, distanceVeh, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDistanceVeh>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDistanceVeh[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
