package pe.edu.utp.dao;

import jakarta.ejb.Stateless;
import pe.edu.utp.entity.Estudiante;

@Stateless
public class EstudianteDAOImpl extends GenericDAOImpl<Estudiante, Integer> implements EstudianteDAO {
    public EstudianteDAOImpl() {
        super(Estudiante.class);
    }
}