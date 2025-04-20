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
export class RegisterService {
  private apiUrl = 'http://localhost:8080/v1/customers';

  constructor(private http: HttpClient) {}

  getCustomer() {
    return this.http.get(this.apiUrl)
  }

  getCustomerIdByEmail(data: any) {
    return this.http.get<any>(`http://localhost:8080/v1/customers/id-by-email/${data.email}`);
  }

  postCustomer(userData: any): Observable<object> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

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
      "firstName": userData.firstName,
      "lastName": userData.lastName,
      "email": userData.email,
      "phone": userData.phone,
      "address": userData.address,
      "dateOfBirth": "1993-09-07",
      "createdAt": null
    };
    this.postUser(userData)
    return this.http.post<any>(`http://localhost:8080/v1/customers`, body, { headers });
  }

  postUser(userData: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    let body = {

      "email": userData.email,
      "username": userData.username,
      "passwordHash": userData.passwordHash,
      "role": userData.role
    };
    return this.http.post<any>(`http://localhost:8080/v1/users`, body, { headers });
  }





}
