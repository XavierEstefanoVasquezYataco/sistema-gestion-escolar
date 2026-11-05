package pe.edu.utp.dao;

import jakarta.ejb.Stateless;
import pe.edu.utp.entity.Curso;

@Stateless
public class CursoDAOImpl extends GenericDAOImpl<Curso, Integer> implements CursoDAO {
    public CursoDAOImpl() {
        super(Curso.class);
    }
}