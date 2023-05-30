package co.edu.uniquindio.homebliss.dto;

import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.model.Moderator;
import co.edu.uniquindio.homebliss.model.Product;
import co.edu.uniquindio.homebliss.model.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductModeratorDTO {

    @NotNull
    private String reason;

    @NotNull
    private String state;

    private int productCode;
    private int moderatorCode;


}
