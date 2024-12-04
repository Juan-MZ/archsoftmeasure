import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { ApiService } from '../service/api.service';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  imports: [
    FormsModule,
    NgIf,
    NgForOf
  ],
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {
  test: any;
  selectedAnswers: { [key: number]: number | null } = {};
  email: any;
  measurementId: any;
  modalVisibility: boolean = false;

  constructor(private apiService: ApiService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.apiService.getTest().subscribe((response: any) => {
      this.test = response.data;
      this.initializeAnswers();
    });
    this.route.queryParams.subscribe((params) => {
      this.email = params['email'];
      this.measurementId = params['measurementId'];
      console.log("email: "+this.email);
    });
  }

  initializeAnswers() {
    this.test.questions.forEach((_: any, index: number) => {
      this.selectedAnswers[index] = null; // Inicializa las respuestas seleccionadas como null
    });
  }

  submitAnswers() {
    //respuestas seleccionadas
    const answers = this.test.questions.map((question: any, index: number) => this.selectedAnswers[index]);
    console.log(answers);

    this.apiService.sumbitMeasurement(answers, this.measurementId).subscribe(
      (response) => {
        console.log('Respuestas enviadas:', response);
        alert(response.userMessage);
        this.router.navigate(['']);
      },
      (error) => {
        console.error('Error al enviar respuestas:', error);
        alert('Ocurrió un error al enviar las respuestas.');
      }
    );
  }
}
