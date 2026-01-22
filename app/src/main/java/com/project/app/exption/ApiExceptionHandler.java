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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.app.exption.types.BusinessException;
import com.project.app.exption.types.ErrorCustonController;

@ControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    /*
     * ===============================
     * JSON inválido / corpo mal formado
     * ===============================
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle(getMessage("mensagem.invalida"));
        problem.setDetail("O corpo da requisição está inválido ou mal formatado");
        problem.setProperty("developerMessage", getDevMessage(ex));

        return ResponseEntity.badRequest().body(problem);
    }

    /*
     * ===============================
     * Validação de campos (@Valid)
     * ===============================
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        List<FieldErrorResponse> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorResponse(
                        error.getField(),
                        messageSource.getMessage(error, LocaleContextHolder.getLocale())))
                .toList();

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Erro de validação");
        problem.setDetail("Um ou mais campos estão inválidos");
        problem.setProperty("errors", errors);

        return ResponseEntity.badRequest().body(problem);
    }

    /*
     * ===============================
     * Regra de negócio
     * ===============================
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> handleBusinessException(
            BusinessException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Erro de regra de negócio");
        problem.setDetail(ex.getMessage());

        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(ErrorCustonController.class)
    public ResponseEntity<ProblemDetail> ErrorCustonControllerException(
            ErrorCustonController ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Erro de regra de negócio");
        problem.setDetail(ex.getMessage());

        return ResponseEntity.badRequest().body(problem);
    }

    /*
     * ===============================
     * Erro genérico (fallback)
     * ===============================
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(Exception ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Erro interno do servidor");
        problem.setDetail("Ocorreu um erro inesperado. Tente novamente mais tarde.");
        problem.setProperty("developerMessage", ex.getMessage());

        return ResponseEntity.internalServerError().body(problem);
    }

    /*
     * ===============================
     * Helpers
     * ===============================
     */
    private String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    private String getDevMessage(Throwable ex) {
        String message = ex.getMessage();
        return (message == null || message.isBlank())
                ? "Erro técnico interno"
                : message;
    }

}
