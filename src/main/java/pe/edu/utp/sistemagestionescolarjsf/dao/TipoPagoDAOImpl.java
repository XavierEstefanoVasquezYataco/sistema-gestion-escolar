package pe.edu.utp.dao;
import jakarta.ejb.Stateless;
import pe.edu.utp.entity.TipoPago;
@Stateless
public class TipoPagoDAOImpl extends GenericDAOImpl<TipoPago, Integer> implements TipoPagoDAO {
    public TipoPagoDAOImpl() { super(TipoPago.class); }
}