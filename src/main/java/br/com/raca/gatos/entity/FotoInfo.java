package br.com.raca.gatos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "foto_info")
public class FotoInfo implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idFoto;

    @Column
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raca_id", referencedColumnName = "id")
    private Raca raca;

    @Column(name = "id_externo")
    private String idExternoImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public FotoInfo() {
    }

    public FotoInfo(Long idFoto, String url, Raca raca, String idExternoImage, Categoria categoria) {
        this.idFoto = idFoto;
        this.url = url;
        this.raca = raca;
        this.idExternoImage = idExternoImage;
        this.categoria = categoria;
    }

    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public String getIdExternoImage() {
        return idExternoImage;
    }

    public void setIdExternoImage(String idExternoImage) {
        this.idExternoImage = idExternoImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FotoInfo fotoInfo = (FotoInfo) o;
        return Objects.equals(idFoto, fotoInfo.idFoto) && Objects.equals(url, fotoInfo.url) && Objects.equals(raca, fotoInfo.raca) && Objects.equals(idExternoImage, fotoInfo.idExternoImage) && Objects.equals(categoria, fotoInfo.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFoto, url, raca, idExternoImage, categoria);
    }
}
