package com.gorgui.customerservice;

import com.gorgui.customerservice.model.Customer;
import com.gorgui.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.RestControllerConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
   @Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
			repositoryRestConfiguration.exposeIdsFor(Customer.class);
			customerRepository.save(new Customer(null,"gogo","gogo@gmail.com"));
			customerRepository.save(new Customer(null,"moussa","moussa@gmail.com"));
			customerRepository.save(new Customer(null,"abdou","abdou@gmail.com"));
			customerRepository.findAll().forEach(System.out::println);
		};

   }
}
