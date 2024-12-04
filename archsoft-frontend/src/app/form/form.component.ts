import { Component } from '@angular/core';
import { ApiService } from '../service/api.service';
import { Router } from '@angular/router';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  styleUrls: ['./form.component.scss']
})
export class FormComponent {
  constructor(private apiService: ApiService, private router: Router) {}
  modalVisible: boolean = false; // Controla la visibilidad del modal
  email: any;
  tests: any;
  data: any;

  createMeasurement(testId: any) {

      this.apiService.createMeasurement(this.email, testId).subscribe(
        (response) => {
          this.router.navigate(['test'], {
            queryParams: { email: this.email, measurementId: response.data.measurementId},
          });
          console.log("Medición creada:", response);
        },
        (response) => {
          alert(response.data.error);
        }
      );
  }

  validateEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }

  getTests(){
    if (this.email && this.validateEmail(this.email)) {
      this.apiService.getAllTests().subscribe(
        (response) => {
          this.tests = response.data;
          this.modalVisible = true;
        },
        (error) => {
          alert("No se han encontrado tests.");
          this.router.navigate(['']);
        }
      );
    } else {
      alert("Correo inválido");
    }
  }

  // Cierra el modal
  closeModal(): void {
    this.modalVisible = false;
  }
}
