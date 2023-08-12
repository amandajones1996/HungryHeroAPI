package com.hungryhero.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.hungryhero.service.StripeService;
import com.hungryhero.model.CreateCustomerRequest;
import com.hungryhero.model.CreatePaymentIntentRequest;
import com.hungryhero.model.CreateSubscriptionRequest;
import java.util.Map;
import java.util.List;

@RestController
// @RequestMapping("/stripe")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StripeController {
    
    @Autowired
    private StripeService stripeService;

    
    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("stripe/create-setup-intent")
    public ResponseEntity<Map<String, String>> createSetupIntent(@RequestBody Map<String, String> request) {
        try {
            String customerId = request.get("customerId");

            // Call the createSetupIntent method on the stripeService instance
            Map<String, String> setupIntentResult = stripeService.createSetupIntent(customerId);

            // Return the SetupIntent ID and PaymentMethod ID as the response
            return ResponseEntity.ok(setupIntentResult);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("stripe/createCustomer")
    public ResponseEntity<Map<String, String>> createCustomer(@RequestBody CreateCustomerRequest request) {
        try {
            String email = request.getEmail();
            String name = request.getName();

            Map<String, String> customerInfo = stripeService.createCustomer(email, name);

            return ResponseEntity.ok().body(customerInfo);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("stripe/create-payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody CreatePaymentIntentRequest request) {
        try {
            // Get the necessary data from the request or database
            String customerId = request.getCustomerId();
            long amount = request.getAmount();
            String currency = request.getCurrency();

            // Call the createPaymentIntent method on the stripeService instance
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(customerId, amount, currency);

            // Return a success response
            return ResponseEntity.ok("PaymentIntent created successfully: " + paymentIntent.getId());
        } catch (StripeException e) {
            // Handle Stripe API errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create PaymentIntent: " + e.getMessage());
        }
    }

    @PostMapping("stripe/create-subscription")
    public ResponseEntity<String> createSubscription(@RequestBody CreateSubscriptionRequest request) {
        try {
            String customerId = request.getCustomerId();
            List<String> priceIds = request.getItems();
            String defaultPaymentMethod = request.getDefaultPaymentMethod(); // Get the default payment method ID

            // Call the StripeService to create the subscription and optionally set the default payment method
            Subscription subscription = stripeService.createSubscription(customerId, priceIds, defaultPaymentMethod);

            return ResponseEntity.ok("Subscription created successfully: " + subscription.getId());
        } catch (StripeException e) {
            // Handle Stripe API errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create subscription: " + e.getMessage());
        }
    }

    
    @DeleteMapping("/stripe/cancel-subscription/{subscriptionId}")
    public ResponseEntity<String> cancelSubscription(@PathVariable String subscriptionId) {
        try {
            stripeService.cancelSubscription(subscriptionId);
            return ResponseEntity.ok("Subscription canceled successfully.");
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel subscription: " + e.getMessage());
        }
    }

    @GetMapping("/stripe/get-customer/{customerId}")
    public ResponseEntity<String> getDefaultPaymentMethod(@PathVariable String customerId) {
        try {
            String defaultPaymentMethodId = stripeService.getDefaultPaymentMethodId(customerId);
            return ResponseEntity.ok(defaultPaymentMethodId);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching default payment method");
        }
    }
}
