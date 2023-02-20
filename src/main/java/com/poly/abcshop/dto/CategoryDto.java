package com.poly.abcshop.dto;

import com.poly.abcshop.domain.Account;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int categoryId;
    private String name;
    private Date createDate;
}
