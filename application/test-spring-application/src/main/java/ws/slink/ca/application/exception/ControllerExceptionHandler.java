package ws.slink.ca.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ws.slink.ca.api.exception.account.AccountNotFoundException;
import ws.slink.ca.api.exception.account.InvalidBalanceException;
import ws.slink.ca.domain.exception.InvalidAccountIdException;
import ws.slink.ca.domain.exception.NegativeAmountException;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({
        InvalidBalanceException.class,
        AccountNotFoundException.class,
        InvalidAccountIdException.class,
        NegativeAmountException.class
    })
    ResponseEntity<Object> handle(final Exception e) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_PROBLEM_JSON)
            .body(Map.of("warning", e.getMessage()))
        ;
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handleFallback(final Exception e) {
        log.error("unhandled exception: ", e);
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_PROBLEM_JSON)
            .body(
                Map.of(
                    "error", e.getMessage(),
                    "type", e.getClass().getSimpleName()
                )
            )
        ;
    }

}
