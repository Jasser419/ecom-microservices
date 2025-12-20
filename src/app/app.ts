import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
// Correction 1 : Le chemin correspond à ton fichier "products.ts"
import { ProductsComponent } from './products/products';

@Component({
  selector: 'app-root',
  // Correction 2 : On ajoute ProductsComponent ici pour pouvoir l'utiliser dans le HTML
  imports: [RouterOutlet, ProductsComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('ecom-app');
}
