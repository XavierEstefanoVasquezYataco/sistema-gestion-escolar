package pe.edu.utp.service;
import jakarta.ejb.Local;
import pe.edu.utp.entity.Periodo;
import java.util.List;
@Local
public interface PeriodoService { List<Periodo> listarTodos(); }