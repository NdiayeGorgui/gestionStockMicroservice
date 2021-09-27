package com.gorgui.billingservice.service;


import com.gorgui.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductService {
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable(name = "id") Long id);
    @GetMapping("/products")
    //public Collection<Product> findAllProducts();
  //on utilise pagemodel a la place de collection pour pouvoir deserialiser avec hateoas
    public PagedModel<Product> findAllProducts();

}
