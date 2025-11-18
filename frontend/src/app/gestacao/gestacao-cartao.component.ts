import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { GestacaoService } from './gestacao.service';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';

@Component({
  standalone: true,
  selector: 'app-gestacao-cartao',
  imports: [CommonModule, MatCardModule, MatListModule],
  templateUrl: './gestacao-cartao.component.html'
})
export class GestacaoCartaoComponent implements OnInit {
  cartao: any;
  constructor(private route: ActivatedRoute, private service: GestacaoService) {}
  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.getCartao(id).subscribe(c => this.cartao = c);
  }
}