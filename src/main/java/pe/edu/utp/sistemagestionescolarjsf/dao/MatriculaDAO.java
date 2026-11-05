package pe.edu.utp.dao;
import jakarta.ejb.Local;
import pe.edu.utp.entity.Matricula;
@Local
public interface MatriculaDAO extends GenericDAO<Matricula, Integer> {}