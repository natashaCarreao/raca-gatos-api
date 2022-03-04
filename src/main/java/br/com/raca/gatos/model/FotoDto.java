package br.com.raca.gatos.model;

import br.com.raca.gatos.entity.Raca;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FotoDto {

    private String url;

    private Raca raca;

    private CategoriaEnum categoria;

    public FotoDto(String url) {
        this.url = url;
    }

    public FotoDto() {
    }

    public FotoDto(String url, Raca raca, CategoriaEnum categoria) {
        this.url = url;
        this.raca = raca;
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
