package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NotNull
    @CreationTimestamp
    private Timestamp created_date;

    @NotNull
    @Column(scale = 2)
    private float total_price;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod payment_method;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseDetail> purchaseDetails;

    public Purchase(){
        super();
    }
}
