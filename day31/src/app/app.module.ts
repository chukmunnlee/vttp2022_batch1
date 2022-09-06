import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { GreetingsComponent } from './components/greetings.component';
import { RegistrationComponent } from './components/registration.component';

@NgModule({
  declarations: [
    AppComponent,
    GreetingsComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
