package pe.edu.utp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import pe.edu.utp.entity.*;
import pe.edu.utp.service.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@ViewScoped
public class MatriculaBean implements Serializable {

    @Inject private MatriculaService matriculaService;
    @Inject private EstudianteService estudianteService;
    @Inject private PeriodoService periodoService;
    @Inject private GradoService gradoService;
    @Inject private SeccionService seccionService;

    private List<Matricula> matriculas;
    private List<Estudiante> estudiantes;
    private List<Periodo> periodos;
    private List<Grado> grados;
    private List<Seccion> secciones;

    private Matricula matriculaSeleccionada;
    private Integer idEstudiante;
    private Integer idPeriodo;
    private Integer idGrado;
    private Integer idSeccion;

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        matriculas = matriculaService.listarTodos();
        estudiantes = estudianteService.listarTodos();
        periodos = periodoService.listarTodos();
        grados = gradoService.listarTodos();
        secciones = seccionService.listarTodos();
    }

    public void nuevaMatricula() {
        matriculaSeleccionada = new Matricula();
        matriculaSeleccionada.setFechaMatricula(LocalDate.now());
        matriculaSeleccionada.setEstado("ACTIVA");
        idEstudiante = null;
        idPeriodo = null;
        idGrado = null;
        idSeccion = null;
    }

    public void guardarMatricula() {
        try {
            Estudiante e = new Estudiante(); e.setId(idEstudiante);
            Periodo p = new Periodo(); p.setId(idPeriodo);
            Grado g = new Grado(); g.setId(idGrado);
            Seccion s = new Seccion(); s.setId(idSeccion);

            matriculaSeleccionada.setEstudiante(e);
            matriculaSeleccionada.setPeriodo(p);
            matriculaSeleccionada.setGrado(g);
            matriculaSeleccionada.setSeccion(s);

            if (matriculaSeleccionada.getId() == null) {
                matriculaService.registrarMatricula(matriculaSeleccionada);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Matrícula registrada correctamente"));
            } else {
                matriculaService.actualizarMatricula(matriculaSeleccionada);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Matrícula actualizada correctamente"));
            }
            
            cargarDatos();
            PrimeFaces.current().executeScript("PF('dialogoMatricula').hide()");
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-matriculas");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El estudiante ya está matriculado en este periodo."));
        }
    }

    public void eliminarMatricula() {
        try {
            matriculaService.eliminarMatricula(matriculaSeleccionada.getId());
            cargarDatos();
            matriculaSeleccionada = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Éxito", "Matrícula eliminada correctamente"));
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-matriculas");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede eliminar la matrícula (tiene notas o pagos)."));
        }
    }

    // Getters y Setters
    public List<Matricula> getMatriculas() { return matriculas; }
    public void setMatriculas(List<Matricula> matriculas) { this.matriculas = matriculas; }
    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(List<Estudiante> estudiantes) { this.estudiantes = estudiantes; }
    public List<Periodo> getPeriodos() { return periodos; }
    public void setPeriodos(List<Periodo> periodos) { this.periodos = periodos; }
    public List<Grado> getGrados() { return grados; }
    public void setGrados(List<Grado> grados) { this.grados = grados; }
    public List<Seccion> getSecciones() { return secciones; }
    public void setSecciones(List<Seccion> secciones) { this.secciones = secciones; }
    public Matricula getMatriculaSeleccionada() { return matriculaSeleccionada; }
    public void setMatriculaSeleccionada(Matricula matriculaSeleccionada) { this.matriculaSeleccionada = matriculaSeleccionada; }
    public Integer getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(Integer idEstudiante) { this.idEstudiante = idEstudiante; }
    public Integer getIdPeriodo() { return idPeriodo; }
    public void setIdPeriodo(Integer idPeriodo) { this.idPeriodo = idPeriodo; }
    public Integer getIdGrado() { return idGrado; }
    public void setIdGrado(Integer idGrado) { this.idGrado = idGrado; }
    public Integer getIdSeccion() { return idSeccion; }
    public void setIdSeccion(Integer idSeccion) { this.idSeccion = idSeccion; }
}