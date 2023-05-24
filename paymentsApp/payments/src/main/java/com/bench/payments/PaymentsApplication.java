package com.bench.payments;

import com.bench.payments.entities.Payment;
import com.bench.payments.entities.PaymentMethod;
import com.bench.payments.entities.PaymentType;
import com.bench.payments.repositories.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class PaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
	}
	/*@Bean
	public CommandLineRunner initData(PaymentRepository paymentRepository) {
		return (args) -> {


			Payment payment1 = new Payment(PaymentType.CREDIT, 12000.00, PaymentMethod.CARD, 1l, 2l);
			paymentRepository.save(payment1);
		};
	}*/
}
