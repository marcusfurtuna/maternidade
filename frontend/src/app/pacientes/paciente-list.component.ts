import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PacienteService, PacienteDTO } from '../core/paciente.service';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../core/auth.service';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  standalone: true,
  selector: 'app-paciente-list',
  imports: [CommonModule, RouterModule, FormsModule, MatListModule, MatButtonModule, MatIconModule, MatCardModule, MatFormFieldModule, MatInputModule],
  templateUrl: './paciente-list.component.html'
})
export class PacienteListComponent implements OnInit {
  pacientes: PacienteDTO[] = [];
  q = '';
  page = 0;
  size = 10;
  canEdit = false;
  constructor(private service: PacienteService, private auth: AuthService) {}
  ngOnInit() { this.load(); }
  load() { this.service.list(this.q, this.page, this.size).subscribe(p => { this.pacientes = p.content; this.canEdit = this.auth.hasAnyRole(['ADMIN','ATENDENTE']); }); }
  prev() { if (this.page>0) { this.page--; this.load(); } }
  next() { this.page++; this.load(); }
  remover(p: PacienteDTO) {
    if (!p.id) return;
    if (confirm('Excluir paciente?')) this.service.delete(p.id).subscribe(() => this.load());
  }
  exportCsv() { this.service.exportCsv().subscribe(blob => {
    const url = URL.createObjectURL(blob); const a = document.createElement('a'); a.href = url; a.download = 'pacientes.csv'; a.click(); URL.revokeObjectURL(url);
  }); }
}