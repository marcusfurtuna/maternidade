import { Component } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { AuthService } from '../core/auth.service';

@Component({
  standalone: true,
  selector: 'app-login',
  imports: [ReactiveFormsModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  form = this.fb.group({ username: ['', Validators.required], password: ['', Validators.required] });
  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router) {}
  submit() {
    if (this.form.invalid) return;
    const { username, password } = this.form.value;
    this.auth.login(username!, password!).subscribe(() => this.router.navigate(['/']));
  }
}