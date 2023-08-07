package com.hungryhero.service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



// import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
// import com.stripe.model.PaymentMethod;
import com.stripe.model.SetupIntent;
// import com.stripe.model.PaymentMethod;
// import com.stripe.model.Price;
import com.stripe.model.Subscription;
// import com.stripe.net.RequestOptions;
// import com.stripe.param.CustomerCreateParams;
// import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.PaymentIntentCreateParams;
// import com.stripe.param.PriceCreateParams;
// import com.stripe.param.PriceListParams;
// import com.stripe.param.SubscriptionCreateParams;
// import com.stripe.param.SubscriptionItemCreateParams;
// import com.stripe.param.PaymentMethodAttachParams;
// import com.stripe.param.PaymentMethodCreateParams;
// import com.stripe.param.SubscriptionCreateParams;
import com.stripe.param.SetupIntentCreateParams;

import java.util.ArrayList;
import java.util.HashMap;
// import com.stripe.model.Price;
// import com.stripe.param.PriceListParams;
import java.util.List;
import java.util.Map; 

@Service
@Component
public class StripeService {
        
        public StripeService() {}
        
        // public String createPaymentMethod(String cardNumber, Integer expMonth, Integer expYear, String cvc) throws StripeException {
        //         Map<String, Object> cardParams = new HashMap<>();
        //         cardParams.put("number", cardNumber);
        //         cardParams.put("exp_month", expMonth);
        //         cardParams.put("exp_year", expYear);
        //         cardParams.put("cvc", cvc);

        //         Map<String, Object> paymentMethodParams = new HashMap<>();
        //         paymentMethodParams.put("type", "card");
        //         paymentMethodParams.put("card", cardParams);

        //         PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);

        //         return paymentMethod.getId();
        // }

        // public void attachPaymentMethodToCustomer(String customerId, String paymentMethodId) throws StripeException {
        //         Customer customer = Customer.retrieve(customerId);
        
        //         CustomerUpdateParams customerUpdateParams = CustomerUpdateParams.builder()
        //                 .setInvoiceSettings(
        //                         CustomerUpdateParams.InvoiceSettings.builder()
        //                                 .setDefaultPaymentMethod(paymentMethodId)
        //                                 .build()
        //                 )
        //                 .build();
        
        //         customer = customer.update(customerUpdateParams);
        // }

        
        public Map<String, String> createSetupIntent(String customerId) throws StripeException {
                // Specify the supported payment method types (in this case, only card)
                List<Object> paymentMethodTypes = new ArrayList<>();
                paymentMethodTypes.add("card");

                // Create SetupIntent with the customer ID and payment method types
                SetupIntentCreateParams params = SetupIntentCreateParams.builder()
                        .setCustomer(customerId)
                        .putExtraParam("payment_method_types", paymentMethodTypes)
                        .build();

                SetupIntent setupIntent = SetupIntent.create(params);

                // Get the PaymentMethod ID from the SetupIntent (It will be automatically attached to the Customer on successful setup)
                String paymentMethodId = setupIntent.getPaymentMethod();

                // Prepare the result map
                Map<String, String> result = new HashMap<>();
                result.put("setupIntentId", setupIntent.getId());
                result.put("paymentMethodId", paymentMethodId);

                // Return the SetupIntent ID and PaymentMethod ID
                return result;
        }
                

        public Map<String, String> createCustomer(String email, String name) throws StripeException {
                Map<String, Object> customerParams = new HashMap<>();
                customerParams.put("email", email);
                customerParams.put("name", name);
        
                Customer customer = Customer.create(customerParams);
                
                Map<String, String> customerInfo = new HashMap<>();
                customerInfo.put("customerId", customer.getId());
                
                return customerInfo;
        }

        public PaymentIntent createPaymentIntent(String customerId, long amount, String currency) throws StripeException {

                PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                        .setCustomer(customerId)
                        .setAmount(amount)
                        .setCurrency(currency)
                        .build();

                return PaymentIntent.create(params);
        }

        // public Subscription createSubscription(String customerId, List<String> priceIds) throws StripeException {
        //         List<Map<String, Object>> items = buildSubscriptionItems(priceIds);
        
        //         Map<String, Object> subscriptionParams = new HashMap<>();
        //         subscriptionParams.put("customer", customerId);
        //         subscriptionParams.put("items", items);
        
        //         return Subscription.create(subscriptionParams);
        // }

        // private List<Map<String, Object>> buildSubscriptionItems(List<String> priceIds) {
        // List<Map<String, Object>> items = new ArrayList<>();
        // for (String priceId : priceIds) {
        //         Map<String, Object> item = new HashMap<>();
        //         item.put("price", priceId);
        //         items.add(item);
        // }
        // return items;
        // }

        public Subscription createSubscription(String customerId, List<String> priceIds, String defaultPaymentMethod) throws StripeException {
                List<Map<String, Object>> items = buildSubscriptionItems(priceIds);

                Map<String, Object> subscriptionParams = new HashMap<>();
                subscriptionParams.put("customer", customerId);
                subscriptionParams.put("items", items);

                if (defaultPaymentMethod != null && !defaultPaymentMethod.isEmpty()) {
                subscriptionParams.put("default_payment_method", defaultPaymentMethod);
                }

                return Subscription.create(subscriptionParams);
        }

