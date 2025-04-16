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

  postAutoQuote(data: IAutoPolicy): Observable<object> {
    return this.http.post<IAutoPolicy>('URL GOES HERE', {data}); 
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
