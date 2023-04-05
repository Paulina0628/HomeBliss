package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client extends User implements Serializable {

    @Column(length = 50)
    @NotNull
    private String lastname;

    @Column(length = 12)
    @NotNull
    private String phone;

    @Column(length = 50)
    @NotNull
    private String address;

    @ManyToMany
    private List<Product> favorites;

    @OneToMany(mappedBy = "seller")
    private List<Product> products;

    @OneToMany(mappedBy = "client")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "client")
    private List<Question> questions;

    @OneToMany(mappedBy = "client")
    private List<Qualification> qualifications;

    public Client() {

    }
}
