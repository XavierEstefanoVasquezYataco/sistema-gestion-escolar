package pe.edu.utp.dao;
import jakarta.ejb.Stateless;
import pe.edu.utp.entity.Pago;
@Stateless
public class PagoDAOImpl extends GenericDAOImpl<Pago, Integer> implements PagoDAO {
    public PagoDAOImpl() { super(Pago.class); }
}