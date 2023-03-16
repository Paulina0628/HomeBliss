package co.edu.uniquindio.homebliss.services;

import co.edu.uniquindio.homebliss.dto.CommentDTO;
import co.edu.uniquindio.homebliss.dto.CommentGetDTO;

import java.util.List;

public interface CommentService {

    int createComment(CommentDTO commentDTO);

    List<CommentGetDTO> getComments(int productCode);
}
