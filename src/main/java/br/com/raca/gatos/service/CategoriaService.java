package br.com.raca.gatos.service;

import br.com.raca.gatos.client.TheCatsClient;
import br.com.raca.gatos.entity.Categoria;
import br.com.raca.gatos.model.CategoriaEnum;
import br.com.raca.gatos.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CategoriaService {

    private  static Logger log = LoggerFactory.getLogger(RacaService.class);

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(TheCatsClient theCatsClient, CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    private final ModelMapper mapper = new ModelMapper();

    public Categoria salvarCategoriaFotoInfo(Map<String, String> categoria) {
        var categEnum = CategoriaEnum.trasCategoriaEnumPorId(categoria.get("id"));
        var categEntity =  new Categoria();
        categEntity.setCategoria(categEnum);
        categEntity.setId(Long.parseLong(categEnum.getCategoria()));
        return salvarCategoria(categEntity);
    }

    public Categoria salvarCategoria(Categoria categoria) {
        log.info("Salvando Categoria");
        return categoriaRepository.save(categoria);
    }
}
