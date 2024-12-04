import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ApiService} from '../service/api.service';
import { Router } from '@angular/router';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-search',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {
  constructor(private apiService: ApiService, private router: Router) {}
  email: any;

  buscarResultados() {
    if (this.email && this.validateEmail(this.email)) {
      this.apiService.getMeasurementsByMail(this.email).subscribe(
        (response) => {
          this.router.navigate(['measurement-results'], {
            queryParams: { measurements: JSON.stringify(response.data) },
          });
        },
        (error) => {
          alert(JSON.stringify(error.error.data.error));
        }
      );
    } else {
      alert("Correo inválido");
    }
  }

  validateEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
}
