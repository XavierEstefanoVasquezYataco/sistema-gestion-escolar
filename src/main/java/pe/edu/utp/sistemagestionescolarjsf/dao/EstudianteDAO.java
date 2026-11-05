package pe.edu.utp.dao;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Estudiante;

@Local
public interface EstudianteDAO extends GenericDAO<Estudiante, Integer> {
}