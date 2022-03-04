package br.com.raca.gatos.controller;

import br.com.raca.gatos.constant.sql.SqlScripts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts ={
        SqlScripts.CLEAR,
        SqlScripts.RACA
})
public class RacaControllerTeste {

    @Autowired
    MockMvc mockMvc;

    private static String URL_BASE_RACAS = "/racas";

    private static String HEADER_ORIGIN_VALUE = "*";

    private static String QUERY_PARAM_TEMPERAMENTO = "temperamento";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarTodasAsRacas () throws Exception {

        var mockResponse = mockMvc.perform(MockMvcRequestBuilders.get(URL_BASE_RACAS)
                .header(HttpHeaders.ORIGIN, HEADER_ORIGIN_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        var mockResult = mockResponse.getResponse().getContentAsString();

        assertNotNull(mockResult);

    }

    @Test
    void buscarTodasAsRacaPorTemperamento() throws Exception {

        var temperamento = "Docil";

        var mockResponse = mockMvc.perform(
                MockMvcRequestBuilders.get(URL_BASE_RACAS.concat("/temperamento"))
                .header(HttpHeaders.ORIGIN, HEADER_ORIGIN_VALUE)
                        .queryParam(QUERY_PARAM_TEMPERAMENTO , temperamento)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        var mockResult = mockResponse.getResponse().getContentAsString();

        assertNotNull(mockResult);
    }
}
