package co.edu.uniquindio.homebliss.controllers;
import co.edu.uniquindio.homebliss.dto.CommentDTO;
import co.edu.uniquindio.homebliss.dto.CommentGetDTO;
import co.edu.uniquindio.homebliss.dto.MessageDTO;
import co.edu.uniquindio.homebliss.services.interfaces.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/commentProduct")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{productCode}")
    public List<CommentGetDTO> list (@PathVariable int productCode){
        return commentService.getComments(productCode);
    }

    @PostMapping
    public ResponseEntity<MessageDTO> register (@Valid @RequestBody CommentDTO comment){
        commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO(HttpStatus.CREATED,
                false, "Usuario creado correctamente"));
    }
}