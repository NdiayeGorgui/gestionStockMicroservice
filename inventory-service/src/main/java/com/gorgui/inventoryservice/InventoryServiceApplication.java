package com.gorgui.inventoryservice;

import com.gorgui.inventoryservice.model.Product;
import com.gorgui.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository) {
		return args -> {
			productRepository.save(new Product(null, "ordinateur", 100));
			productRepository.save(new Product(null, "imprimante", 250));
			productRepository.save(new Product(null, "clavier", 124));
			productRepository.findAll().forEach(System.out::println);
		};

	}
}