package org.example.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.example.views.Views;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonView(Views.UserSummary.class)
    private UUID id;

    @Column(name = "name")
    @JsonView(Views.UserSummary.class)
    private String name;

    @Column(name = "email")
    @JsonView(Views.UserSummary.class)
    private String email;

    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonView(Views.UserDetails.class)
    private List <Orders> orders = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
