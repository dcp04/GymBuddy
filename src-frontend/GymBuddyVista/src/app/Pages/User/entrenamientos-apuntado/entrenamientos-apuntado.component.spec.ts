import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntrenamientosApuntadoComponent } from './entrenamientos-apuntado.component';

describe('EntrenamientosApuntadoComponent', () => {
  let component: EntrenamientosApuntadoComponent;
  let fixture: ComponentFixture<EntrenamientosApuntadoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntrenamientosApuntadoComponent]
    });
    fixture = TestBed.createComponent(EntrenamientosApuntadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
