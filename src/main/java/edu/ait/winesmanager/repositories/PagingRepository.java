package edu.ait.winesmanager.repositories;

import edu.ait.winesmanager.dto.Wine;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagingRepository extends PagingAndSortingRepository<Wine, Integer> {
}
