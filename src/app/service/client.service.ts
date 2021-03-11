import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

let client_service = "http://localhost:8080/api/client/";
 
export interface ClientInterface {
  id : number;
  clientName : String;
  clientLastName : String;
}
 
@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http : HttpClient) { }
 
  loadClients() {
      return this.http.get<Array<ClientInterface>>(client_service + 'all');
  }
 
  createClient(client: ClientInterface) {
    return this.http.post<ClientInterface>(client_service + 'create', client);
  }
 
  updateClient(client: ClientInterface) {
    return this.http.put<ClientInterface>(client_service + 'update/', client);
  }
 
  deleteClient(id:number) {
    return this.http.delete<String>(client_service + 'delete/' + id, { responseType: 'text' as 'json'}
    );
  }
}
