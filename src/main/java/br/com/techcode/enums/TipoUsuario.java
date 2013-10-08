/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techcode.enums;

/**
 *
 * @author Peixe
 */
public enum TipoUsuario {

    INTERNO("Interno"),
    EXTERNO("Externo");
    private String descricao;

    private TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
