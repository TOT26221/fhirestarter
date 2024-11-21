import { CommonModule } from "@angular/common";
import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  OnChanges,
} from "@angular/core";
import { Practitioner, HumanName, Qualification} from "../models/Practitioner";
import { DataService } from "../data.service";
import { FormsModule } from "@angular/forms";

@Component({
  selector: "app-practitioner",
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: "./practitioner.component.html",
  styleUrl: "./practitioner.component.scss",
})
export class PractitionerComponent implements OnInit, OnChanges {
  constructor(private service: DataService) {}

  @Input()
  id: string = "";

  @Output()
  practitionerModified = new EventEmitter<boolean>();
  practitioner: Practitioner = new Practitioner();
  humanNameUse = HumanName.useCode;
  practitionerGender = Practitioner.genderCode;

  ngOnInit(): void {
    this.getPractitioner();
  }

  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    this.getPractitioner();
  }

  getPractitioner() {
    this.service.getPractitioner(this.id).subscribe((data: Practitioner) => {
      console.log(data);
      this.practitioner = data;
    });
  }

  deletePractitioner() {
    this.service
      .deletePractitioner(this.practitioner.id!) //! => Promise, that it will not be null
      .subscribe((x) => this.practitionerModified.emit(true));
  }

  updatePractitioner() {
    var newPractitioner: Practitioner = this.practitioner;
    this.service.updatePractitioner(newPractitioner).subscribe((practitioner) => {
      console.log("Practitioner updated");
      this.practitioner = practitioner;
      this.practitionerModified.emit(false);
    });
  }

  addName() {
    this.practitioner.name?.push(new HumanName());
  }

  deleteName(name: HumanName) {
    const index = this.practitioner.name?.indexOf(name, 0);
    if (index !== undefined && index > -1) {
      this.practitioner.name?.splice(index, 1);
    }
  }

  addQualification() {
    this.practitioner.qualification?.push(new Qualification());
  }

  deleteQualification(qualification: Qualification) {
    const index = this.practitioner.qualification?.indexOf(qualification, 0);
    if (index !== undefined && index > -1) {
      this.practitioner.name?.splice(index, 1);
    }
  }
}
