package com.project.app.event.lists;

import java.net.URI;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.app.event.EventoCriado;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class RecursoListEvent  implements ApplicationListener<EventoCriado>{

    @Override
    public void onApplicationEvent(EventoCriado event) {
        HttpServletResponse response = event.getResponse();
        Long codigo = event.getCodigo();
        getUri(response, codigo);
    }

    private void getUri(HttpServletResponse response, Long codigo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(codigo).toUri();   
        response.setHeader("location", uri.toASCIIString());
    }
    
}
