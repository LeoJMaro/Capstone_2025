import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { IHomePolicy } from '../interfaces/ihome-policy';

@Injectable({
  providedIn: 'root'
})
export class HomePolicyService {

  constructor(private http: HttpClient) {}

  getHomeQuote(): Observable<IHomePolicy[]> {
    return this.http.get<IHomePolicy[]>('URL GOES HERE');
  }


  postHomeQuote(data: object): Observable<object> {
    return this.http.post<IHomePolicy>('URL GOES HERE', {data}); 
  }

  // postMockQuote(data: any): any {

  //   return {
  //       basePremium: 40000,
  //       ageFee: 30,
  //       heatingFee: 44,
  //       locationFee: 589,
  //       additional: 23,
  //       discountAmount: 50,
  //       quoteAmount: 900
      
  //   }
  // }
  
}
