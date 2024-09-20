import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { PLF22 } from '../models/PLF22';
import { DataService } from '../data.service';

@Component({
  selector: 'app-plf22s',
  standalone: true,
  imports: [CommonModule, Plf22sComponent],
  templateUrl: './plf22s.component.html',
  styleUrl: './plf22s.component.scss'
})
export class Plf22sComponent {
  constructor(private service: DataService) { }
  plf2Arr$: PLF22[] = [];
  ngOnInit(): void {
      this.getPLF2();
  }
  getPLF2() {
      this.service.getAllPLF22s()
          .subscribe((data: PLF22[]) => {
              console.log(data);
              this.plf2Arr$ = data
          })
  }
}
