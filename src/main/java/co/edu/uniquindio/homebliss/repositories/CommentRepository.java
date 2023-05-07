package co.edu.uniquindio.homebliss.repositories;

import co.edu.uniquindio.homebliss.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {


}