        private List<Map<String, Object>> buildSubscriptionItems(List<String> priceIds) {
                List<Map<String, Object>> items = new ArrayList<>();
                for (String priceId : priceIds) {
                Map<String, Object> item = new HashMap<>();
                item.put("price", priceId);
                items.add(item);
                }
                return items;
        }

        
        public void cancelSubscription(String subscriptionId) throws StripeException {
                Subscription subscription = Subscription.retrieve(subscriptionId);
                subscription.cancel();
        }

}
        
        // public Subscription createSubscription(String customerId, List<String> priceIds, String cardNumber, Integer expMonth, Integer expYear, String cvc) throws StripeException {
        //         // Step 1: Create the Payment Method
        //         Map<String, Object> cardParams = new HashMap<>();
        //         cardParams.put("number", cardNumber);
        //         cardParams.put("exp_month", expMonth);
        //         cardParams.put("exp_year", expYear);
        //         cardParams.put("cvc", cvc);
                
        //         Map<String, Object> paymentMethodParams = new HashMap<>();
        //         paymentMethodParams.put("type", "card");
        //         paymentMethodParams.put("card", cardParams);
                
        //         PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);
                
        //         // Step 2: Attach the Payment Method to the customer
        //         Customer customer = Customer.retrieve(customerId);
        //         Map<String, Object> customerParams = new HashMap<>();
        //         Map<String, Object> invoiceSettings = new HashMap<>();
        //         invoiceSettings.put("default_payment_method", paymentMethod.getId());
        //         customerParams.put("invoice_settings", invoiceSettings);
                
        //         customer.update(customerParams);
                
        //         // Step 3: Create the subscription with the default payment method
        //         List<Map<String, Object>> items = buildSubscriptionItems(priceIds);
                
        //         Map<String, Object> subscriptionParams = new HashMap<>();
        //         subscriptionParams.put("customer", customerId);
        //         subscriptionParams.put("items", items);
                
        //         return Subscription.create(subscriptionParams);
        //         }
                
        //         private List<Map<String, Object>> buildSubscriptionItems(List<String> priceIds) {
        //         List<Map<String, Object>> items = new ArrayList<>();
        //         for (String priceId : priceIds) {
        //                 Map<String, Object> item = new HashMap<>();
        //                 item.put("price", priceId);
        //                 items.add(item);
        //         }
        //         return items;
        //         }            

        // public Subscription createSubscription(String customerId, List<String> priceIds, String cardNumber, Integer expMonth, Integer expYear, String cvc) throws StripeException {
        //         // Step 1: Create the Payment Method
        //         Map<String, Object> cardParams = new HashMap<>();
        //         cardParams.put("number", cardNumber);
        //         cardParams.put("exp_month", expMonth);
        //         cardParams.put("exp_year", expYear);
        //         cardParams.put("cvc", cvc);
        
        //         Map<String, Object> paymentMethodParams = new HashMap<>();
        //         paymentMethodParams.put("type", "card");
        //         paymentMethodParams.put("card", cardParams);
        
        //         PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);
        
        //         // Step 2: Attach the Payment Method to the customer
        //         PaymentMethodAttachParams attachParams = PaymentMethodAttachParams.builder()
        //                 .setCustomer(customerId)
        //                 .build();
        
        //         PaymentMethod attachedPaymentMethod = paymentMethod.attach(attachParams);
        
        //         // Step 3: Set the Payment Method as the default for the customer
        //         CustomerUpdateParams customerUpdateParams = CustomerUpdateParams.builder()
        //                 .setInvoiceSettings(CustomerUpdateParams.InvoiceSettings.builder()
        //                         .setDefaultPaymentMethod(attachedPaymentMethod.getId())
        //                         .build())
        //                 .build();
        
        //         Customer customer = Customer.retrieve(customerId).update(customerUpdateParams);

        //         // Step 4: Create the subscription with the default payment method
        //         List<Map<String, Object>> items = buildSubscriptionItems(priceIds);
        
        //         SubscriptionCreateParams.Builder subscriptionBuilder = SubscriptionCreateParams.builder()
        //                 .setCustomer(customerId)
        //                 .setItems(items)
        //                 .build();
        
        //         // If you want to set trial or other subscription parameters, add them here using the builder.
        
        //         SubscriptionCreateParams subscriptionParams = subscriptionBuilder.build();
        //         return Subscription.create(subscriptionParams);
        //         }
        
        //         private List<Map<String, Object>> buildSubscriptionItems(List<String> priceIds) {
        //         List<Map<String, Object>> items = new ArrayList<>();
        //         for (String priceId : priceIds) {
        //                 Map<String, Object> item = new HashMap<>();
        //                 item.put("price", priceId);
        //                 items.add(item);
        //         }
        //         return items;
        //         }
        
        
        // public Subscription createSubscription(String customerId, List<String> priceIds) throws StripeException {
        //         List<Object> items = new ArrayList<>();
        //         for (String priceId : priceIds) {
        //                 Map<String, Object> item = new HashMap<>();
        //                 item.put("price", priceId);
        //                 items.add(item);
        //         }

        //         Map<String, Object> params = new HashMap<>();
        //         params.put("customer", customerId);
        //         params.put("items", items);

        //         return Subscription.create(params);
        // }


        // public List<Price> listPrices() throws StripeException {
        //         PriceListParams params = PriceListParams.builder().build();
        //         return Price.list(params).getData();
        // }

