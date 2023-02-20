package com.poly.abcshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authorities", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"roleId", "username"})
})
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @ManyToOne  @JoinColumn(name = "roleId")
    private Role role;
}
