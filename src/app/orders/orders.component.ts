import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { OrderService } from '../services/order.service'; // Assure-toi du chemin
import { Order } from '../models/order.model';
import { Product } from '../models/product.model'; // On réutilise ton modèle existant

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'] // Crée un fichier css vide si besoin
})
export class OrdersComponent implements OnInit {

  orders: Order[] = [];
  products: Product[] = []; // Pour la liste déroulante

  // L'objet pour la nouvelle commande
  newOrder: any = { productId: null, quantity: null };
  isLoading: boolean = false;

  constructor(private orderService: OrderService, private http: HttpClient) {}

  ngOnInit(): void {
    this.loadOrders();
    this.loadProducts(); // On charge aussi les produits
  }

  loadOrders() {
    this.orderService.getAllOrders().subscribe({
      next: (data) => this.orders = data,
      error: (err) => console.error("Erreur chargement commandes:", err)
    });
  }

  // On récupère les produits via la Gateway pour le formulaire
  loadProducts() {
    this.http.get<Product[]>("http://localhost:8080/products").subscribe({
      next: (data) => this.products = data,
      error: (err) => console.error("Erreur chargement produits:", err)
    });
  }

  saveOrder() {
    if (!this.newOrder.productId || !this.newOrder.quantity) {
      alert("Veuillez choisir un produit et une quantité !");
      return;
    }

    this.isLoading = true;

    this.orderService.createOrder(this.newOrder).subscribe({
      next: (data) => {
        // Ajout réussi
        this.orders.push(data); // Ajoute à la liste locale
        this.newOrder = { productId: null, quantity: null }; // Reset form
        this.isLoading = false;
        alert("Commande validée !");
      },
      error: (err) => {
        console.error("Erreur création commande:", err);
        this.isLoading = false;
        alert("Erreur lors de la commande (Vérifie le stock ou le backend)");
      }
    });
  }
}
