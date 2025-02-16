import {Component} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {registerUser} from "../_modules/auth";
import {AuthenticationService} from "../_services/auth.service";
import {AppComponent} from "../app.component";

@Component({
  standalone: true,
  selector: 'app-register',
  templateUrl: './register.component.html',
  imports: [
    ReactiveFormsModule,
  ],
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  showError = false;

  constructor(private authService: AuthenticationService, private main: AppComponent) {}

  registrationForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.pattern('^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$'), Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  redirect(page: string): void {
    this.main.redirect(page);
  }

  onInput(): void {
    this.showError = false;
  }

  register(): void {
    const { username, email, password } = this.registrationForm.value;

    if (username && email && password) {
      const registerUserObject: registerUser = { username, email, password };

      this.authService.register(registerUserObject).subscribe({
        next: () => this.redirect('confirm'),
        error: () => (this.showError = true),
      });
    }
  }
}
