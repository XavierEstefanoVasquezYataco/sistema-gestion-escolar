package pe.edu.utp.service;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.MatriculaDAO;
import pe.edu.utp.entity.Matricula;
import java.util.List;
@Stateless
public class MatriculaServiceImpl implements MatriculaService {
    @Inject private MatriculaDAO matriculaDAO;
    @Override public void registrarMatricula(Matricula matricula) { matriculaDAO.create(matricula); }
    @Override public void actualizarMatricula(Matricula matricula) { matriculaDAO.update(matricula); }
    @Override public void eliminarMatricula(Integer id) { matriculaDAO.deleteById(id); }
    @Override public List<Matricula> listarTodos() { return matriculaDAO.findAll(); }
}