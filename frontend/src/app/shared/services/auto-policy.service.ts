import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
export interface AutoPolicy {

  customerId: number;
  startDate: string;
  endDate: string;
  basePremium: number;
  status: string;
  vehicle: {
    vehicleMake: string;
    vehicleModel: string;
    vehicleYear: number;
    vehicleAccidents: number;
  };
}

@Injectable({
  providedIn: 'root'
})
export class AutoPolicyService {
  private apiUrl = 'http://localhost:8080/v1/autopolicies';

  constructor(private http: HttpClient) {}

  getAutoPolicies() {
    return this.http.get(this.apiUrl)
  }

  postAutoQuote(autoData: any): Observable<AutoPolicy> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    let body = {
      "customerId": 1,
      "startDate": "2025-05-01T00:00:00",
      "endDate": "2026-05-01T00:00:00",
      "basePremium": 100.0,
      "status": "ACTIVE",
      "vehicle": {
        "vehicleMake": autoData.vehicleMake,
        "vehicleModel": autoData.vehicleModel,
        "vehicleYear": autoData.vehicleYear,
        "vehicleAccidents": autoData.vehicleAccidents
      }}
    return this.http.post<AutoPolicy>(this.apiUrl, body, { headers });
  }
}
