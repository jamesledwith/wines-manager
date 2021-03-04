package edu.ait.winesmanager.repositories;

import edu.ait.winesmanager.dto.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository<Wine, Integer> {
}
