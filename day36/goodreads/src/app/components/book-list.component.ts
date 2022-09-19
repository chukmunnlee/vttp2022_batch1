import { Component, OnInit } from '@angular/core';
import { BookSummary } from '../models';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books: BookSummary[] = []

  constructor(private bookSvc: BookService) { }

  ngOnInit(): void {

    this.bookSvc.getBooks()
      .then(result => {
        console.info('>>> books: ', result)
        this.books = result
      })
      .catch(error => {
        console.error('>>>> error: ', error)
      })
  }

}
