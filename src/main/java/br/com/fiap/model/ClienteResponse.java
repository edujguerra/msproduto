package br.com.fiap.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ClienteResponse {
    private Integer id;
    private String nome;
}
