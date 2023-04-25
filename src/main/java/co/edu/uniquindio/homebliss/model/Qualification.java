package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter @Setter
public class Qualification extends Comment implements Serializable {

    @NotNull
    @Column(length = 50)
    private String title;

    public Qualification (){ super();}

}
