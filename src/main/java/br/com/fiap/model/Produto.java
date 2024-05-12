package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nm_produto", nullable = false)
    private String nome;

    @Column(name = "ds_descricao")
    private String descricao;

    @Column(name = "qt_estoque", nullable = false)
    private Integer quantidade_estoque;

    @Column(name = "pr_produto", nullable = false)
    private double preco;
}
