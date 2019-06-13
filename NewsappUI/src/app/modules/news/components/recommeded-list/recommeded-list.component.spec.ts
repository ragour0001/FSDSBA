import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommededListComponent } from './recommeded-list.component';

xdescribe('RecommededListComponent', () => {
  let component: RecommededListComponent;
  let fixture: ComponentFixture<RecommededListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommededListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommededListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
