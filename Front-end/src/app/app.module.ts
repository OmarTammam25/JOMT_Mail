import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {MatFormFieldModule} from '@angular/material/form-field';
import {MatBadgeModule} from '@angular/material/badge';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatChipsModule} from '@angular/material/chips';
import {MatDialogModule} from '@angular/material/dialog';
import {MatCheckboxModule} from '@angular/material/checkbox';



import { AppRoutingModule } from './app-routing.module';
import { InboxComponent } from './inbox/inbox.component';
import RegisterComponent from './register/register.component';
import { LoginComponent } from './login/login.component';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatMenuModule } from '@angular/material/menu';
import { MatSliderModule } from '@angular/material/slider';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatTabsModule} from '@angular/material/tabs';
import {MatNativeDateModule} from '@angular/material/core';
import {MatRippleModule} from '@angular/material/core';
import {MatIconModule} from '@angular/material/icon';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatPaginatorModule} from '@angular/material/paginator';

import {ReactiveFormsModule} from "@angular/forms";
import { SideBarComponent } from "./side-bar/side-bar.component";
import { NavBarComponent } from "./nav-bar/nav-bar.component";
import { MailsComponent } from './mails/mails.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { NewMailComponent } from './newComponent-dialog/newComponent-dialog.component';

@NgModule({
    declarations: [
        AppComponent,
        InboxComponent,
        RegisterComponent,
        LoginComponent,
        NavBarComponent,
        SideBarComponent,
        MailsComponent,
        NotFoundComponent,
        NewMailComponent
    ],
    providers: [],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatGridListModule,
        MatDialogModule,
        MatTabsModule,
        MatRippleModule,
        MatChipsModule,
        ReactiveFormsModule,
        MatPaginatorModule,
        HttpClientModule,
        MatInputModule,
        MatCheckboxModule,
        FormsModule,
        MatButtonModule,
        MatTooltipModule,
        MatMenuModule,
        MatSliderModule,
        MatBottomSheetModule,
        MatIconModule,
        MatBadgeModule,
        MatFormFieldModule,
        MatSlideToggleModule,
    ]
})
export class AppModule { }