package pe.edu.utp.service;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.PeriodoDAO;
import pe.edu.utp.entity.Periodo;
import java.util.List;
@Stateless
public class PeriodoServiceImpl implements PeriodoService {
    @Inject private PeriodoDAO periodoDAO;
    @Override public List<Periodo> listarTodos() { return periodoDAO.findAll(); }
}