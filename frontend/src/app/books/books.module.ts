import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BookListComponent} from './book-list/book-list.component';
import {BookPanelComponent} from './book-list/book-panel/book-panel.component';


@NgModule({
  declarations: [
    BookListComponent,
    BookPanelComponent
  ],
  imports: [
    CommonModule
  ]
})
export class BooksModule {
}
