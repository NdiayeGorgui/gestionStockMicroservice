package com.gorgui.billingservice;

import com.gorgui.billingservice.model.Bill;
import com.gorgui.billingservice.model.Customer;
import com.gorgui.billingservice.model.Product;
import com.gorgui.billingservice.model.ProductItem;
import com.gorgui.billingservice.repository.BillRepository;
import com.gorgui.billingservice.repository.ProductItemRepository;
import com.gorgui.billingservice.service.CustomerService;
import com.gorgui.billingservice.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients //pour activer open feign
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	/*@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository) {
		return args -> {
			Bill bill1 = billRepository.save(new Bill(null, new Date(), 1L, null, null));
			productItemRepository.save(new ProductItem(null, 1L, null, 80, 500, bill1));
			productItemRepository.save(new ProductItem(null, 2L, null, 51, 400, bill1));
			productItemRepository.save(new ProductItem(null, 3L, null, 30, 300, bill1));

		};
	}*/
	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository,
							CustomerService customerService,
							ProductService productService) {
		Customer c1=customerService.findCustomerById(1L);
		Product p1=productService.findProductById(1L);
		Product p2=productService.findProductById(1L);
		Product p3=productService.findProductById(1L);


		System.out.println("test pour contacter customer dans customer service");
		System.out.println("================================");
		System.out.println("ID="+c1.getId());
		System.out.println("NAME="+c1.getName());
		System.out.println("EMail="+c1.getEmail());
		System.out.println("================================");

		System.out.println("test pour contacter product dans inventory service");
		System.out.println("================================");
		System.out.println("ID="+p1.getId());
		System.out.println("NAME="+p1.getName());
		System.out.println("PRICE="+p1.getPrice());
		System.out.println("================================");
		return args -> {
			Bill bill1 = billRepository.save(new Bill(null, new Date(), c1.getId(), null, null));
			/*productItemRepository.save(new ProductItem(null, p1.getId(), null, 80, p1.getPrice(), bill1));
			productItemRepository.save(new ProductItem(null, p2.getId(), null, 51, p2.getPrice(), bill1));
			productItemRepository.save(new ProductItem(null, p3.getId(), null, 30, p3.getPrice(), bill1));*/

			PagedModel <Product> products=productService.findAllProducts();
			products.getContent().forEach(p->{
				productItemRepository.save(new ProductItem(null, p.getId(), null, 80, p.getPrice(), bill1));
			});
		};
	}
}