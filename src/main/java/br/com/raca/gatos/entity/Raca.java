package br.com.raca.gatos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "raca")
public class Raca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idRaca;

    @Column(name = "pais_origem")
    private String paisOrigem;

    @Column
    private String temperamento;

    @Column(columnDefinition = "text")
    private String descricao;

    @Column
    private String nome;

    @Column(name = "id_externo")
    private String idExternoBreed;

    public Raca(Long idRaca, String paisOrigem, String temperamento, String descricao, String nome, String idExternoBreed) {
        this.idRaca = idRaca;
        this.paisOrigem = paisOrigem;
        this.temperamento = temperamento;
        this.descricao = descricao;
        this.nome = nome;
        this.idExternoBreed = idExternoBreed;
    }

    public Raca() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Raca raca = (Raca) o;
        return Objects.equals(idRaca, raca.idRaca) && Objects.equals(paisOrigem, raca.paisOrigem) && Objects.equals(temperamento, raca.temperamento) && Objects.equals(descricao, raca.descricao) && Objects.equals(nome, raca.nome) && Objects.equals(idExternoBreed, raca.idExternoBreed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRaca, paisOrigem, temperamento, descricao, nome, idExternoBreed);
    }
}
