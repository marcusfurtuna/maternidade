import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

export interface PacienteDTO {
  id?: number;
  nome: string;
  cpf: string;
  email?: string;
  telefone?: string;
  endereco?: string;
  tipoSanguineo?: string;
  alergias?: string;
  comorbidades?: string;
  statusGestacao?: string;
  dataNascimento?: string;
}

@Injectable({ providedIn: 'root' })
export class PacienteService {
  constructor(private http: HttpClient) {}
  list(q: string, page: number, size: number) {
    let params = new HttpParams().set('page', page).set('size', size);
    if (q) params = params.set('q', q);
    return this.http.get<{ content: PacienteDTO[] }>(`/api/pacientes`, { params });
  }
  get(id: number) { return this.http.get<PacienteDTO>(`/api/pacientes/${id}`); }
  create(dto: any) { return this.http.post<PacienteDTO>(`/api/pacientes`, dto); }
  update(id: number, dto: any) { return this.http.put<PacienteDTO>(`/api/pacientes/${id}`, dto); }
  delete(id: number) { return this.http.delete<void>(`/api/pacientes/${id}`); }
  exportCsv() { return this.http.get(`/api/pacientes/export`, { responseType: 'blob' }); }
}