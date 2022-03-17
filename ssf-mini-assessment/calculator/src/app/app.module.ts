import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { RouterModule, Routes } from '@angular/router'
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home.component';
import { ResultComponent } from './components/result.component';
import {CalculateService} from './calculate.service';

const appRoute: Routes = [
	{ path: '', component: HomeComponent },
	{ path: 'result', component: ResultComponent },
	{ path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ResultComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule,
	  HttpClientModule,
	  RouterModule.forRoot(appRoute), 
  ],
  providers: [ CalculateService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
