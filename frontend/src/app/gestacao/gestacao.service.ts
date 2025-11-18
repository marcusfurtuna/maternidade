import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class GestacaoService {
  constructor(private http: HttpClient) {}
  listByPaciente(pacienteId: number) { return this.http.get<any[]>(`/api/pacientes/${pacienteId}/gestacoes`); }
  getCartao(gestacaoId: number) { return this.http.get<any>(`/api/gestacoes/${gestacaoId}/cartao`); }
}