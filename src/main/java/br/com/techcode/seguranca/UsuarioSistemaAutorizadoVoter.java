package br.com.techcode.seguranca;

import br.com.techcode.entidades.GruposUsuario;
import br.com.techcode.entidades.RecursoSistema;
import br.com.techcode.entidades.Usuario;
import br.com.techcode.negocios.GrupoRecursoFacade;
import br.com.techcode.negocios.GrupoUsuarioFacade;
import br.com.techcode.negocios.UsuarioFacade;
import com.google.common.collect.Sets;
import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.config.PrettyConfigurator;
import com.ocpsoft.pretty.faces.url.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class UsuarioSistemaAutorizadoVoter implements AccessDecisionVoter<Object> {

    private static final String URI_MONITORING = "/monitoring";
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    GrupoRecursoFacade grupoRecursoService;
    @Autowired
    GrupoUsuarioFacade grupoUsuarioService;
    @Autowired
    UsuarioFacade usuarioFacade;
    PrettyContext ctx;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Transactional(readOnly = true)
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        Usuario usuarioSistema = (Usuario) authentication.getPrincipal();
        if (object instanceof FilterInvocation) {
            String uri = extractUri((FilterInvocation) object);
            System.out.println("URI: : " + uri);
//            if ((!uri.endsWith(".xhtml")) && (!URI_MONITORING.equalsIgnoreCase(uri))) {
//                return ACCESS_GRANTED;
//            }
            //Retirar if... que libera todos os links kkkk
            if (usuarioSistema.getLogin().equals("admin")) {
                return ACCESS_GRANTED;
            }
            if (Sets.newHashSet("/template.xhtml", "/resources/images/general/macbook.png", "/home", "/resources/images/general/ajaxLoader_16x16.gif", "/index.xhtml", "/tributario/emDesenvolvimento.xhtml", "/troca.xhtml", "/erro500.xhtml", "/alterasenha.xhtml").contains(uri)) {
                return ACCESS_GRANTED;
            }

            RecursoSistema recursoSistema = RecursoSistema.findRecurso(uri);
            if (recursoSistema == null) {
                return ACCESS_DENIED;
            }

            List<GruposUsuario> gruposUsuario = grupoUsuarioService.findByUsuario(usuarioSistema);
            //System.out.print(" Grupos do usuario:" + gruposUsuario);
            if (gruposUsuario.isEmpty()) {
                return ACCESS_DENIED;
            } else {
                for (GruposUsuario grupo : gruposUsuario) {
                    if (grupoRecursoService.listaGrupoRecursosUsuario(grupo, recursoSistema)) {
                        return ACCESS_GRANTED;
                    }
                }
                return ACCESS_DENIED;
            }
        }
        return ACCESS_DENIED;
    }

    private String extractUri(FilterInvocation filterInvocation) {
        return extractUri(filterInvocation.getHttpRequest()).replaceAll("/faces/", "/");
    }

    private String extractUri(HttpServletRequest request) {
        String uri = request.getServletPath();
        if (request.getPathInfo() != null) {
            return String.format("%s%s", request.getServletPath(), request.getPathInfo());
        } else {
            URL url = new URL(uri);
            if (ctx == null) {
                ctx = PrettyContext.getCurrentInstance(request);
                if (ctx.getConfig().getMappings().isEmpty()) {
                    initPrettyFacesFromServlet(request);
                }
            }
            if (ctx.getConfig().isURLMapped(url)) {
                return getViewID(url);
            }

            return request.getServletPath();
        }
    }

    public String getViewID(URL url) {
        return ctx.getConfig().getMappingForUrl(url).getViewId();
    }

    public void initPrettyFacesFromServlet(HttpServletRequest request) {
        PrettyConfigurator pc = new PrettyConfigurator(request.getServletContext());
        pc.configure();
        ctx.getConfig().setMappings(pc.getConfig().getMappings());
    }
}
