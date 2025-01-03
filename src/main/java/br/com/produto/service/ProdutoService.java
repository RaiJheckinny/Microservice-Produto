package br.com.produto.service;

import br.com.produto.domain.DTO.AlterProdutoDTO;
import br.com.produto.domain.DTO.CreateProdutoDTO;
import br.com.produto.domain.Produto;
import br.com.produto.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService implements IProdutoService{
    @Autowired
    IProdutoRepository repository;

    @Override
    public Produto create(CreateProdutoDTO createProdutoDTO) {
        Produto produto = new Produto(createProdutoDTO);
        produto = repository.save(produto);
        return produto;
    }

    @Override
    public Produto alter(AlterProdutoDTO alterProdutoDTO, UUID id) {
        Produto produto = repository.findById(id).get();
        produto.setName(alterProdutoDTO.name());
        produto.setDescription(alterProdutoDTO.description());
        produto.setPrice(alterProdutoDTO.price());

        repository.save(produto);
        return produto;
    }

    @Override
    public Produto getById(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public Produto remove(UUID id) {
        Produto produto = repository.findById(id).get();
        repository.delete(produto);
        return produto;
    }


    @Override
    public List<Produto> getAll() {
        return repository.findAll();
    }
    public Optional<Produto> getByIdfind(UUID id){
        return repository.findById(id);
    }
}
