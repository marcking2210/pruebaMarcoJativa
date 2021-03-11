import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

let orders_service = "http://localhost:8080/api/orders/";
 
export interface OrdersInterface {
  id : number;
  orderCode : String;
  orderDate : Date;
  clientId : number;
  productId: number;
}

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http : HttpClient) { }
 
  loadOrders() {
      return this.http.get<Array<OrdersInterface>>(orders_service + 'all');
  }
 
  createOrder(order: OrdersInterface) {
    return this.http.post<OrdersInterface>(orders_service + 'create', order);
  }
 
  updateOrder(order: OrdersInterface) {
    return this.http.put<OrdersInterface>(orders_service + 'update/', order);
  }
 
  deleteOrder(id:number) {
    return this.http.delete<String>(orders_service + 'delete/' + id, { responseType: 'text' as 'json'}
    );
  }
}
