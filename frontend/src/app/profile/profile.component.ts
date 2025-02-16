import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "../_services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import { ProfileService } from "../_services/profile.service";
import { profileResponse } from "../_modules/response";
import { authenticateResponse } from "../_modules/auth";
import { AppComponent } from "../app.component";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import { listingResponse } from "../_modules/response";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  modalShow = false;
  username: string = '';
  totalProducts = 0;
  image: File = new File([], '');
  listings: Array<listingResponse> = [];
  totalSold = 0;
  show = false;
  searchvalue = '';

  constructor(private main: AppComponent,
              private authService: AuthenticationService,
              private profileService: ProfileService,
              private route: ActivatedRoute,
              private router: Router) {}

  ListingForm = new FormGroup({
    title: new FormControl('', [Validators.required, Validators.maxLength(10)]),
    description: new FormControl('', [Validators.required, Validators.maxLength(100)]),
    price: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$")]),
    category: new FormControl('', [Validators.required]),
    image: new FormControl('', [Validators.required]),
    number: new FormControl('', [Validators.required, Validators.pattern("^[0-9]*$")]),
  });

  modal() {
    this.modalShow = !this.modalShow;
  }

  redirect(page: string): void {
    this.main.redirect(page);
  }

  file(event: any) {
    if (event.target.files.length > 0) {
      this.image = event.target.files[0];
      console.log(this.image);
    }
  }

  ngOnInit(): void {
    const linkUsername = this.route.snapshot.paramMap.get('username');
    if (localStorage.getItem('username') === linkUsername) this.show = true;

    const handleProfileResponse = (response: profileResponse) => {
      this.username = response.username;
      this.totalSold = response.totalSold;
      this.totalProducts = response.totalProducts;
      this.listings = response.listings;
    };

    this.profileService.profile(linkUsername).subscribe({
      next: handleProfileResponse,
      error: () => {
        this.authService.refresh().subscribe({
          next: (authResponse: authenticateResponse) => {
            localStorage.setItem('token', authResponse.token);
            localStorage.setItem('refreshToken', authResponse.refreshToken);
            this.profileService.profile(linkUsername).subscribe({
              next: handleProfileResponse,
              error: this.handleAuthServiceError
            });
          },
          error: this.handleAuthServiceError
        });
      }
    });
  }

  listing() {
    const values = this.ListingForm.value;

    if (values.title && values.description && values.price && values.category && values.image && values.number) {
      const request = {
        title: values.title,
        description: values.description,
        price: parseFloat(values.price),
        category: values.category,
        image: this.image,
        number: values.number,
      };

      const handleListingResponse = () => {
        location.reload();
        this.modal();
      };

      const handleListingError = () => {
        this.authService.refresh().subscribe({
          next: (authResponse: authenticateResponse) => {
            localStorage.setItem('token', authResponse.token);
            localStorage.setItem('refreshToken', authResponse.refreshToken);
            this.profileService.listing(request).subscribe({
              next: handleListingResponse,
              error: this.handleAuthServiceError
            });
          },
          error: this.handleAuthServiceError
        });
      };

      this.profileService.listing(request).subscribe({
        next: handleListingResponse,
        error: handleListingError
      });
    }
  }

  private handleOperationResponse(operation: any, id: number) {
    operation.subscribe({
      next: () => {
        location.reload();
      },
      error: () => {
        this.authService.refresh().subscribe({
          next: (authResponse: authenticateResponse) => {
            localStorage.setItem('token', authResponse.token);
            localStorage.setItem('refreshToken', authResponse.refreshToken);
            operation.subscribe({
              next: () => {
                location.reload();
              },
              error: this.handleAuthServiceError
            });
          },
          error: this.handleAuthServiceError
        });
      }
    });
  }

  private handleAuthServiceError = () => {
    this.authService.logout();
    this.main.redirect("error");
  };

  delete(id: number) {
    this.handleOperationResponse(this.profileService.delete(id), id);
  }

  sold(id: number) {
    this.handleOperationResponse(this.profileService.sold(id), id);
  }

  logout() {
    this.authService.logout();
    this.main.redirect("home");
  }

  search(): void {
    if (this.searchvalue !== '') {
      this.router.navigate(['/search'], { queryParams: { title: this.searchvalue } }).then((r) => console.log(r));
    }
  }
}
