package com.luanvv.spring.springstructure.services;

import com.luanvv.spring.springstructure.entities.Stock;
import com.luanvv.spring.springstructure.repositories.StockRepository;
import com.luanvv.spring.springstructure.repositories.StockRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StockServiceImpl implements StockService {

  @Autowired
  private StockRepositoryCustom stockDaoCustom;

  @Autowired
  private StockRepository stockDao;

  @Autowired
  private EntityManager em;

  @Override
  public Optional<Stock> findOne(long stockId) {
    return stockDao.findById(stockId);
  }

  @Override
  public Page<Stock> findPaginated(int page, int size) {
    return stockDao.findAll(PageRequest.of(page, size));
  }

  @Override
  public List<Stock> findAll() {
    return stockDao.findAll();
  }

  @Transactional
  @Override
  public void delete(String id) {
    Stock stock = stockDao.findByUuid(UUID.fromString(id))
        .orElseThrow(EntityNotFoundException::new);
    stockDao.delete(stock);
  }

  @Transactional
  @Override
  public Stock create(Stock resource) {
    return stockDao.save(resource);
  }

  @Override
  public Optional<Stock> findOne(String uuid) {
    return stockDao.findByUuid(UUID.fromString(uuid));
  }
}
