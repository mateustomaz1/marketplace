import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

const BASE_URL = 'http://localhost:8080/api'

@Injectable({
  providedIn: 'root',
})
export class SearchService {
  private http = inject(HttpClient);
  constructor() { }

  search(title: string) : Observable<any> {
    const params = new HttpParams().set('title', encodeURIComponent(title));
    return this.http.get(BASE_URL + '/search', {params});
  }
}
