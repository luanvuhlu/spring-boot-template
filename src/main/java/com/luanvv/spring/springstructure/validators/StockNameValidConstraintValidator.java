package com.luanvv.spring.springstructure.validators;

import com.luanvv.spring.springstructure.entities.Stock;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class StockNameValidConstraintValidator implements
    ConstraintValidator<StockNameValid, Stock> {

  @Override
  public void initialize(StockNameValid constraintAnnotation) {
    // Empty
  }

  @Override
  public boolean isValid(Stock stock, ConstraintValidatorContext context) {
    // TODO custom logic
    return stock.getStockName().length() > 10;
  }
}