package edu.ait.winesmanager.dao;

import edu.ait.winesmanager.dto.Wine;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class WineDAOService {
    @PersistenceContext
    private EntityManager entityManager;

    public long insertWine(Wine wine)
    {
        entityManager.persist(wine);
        return wine.getId();
    }
}
