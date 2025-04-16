import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IAutoPolicy } from '../interfaces/iauto-policy';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class AutoPolicyService {


  constructor(private http: HttpClient) {}

  getAutoQuote(): Observable<IAutoPolicy[]> {
    return this.http.get<IAutoPolicy[]>('URL GOES HERE');
  }

  // postAutoQuote(): Observable<any> {
  //   this.http.post<any>('URL GOES HERE');
  // }

  postAutoQuote(data: any): Observable<object> {

    let body = {
      "customerId": 1,
      "startDate": "2025-04-16",
      "endDate": "2026-04-16",
      "basePremium": 750,
      "premium": 0.0,
      "status": "PENDING",
      "vehicle": {
        "vehicleMake": data.vehicleMake,
        "vehicleModel": data.vehicleModel,
        "vehicleYear": data.vehicleYear,
        "vehicleAccidents": data.vehicleYear
      }
    }

    return this.http.post<IAutoPolicy>('http//:localhost:8080/v1/autopolicies', {data}); 
  }

  // postMockQuote(data: any): any {

  //   return {
  //     basePremium: 40,
  //     accidentFee: 30,
  //     discountAmount: 50,
  //     quoteAmount: 400
      
  //   }
  // }
  
}
