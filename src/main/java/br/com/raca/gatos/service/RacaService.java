package br.com.raca.gatos.service;

import br.com.raca.gatos.client.TheCatsClient;
import br.com.raca.gatos.entity.Raca;
import br.com.raca.gatos.model.RacaDto;
import br.com.raca.gatos.repository.RacaRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
public class RacaService {

    private  static Logger log = LoggerFactory.getLogger(RacaService.class);

    private final TheCatsClient theCatsClient;

    private final RacaRepository racaRepository;

    private final FotoInfoService fotoInfoService;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public RacaService(TheCatsClient theCatsClient, RacaRepository racaRepository, FotoInfoService fotoInfoService, CategoriaService categoriaService) {
        this.theCatsClient = theCatsClient;
        this.racaRepository = racaRepository;
        this.fotoInfoService = fotoInfoService;
    }

    public List<Raca>  buscarRacasCatsAPI(String limite) throws DataFormatException {

        log.info("Inicia Busca de Breeds em The Cats API");
        var breeds = theCatsClient.getBreeds(limite);

        var  racas = breeds.stream().map( i -> mapper.map(i, Raca.class)).collect(Collectors.toList());

        return salvarRacas(racas);

    }

    public List<Raca> salvarRacas(List<Raca> racas) {
        log.info("Salvando lista de Racas");
        return racaRepository.saveAll(racas);
    }

    public List<RacaDto> buscarRacas(){
        log.info("Buscando todas as Racas dísponideis");
        return racaRepository.findAll().stream().map(this::transformarRacaEntityEmRacaDto).collect(Collectors.toList());
    }

    public List<RacaDto> buscarRacaPorOrigem(String origem){
        log.info("Buscando Racas por Origem");
        return transformarListaRacaEntityEmListaRacaDto(racaRepository.findByPaisOrigem(origem));
    }

    public List<RacaDto> buscarRacasPorTemperamento(String temperamento){
        log.info("Buscando Lista de Racas por temperamento");
        return racaRepository.findAllByTemperamentoContains(temperamento)
                .stream().map(this::transformarRacaEntityEmRacaDto).collect(Collectors.toList());
    }

    public RacaDto buscarRacaPorId(String racaId) {
        log.info("Buscando Racas por id");
        return transformarRacaEntityEmRacaDto(racaRepository.findById(Long.parseLong(racaId)).orElseThrow());
    }

    public RacaDto buscarRacaPorIdExterno(String idExterno) {
        log.info("Buscando Raca por idExterno");
        return transformarRacaEntityEmRacaDto(racaRepository.findByIdExternoBreed(idExterno).orElseThrow());
    }

    public List<RacaDto> buscarRacaPorNome(String nome) {
        log.info("Buscando Lista de Racas por nome");
        return transformarListaRacaEntityEmListaRacaDto(racaRepository.findByNome(nome));
    }

    public Boolean verificaCargaTabela() {
        log.info("Iniciando validacao de carga de dados de Raca");
        return racaRepository.findAll(PageRequest.of(0, 1)).isEmpty();
    }

    private RacaDto transformarRacaEntityEmRacaDto(Raca raca){
        var racaDto = mapper.map(raca, RacaDto.class);
        racaDto.setFotos(fotoInfoService.buscarFotoPorIdRaca(racaDto.getIdRaca().toString()));
        return racaDto;
    }

    private List<RacaDto> transformarListaRacaEntityEmListaRacaDto(Optional<Raca> racas){
        return racas.stream().map(this::transformarRacaEntityEmRacaDto).collect(Collectors.toList());
    }

}
