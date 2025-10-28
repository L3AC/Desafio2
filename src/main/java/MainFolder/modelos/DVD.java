
package MainFolder.modelos;

import java.sql.Time;

public class DVD extends Material {
    private String director;
    private Time duracion;
    private String genero;

    public DVD() {}

    public DVD(int id, String codigo, String titulo, int unidadesDisponibles,
               String director, Time duracion, String genero) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.tipo = "dvd";
        this.unidadesDisponibles = unidadesDisponibles;
        this.director = director;
        this.duracion = duracion;
        this.genero = genero;
    }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public Time getDuracion() { return duracion; }
    public void setDuracion(Time duracion) { this.duracion = duracion; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}