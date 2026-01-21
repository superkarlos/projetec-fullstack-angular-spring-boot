package com.project.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.model.Categoria;
import com.project.app.services.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class IndexController {

    private final CategoriaService categoriaService;

    @GetMapping()
    public ResponseEntity<List<Categoria>> index(){
        List<Categoria> list = this.categoriaService.getall();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public  ResponseEntity<String> save(@RequestBody Categoria categoria){
        categoriaService.salvar(categoria); 
        return ResponseEntity.status(HttpStatus.CREATED).body("Salvo");
    }

}
