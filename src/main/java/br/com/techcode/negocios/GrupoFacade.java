/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techcode.negocios;

import br.com.techcode.entidades.Grupo;
import br.com.techcode.supers.SuperFacade;
import br.com.techcode.util.Persistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Peixe
 */
@Repository
public class GrupoFacade extends SuperFacade<Grupo> {
    
    @PersistenceContext(unitName = Persistencia.UNIDADE_PERSISTENCIA)
    private EntityManager em;
    
    public GrupoFacade() {
        super(Grupo.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public Grupo recuperar(Object id) {
        Grupo grupo = em.find(Grupo.class, id);
        return grupo;
    }
    
    @Override
    public List<Grupo> listar() {
        String hql = "from Grupo e order by e.descricao asc";
        Query q = getEntityManager().createQuery(hql);
        
        return q.getResultList();
    }
    

}
