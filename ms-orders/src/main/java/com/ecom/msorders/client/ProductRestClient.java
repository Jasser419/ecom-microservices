package com.ecom.msorders.client;



import com.ecom.msorders.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-products")
public interface ProductRestClient {

    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);
}

