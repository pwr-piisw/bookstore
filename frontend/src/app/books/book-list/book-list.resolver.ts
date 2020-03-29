import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Book} from '../model/book';
import {Observable} from 'rxjs';
import {BooksRestService} from '../shared/services/books-rest.service';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookListResolver implements Resolve<Book[] | null> {

  constructor(private readonly booksService: BooksRestService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Book[] | null> | Book[] | null {
    return this.booksService.findAll();
  }

}
