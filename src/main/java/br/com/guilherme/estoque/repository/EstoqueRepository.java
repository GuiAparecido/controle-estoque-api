package br.com.guilherme.estoque.repository;

import br.com.guilherme.estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque,Long> {
}
