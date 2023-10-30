import { Component, EventEmitter, Input, OnInit, Output, ɵsetCurrentInjector } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { ContactDTO } from '../DTO/ContactDTO';
import { FolderDTO } from '../DTO/FolderDTO';
import { DialogService } from '../Service/Dialog/dialog.service';
import { UserService } from '../Service/User/user.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})

export class SideBarComponent implements OnInit{
  @Input() contacts: ContactDTO[] = [];
  @Input() folders: FolderDTO[] = [];
  @Output() activeFolder = new EventEmitter<FolderDTO>  ();
  @Output() activeContact = new EventEmitter<ContactDTO>  ();
  staticFolders: Map<string, string> = new Map ([
    ["inbox", ''],
    ["draft", ""],
    ["sent", ""],
    ["trash", ""]
  ])

  folderArray: FolderDTO[] = [];
  constructor(public dialog: MatDialog, public dialogservice: DialogService, public userService: UserService) { }
  
  ngOnInit() {
    this.staticFolders =  this.userService.folders;
    let myMap = new Map(Object.entries(this.userService.folders));
    myMap.forEach((value:any, key:any) =>{
      let folder = new FolderDTO();
      folder.folderId = value;
      folder.folderName = key;
      this.folderArray.push(folder);
    })
    console.log("side bar init: ", this.folderArray);
  }

  openDialog(window: string, update?: boolean, toUpdate?: any) {
    this.dialogservice.resetAllDialogs();
    this.dialogservice.selectedDialog[window] = true;
    this.dialogservice.selectedDialog['update'] = update;
    this.dialogservice.toUpdate = toUpdate;
    console.log(toUpdate);
  }

  selectFolder(folderIndex: any){
    console.log("my folder index:", folderIndex);
    this.activeFolder.emit(folderIndex);
  }

  selectContact(contactIndex: any){
    this.activeContact.emit(this.contacts[contactIndex]);
  }

  emitFolder(folder: string){
    this.activeFolder.emit(this.searchFolder(folder))
  }

  searchFolder(myFolder: string){
    for(let i = 0; i < this.folderArray.length; i++) {
      if(myFolder == this.folderArray[i].folderName)
        return this.folderArray[i];
    }
    return new FolderDTO();
  }

}