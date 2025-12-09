package com.luanvv.spring.springstructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "stock", catalog = "mkyong")
@EntityListeners(AuditingEntityListener.class)
public class Stock extends AbstractEntity implements java.io.Serializable {

  @Size(min = 10, max = 20)
  @Column(name = "STOCK_CODE", nullable = false, length = 10)
  private String stockCode;

  @Size(min = 10, max = 20)
  @Column(name = "STOCK_NAME", nullable = false, length = 20)
  private String stockName;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
  private Set<StockDailyRecord> stockDailyRecords = new HashSet<>(0);

  @CreatedDate
  @Column(name = "created_time", updatable = false)
  private LocalDateTime createdTime;

  @LastModifiedDate
  @Column(name = "updated_time")
  private LocalDateTime updatedTime;

  public Stock(String stockCode, String stockName) {
    this.stockCode = stockCode;
    this.stockName = stockName;
  }

  public Stock() {
  }

  public Stock(String stockCode, String stockName, Set<StockDailyRecord> stockDailyRecords,
      LocalDateTime createdTime, LocalDateTime updatedTime) {
    this.stockCode = stockCode;
    this.stockName = stockName;
    this.stockDailyRecords = stockDailyRecords;
    this.createdTime = createdTime;
    this.updatedTime = updatedTime;
  }

  public String getStockCode() {
    return stockCode;
  }

  public void setStockCode(String stockCode) {
    this.stockCode = stockCode;
  }

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  public Set<StockDailyRecord> getStockDailyRecords() {
    return stockDailyRecords;
  }

  public void setStockDailyRecords(
      Set<StockDailyRecord> stockDailyRecords) {
    this.stockDailyRecords = stockDailyRecords;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public LocalDateTime getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(LocalDateTime updatedTime) {
    this.updatedTime = updatedTime;
  }
}
