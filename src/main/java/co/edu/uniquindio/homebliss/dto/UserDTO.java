package co.edu.uniquindio.homebliss.dto;

import co.edu.uniquindio.homebliss.model.Moderator;
import co.edu.uniquindio.homebliss.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    private String name;

    private String email;

    private boolean moderator;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.moderator = user instanceof Moderator;
    }

}

