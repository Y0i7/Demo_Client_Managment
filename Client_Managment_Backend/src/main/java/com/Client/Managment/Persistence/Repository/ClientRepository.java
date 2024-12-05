package com.Client.Managment.Persistence.Repository;

import com.Client.Managment.Domain.Client;
import com.Client.Managment.Persistence.Entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    boolean existsByClientEmail(String email);

    @Query("SELECT c FROM ClientEntity c WHERE LOWER(c.clientName) = LOWER(:clientName)")
    List<ClientEntity> findByClientName(String clientName);

    @Query("SELECT new com.Client.Managment.Domain.Client(c.id, c.clientName, c.clientLname, c.clientEmail, c.clientDate) " +
            "FROM ClientEntity c ORDER BY c.id ASC")
    List<Client> findAllClientsSortedById();
}
