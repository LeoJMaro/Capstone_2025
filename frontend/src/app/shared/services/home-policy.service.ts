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

  getHomePolicyById(id: any) {
    return this.http.get<any>(`${this.apiUrl}/customers/${id}`);
  }

  postHomeQuote(homeData: any): Observable<IHomePolicy> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    // Dynamically calculate dates
    const currentDate = new Date();
    const nextYearDate = new Date();
    nextYearDate.setFullYear(currentDate.getFullYear() + 1);

    const formatDate = (date: Date): string => {
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      return `${year}-${month}-${day}`;
    };

    let emailResponse: any = localStorage.getItem('emailResponse');
    let parsedEmailResponse = JSON.parse(emailResponse);
    console.log("PARSED EMAIL RESP:",parsedEmailResponse)

    let body = {
      "customerId": parsedEmailResponse,
      "startDate": formatDate(currentDate),
      "endDate": formatDate(nextYearDate),
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
    };
    return this.http.post<any>(this.apiUrl, body, { headers });
  }

  renewHomePolicy(homeData: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    // Dynamically calculate dates
    const currentDate = new Date();
    const nextYearDate = new Date();
    nextYearDate.setFullYear(currentDate.getFullYear() + 1);

    const formatDate = (date: Date): string => {
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      return `${year}-${month}-${day}`;
    };

    let emailResponse: any = localStorage.getItem('emailResponse');
    let parsedEmailResponse = JSON.parse(emailResponse);
    console.log("PARSED EMAIL RESP:",parsedEmailResponse)

    let body = {
      "customerId": parsedEmailResponse,
      "startDate": formatDate(currentDate),
      "endDate": formatDate(nextYearDate),
      "basePremium": 500,
      "premium": 0.0,
      "status": "ACTIVE",
      "dwelling": {
        "dwellingType": homeData.dwelling.dwellingType,
        "heatingType": homeData.dwelling.heatingType,
        "location": homeData.dwelling.location,
        "age": homeData.dwelling.age,
        "homeValue": homeData.dwelling.homeValue
      }
    };
    return this.http.put<any>(`http://localhost:8080/v1/homepolicies/${homeData.policyId}`, body, { headers });
  }

}
