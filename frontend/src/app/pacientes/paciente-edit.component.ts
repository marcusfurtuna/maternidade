import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { PacienteService, PacienteDTO } from '../core/paciente.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  standalone: true,
  selector: 'app-paciente-edit',
  imports: [ReactiveFormsModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './paciente-edit.component.html'
})
export class PacienteEditComponent implements OnInit {
  id!: number;
  form = this.fb.group({
    nome: ['', Validators.required],
    cpf: [''],
    email: [''],
    telefone: [''],
    endereco: ['']
  });
  constructor(private fb: FormBuilder, private service: PacienteService, private route: ActivatedRoute, private router: Router) {}
  ngOnInit() {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.get(this.id).subscribe(p => this.form.patchValue(p));
  }
  salvar() {
    if (this.form.invalid) return;
    this.service.update(this.id, this.form.value as PacienteDTO).subscribe(() => this.router.navigate(['/pacientes', this.id]));
  }
}