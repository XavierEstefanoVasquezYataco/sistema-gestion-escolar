package pe.edu.utp.service;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.TipoPagoDAO;
import pe.edu.utp.entity.TipoPago;
import java.util.List;
@Stateless
public class TipoPagoServiceImpl implements TipoPagoService {
    @Inject private TipoPagoDAO tipoPagoDAO;
    @Override public List<TipoPago> listarTodos() { return tipoPagoDAO.findAll(); }
}