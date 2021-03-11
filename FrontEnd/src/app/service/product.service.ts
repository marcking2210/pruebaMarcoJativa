import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

let product_service = "http://localhost:8080/api/product/";
 
export interface ProductInterface {
  id : number;
  productCode : String;
  productName : String;
  productPrice : number;
}
 
@Injectable({
  providedIn: 'root'
})

export class ProductService {

  constructor(private http : HttpClient) { }
 
  loadProducts() {
      return this.http.get<Array<ProductInterface>>(product_service + 'all');
  }
 
  createProduct(product: ProductInterface) {
    return this.http.post<ProductInterface>(product_service + 'create', product);
  }
 
  updateProduct(product: ProductInterface) {
    return this.http.put<ProductInterface>(product_service + 'update/', product);
  }
 
  deleteProduct(id:number) {
    return this.http.delete<String>(product_service + 'delete/' + id, { responseType: 'text' as 'json'}
    );
  }
}
