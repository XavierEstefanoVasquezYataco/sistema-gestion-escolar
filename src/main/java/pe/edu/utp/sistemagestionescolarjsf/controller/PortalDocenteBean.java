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
public class PortalDocenteBean implements Serializable {

    @Inject private DocenteService docenteService;
    @Inject private DocenteCursoService docenteCursoService;
    @Inject private MatriculaService matriculaService;
    @Inject private UsuarioService usuarioService;

    private Docente docActual;
    private Usuario miUsuario;
    private String nuevaContrasena;
    private List<DocenteCurso> misCursosAsignados = new ArrayList<>();
    private List<Matricula> alumnosDeMisCursos = new ArrayList<>();

    @PostConstruct
    public void init() {
        HttpSession session = SessionUtils.getSession();
        if (session != null) {
            miUsuario = (Usuario) session.getAttribute("usuarioLogueado");
            if (miUsuario != null && "DOCENTE".equals(miUsuario.getRol().getNombre())) {
                
                docActual = docenteService.listarTodos().stream()
                        .filter(d -> d.getUsuario() != null && d.getUsuario().getId().equals(miUsuario.getId()))
                        .findFirst()
                        .orElse(null);

                if (docActual != null) {
                    misCursosAsignados = docenteCursoService.listarTodos().stream()
                            .filter(dc -> dc.getDocente().getId().equals(docActual.getId()))
                            .collect(Collectors.toList());

                    List<Matricula> todasMatriculas = matriculaService.listarTodos();
                    alumnosDeMisCursos = todasMatriculas.stream()
                            .filter(m -> misCursosAsignados.stream().anyMatch(dc -> 
                                    dc.getCurso().getGrado().getId().equals(m.getGrado().getId()) && 
                                    dc.getSeccion().getId().equals(m.getSeccion().getId())
                            ))
                            .collect(Collectors.toList());
                }
            }
        }
    }

    public void actualizarPerfil() {
        try {
            if (docActual != null) {
                docenteService.actualizarDocente(docActual);
            }
            
            if (nuevaContrasena != null && !nuevaContrasena.trim().isEmpty()) {
                miUsuario.setPassword(nuevaContrasena);
                usuarioService.actualizarUsuario(miUsuario);
                nuevaContrasena = null; 
            }
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Éxito", "Tus datos personales y/o contraseña fueron actualizados."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el perfil."));
        }
    }

    public Docente getDocActual() { return docActual; }
    public void setDocActual(Docente docActual) { this.docActual = docActual; }
    public Usuario getMiUsuario() { return miUsuario; }
    public void setMiUsuario(Usuario miUsuario) { this.miUsuario = miUsuario; }
    public String getNuevaContrasena() { return nuevaContrasena; }
    public void setNuevaContrasena(String nuevaContrasena) { this.nuevaContrasena = nuevaContrasena; }
    public List<DocenteCurso> getMisCursosAsignados() { return misCursosAsignados; }
    public List<Matricula> getAlumnosDeMisCursos() { return alumnosDeMisCursos; }
}