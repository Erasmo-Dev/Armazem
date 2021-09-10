package br.com.estoque.backend.repository;

import br.com.estoque.backend.models.Armazem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmazemRepository extends JpaRepository<Armazem,Long> {
}
