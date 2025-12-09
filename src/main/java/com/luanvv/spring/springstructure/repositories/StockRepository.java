package com.luanvv.spring.springstructure.repositories;

import com.luanvv.spring.springstructure.entities.Stock;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

  @Query("FROM Stock WHERE uuid = ?1 ")
  Optional<Stock> findByUuid(UUID uuid);

  List<Stock> findAll();

}
