package co.edu.uniquindio.homebliss.dto;

import co.edu.uniquindio.homebliss.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductGetDTO {

    private int code;

    private Boolean state;

    private LocalDateTime limit_date;

    private String name;

    private String description;

    private int stock;

    private float price;

    private int sellerCode;

    private List<String> images;

    private List<Category> categories;

}
