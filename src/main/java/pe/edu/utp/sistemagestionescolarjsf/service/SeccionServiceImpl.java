package pe.edu.utp.service;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.SeccionDAO;
import pe.edu.utp.entity.Seccion;
import java.util.List;
@Stateless
public class SeccionServiceImpl implements SeccionService {
    @Inject private SeccionDAO seccionDAO;
    @Override public List<Seccion> listarTodos() { return seccionDAO.findAll(); }
}