package br.com.techcode.seguranca;

import br.com.techcode.enums.Direito;



public class UriAutorizacaoUtils {

    private UriAutorizacaoUtils() {
    }

    public static Direito obterDireitoNecessarioParaAcessar(String uri) {
        if (uri.matches(".*/(listar|visualizar)\\.(xhtml|jsf)")) {
            return Direito.LEITURA;
        } else if (uri.matches(".*/editar\\.(xhtml|jsf)")) {
            return Direito.ESCRITA;
        }
        return Direito.LEITURA;
    }
}