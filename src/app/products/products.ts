import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Product } from '../models/product.model';
import { finalize } from 'rxjs/operators';
import { Observable } from 'rxjs'; // On ajoute ça pour simplifier le code

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './products.html',
  styleUrl: './products.css'
})
export class ProductsComponent implements OnInit {

  products: Product[] = [];
  newProduct: any = { id: null, name: '', price: null, quantity: null };
  isLoading: boolean = false;

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.getAllProducts();
  }

  getAllProducts() {
    this.http.get<Product[]>("http://localhost:8081/products")
      .subscribe({
        next: (data) => this.products = data,
        error: (err) => console.error("Erreur chargement:", err)
      });
  }

  editProduct(p: Product): void {
    this.newProduct = { ...p };
    window.scrollTo(0, 0); // Remonte la page
  }

  saveProduct(): void {
    // 1. Vérifications
    if (this.isLoading) return;
    if (!this.newProduct.name || !this.newProduct.price || !this.newProduct.quantity) {
      alert("Veuillez remplir tous les champs !");
      return;
    }

    this.isLoading = true;
    let request: Observable<Product>;

    // 2. On décide si c'est un AJOUT (POST) ou une MODIF (PUT)
    if (this.newProduct.id) {
      // Modif
      request = this.http.put<Product>("http://localhost:8081/products/" + this.newProduct.id, this.newProduct);
    } else {
      // Ajout
      request = this.http.post<Product>("http://localhost:8081/products", this.newProduct);
    }

    // 3. Exécution COMMUNE (La solution au bug)
    request
      .pipe(
        finalize(() => {
          // Ce code s'exécute QUOI QU'IL ARRIVE (Succès ou Erreur)
          this.isLoading = false;
          this.cdr.detectChanges(); // <-- C'est ça qui débloque le bouton
          console.log("Opération terminée, bouton débloqué.");
        })
      )
      .subscribe({
        next: (data) => {
          if (this.newProduct.id) {
            // Si c'était une modif, on met à jour la ligne dans le tableau
            const index = this.products.findIndex(p => p.id === data.id);
            this.products[index] = data;
          } else {
            // Si c'était un ajout, on ajoute à la liste
            this.products = [...this.products, data];
          }
          this.resetForm();
        },
        error: (err) => {
          console.error("Erreur backend:", err);
          alert("Une erreur est survenue (Vérifie que ton backend gère bien le PUT)");
        }
      });
  }

  deleteProduct(p: Product): void {
    if(!confirm("Supprimer " + p.name + " ?")) return;

    this.http.delete("http://localhost:8081/products/" + p.id)
      .subscribe({
        next: () => {
          this.products = this.products.filter(prod => prod.id !== p.id);
          this.cdr.detectChanges();
        },
        error: (err) => console.error("Erreur suppression:", err)
      });
  }

  resetForm(): void {
    this.newProduct = { id: null, name: '', price: null, quantity: null };
  }
}
