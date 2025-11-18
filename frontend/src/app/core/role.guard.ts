import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';

export const RoleGuard = (route: ActivatedRouteSnapshot) => {
  const auth = inject(AuthService);
  const router = inject(Router);
  const token = auth.getToken();
  const roles = route.data['roles'] as string[];
  if (!token) { router.navigate(['/login']); return false; }
  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    const userRoles: string[] = payload.roles || [];
    if (roles.some(r => userRoles.includes('ROLE_' + r))) return true;
  } catch { }
  router.navigate(['/']);
  return false;
};