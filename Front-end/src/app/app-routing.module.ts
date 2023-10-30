import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InboxComponent } from './inbox/inbox.component';
import { LoginComponent } from './login/login.component';
import { MailsComponent } from './mails/mails.component';
import { NotFoundComponent } from './not-found/not-found.component';
import RegisterComponent from './register/register.component';

const routes: Routes = [
  {path: 'login',component: LoginComponent},
  {path: 'register',component: RegisterComponent},
  {path: 'Mails', component: MailsComponent},
  {path: "", redirectTo: 'login', pathMatch: 'full'},
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
