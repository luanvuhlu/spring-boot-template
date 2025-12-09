package com.luanvv.spring.springstructure.controllers.stock;

import com.google.common.base.Preconditions;
import com.luanvv.spring.springstructure.controllers.base.BaseController;
import com.luanvv.spring.springstructure.entities.Stock;
import com.luanvv.spring.springstructure.exeptions.MyResourceNotFoundException;
import com.luanvv.spring.springstructure.responses.StockDTO;
import com.luanvv.spring.springstructure.services.StockService;
import com.luanvv.spring.springstructure.validators.StockValidator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class StockController extends BaseController {

  private static final Logger log = LoggerFactory.getLogger(StockController.class);

  @Autowired
  private StockService stockService;

  @Autowired
  private ModelMapper modelMapper;

  @RequestMapping(value = "/stocks/{id}", method = RequestMethod.GET)
  public ResponseEntity<StockDTO> get(@PathVariable("id") String stockId,
      final HttpServletResponse response) {
    log.debug("Say something....");
    Stock stock = stockService.findOne(stockId).orElseThrow(MyResourceNotFoundException::new);
    stock.setStockDailyRecords(Collections.emptySet());
    return ResponseEntity.ok(convertToDTO(stock));
  }

  private StockDTO convertToDTO(Stock stock) {
    return modelMapper.map(stock, StockDTO.class);
  }

  @RequestMapping(value = "/stocks", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> create(@Valid @RequestBody final Stock stock,
      final HttpServletResponse response) {
    Preconditions.checkNotNull(stock);
    final Stock newStock = stockService.create(stock);
    final String idOfCreatedResource = newStock.getUuid().toString();
    return ResponseEntity.ok(idOfCreatedResource);
  }

  @DeleteMapping("/stocks/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> delete(@PathVariable String id) {
    stockService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "/stocks", params = {"page", "size"}, method = RequestMethod.GET)
  public ResponseEntity<List<StockDTO>> findPaginated(@RequestParam("page") final int page,
      @RequestParam("size") final int size,
      final UriComponentsBuilder uriBuilder,
      final HttpServletResponse response) {
    Page<Stock> resultPage = stockService.findPaginated(page, size);
    if (page > resultPage.getTotalPages()) {
      throw new MyResourceNotFoundException();
    }
    List<StockDTO> stockDoes = resultPage.getContent()
        .stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(stockDoes);
  }

  @RequestMapping(value = "/stocks/all", method = RequestMethod.GET)
  public List<StockDTO> findAll() {
    return stockService.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(new StockValidator());
  }
}
