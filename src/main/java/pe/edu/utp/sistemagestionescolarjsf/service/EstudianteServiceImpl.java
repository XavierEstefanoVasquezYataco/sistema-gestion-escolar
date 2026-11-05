package pe.edu.utp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.EstudianteDAO;
import pe.edu.utp.dao.UsuarioDAO;
import pe.edu.utp.dao.RolDAO;
import pe.edu.utp.entity.Estudiante;
import pe.edu.utp.entity.Usuario;
import pe.edu.utp.entity.Rol;
import java.util.List;

@Stateless
public class EstudianteServiceImpl implements EstudianteService {

    @Inject
    private EstudianteDAO estudianteDAO;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private RolDAO rolDAO;

    @Override
    public void registrarEstudiante(Estudiante estudiante) {
        // 1. Generar de forma automática el nombre de usuario (ejemplo: juan.perez)
        String[] nombresArr = estudiante.getNombres().trim().split("\\s+");
        String[] apellidosArr = estudiante.getApellidos().trim().split("\\s+");
        
        String primerNombre = nombresArr[0].toLowerCase().replaceAll("[^a-z0-9]", "");
        String primerApellido = apellidosArr[0].toLowerCase().replaceAll("[^a-z0-9]", "");
        String usernameGenerado = primerNombre + "." + primerApellido;

        // 2. Validar que el username no se repita, si existe le añadimos los últimos dígitos del DNI
        boolean existe = usuarioDAO.findAll().stream().anyMatch(u -> u.getUsername().equals(usernameGenerado));
        String usernameFinal = existe ? usernameGenerado + estudiante.getDni().substring(estudiante.getDni().length() - 3) : usernameGenerado;

        // 3. Crear la entidad Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(usernameFinal);
        
        // Asignamos la contraseña para que sea EXACTAMENTE el DNI en texto normal
        nuevoUsuario.setPassword(estudiante.getDni().trim());

        // Asignar el Rol de ESTUDIANTE (Buscamos el Rol con ID = 3 de la BD)
        Rol rolEstudiante = rolDAO.findById(3).orElseThrow(() -> new RuntimeException("Error: Rol Estudiante no encontrado en la BD."));
        nuevoUsuario.setRol(rolEstudiante);

        // 4. Guardar primero el Usuario en la Base de Datos
        usuarioDAO.create(nuevoUsuario);

        // 5. Vincular el Usuario recién creado al Estudiante y persistir
        estudiante.setUsuario(nuevoUsuario);
        estudianteDAO.create(estudiante);
    }

    @Override
    public void actualizarEstudiante(Estudiante estudiante) {
        estudianteDAO.update(estudiante);
    }

    @Override
    public void eliminarEstudiante(Integer id) {
        estudianteDAO.deleteById(id);
    }

    @Override
    public Estudiante buscarPorId(Integer id) {
        return estudianteDAO.findById(id).orElse(null);
    }

    @Override
    public List<Estudiante> listarTodos() {
        return estudianteDAO.findAll();
    }
}