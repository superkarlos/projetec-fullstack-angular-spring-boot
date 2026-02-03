package com.project.app.exption;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.project.app.exption.types.BusinessException;
import com.project.app.exption.types.EntityNotFoundException;


@RestControllerAdvice
public class ApiExceptionHandler {

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /* ================================
       400 - Requisição inválida
       ================================ */

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle(getMessage("mensagem.invalida"));
        problem.setDetail("O corpo da requisição está inválido ou mal formatado.");
        problem.setProperty("developerMessage", getDevMessage(ex));

        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        List<FieldErrorResponse> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorResponse(
                        error.getField(),
                        messageSource.getMessage(error, LocaleContextHolder.getLocale())
                ))
                .toList();

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Erro de validação");
        problem.setDetail("Um ou mais campos estão inválidos.");
        problem.setProperty("errors", errors);

        return ResponseEntity.badRequest().body(problem);
    }

    /* ================================
       400 - Regras de negócio
       ================================ */

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> handleBusinessException(
            BusinessException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Violação de regra de negócio");
        problem.setDetail(ex.getMessage());
        problem.setProperty("code", "ERR-BUSINESS");

        return ResponseEntity.badRequest().body(problem);
    }

    /* ================================
       404 - Recurso não encontrado
       ================================ */

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFound(
            EntityNotFoundException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Recurso não encontrado");
        problem.setDetail(ex.getMessage());
        problem.setProperty("code", "ERR-404");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }

    /* ================================
       500 - Erro inesperado
       ================================ */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(Exception ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Erro interno do servidor");
        problem.setDetail("Ocorreu um erro inesperado. Tente novamente mais tarde.");
        problem.setProperty("developerMessage", getDevMessage(ex));
        problem.setProperty("code", "ERR-500");

        return ResponseEntity.internalServerError().body(problem);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNoHandlerFound(NoHandlerFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Endpoint não encontrado");
        problem.setDetail("A URL informada não existe.");
        return problem;
    }

    /* ================================
       Helpers
       ================================ */

    private String getMessage(String code) {
        return messageSource.getMessage(
                code,
                null,
                LocaleContextHolder.getLocale()
        );
    }

    private String getDevMessage(Throwable ex) {
        String message = ex.getMessage();
        return (message == null || message.isBlank())
                ? "Erro técnico interno"
                : message;
    }
}
