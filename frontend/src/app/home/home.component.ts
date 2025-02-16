import {Component} from '@angular/core';
import {AuthenticationService} from "../_services/auth.service";
import {AppComponent} from "../app.component";
import {FormsModule} from "@angular/forms";
import {SearchService} from "../_services/search.service";
import {Router} from "@angular/router";

@Component({
  standalone: true,
  selector: 'app-home',
  styleUrls: ['./home.component.css'],
  templateUrl: './home.component.html',
  imports: [
    FormsModule
  ]
})

export class HomeComponent {
  searchvalue = '';
  constructor(private main: AppComponent,
              private authenticationService: AuthenticationService,
              private router: Router) {}

  redirect(page: String) : void {
    this.main.redirect(page);
  }

  logout() {
    this.authenticationService.logout();
    this.redirect('home')
  }

  hasToken(): boolean {
    return !!localStorage.getItem('token');
  }

  search(): void {
    if (this.searchvalue !== '') {
      this.router.navigate(['/search'], { queryParams: { title: this.searchvalue } }).then((r) => console.log(r));
    }
  }

  protected readonly localStorage = localStorage;
}
