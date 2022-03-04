package br.com.dashbaoard.cats.service;

import br.com.raca.gatos.client.TheCatsClient;
import br.com.raca.gatos.entity.FotoInfo;
import br.com.raca.gatos.repository.FotoInfoRepository;
import br.com.raca.gatos.service.FotoInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FotoInfoServiceTest {

    @Mock
    private TheCatsClient theCatsClient;

    @Mock
    private FotoInfoRepository fotoInfoRepository;

    @InjectMocks
    private FotoInfoService fotoInfoService;

    @BeforeEach
    void beforeEach() throws Exception {
        reset(fotoInfoRepository);
        reset(theCatsClient);
    }

    /*@Test
    void testBuscarFotoUrlPorRacaId() {
        when(theCatsClient.getImagesSearchByBreedIds(any(), anyString(), anyString())).thenReturn(new ArrayList<ImagesResponse>());
        fotoInfoService.buscarFotoUrlPorRacaId();

        verify(theCatsClient, Mockito.times(1)).getImagesSearchByBreedIds(any(), anyString(), anyString());
    }*/

    @Test
    void salvarFoto() {
        when(fotoInfoRepository.save(any())).thenReturn(null);
        fotoInfoService.salvarFoto(new FotoInfo());

        verify(fotoInfoRepository, Mockito.times(1)).save(any());
    }
}
