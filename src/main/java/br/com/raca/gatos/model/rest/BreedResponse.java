package br.com.raca.gatos.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BreedResponse {

    @JsonProperty("id")
    private String idExternoBreed;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("temperament")
    private String temperamento;

    @JsonProperty("origin")
    private String paisOrigem;

    @JsonProperty("description")
    private String descricao;

    @JsonProperty("image")
    private ImagesResponse imagesResponse;

    public BreedResponse() {
    }

    public BreedResponse(String idExternoBreed, String nome, String temperamento, String paisOrigem, String descricao, ImagesResponse imagesResponse) {
        this.idExternoBreed = idExternoBreed;
        this.nome = nome;
        this.temperamento = temperamento;
        this.paisOrigem = paisOrigem;
        this.descricao = descricao;
        this.imagesResponse = imagesResponse;
    }

    public String getIdExternoBreed() {
        return idExternoBreed;
    }

    public void setIdExternoBreed(String idExternoBreed) {
        this.idExternoBreed = idExternoBreed;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(String temperamento) {
        this.temperamento = temperamento;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ImagesResponse getImagesResponse() {
        return imagesResponse;
    }

    public void setImagesResponse(ImagesResponse imagesResponse) {
        this.imagesResponse = imagesResponse;
    }
}
