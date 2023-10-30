import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FolderDTO } from 'src/app/DTO/FolderDTO';

@Injectable({
  providedIn: 'root'
})
export class FolderService {

  private _url: string = "http://localhost:8080";
  private DTOType: string = "folder";

  
  constructor(private http: HttpClient) { }
  

  create(folder: FolderDTO){
    return this.http.post<FolderDTO>(`${this._url}/${this.DTOType}/create`, folder)
  }
  read(folder: FolderDTO){
    return this.http.post<FolderDTO>(`${this._url}/${this.DTOType}/register`, folder)
  }
  update(folder: FolderDTO){
    return this.http.put<FolderDTO>(`${this._url}/${this.DTOType}/rename`, folder)
  }
  delete(folder: FolderDTO){
    return this.http.delete<FolderDTO>(`${this._url}/${this.DTOType}/delete/${folder.folderId}`)
  }
  
}
