import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDTO } from 'src/app/DTO/UserDTO';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  private _url: string = "http://localhost:8080";
  private DTOType: string = "user";
  userId!: string;
  userEmail!: string;
  folders: Map<string,string> = new Map<string, string>([
    ['aklo', 'zeby']
  ]);
  
  constructor(private http: HttpClient) { }
  
  login(user: UserDTO){
    return this.http.post<UserDTO>(`${this._url}/${this.DTOType}/login`, user)
  }

  create(user: UserDTO){
    return this.http.post<UserDTO>(`${this._url}/${this.DTOType}/register`, user)
  }
  
  read(user: UserDTO){
    this.http.post(`${this._url}/read/${this.DTOType}`, user);
  }
  
  update(user: UserDTO){
    this.http.post(`${this._url}/update/${this.DTOType}`, user);
  }

  Delete(user: UserDTO){
    this.http.post(`${this._url}/delete/${this.DTOType}`, user);
  }
}