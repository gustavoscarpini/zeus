package br.com.techcode.seguranca;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsuarioSistemaInitListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final PasswordEncoder PASSWORD_ENCODER = new ShaPasswordEncoder();
//    @Autowired
//    private UsuarioService usuarioSistemaService;
//    @Autowired
//    private GrupoUsuarioService grupoUsuarioService;
//   
//    @Autowired
//    private GrupoRecursoService grupoRecursoService;
//    @Autowired
//    private RecursoSistemaService recursoSistemaService;

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        UsuarioSistema usuarioSistema = usuarioSistemaService.findOne(QUsuarioSistema.usuarioSistema.login.eq("yanaga"));
//        if (usuarioSistema == null) {
//            usuarioSistema = new UsuarioSistema();
//            usuarioSistema.setLogin("yanaga");
//            usuarioSistema.setSenha(PASSWORD_ENCODER.encodePassword(Seguranca.md5("senha10"), usuarioSistema.getSalt()));
//            usuarioSistema = usuarioSistemaService.save(usuarioSistema);
//
//            List<GrupoUsuario> grupos = grupoUsuarioService.findAll(QGrupoUsuario.grupoUsuario.usuarios.contains(usuarioSistema));
//            if (grupos.isEmpty()) {
//                GrupoUsuario grupoUsuario = new GrupoUsuario();
//                grupoUsuario.setNome("Administrador");
//                grupoUsuario.setTipo(TipoGrupoUsuario.AUTORIZACAO);
//                grupoUsuario.addUsuario(usuarioSistema);
//
//                RecursoSistema recursoSistema = new RecursoSistema();
//                recursoSistema.setCadastro(false);
//                recursoSistema.setCaminho("/faces/index.xhtml");
//                recursoSistema.setNome("index");
//                recursoSistema = recursoSistemaService.save(recursoSistema);
//
//                GrupoRecurso grupoRecurso = new GrupoRecurso();
//                grupoRecurso.setNome("Administrador");
//                grupoRecurso.addRecursoSistema(recursoSistema);
//
//                ItemGrupoUsuario itemGrupoUsuario = new ItemGrupoUsuario();
//                itemGrupoUsuario.setGrupoRecurso(grupoRecurso);
//                itemGrupoUsuario.setGrupoUsuario(grupoUsuario);
//                //itemGrupoUsuario.setDireitos(Sets.newHashSet(Direito.LEITURA, Direito.ESCRITA, Direito.EXCLUSAO));
//                itemGrupoUsuario.setLeitura(true);
//                itemGrupoUsuario.setEscrita(true);
//                itemGrupoUsuario.setExclusao(true);
//                grupoRecursoService.save(grupoRecurso);
//
//                grupoUsuarioService.save(grupoUsuario);
//            }
//        }
//        Exercicio exercicio = exercicioService.findOne(QExercicio.exercicio.ano.eq(2012));
//        if (exercicio == null) {
//            exercicio = new Exercicio();
//            exercicio.setAno(2012);
//            exercicioService.save(exercicio);
//        }
    }
}
