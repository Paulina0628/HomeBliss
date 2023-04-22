package co.edu.uniquindio.homebliss.controllers;

import co.edu.uniquindio.homebliss.dto.ClientGetDTO;
import co.edu.uniquindio.homebliss.dto.ClientPostDTO;
import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientGetDTO> list() {
        return clientService.getClients();
    }

    @GetMapping("/{code}")
    public ResponseEntity<MessageDTO> get(@PathVariable int code) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,
                clientService.getClientDTO(code)));
    }

    @PostMapping
    public ResponseEntity<MessageDTO> register(@Valid @RequestBody ClientPostDTO client) throws Exception {
        clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Usuario creado correctamente"));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<MessageDTO> delete(@PathVariable int code) throws Exception {
        clientService.deleteClient(code);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,
                "Eliminado correctamente" ) );
    }

    @PutMapping("/{code}")
    public ResponseEntity<MessageDTO> update(@PathVariable int code, @Valid @RequestBody ClientPostDTO client) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false,
                clientService.updateClient(code, client)));
    }


}
