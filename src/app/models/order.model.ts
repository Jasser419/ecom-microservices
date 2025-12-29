export interface Order {
  id: number;
  productId: number;
  quantity: number;
  date?: string;     // Optionnel, dépend de ton backend
  status?: string;   // Optionnel, ex: PENDING, CONFIRMED
  productName?: string; // Pour l'affichage si ton backend renvoie les détails du produit
}
