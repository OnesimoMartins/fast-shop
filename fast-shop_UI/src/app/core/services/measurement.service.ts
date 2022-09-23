import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, from } from 'rxjs';
import { MeasurementUnitie } from '../http/Responses';

@Injectable({
  providedIn: 'root',
})
export class MeasurementService {
  private url = 'http://localhost:8080/measurement-unities'

  constructor(private http: HttpClient) {}

  getAllMeasurements(): Observable<MeasurementUnitie[]> {
    return from(
      fetch(this.url, {
        method: 'get',
        headers: {
          'Content-Type': 'application/json',
        },
      }).then((res) => res.json())
    );
  }
}
