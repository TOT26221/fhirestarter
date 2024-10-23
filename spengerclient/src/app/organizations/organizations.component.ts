import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Organization } from '../models/Organization';
import { CommonModule } from '@angular/common';
import { OrganizationComponent } from '../organization/organization.component';

@Component({
  selector: 'app-Organizations',
  standalone: true,
  imports: [CommonModule, OrganizationComponent],
  templateUrl: './organizations.component.html',
  styleUrl: './organizations.component.scss'
})
export class OrganizationsComponent implements OnInit{
  constructor(private service: DataService) {}
  OrganizationArr$: Organization[] = [];
  selectedOrganization: Organization = new Organization();

  ngOnInit(): void {
    this.getOrganization();
  }

  getOrganization() {
    this.service.getOrganizations().subscribe((data: Organization[]) => {
      console.log(data);
      this.OrganizationArr$ = data;
    });
  }

  selectOrganization(selected: Organization) {
    console.log("clicked Organization");
    this.selectedOrganization = selected;
  }
}
