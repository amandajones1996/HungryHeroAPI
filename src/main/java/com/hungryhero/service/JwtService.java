package com.hungryhero.service;

// import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*; 

import java.security.Key;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
// import java.util.UUID;
import java.util.Random;

@Service
public class JwtService {

    private final RestTemplate restTemplate;
    // private final String developerId;
    // private final String keyId;
    // private final String signingSecret;

    public JwtService() {
        this.restTemplate = new RestTemplate();
    }

    public String generateJwt(String developerId, String keyId, String signingSecret) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", developerId);
        claims.put("kid", keyId);
        byte[] keyBytes = Decoders.BASE64URL.decode(signingSecret);

        claims.put("aud", "doordash");

        claims.put("exp", ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(5).toEpochSecond());
        claims.put("iat", ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond());

        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setHeaderParam("dd-ver", "DD-JWT-V1")
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(key)
                .compact();
    }


    public String createDelivery(String developerId, String keyId, String signingSecret,
                                String dropoffAddress, String dropoffPhoneNumber,
                                String pickupAddress, String pickupPhoneNumber) {
        // Generate a unique ID for the delivery (you can use any method to generate a unique ID)
        String externalDeliveryId = "D-" + String.format("%04d", new Random().nextInt(10000));

        String jwtToken = generateJwt(developerId, keyId, signingSecret);

        String apiUrl = "https://openapi.doordash.com/drive/v2/deliveries";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwtToken);

        // Construct the request body for creating a delivery
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("external_delivery_id", externalDeliveryId);
        requestBody.put("dropoff_address", dropoffAddress);
        requestBody.put("dropoff_phone_number", dropoffPhoneNumber);
        requestBody.put("pickup_address", pickupAddress);
        requestBody.put("pickup_phone_number", pickupPhoneNumber);


        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return "Failed to create delivery. Status code: " + response.getStatusCode();
        }
    }

}
