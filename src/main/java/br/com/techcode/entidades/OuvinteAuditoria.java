package br.com.techcode.entidades;

import org.hibernate.envers.RevisionListener;

/**
 *
 * @author AndreGustavo
 */
public class OuvinteAuditoria implements RevisionListener {

    @Override
    public void newRevision(Object revisaoAuditoria) {
        RevisaoAuditoria ra = (RevisaoAuditoria) revisaoAuditoria;
        //obter usuario
        //obter ip
    }
}
