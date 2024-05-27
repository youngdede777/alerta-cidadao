package br.com.fiap.alerta_cidadao.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e) {
        Map<String, String> mapErros = new HashMap<>();
        List<FieldError> fields = e.getBindingResult().getFieldErrors();

        for (FieldError field : fields) {
            mapErros.put(field.getField(), field.getDefaultMessage());
        }

        return mapErros;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleDataIntegrity() {
        Map<String, String> mapErros = new HashMap<>();
        mapErros.put("erro", "Já cadastrado!");
        return mapErros;
    }
}
