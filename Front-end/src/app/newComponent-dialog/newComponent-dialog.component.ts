import { Component, EventEmitter, Output, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ContactDTO } from '../DTO/ContactDTO';
import { FolderDTO } from '../DTO/FolderDTO';
import { MailDTO } from '../DTO/MailDTO';
import { ContactService } from '../Service/Contact/contact.service';
import { DialogService } from '../Service/Dialog/dialog.service';
import { FolderService } from '../Service/Folder/folder.service';
import { MailService } from '../Service/Mail/mail.service';
import { UserService } from '../Service/User/user.service';

@Component({
  selector: 'app-newComponent-dialog',
  templateUrl: './newComponent-dialog.component.html',
  styleUrls: ['./newComponent-dialog.component.css'],
})

export class NewMailComponent {
  
  constructor(public dialogservice: DialogService, public userService: UserService,
     public folderService: FolderService, public contactService: ContactService) { }
  
  important = "Must Enter a Value!"
  mailValid = "Must Enter a Valid Email!"
  userFiles!: FileList

  limitLength(ch: number){ return ch + " Characters Only!"}

  mailGroup = new FormGroup({
    receiver: new FormControl('', [Validators.required, Validators.email]),
    subject: new FormControl('', [Validators.required, Validators.maxLength(30)]),
    body: new FormControl('', [Validators.required]),
    priority: new FormControl(0, [Validators.required])
  });
  
  contactGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.maxLength(15)]),
    mail: new FormControl('', [Validators.required, Validators.email])
  });

  folderGroup = new FormGroup({name: new FormControl('', [Validators.required, Validators.maxLength(15)])});

  fieldError(field: string, formgroup: FormGroup){ 
    return formgroup.get(field)?.invalid 
  }

  fieldErrorMessage(field: string, formgroup: FormGroup){ 
    if(formgroup.get(field)?.hasError(field) && field == 'name')
      return this.limitLength(15);

    return formgroup.get(field)?.hasError('required') ? this.important : 
    formgroup.get(field)?.hasError('email') ? this.mailValid :
    formgroup.get(field)?.hasError('maxlength') ? this.limitLength(30) : '';
  }

  fieldUpdateMessage(field: string, formgroup: FormGroup){ 
    if(!this.dialogservice.selectedDialog['update']) return "";
    if(formgroup == this.mailGroup){
      switch(field){
        case 'to': return this.dialogservice.toUpdate.to;
        case 'subject': return this.dialogservice.toUpdate.subject;
        case 'body': return this.dialogservice.toUpdate.content;
      }
    }
    if(formgroup == this.contactGroup){
      switch(field){
        case 'name': return this.dialogservice.toUpdate.name;
        case 'mail': return this.dialogservice.toUpdate.mails;
      }
    }
    if(formgroup == this.folderGroup){
      switch(field){
        case 'name': return this.dialogservice.toUpdate.folderName;
      }
    }
  }

  @Output() emitMail = new EventEmitter<MailDTO>();
  @Output() emitFile = new EventEmitter<FileList>();
  submitMail(){
    this.dialogservice.selectedDialog['mail'] = false
    let mail = <MailDTO>{
      from: this.userService.userEmail,
      to: this.mailGroup.controls.receiver.value, 
      subject: this.mailGroup.controls.subject.value, 
      content: this.mailGroup.controls.body.value,
      state: "mail",
      priority: this.mailGroup.controls.priority.value?this.mailGroup.controls.priority.value:0,
      isStarred: false
    };
    console.log("send called", mail);
    this.emitFile.emit(this.userFiles);
    this.emitMail.emit(mail);
    this.mailGroup.reset();
  }

  addFiles(e: any){
    if(e){
      this.userFiles = e.target.files
      console.log("files: ", this.userFiles)
    }
  }


  @Output() updateDraft = new EventEmitter<MailDTO>();
  submitDraft(){
    this.dialogservice.selectedDialog['mail'] = false
    let mail = <MailDTO>{
      mailId: this.dialogservice.toUpdate ? this.dialogservice.toUpdate.id:'',
      from: this.userService.userEmail,
      to: this.mailGroup.controls.receiver.value, 
      subject: this.mailGroup.controls.subject.value, 
      content: this.mailGroup.controls.body.value,
      state: "draft",
      priority: this.mailGroup.controls.priority.value?this.mailGroup.controls.priority.value:0,
      isStarred: false
    };
    if(this.dialogservice.selectedDialog['update'])
      this.updateDraft.emit(mail);
    else{
      this.emitMail.emit(mail);
      console.log("emit called: ", mail);
    }
    this.mailGroup.reset();
    this.dialogservice.selectedDialog['update'] = false
  }

  @Output() emitFolder = new EventEmitter<FolderDTO>();
  @Output() updateFolder = new EventEmitter<FolderDTO>();
  submitFolder(){
    this.dialogservice.selectedDialog['folder'] = false
    let folder = <FolderDTO>{
      folderId: this.dialogservice.toUpdate ? this.dialogservice.toUpdate.folderId:'',
      folderName: this.folderGroup.controls.name.value,
      userId: this.userService.userId
    };

    
    if(this.dialogservice.selectedDialog['update'])
    this.updateFolder.emit(folder);
    else{
      this.folderService.create(folder).subscribe(data => {
        folder.folderId = data.folderId;
        console.log("folder created: ", folder);
      })
      this.emitFolder.emit(folder);
    } 
    this.folderGroup.reset();
    this.dialogservice.selectedDialog['update'] = false
  }
  
  @Output() emitContact = new EventEmitter<ContactDTO>();
  @Output() updateContact = new EventEmitter<ContactDTO>();
  submitContact(){
    this.dialogservice.selectedDialog['contact'] = false
    let contact:ContactDTO = <ContactDTO>{
      id: this.dialogservice.toUpdate ? this.dialogservice.toUpdate.id: '',
      name: this.contactGroup.controls.name.value, 
      mails: this.contactGroup.controls.mail.value,
      userId: this.userService.userId
    };
    
    if(this.dialogservice.selectedDialog['update'])
      this.updateContact.emit(contact);
    else{
      this.contactService.create(contact).subscribe(data => {
        contact.id = data.id
      })
        this.emitContact.emit(contact);
    }
    
    this.contactGroup.reset();
    this.dialogservice.selectedDialog['update'] = false
  }

  @Output() deleteDTO = new EventEmitter<any>();
  deleteDialog(){
    this.dialogservice.resetAllDialogs();
    if (this.dialogservice.toUpdate)
      this.deleteDTO.emit(this.dialogservice.toUpdate)
  }
}
