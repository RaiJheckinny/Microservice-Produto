package br.com.produto.controller;

import br.com.produto.domain.DTO.CreateProdutoDTO;
import br.com.produto.domain.Produto;
import br.com.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    ProdutoService service;

    @PostMapping("/create")
    public ResponseEntity<Produto> createProduto(@RequestBody CreateProdutoDTO createProdutoDTO){
        Produto produto = service.create(createProdutoDTO);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Produto>> getAllProduto(){
        List<Produto> produtos = service.getAll();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Produto> removeProduto(@PathVariable UUID id){
        Produto produto = service.getById(id);
        service.remove(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }
}
