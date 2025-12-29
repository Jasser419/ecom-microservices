import { Routes } from '@angular/router';
// Attention : on importe depuis 'products.ts' car c'est le nom de ton fichier
import { ProductsComponent } from './products/products';
import { OrdersComponent } from './orders/orders.component';

export const routes: Routes = [
  { path: 'products', component: ProductsComponent },
  { path: 'orders', component: OrdersComponent },
  { path: '', redirectTo: 'products', pathMatch: 'full' }
];
