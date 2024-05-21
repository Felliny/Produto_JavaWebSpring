package br.edu.fateczl.Produto_JavaWebSpring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
    int codigo;
    String nome;
    float preco_unitario;
    int quantidade;
}
