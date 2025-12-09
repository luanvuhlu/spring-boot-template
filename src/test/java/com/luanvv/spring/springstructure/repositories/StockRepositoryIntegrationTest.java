package com.luanvv.spring.springstructure.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.luanvv.spring.springstructure.entities.Stock;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test for StockRepository using H2 in-memory database.
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class StockRepositoryIntegrationTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    public void testSaveAndFindById() {
        // Given
        Stock stock = new Stock();
        stock.setStockCode("TEST123456");
        stock.setStockName("Test Stock Name");

        // When
        Stock savedStock = stockRepository.save(stock);

        // Then
        Optional<Stock> foundStock = stockRepository.findById(savedStock.getId());
        assertThat(foundStock).isPresent();
        assertThat(foundStock.get().getStockCode()).isEqualTo("TEST123456");
        assertThat(foundStock.get().getStockName()).isEqualTo("Test Stock Name");
        assertThat(foundStock.get().getUuid()).isNotNull();
    }

    @Test
    public void testFindByUuid() {
        // Given
        Stock stock = new Stock();
        stock.setStockCode("UUID123456");
        stock.setStockName("UUID Test Stock");

        // When
        Stock savedStock = stockRepository.save(stock);

        // Then
        Optional<Stock> foundStock = stockRepository.findByUuid(savedStock.getUuid());
        assertThat(foundStock).isPresent();
        assertThat(foundStock.get().getStockCode()).isEqualTo("UUID123456");
        assertThat(foundStock.get().getId()).isEqualTo(savedStock.getId());
    }

    @Test
    public void testFindAll() {
        // Given
        Stock stock1 = new Stock();
        stock1.setStockCode("ALL1234567");
        stock1.setStockName("Test Stock One");

        Stock stock2 = new Stock();
        stock2.setStockCode("ALL2345678");
        stock2.setStockName("Test Stock Two");

        // When
        stockRepository.save(stock1);
        stockRepository.save(stock2);

        // Then
        List<Stock> allStocks = stockRepository.findAll();
        assertThat(allStocks).hasSizeGreaterThanOrEqualTo(2);
        assertThat(allStocks).extracting(Stock::getStockCode)
            .contains("ALL1234567", "ALL2345678");
    }

    @Test
    public void testDeleteStock() {
        // Given
        Stock stock = new Stock();
        stock.setStockCode("DEL1234567");
        stock.setStockName("Delete Test Stock");

        // When
        Stock savedStock = stockRepository.save(stock);
        Long stockId = savedStock.getId();

        stockRepository.delete(savedStock);

        // Then
        Optional<Stock> deletedStock = stockRepository.findById(stockId);
        assertThat(deletedStock).isNotPresent();
    }

    @Test
    public void testUpdateStock() {
        // Given
        Stock stock = new Stock();
        stock.setStockCode("UPD1234567");
        stock.setStockName("Original Name");

        // When
        Stock savedStock = stockRepository.save(stock);

        savedStock.setStockName("Updated Name");
        Stock updatedStock = stockRepository.save(savedStock);

        // Then
        Optional<Stock> foundStock = stockRepository.findById(updatedStock.getId());
        assertThat(foundStock).isPresent();
        assertThat(foundStock.get().getStockName()).isEqualTo("Updated Name");
        assertThat(foundStock.get().getStockCode()).isEqualTo("UPD1234567");
    }
}
