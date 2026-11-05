package pe.edu.utp.dao;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Grado;

@Local
public interface GradoDAO extends GenericDAO<Grado, Integer> {
}