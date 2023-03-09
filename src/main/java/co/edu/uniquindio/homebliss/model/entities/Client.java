package co.edu.uniquindio.homebliss.model.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client extends User implements Serializable {

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String cc;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

}
