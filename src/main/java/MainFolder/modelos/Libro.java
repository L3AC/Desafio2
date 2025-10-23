
package MainFolder.modelos;

public class Libro extends MaterialEscrito {
    private String autor;
    private int numPaginas;
    private String editorial;
    private String isbn;
    private int añoPublicacion;

    public Libro() {}

    public Libro(String codigo, String titulo, int unidadesDisponibles,
                 String autor, int numPaginas, String editorial, String isbn, int añoPublicacion) {
        super(codigo, titulo, "libro", unidadesDisponibles);
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

    @Override
    public String toString() {
        return "Libro{" +
                "autor='" + autor + '\'' +
                ", numPaginas=" + numPaginas +
                ", editorial='" + editorial + '\'' +
                ", isbn='" + isbn + '\'' +
                ", añoPublicacion=" + añoPublicacion +
                "} " + super.toString();
    }
}
