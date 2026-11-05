package pe.edu.utp.dao;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Curso;

@Local
public interface CursoDAO extends GenericDAO<Curso, Integer> {
}