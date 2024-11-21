import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private urlApi = 'http://localhost:8080';
  private components = ["/section","/test"];
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getMetrics()
  }



  public getMetrics() : Observable<any> {
    let methodExtension = "/getAllSections";
    return this.http.get<any>(this.urlApi+this.components[0].toString()+methodExtension);

  }
}
