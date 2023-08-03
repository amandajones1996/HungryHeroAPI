package com.hungryhero.model;

public class CreatePaymentIntentRequest {
    private String customerId;
    private long amount;
    private String currency;

    public CreatePaymentIntentRequest() {
        // Empty constructor (no-argument constructor)
    }

    public CreatePaymentIntentRequest(String customerId, long amount, String currency) {
        this.customerId = customerId;
        this.amount = amount;
        this.currency = currency;
    }

    // Getters and setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}






