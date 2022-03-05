package br.com.raca.gatos.service;

import br.com.raca.gatos.client.TheCatsClient;
import br.com.raca.gatos.entity.FotoInfo;
import br.com.raca.gatos.entity.Raca;
import br.com.raca.gatos.model.FotoDto;
import br.com.raca.gatos.model.rest.BreedResponse;
import br.com.raca.gatos.model.rest.ImagesResponse;
import br.com.raca.gatos.repository.FotoInfoRepository;
import br.com.raca.gatos.repository.RacaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RacaServiceTeste {

    @Mock
    private TheCatsClient theCatsClient;

    @Mock
    private RacaRepository racaRepository;

    @Mock
    private FotoInfoRepository fotoInfoRepository;

    @Mock
    private FotoInfoService fotoInfoService;

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private RacaService racaService;

    @BeforeEach
    void beforeEach() throws Exception {
        reset(theCatsClient);
        reset(racaRepository);
        reset(fotoInfoRepository);
        reset(fotoInfoService);
        reset(categoriaService);
    }

    @Test
    void testeBuscarRacasCatsAPIeSalvar() throws DataFormatException {
        List<BreedResponse> breedResponses = new ArrayList<>();
        var breedResponse = new BreedResponse();
        breedResponse.setImagesResponse(new ImagesResponse());
        breedResponses.add(breedResponse);

        List<Raca> racas = new ArrayList<>();
        racas.add(new Raca());

        when(theCatsClient.getBreeds(anyString())).thenReturn(breedResponses);
        when(racaRepository.saveAll(any())).thenReturn(racas);
        when(fotoInfoRepository.saveAll(any())).thenReturn(new ArrayList<FotoInfo>());
        racaService.buscarRacasCatsAPI("teste");

        verify(theCatsClient, Mockito.times(1)).getBreeds(anyString());
    }

    @Test
    void testeSalvarRacas(){
        when(racaRepository.saveAll(any())).thenReturn(new ArrayList<Raca>());
        racaService.salvarRacas(new ArrayList<Raca>());

        verify(racaRepository, Mockito.times(1)).saveAll(any());
    }

    @Test
    void testeBuscarRacas(){
        when(racaRepository.findAll()).thenReturn(new ArrayList<Raca>());
        racaService.buscarRacas();

        verify(racaRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testeBuscarRacaPorOrigem(){
        Raca raca = new Raca();
        raca.setIdRaca(123l);
        when(racaRepository.findByPaisOrigem(any())).thenReturn(Optional.of(raca));
        racaService.buscarRacaPorOrigem("teste");

        verify(racaRepository, Mockito.times(1)).findByPaisOrigem(any());
    }

    @Test
    void testeBuscarRacasPorTemperamento(){
        when(racaRepository.findAllByTemperamentoContains(any())).thenReturn(new ArrayList<Raca>());
        racaService.buscarRacasPorTemperamento("teste");

        verify(racaRepository, Mockito.times(1)).findAllByTemperamentoContains(any());
    }

    @Test
    void testeBuscarRacaPorId() {
        Raca raca = new Raca();
        raca.setIdRaca(123l);
        when(racaRepository.findById(any())).thenReturn(Optional.of(raca));
        when(fotoInfoService.buscarFotoPorIdRaca(anyString())).thenReturn(List.of(new FotoDto()));
        racaService.buscarRacaPorId("123");

        verify(racaRepository, Mockito.times(1)).findById(any());
    }

    @Test
    void testeBuscarRacaPorIdExterno() {
        Raca raca = new Raca();
        raca.setIdRaca(123l);
        when(racaRepository.findByIdExternoBreed(anyString())).thenReturn(Optional.of(raca));
        racaService.buscarRacaPorIdExterno("123");

        verify(racaRepository, Mockito.times(1)).findByIdExternoBreed(anyString());
    }

    @Test
    void testeBuscarRacaPorNome() {
        Raca raca = new Raca();
        raca.setIdRaca(123l);
        when(racaRepository.findByNome(anyString())).thenReturn(Optional.of(raca));
        racaService.buscarRacaPorNome("teste");

        verify(racaRepository, Mockito.times(1)).findByNome(anyString());
    }

    @Test
    void testeVerificaCargaTabela() {
        when(racaRepository.existsBy()).thenReturn(true);
        racaService.verificaCargaTabela();

        verify(racaRepository, Mockito.times(1)).existsBy();
    }

}
