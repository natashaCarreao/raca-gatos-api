package br.com.raca.gatos.entity;

import br.com.raca.gatos.model.CategoriaEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    public Categoria() {
    }

    public Categoria(Long id, CategoriaEnum categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }
}
