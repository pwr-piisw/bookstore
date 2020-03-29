import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BookListComponent} from './books/book-list/book-list.component';
import {BookListResolver} from './books/book-list/book-list.resolver';


const routes: Routes = [
  {
    path: 'books',
    component: BookListComponent,
    resolve: {
      books: BookListResolver
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
