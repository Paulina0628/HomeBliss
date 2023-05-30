package co.edu.uniquindio.homebliss.dto;

import co.edu.uniquindio.homebliss.model.Category;
import co.edu.uniquindio.homebliss.model.ProductState;
import co.edu.uniquindio.homebliss.model.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductByClientGetDTO {

    private int id;

    private String name;

    private String description;

    private float price;

    private int stock;

    private ProductState state;

    private LocalDateTime limit_date;

    private int sellerCode;

    private List<String> images;

    private List<Category> categories;

    private List<Question> questions;

    private boolean favorite;

}
