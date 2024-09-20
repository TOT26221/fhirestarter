import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PLF22Component } from './plf22.component';

describe('PLF22Component', () => {
  let component: PLF22Component;
  let fixture: ComponentFixture<PLF22Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PLF22Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PLF22Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
