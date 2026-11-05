package pe.edu.utp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.entity.*;
import pe.edu.utp.service.*;
import pe.edu.utp.util.SessionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class PortalEstudianteBean implements Serializable {

    @Inject private EstudianteService estudianteService;
    @Inject private UsuarioService usuarioService;
    @Inject private NotaService notaService;
    @Inject private PagoService pagoService;

    private Estudiante miEstudiante;
    private Usuario miUsuario;
    private List<Nota> misNotas = new ArrayList<>();
    private List<Pago> misPagos = new ArrayList<>();
    private String nuevaContrasena;

    @PostConstruct
    public void init() {
        HttpSession session = SessionUtils.getSession();
        if (session != null) {
            miUsuario = (Usuario) session.getAttribute("usuarioLogueado");
            
            if (miUsuario != null && "ESTUDIANTE".equals(miUsuario.getRol().getNombre())) {
                // 1. Buscar el registro de estudiante asociado al ID del usuario logueado
                miEstudiante = estudianteService.listarTodos().stream()
                        .filter(e -> e.getUsuario() != null && e.getUsuario().getId().equals(miUsuario.getId()))
                        .findFirst()
                        .orElse(null);

                if (miEstudiante != null) {
                    // 2. Filtrar solo las notas que pertenezcan a las matrículas de ESTE estudiante
                    misNotas = notaService.listarTodas().stream()
                            .filter(n -> n.getMatricula().getEstudiante().getId().equals(miEstudiante.getId()))
                            .collect(Collectors.toList());

                    // 3. Filtrar solo los pagos que pertenezcan a las matrículas de ESTE estudiante
                    misPagos = pagoService.listarTodos().stream()
                            .filter(p -> p.getMatricula().getEstudiante().getId().equals(miEstudiante.getId()))
                            .collect(Collectors.toList());
                }
            }
        }
    }

    public void actualizarPerfil() {
        try {
            if (miEstudiante != null) {
                estudianteService.actualizarEstudiante(miEstudiante);
            }
            
            if (nuevaContrasena != null && !nuevaContrasena.trim().isEmpty()) {
                miUsuario.setPassword(nuevaContrasena);
                usuarioService.actualizarUsuario(miUsuario);
                nuevaContrasena = null; // Limpiar el campo tras guardar
            }
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Éxito", "Tus datos personales fueron actualizados correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el perfil."));
        }
    }

    // Getters y Setters
    public Estudiante getMiEstudiante() { return miEstudiante; }
    public void setMiEstudiante(Estudiante miEstudiante) { this.miEstudiante = miEstudiante; }
    public Usuario getMiUsuario() { return miUsuario; }
    public void setMiUsuario(Usuario miUsuario) { this.miUsuario = miUsuario; }
    public List<Nota> getMisNotas() { return misNotas; }
    public void setMisNotas(List<Nota> misNotas) { this.misNotas = misNotas; }
    public List<Pago> getMisPagos() { return misPagos; }
    public void setMisPagos(List<Pago> misPagos) { this.misPagos = misPagos; }
    public String getNuevaContrasena() { return nuevaContrasena; }
    public void setNuevaContrasena(String nuevaContrasena) { this.nuevaContrasena = nuevaContrasena; }
}