package pe.edu.utp.dao;
import jakarta.ejb.Local;
import pe.edu.utp.entity.Pago;
@Local
public interface PagoDAO extends GenericDAO<Pago, Integer> {}