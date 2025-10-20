package com.kolotree.task1.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler({ExpiredJwtException.class, JwtException.class})
    public ResponseEntity incorrectJwt(JwtException ex) {
        logger.warn("JWT was incorrect", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler({ChangeSetPersister.NotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity notFoundException(ChangeSetPersister.NotFoundException ex) {
        logger.warn("Resource not found", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource was not found");
    }

    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
    public ResponseEntity illegalArgumentException(Exception ex) {
        logger.warn(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity authorizationDeniedException(AuthorizationDeniedException ex, HttpServletRequest request) {
        logger.warn("User tried to access forbidden method for its role. User principal:" + request.getUserPrincipal(), ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(NotEnoughVacationDays.class)
    public ResponseEntity notEnoughtVacationDays(Exception ex, HttpServletRequest request) {
        logger.warn("User tried to take more vacation than they have left, Username: " + request.getRemoteUser());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity usernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        logger.warn("Username Not Found. ", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity generalException(Exception ex, HttpServletRequest request) {
        logger.error("Unexpected error for {} request on {}", request.getMethod(), request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
