package co.edu.uniquindio.homebliss.controllers;

import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.dto.UserDTO;
import co.edu.uniquindio.homebliss.model.User;
import co.edu.uniquindio.homebliss.services.interfaces.ClientService;
import co.edu.uniquindio.homebliss.services.interfaces.FavoriteService;
import co.edu.uniquindio.homebliss.services.interfaces.ProductService;
import co.edu.uniquindio.homebliss.services.interfaces.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
@AllArgsConstructor
public class FavoriteController {

    @Autowired
    private final FavoriteService favoriteService;

    @Autowired
    private final SessionService sessionService;

    @PutMapping("/change/{code}")
    public ResponseEntity<MessageDTO<UserDTO>> changeState(@PathVariable int code) throws Exception {
        User user = sessionService.getCurrentUser();
        boolean nextState = favoriteService.changeState(user.getId(), code);
        return ResponseEntity.status(HttpStatus.OK).body( new MessageDTO(HttpStatus.OK, false, nextState));
    }

}
