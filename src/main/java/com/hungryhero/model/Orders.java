package com.hungryhero.model;
// import com.hungryhero.Users;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
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

    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Column(name = "user_id")
    private Long userId;

    // Define the relationship between Order and User
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "user_id", referencedColumnName = "id")
    // private Users user;

    public Orders() {}

    public Orders(Long orderId, String restaurant, String foodOrder, String deliveryFrequency, Double totalAmount, Long userId) {
        this.orderId = orderId;
        this.restaurant = restaurant;
        this.foodOrder = foodOrder;
        this.deliveryFrequency = deliveryFrequency;
        this.totalAmount = totalAmount;
        // this.user = user;
        this.userId = userId;
    }

    public Long getorderId() {
        return orderId;
    }

    public void setorderId(Long orderId) {
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

    // public Users getUser() {
    //     return user;
    // }

    // public void setUser(Users user) {
    //     this.user = user;
    // }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
