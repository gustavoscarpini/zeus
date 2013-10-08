package br.com.techcode.negocios;

import br.com.techcode.entidades.GrupoRecurso;
import br.com.techcode.entidades.GruposUsuario;
import br.com.techcode.entidades.RecursoSistema;
import br.com.techcode.supers.SuperFacade;
import br.com.techcode.util.Persistencia;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoRecursoFacade extends SuperFacade<GrupoRecurso> {

    @PersistenceContext(unitName = Persistencia.UNIDADE_PERSISTENCIA)
    private EntityManager entityManager;

    public GrupoRecursoFacade() {
        super(GrupoRecurso.class);
    }

    public boolean listaGrupoRecursosUsuario(GruposUsuario gru, RecursoSistema recursoSistema) {
        Query q = entityManager.createQuery(" from Grupo gru join gru.grupoRecursos rec join gru.gruposUsuarios use  "
                + " where use = :gu and rec.recursoSistema = :recurso");
        q.setParameter("gu", gru);
        q.setParameter("recurso", recursoSistema);
        return !q.getResultList().isEmpty();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}