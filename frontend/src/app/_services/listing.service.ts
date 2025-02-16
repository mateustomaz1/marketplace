import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const BASE_URL = 'http://localhost:8080/api'

@Injectable({
  providedIn: 'root',
})
export class ListingService {
  private http = inject(HttpClient);
  constructor() { }

  listing(id: String) : Observable<any> {
    return this.http.get(BASE_URL + '/listing/' + id);
  }
}
