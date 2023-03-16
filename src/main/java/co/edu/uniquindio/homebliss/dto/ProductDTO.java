package co.edu.uniquindio.homebliss.dto;

import co.edu.uniquindio.homebliss.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private String name;

    private String description;

    private int stock;

    private float price;

    private int sellerCode;

    private List<String> images;

    private List<Category> categories;
}
