import { CommonModule } from "@angular/common";
import { Component , OnInit, Input, Output, EventEmitter, OnChanges} from '@angular/core';
import { DataService } from '../data.service';
import { Contract } from '../models/Contract';



@Component({
  selector: "app-patient",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "./contract.component.html",
  styleUrl: "./contract.component.scss",
})
export class ContractComponent implements OnInit, OnChanges {
  constructor(private service: DataService) {}
  //Input parameter from patient list, which patient details should be displayed;
  @Input()
  id: string = "";
  //Notify the parent View to refresh the list
  @Output()
  patientModified = new EventEmitter<boolean>();
  patient: Contract = new Contract();

  ngOnInit(): void {
    this.getContract();
  }

  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    this.getContract();
  }

  getContract() {
    this.service.getContract(this.id).subscribe((data: Contract) => {
      console.log(data);
      this.patient = data;
    });
  }

  deleteContract() {
    this.service.deleteContract(this.patient.id!) //! => Promise, that it will not be null
    .subscribe((x) => this.patientModified.emit(true));
  }

  updateContract() {
    var newContract: Contract = this.patient;
    this.service.updateContract(newContract).subscribe((patient) => {
      console.log("Contract updated");
      this.patient = patient;
      this.patientModified.emit(false);
    });
  }
}
