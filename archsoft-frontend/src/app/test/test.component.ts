import { Component, OnInit } from '@angular/core';
import {ApiService} from '../service/api.service';

@Component({
  selector: 'app-test',
  providers: [ApiService],
  templateUrl: './test.component.html',
  styleUrl: './test.component.scss'
})
export class TestComponent implements OnInit{
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
