package br.com.raca.gatos.service;

import br.com.raca.gatos.client.TheCatsClient;
import br.com.raca.gatos.entity.FotoInfo;
import br.com.raca.gatos.entity.Raca;
import br.com.raca.gatos.model.CategoriaEnum;
import br.com.raca.gatos.model.FotoDto;
import br.com.raca.gatos.model.rest.ImagesResponse;
import br.com.raca.gatos.repository.FotoInfoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FotoInfoService {

    private  static Logger log = LoggerFactory.getLogger(RacaService.class);

    @Autowired
    public FotoInfoService(TheCatsClient theCatsClient, FotoInfoRepository fotoInfoRepository, CategoriaService categoriaService) {
        this.theCatsClient = theCatsClient;
        this.fotoInfoRepository = fotoInfoRepository;
        this.categoriaService = categoriaService;
    }

    private final TheCatsClient theCatsClient;

    private final FotoInfoRepository fotoInfoRepository;

    private final CategoriaService categoriaService;

    private final ModelMapper mapper = new ModelMapper();

    @Value("${the-cats-api.api-key}")
    public String apiKey;

    public FotoInfo salvarFoto(FotoInfo fotoInfo) {
        log.info("Salvando FotoInfo");
        return fotoInfoRepository.save(fotoInfo);
    }

    public List<FotoInfo> salvarTodasFotos(List<FotoInfo> fotos) {
        log.info("Salvando lista de FotoInfo");
        return fotoInfoRepository.saveAll(fotos);
    }

    public List<FotoInfo> montaListaDeFotoInfoComCategoria(CategoriaEnum categoriaEnum){

        ArrayList<FotoInfo> fotos = new ArrayList<>();

        log.info("Iniciando Busca de Images por categoria em The Cats API");
        var imagens = theCatsClient.getImagesSearchByBreedIdOrCategoryId(null, categoriaEnum.getCategoria(),"3", apiKey, "desc");

        imagens.forEach(imagesResponse -> fotos.add(montaFotoInfoComCategoria(imagesResponse)));

        return fotos;
    }

    public List<FotoInfo> montaListaDeFotoInfoParaRaca(List<Raca> racas) {

        List<FotoInfo> fotos = new ArrayList<>();

        racas.forEach(raca -> {
            log.info("Inicia Busca de Images por categoria em The Cats API");
            var images = theCatsClient.getImagesSearchByBreedIdOrCategoryId(raca.getIdExternoBreed(), null,"3", apiKey, "desc");
            images.forEach(image ->{
                FotoInfo foto = mapper.map(image, FotoInfo.class);
                foto.setRaca(raca);
                fotos.add(foto);
            });
        });

        return fotos;
    }

    public List<FotoDto> buscarFotoPorIdRaca(String idRaca){
        log.info("Bunscando lista de foto por id Raca");
        return fotoInfoRepository.findAllByRacaIdRaca(Long.valueOf(idRaca))
                .stream().map(this::transformarFotoEntityEmFotoDto).collect(Collectors.toList());
    }

    private FotoInfo montaFotoInfoComCategoria(ImagesResponse imagesResponse) {

        FotoInfo foto = mapper.map(imagesResponse, FotoInfo.class);

        imagesResponse.getCategorias().forEach(i -> {
            var categoriaSalva = categoriaService.salvarCategoriaFotoInfo(i);
            foto.setCategoria(categoriaSalva);
        });

        return foto;
    }

    private FotoDto transformarFotoEntityEmFotoDto(FotoInfo fotoInfo){
        return mapper.map(fotoInfo, FotoDto.class);
    }
}
