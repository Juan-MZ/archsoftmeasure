import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private urlApi = 'https://atlantes.servehttp.com:8080/api';
  //private urlApi = 'http://localhost:8080/api';
  private components = ["/section","/test","/measurement","/metric"];
  constructor(private http: HttpClient) { }

  public createMeasurement(correo: string, testId: number) : Observable<any> {
    let methodExtension = "/createMeasurement"+"/"+correo+"/"+testId.toString();
    // @ts-ignore
    return this.http.post<any>(this.urlApi+this.components[2].toString()+methodExtension);
  }

  public getAllTests() : Observable<any> {
    let methodExtension = "/getAllTests";
    return this.http.get<any>(this.urlApi+this.components[1].toString()+methodExtension);
  }

  public getTest() : Observable<any> {
    let methodExtension = "/getTest"+"/1";
    return this.http.get<any>(this.urlApi+this.components[1].toString()+methodExtension);
  }

  public sumbitMeasurement(payload: any, measurementId: any) : Observable<any> {
    let body = {
      "answers": payload
    }
    let methodExtension = "/sendAnswersToMeasurement"+"/"+measurementId.toString();
    return this.http.patch<any>(this.urlApi+this.components[2].toString()+methodExtension, body);
  }

  public getMeasurementsByMail(mail: string) {
    let methodExtension = "/getMeasurementsByEmail"+"/"+mail;
    return this.http.get<any>(this.urlApi+this.components[2].toString()+methodExtension);
  }

  public calculateScores(measurementId: any) : Observable<any> {
    let methodExtension = "/calculateScores"+"/"+measurementId.toString();
    return this.http.get<any>(this.urlApi+this.components[2].toString()+methodExtension);
  }
}
