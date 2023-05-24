package com.bench.clients;

import com.bench.clients.entities.Address;
import com.bench.clients.entities.Client;
import com.bench.clients.repositories.AddressRepository;
import com.bench.clients.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class ClientsApplication {   public static void main(String[] args) {      SpringApplication.run(ClientsApplication.class, args);   }

	/*@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AddressRepository addressRepository) {
		return (args) -> {
			Address address1 = new Address(24, "Sesame Street", "Kansas");
			addressRepository.save(address1);
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", "123", address1);
			clientRepository.save(client1);
			Client client2 = new Client("Agustin", "Retamar", "agustinr@gmail.com", "123", address1);
			clientRepository.save(client2);
		};
	}*/
}
