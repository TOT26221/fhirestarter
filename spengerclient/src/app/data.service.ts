import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { catchError } from "rxjs/operators";
import { Patient} from "./models/Patient";
import { Practitioner} from "./models/Practitioner";
import { Organization } from "./models/Organization";
import { Contract } from "./models/Contract";
import { Encounter } from "./models/Encounter";
import e from "express";

@Injectable({
  providedIn: "root",
})
export class DataService {
  private patientUrl = "http://localhost:8080/api/patient/";
  private practitionerUrl: string = "http://localhost:8080/api/practitioner/";
  private organisationUrl: string = "http://localhost:8080/api/organisation/";
  private contractUrl: string = "http://localhost:8080/api/contract/";
  private encounterUrl: string = "http://localhost:8080/api/encounter/";

  constructor(private http: HttpClient) {}

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
      .pipe(
        catchError(this.handleError("getPractitioner", new Practitioner()))
      );
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

  public updatePractitioner(
    practitioner: Practitioner
  ): Observable<Practitioner> {
    return this.http
      .put<Practitioner>(this.practitionerUrl + practitioner.id, practitioner)
      .pipe(catchError(this.handleError("updatePractitioner", practitioner)));
  }
  /*
  public getOrganisations(): Observable<Organization[]> {
    return this.http
      .get<Organization[]>(this.organisationUrl)
      .pipe(catchError(this.handleError<Organization[]>("getOrganisations", [])));
  }

  public getOrganisation(id: string): Observable<Organization> {
    return this.http
      .get<Organization>(`${this.organisationUrl}${id}`)
      .pipe(catchError(this.handleError<Organization>("getOrganisation", new Organization())));
  }

  public deleteOrganisation(id: string): Observable<{}> {
    return this.http
      .delete(this.organisationUrl + id)
      .pipe(catchError(this.handleError("delete Organisation", new Organization())));
  }

  public addOrganisation(organisation: Organization): Observable<Organization> {
    return this.http
      .post<Organization>(this.organisationUrl, organisation)
      .pipe(catchError(this.handleError("addOrganisation", organisation)));
  }

  public updateOrganisation(organisation: Organization): Observable<Organization> {
    return this.http
      .put<Organization>(this.organisationUrl + "/" + organisation.id, organisation)
      .pipe(catchError(this.handleError("updateOrganisation", organisation)));
  }*/

  public getContracts(): Observable<Contract[]> {
    return this.http
      .get<Contract[]>(this.contractUrl)
      .pipe(catchError(this.handleError<Contract[]>("getContracts", [])));
  }

  public getContract(id: string): Observable<Contract> {
    return this.http
      .get<Contract>(`${this.contractUrl}${id}`)
      .pipe(catchError(this.handleError<Contract>("getContract", new Contract())));
  }

  public deleteContract(id: string): Observable<{}> {
    return this.http
      .delete(this.contractUrl + id)
      .pipe(catchError(this.handleError("delete Contract", new Contract())));
  }

  public addContract(contract: Contract): Observable<Contract> {
    return this.http
      .post<Contract>(this.contractUrl, contract)
      .pipe(catchError(this.handleError("addContract", contract)));
  }

  public updateContract(contract: Contract): Observable<Contract> {
    return this.http
      .put<Contract>(this.contractUrl + contract.id, contract)
      .pipe(catchError(this.handleError("updateContract", contract)));
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

  public updateEncounter(
    encounter: Encounter
  ): Observable<Encounter> {
    return this.http
      .put<Encounter>(this.encounterUrl + encounter.id, encounter)
      .pipe(catchError(this.handleError("updateEncounter",  encounter)));
  }

  private handleError<T>(operation = "operation", result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as any);
    };
  }


}
