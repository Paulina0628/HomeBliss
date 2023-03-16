package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "limit_date", nullable = false)
    private Date limitDate;

    @Column(nullable = false)
    private String state;

    @OneToOne
    @JoinColumn(nullable = false)
    private Product product;

    public Post(){
        super();
    }

}
