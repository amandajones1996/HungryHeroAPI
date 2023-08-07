package com.hungryhero.model;

public class CreateCustomerRequest {
    public String email;
    public String name;
    public String customerId;
    // private String cardNumber; // Add fields for capturing card details
    // private Integer expMonth;
    // private Integer expYear;
    // private String cvc;

    // public CreateCustomerRequest() {
    //     // Empty constructor (no-argument constructor)
    // }

    // public CreateCustomerRequest(String email, String name) {
    //     this.email = email;
    //     this.name = name;
    // }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // public String getCardNumber() {
    //     return cardNumber;
    // }

    // public void setCardNumber(String cardNumber) {
    //     this.cardNumber = cardNumber;
    // }

    // // Getter and setter methods for expMonth
    // public Integer getExpMonth() {
    //     return expMonth;
    // }

    // public void setExpMonth(Integer expMonth) {
    //     this.expMonth = expMonth;
    // }

    // Getter and setter methods for expYear
    // public Integer getExpYear() {
    //     return expYear;
    // }

    // public void setExpYear(Integer expYear) {
    //     this.expYear = expYear;
    // }

    // // Getter and setter methods for cvc
    // public String getCvc() {
    //     return cvc;
    // }

    // public void setCvc(String cvc) {
    //     this.cvc = cvc;
    // }
}
