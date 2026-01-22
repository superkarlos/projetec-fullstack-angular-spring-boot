package com.project.app.exption;

import org.hibernate.validator.internal.util.logging.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExeceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String msg = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String msgdev = ex.getCause().toString();

        return handleExceptionInternal(ex, new ErrorMgs(msg, msgdev), headers, HttpStatus.BAD_REQUEST, request);
    }

    public static class ErrorMgs {

        private String msgUser;
        private String msgDev;

        public ErrorMgs(String msguser, String msgdev) {
            this.msgUser = msguser;
            this.msgDev = msgdev;
        }

        public String getMsgUser() {
            return msgUser;
        }

        public void setMsgUser(String msgUser) {
            this.msgUser = msgUser;
        }

        public String getMsgDev() {
            return msgDev;
        }

        public void setMsgDev(String msgDev) {
            this.msgDev = msgDev;
        }
    }

}
