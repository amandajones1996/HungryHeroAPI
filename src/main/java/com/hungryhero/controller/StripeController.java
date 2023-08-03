package com.hungryhero.controller;

// import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
// import com.stripe.model.Price;
import com.stripe.model.Subscription;
// import com.stripe.param.PriceCreateParams;
// import com.stripe.param.SubscriptionItemCreateParams;

// import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.hungryhero.service.StripeService;
import com.hungryhero.model.CreateCustomerRequest;
import com.hungryhero.model.CreatePaymentIntentRequest;
import com.hungryhero.model.CreateSubscriptionRequest;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;
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

    @PostMapping("stripe/createCustomer")
    public ResponseEntity<String> createCustomer(@RequestBody CreateCustomerRequest request) {
        try {
            // Get the necessary data from the request or database
            String email = request.getEmail();
            String name = request.getName();
            // Map<String, Object> params = new HashMap<>();
            // params.put("email", request.getEmail());
            // params.put("name", request.getName());

            // Call the createCustomer method on the stripeService instance
            Customer customer = stripeService.createCustomer(email, name);

            // Return a success response
            return ResponseEntity.ok("Customer created successfully: " + customer.getId());
        } catch (StripeException e) {
            // Handle Stripe API errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create customer: " + e.getMessage());
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
            String cardNumber = request.getCardNumber();
            Integer expMonth = request.getExpMonth();
            Integer expYear = request.getExpYear();
            String cvc = request.getCvc();
    
            Subscription subscription = stripeService.createSubscription(customerId, priceIds, cardNumber, expMonth, expYear, cvc);
    
            return ResponseEntity.ok("Subscription created successfully: " + subscription.getId());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create subscription: " + e.getMessage());
        }

    }
}
    // @GetMapping("stripe/prices")
    // public ResponseEntity<List<Price>> listPrices() {
    //     try {
    //         List<Price> prices = stripeService.listPrices();
    //         return ResponseEntity.ok(prices);
    //     } catch (StripeException e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    //     }
    // }

    // @PostMapping("/create-customer")
    // public ResponseEntity<Customer> createCustomer(@RequestParam String email, @RequestParam String paymentMethodId) {
    //     try {
    //         Customer customer = stripeService.createCustomer(email, paymentMethodId);
    //         return ResponseEntity.ok(customer);
    //     } catch (StripeException e) {
    //         // Handle Stripe API error
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }

    // @PostMapping("/create-payment-intent")
    // public ResponseEntity<PaymentIntent> createPaymentIntent(@RequestParam String customerId, @RequestParam long amount, @RequestParam String currency) {
    //     try {
    //         PaymentIntent paymentIntent = stripeService.createPaymentIntent(customerId, amount, currency);
    //         return ResponseEntity.ok(paymentIntent);
    //     } catch (StripeException e) {
    //         // Handle Stripe API error
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }

    // @PostMapping("/create-subscription")
    // public ResponseEntity<Subscription> createSubscription(@RequestParam String customerId, @RequestParam String priceId) {
    //     try {
    //         Subscription subscription = stripeService.createSubscription(customerId, priceId);
    //         return ResponseEntity.ok(subscription);
    //     } catch (StripeException e) {
    //         // Handle Stripe API error
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }

// }
