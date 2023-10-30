import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ContactDTO } from 'src/app/DTO/ContactDTO';

@Injectable({
  providedIn: 'root'
})

export class ContactService {

  private _url: string = "http://localhost:8080";
  private DTOType: string = "contact";

  constructor(private http: HttpClient) { }

  create(contact: ContactDTO){
    return this.http.post<ContactDTO>(`${this._url}/${this.DTOType}/add`, contact);
  }
  
  getAllContacts(id: string){
    return this.http.get<ContactDTO[]>(`${this._url}/${this.DTOType}/get/${id}`);
  }
  
  read(contact: ContactDTO){
    this.http.post(`${this._url}/read/${this.DTOType}`, contact);
  }
  
  update(contact: ContactDTO){
    return this.http.put(`${this._url}/${this.DTOType}/update`, contact);
  }

  delete(contact: ContactDTO){
    return this.http.delete(`${this._url}/${this.DTOType}/delete/${contact.id}`);
  }
  
}
