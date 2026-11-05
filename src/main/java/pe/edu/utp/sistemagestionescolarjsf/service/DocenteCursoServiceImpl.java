package pe.edu.utp.service;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.DocenteCursoDAO;
import pe.edu.utp.entity.DocenteCurso;
import java.util.List;
@Stateless
public class DocenteCursoServiceImpl implements DocenteCursoService {
    @Inject private DocenteCursoDAO docenteCursoDAO;
    @Override public List<DocenteCurso> listarTodos() { return docenteCursoDAO.findAll(); }
}