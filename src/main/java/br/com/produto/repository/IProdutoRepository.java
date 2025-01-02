package br.com.produto.repository;

import br.com.produto.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProdutoRepository extends JpaRepository<Produto, UUID> {
}
