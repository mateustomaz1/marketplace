import { Component } from '@angular/core';
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-confirm',
  standalone: true,
  templateUrl: './confirm.component.html',
  styleUrl: './confirm.component.css'
})
export class ConfirmComponent {
  constructor(private main: AppComponent) {}

  redirect(page: String) : void {
    this.main.redirect(page);
  }
}
