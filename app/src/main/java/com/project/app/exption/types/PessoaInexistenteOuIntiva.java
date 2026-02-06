package com.project.app.exption.types;

public class PessoaInexistenteOuIntiva extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public PessoaInexistenteOuIntiva(String msg){
        super(msg);
    }
}
