package br.com.fiap.service;

import br.com.fiap.model.Produto;
import br.com.fiap.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public ResponseEntity<?> obterProduto(Integer produtoId) {
        Produto produto = produtoRepository.findById(produtoId).orElse(null);

        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado");
        }
    }

    public Produto atualizarProduto(Integer produtoId, Produto novoProduto) {
        Produto produtoExistente = produtoRepository.findById(produtoId).orElse(null);

        if(produtoExistente != null) {
            produtoExistente.setNome(novoProduto.getNome());
            produtoExistente.setDescricao(novoProduto.getDescricao());
            produtoExistente.setQuantidade_estoque(novoProduto.getQuantidade_estoque());
            produtoExistente.setPreco(novoProduto.getPreco());

            return produtoRepository.save(produtoExistente);
        } else {
            throw new NoSuchElementException("Produto nao encontrado");
        }
    }

    public void excluirProduto(Integer produtoId) {
        Produto produtoExistente = produtoRepository.findById(produtoId).orElse(null);

        if (produtoExistente != null) {
            produtoRepository.deleteById(produtoId);
        } else {
            throw new NoSuchElementException("Produto nao encontrado");
        }
    }

    public Produto atualizarEstoque(Integer produtoId, int quantidade, String entradaSaida) {
        Produto produto = produtoRepository.findById(produtoId).orElse(null);

        if (produto != null && entradaSaida.equals("retirar")) {
            produto.setQuantidade_estoque(produto.getQuantidade_estoque() - quantidade);

            return produtoRepository.save(produto);
        }
        if (produto != null && entradaSaida.equals("inserir")) {
            produto.setQuantidade_estoque(produto.getQuantidade_estoque() + quantidade);

            return produtoRepository.save(produto);
        }
        return null;
    }
}