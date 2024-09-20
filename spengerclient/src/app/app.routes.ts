import { Routes } from '@angular/router';
import { PatientsComponent } from './patients/patients.component';
import { PractitionersComponent } from './practitioners/practitioners.component';
import { OrganizationsComponent } from './organizations/organizations.component';
import { ContractsComponent } from './contracts/contracts.component';
import { PLF22Component } from './plf22/plf22.component';
import { Plf22sComponent } from './plf22s/plf22s.component';

export const routes: Routes = [
    {
        path:'',
        title:"Patienten",
        component: PatientsComponent
        },
        {
        path:'patients',
        title:"Patienten",
        component: PatientsComponent
    },
    {
        path:'',
        title:"Practitioners",
        component: PractitionersComponent
        },
        {
        path:'practitioners',
        title:"Practitioners",
        component: PractitionersComponent
    },
    {
        path:'',
        title:"Organizations",
        component: OrganizationsComponent
        },
        {
        path:'organizations',
        title:"Organizations",
        component: OrganizationsComponent
    },
    {
        path:'',
        title:"Contracts",
        component: ContractsComponent
        },
        {
        path:'contracts',
        title:"Contracts",
        component: ContractsComponent
    },
    {
        path:'',
        title:"PLF22",
        component: Plf22sComponent
        },
        {
        path:'plf22s',
        title:"PLF22s",
        component: Plf22sComponent
    }
];
