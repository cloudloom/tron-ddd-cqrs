package com.tracebucket.tron.rest.validation;

import com.tracebucket.tron.rest.exception.X1Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

/**
 * @author ffazil
 * @since 14/06/15
 */
@ControllerAdvice
public class TronControllerAdvisor {
    private final Logger log = LoggerFactory.getLogger(TronControllerAdvisor.class);

    private MessageSource messageSource;

    @Autowired
    public TronControllerAdvisor(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationError processValidationError(MethodArgumentNotValidException ex) {
        log.debug("Handling rest API validation error");

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(X1Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleOrganizationException(X1Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), ex.getHttpStatus());
    }

    private ValidationError processFieldErrors(List<FieldError> fieldErrors) {
        ValidationError errors = new ValidationError();

        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            log.debug("Adding error message: {} to attribute: {}", localizedErrorMessage, fieldError.getField());
            errors.addFieldError(fieldError.getObjectName(), fieldError.getRejectedValue(), fieldError.getField(), localizedErrorMessage);
        }

        return errors;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If a message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }



}