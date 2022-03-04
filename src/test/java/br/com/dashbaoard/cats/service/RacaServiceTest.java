package br.com.dashbaoard.cats.service;

import br.com.raca.gatos.client.TheCatsClient;
import br.com.raca.gatos.entity.FotoInfo;
import br.com.raca.gatos.entity.Raca;
import br.com.raca.gatos.model.FotoDto;
import br.com.raca.gatos.model.rest.BreedResponse;
import br.com.raca.gatos.model.rest.ImagesResponse;
import br.com.raca.gatos.repository.FotoInfoRepository;
import br.com.raca.gatos.repository.RacaRepository;
import br.com.raca.gatos.service.CategoriaService;
import br.com.raca.gatos.service.FotoInfoService;
import br.com.raca.gatos.service.RacaService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RacaServiceTest {

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
    void testBuscarRacasCatsAPIeSalvar() throws DataFormatException {
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
    void testBuscarRacasCatsAPIeSalvarException() throws DataFormatException {
        List<BreedResponse> breedResponses = new ArrayList<>();
        var breedResponse = new BreedResponse();
        breedResponse.setImagesResponse(new ImagesResponse());
        breedResponses.add(breedResponse);

        when(theCatsClient.getBreeds(anyString())).thenReturn(breedResponses);
        when(racaRepository.saveAll(any())).thenReturn(new ArrayList<>());

        Exception error = null;
        try {
            racaService.buscarRacasCatsAPI("teste");
        } catch (Exception e){
            error = e;
        }

        assertNotNull(error);
        assertEquals(DataFormatException.class, error.getClass());
    }

    @Test
    void testSalvarRacas(){
        when(racaRepository.saveAll(any())).thenReturn(new ArrayList<Raca>());
        racaService.salvarRacas(new ArrayList<Raca>());

        verify(racaRepository, Mockito.times(1)).saveAll(any());
    }

    @Test
    void testBuscarRacas(){
        when(racaRepository.findAll()).thenReturn(new ArrayList<Raca>());
        racaService.buscarRacas();

        verify(racaRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testeBuscarRacaPorOrigem(){
        when(racaRepository.findByPaisOrigem(any())).thenReturn(Optional.of(new Raca()));
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
    void testBuscarRacaPorId() {
        when(racaRepository.findById(any())).thenReturn(Optional.of(new Raca()));
        when(fotoInfoService.buscarFotoPorIdRaca(anyString())).thenReturn(List.of(new FotoDto()));
        racaService.buscarRacaPorId("123");

        verify(racaRepository, Mockito.times(1)).getById(anyLong());
    }

    @Test
    void testBuscarRacaPorIdExterno() {
        when(racaRepository.findByIdExternoBreed(anyString())).thenReturn(Optional.of(new Raca()));
        racaService.buscarRacaPorIdExterno("123");

        verify(racaRepository, Mockito.times(1)).findByIdExternoBreed(anyString());
    }

    @Test
    void testBuscarRacaPorNome() {
        when(racaRepository.findByNome(anyString())).thenReturn(Optional.of(new Raca()));
        racaService.buscarRacaPorNome("teste");

        verify(racaRepository, Mockito.times(1)).findByNome(anyString());
    }

}
