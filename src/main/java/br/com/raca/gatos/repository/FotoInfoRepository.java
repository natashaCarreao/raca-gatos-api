package br.com.raca.gatos.repository;

import br.com.raca.gatos.entity.FotoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoInfoRepository extends JpaRepository<FotoInfo, Long> {

    List<FotoInfo> findAllByRacaIdRaca(Long idRaca);


}
