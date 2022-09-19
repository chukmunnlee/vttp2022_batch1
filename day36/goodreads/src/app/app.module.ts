import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { BookListComponent } from './components/book-list.component';
import { BookService } from './services/book.service';
import { BookDetailComponent } from './components/book-detail.component';

const appRoutes: Routes = [
  { path: '', component: BookListComponent },
  { path: 'book/:bookId', component: BookDetailComponent },

  // Catch all
  { path: '**', redirectTo: '/', pathMatch: 'full'},
]

@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    BookDetailComponent,
  ],
  imports: [
    BrowserModule, HttpClientModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [ BookService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
