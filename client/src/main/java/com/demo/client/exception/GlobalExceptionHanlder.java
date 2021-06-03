package com.demo.client.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHanlder  extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(RuntimeException.class)
	  public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {

	    log.debug("Exception has occured : " + ex);
	    String msg = ex.getMessage();
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("timestamp", LocalDateTime.now());
	    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	    body.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
	    body.put("message", msg);
	    body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI().toString());

	    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

	  }
}
