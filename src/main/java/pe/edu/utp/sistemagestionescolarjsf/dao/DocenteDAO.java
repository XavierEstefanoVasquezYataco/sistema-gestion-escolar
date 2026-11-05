package pe.edu.utp.dao;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Docente;

@Local
public interface DocenteDAO extends GenericDAO<Docente, Integer> {
}