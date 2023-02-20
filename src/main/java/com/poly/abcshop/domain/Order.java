package com.poly.abcshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long id;
    private String address;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date create_date = new Date();
    private float amount;
    private boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}
