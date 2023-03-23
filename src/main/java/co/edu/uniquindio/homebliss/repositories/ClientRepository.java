package co.edu.uniquindio.homebliss.repositories;

import co.edu.uniquindio.homebliss.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("select c from Client c where c.email = :email")
    Client searchClient(String email);



}
