package com.kovaliv.task.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cases")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double sizeX;

    @Column(nullable = false)
    private Double sizeY;

    @Column(nullable = false)
    private Double sizeZ;
}
