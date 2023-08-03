package com.hungryhero.model;

public class CreateCustomerRequest {
    public String email;
    public String name;
    public String customerId;

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
}
