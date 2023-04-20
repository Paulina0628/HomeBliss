package co.edu.uniquindio.homebliss.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class SessionDTO {
    @NotNull(message = "El correo no puede ser null")
    @NotBlank(message = "El correo no puede estar vacía")
    @Length(max = 100, message = "El correo debe ser de máximo 100 caracteres")
    @Email(message = "El email no es válido")
    private String email;

    @NotNull(message = "La contraseña no puede ser null")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Length(min = 7, max = 50, message = "La contraseña debe ser de máximo 50 caracteres")
    private String password;
}
