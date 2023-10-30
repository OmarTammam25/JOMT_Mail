import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ContactDTO } from 'src/app/DTO/ContactDTO';
import { MailDTO } from 'src/app/DTO/MailDTO';
import { ContactService } from '../Contact/contact.service';
import { MailService } from '../Mail/mail.service';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(mailservice: MailService,
              contactservice: ContactService) { }

  toUpdate!: any
  
  selectedDialog: any = {
    'mail': false,
    'folder': false,
    'contact': false,
    'update': false
  }

  resetAllDialogs(){
    this.selectedDialog = {
      'mail': false,
      'folder': false,
      'contact': false,
      'update': false
    }
  }
}
