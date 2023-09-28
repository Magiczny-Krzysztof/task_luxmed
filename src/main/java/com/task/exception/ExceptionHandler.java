package com.task.exception;

import com.task.controller.UserController;
import com.task.dto.ErrorResponseDto;
import com.task.enums.ExceptionMessages;
import feign.FeignException;
import org.jboss.logging.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;

import static com.task.configuration.audit.AuditConstants.UUID_MDC;

@ControllerAdvice(assignableTypes = {UserController.class})
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<Object> handleFeignException(FeignException.NotFound ex, WebRequest request) {

        return new ResponseEntity<>(ErrorResponseDto.builder()
                .message(ExceptionMessages.NOT_FOUND.getDescription())
                .messageUUID((String) MDC.get(UUID_MDC)).build(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {

        return new ResponseEntity<>(ErrorResponseDto.builder()
                .message(ExceptionMessages.VALIDATION_FAILED.getDescription())
                .messageUUID((String) MDC.get(UUID_MDC)).build(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {

        return new ResponseEntity<>(ErrorResponseDto.builder()
                .message(ExceptionMessages.UNKNOWN_EXCEPTION.getDescription())
                .messageUUID((String) MDC.get(UUID_MDC)).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
