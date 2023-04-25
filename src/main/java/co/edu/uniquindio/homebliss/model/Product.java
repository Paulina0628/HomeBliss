package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @PositiveOrZero
    private float price;

    @NotNull
    @PositiveOrZero
    private int stock;

    @NotNull
    private ProductState state;

    @NotNull
    private LocalDateTime created_date;

    @NotNull
    private LocalDateTime limit_date;

    @NotNull
    @ElementCollection
    private List<String> images = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private List<Category> categories;

    @ManyToMany(mappedBy = "favorites")
    private List<Client> clientFavorite;

    @NotNull
    @ManyToOne
    private Client seller;

    @OneToMany(mappedBy = "product")
    private List<ProductModerator> productsModerator;

    @OneToMany(mappedBy = "product")
    private List<PurchaseDetail> purchaseDetails;

    @OneToMany(mappedBy = "product")
    private List<Qualification> qualifications;

    @OneToMany(mappedBy = "product")
    private List<Question> questions;

    public Product(){
        super();
    }

}
