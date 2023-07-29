package com.hungryhero;
import com.hungryhero.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Orders {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "restaurant", nullable = false)
    private String restaurant;

    @Column(name = "food_order", nullable = false)
    private String foodOrder;

    @Column(name = "delivery_freq", nullable = false)
    private String deliveryFrequency;

    @Column(name = "total_amount")
    private Double totalAmount;

    // Define the relationship between Order and User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users user;

    public Orders() {
        // Default constructor required by JPA
    }

    public Orders(String restaurant, String foodOrder, String deliveryFrequency, Double totalAmount, Users user) {
        this.restaurant = restaurant;
        this.foodOrder = foodOrder;
        this.deliveryFrequency = deliveryFrequency;
        this.totalAmount = totalAmount;
        this.user = user;
    }

    public Long getOrder_id() {
        return orderId;
    }

    public void setOrder_id(Long orderId) {
        this.orderId = orderId;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(String foodOrder) {
        this.foodOrder = foodOrder;
    }

    public String getDeliveryFrequency() {
        return deliveryFrequency;
    }

    public void setDeliveryFrequency(String deliveryFrequency) {
        this.deliveryFrequency = deliveryFrequency;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
