
package MainFolder.modelos;

import java.sql.Timestamp;

public class Material {
    private int id;
    private String codigo;
    private String titulo;
    private String tipo; // 'libro', 'revista', 'cd_audio', 'dvd'
    private int unidadesDisponibles;
    private Timestamp fechaRegistro;

    // Constructor vacío
    public Material() {}

    // Constructor completo
    public Material(int id, String codigo, String titulo, String tipo, int unidadesDisponibles, Timestamp fechaRegistro) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.tipo = tipo;
        this.unidadesDisponibles = unidadesDisponibles;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getUnidadesDisponibles() { return unidadesDisponibles; }
    public void setUnidadesDisponibles(int unidadesDisponibles) { this.unidadesDisponibles = unidadesDisponibles; }

    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", unidadesDisponibles=" + unidadesDisponibles +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}