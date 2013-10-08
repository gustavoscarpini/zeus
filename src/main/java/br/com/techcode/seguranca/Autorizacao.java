package br.com.techcode.seguranca;

import br.com.techcode.entidades.GruposUsuario;
import br.com.techcode.entidades.RecursoSistema;
import br.com.techcode.entidades.Usuario;
import br.com.techcode.enums.Direito;
import br.com.techcode.negocios.GrupoRecursoFacade;
import br.com.techcode.negocios.GrupoUsuarioFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class Autorizacao {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    GrupoRecursoFacade grupoRecursoFacade;
    @Autowired
    GrupoUsuarioFacade grupoUsuarioService;


    public boolean isPodeEditar(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isPodeEditar(request, authentication);
    }

    public boolean isPodeExcluir(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isPodeExcluir(request, authentication);
    }

    public boolean isPodeEditar(HttpServletRequest request, Authentication authentication) {
        return isPossuiDireito(request, authentication, Direito.ESCRITA);
    }

    public boolean isPodeExcluir(HttpServletRequest request, Authentication authentication) {
        return isPossuiDireito(request, authentication, Direito.EXCLUSAO);
    }

    private boolean isPossuiDireito(HttpServletRequest request, Authentication authentication, Direito direito) {
        Usuario usuarioSistema = (Usuario) authentication.getPrincipal();
        String uri = extractUri(request);
        uri = extractFaces(uri);
        System.out.println("opaaa passou aqui");
        if (!uri.endsWith(".xhtml")) {
            return true;
        }

//        if (Sets.newHashSet("admin", "yanaga", "webpublico").contains(usuarioSistema.getLogin())) {
//            return true;
//        }

        RecursoSistema recursoSistema = RecursoSistema.findRecurso(uri);
        if (recursoSistema == null) {
            return false;
        }

        List<GruposUsuario> gruposUsuario = grupoUsuarioService.findByUsuario(usuarioSistema);
        if (gruposUsuario.isEmpty()) {
            return false;
        }
        for (GruposUsuario gu : gruposUsuario) {
            if (grupoRecursoFacade.listaGrupoRecursosUsuario(gu, recursoSistema)) {
                return true;
            }
        }

//        List<GruposUsuario> listaGrupoRecursosUsuario = grupoRecursoService.listaGrupoRecursosUsuario(usuarioSistema);
        return false;
    }

    private String extractFaces(String uri) {
        return uri.replaceAll("/faces/", "/");
    }

    private String extractUri(HttpServletRequest request) {
        if (request.getPathInfo() != null) {
            return String.format("%s%s", request.getServletPath(), request.getPathInfo());
        } else {
            return request.getServletPath();
        }
    }
}