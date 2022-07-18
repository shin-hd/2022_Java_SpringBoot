package com.springboot.relationship.data.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    // 1대1
    // optional: null 허용여부, false-inner, true-outer
    @OneToOne(optional = false)
    @JoinColumn(name = "product_number") // 외래키
    private Product product;
}
