package co.edu.uniquindio.homebliss.repositories;

import co.edu.uniquindio.homebliss.model.Moderator;
import co.edu.uniquindio.homebliss.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{


}
