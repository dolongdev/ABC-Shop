package com.poly.abcshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "role_id")
    private String roleId;
    private String name;

    @OneToMany(mappedBy = "role")
    List<Authority> authorities;
}
