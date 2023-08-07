package com.hungryhero.model;


public class QuoteRequest {
    private String externalDeliveryId;
    private String dropoffAddress;
    private String dropoffPhoneNumber;
    private String pickupAddress;
    private String pickupPhoneNumber;


    public String getExternalDeliveryId() {
        return externalDeliveryId;
    }

    public void setExternalDeliveryId(String externalDeliveryId) {
        this.externalDeliveryId = externalDeliveryId;
    }

    public String getDropoffAddress() {
        return dropoffAddress;
    }

    public void setDropoffAddress(String dropoffAddress) {
        this.dropoffAddress = dropoffAddress;
    }

    public String getDropoffPhoneNumber() {
        return dropoffPhoneNumber;
    }

    public void setDropoffPhoneNumber(String dropoffPhoneNumber) {
        this.dropoffPhoneNumber = dropoffPhoneNumber;
    }


    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getPickupPhoneNumber() {
        return pickupPhoneNumber;
    }

    public void setPickupPhoneNumber(String pickupPhoneNumber) {
        this.pickupPhoneNumber = pickupPhoneNumber;
    }
}
