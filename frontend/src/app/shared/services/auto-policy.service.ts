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

  getAutoPolicyById(id: number) {
    return this.http.get<any>(`${this.apiUrl}/customers/${id}`);
  }

  getAutoPolicies() {
    return this.http.get(this.apiUrl)
  }

  postAutoQuote(autoData: any): Observable<AutoPolicy> {
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

    let body = {
      "customerId": 1,
      "startDate": formatDate(currentDate),
      "endDate": formatDate(nextYearDate),
      "basePremium": 750.00,
      "status": "ACTIVE",
      "vehicle": {
        "vehicleMake": autoData.vehicleMake,
        "vehicleModel": autoData.vehicleModel,
        "vehicleYear": autoData.vehicleYear,
        "vehicleAccidents": autoData.vehicleAccidents
      }}
    return this.http.post<AutoPolicy>(this.apiUrl, body, { headers });
  }

  renewAutoPolicy(autoData: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    console.log("aahh:", autoData)

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

    // Ensure `policyId` is included in the body
    const body = {
      customerId: autoData.customerId,
      startDate: formatDate(currentDate),
      endDate: formatDate(nextYearDate),
      basePremium: autoData.basePremium || 750.00, // Use a default if not provided
      status: autoData.status || "ACTIVE",
      vehicle: {
        vehicleMake: autoData.vehicle.vehicleMake,
        vehicleModel: autoData.vehicle.vehicleModel,
        vehicleYear: autoData.vehicle.vehicleYear,
        vehicleAccidents: autoData.vehicle.vehicleAccidents
      }
    };

    // Send the `PUT` request with the full payload
    return this.http.put<any>(`http://localhost:8080/v1/autopolicies/${autoData.policyId}`, body, { headers });
  }

}
