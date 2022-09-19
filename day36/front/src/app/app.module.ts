import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home.component';
import { CatComponent } from './components/cat.component';
import { DogComponent } from './components/dog.component';
import { NumListComponent } from './components/num-list.component';
import { NumDisplayComponent } from './components/num-display.component';

const appPath: Routes = [
  //{ path: '', component: HomeComponent },
  //{ path: 'dog', component: DogComponent },
  //{ path: 'cat', component: CatComponent },
  { path: '', component: NumListComponent },
  { path: 'number/:num', component: NumDisplayComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full'},
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent, CatComponent, DogComponent, NumListComponent, NumDisplayComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appPath)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
