package com.bench.payments.services;

import com.bench.payments.entities.Payment;
import com.bench.payments.models.AccountDTO;
import com.bench.payments.models.PaymentDTO;
import com.bench.payments.repositories.PaymentRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.jms.ObjectMessage;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RestTemplate paymentRest;
    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDTO> findAll() {
        return paymentRepository.findAll().stream().map(PaymentDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional (readOnly = true)
    public PaymentDTO findById(Long id) {
        return paymentRepository.findById(id).map(PaymentDTO::new).orElse(null);
    }

    @Override
    @Transactional (readOnly = true)
    public List<PaymentDTO> findByFromAccountId(Long fromAccountId) {
        return paymentRepository.findByFromAccountId(fromAccountId).stream().map(PaymentDTO::new).collect(Collectors.toList());
    }
    @Override
    public Payment createNewPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment(paymentDTO);

        paymentRest.postForEntity("http://localhost:8090/accounts/api/accounts/updateBalance", paymentDTO, AccountDTO.class);

        return paymentRepository.save(payment);
    }
@Override
public String convertAndSend(PaymentDTO paymentDTO) {
    jmsTemplate.send("ProcessPayment", session -> {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount",paymentDTO.getAmount());
        jsonObject.put("fromAccountId",paymentDTO.getFromAccountId());
        jsonObject.put("toAccountId",paymentDTO.getToAccountId());

        Payment payment = new Payment(paymentDTO);
        paymentRepository.save(payment);

        ObjectMessage object = session.createObjectMessage(jsonObject.toString());

        System.out.println(object);
        return object;
    });
    return "The Transaction is in process...";
}
}
