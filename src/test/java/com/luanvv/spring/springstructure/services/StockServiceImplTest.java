package com.luanvv.spring.springstructure.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.luanvv.spring.springstructure.entities.Stock;
import com.luanvv.spring.springstructure.repositories.StockRepository;
import com.luanvv.spring.springstructure.repositories.StockRepositoryCustom;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class StockServiceImplTest {

  @Mock
  private StockRepositoryCustom stockDaoCustom;

  @Mock
  private StockRepository stockDao;

  @InjectMocks
  private StockServiceImpl stockService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindOneById() {
    Stock stock = new Stock();
    when(stockDao.findById(1L)).thenReturn(Optional.of(stock));

    Optional<Stock> result = stockService.findOne(1L);
    assertTrue(result.isPresent());
    assertEquals(stock, result.get());
  }

  @Test
  public void testFindOneByUuid() {
    Stock stock = new Stock();
    UUID uuid = UUID.randomUUID();
    when(stockDao.findByUuid(uuid)).thenReturn(Optional.of(stock));

    Optional<Stock> result = stockService.findOne(uuid.toString());
    assertTrue(result.isPresent());
    assertEquals(stock, result.get());
  }

  @Test
  public void testFindPaginated() {
    Stock stock = new Stock();
    List<Stock> stocks = Arrays.asList(stock);
    Page<Stock> page = new PageImpl<>(stocks);
    when(stockDao.findAll(PageRequest.of(0, 10))).thenReturn(page);

    Page<Stock> result = stockService.findPaginated(0, 10);
    assertEquals(1, result.getTotalElements());
    assertEquals(stock, result.getContent().get(0));
  }

  @Test
  public void testFindAll() {
    Stock stock = new Stock();
    List<Stock> stocks = List.of(stock);
    when(stockDao.findAll()).thenReturn(stocks);

    List<Stock> result = stockService.findAll();
    assertEquals(1, result.size());
    assertEquals(stock, result.getFirst());
  }

  @Test
  public void testDelete() {
    Stock stock = new Stock();
    UUID uuid = UUID.randomUUID();
    when(stockDao.findByUuid(uuid)).thenReturn(Optional.of(stock));

    stockService.delete(uuid.toString());
    verify(stockDao, times(1)).delete(stock);
  }

  @Test
  public void testCreate() {
    Stock stock = new Stock();
    when(stockDao.save(stock)).thenReturn(stock);

    Stock result = stockService.create(stock);
    assertEquals(stock, result);
  }
}
