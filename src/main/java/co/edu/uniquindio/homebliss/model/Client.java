package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
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

    @ManyToMany(mappedBy = "favorites")
    private List<Product> favorites;
}
