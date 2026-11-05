package pe.edu.utp.service;
import jakarta.ejb.Local;
import pe.edu.utp.entity.Pago;
import java.util.List;
@Local
public interface PagoService {
    void registrarPago(Pago pago);
    void actualizarPago(Pago pago);
    void eliminarPago(Integer id);
    List<Pago> listarTodos();
}