import { CommonModule } from "@angular/common";
import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
} from "@angular/core";
import { DataService } from "../data.service";
import { Medication } from "../models/Medication";
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-medication',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './medication.component.html',
  styleUrl: './medication.component.scss'
})
export class MedicationComponent {
  constructor(private service: DataService) {}

  @Input() id: string = "";
  @Output() MedicationModified = new EventEmitter<boolean>();

  medication: Medication = new Medication();

  ngOnInit(): void {
    this.getMedication();
  }

  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    this.getMedication();
  }

  getMedication(): void {
    this.service.getMedication(this.id).subscribe((data: Medication) => {
      this.medication = data;
    });
  }

  updateMedication() {
    var newMedication: Medication = this.medication;
    this.service.updateMedication(newMedication).subscribe((Medication) => {
      console.log("Medication updated");
      this.medication = Medication;
      this.MedicationModified.emit(false);
    });
  }

  onSubmit(): void {
    this.service.updateMedication(this.medication).subscribe(() => {
      this.MedicationModified.emit(true);
    });
  }

  deleteMedication(): void {
    this.service.deleteMedication(this.id).subscribe(() => {
      this.MedicationModified.emit(true);
    });
  }
}
