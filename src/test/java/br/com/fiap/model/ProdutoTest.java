package br.com.fiap.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    public void testEmptyConstrutor() {
        Produto produto = new Produto();
        assertNull(produto.getId());
        assertNull(produto.getNome());
        assertNull(produto.getDescricao());
        assertNull(produto.getQuantidade_estoque());
        assertEquals(0.0, produto.getPreco());
    }

    @Test
    public void testArgsConstrutor() {
        Produto produto = new Produto(1, "Meu produto", "Produto de boa qualidade", 25, 15.50);
        assertNotNull(produto);
    }

    @Test
    public void testGetSetId() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Produto de Teste");
        produto.setDescricao("Descrição do produto de teste");
        produto.setQuantidade_estoque(10);
        produto.setPreco(100.00);

        assertTrue(Integer.valueOf(1).equals(produto.getId()));
        assertTrue("Produto de Teste".equals(produto.getNome()));
        assertTrue("Descrição do produto de teste".equals(produto.getDescricao()));
        assertTrue(Integer.valueOf(10).equals(produto.getQuantidade_estoque()));
        assertEquals(100.00, produto.getPreco());;
    }

}
