/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techcode.controladores;

import br.com.techcode.entidades.Grupo;
import br.com.techcode.entidades.GrupoRecurso;
import br.com.techcode.entidades.RecursoSistema;
import br.com.techcode.negocios.GrupoFacade;
import br.com.techcode.supers.SuperControlador;
import br.com.techcode.supers.SuperFacade;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Peixe
 */
@ManagedBean(name = "grupoControlador")
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "novo-grupo", pattern = "/admin/grupo/novo/", viewId = "/faces/admin/grupo/editar.xhtml"),
    @URLMapping(id = "editar-grupo", pattern = "/admin/grupo/editar/#{grupoControlador.id}/", viewId = "/faces/admin/grupo/editar.xhtml"),
    @URLMapping(id = "listar-grupo", pattern = "/admin/grupo/listar/", viewId = "/faces/admin/grupo/listar.xhtml"),
    @URLMapping(id = "ver-grupo", pattern = "/admin/grupo/ver/#{grupoControlador.id}/", viewId = "/faces/admin/grupo/visualizar.xhtml")
})
public class GrupoControlador extends SuperControlador<Grupo> implements Serializable {

    @Autowired
    private GrupoFacade grupoFacade;
    private RecursoSistema recursoSistema;

    public GrupoControlador() {
        super(Grupo.class);
    }

    @Override
    public SuperFacade getEsteFacade() {
        return grupoFacade;
    }

    @URLAction(mappingId = "listar-grupo", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    @Override
    public void listar() {
        super.listar();

    }

    @URLAction(mappingId = "novo-grupo", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    @Override
    public void novo() {
        super.novo();
    }

    @URLAction(mappingId = "editar-grupo", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    @Override
    public void editar() {
        super.editar();
    }

    @URLAction(mappingId = "ver-grupo", phaseId = URLAction.PhaseId.RENDER_RESPONSE, onPostback = false)
    @Override
    public void visualizar() {
        super.visualizar();
    }

    public List<SelectItem> getSelectItensRecursos() {
        List<SelectItem> toReturn = new ArrayList<SelectItem>();
        toReturn.add(new SelectItem(null, " "));
        for (RecursoSistema catDaVez : RecursoSistema.values()) {
            toReturn.add(new SelectItem(catDaVez, catDaVez.getLabel()));
        }
        return toReturn;
    }

    public RecursoSistema getRecursoSistema() {
        return recursoSistema;
    }

    public void setRecursoSistema(RecursoSistema recursoSistema) {
        this.recursoSistema = recursoSistema;
    }

    public Grupo getGrupoSelecionado() {
        return selecionado;
    }

    public void adicionaRecurso() {
        if (recursoSistema != null) {
            GrupoRecurso gr = new GrupoRecurso();
            gr.setGrupo(getGrupoSelecionado());
            gr.setRecursoSistema(recursoSistema);
            getGrupoSelecionado().getGrupoRecursos().add(gr);
        } else {
            //aqui mensagem de validação!!
            System.out.println("recurso nulo!!!");
        }
    }

    public void removeRecurso(GrupoRecurso evento) {
        if (evento != null) {
            System.out.println("Evento : "+ evento);
            getGrupoSelecionado().getGrupoRecursos().remove(evento);

        }
    }

    @Override
    public List<Grupo> getLista() {
        return grupoFacade.listar();
    }
}
