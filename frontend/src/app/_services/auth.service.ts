import {inject, Injectable} from "@angular/core";
import {loginUser, registerUser} from "../_modules/auth";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

const BASE_URL = 'http://localhost:8080/api/auth/'

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private http = inject(HttpClient);
  constructor() { }

  authenticate(data: loginUser) : Observable<any> {
    return this.http.post<any>(BASE_URL + 'authenticate', data);
  }

  refresh() : Observable<any> {
    return this.http.post(BASE_URL + 'refresh', {
      'refreshToken': localStorage.getItem('refreshToken')
    });
  }

  register(data: registerUser) : Observable<any> {
    return this.http.post<any>(BASE_URL + 'register', data);
  }

  logout(): void {
    localStorage.clear();
  }

  hasPermission(): boolean {
    const authToken = localStorage.getItem('token');
    return !!authToken;
  }
}
