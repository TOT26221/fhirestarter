import { Component, OnInit } from "@angular/core";
import { DataService } from "../data.service";
import { Contract } from "../models/Contract";
import { CommonModule } from "@angular/common";

@Component({
  selector: "app-contracts",
  standalone: true,
  imports: [CommonModule,], //!!!
  templateUrl: "./contracts.component.html",
  styleUrl: "./contracts.component.scss",
})

export class ContractsComponent implements OnInit {
  constructor(private service: DataService) {}
  contractArr$: Contract[] = [];
  selectedContract: Contract = new Contract();

  ngOnInit(): void {
    this.getContract();
  }

  getContract() {
    this.service.getContracts().subscribe((data: Contract[]) => {
      console.log(data);
      this.contractArr$ = data;
    });
  }

  selectContract(selected: Contract) {
    console.log("clicked Contract" + selected.id);
    this.selectedContract = selected;
  }

  onContractModified(hideContract: boolean) {
    console.log("Contract modified " + hideContract);
    if (hideContract) {
      this.selectedContract = new Contract();
    }
    this.getContract();
  }
}
