package pe.edu.utp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.edu.utp.dao.DocenteDAO;
import pe.edu.utp.dao.UsuarioDAO;
import pe.edu.utp.dao.RolDAO;
import pe.edu.utp.entity.Docente;
import pe.edu.utp.entity.Usuario;
import pe.edu.utp.entity.Rol;
import java.util.List;

@Stateless
public class DocenteServiceImpl implements DocenteService {

    @Inject
    private DocenteDAO docenteDAO;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private RolDAO rolDAO;

    @Override
    public void registrarDocente(Docente docente) {
        // 1. Generar usuario con tu lógica: prefijo "d." + nombre + apellido
        String[] nombresArr = docente.getNombres().trim().split("\\s+");
        String[] apellidosArr = docente.getApellidos().trim().split("\\s+");
        
        String primerNombre = nombresArr[0].toLowerCase().replaceAll("[^a-z0-9]", "");
        String primerApellido = apellidosArr[0].toLowerCase().replaceAll("[^a-z0-9]", "");
        String usernameGenerado = "d." + primerNombre + "." + primerApellido;

        // 2. Validar que no se repita
        boolean existe = usuarioDAO.findAll().stream().anyMatch(u -> u.getUsername().equals(usernameGenerado));
        String usernameFinal = existe ? usernameGenerado + docente.getDni().substring(docente.getDni().length() - 3) : usernameGenerado;

        // 3. Crear la entidad Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(usernameFinal);
        
        // 4. Contraseña con tu lógica: "D" mayúscula + DNI
        nuevoUsuario.setPassword("D" + docente.getDni().trim());

        // Asignar el Rol de DOCENTE (ID = 2 en la base de datos)
        Rol rolDocente = rolDAO.findById(2).orElseThrow(() -> new RuntimeException("Error: Rol Docente no encontrado."));
        nuevoUsuario.setRol(rolDocente);

        // 5. Guardar primero el Usuario
        usuarioDAO.create(nuevoUsuario);

        // 6. Vincular y guardar el Docente
        docente.setUsuario(nuevoUsuario);
        docenteDAO.create(docente);
    }

    @Override
    public void actualizarDocente(Docente docente) {
        docenteDAO.update(docente);
    }

    @Override
    public void eliminarDocente(Integer id) {
        docenteDAO.deleteById(id);
    }

    @Override
    public Docente buscarPorId(Integer id) {
        return docenteDAO.findById(id).orElse(null);
    }

    @Override
    public List<Docente> listarTodos() {
        return docenteDAO.findAll();
    }
}