package br.com.produto.service;

import br.com.produto.domain.DTO.AlterProdutoDTO;
import br.com.produto.domain.DTO.CreateProdutoDTO;
import br.com.produto.domain.Produto;

import java.util.List;
import java.util.UUID;

public interface IProdutoService {
    public Produto create(CreateProdutoDTO createProdutoDTO);
    public Produto alter(AlterProdutoDTO alterProdutoDTO, UUID id);
    public Produto getById(UUID id);
    public Produto remove(UUID id);
    public List<Produto> getAll();
}
