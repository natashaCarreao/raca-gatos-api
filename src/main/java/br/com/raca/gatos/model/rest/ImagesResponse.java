package br.com.raca.gatos.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ImagesResponse {

    @JsonProperty("breeds")
    private List<BreedResponse> breeds;

    @JsonProperty("id")
    private String idExternoImage;

    private String url;

    @JsonProperty("categories")
    private List<Map<String, String>> categorias;

    public ImagesResponse() {
    }

    public ImagesResponse(List<BreedResponse> breeds, String idExternoImage, String url, List<Map<String, String>> categorias) {
        this.breeds = breeds;
        this.idExternoImage = idExternoImage;
        this.url = url;
        this.categorias = categorias;
    }

    public List<BreedResponse> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<BreedResponse> breeds) {
        this.breeds = breeds;
    }

    public String getIdExternoImage() {
        return idExternoImage;
    }

    public void setIdExternoImage(String idExternoImage) {
        this.idExternoImage = idExternoImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Map<String, String>> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Map<String, String>> categorias) {
        this.categorias = categorias;
    }
}
