import { Routes } from '@angular/router';
import { AuthGuard } from './core/auth.guard';
import { RoleGuard } from './core/role.guard';

export const routes: Routes = [
  { path: 'login', loadComponent: () => import('./auth/login.component').then(m => m.LoginComponent) },
  { path: 'pacientes', canActivate: [AuthGuard], loadChildren: () => import('./pacientes/pacientes.routes').then(m => m.PACIENTES_ROUTES) },
  { path: 'admin', canActivate: [RoleGuard], data: { roles: ['ADMIN'] }, loadChildren: () => import('./admin/admin.routes').then(m => m.ADMIN_ROUTES) },
  { path: 'gestacoes/:id/cartao', canActivate: [AuthGuard], loadComponent: () => import('./gestacao/gestacao-cartao.component').then(m => m.GestacaoCartaoComponent) },
  { path: '', redirectTo: 'pacientes', pathMatch: 'full' }
];