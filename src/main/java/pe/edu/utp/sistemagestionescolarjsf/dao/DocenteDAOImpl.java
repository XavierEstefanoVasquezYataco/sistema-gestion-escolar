package pe.edu.utp.dao;

import jakarta.ejb.Stateless;
import pe.edu.utp.entity.Docente;

@Stateless
public class DocenteDAOImpl extends GenericDAOImpl<Docente, Integer> implements DocenteDAO {
    public DocenteDAOImpl() {
        super(Docente.class);
    }
}