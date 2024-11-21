import { Component, OnInit } from "@angular/core";
import { DataService } from "../data.service";
import { CommonModule } from "@angular/common";
import { Medication } from "../models/Medication";
import { MedicationComponent } from "../medication/medication.component";

@Component({
  selector: 'app-medications',
  standalone: true,
  imports: [CommonModule, MedicationComponent],
  templateUrl: './medications.component.html',
  styleUrls: ['./medications.component.scss']
})
export class MedicationsComponent implements OnInit {
  medications: Medication[] = [];
  medicationArr$: Medication[] = [];
  selectedMedication: Medication | null = null;

  constructor(private service: DataService) {}

  ngOnInit(): void {
    this.getMedications();
  }

  getMedications() {
    this.service.getMedications().subscribe((data: Medication[]) => {
      console.log(data);
      this.medicationArr$ = data;
    });
  }

  selectMedication(selected: Medication) {
    console.log("clicked " + selected.id);
    this.selectedMedication = selected;
  }

  onMedicationModified(hideMedication: boolean) {
    console.log("Medication modified " + hideMedication);
    if (hideMedication) {
      this.selectedMedication = new Medication();
    }
    this.getMedications();
  }

  createMedication() {
    var newMedication: Medication = new Medication();
    this.service.addMedication(newMedication).subscribe((Medication) => {
      console.log("Medication created");
      this.onMedicationModified(true);
    });
  }
}