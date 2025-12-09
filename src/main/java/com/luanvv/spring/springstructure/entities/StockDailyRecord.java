package com.luanvv.spring.springstructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "stock_daily_record", catalog = "mkyong")
public class StockDailyRecord extends AbstractEntity implements java.io.Serializable {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "STOCK_ID", nullable = false)
  private Stock stock;

  @Column(name = "PRICE_OPEN", precision = 6)
  private Float priceOpen;

  @Column(name = "PRICE_CLOSE", precision = 6)
  private Float priceClose;

  @Column(name = "PRICE_CHANGE", precision = 6)
  private Float priceChange;

  @Column(name = "VOLUME")
  private Long volume;

  @Column(name = "DATE", nullable = false, length = 10)
  private LocalDate date;

  public StockDailyRecord(Stock stock, LocalDate date) {
    this.stock = stock;
    this.date = date;
  }

  public StockDailyRecord() {
  }

  public StockDailyRecord(Stock stock, Float priceOpen, Float priceClose, Float priceChange,
      Long volume, LocalDate date) {
    this.stock = stock;
    this.priceOpen = priceOpen;
    this.priceClose = priceClose;
    this.priceChange = priceChange;
    this.volume = volume;
    this.date = date;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

  public Float getPriceOpen() {
    return priceOpen;
  }

  public void setPriceOpen(Float priceOpen) {
    this.priceOpen = priceOpen;
  }

  public Float getPriceClose() {
    return priceClose;
  }

  public void setPriceClose(Float priceClose) {
    this.priceClose = priceClose;
  }

  public Float getPriceChange() {
    return priceChange;
  }

  public void setPriceChange(Float priceChange) {
    this.priceChange = priceChange;
  }

  public Long getVolume() {
    return volume;
  }

  public void setVolume(Long volume) {
    this.volume = volume;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
