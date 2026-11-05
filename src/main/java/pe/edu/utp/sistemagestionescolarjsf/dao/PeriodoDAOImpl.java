package pe.edu.utp.dao;
import jakarta.ejb.Stateless;
import pe.edu.utp.entity.Periodo;
@Stateless
public class PeriodoDAOImpl extends GenericDAOImpl<Periodo, Integer> implements PeriodoDAO {
    public PeriodoDAOImpl() { super(Periodo.class); }
}