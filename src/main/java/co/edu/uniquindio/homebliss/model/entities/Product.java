package co.edu.uniquindio.homebliss.model.entities;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Lob
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private int stock;

    @OneToOne(mappedBy = "product")
    private Post post;


    public Product(){
        super();
    }

}
