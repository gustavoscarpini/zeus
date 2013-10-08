/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techcode.entidades;

import br.com.techcode.util.Cadastravel;
import br.com.techcode.util.Validacao;
import br.com.techcode.util.anotacoes.CRUD;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.envers.Audited;


/**
 *
 * @author Gustavo
 */
@Entity
@Audited
@CRUD(label="Permissao")
public class PermissaoUsuario implements Serializable, Cadastravel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @CRUD(label = "Código", obrigatorio = false)
    private Long id;
    @CRUD(label = "Nome")
    private String nome;
    @Enumerated(EnumType.STRING)
    @CRUD(label = "Permição")
    private Permissao permissao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissaoUsuario)) {
            return false;
        }
        PermissaoUsuario other = (PermissaoUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.scarpini.entidades.GrupoUsuario[ id=" + id + " ]";
    }

    @Override
    public String getCaminhoPadrao() {
        return "/admin/permissao/";
    }

    @Override
    public Validacao executaValidacao() {
        return Validacao.newValidacao();
    }

    public enum Permissao {

        ROLE_ADMIN, ROLE_USER;
    }
}
