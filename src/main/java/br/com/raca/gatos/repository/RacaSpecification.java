package br.com.raca.gatos.repository;

import br.com.raca.gatos.entity.Raca;
import org.springframework.data.jpa.domain.Specification;

public class RacaSpecification {

    public static Specification<Raca> temperamento(String temperamento) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("temperamento"), temperamento);
    }

    public static Specification<Raca> origem(String origem) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("origem"), origem);
    }
}
