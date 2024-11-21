import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { Patient } from "./models/Patient";
import { Practitioner } from "./models/Practitioner";
import { Encounter } from "./models/Encounter";
import { Medication } from "./models/Medication";

@Injectable({
  providedIn: "root",
})
export class DataService {
  private patientUrl = "http://localhost:8080/api/patient/";
  private practitionerUrl: string = "http://localhost:8080/api/practitioner/";
  private encounterUrl: string = "http://localhost:8080/api/encounter/";
  private medicationUrl: string = "http://localhost:8080/api/medication/";

  constructor(private http: HttpClient) { }

  public getPatients(): Observable<Patient[]> {
    return this.http
      .get<Patient[]>(this.patientUrl)
      .pipe(catchError(this.handleError<Patient[]>("getPatients", [])));
  }

  public getPatient(id: string): Observable<Patient> {
    return this.http
      .get<Patient>(`${this.patientUrl}${id}`)
      .pipe(catchError(this.handleError<Patient>("getPatient", new Patient())));
  }

  public deletePatient(id: string): Observable<{}> {
    return this.http
      .delete(this.patientUrl + id)
      .pipe(catchError(this.handleError("delete Patient", new Patient())));
  }

  public addPatient(patient: Patient): Observable<Patient> {
    return this.http
      .post<Patient>(this.patientUrl, patient)
      .pipe(catchError(this.handleError("addPatient", patient)));
  }

  public updatePatient(patient: Patient): Observable<Patient> {
    return this.http
      .put<Patient>(this.patientUrl + "/" + patient.id, patient)
      .pipe(catchError(this.handleError("updatePatient", patient)));
  }

  public getPractitioners(): Observable<Practitioner[]> {
    return this.http
      .get<Practitioner[]>(this.practitionerUrl)
      .pipe(catchError(this.handleError("getPractitioners", [])));
  }

  public getPractitioner(id: string): Observable<Practitioner> {
    return this.http
      .get<Practitioner>(this.practitionerUrl + id)
      .pipe(catchError(this.handleError("getPractitioner", new Practitioner())));
  }

  public deletePractitioner(id: string): Observable<{}> {
    return this.http
      .delete(this.practitionerUrl + id)
      .pipe(catchError(this.handleError("deletePractitioner", {})));
  }

  public addPractitioner(practitioner: Practitioner): Observable<Practitioner> {
    return this.http
      .post<Practitioner>(this.practitionerUrl, practitioner)
      .pipe(catchError(this.handleError("addPractitioner", practitioner)));
  }

  public updatePractitioner(practitioner: Practitioner): Observable<Practitioner> {
    return this.http
      .put<Practitioner>(this.practitionerUrl + practitioner.id, practitioner)
      .pipe(catchError(this.handleError("updatePractitioner", practitioner)));
  }

  public getEncounters(): Observable<Encounter[]> {
    return this.http
      .get<Encounter[]>(this.encounterUrl)
      .pipe(catchError(this.handleError<Encounter[]>("getEncounters", [])));
  }

  public getEncounter(id: string): Observable<Encounter> {
    return this.http
      .get<Encounter>(`${this.encounterUrl}${id}`)
      .pipe(catchError(this.handleError<Encounter>("getEncounter", new Encounter())));
  }

  public deleteEncounter(id: string): Observable<{}> {
    return this.http
      .delete(this.encounterUrl + id)
      .pipe(catchError(this.handleError("delete Encounter", new Encounter())));
  }

  public addEncounter(encounter: Encounter): Observable<Encounter> {
    return this.http
      .post<Encounter>(this.encounterUrl, encounter)
      .pipe(catchError(this.handleError("addEncounter", encounter)));
  }

  public updateEncounter(encounter: Encounter): Observable<Encounter> {
    return this.http
      .put<Encounter>(this.encounterUrl + encounter.id, encounter)
      .pipe(catchError(this.handleError("updateEncounter", encounter)));
  }

  public addMedication(medication: Medication): Observable<Medication> {
    return this.http
      .post<Medication>(this.medicationUrl, medication)
      .pipe(catchError(this.handleError('addMedication', medication)));
  }

  public updateMedication(medication: Medication): Observable<Medication> {
    return this.http
      .put<Medication>(this.medicationUrl + medication.id, medication)
      .pipe(catchError(this.handleError('updateMedication', medication)));
  }

  public getMedications(): Observable<Medication[]> {
    return this.http
      .get<Medication[]>(this.medicationUrl)
      .pipe(catchError(this.handleError<Medication[]>('getMedications', [])));
  }

  public getMedication(id: string): Observable<Medication> {
    return this.http
      .get<Medication>(`${this.medicationUrl}${id}`)
      .pipe(catchError(this.handleError<Medication>('getMedication', new Medication())));
  }

  public deleteMedication(id: string): Observable<{}> {
    return this.http
      .delete(this.medicationUrl + id)
      .pipe(catchError(this.handleError('deleteMedication', new Medication())));
  }

  private handleError<T>(operation = "operation", result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as any);
    };
  }
}
