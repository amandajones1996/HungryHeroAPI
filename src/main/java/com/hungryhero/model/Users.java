package com.hungryhero.model;
// import javax.persistence.CascadeType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
// import jakarta.persistence.JoinColumn;


@Entity
@Table(name="users")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "email", nullable = false, unique = true)
    private String email;

    // @Column(name = "password", nullable = false)
    private String password;

    // Add the one-to-many relationship mapping
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Orders> orders = new ArrayList<>(0);


    public Users() {
        // Default constructor required by JPA
    }

    public Users(Long id, String email, String password, List<Orders> orders) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public List<Orders> getOrders(){
        return orders;
    }

}