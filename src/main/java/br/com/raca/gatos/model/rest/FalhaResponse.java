package br.com.raca.gatos.model.rest;

public class FalhaResponse {

    private Integer status;
    private String mensagem;
    private Long timestamp;
    private String origem;

    public FalhaResponse(Integer status, String mensagem, Long timestamp, String origem) {
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = timestamp;
        this.origem = origem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
}
