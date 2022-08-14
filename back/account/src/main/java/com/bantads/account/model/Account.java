package com.bantads.account.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "balance")
    private Double balance;

    public Account() {
        super();
    }

    public Account(Long id, Long userId, Double balance) {
        super();
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
