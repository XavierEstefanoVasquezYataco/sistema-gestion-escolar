package pe.edu.utp.dao;
import jakarta.ejb.Stateless;
import pe.edu.utp.entity.Seccion;
@Stateless
public class SeccionDAOImpl extends GenericDAOImpl<Seccion, Integer> implements SeccionDAO {
    public SeccionDAOImpl() { super(Seccion.class); }
}