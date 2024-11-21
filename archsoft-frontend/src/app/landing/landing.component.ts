import { Component, OnInit} from '@angular/core';
import {ApiService} from "../service/api.service";

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrl: './landing.component.scss',
  providers: [ApiService]
})
export class LandingComponent implements OnInit {
  data: any = {};
  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.getData()
  }

  getData(){
    this.apiService.getMetrics().subscribe(data => {
      this.data = data;
      console.log(this.data)
    })
  }
}
