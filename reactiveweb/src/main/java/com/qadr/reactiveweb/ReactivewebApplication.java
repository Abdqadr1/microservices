package com.qadr.reactiveweb;

import com.qadr.reactiveweb.dao.CustomerRepository;
import com.qadr.reactiveweb.model.Customer;
import com.qadr.reactiveweb.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class ReactivewebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivewebApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

//	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepo, PasswordEncoder encoder){
		return args -> {
			System.out.println("encoding password");
			customerRepo.findAll()
					.map(customer -> {
						customer.setPassword(encoder.encode(customer.getPassword()));
						return customer;
					}).subscribe(customer -> {
						System.out.println(customer);
						customerRepo.save(customer).subscribe(System.out::println);
					});
		};
	}

}
