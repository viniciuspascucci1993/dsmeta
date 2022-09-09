package com.sysmantic.dsmeta.services;

import com.sysmantic.dsmeta.entities.Sale;
import com.sysmantic.dsmeta.repositories.SalesRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CallerSmsService {

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    @Autowired
    private SalesRepository salesRepository;
    public void sendSms(Long salesId) {

        Sale sale = salesRepository.findById(salesId).get();

        String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();

        String messageVendor = "O Vendedor " + sale.getSellerName() + " realizou uma venda na data " + date
                + " com um total de: R$ " + String.format("%.2f", sale.getAmount());
        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, messageVendor).create();

        System.out.println(message.getSid());
    }
}
