package co.edu.uniquindio.homebliss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter @Setter
public class Question extends Comment implements Serializable {

    @NotNull
    @Lob
    private String answer;
}
