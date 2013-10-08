package br.com.techcode.negocios;

import br.com.techcode.entidades.PermissaoUsuario;
import br.com.techcode.supers.SuperFacade;
import br.com.techcode.util.Persistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PermissaoUsuarioFacade extends SuperFacade<PermissaoUsuario> {

    @PersistenceContext(unitName = Persistencia.UNIDADE_PERSISTENCIA)
    private EntityManager em;

    public PermissaoUsuarioFacade() {
        super(PermissaoUsuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Transactional
    @Override
    public List<PermissaoUsuario> listar() {
        String hql = "from PermissaoUsuario e order by e.nome asc";
        Query q = getEntityManager().createQuery(hql);

        return q.getResultList();
    }
}
