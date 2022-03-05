package br.com.raca.gatos.exception;

import br.com.raca.gatos.model.FalhaConstants;
import br.com.raca.gatos.model.rest.FalhaResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private  static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FalhaResponse> handleGenericThrowable(final Exception ex, final HttpServletRequest request) {

        log.error(FalhaConstants.MSG_ERRO_GENERICA, ex);

        return ResponseEntity.internalServerError().body(new FalhaResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(), System.currentTimeMillis(), request.getRequestURI()));
    }

    @ExceptionHandler({FeignException.class, FeignException.Unauthorized.class, MissingRequestHeaderException.class})
    public ResponseEntity<FalhaResponse> feignUnauthorizedException(Exception ex, HttpServletRequest request) {

        log.error(FalhaConstants.MSG_ERRO_INTEGRACAO_API, ex);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new FalhaResponse(HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(), System.currentTimeMillis(), request.getRequestURI()));
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<FalhaResponse> invalidParameterException(InvalidParameterException ex, HttpServletRequest request) {

        log.error(FalhaConstants.MSG_ERRO_PARAMETROS_INVALIDOS, ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FalhaResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), System.currentTimeMillis(), request.getRequestURI()));

    }
}
