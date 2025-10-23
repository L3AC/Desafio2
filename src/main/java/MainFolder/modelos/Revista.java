
package MainFolder.modelos;
import java.sql.Date;

public class Revista extends MaterialEscrito {
    private String editorial;
    private String periodicidad;
    private Date fechaPublicacion;

    public Revista() {}

    public Revista(int id, String codigo, String titulo, int unidadesDisponibles,
                   String editorial, String periodicidad, Date fechaPublicacion) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.tipo = "revista";
        this.unidadesDisponibles = unidadesDisponibles;
        this.editorial = editorial;
        this.periodicidad = periodicidad;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public String getPeriodicidad() { return periodicidad; }
    public void setPeriodicidad(String periodicidad) { this.periodicidad = periodicidad; }

    public Date getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(Date fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
}
