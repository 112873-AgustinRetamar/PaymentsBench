package com.bench.payments.controllers;

import com.bench.payments.entities.Payment;
import com.bench.payments.models.AccountDTO;
import com.bench.payments.models.PaymentDTO;
import com.bench.payments.repositories.PaymentRepository;
import com.bench.payments.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private IPaymentService paymentService;

    //Trae todos los PAYMENTS
    @GetMapping("/payments")
    public List<PaymentDTO> getPayments(){
        return new ArrayList<>(this.paymentService.findAll());
    }

    //Trae un PAYMENT por ID
    @GetMapping("/payments/{id}")
    public PaymentDTO getById(@PathVariable Long id){
        return paymentService.findById(id);
    }

    //Trae los PAYMENTS de UNA CUENTA por su ID
    @GetMapping("/payments/account/{fromAccountId}")
    public List<PaymentDTO> getByFromAccountId(@PathVariable Long fromAccountId) {
        return paymentService.findByFromAccountId(fromAccountId);
    }


    @PostMapping("/payments")
    public ResponseEntity<Object> createPayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            Payment payment = paymentService.createNewPayment(paymentDTO);
            return new ResponseEntity<>("New payment created succesfully!", HttpStatus.CREATED);

        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return new ResponseEntity<>("Error creating payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
@Transactional //Para que si algo falla, se hecha todi para atrás
@PostMapping("/paymentsActiveMQ")
public ResponseEntity<Object> createPaymentActiveMQ(@RequestBody PaymentDTO paymentDTO) {

    try {
        paymentService.convertAndSend(paymentDTO);
        return new ResponseEntity<>("Processing payment...", HttpStatus.CREATED);
    }catch (Exception exception){ //si algo más falla
        System.out.println(exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity<>("Error creating payment", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}