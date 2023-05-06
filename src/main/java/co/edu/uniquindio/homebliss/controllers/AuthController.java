package co.edu.uniquindio.homebliss.controllers;

import co.edu.uniquindio.homebliss.dto.ClientPostDTO;
import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.dto.SessionDTO;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.SessionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final ClientService clientService;

    private final SessionService sessionService;

    @PostMapping("/login")
    public ResponseEntity<MessageDTO> login(@Valid @RequestBody SessionDTO loginUser) {
        System.out.println("ENTRA :)");
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,
                sessionService.login(loginUser)) );
    }
    @PostMapping("/register")
    public ResponseEntity<MessageDTO> registrarCliente(@Valid @RequestBody ClientPostDTO client) throws Exception {
        clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Cliente creado correctamente"));
    }
}
