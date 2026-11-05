package pe.edu.utp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.CursoDAO;
import pe.edu.utp.entity.Curso;
import java.util.List;

@Stateless
public class CursoServiceImpl implements CursoService {

    @Inject
    private CursoDAO cursoDAO;

    @Override
    public void registrarCurso(Curso curso) {
        cursoDAO.create(curso);
    }

    @Override
    public void actualizarCurso(Curso curso) {
        cursoDAO.update(curso);
    }

    @Override
    public void eliminarCurso(Integer id) {
        cursoDAO.deleteById(id);
    }

    @Override
    public Curso buscarPorId(Integer id) {
        return cursoDAO.findById(id).orElse(null);
    }

    @Override
    public List<Curso> listarTodos() {
        return cursoDAO.findAll();
    }
}