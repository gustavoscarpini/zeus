/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techcode.entidades;

import br.com.techcode.util.Cadastravel;
import br.com.techcode.util.Seguranca;
import br.com.techcode.util.Validacao;
import br.com.techcode.util.anotacoes.CRUD;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.envers.Audited;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

/**
 *
 * @author Gustavo
 */
@Entity
@Audited
@CRUD(label = "Usuário")
public class Usuario implements Serializable, Comparable<Usuario>, UserDetails, Cadastravel {

    private static final long serialVersionUID = 1L;
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private static final PasswordEncoder PASSWORD_ENCODER = new ShaPasswordEncoder();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @CRUD(label = "Código", obrigatorio = false)
    private Long id;
    @CRUD(label = "Usuário", obrigatorio = false)
    private String login;
    @CRUD(label = "Senha", visualizavel = false, obrigatorio = false)
    private String senha;
    private String salt = UUID.randomUUID() + "";
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GruposUsuario> gruposUsuarios;
    @Transient
    private String repeteSenha;

    @PreUpdate
    void initSalt() {
        if (salt == null) {
            salt = UUID.randomUUID() + "";
        }
    }

    public Usuario(Long id, String login, String senha, List<GruposUsuario> gruposUsuarios) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.gruposUsuarios = gruposUsuarios;
    }

    public Usuario() {
        gruposUsuarios = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<GruposUsuario> getGruposUsuarios() {
        return gruposUsuarios;
    }

    public void setGruposUsuarios(List<GruposUsuario> gruposUsuarios) {
        this.gruposUsuarios = gruposUsuarios;
    }

    public String getRepeteSenha() {
        return repeteSenha;
    }

    public void setRepeteSenha(String repeteSenha) {
        this.repeteSenha = repeteSenha;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.scarpini.entidades.Usuario[ id=" + id + " ]";
    }

    @Override
    public final String getCaminhoPadrao() {
        return "/admin/usuario/";
    }

    @Override
    public Validacao executaValidacao() {
        Validacao validacao = Validacao.newValidacao();
        if (this.getLogin() != null && this.getLogin().length() < 5) {
            validacao.addMensagem("Informação inválida", "O campo login deve conter cinco ou mais caracteres");
        }
        if (this.getSenha() != null && this.getSenha().length() < 6) {
            validacao.addMensagem("Informação inválida", "O campo seneha deve conter 6 ou mais caracteres");
        }
        if (this.getRepeteSenha() != null && this.getSenha() != null && !isAutenticacaoCorreta(Seguranca.md5(getRepeteSenha()))) {
            validacao.addMensagem("Informação inválida", "As senhas não conferem, por favor preencha os campos novamente com senha iguais");
        }
        return validacao;
    }

    public boolean isAutenticacaoCorreta(String credentials) {
        String encodedPassword = PASSWORD_ENCODER.encodePassword(credentials, salt);
        if (encodedPassword.equals(senha)) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Usuario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return DEFAULT_AUTHORITIES;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
