/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainFolder.modelos;

import java.sql.Time;

public class DVD extends MaterialAudioVisual {
    private String director;
    private Time duracion;
    private String genero;

    public DVD() {}

    public DVD(int id, String codigo, String titulo, String tipo, int unidadesDisponibles,
               String director, Time duracion, String genero) {
        super();
        setId(id);
        setCodigo(codigo);
        setTitulo(titulo);
        setTipo(tipo);
        setUnidadesDisponibles(unidadesDisponibles);
        this.director = director;
        this.duracion = duracion;
        this.genero = genero;
    }

    // Getters y Setters
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public Time getDuracion() { return duracion; }
    public void setDuracion(Time duracion) { this.duracion = duracion; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    @Override
    public String toString() {
        return "DVD{" +
                "director='" + director + '\'' +
                ", duracion=" + duracion +
                ", genero='" + genero + '\'' +
                "} " + super.toString();
    }
}