package co.edu.uniquindio.homebliss.services.implementation;

import co.edu.uniquindio.homebliss.dto.CommentDTO;
import co.edu.uniquindio.homebliss.dto.CommentGetDTO;
import co.edu.uniquindio.homebliss.model.Comment;
import co.edu.uniquindio.homebliss.repositories.ClientRepository;
import co.edu.uniquindio.homebliss.repositories.CommentRepository;
import co.edu.uniquindio.homebliss.repositories.ProductRepository;
import co.edu.uniquindio.homebliss.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public int createComment(CommentDTO commentDTO) {
        return commentRepository.save(toComment(commentDTO)).getId();
    }

    @Override
    public List<CommentGetDTO> getComments(int productCode) {


        return toListCommentDTO(commentRepository.findAll());
    }

    /**
     * Método que convierte el objeto CommentDTO en objeto Comment
     *
     * @param commentDTO Comentario realizado por el usuario
     * @return Objeto Comentario
     */
    private Comment toComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setCreated_date(LocalDateTime.now());
        comment.setClient(clientRepository.getById(commentDTO.getId()));
        comment.setProduct(productRepository.getReferenceById(commentDTO.getProductCode()));
        comment.setMessage(commentDTO.getMessage());
        return comment;
    }

    /**
     *
     * @param comment
     * @return
     */
    private CommentGetDTO toCommentDTO(Comment comment) {
        CommentGetDTO commentGetDTO = new CommentGetDTO();
        commentGetDTO.setId(comment.getId());
        commentGetDTO.setProductCode(comment.getProduct().getId());
        commentGetDTO.setUserCode(comment.getClient().getId());
        commentGetDTO.setMessage(comment.getMessage());
        return commentGetDTO;
    }

    /**
     * Función que permite convertir de Objeto Comment a Objeto CommentDTO
     * @param list lista de comentarios
     * @return Lista comentarios DTO
     */
    private List<CommentGetDTO> toListCommentDTO(List<Comment> list) {

        List<CommentGetDTO> commentGetDTOS = new ArrayList<>();
        for (Comment c : list) {
            commentGetDTOS.add(toCommentDTO(c));
        }
        return commentGetDTOS;
    }
}
