import { CommonModule } from "@angular/common";
import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  OnChanges,
} from "@angular/core";
import { DataService } from "../data.service";
import { Encounter } from "../models/Encounter";
import { FormsModule } from "@angular/forms";
import e from "express";

@Component({
  selector: "app-encounter",
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: "./encounter.component.html",
  styleUrls: ["./encounter.component.scss"],
})
export class EncounterComponent implements OnInit, OnChanges {
  constructor(private service: DataService) {}

  @Input() id: string = "";
  @Output() encounterModified = new EventEmitter<boolean>();

  encounter: Encounter = new Encounter();
  statusOptions: string[] = ["planned", "arrived", "in-progress", "onleave", "finished", "cancelled"];

  ngOnInit(): void {
    this.getEncounter();
  }

  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    this.getEncounter();
  }

  getEncounter(): void {
    this.service.getEncounter(this.id).subscribe((data: Encounter) => {
      this.encounter = data;
    });
  }

  updateEnconter() {
    var newEncounter: Encounter = this.encounter;
    this.service.updateEncounter(newEncounter).subscribe((encounter) => {
      console.log("Encounter updated");
      this.encounter = encounter;
      this.encounterModified.emit(false);
    });
  }

  onSubmit(): void {
    this.service.updateEncounter(this.encounter).subscribe(() => {
      this.encounterModified.emit(true);
    });
  }

  deleteEncounter(): void {
    this.service.deleteEncounter(this.id).subscribe(() => {
      this.encounterModified.emit(true);
    });
  }
}