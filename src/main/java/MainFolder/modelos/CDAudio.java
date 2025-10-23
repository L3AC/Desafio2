
package MainFolder.modelos;

import java.sql.Time;

public class CDAudio extends MaterialAudiovisual {
    private String artista;
    private String genero;
    private Time duracion;
    private int numCanciones;

    public CDAudio() {}

    public CDAudio(int id, String codigo, String titulo, int unidadesDisponibles,
                   String artista, String genero, Time duracion, int numCanciones) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.tipo = "cd_audio";
        this.unidadesDisponibles = unidadesDisponibles;
        this.artista = artista;
        this.genero = genero;
        this.duracion = duracion;
        this.numCanciones = numCanciones;
    }

    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Time getDuracion() { return duracion; }
    public void setDuracion(Time duracion) { this.duracion = duracion; }

    public int getNumCanciones() { return numCanciones; }
    public void setNumCanciones(int numCanciones) { this.numCanciones = numCanciones; }
}
