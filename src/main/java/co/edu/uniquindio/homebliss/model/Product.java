package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter @Setter
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
    private float price;

    @NotNull
    private int stock;

    @NotNull
    private Boolean isActive;

    @NotNull
    private Date created_date;

    @NotNull
    private Date limit_date;

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
