package main;

public class Pelicula {
    private String titulo, genero;
    private int votos;

    public Pelicula(String titulo, String genero, int votos) {
        this.titulo = titulo;
        this.genero = genero;
        this.votos = votos;}

    public int getVotos() {
        return votos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

  
}
