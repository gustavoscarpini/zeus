package br.com.techcode.seguranca;

import br.com.techcode.entidades.Usuario;
import br.com.techcode.negocios.UsuarioFacade;
import br.com.techcode.util.Seguranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class UsuarioSistemaAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsuarioFacade usuarioSistemaFacade;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            Usuario usuario = new Usuario();
            usuario.setLogin("admin");
            usuario.setSenha(new ShaPasswordEncoder().encodePassword(Seguranca.md5("123mudar"), usuario.getSalt()));
            System.out.println("Usuario: "+ usuario.getLogin() +" - "+ usuario.getSenha() +" - "+ usuario.getSalt());

            
            //admin - 5505b672823f47eeea37fde52c8950a2ab5bca12 - 16ef7231-7983-4170-9acf-3f3daddfc264
            


            Usuario usuarioSistema = findUsuarioSistema(authentication);
            if (usuarioSistema.isAutenticacaoCorreta(Seguranca.md5(authentication.getCredentials().toString()))) {
                //if (usuarioSistema.loginIgualSenha(Seguranca.md5(usuarioSistema.getLogin()))) {
                //    System.out.println("LOGIN DO SISTEMA...: Login igual a senha!");
                //}

                return new UsernamePasswordAuthenticationToken(usuarioSistema, null, usuarioSistema.getAuthorities());
            } else {
                throw new BadCredentialsException("Autenticação incorreta.");
            }
        }
        return null;
    }

    private Usuario findUsuarioSistema(Authentication authentication) {
        Usuario usuarioSistema = usuarioSistemaFacade.findByLogin(authentication.getName());
        if (usuarioSistema == null) {
            throw new UsernameNotFoundException(String.format("Usuário não encontrado: '%s'",
                    authentication.getName()));
        }
        return usuarioSistema;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}