import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../models/order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  // On pointe vers la Gateway (8080)
  private apiUrl = 'http://localhost:8080/orders';

  constructor(private http: HttpClient) { }

  getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl);
  }

  createOrder(order: any): Observable<Order> {
    return this.http.post<Order>(this.apiUrl, order);
  }
}
