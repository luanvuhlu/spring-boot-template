package com.luanvv.spring.springstructure.configurations.handlers;

import com.google.common.base.Preconditions;
import com.luanvv.spring.springstructure.exeptions.MyResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
@ResponseBody
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    if (logger.isDebugEnabled()) {
      logger.debug("Missing parameters");
    }
    return super.handleMissingServletRequestParameter(ex, headers, status, request);
  }

  @ExceptionHandler(MyResourceNotFoundException.class)
  protected ResponseEntity<Object> handleResourceNotFound(MyResourceNotFoundException ex) {
    if (logger.isDebugEnabled()) {
      logger.debug("Missing parameters");
    }
    return new ResponseEntity<>(error("ResourceNotFound", "Resource not found"),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<VndErrors> handleConstraintViolation(ConstraintViolationException ex) {
    List<String> errors = ex.getConstraintViolations()
        .stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.toList());
    return ResponseEntity.badRequest().body(error(ex, errors));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<VndErrors> handleEntityNotfound(EntityNotFoundException ex) {
    return new ResponseEntity<>(error("EntityNotFound", "Entity not found"), HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    return ResponseEntity.badRequest().body(ex.getBindingResult());
  }

  /**
   * Error.
   *
   * @param logRef the log ref => i18n key
   * @param error  the error => Default message
   * @return the vnd errors
   */
  private VndErrors error(String logRef, String error) {
    return new VndErrors(logRef, error);
  }

  private <E extends Exception> VndErrors error(E e, List<String> errors) {
    Preconditions.checkNotNull(errors);
    Preconditions.checkArgument(!errors.isEmpty());
    String msg = e.getClass().getSimpleName();
    return new VndErrors(joinErrorMses(errors), msg);
  }

  private String joinErrorMses(List<String> errors) {
    return String.join("", errors);
  }

}
