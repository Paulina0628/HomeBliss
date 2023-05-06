package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.CommentDTO;
import co.edu.uniquindio.homebliss.dto.CommentGetDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    int createComment(CommentDTO commentDTO);
    List<CommentGetDTO> getComments(int productCode);
}