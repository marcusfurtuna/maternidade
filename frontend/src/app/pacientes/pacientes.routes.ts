import { Routes } from '@angular/router';
import { PacienteListComponent } from './paciente-list.component';
import { PacienteDetailComponent } from './paciente-detail.component';
import { PacienteFormComponent } from './paciente-form.component';
import { RoleGuard } from '../core/role.guard';

export const PACIENTES_ROUTES: Routes = [
  { path: '', loadComponent: () => import('./paciente-list.component').then(m => m.PacienteListComponent) },
  { path: 'novo', canActivate: [RoleGuard], data: { roles: ['ADMIN','ATENDENTE'] }, loadComponent: () => import('./paciente-form.component').then(m => m.PacienteFormComponent) },
  { path: ':id/editar', canActivate: [RoleGuard], data: { roles: ['ADMIN','ATENDENTE'] }, loadComponent: () => import('./paciente-edit.component').then(m => m.PacienteEditComponent) },
  { path: ':id', loadComponent: () => import('./paciente-detail.component').then(m => m.PacienteDetailComponent) }
];