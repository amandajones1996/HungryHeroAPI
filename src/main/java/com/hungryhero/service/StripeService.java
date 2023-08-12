package com.hungryhero.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.SetupIntent;
import com.stripe.model.Subscription;
import com.stripe.param.CustomerListPaymentMethodsParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.SetupIntentCreateParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import com.stripe.model.PaymentMethodCollection;




@Service
@Component
public class StripeService {
        
        public StripeService() {}

        
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

        

        public String getDefaultPaymentMethodId(String customerId) throws StripeException {
                Customer customer = Customer.retrieve(customerId);
                
                CustomerListPaymentMethodsParams params = CustomerListPaymentMethodsParams.builder()
                        .setType(CustomerListPaymentMethodsParams.Type.CARD)
                        .build();
                
                PaymentMethodCollection paymentMethodCollection = customer.listPaymentMethods(params);
                
                if (paymentMethodCollection.getData() != null && !paymentMethodCollection.getData().isEmpty()) {
                        PaymentMethod defaultPaymentMethod = paymentMethodCollection.getData().get(0);
                        return defaultPaymentMethod.getId();
                }
                
                return null;
                }
}