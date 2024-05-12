package br.com.fiap.controller;

import br.com.fiap.Helper.ProdutoHelper;
import br.com.fiap.model.Produto;
import br.com.fiap.service.ProdutoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProdutoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProdutoService produtoService;

    AutoCloseable autoMock;

    @BeforeEach
    void setup() {
        autoMock = MockitoAnnotations.openMocks(this);
        ProdutoController produtoController = new ProdutoController(produtoService);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoMock.close();
    }

    @Test
    void testRegistrarProduto() throws Exception {
        Produto produto = ProdutoHelper.gerarProduto();
        when(produtoService.cadastrarProduto(any(Produto.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ProdutoHelper.asJsonString(produto)))
                .andExpect(status().isCreated());

        verify(produtoService, times(1)).cadastrarProduto(any(Produto.class));
    }

    @Test
    void testListarProdutos() throws Exception {
        mockMvc.perform(get("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(produtoService, times(1)).listarProdutos();
    }

    @Test
    void testObterProduto() throws Exception {
        Integer id = 21;
        Produto produto = ProdutoHelper.gerarProduto();
        produto.setId(id);

        mockMvc.perform(get("/api/produtos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(produtoService, times(1)).obterProduto(id);
    }

    @Test
    void testExcluirProduto() throws Exception {
        Integer id = 22;

        mockMvc.perform(delete("/api/produtos/{id}", id))
                .andExpect(status().isOk());

        verify(produtoService, times(1)).excluirProduto(any(Integer.class));
    }

    @Test
    void testAtualizarProduto() throws Exception {
        Integer id = 23;
        Produto produto = ProdutoHelper.gerarProduto();
        produto.setId(id);

        when(produtoService.atualizarProduto(any(Integer.class), any(Produto.class)))
                .thenAnswer(i -> i.getArgument(1));

        mockMvc.perform(put("/api/produtos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ProdutoHelper.asJsonString(produto)))
                .andExpect(status().isOk());

        verify(produtoService, times(1)).atualizarProduto(any(Integer.class), any(Produto.class));
    }

    @Test
    void testAtualizarEstoque() throws Exception {
        Integer id = 23;
        Produto produto = ProdutoHelper.gerarProduto();
        produto.setQuantidade_estoque(produto.getQuantidade_estoque() - 5);
        produto.setId(id);

        when(produtoService.atualizarEstoque(any(Integer.class), anyInt(), anyString()))
                .thenReturn(produto);

        mockMvc.perform(put("/api/produtos/atualizar/estoque/{produtoId}/{quantidade}/{entradaSaida}",
                        id, produto.getQuantidade_estoque(), "retirar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ProdutoHelper.asJsonString(produto)))
                .andExpect(status().isOk());

        verify(produtoService, times(1)).atualizarEstoque(any(Integer.class), anyInt(), anyString());
    }

}
