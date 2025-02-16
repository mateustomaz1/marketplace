import {Component, OnInit} from '@angular/core';
import {SearchService} from "../_services/search.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppComponent} from "../app.component";
import {AuthenticationService} from "../_services/auth.service";
import {listingResponse} from "../_modules/response";

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {
  searchvalue = '';
  listings: Array<listingResponse> = [];
  constructor(private main: AppComponent,
              private authService: AuthenticationService,
              private searchService: SearchService,
              private router: Router,
              private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.searchService.search(params['title']).subscribe({
        next: (response: listingResponse[]) => {
          this.listings = response;
        },
        error: (error) => {
          console.error(error);
          console.log('No listings found');
        },
      });
    });
  }

  redirect(page: string): void {
    this.main.redirect(page);
  }

  logout(): void {
    this.authService.logout();
    this.redirect('home');
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
