package com.kovaliv.task.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double sizeX;

    @Column(nullable = false)
    private Double sizeY;

    @Column(nullable = false)
    private Double sizeZ;

    @OneToMany(mappedBy = "product")
    private List<Orderline> orderlines;
}
