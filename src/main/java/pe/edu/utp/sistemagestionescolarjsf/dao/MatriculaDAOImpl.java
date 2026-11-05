package pe.edu.utp.dao;
import jakarta.ejb.Stateless;
import pe.edu.utp.entity.Matricula;
@Stateless
public class MatriculaDAOImpl extends GenericDAOImpl<Matricula, Integer> implements MatriculaDAO {
    public MatriculaDAOImpl() { super(Matricula.class); }
}