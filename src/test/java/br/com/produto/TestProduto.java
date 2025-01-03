package br.com.produto;

import br.com.produto.domain.DTO.AlterProdutoDTO;
import br.com.produto.domain.DTO.CreateProdutoDTO;
import br.com.produto.domain.Produto;
import br.com.produto.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class TestProduto {
    @Autowired
    ProdutoService service;
    @Test
    public void createProduto_Happy_Path(){
        CreateProdutoDTO createProdutoDTO = new CreateProdutoDTO("computador", "i7, 16Ram, water coler", 1000.99);
        Produto produto = service.create(createProdutoDTO);

        Produto produtoBD = service.getById(produto.getId());

        Assertions.assertEquals(produto.getId(), produtoBD.getId());
    }

    @Test
    public void alterProduto_Happy_Path(){
        CreateProdutoDTO createProdutoDTO = new CreateProdutoDTO("computador", "i7, 16Ram, water coler", 1000.99);
        Produto produto = service.create(createProdutoDTO);

        AlterProdutoDTO alterProdutoDTO = new AlterProdutoDTO("computador", "i5, 16Ram, water coler", 2000.99);
        service.alter(alterProdutoDTO, produto.getId());

        Produto produtoBD = service.getById(produto.getId());

        Assertions.assertEquals("i5, 16Ram, water coler" , produtoBD.getDescription());
        Assertions.assertEquals(2000.99, produtoBD.getPrice());
    }

    @Test
    public void deleteProduto_Happy_Path(){
        CreateProdutoDTO createProdutoDTO = new CreateProdutoDTO("computador", "i7, 16Ram, water coler", 1000.99);
        Produto produto = service.create(createProdutoDTO);
        Produto produtoBD = service.getById(produto.getId());
        Assertions.assertNotNull(produtoBD);

        service.remove(produto.getId());
        Optional<Produto> produtoBD2 = service.getByIdfind(produto.getId());
        Assertions.assertTrue(produtoBD2.isEmpty());
    }

    @Test
    public void getAllProduto_HappyPath(){
        CreateProdutoDTO createProdutoDTO = new CreateProdutoDTO("computador", "i7, 16Ram, water coler", 1000.99);
        Produto produto = service.create(createProdutoDTO);

        CreateProdutoDTO createProdutoDTO2 = new CreateProdutoDTO("notebook", "i5, 8Ram, air coler", 500.00);
        Produto produto2 = service.create(createProdutoDTO2);

        List<Produto> produtos = service.getAll();

        Assertions.assertNotNull(produtos);
        Assertions.assertEquals(2, produtos.size());
        Assertions.assertEquals(produtos.get(0).getId(), produto.getId());
        Assertions.assertEquals(produtos.get(1).getId(), produto2.getId());
    }
    @Test
    public void getById(){
        CreateProdutoDTO createProdutoDTO = new CreateProdutoDTO("computador", "i7, 16Ram, water coler", 1000.99);
        Produto produto = service.create(createProdutoDTO);

        Produto produtoBD = service.getById(produto.getId());

        Assertions.assertEquals(produto.getName(), produtoBD.getName());
        Assertions.assertEquals(produto.getId(), produtoBD.getId());
        Assertions.assertEquals(produto.getDescription(), produtoBD.getDescription());
        Assertions.assertEquals(produto.getPrice(), produtoBD.getPrice());
    }
}
