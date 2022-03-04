package br.com.raca.gatos.service;

import br.com.raca.gatos.entity.Categoria;
import br.com.raca.gatos.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaServiceTeste {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeEach
    void beforeEach() throws Exception {
        reset(categoriaRepository);
    }

    @Test
    void testeSalvarCategoriaFotoInfo() {
        Map<String, String> categoria = new HashMap<>();
        categoria.put("id", "5");
        when(categoriaRepository.save(any())).thenReturn(new Categoria());
        categoriaService.salvarCategoriaFotoInfo(categoria);

        verify(categoriaRepository, Mockito.times(1)).save(any());
    }
}
