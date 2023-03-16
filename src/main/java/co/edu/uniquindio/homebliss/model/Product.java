package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 100)
    @NotNull
    private String name;

    @NotNull
    @Lob
    private String description;

    @Column(scale = 2)
    @NotNull
    private Double price;

    @NotNull
    private int stock;

    @ElementCollection
    @NotNull
    private List<String> images;

    @Enumerated(EnumType.ORDINAL)
    private Category categories;

    @ManyToMany
    private List<Client> favorites;


    public Product(){
        super();
    }

}
