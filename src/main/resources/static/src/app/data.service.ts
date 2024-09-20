import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Patient } from "./models/Patient";
import { Observable, catchError, of } from "rxjs";
import { Practitioner } from "./models/Practitioner";
import { Organization } from "./models/Organization";
import { Contract } from "./models/Contract";
import { PLF22 } from './models/PLF22';
@Injectable({
  providedIn: "root",
})
export class DataService {
  constructor(private http: HttpClient) {}
  private patientUrl: string = "http://localhost:8080/api/patient/";
  private practitionerUrl: string = "http://localhost:8080/api/practitioner/"
  private OrganizationUrl: string = "http://localhost:8080/api/Organization/"
  private contractUrl: string = "http://localhost:8080/api/contract/"
  private plf22Url: string = 'http://localhost:8080/api/plf22/'

  public getPatients(): Observable<Patient[]> {
    return this.http
      .get<Patient[]>(this.patientUrl)
      .pipe(catchError(this.handleError("getPatientDetail", [])));
  }

  public getPatient(id: string): Observable<Patient> {
    return this.http
      .get<Patient>(this.patientUrl + id)
      .pipe(catchError(this.handleError("getPatientDetail", new Patient())));
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
//--
  public getOrganizations(): Observable<Organization[]> {
    return this.http
      .get<Organization[]>(this.OrganizationUrl)
      .pipe(catchError(this.handleError("getOrganizationDetail", [])));
  }

  public getOrganization(id: string): Observable<Organization> {
    return this.http
      .get<Organization>(this.OrganizationUrl + id)
      .pipe(catchError(this.handleError("getOrgansisationDetail", new Organization())));
  }
  
 
  public getPractitioners(): Observable<Practitioner[]> {
    return this.http
      .get<Practitioner[]>(this.practitionerUrl)
      .pipe(catchError(this.handleError("getPractitionerDetail", [])));
  }

  public getPractitioner(id: string): Observable<Practitioner> {
    return this.http
      .get<Practitioner>(this.practitionerUrl + id)
      .pipe(catchError(this.handleError("getPractitionerDetail", new Practitioner())));
  }
  
  public deletePractitioner(id: string): Observable<{}> {
    return this.http
      .delete(this.practitionerUrl + id)
      .pipe(catchError(this.handleError("delete Practitioner", new Practitioner())));
  }

  public addPractitioner(practitioner: Practitioner): Observable<Practitioner> {
    return this.http
      .post<Practitioner>(this.practitionerUrl, practitioner)
      .pipe(catchError(this.handleError("addPractitioner", practitioner)));
  }

  public updatePractitioner(practitioner: Practitioner): Observable<Practitioner> {
    return this.http
      .put<Practitioner>(this.practitionerUrl + "/" + practitioner.id, practitioner)
      .pipe(catchError(this.handleError("updatePractitioner", practitioner)));
  }

  public getContracts(): Observable<Contract[]> {
    return this.http
      .get<Contract[]>(this.contractUrl)
      .pipe(catchError(this.handleError("getContractDetail", [])));
  }

  public getContract(id: string): Observable<Contract> {
    return this.http
      .get<Contract>(this.contractUrl + id)
      .pipe(catchError(this.handleError("getContractDetail", new Contract())));
  }

  private handleError<T>(operation = "operation", result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as any);
    };
  }
  public getAllPLF22s(): Observable<PLF22[]>{
    return this.http
        .get<PLF22[]>(this.plf22Url)
        .pipe(catchError(this.handleError('getPLF2Detail', [])))
    }
}
