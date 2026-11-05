package pe.edu.utp.dao;
import jakarta.ejb.Stateless;
import pe.edu.utp.entity.DocenteCurso;
@Stateless
public class DocenteCursoDAOImpl extends GenericDAOImpl<DocenteCurso, Integer> implements DocenteCursoDAO {
    public DocenteCursoDAOImpl() { super(DocenteCurso.class); }
}