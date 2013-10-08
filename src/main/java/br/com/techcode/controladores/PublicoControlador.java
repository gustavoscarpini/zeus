/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techcode.controladores;

import br.com.techcode.entidades.Usuario;
import br.com.techcode.negocios.UsuarioFacade;
import br.com.techcode.supers.SuperControlador;
import br.com.techcode.supers.SuperFacade;
import br.com.techcode.util.FacesUtil;
import br.com.techcode.util.excecoes.ExcecaoEnvioEmail;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Peixe
 */
@ManagedBean(name = "publicoControlador")
@SessionScoped
@URLMappings(mappings = {
    @URLMapping(id = "recuperar-senha", pattern = "/recuperarsenha/", viewId = "/faces/recuperasenha.xhtml"), //    @URLMapping(id = "editar-estado", pattern = "/cadastro/estado/editar/#{estadoControlador.id}/", viewId = "/faces/cadastro/estado/editar.xhtml"),
})
public class PublicoControlador extends SuperControlador<Usuario> implements Serializable {

    @Autowired
    UsuarioFacade usuarioFacade;
    String nomeUsuario;
    String emailUsuario;

    @Override
    public SuperFacade getEsteFacade() {
        return usuarioFacade;
    }

    public PublicoControlador() {
        System.out.println("construi");
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public void recuperarSenha() {
        try {
            usuarioFacade.iniciaRecuperaSenha(nomeUsuario, emailUsuario);
            FacesUtil.addInfo("Operação realizada com sucesso. ", "Foi enviado um e-mail para seu endereço principal, para continuar siga os procedimentos nele contido.");
        } catch (ExcecaoEnvioEmail ex) {
            FacesUtil.addError("Impossível Continuar", "Não foi identificado um email válido para o envio, contate o suporte.");
//            Logger.getLogger(LogadoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
