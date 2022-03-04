package br.com.raca.gatos.service;

import br.com.raca.gatos.client.TheCatsClient;
import br.com.raca.gatos.entity.Categoria;
import br.com.raca.gatos.entity.FotoInfo;
import br.com.raca.gatos.entity.Raca;
import br.com.raca.gatos.model.CategoriaEnum;
import br.com.raca.gatos.model.rest.ImagesResponse;
import br.com.raca.gatos.repository.FotoInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.nullable;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FotoInfoServiceTeste {

    @Mock
    private TheCatsClient theCatsClient;

    @Mock
    private FotoInfoRepository fotoInfoRepository;

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private FotoInfoService fotoInfoService;

    @BeforeEach
    void beforeEach() throws Exception {
        reset(fotoInfoRepository);
        reset(theCatsClient);
        reset(categoriaService);
    }

    @Test
    void testeSalvarFoto() {
        when(fotoInfoRepository.save(any())).thenReturn(null);
        fotoInfoService.salvarFoto(new FotoInfo());

        verify(fotoInfoRepository, Mockito.times(1)).save(any());
    }

    @Test
    void testeSalvarTodasFotos() {
        when(fotoInfoRepository.saveAll(any())).thenReturn(new ArrayList<FotoInfo>());
        fotoInfoService.salvarTodasFotos(new ArrayList<FotoInfo>());

        verify(fotoInfoRepository, Mockito.times(1)).saveAll(any());
    }

    @Test
    void testeMontaListaDeFotoInfoComCategoria(){
        Map<String, String> categoria = new HashMap<>();
        categoria.put("teste","teste");
        List<Map<String, String>> categorias = new ArrayList<>();
        categorias.add(categoria);
        ImagesResponse imagesResponse = new ImagesResponse();
        imagesResponse.setCategorias(categorias);
        List<ImagesResponse> imagesResponses = new ArrayList<>();
        imagesResponses.add(imagesResponse);
        CategoriaEnum categoriaEnum = CategoriaEnum.CAIXAS;
        when(theCatsClient.getImagesSearchByBreedIdOrCategoryId(
                nullable(String.class), nullable(String.class), nullable(String.class), nullable(String.class), nullable(String.class)))
                .thenReturn(imagesResponses);
        when(categoriaService.salvarCategoriaFotoInfo(any())).thenReturn(new Categoria());
        fotoInfoService.montaListaDeFotoInfoComCategoria(categoriaEnum);

        verify(theCatsClient, Mockito.times(1)).getImagesSearchByBreedIdOrCategoryId(
                nullable(String.class), anyString(), anyString(), nullable(String.class), anyString());
    }

    @Test
    void testeMontaListaDeFotoInfoParaRaca() {
        List<ImagesResponse> imagesResponse = new ArrayList<ImagesResponse>();
        imagesResponse.add(new ImagesResponse());
        when(theCatsClient.getImagesSearchByBreedIdOrCategoryId(
                nullable(String.class), nullable(String.class), nullable(String.class), nullable(String.class), nullable(String.class)))
                .thenReturn(imagesResponse);
        List<Raca> racas = new ArrayList<>();
        racas.add(new Raca());
        fotoInfoService.montaListaDeFotoInfoParaRaca(racas);

        verify(theCatsClient, Mockito.times(1)).getImagesSearchByBreedIdOrCategoryId(
                nullable(String.class), nullable(String.class), nullable(String.class), nullable(String.class), nullable(String.class));
    }

    @Test
    void testeBuscarFotoPorIdRaca(){
        List<FotoInfo> fotoInfos = new ArrayList<>();
        fotoInfos.add(new FotoInfo());
        when(fotoInfoRepository.findAllByRacaIdRaca(anyLong())).thenReturn(fotoInfos);
        fotoInfoService.buscarFotoPorIdRaca("123456789");

        verify(fotoInfoRepository, Mockito.times(1)).findAllByRacaIdRaca(anyLong());
    }
}
