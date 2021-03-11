import { Component, OnInit } from '@angular/core';
import { ProductService, ProductInterface } from '../service/product.service';
import { NgbModal, NgbModalRef, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  _products: Array<ProductInterface> = [];
  _message: String = "";
  _product = {} as ProductInterface;
 
  modalReference!: NgbModalRef;
  modalOption: NgbModalOptions = {};
 
  constructor(private productService: ProductService,
    private modalService: NgbModal) { }
 
    deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe((msg: String) => this._message = msg);
    this._products.splice(this._products.findIndex(s => s.id === id), 1);
  }
 
  updateProduct(id: number) {
   this.productService.updateProduct(this._product).subscribe((product: ProductInterface) => this._product = product);
  }
 
  addProduct() {
   this.productService.createProduct(this._product).subscribe((product: ProductInterface) => {
      this._product = product;
      this._products.push(this._product);
    });
  }
 
  createUpdate() {
    if(this._product.id === null || this._product.id === 0){
      this.addProduct();
    } else {
      this.updateProduct(this._product.id);
    }
    this.modalReference.close();
  }
 
  ngOnInit(): void {
    this.productService.loadProducts().subscribe(productos => this._products = productos);
    this._message = "";
  }
 
  open(id : number, content: any) {
    this.modalOption.backdrop = 'static';
    this.modalOption.keyboard = false;
    this.modalReference = this.modalService.open(content, this.modalOption);
    if(id === 0 ) {
      this._product = {id : 0, productCode: "", productName: "",productPrice: 0};
    } else {
      this._product = this._products.find(s => s.id === id) || {} as ProductInterface;
    }
  }

}
