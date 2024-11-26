import { Component } from '@angular/core';
import { ApiService } from '../service/api.service';
import { Router } from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./form.component.scss']
})
export class FormComponent {
  constructor(private apiService: ApiService, private router: Router) {}
  email: any;
  data: any;

  createMeasurement() {
    if (this.email && this.validateEmail(this.email)) {
      this.apiService.createMeasurement(this.email).subscribe(
        (response) => {
          this.router.navigate(['test'], {
            queryParams: { email: this.email, measurementId: response.data.measurementId},
          });
          console.log("Medición creada:", response);
        },
        (error) => {
          console.error("Error al crear la medición:", error);
        }
      );
    } else {
      console.error("Correo inválido");
    }
  }

  validateEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
}
