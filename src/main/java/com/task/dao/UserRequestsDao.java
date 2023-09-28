package com.task.dao;

import com.task.entity.UserRequests;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UserRequestsDao {

   @PersistenceContext
   private EntityManager entityManager;

   public Optional<UserRequests> findByLogin(String login) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<UserRequests> cq = cb.createQuery(UserRequests.class);
      Root<UserRequests> root = cq.from(UserRequests.class);

      cq.select(root)
              .where(cb.equal(root.get("login"), login));

      return entityManager.createQuery(cq).setLockMode(LockModeType.PESSIMISTIC_WRITE).getResultStream()
              .findFirst();

   }

   @Transactional(propagation = Propagation.MANDATORY)
   public void save(UserRequests userRequests) {
      entityManager.persist(userRequests);
   }
}