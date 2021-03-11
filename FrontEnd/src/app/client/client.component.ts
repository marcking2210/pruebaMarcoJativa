import { Component, OnInit } from '@angular/core';
import { ClientService, ClientInterface } from '../service/client.service';
import { NgbModal, NgbModalRef, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  _clients: Array<ClientInterface> = [];
  _message: String = "";
  _client = {} as ClientInterface;
 
  modalReference!: NgbModalRef;
  modalOption: NgbModalOptions = {};
 
  constructor(private clientService: ClientService,
    private modalService: NgbModal) { }
 
  deleteClient(id: number) {
    this.clientService.deleteClient(id).subscribe((msg: String) => this._message = msg);
    this._clients.splice(this._clients.findIndex(s => s.id === id), 1);
  }
 
  updateClient(id: number) {
   this.clientService.updateClient(this._client).subscribe((client: ClientInterface) => this._client = client);
  }
 
  addClient() {
   this.clientService.createClient(this._client).subscribe((client: ClientInterface) => {
      this._client = client;
      this._clients.push(this._client);
    });
  }
 
  createUpdate() {
    if(this._client.id === null || this._client.id === 0){
      this.addClient();
    } else {
      this.updateClient(this._client.id);
    }
    this.modalReference.close();
  }
 
  ngOnInit(): void {
    this.clientService.loadClients().subscribe(clients => this._clients = clients);
    this._message = "";
  }
 
  open(id : number, content: any) {
    this.modalOption.backdrop = 'static';
    this.modalOption.keyboard = false;
    this.modalReference = this.modalService.open(content, this.modalOption);
    if(id === 0 ) {
      this._client = {id : 0, clientName: "", clientLastName : ""};
    } else {
      this._client = this._clients.find(s => s.id === id) || {} as ClientInterface;
    }
  }

}
