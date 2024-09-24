package com.gn.testtaskriga.advice;

import com.gn.testtaskriga.dto.response.ApiErrorDto;
import com.gn.testtaskriga.dto.response.ErrorDto;
import com.gn.testtaskriga.exception.auth.AccessDeniedException;
import com.gn.testtaskriga.exception.auth.UserLoginException;
import com.gn.testtaskriga.exception.conflict.InvalidTokenRequestException;
import com.gn.testtaskriga.exception.exist.UserAlreadyExistException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            InvalidTokenRequestException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiErrorDto> handleBadRequest(
            RuntimeException ex, WebRequest request) {
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.BAD_REQUEST,
                ((ServletWebRequest)request).getRequest().getRequestURI().toString(),
                new ErrorDto(ex.getClass().getName(), ex.getMessage())
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            UserLoginException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiErrorDto> handleLoginBadCredentials(
            RuntimeException ex, WebRequest request) {
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.BAD_REQUEST,
                ((ServletWebRequest)request).getRequest().getRequestURI().toString(),
                new ErrorDto(ex.getClass().getName(), "Bad credentials! Invalid Username or Password")
        );
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            UserAlreadyExistException.class,
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<ApiErrorDto> handleConflict(
            RuntimeException ex, WebRequest request) {
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.CONFLICT,
                ((ServletWebRequest)request).getRequest().getRequestURI().toString(),
                new ErrorDto(ex.getClass().getName(), ex.getMessage())
        );
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {
            AccessDeniedException.class
    })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<ApiErrorDto> handleForbidden(
            RuntimeException ex, WebRequest request) {
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.FORBIDDEN,
                ((ServletWebRequest)request).getRequest().getRequestURI().toString(),
                new ErrorDto(ex.getClass().getName(), ex.getMessage())
        );
        return new ResponseEntity<>(errorDto, HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.BAD_REQUEST,
                ((ServletWebRequest)request).getRequest().getRequestURI().toString(),
                new ErrorDto(ex.getClass().getName(), ex.getMessage())
        );
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.BAD_REQUEST,
                ((ServletWebRequest)request).getRequest().getRequestURI().toString(),
                new ErrorDto(ex.getClass().getName(), ex.getMessage())
        );
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorDto> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> new ErrorDto(ex.getObjectName(), objectError.getDefaultMessage()))
                .toList();
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.BAD_REQUEST,
                ((ServletWebRequest)request).getRequest().getRequestURI().toString(),
                errors.toArray(new ErrorDto[0])
        );
        return handleExceptionInternal(ex, errorDto, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.BAD_REQUEST,
                ((ServletWebRequest)request).getRequest().getRequestURI().toString(),
                new ErrorDto(ex.getClass().getName(), ex.getMessage())
        );
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.NOT_FOUND,
                ((ServletWebRequest)request).getRequest().getRequestURI().toString(),
                new ErrorDto(ex.getClass().getName(), ex.getMessage())
        );
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiErrorDto errorDto = new ApiErrorDto(HttpStatus.METHOD_NOT_ALLOWED,
                ((ServletWebRequest)request).getRequest().getRequestURI().toString(),
                new ErrorDto(ex.getClass().getName(), ex.getMessage())
        );
        return handleExceptionInternal(ex, errorDto, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }

}
