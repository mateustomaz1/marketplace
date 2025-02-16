import {Component} from '@angular/core';
import {Router, RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-root',
  standalone: true,
  template: '<router-outlet></router-outlet>',
  imports: [
    RouterOutlet
  ],
  styles: []
})
export class AppComponent {
  title = "MarketPlace"

  constructor(private router: Router) {}

  redirect(page: String) {
    this.router.navigate(['/' + page]).then(r => console.log(r));
  }
}
