package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductModerator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NotNull
    @Lob
    private String reason;

    @NotNull
    @CreationTimestamp
    private Timestamp date;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private State state;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Moderator moderator;

    public ProductModerator(){
        super();
    }

}
