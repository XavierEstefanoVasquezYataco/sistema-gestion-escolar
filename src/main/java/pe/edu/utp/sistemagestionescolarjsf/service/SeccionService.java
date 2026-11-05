package pe.edu.utp.service;
import jakarta.ejb.Local;
import pe.edu.utp.entity.Seccion;
import java.util.List;
@Local
public interface SeccionService { List<Seccion> listarTodos(); }