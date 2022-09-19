import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { Book, BookSummary } from "../models";

@Injectable()
export class BookService {

  constructor(private http: HttpClient) { }

  getBookById(bookId: string): Promise<Book> {
    return firstValueFrom(
      this.http.get<Book>(`/api/book/${bookId}`)
    )
  }

  getBooks(limit = 20, offset = 0): Promise<BookSummary[]> {
    const params = new HttpParams()
        .set("limit", limit).set("offset", offset)

    return firstValueFrom(
      this.http.get<BookSummary[]>('/api/books', { params })
    )
  }

}
