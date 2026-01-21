package com.project.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.app.model.Categoria;
import com.project.app.services.CategoriaService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class IndexController {

    private final CategoriaService categoriaService;

    @GetMapping()
    public ResponseEntity<List<Categoria>> index() {
        List<Categoria> list = this.categoriaService.getall();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria, HttpServletResponse response) {

        Categoria data = categoriaService.salvar(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{codigo}").buildAndExpand(data.getCodigo()).toUri();
        response.setHeader("location", uri.toASCIIString());
        return ResponseEntity.created(uri).body(data);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> get(@PathVariable Long codigo) {

        Optional<Categoria> data = categoriaService.findyById(codigo);

        if (data.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Categoria não encontrada");
        }

        return ResponseEntity.ok(data.get());
    }

}
