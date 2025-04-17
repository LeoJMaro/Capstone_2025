import { Injectable } from '@angular/core';
import { HttpClient,  HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  // Update the baseUrl to match your backend's admin endpoints.
  private baseUrl = 'http://localhost:8080/v1/admin';

  constructor(private http: HttpClient) {}

  // Retrieves all users.
  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/users`);
  }

  // Retrieves the total number of users.
  getTotalUsers(): Observable<number> {
    // You must have a corresponding backend endpoint that returns a number.
    return this.http.get<number>(`${this.baseUrl}/users/count`);
  }

  // Deletes a user by id.
  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/users/${id}`);
  }
}
