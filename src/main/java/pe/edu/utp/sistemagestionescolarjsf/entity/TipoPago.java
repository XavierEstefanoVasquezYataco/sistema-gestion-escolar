package pe.edu.utp.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tipos_pago")
public class TipoPago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(name = "monto_referencial", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoReferencial;

    public TipoPago() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getMontoReferencial() { return montoReferencial; }
    public void setMontoReferencial(BigDecimal montoReferencial) { this.montoReferencial = montoReferencial; }
}