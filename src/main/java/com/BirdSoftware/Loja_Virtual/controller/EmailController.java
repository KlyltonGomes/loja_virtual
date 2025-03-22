package com.BirdSoftware.Loja_Virtual.controller;

import com.BirdSoftware.Loja_Virtual.model.EmailRequest;
import com.BirdSoftware.Loja_Virtual.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar")
    public String enviarEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.enviarEmail(emailRequest.getPara(), emailRequest.getAssunto(), emailRequest.getMensagem());
            return "E-mail enviado com sucesso!";
        } catch (MessagingException e) {
            return "Erro ao enviar e-mail: " + e.getMessage();
        }
    }
}
