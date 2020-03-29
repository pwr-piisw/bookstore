import {Component, OnInit} from '@angular/core';
import {Book} from '../model/book';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {

  books: Book[];

  constructor(private readonly route: ActivatedRoute) {
  }

  ngOnInit() {
    this.books = this.route.snapshot.data.books;
    console.log('--- books ---');
    console.log(this.books);
  }

}
