package br.com.fiap.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ClienteResponse {
    private Integer id;
    private String nome;
    //private String cpf;
    //private String email;
    //private String cep;
    //private String endereco;
    //private String bairro;
    //private String cidade;
    //private String uf;
    //private String complemento;
}
