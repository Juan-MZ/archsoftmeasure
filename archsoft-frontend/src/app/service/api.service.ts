import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private urlApi = 'http://localhost:8080';
  private components = ["/section","/test","/measurement","/metric"];
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getMetrics()
  }

  public getMetrics() : Observable<any> {
    let methodExtension = "/getAllSections";
    return this.http.get<any>(this.urlApi+this.components[0].toString()+methodExtension);
  }

  public createMeasurement(correo: string) : Observable<any> {
    let methodExtension = "/createMeasurement"+"/"+correo+"/1";
    // @ts-ignore
    return this.http.post<any>(this.urlApi+this.components[2].toString()+methodExtension);
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
