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
import java.util.List;

@Named
@ViewScoped
public class NotaBean implements Serializable {

    @Inject private NotaService notaService;
    @Inject private MatriculaService matriculaService;
    @Inject private CursoService cursoService;

    private List<Nota> notas;
    private List<Matricula> matriculas;
    private List<Curso> cursos;

    private Nota notaSeleccionada;
    private Integer idMatricula;
    private Integer idCurso;

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        notas = notaService.listarTodas();
        // Solo listamos las matrículas activas
        matriculas = matriculaService.listarTodos().stream()
                .filter(m -> "ACTIVA".equals(m.getEstado()))
                .toList();
        cursos = cursoService.listarTodos();
    }

    public void nuevaNota() {
        notaSeleccionada = new Nota();
        idMatricula = null;
        idCurso = null;
    }

    public void guardarNota() {
        try {
            Matricula m = new Matricula(); m.setId(idMatricula);
            Curso c = new Curso(); c.setId(idCurso);

            notaSeleccionada.setMatricula(m);
            notaSeleccionada.setCurso(c);

            if (notaSeleccionada.getId() == null) {
                notaService.registrarNota(notaSeleccionada);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Calificación registrada correctamente"));
            } else {
                notaService.actualizarNota(notaSeleccionada);
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Éxito", "Calificación actualizada correctamente"));
            }
            
            cargarDatos();
            PrimeFaces.current().executeScript("PF('dialogoNota').hide()");
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-notas");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La nota para esta unidad y curso ya existe, o ocurrió un error en la BD."));
        }
    }

    public void eliminarNota() {
        try {
            notaService.eliminarNota(notaSeleccionada.getId());
            cargarDatos();
            notaSeleccionada = null;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Éxito", "Calificación eliminada correctamente"));
            PrimeFaces.current().ajax().update("form:mensajes", "form:dt-notas");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la calificación."));
        }
    }

    // Getters y Setters
    public List<Nota> getNotas() { return notas; }
    public void setNotas(List<Nota> notas) { this.notas = notas; }
    public List<Matricula> getMatriculas() { return matriculas; }
    public void setMatriculas(List<Matricula> matriculas) { this.matriculas = matriculas; }
    public List<Curso> getCursos() { return cursos; }
    public void setCursos(List<Curso> cursos) { this.cursos = cursos; }
    public Nota getNotaSeleccionada() { return notaSeleccionada; }
    public void setNotaSeleccionada(Nota notaSeleccionada) { this.notaSeleccionada = notaSeleccionada; }
    public Integer getIdMatricula() { return idMatricula; }
    public void setIdMatricula(Integer idMatricula) { this.idMatricula = idMatricula; }
    public Integer getIdCurso() { return idCurso; }
    public void setIdCurso(Integer idCurso) { this.idCurso = idCurso; }
}