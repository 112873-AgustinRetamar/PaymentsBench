package com.bench.accounts;

import com.google.gson.*;
import com.bench.accounts.models.PaymentDTO;
import com.bench.accounts.services.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Component
public class MessageConsumer {
    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    private PaymentDTO paymentDTO;

    @Autowired
    private IAccountService iAccountService;

    @JmsListener(destination = "ProcessPayment")
    public void receiveMessage(String message) {

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }).create();
        paymentDTO = gson.fromJson(message, PaymentDTO.class);
        System.out.println(paymentDTO.getAmount());
        System.out.println(paymentDTO.getFromAccountId());
        System.out.println(paymentDTO.getToAccountId());
        logger.info("MSAccounts: processing transaction");
        if(iAccountService.findById(paymentDTO.getFromAccountId()).getBalance()>=paymentDTO.getAmount()){
            iAccountService.updateBalance(paymentDTO);
            logger.info("MSAccounts: transaction made");
        }
        else {
            logger.info("Not enough funds");
        }
    }

}
