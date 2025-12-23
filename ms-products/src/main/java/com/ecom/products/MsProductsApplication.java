package com.ecom.products;

import com.ecom.products.entities.Product;
import com.ecom.products.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductsApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder().name("Ordinateur").price(12000).quantity(10).build());
            productRepository.save(Product.builder().name("Imprimante").price(1500).quantity(5).build());
            productRepository.save(Product.builder().name("Smartphone").price(4500).quantity(23).build());

            productRepository.findAll().forEach(p -> {
                System.out.println("Produit ajouté : " + p.toString());
            });
        };
    }
}