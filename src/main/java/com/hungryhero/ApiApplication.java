package com.hungryhero;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import io.github.cdimascio.dotenv.Dotenv;
@SpringBootApplication
@ComponentScan(basePackages = {"com.hungryhero.service", "com.hungryhero.controller"}) 
@EntityScan("com.hungryhero")
public class ApiApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		String stripeApiKey = dotenv.get("STRIPE_API_KEY");
        // Set your Stripe API key
        com.stripe.Stripe.apiKey = stripeApiKey;
		SpringApplication.run(ApiApplication.class, args);
	}

}
