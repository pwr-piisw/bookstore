import {TestBed} from '@angular/core/testing';
import {BooksRestService} from './books-rest.service';


describe('BooksRest.ServiceService', () => {
  let service: BooksRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BooksRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
