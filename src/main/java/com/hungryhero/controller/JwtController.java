package com.hungryhero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hungryhero.service.JwtService;

import io.github.cdimascio.dotenv.Dotenv;
import com.hungryhero.model.DeliveryRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtController {

    @Autowired
    private final JwtService jwtService;
    private final String developerId;
    private final String keyId;
    private final String signingSecret;


    @Autowired
    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
        Dotenv dotenv = Dotenv.load();
        this.developerId = dotenv.get("DEVELOPER_ID");
        this.keyId = dotenv.get("KEY_ID");
        this.signingSecret = dotenv.get("SIGNING_SECRET");
    }

    // @GetMapping("/generate-jwt")
    // public String generateJwt() {
    //     return jwtService.generateJwt(developerId, keyId, signingSecret);
    // }
    @PostMapping("/create-delivery")
    public String createDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        return jwtService.createDelivery(developerId, keyId, signingSecret,
            deliveryRequest.getDropoffAddress(),
            deliveryRequest.getDropoffPhoneNumber(),
            deliveryRequest.getPickupAddress(),
            deliveryRequest.getPickupPhoneNumber());
    }
}
