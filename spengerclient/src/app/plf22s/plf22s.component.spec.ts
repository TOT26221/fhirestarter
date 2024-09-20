import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PLF22sComponent } from './plf22s.component';

describe('PLF22sComponent', () => {
  let component: PLF22sComponent;
  let fixture: ComponentFixture<PLF22sComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PLF22sComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PLF22sComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
