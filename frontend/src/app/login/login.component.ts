import {Component} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthenticationService} from "../_services/auth.service";
import {loginUser} from "../_modules/auth";
import {authenticateResponse} from "../_modules/auth";
import {AppComponent} from "../app.component";

@Component({
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [
    ReactiveFormsModule
  ],
  styleUrl: './login.component.css'
})
export class LoginComponent {
  showError = false;

  constructor(private authService: AuthenticationService,
              private main: AppComponent) {}

  redirect(page: string): void {
    this.main.redirect(page);
  }

  onInput(): void {
    this.showError = false;
  }

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  authenticate(): void {
    const { username, password } = this.loginForm.value;

    if (username && password) {
      const loginUserObject: loginUser = { username, password };

      this.authService.authenticate(loginUserObject).subscribe({
        next: (response: authenticateResponse) => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('refreshToken', response.refreshToken);
          localStorage.setItem('username', username);
          this.redirect('home');
        },
        error: (error) => {
          this.showError = true;
        },
      });
    }
  }
}
