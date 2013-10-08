package br.com.techcode.negocios;

import br.com.techcode.entidades.GruposUsuario;
import br.com.techcode.entidades.Usuario;
import br.com.techcode.supers.SuperFacade;
import br.com.techcode.util.Persistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoUsuarioFacade extends SuperFacade<GruposUsuario> {

    @PersistenceContext(unitName = Persistencia.UNIDADE_PERSISTENCIA)
    private EntityManager em;

    public GrupoUsuarioFacade() {
        super(GruposUsuario.class);
    }

    public List<GruposUsuario> findByUsuario(Usuario usuarioSistema) {
        return em.createQuery("select grupo from GruposUsuario grupo where grupo.usuario = :usuario ").setParameter("usuario", usuarioSistema).getResultList();

    }

    @Override
    protected EntityManager getEntityManager() {
        return em; //To change body of generated methods, choose Tools | Templates.
    }
}