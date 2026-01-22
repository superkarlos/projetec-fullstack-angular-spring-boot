package com.project.app.event;

import org.springframework.context.ApplicationEvent;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

@Getter
public class EventoCriado extends ApplicationEvent{

    private static final long serialVersion = 1L;
    private HttpServletResponse response;
    private Long codigo;

    public EventoCriado(Object source,HttpServletResponse response, Long codigo) {
        super(source);

        this.codigo = codigo;
        this.response = response;
    }
    
}
