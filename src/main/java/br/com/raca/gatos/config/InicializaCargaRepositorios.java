package br.com.raca.gatos.config;

import br.com.raca.gatos.model.CategoriaEnum;
import br.com.raca.gatos.model.FalhaConstants;
import br.com.raca.gatos.service.FotoInfoService;
import br.com.raca.gatos.service.RacaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.zip.DataFormatException;

@Component
public class InicializaCargaRepositorios {

    private  static Logger log = LoggerFactory.getLogger(RacaService.class);

    private final RacaService racaService;
    private final FotoInfoService fotoInfoService;

    @Autowired
    public InicializaCargaRepositorios(RacaService racaService, FotoInfoService fotoInfoService) {
        this.racaService = racaService;
        this.fotoInfoService = fotoInfoService;
    }

    @Scheduled(cron = "${cron.inicia.carga.dados}")
    public void iniciaCargaBanco () throws DataFormatException {

        if (racaService.verificaCargaTabela()){
            log.info("Buscando Breeds em The Cats API");
            var racas = racaService.buscarRacasCatsAPI("20");

            if(racas.isEmpty()) {
                log.error(FalhaConstants.MSG_LISTA_FOTOINFO_VAZIA);
                throw new DataFormatException(FalhaConstants.ERRO_SALVAR_LISTA_RACAS);
            }

            log.info("Buscando Images by breedIds em The Cats API");
            var fotos = fotoInfoService.montaListaDeFotoInfoParaRaca(racas);

            var categorias = CategoriaEnum.values();

            log.info("Buscando Images by breedIds em The Cats API");
            for (CategoriaEnum categria : categorias) {
                fotos.addAll(fotoInfoService.montaListaDeFotoInfoComCategoria(categria));
            }

            var fotosSalvas = fotoInfoService.salvarTodasFotos(fotos);

            if(fotosSalvas.isEmpty()) {
                log.error(FalhaConstants.MSG_LISTA_FOTOINFO_VAZIA);
                throw new DataFormatException(FalhaConstants.ERRO_SALVAR_LISTA_RACAS);
            }
            log.info("Carga de tabelas Finalizada com Sucesso");
        }else {
            log.info("Carga de tabelas OK");
        }
    }
}
