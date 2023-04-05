package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter
public class Moderator extends User implements Serializable {

    @OneToMany(mappedBy = "moderator")
    private List<ProductModerator> productsModerator;
}
