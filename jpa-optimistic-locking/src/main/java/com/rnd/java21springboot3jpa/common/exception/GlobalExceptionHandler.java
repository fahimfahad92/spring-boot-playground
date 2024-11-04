package com.rnd.java21springboot3jpa.common.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ObjectOptimisticLockingFailureException.class})
  protected ResponseEntity<ProblemDetail> handleObjectOptimisticLockingFailureException(
      ObjectOptimisticLockingFailureException ex) {

    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(
            HttpStatusCode.valueOf(400), "Another transaction is ongoing");
    return ResponseEntity.badRequest().body(problemDetail);
  }

  @ExceptionHandler(value = {InsufficientBalanceException.class})
  protected ResponseEntity<ProblemDetail> handleInsufficientBalanceException(
      InsufficientBalanceException ex) {

    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(
            HttpStatusCode.valueOf(400), "You do not have sufficient balance");
    return ResponseEntity.badRequest().body(problemDetail);
  }
}
