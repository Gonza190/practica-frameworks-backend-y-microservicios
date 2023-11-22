package es.uah.peliculasfront.model;

//Clase para obtener de manera sencilla los datos del
//formulario al asignar película y actor
public class PeliculasActores {

    int idPelicula;
    int idActor;

    public PeliculasActores(int idPelicula, int idActor) {
        this.idPelicula = idPelicula;
        this.idActor = idActor;
    }

    public PeliculasActores(){

    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }
}
