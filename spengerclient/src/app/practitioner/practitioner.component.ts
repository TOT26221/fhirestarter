import { CommonModule } from "@angular/common";
import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  OnChanges,
} from "@angular/core";
import { Practitioner } from "../models/Practitioner";
import { DataService } from "../data.service";

@Component({
  selector: "app-practitioner",
  standalone: true,
  imports: [CommonModule],
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
}
