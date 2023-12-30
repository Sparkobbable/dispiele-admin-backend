package de.spielemanufaktur.adminbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.spielemanufaktur.backend.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // You can add custom query methods here if needed
    Item findFirstByOrderByIdDesc();
}
