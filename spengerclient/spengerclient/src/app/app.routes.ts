import { Routes } from '@angular/router';
import { PatientsComponent } from './patients/patients.component';
import { PractitionersComponent } from './practitioners/practitioners.component'; // Import the practitioners component
//import { OrganisationComponent } from './organisations/organisations.component';
import { ContractsComponent } from './contracts/contracts.component';
import { EncounterComponent } from './encounter/encounter.component';
import { EncountersComponent } from './encounters/encounters.component';
import { MedicationComponent } from './medication/medication.component';
import { MedicationsComponent } from './medications/medications.component';


export const routes: Routes = [
  {
    path: '',
    title: 'Patienten',
    component: PatientsComponent,
  },
  {
    path: 'patients',
    title: 'Patienten',
    component: PatientsComponent,
  },
  {
    path: 'practitioners',
    title: 'Practitioners',
    component: PractitionersComponent, // Add the route for practitioners
  },
  /*{
    path: 'organisations',
    title: 'Organisations',
    component: OrganisationComponent, // Add the route for practitioners
  },*/
  {
    path: 'contracts',
    title: 'Contract',
    component: ContractsComponent,
  },
  {
    path: 'encounters',
    title: 'Encounters',
    component: EncountersComponent,
  },
  {
    path: 'encounter',
    title: 'Encounter',
    component: EncounterComponent,
  },
  {
    path: 'medication',
    title: 'Medication',
    component: MedicationComponent,
  },
  {
    path: 'medications',
    title: 'Medications',
    component: MedicationsComponent,
  },
];
