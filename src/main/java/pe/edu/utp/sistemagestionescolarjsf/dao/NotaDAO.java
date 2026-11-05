package pe.edu.utp.dao;

import jakarta.ejb.Local;
import pe.edu.utp.entity.Nota;

@Local
public interface NotaDAO extends GenericDAO<Nota, Integer> {
}