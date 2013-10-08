package br.com.techcode.controladores;

import br.com.techcode.entidades.PermissaoUsuario;
import br.com.techcode.negocios.PermissaoUsuarioFacade;
import br.com.techcode.supers.SuperControlador;
import br.com.techcode.supers.SuperFacade;
import br.com.techcode.util.ConverterGenerico;
import br.com.techcode.util.Util;
import br.com.techcode.util.anotacoes.Operacoes;
import br.com.techcode.util.excecoes.ValidacaoException;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLAction.PhaseId;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean(name = "permissaoUsuarioControlador")
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "novo-permissaoUsuario", pattern = "/admin/permissao/novo/", viewId = "/faces/admin/permissaousuario/editar.xhtml"),
    @URLMapping(id = "editar-permissaoUsuario", pattern = "/admin/permissao/editar/#{permissaoUsuarioControlador.id}/", viewId = "/faces/admin/permissaousuario/editar.xhtml"),
    @URLMapping(id = "listar-permissaoUsuario", pattern = "/admin/permissao/listar/", viewId = "/faces/admin/permissaousuario/listar.xhtml"),
    @URLMapping(id = "ver-permissaoUsuario", pattern = "/admin/permissao/ver/#{permissaoUsuarioControlador.id}/", viewId = "/faces/admin/permissaousuario/visualizar.xhtml")
})
public class PermissaoUsuarioControlador extends SuperControlador<PermissaoUsuario> implements Serializable {

    @Autowired
    private PermissaoUsuarioFacade permissaoUsuarioFacade;

    public PermissaoUsuarioControlador() {
        super(PermissaoUsuario.class);
    }

    @Override
    public SuperFacade getEsteFacade() {
        return permissaoUsuarioFacade;
    }

    @URLAction(mappingId = "listar-permissaoUsuario", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    @Override
    public void listar() {
        super.listar();
    }

    @URLAction(mappingId = "novo-permissaoUsuario", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    @Override
    public void novo() {
        super.novo();
    }

    @URLAction(mappingId = "editar-permissaoUsuario", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    @Override
    public void editar() {
        super.editar();
    }

    @URLAction(mappingId = "ver-permissaoUsuario", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    @Override
    public void visualizar() {
        super.visualizar();
    }

    public List<PermissaoUsuario> completarEstaEntidade(String parte) {
        return permissaoUsuarioFacade.listarFiltrando(parte.trim(), "titulo", "id");
    }

    public List<SelectItem> selectItensPermissaoUsuario() {
        List<SelectItem> toReturn = new ArrayList<SelectItem>();
        toReturn.add(new SelectItem(null, " "));
        for (PermissaoUsuario catDaVez : permissaoUsuarioFacade.listar()) {
            toReturn.add(new SelectItem(catDaVez, catDaVez.getNome()));
        }
        return toReturn;
    }

    public List<SelectItem> selectItensTipoPermissao() {
        List<SelectItem> toReturn = new ArrayList<SelectItem>();
        for (PermissaoUsuario.Permissao catDaVez : PermissaoUsuario.Permissao.values()) {
            toReturn.add(new SelectItem(catDaVez, catDaVez.toString()));
        }
        return toReturn;
    }

    public Converter getConverterGenerico() {
        return new ConverterGenerico(PermissaoUsuario.class, permissaoUsuarioFacade);
    }
}
