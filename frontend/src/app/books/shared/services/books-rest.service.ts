import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Book} from '../../model/book';

@Injectable({
  providedIn: 'root'
})
export class BooksRestService {

  constructor(private readonly http: HttpClient) {
  }

  findAll(): Observable<Book[]> {
    return this.http.get<Book[]>("/api/books");
  }
}
