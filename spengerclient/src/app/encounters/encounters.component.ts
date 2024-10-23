import { Component, OnInit } from "@angular/core";
import { DataService } from "../data.service";
import { CommonModule } from "@angular/common";
import { Encounter } from "../models/Encounter";
import { EncounterComponent } from "../encounter/encounter.component";

@Component({
  selector: "app-encounters",
  standalone: true,
  imports: [CommonModule, EncounterComponent],
  templateUrl: "./encounters.component.html",
  styleUrls: ["./encounters.component.scss"],
})
export class EncountersComponent implements OnInit {
  constructor(private service: DataService) {}
  encounterArr$: Encounter[] = [];
  selectedEncounter: Encounter = new Encounter();

  ngOnInit(): void {
    this.getEncounters();
  }

  getEncounters() {
    this.service.getEncounters().subscribe((data: Encounter[]) => {
      console.log(data);
      this.encounterArr$ = data;
    });
  }

  selectEncounter(selected: Encounter) {
    console.log("clicked " + selected.id);
    this.selectedEncounter = selected;
  }

  onEncounterModified(hideEncounter: boolean) {
    console.log("Encounter modified " + hideEncounter);
    if (hideEncounter) {
      this.selectedEncounter = new Encounter();
    }
    this.getEncounters();
  }

  createEncounter() {
    var newEncounter: Encounter = new Encounter();
    this.service.addEncounter(newEncounter).subscribe((Encounter) => {
      console.log("Encounter created");
      this.onEncounterModified(true);
    });
  }
}
