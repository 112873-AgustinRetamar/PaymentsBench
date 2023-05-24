package com.bench.payments.repositories;

import com.bench.payments.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByFromAccountId(Long fromAccountId);
}
