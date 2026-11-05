package pe.edu.utp.dao;
import jakarta.ejb.Local;
import pe.edu.utp.entity.Periodo;
@Local
public interface PeriodoDAO extends GenericDAO<Periodo, Integer> {}