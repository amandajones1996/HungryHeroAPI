package com.hungryhero.model;

// import com.stripe.param.PriceCreateParams;
// import com.stripe.param.SubscriptionItemCreateParams;
import java.util.List;

public class CreateSubscriptionRequest {
    private String customerId;
    private  List<String> items;
    private String cardNumber; // Add fields for capturing card details
    private Integer expMonth;
    private Integer expYear;
    private String cvc;


    // public CreateSubscriptionRequest() {
    // }

    // public CreateSubscriptionRequest(String customerId, private List items) {
    //     this.customerId = customerId;
    //     this.items = items;
    // }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // public String getPriceId() {
    //     return priceId;
    // }

    // public void setPriceId(String priceId) {
    //     this.priceId = priceId;
    // }
    
    public  List<String> getItems() {
        return items;
    }

    public void setItems( List<String> items) {
        this.items = items;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Getter and setter methods for expMonth
    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    // Getter and setter methods for expYear
    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    // Getter and setter methods for cvc
    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
