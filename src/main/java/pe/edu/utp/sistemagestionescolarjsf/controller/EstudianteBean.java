package pe.edu.utp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import pe.edu.utp.entity.Estudiante;
import pe.edu.utp.service.EstudianteService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EstudianteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EstudianteService estudianteService;

    private List<Estudiante> estudiantes;
    private Estudiante estudianteSeleccionado;

    @PostConstruct
    public void init() {
        estudianteSeleccionado = new Estudiante();
        cargarDatos();
    }

    private void cargarDatos() {
        estudiantes = estudianteService.listarTodos();
    }

    public void nuevoEstudiante() {
        estudianteSeleccionado = new Estudiante();
    }

    public void guardarEstudiante() {
        try {
            if (estudianteSeleccionado == null) {
                estudianteSeleccionado = new Estudiante();
            }

            if (estudianteSeleccionado.getId() == null) {
                estudianteService.registrarEstudiante(estudianteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Éxito",
                                "Estudiante registrado. Cuenta de acceso creada automáticamente.")
                );
            } else {
                estudianteService.actualizarEstudiante(estudianteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Éxito",
                                "Estudiante actualizado correctamente")
                );
            }

            cargarDatos();
            PrimeFaces.current().executeScript("PF('dialogoEstudiante').hide()");
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-estudiantes");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "El DNI ya existe o los datos son inválidos.")
            );
        }
    }

    public void eliminarEstudiante() {
        try {
            if (estudianteSeleccionado != null && estudianteSeleccionado.getId() != null) {
                estudianteService.eliminarEstudiante(estudianteSeleccionado.getId());
                cargarDatos();
                estudianteSeleccionado = new Estudiante();

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Éxito",
                                "Estudiante eliminado correctamente")
                );

                PrimeFaces.current().ajax().update("form:mensajes", "form:dt-estudiantes");
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Aviso",
                                "No se seleccionó ningún estudiante para eliminar.")
                );
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "No se pudo eliminar el estudiante.")
            );
        }
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Estudiante getEstudianteSeleccionado() {
        return estudianteSeleccionado;
    }

    public void setEstudianteSeleccionado(Estudiante estudianteSeleccionado) {
        this.estudianteSeleccionado = estudianteSeleccionado;
    }
}