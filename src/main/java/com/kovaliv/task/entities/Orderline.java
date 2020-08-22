package com.kovaliv.task.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderlines")
public class Orderline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(1)
    @Column(nullable = false)
    private Integer numberOfProducts;

    @ManyToOne
    private Product product;
}
