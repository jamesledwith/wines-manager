package edu.ait.winesmanager.repositories;

import edu.ait.winesmanager.dto.Wine;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WineRepository extends JpaRepository<Wine, Integer> {
    //Find all wines matching given priority
    List<Wine> findByPriority(int priority);

    //Query all wines matching given priority
    List<Wine> queryByPriority(int priority);

    //Find the first wine matching provided targetDate
    Wine findFirstByTargetDate(LocalDate targetDate);

    //Find the top 5 tasks matching given priority
    List<Wine> findTop5ByPriority(int priority);

    //Find all wines matching given priority
    List<Wine> findByPriority(int priority, Sort sort);
}
