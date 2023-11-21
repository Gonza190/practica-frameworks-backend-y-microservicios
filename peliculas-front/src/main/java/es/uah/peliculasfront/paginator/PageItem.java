//Clase obtenida de la sesión 3 del laboratorio de la asignatura
package es.uah.peliculasfront.paginator;

public class PageItem {

    private int numero;
    private boolean actual;

    public PageItem(int numero, boolean actual) {
        this.numero = numero;
        this.actual = actual;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isActual() {
        return actual;
    }

}
