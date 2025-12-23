package com.ecom.products.web;

import com.ecom.products.entities.Product;
import com.ecom.products.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
public class ProductRestController {

    private ProductRepository productRepository;

    // Injection de dépendance via le constructeur (Bonne pratique)
    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Afficher la liste de tous les produits
    @GetMapping("/products")
    public List<Product> productList(){
        return productRepository.findAll();
    }

    // Afficher un seul produit par son ID
    @GetMapping("/products/{id}")
    public Product productById(@PathVariable Long id){
        return productRepository.findById(id).orElse(null);
    }
    // CREATE : Ajouter un nouveau produit
    // On utilise @PostMapping pour les ajouts
    // @RequestBody dit à Spring : "Prends le JSON envoyé et transforme-le en objet Product"
    @PostMapping("/products")
    public Product save(@RequestBody Product product){
        return productRepository.save(product);
    }

    // UPDATE : Modifier un produit existant
    // On utilise @PutMapping. On a besoin de l'ID dans l'URL et des données dans le corps
    @PutMapping("/products/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product){
        product.setId(id); // On s'assure que l'ID est bien celui de l'URL
        return productRepository.save(product);
    }

    // DELETE : Supprimer un produit
    // On utilise @DeleteMapping
    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Long id){
        productRepository.deleteById(id);
    }
}