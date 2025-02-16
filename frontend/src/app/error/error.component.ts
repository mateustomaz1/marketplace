import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-error',
  standalone: true,
  imports: [],
  templateUrl: './error.component.html',
  styleUrl: './error.component.css'
})
export class ErrorComponent {
  constructor(private main: AppComponent) {}

  redirect(page: String) : void {
    this.main.redirect(page);
  }
}
