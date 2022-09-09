import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { ReactiveFormsModule, FormsModule } from '@angular/forms'

import { AppComponent } from './app.component';
import { RegistrationComponent } from './components/registration.component'
import { RegistrationService } from './services/registration.service';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ RegistrationService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
