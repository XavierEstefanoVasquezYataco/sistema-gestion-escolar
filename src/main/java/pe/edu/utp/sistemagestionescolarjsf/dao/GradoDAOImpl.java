package pe.edu.utp.dao;

import jakarta.ejb.Stateless;
import pe.edu.utp.entity.Grado;

@Stateless
public class GradoDAOImpl extends GenericDAOImpl<Grado, Integer> implements GradoDAO {
    public GradoDAOImpl() {
        super(Grado.class);
    }
}