import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private tokenKey = 'token';
  constructor(private http: HttpClient) {}
  login(username: string, password: string) {
    return this.http.post<{ token: string }>("/api/auth/login", { username, password }).pipe(tap(res => localStorage.setItem(this.tokenKey, res.token)));
  }
  logout() { localStorage.removeItem(this.tokenKey); }
  getToken() { return localStorage.getItem(this.tokenKey); }
  isAuthenticated() { return !!this.getToken(); }
  getRoles(): string[] {
    const token = this.getToken();
    if (!token) return [];
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return (payload.roles || []) as string[];
    } catch { return []; }
  }
  hasAnyRole(roles: string[]): boolean {
    const userRoles = this.getRoles();
    return roles.some(r => userRoles.includes('ROLE_' + r));
  }
}