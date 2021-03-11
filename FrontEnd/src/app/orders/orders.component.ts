import { Component, OnInit } from '@angular/core';
import { OrdersService, OrdersInterface } from '../service/orders.service';
import { NgbModal, NgbModalRef, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  _orders: Array<OrdersInterface> = [];
  _message: String = "";
  _order = {} as OrdersInterface;
 
  modalReference!: NgbModalRef;
  modalOption: NgbModalOptions = {};
 
  constructor(private orderService: OrdersService,
    private modalService: NgbModal) { }
 
  deleteOrder(id: number) {
    this.orderService.deleteOrder(id).subscribe((msg: String) => this._message = msg);
    this._orders.splice(this._orders.findIndex(s => s.id === id), 1);
  }
 
  updateOrder(id: number) {
   this.orderService.updateOrder(this._order).subscribe((order: OrdersInterface) => this._order = order);
  }
 
  addOrder() {
   this.orderService.createOrder(this._order).subscribe((order: OrdersInterface) => {
      this._order = order;
      this._orders.push(this._order);
    });
  }
 
  createUpdate() {
    if(this._order.id === null || this._order.id === 0){
      this.addOrder();
    } else {
      this.updateOrder(this._order.id);
    }
    this.modalReference.close();
  }
 
  ngOnInit(): void {
    this.orderService.loadOrders().subscribe(orders => this._orders = orders);
    this._message = "";
  }
 
  open(id : number, content: any) {
    this.modalOption.backdrop = 'static';
    this.modalOption.keyboard = false;
    this.modalReference = this.modalService.open(content, this.modalOption);
    if(id === 0 ) {
      this._order = {id : 0, orderCode: "", orderDate: new Date(), clientId: 0, productId: 0};
    } else {
      this._order = this._orders.find(s => s.id === id) || {} as OrdersInterface;
    }
  }

}
