import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import {IHomePolicy} from '../interfaces/ihome-policy';
export interface AutoPolicy {

  customerId: number;
  startDate: Date;
  endDate: Date;
  basePremium: number;
  premium: number;
  status: string;
  dwelling: {
    dwellingType: string;
    heatingType: string;
    location: string;
    age: number;
    homeValue: number;
  }
}

@Injectable({
  providedIn: 'root'
})
export class HomePolicyService {
  private apiUrl = 'http://localhost:8080/v1/homepolicies';

  constructor(private http: HttpClient) {}

  getHomePolicies() {
    return this.http.get(this.apiUrl)
  }

  // getHomePolicyById(id: number) {
  //   const params = new HttpParams().set('id', id.toString());
  //   return this.http.get(this.apiUrl, { params });
  // }

  getHomePolicyById(id: number) {
    return this.http.get<any>(`${this.apiUrl}/customers/${id}`);
  }





  postHomeQuote(homeData: any): Observable<IHomePolicy> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    let body = {
      "customerId": 1,
      "startDate": "2025-04-16",
      "endDate": "2026-04-16",
      "basePremium": 500,
      "premium": 0.0,
      "status": "ACTIVE",
      "dwelling": {
        "dwellingType": homeData.dwellingType,
        "heatingType": homeData.heatingType,
        "location": homeData.location,
        "age": homeData.age,
        "homeValue": homeData.homeValue
    }
    }
    return this.http.post<any>(this.apiUrl, body, { headers });
  }
}
