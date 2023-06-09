package co.edu.uniquindio.homebliss.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    @NotNull(message = "El correo no puede ser null")
    @Email(message = "El email no es válido")
    private String email;

    @NotNull(message = "La contraseña no puede ser null")
    private String password;
}
