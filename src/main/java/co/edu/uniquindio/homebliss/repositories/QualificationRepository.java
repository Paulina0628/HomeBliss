package co.edu.uniquindio.homebliss.repositories;

import co.edu.uniquindio.homebliss.model.Moderator;
import co.edu.uniquindio.homebliss.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Integer>{



}
