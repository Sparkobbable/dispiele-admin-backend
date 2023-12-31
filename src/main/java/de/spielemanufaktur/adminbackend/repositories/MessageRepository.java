package de.spielemanufaktur.adminbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.spielemanufaktur.adminbackend.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // You can add custom query methods here if needed
}
