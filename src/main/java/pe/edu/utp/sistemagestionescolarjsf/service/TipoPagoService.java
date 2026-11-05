package pe.edu.utp.service;
import jakarta.ejb.Local;
import pe.edu.utp.entity.TipoPago;
import java.util.List;
@Local
public interface TipoPagoService { List<TipoPago> listarTodos(); }