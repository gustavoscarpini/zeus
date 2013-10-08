/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techcode.entidades;

import br.com.techcode.util.Cadastravel;
import br.com.techcode.util.Validacao;
import br.com.techcode.util.anotacoes.CRUD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.envers.Audited;

/**
 *
 * @author Peixe
 */
@Entity
@Audited
public class Grupo implements Serializable, Cadastravel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CRUD(obrigatorio = true, label = "Descrição")
    private String descricao;
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<GrupoRecurso> grupoRecursos;
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GruposUsuario> gruposUsuarios;

    public Grupo() {
        grupoRecursos = new ArrayList<>();
        gruposUsuarios = new ArrayList<>();
    }

    public Grupo(Long id, String descricao, List<GrupoRecurso> grupoRecursos, List<GruposUsuario> gruposUsuarios) {
        this.id = id;
        this.descricao = descricao;
        this.grupoRecursos = grupoRecursos;
        this.gruposUsuarios = gruposUsuarios;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<GrupoRecurso> getGrupoRecursos() {
        return grupoRecursos;
    }

    public void setGrupoRecursos(List<GrupoRecurso> grupoRecursos) {
        this.grupoRecursos = grupoRecursos;
    }

    public List<GruposUsuario> getGruposUsuarios() {
        return gruposUsuarios;
    }

    public void setGruposUsuarios(List<GruposUsuario> gruposUsuarios) {
        this.gruposUsuarios = gruposUsuarios;
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
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }

    @Override
    public String getCaminhoPadrao() {
        return "/admin/grupo/";
    }

    @Override
    public Validacao executaValidacao() {
        return Validacao.newValidacao();
    }
}