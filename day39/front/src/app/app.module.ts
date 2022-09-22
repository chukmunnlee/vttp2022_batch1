import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router'
import { ReactiveFormsModule } from '@angular/forms'

import { AppComponent } from './app.component';
import { NumberComponent } from './components/number.component';
import { RegisterComponent } from './components/register.component';
import { RegistrationService } from './registration.service';

const appRoutes: Routes = [
  { path: '', component: NumberComponent },
  { path: 'number/:num', component: NumberComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    NumberComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,
    //RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [RegistrationService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
