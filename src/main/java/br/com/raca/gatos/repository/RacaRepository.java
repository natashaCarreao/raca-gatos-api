package br.com.raca.gatos.repository;

import br.com.raca.gatos.entity.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Long>, JpaSpecificationExecutor<Raca> {

    Optional<Raca> findByPaisOrigem(String paisOrigem);

    List<Raca> findAllByTemperamentoContains(String temperamento);

    Optional<Raca> findByIdExternoBreed(String id);

    Optional<Raca> findByNome(String id);

    Optional<Raca> findById(Long id);

    Boolean existsBy();
}
