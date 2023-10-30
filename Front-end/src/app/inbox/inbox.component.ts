import { Component, EventEmitter, Output, Input, OnInit } from '@angular/core';
import { AttachmentDTO } from '../DTO/AttachmentDTO';
import { FolderDTO } from '../DTO/FolderDTO';
import { MailDTO } from '../DTO/MailDTO';
import { DialogService } from '../Service/Dialog/dialog.service';
import { MailService } from '../Service/Mail/mail.service';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})

export class InboxComponent {

  mailActive = false;
  activeMail: MailDTO = this.resetActiveMail();
  allChecked = false;
  selected: any = []

  
  @Input() mails: MailDTO[] = [];
  @Input() folders: FolderDTO[] = [];
  @Input() activeFolder!: FolderDTO;
  @Output() inboxAction = new EventEmitter<string>();
  @Output() selectedMail = new EventEmitter<MailDTO>();
  @Output() selectedMails = new EventEmitter<MailDTO[]>();
  
  attachments: AttachmentDTO[] = [];
  
  constructor(public dialogservice: DialogService, public mailService: MailService) { }
/* 
  ngOnInit(){
    this.emitInboxAction('read');
  } */

  showMail(mail: MailDTO){
    this.activeMail = mail;
    console.log("active from top haha ", mail)
    this.getAttachments();
  }
  
  selectMail(mail: any){
    var index = this.getMailIndex(mail);
    if(index == -1){
      this.selected.push(mail);console.log("Added")}
    else{
      this.selected.splice(index, 1);console.log("removed")}
    console.log(this.selected)
    this.allChecked = this.isAllSelected()
  }
  
  selectAllMails(){ 
    this.selected = []
    if(this.allChecked)
    this.mails.forEach((val:any) => {this.selected.push(Object.assign({}, val));});
    console.log(this.selected)
  }

  isAllSelected(){return this.selected.length == this.mails.length}
  someSelected(): boolean {return this.selected.length != 0 && !this.allChecked}

  isSelected(mail: MailDTO){
    for(var selec of this.selected)
      if(JSON.stringify(mail) == JSON.stringify(selec)) 
      return true
    return false
  }

  getMailIndex(mail: MailDTO){
    for(var i = 0;i < this.selected.length;i++)
      if(JSON.stringify(mail) == JSON.stringify(this.selected[i])) 
      return i
    return -1;
  }

  getPriority(pr: number){
    switch(pr){
      case 1: return "Unimportant";break;
      case 2: return "Normal";break;
      case 3: return "Important";break;
      case 4: return "Urgent";break;
      default: return "UnSet";break;
    }
  }

  openDialog(window: string, mail: MailDTO) {
    if(this.activeFolder.folderName != 'draft') return
    this.dialogservice.selectedDialog[window] = true;
    this.dialogservice.selectedDialog['update'] = true;
    this.dialogservice.toUpdate = mail;
  }

  emitInboxAction(type: string){
    this.selectedMails.emit(this.selected);
    this.inboxAction.emit(type);
  }
  
  emitMailAction(type: string, folder?: FolderDTO){
    this.mailActive = false;
    this.selectedMail.emit(this.activeMail);
    this.inboxAction.emit(type);
    this.activeMail = this.resetActiveMail();
  }

  changeMailFolder(folder: FolderDTO){
    console.log(folder);
    this.mailService.addMailToFolder(this.activeMail, folder).subscribe(data => {
        console.log("mail changed location ", data);
    })
  }

  resetActiveMail(){
    return new MailDTO();
  }

  getAttachments(){
    console.log("acitove", this.activeMail.mailId)
    this.mailService.getAttachments(this.activeMail.mailId).subscribe(data => {
      console.log(data);
      this.attachments = data;
        // saveAs(i.url);
    });

  }
  
}
