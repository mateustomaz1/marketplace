import {Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {ConfirmComponent} from "./confirm/confirm.component";
import {ProfileComponent} from "./profile/profile.component";
import {AuthGuard} from "./_guards/auth.guard";
import {ErrorComponent} from "./error/error.component";
import {ListingComponent} from "./listing/listing.component";
import {SearchComponent} from "./search/search.component";

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, title: 'Home' },
  { path: 'login', component: LoginComponent, title: 'Login' },
  { path: 'register', component: RegisterComponent, title: 'Registration' },
  { path: 'confirm', component: ConfirmComponent, title: 'Confirm' },
  { path: 'error', component: ErrorComponent, title: 'Error' },
  { path: 'profile/:username', component: ProfileComponent, title: 'Profile', canActivate: [AuthGuard] },
  { path: 'listing/:id', component: ListingComponent, title: 'Listing' },
  { path: 'search', component: SearchComponent, title: 'Search' }
];
