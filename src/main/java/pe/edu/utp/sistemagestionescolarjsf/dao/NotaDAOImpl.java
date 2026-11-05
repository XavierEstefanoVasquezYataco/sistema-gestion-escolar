package pe.edu.utp.dao;

import jakarta.ejb.Stateless;
import pe.edu.utp.entity.Nota;

@Stateless
public class NotaDAOImpl extends GenericDAOImpl<Nota, Integer> implements NotaDAO {
    public NotaDAOImpl() {
        super(Nota.class);
    }
}