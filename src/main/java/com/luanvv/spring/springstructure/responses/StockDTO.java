package com.luanvv.spring.springstructure.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class StockDTO {

  @JsonProperty("id")
  private UUID uuid;

  @JsonProperty("code")
  private String stockCode;

  @JsonProperty("name")
  private String stockName;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  @JsonProperty("create_time")
  private String createdTime;

  public StockDTO() {
  }

  public StockDTO(UUID uuid, String stockCode, String stockName, String createdTime) {
    this.uuid = uuid;
    this.stockCode = stockCode;
    this.stockName = stockName;
    this.createdTime = createdTime;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
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

  public String getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(String createdTime) {
    this.createdTime = createdTime;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    StockDTO stockDTO = (StockDTO) o;
    return uuid.equals(stockDTO.uuid) && stockCode.equals(stockDTO.stockCode) && stockName.equals(
        stockDTO.stockName) && createdTime.equals(stockDTO.createdTime);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + uuid.hashCode();
    result = 31 * result + stockCode.hashCode();
    result = 31 * result + stockName.hashCode();
    result = 31 * result + createdTime.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "StockDTO{" +
        "uuid=" + uuid +
        ", stockCode='" + stockCode + '\'' +
        ", stockName='" + stockName + '\'' +
        ", createdTime='" + createdTime + '\'' +
        '}';
  }
}
