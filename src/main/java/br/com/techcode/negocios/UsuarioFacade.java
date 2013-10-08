package br.com.techcode.negocios;

import br.com.techcode.entidades.RecuperaSenha;
import br.com.techcode.entidades.Usuario;
import br.com.techcode.supers.SuperFacade;
import br.com.techcode.util.EmailUtil;
import br.com.techcode.util.Persistencia;
import br.com.techcode.util.excecoes.ExcecaoEnvioEmail;
import br.com.techcode.util.excecoes.ExcecaoNegocioGenerica;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuarioFacade extends SuperFacade<Usuario> {

    @PersistenceContext(unitName = Persistencia.UNIDADE_PERSISTENCIA)
    private EntityManager em;

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Transactional
    @Override
    public List<Usuario> listar() {
        String hql = "from Usuario e order by e.login asc";
        Query q = getEntityManager().createQuery(hql);

        return q.getResultList();
    }

    @Transactional
    @Override
    public Usuario recuperar(Object id) {
        Usuario u = getEntityManager().find(Usuario.class, id);
        System.out.println(u);
        u.getGruposUsuarios().size();
        return u;
    }

    public void iniciaRecuperaSenha(String nomeUsuario, String email) throws ExcecaoEnvioEmail {

        RecuperaSenha alteraSenha = new RecuperaSenha(recuperarUsuarioPorLogin(nomeUsuario, email));
        alteraSenha = em.merge(alteraSenha);
        Query q = em.createQuery("select c from Usuario c where lower(c.login) = :login and lower(c.pessoa.email) = :email");

        q.setParameter("login", nomeUsuario.trim());
        q.setParameter("email", email.toLowerCase().trim());
//        Pessoa c = null;
//
//        if (q.getResultList().size() > 0) {
//            c = ((Usuario) q.getResultList().get(0)).getPessoa();
//            c.geteMail();
//        }
//        if (c != null && c.geteMail() != null) {
//            enviaEmailEsqueciMinhaSenha(c.geteMail(), monteCorpoEsqueciMinhaSenha(alteraSenha, c));
//        }
    }

    public void enviaEmailEsqueciMinhaSenha(String emailDestino, String corpo) throws ExcecaoEnvioEmail {
        try {
            EmailUtil.enviaEmailHtml(emailDestino, corpo, "Recuperação de Senha");
        } catch (Exception e) {
            throw new ExcecaoEnvioEmail("Ocorreu um erro de comunicação e o email não pode ser enviado, tente outra vez ou contate o suporte");

        }
    }

    public Usuario recuperarUsuarioPorLogin(String login, String email) {
        String hql = "from Usuario u where lower(u.login) = :login and lower(u.pessoa.email) = :email";
        Query q = em.createQuery(hql);
        q.setParameter("login", login.trim());
        q.setParameter("email", email.toLowerCase().trim());
        try {
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario recuperausuarioPorTicketAlteracaoSenha(String codigo) {
        String hql = "select a.usuario from RecuperaSenha a where a.codigo = :codigo and current_date() <= a.validade";
        Query q = em.createQuery(hql).setParameter("codigo", codigo);
        try {
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            new ExcecaoNegocioGenerica("Erro inesperado " + e);
            return null;
        }
    }

    public Usuario findByLogin(String login) {
        String hql = "from Usuario u where lower(u.login) = :login";
        Query q = em.createQuery(hql);
        q.setParameter("login", login.trim());
        try {
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
