package co.edu.uniquindio.homebliss.dto;

import co.edu.uniquindio.homebliss.model.Category;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductPostDTO {

    @NotNull(message = "El nombre no puede ser null")
    @NotBlank(message = "El nombre no puede estar vacía")
    @Length(max = 150, message = "El nombre debe ser de máximo 150 caracteres")
    private String name;
    @NotNull(message = "El apellido no puede ser null")
    @NotBlank(message = "El apellido no puede estar vacía")
    @Lob
    private String description;

    @NotNull(message = "Las unidades en stock no puede ser null")
    @NotBlank(message = "Las unidades en stock no puede estar vacía")
    @PositiveOrZero
    private int stock;

    @NotNull(message = "El precio no puede ser null")
    @NotBlank(message = "El precio no puede estar vacío")
    @PositiveOrZero
    private float price;

    @NotNull(message = "El código del vendedor no puede ser null")
    @NotBlank(message = "El código del vendedor no puede estar vací0")
    private int sellerCode;

    @NotNull(message = "Las imagenes no pueden ser null")
    private List<String> images;


    @NotNull(message = "Las categorías no pueden ser null")
    private List<Category> categories;
}
