package br.com.fiap.Helper;

import br.com.fiap.model.Produto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProdutoHelper {
    public static Produto gerarProduto() {
        return new Produto(1, "Meu produto", "Produto de boa qualidade",
                25, 15.50);
    }

    public static Produto gerarProduto2() {
        return new Produto(2, "tenis Adidas", "tenis para corrida n.34",
                12, 311.4);
    }

    public static String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}
