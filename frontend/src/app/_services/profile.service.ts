import {inject, Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {listingRequest} from "../_modules/request";

const BASE_URL = 'http://localhost:8080/api'

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  private http = inject(HttpClient);
  constructor() { }

  profile(username: string | null) : Observable<any> {
    return this.http.get(BASE_URL + '/profile/' + username, {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
      }
    });
  }

  listing(data: listingRequest): Observable<any> {
    const formData = new FormData();
    const blobLoad = new Blob([JSON.stringify({
      title: data.title,
      description: data.description,
      price: data.price,
      category: data.category,
      number: data.number,
    })], {
      type: 'application/json',
    });

    formData.set('request', blobLoad);
    if (data.image) formData.append('image', data.image, data.image.name);

    return this.http.post(BASE_URL + '/profile/new/listing', formData, {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token',),
      }, responseType: 'text'
    });
  }

  delete(id: number) : Observable<any> {
    return this.http.delete(BASE_URL + '/profile/delete/listing/' + id, {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
      }, responseType: 'text'
    });
  }

  sold(id: number) : Observable<any> {
    return this.http.post(BASE_URL + '/profile/listing/sold/' + id, null, {
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
      }, responseType: 'text'
    });
  }
}
