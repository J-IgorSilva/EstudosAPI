package com.teste.primeiroexemplo.model.Error;

public class ErrorMensage {
    private String titulo;
    private String mensagem;
    private int statusError;

    public ErrorMensage(String titulo, String mensagem, int statusError) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.statusError = statusError;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getStatusError() {
        return statusError;
    }

    public void setStatusError(int statusError) {
        this.statusError = statusError;
    }

}
