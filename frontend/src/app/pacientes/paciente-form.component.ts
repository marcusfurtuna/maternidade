import { Component } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { PacienteService } from '../core/paciente.service';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  standalone: true,
  selector: 'app-paciente-form',
  imports: [ReactiveFormsModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './paciente-form.component.html'
})
export class PacienteFormComponent {
  form = this.fb.group({
    nome: ['', Validators.required],
    cpf: ['', Validators.pattern(/^[0-9]{11}$/)],
    email: ['']
  });
  constructor(private fb: FormBuilder, private service: PacienteService, private router: Router) {}
  salvar() {
    if (this.form.invalid) return;
    this.service.create(this.form.value).subscribe(() => this.router.navigate(['/pacientes']));
  }
}