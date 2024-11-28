import { Component} from '@angular/core';
import {Router} from '@angular/router';
import {ApiService} from "../service/api.service";

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrl: './landing.component.scss',
  providers: [ApiService]
})
export class LandingComponent {
  data: any = {};
  constructor(private apiService: ApiService, private router: Router) {}

  // Método para redirigir
  goTo(component: string) {
    this.router.navigate([component]); // Navega a form
  }
}
