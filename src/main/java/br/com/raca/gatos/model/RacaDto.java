package br.com.raca.gatos.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RacaDto {

    private Long idRaca;

    private String paisOrigem;

    private String temperamento;

    private String descricao;

    private String nome;

    private String idExternoBreed;

    private List<FotoDto> fotos;

    public RacaDto() {
    }

    public RacaDto(Long idRaca, String paisOrigem, String temperamento, String descricao, String nome, String idExternoBreed, List<FotoDto> fotos) {
        this.idRaca = idRaca;
        this.paisOrigem = paisOrigem;
        this.temperamento = temperamento;
        this.descricao = descricao;
        this.nome = nome;
        this.idExternoBreed = idExternoBreed;
        this.fotos = fotos;
    }

    public Long getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(Long idRaca) {
        this.idRaca = idRaca;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(String temperamento) {
        this.temperamento = temperamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdExternoBreed() {
        return idExternoBreed;
    }

    public void setIdExternoBreed(String idExternoBreed) {
        this.idExternoBreed = idExternoBreed;
    }

    public List<FotoDto> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoDto> fotos) {
        this.fotos = fotos;
    }

    private Long idFoto;
}
