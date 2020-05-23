
package sudoku;

import java.util.Iterator;

/**
 *
 * @author José Pablo
 * @author Alexander
 * @author Alejandro
 */


public interface ConjuntoADT <T> extends Iterable<T>{
    
    public boolean contiene(T elem);
    public boolean estaVacio();
    public int getCardinalidad();
    public String toString();
    public boolean agrega(T elemento);
    public T quita(T elem);
    public int busca(T elem, int i);
    public ConjuntoADT<T> union(ConjuntoADT<T> otro);
    public ConjuntoADT<T> interseccion(ConjuntoADT<T> otro);
    public ConjuntoADT<T> diferencia(ConjuntoADT<T> otro);
    public Iterator<T> iterator();
    public boolean equalsR(ConjuntoADT<T> otro);
    
    
}
