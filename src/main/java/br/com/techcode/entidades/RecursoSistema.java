/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techcode.entidades;

/**
 *
 * @author Peixe
 */
public enum RecursoSistema {

    //Cidade
    CADASTROCIDADE_EDITAR("Cadastro de Cidade Editar", "/cadastro/cidade/editar.xhtml"),
    CADASTROCIDADE_LISTAR("Cadastro de Cidade Lista", "/cadastro/cidade/listar.xhtml"),
    CADASTROCIDADE_VISUALIZAR("Cadastro de Cidade Visualizar", "/cadastro/cidade/visualizar.xhtml"),
    //Estado
    CADASTROESTADO_EDITAR("Cadastro de Estado Editar", "/cadastro/estado/editar.xhtml"),
    CADASTROESTADO_LISTAR("Cadastro de Estado Listar", "/cadastro/estado/listar/"),
    CADASTROESTADO_VISUALIZAR("Cadastro de Estado Visualizar", "/cadastro/estado/visualizar.xhtml"),
    //Grupo Usuario
    CADASTROGRUPO_EDITAR("Cadastro de Grupo Editar", "/admin/grupo/editar.xhtml"),
    CADASTROGRUPO_LISTAR("Cadastro de Grupo Listar", "/admin/grupo/listar.xhtml"),
    CADASTROGRUPO_VISUALIZAR("Cadastro de Grupo Visualizar", "/admin/grupo/visualizar.xhtml"),
    //Perametro
    CADASTROPARAMETRO_EDITAR("Cadastro de Parâmetro Editar", "/configuracao/parametros/editar.xhtml"),
    CADASTROPARAMETRO_LISTAR("Cadastro de Parâmetro Listar", "/configuracao/parametros/listar.xhtml"),
    CADASTROPARAMETRO_VISUALIZAR("Cadastro de Parâmetro Visualizar", "/configuracao/parametros/visualizar.xhtml"),
    //Pessoa Física
    CADASTROPESSOAFISICA_EDITAR("Cadastro de Pessoa Física Editar", "/cadastro/pessoa/pessoafisica/editar.xhtml"),
    CADASTROPESSOAFISICA_LISTAR("Cadastro de Pessoa Física Listar", "/cadastro/pessoa/pessoafisica/listar.xhtml"),
    CADASTROPESSOAFISICA_VISUALIZAR("Cadastro de Pessoa Física Visualizar", "/cadastro/pessoa/pessoafisica/visualizar.xhtml"),
    //Usuário
    CADASTROUSUARIO_EDITAR("Cadastro de Usuário Editar", "/admin/usuario/editar.xhtml"),
    CADASTROUSUARIO_LISTAR("Cadastro de Usuário Listar", "/admin/usuario/listar.xhtml"),
    CADASTROUSUARIO_VISUALIZAR("Cadastro de Usuário Visualizar", "/admin/usuario/visualizar.xhtml");

    private RecursoSistema() {
    }
    private String label;
    private String caminho;

    private RecursoSistema(String label, String caminho) {
        this.label = label;
        this.caminho = caminho;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public static RecursoSistema findRecurso(String r) {
        for (RecursoSistema rs : values()) {
            if (rs.caminho.equals(r)) {
                return rs;
            }
        }
        return null;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
