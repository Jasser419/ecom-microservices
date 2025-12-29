import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';
// On supprime l'import de ProductsComponent car on passe par les routes

@Component({
  selector: 'app-root',
  standalone: true,
  // On importe RouterModule pour que les liens "routerLink" du menu fonctionnent
  imports: [RouterOutlet, RouterModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('ecom-app');
}
