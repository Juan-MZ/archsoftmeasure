import { Component, OnInit } from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';
import { ActivatedRoute } from '@angular/router'
import {ApiService} from '../service/api.service';

@Component({
  selector: 'app-measurement-results',
  templateUrl: './measurement-results.component.html',
  imports: [
    NgIf,
    NgForOf
  ],
  styleUrls: ['./measurement-results.component.scss']
})
export class MeasurementResultsComponent implements OnInit {
  measurements: any; // Aquí se guardan las mediciones obtenidas
  selectedMeasurementResults: any = null; // Guarda los resultados seleccionados
  modalVisible: boolean = false; // Controla la visibilidad del modal

  constructor(private apiService: ApiService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getMeasurementResults();
  }

  // Método para obtener las mediciones desde el backend
  getMeasurementResults(): void {
    this.route.queryParams.subscribe((params) => {
      this.measurements = JSON.parse(params['measurements']);
    });
  }

  //Método para obtener secciones y métricas


  // Simula una llamada para calcular los puntajes y muestra el modal
  calculateScores(measurementId: any): void {
    this.apiService.calculateScores(measurementId).subscribe(
      (response) => {
        // Asigna los resultados seleccionados y abre el modal
        this.selectedMeasurementResults = response.data;
        this.modalVisible = true;
      },
      (error) => {
        console.error("Error al crear la medición:", error);
      }
    );
  }

  // Cierra el modal
  closeModal(): void {
    this.modalVisible = false;
  }

  protected readonly Number = Number;
}
