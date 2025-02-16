import {Component, OnInit} from '@angular/core';
import {ListingService} from "../_services/listing.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AppComponent} from "../app.component";
import {listingResponse} from "../_modules/response";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthenticationService} from "../_services/auth.service";

@Component({
  selector: 'app-listing',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './listing.component.html',
  styleUrl: './listing.component.css'
})
export class ListingComponent implements OnInit {
  pageListingResponse: listingResponse | undefined;
  searchvalue = '';

  constructor(private listing: ListingService,
              private route: ActivatedRoute,
              private main: AppComponent,
              private authenticationService: AuthenticationService,
              private router: Router) {}

  redirect(page: String) : void {
    this.main.redirect(page);
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.listing.listing(id).subscribe({
        next: (response: listingResponse) => {
          response === null ? this.redirect('home') : (this.pageListingResponse = response);
        },
        error: () => this.redirect('home'),
      });
    } else this.redirect('home');
  }

  logout() {
    this.authenticationService.logout();
    this.main.redirect("home");
  }

  search(): void {
    if (this.searchvalue !== '') {
      this.router.navigate(['/search'], { queryParams: { title: this.searchvalue } }).then((r) => console.log(r));
    }
  }

  protected readonly localStorage = localStorage;
}
