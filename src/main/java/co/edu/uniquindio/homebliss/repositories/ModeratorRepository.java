package co.edu.uniquindio.homebliss.repositories;

import co.edu.uniquindio.homebliss.model.Client;
import co.edu.uniquindio.homebliss.model.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ModeratorRepository extends JpaRepository<Client, Integer> {

    @Query("select m from Moderator m where m.email = :email")
    Optional<Moderator> findByEmail(String email);
}
