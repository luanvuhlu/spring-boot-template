package com.luanvv.spring.springstructure.repositories;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepositoryCustomImpl implements StockRepositoryCustom {

  @Autowired
  private EntityManager em;

  @Override
  public void doSomething() {

  }

}
