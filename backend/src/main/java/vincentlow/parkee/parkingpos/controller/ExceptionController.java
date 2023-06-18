package vincentlow.parkee.parkingpos.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import vincentlow.parkee.parkingpos.model.constant.ErrorMessage;
import vincentlow.parkee.parkingpos.model.response.api.ApiResponse;
import vincentlow.parkee.parkingpos.model.response.exception.BadRequestException;
import vincentlow.parkee.parkingpos.model.response.exception.ConflictException;
import vincentlow.parkee.parkingpos.model.response.exception.NotFoundException;
import vincentlow.parkee.parkingpos.model.response.exception.ServiceUnavailableException;

@Slf4j
@RestControllerAdvice
public class ExceptionController extends BaseController {

  @ExceptionHandler(value = {BadRequestException.class})
  public ResponseEntity<ApiResponse> handleBadRequestException(BadRequestException ex) {

    log.info("#ExceptionController#handleBadRequestException caught error: {}", ex.getMessage());
    return toErrorResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {NotFoundException.class})
  public ResponseEntity handleNotFoundException(NotFoundException ex) {

    log.info("#ExceptionController#handleNotFoundException caught error: {}", ex.getMessage());
    return toErrorResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {ConflictException.class})
  public ResponseEntity handleConflictException(ConflictException ex) {

    log.info("#ExceptionController#handleConflictException caught error: {}", ex.getMessage());
    return toErrorResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = {DataAccessException.class, ServiceUnavailableException.class})
  public ResponseEntity handleServiceUnavailableException(RuntimeException ex) {

    log.info("#ExceptionController#handleServiceUnavailableException caught error: {}", ex.getMessage());
    return toErrorResponseEntity(ErrorMessage.SERVICE_TEMPORARILY_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE);
  }
}
