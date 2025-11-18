import { Routes } from '@angular/router';
import { UsuarioListComponent } from './usuario-list.component';

export const ADMIN_ROUTES: Routes = [
  { path: 'usuarios', loadComponent: () => import('./usuario-list.component').then(m => UsuarioListComponent) },
  { path: '', redirectTo: 'usuarios', pathMatch: 'full' }
];