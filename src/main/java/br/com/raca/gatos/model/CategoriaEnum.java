package br.com.raca.gatos.model;

import java.util.HashMap;
import java.util.Map;

public enum CategoriaEnum {

    CHAPEIS("1"),
    ESPACO("2"),
    OCULOS("4"),
    CAIXAS("5"),
    LACOS("7"),
    PIAS("14"),
    ROUPAS("15");

    private  String categoria;

    private static final Map<String, CategoriaEnum> categoriaEnumPorId = new HashMap<>();

    static {
        for (CategoriaEnum c : CategoriaEnum.values()) {
            categoriaEnumPorId.put(c.getCategoria(), c);
        }
    }

    CategoriaEnum(String i) {
        this.categoria = i;
    }

    public String getCategoria() {
        return categoria;
    }

    public static CategoriaEnum trasCategoriaEnumPorId(String id){
        return categoriaEnumPorId.get(id);
    }
}

