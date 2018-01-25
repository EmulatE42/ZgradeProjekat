import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MakesurveyComponent } from './makesurvey.component';

describe('MakesurveyComponent', () => {
  let component: MakesurveyComponent;
  let fixture: ComponentFixture<MakesurveyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MakesurveyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MakesurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
