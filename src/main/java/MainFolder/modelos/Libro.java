
package MainFolder.modelos;

import java.sql.Timestamp;

public class Libro extends MaterialEscrito {
    private String autor;
    private int numPaginas;
    private String editorial;
    private String isbn;
    private int añoPublicacion;

    public Libro() {}

    // Constructor completo (opcional, pero útil)
    public Libro(int id, String codigo, String titulo, int unidadesDisponibles,
                 String autor, int numPaginas, String editorial, String isbn, int añoPublicacion) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.tipo = "libro";
        this.unidadesDisponibles = unidadesDisponibles;
        this.autor = autor;
        this.numPaginas = numPaginas;
        this.editorial = editorial;
        this.isbn = isbn;
        this.añoPublicacion = añoPublicacion;
    }

    // Getters y Setters
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getNumPaginas() { return numPaginas; }
    public void setNumPaginas(int numPaginas) { this.numPaginas = numPaginas; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getAñoPublicacion() { return añoPublicacion; }
    public void setAñoPublicacion(int añoPublicacion) { this.añoPublicacion = añoPublicacion; }

}
