import {inject, Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from "@angular/router";
import {AuthenticationService} from "../_services/auth.service";

@Injectable({
  providedIn: 'root'
})

export class PermissionsService  {
  constructor(private router: Router, private authenticationService: AuthenticationService) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authenticationService.hasPermission()) {
      return true;
    } else {
      this.router.navigate(['/error']).then(r => console.log(r));
      return false;
    }
  }
}

export const AuthGuard: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(PermissionsService).canActivate(next, state);
}
