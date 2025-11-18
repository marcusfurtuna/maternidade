import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { PacienteService, PacienteDTO } from '../core/paciente.service';
import { AuthService } from '../core/auth.service';
import { GestacaoService } from '../gestacao/gestacao.service';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';

@Component({
  standalone: true,
  selector: 'app-paciente-detail',
  imports: [CommonModule, RouterLink, MatCardModule, MatListModule, MatButtonModule],
  templateUrl: './paciente-detail.component.html'
})
export class PacienteDetailComponent implements OnInit {
  paciente?: PacienteDTO;
  canEdit = false;
  gestacoes: any[] = [];
  constructor(private route: ActivatedRoute, private service: PacienteService, private auth: AuthService, private gestacaoService: GestacaoService) {}
  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.get(id).subscribe(p => { this.paciente = p; this.canEdit = this.auth.hasAnyRole(['ADMIN','ATENDENTE']); });
    this.gestacaoService.listByPaciente(id).subscribe(gs => this.gestacoes = gs);
  }
  remover() {
    if (!this.paciente?.id) return;
    if (confirm('Excluir paciente?')) this.service.delete(this.paciente.id).subscribe(() => history.back());
  }
}