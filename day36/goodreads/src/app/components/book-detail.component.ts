import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../models';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  bookId!: string
  book!: Book

  constructor(private activatedRoute: ActivatedRoute, private title: Title,
      private bookSvc: BookService) { }

  ngOnInit(): void {
    this.book = {
      bookId: '',
      title: '',
      description: '',
      authors: '',
      rating: 0,
      imageUrl: ''
    }
    this.bookId = this.activatedRoute.snapshot.params['bookId']
    this.title.setTitle(`Book: ${this.bookId}`)
    this.bookSvc.getBookById(this.bookId)
      .then(result => {
        console.info('>>>> book: ', result)
        this.book = result
      })
      .catch(error => {
        console.error('>>>> error: ', error)
      })

  }

}
