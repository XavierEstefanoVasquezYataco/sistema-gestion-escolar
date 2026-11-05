package pe.edu.utp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pe.edu.utp.entity.EstadoPago;
import pe.edu.utp.entity.Matricula;
import pe.edu.utp.entity.Pago;
import pe.edu.utp.service.CursoService;
import pe.edu.utp.service.DocenteService;
import pe.edu.utp.service.EstudianteService;
import pe.edu.utp.service.MatriculaService;
import pe.edu.utp.service.PagoService;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class DashboardBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private DocenteService docenteService;

    @Inject
    private CursoService cursoService;

    @Inject
    private PagoService pagoService;

    @Inject
    private MatriculaService matriculaService;

    private long totalEstudiantes;
    private long totalDocentes;
    private long totalCursos;
    private long pagosPendientes;

    private String periodoFiltro = "MENSUAL";

    private BarChartModel barModel;
    private PieChartModel pieModel;

    @PostConstruct
    public void init() {
        totalEstudiantes = estudianteService.listarTodos().size();
        totalDocentes = docenteService.listarTodos().size();
        totalCursos = cursoService.listarTodos().size();

        List<Pago> todosLosPagos = pagoService.listarTodos();

        pagosPendientes = todosLosPagos.stream()
                .filter(p -> p.getEstado() == EstadoPago.PENDIENTE)
                .count();

        inicializarGraficoBarras(filtrarPagosPorPeriodo(todosLosPagos));
        inicializarGraficoPie();
    }

    public void filtrarGraficos() {
        List<Pago> todosLosPagos = pagoService.listarTodos();
        List<Pago> pagosFiltrados = filtrarPagosPorPeriodo(todosLosPagos);
        inicializarGraficoBarras(pagosFiltrados);
    }

    private List<Pago> filtrarPagosPorPeriodo(List<Pago> pagos) {
        LocalDate hoy = LocalDate.now();

        return pagos.stream()
                .filter(p -> p.getFechaPago() != null)
                .filter(p -> {
                    LocalDate fecha = p.getFechaPago().toLocalDate();

                    switch (periodoFiltro) {
                        case "SEMANAL":
                            LocalDate inicioSemana = hoy.with(DayOfWeek.MONDAY);
                            LocalDate finSemana = hoy.with(DayOfWeek.SUNDAY);
                            return !fecha.isBefore(inicioSemana) && !fecha.isAfter(finSemana);

                        case "ANUAL":
                            return fecha.getYear() == hoy.getYear();

                        case "MENSUAL":
                        default:
                            return fecha.getYear() == hoy.getYear()
                                    && fecha.getMonthValue() == hoy.getMonthValue();
                    }
                })
                .collect(Collectors.toList());
    }

    private void inicializarGraficoBarras(List<Pago> pagosFiltrados) {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Recaudación Real (S/)");

        Map<String, Double> recaudacionPorConcepto = pagosFiltrados.stream()
                .filter(p -> p.getEstado() == EstadoPago.PAGADO)
                .collect(Collectors.groupingBy(
                        p -> (p.getTipoPago() != null && p.getTipoPago().getNombre() != null)
                                ? p.getTipoPago().getNombre()
                                : "Otros Conceptos",
                        Collectors.summingDouble(p -> {
                            BigDecimal monto = p.getMonto();
                            return monto != null ? monto.doubleValue() : 0.0;
                        })
                ));

        List<Object> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> bgColors = new ArrayList<>();

        String[] coloresPaleta = {
                "rgba(79, 70, 229, 0.85)",
                "rgba(59, 130, 246, 0.85)",
                "rgba(96, 165, 250, 0.85)",
                "rgba(129, 140, 248, 0.85)"
        };

        int i = 0;

        if (recaudacionPorConcepto.isEmpty()) {
            labels.add("Sin Ingresos");
            values.add(0.0);
            bgColors.add(coloresPaleta[0]);
        } else {
            for (Map.Entry<String, Double> entry : recaudacionPorConcepto.entrySet()) {
                labels.add(entry.getKey());
                values.add(entry.getValue());
                bgColors.add(coloresPaleta[i % coloresPaleta.length]);
                i++;
            }
        }

        barDataSet.setData(values);
        barDataSet.setBackgroundColor(bgColors);
        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        barModel.setData(data);
    }

    private void inicializarGraficoPie() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        PieChartDataSet pieDataSet = new PieChartDataSet();

        List<Matricula> matriculas = matriculaService.listarTodos();
        Map<String, Long> conteoPorNivel = matriculas.stream()
                .filter(m -> m.getGrado() != null && m.getGrado().getNivel() != null)
                .collect(Collectors.groupingBy(
                        m -> m.getGrado().getNivel(),
                        Collectors.counting()
                ));

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> bgColors = new ArrayList<>();

        String[] coloresPaleta = {"#10b981", "#f59e0b", "#3b82f6", "#ef4444"};
        int i = 0;

        if (conteoPorNivel.isEmpty()) {
            labels.add("Sin Matrículas");
            values.add(1);
            bgColors.add("#cbd5e1");
        } else {
            for (Map.Entry<String, Long> entry : conteoPorNivel.entrySet()) {
                labels.add("Nivel " + entry.getKey());
                values.add(entry.getValue());
                bgColors.add(coloresPaleta[i % coloresPaleta.length]);
                i++;
            }
        }

        pieDataSet.setData(values);
        pieDataSet.setBackgroundColor(bgColors);
        data.addChartDataSet(pieDataSet);
        data.setLabels(labels);
        pieModel.setData(data);
    }

    public long getTotalEstudiantes() {
        return totalEstudiantes;
    }

    public long getTotalDocentes() {
        return totalDocentes;
    }

    public long getTotalCursos() {
        return totalCursos;
    }

    public long getPagosPendientes() {
        return pagosPendientes;
    }

    public String getPeriodoFiltro() {
        return periodoFiltro;
    }

    public void setPeriodoFiltro(String periodoFiltro) {
        this.periodoFiltro = periodoFiltro;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }
}