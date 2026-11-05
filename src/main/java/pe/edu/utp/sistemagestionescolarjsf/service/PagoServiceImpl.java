package pe.edu.utp.service;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.PagoDAO;
import pe.edu.utp.entity.Pago;
import java.util.List;
@Stateless
public class PagoServiceImpl implements PagoService {
    @Inject private PagoDAO pagoDAO;
    @Override public void registrarPago(Pago pago) { pagoDAO.create(pago); }
    @Override public void actualizarPago(Pago pago) { pagoDAO.update(pago); }
    @Override public void eliminarPago(Integer id) { pagoDAO.deleteById(id); }
    @Override public List<Pago> listarTodos() { return pagoDAO.findAll(); }
}