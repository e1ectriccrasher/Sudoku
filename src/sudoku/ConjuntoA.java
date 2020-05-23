
package sudoku;

import java.util.Iterator;

/**
 *
 * @author José Pablo
 * @author Alexander
 * @author Alejandro
 */

public class ConjuntoA <T> implements ConjuntoADT<T>{
    
    private T[] conjunto;
    private int cardinalidad;
    private final int MAX=100;
    
    public ConjuntoA(){
        conjunto=(T[]) new Object[MAX];
        cardinalidad=0;
    }
    
    public ConjuntoA(int max){
        conjunto=(T[]) new Object[max];
        cardinalidad=0;
    }
    
    
    @Override
    public int getCardinalidad(){
        return cardinalidad;
    }
    
    @Override
    public Iterator<T> iterator(){
        return new IteradorArreglo(conjunto,cardinalidad);
    }
    
    
    @Override
    public boolean contiene(T elemento){
        boolean resp=false;
        int i=0;
        
        while(!resp && i<cardinalidad){
            if(conjunto[i].equals(elemento)){
                resp=true;
            }
            i++;
        }
        
        return resp;
    }
    
    @Override
    public boolean agrega(T elemento){
        boolean resp=false;
        
        if(elemento!=null && !contiene(elemento)){
            if(cardinalidad==conjunto.length-1)
                expande();
            
            conjunto[cardinalidad]=elemento;
            cardinalidad++;
            resp=true;
        }
        
        return resp;
    }
    
    public void expande(){
        T[] nuevo=(T[]) new Object[conjunto.length*2];
        
        for (int i = 0; i < cardinalidad; i++)
            nuevo[i]=conjunto[i];
        
        conjunto=nuevo;
    }
    
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        
        for (int i = 0; i < cardinalidad; i++) {
            sb.append(conjunto[i]).append(" ");
        }
        
        return sb.toString();
    }
    
    @Override
    public T quita(T elem){
        T resp=null;
        
        if(elem!=null){
            int pos=0;
            pos=busca(elem,0);
            
            if(pos>=0){
                resp=conjunto[pos];
                conjunto[pos]=conjunto[cardinalidad-1];
                conjunto[cardinalidad]=null;
                cardinalidad--;
            }
        }
        
        return resp;
    }
    
    public int busca(T elem, int i){
        if(i==cardinalidad)
            return -1;
        else{
            if(conjunto[i].equals(elem))
                return i;
            else
                return busca(elem,i+1);
        }
    }
    
    @Override
    public boolean estaVacio(){
        return cardinalidad==0;
    }
    
    @Override
    public ConjuntoADT<T> union(ConjuntoADT<T> otro){
        if(otro==null)
            throw new RuntimeException();
        
        ConjuntoA<T> nuevo=new ConjuntoA(cardinalidad);
        
        for (int i = 0; i < cardinalidad; i++) {
            nuevo.agrega(conjunto[i]);
        }
        
        Iterator<T> it=otro.iterator();
        
        while(it.hasNext())
            nuevo.agrega(it.next());
        
        return nuevo;
    }
    

    @Override
    public ConjuntoADT<T> interseccion(ConjuntoADT<T> otro){
        if(otro==null)
            throw new RuntimeException();
        
        ConjuntoA<T>  nuevo=new ConjuntoA();
        Iterator<T> it=otro.iterator();
        
        return interseccion(it,nuevo);
    }
    
    private ConjuntoADT<T> interseccion(Iterator<T> it, ConjuntoADT<T> nuevo){
        if(it.hasNext()){
            T dato;
            
            dato=it.next();
            if(this.contiene(dato))
                nuevo.agrega(dato);
            
            return interseccion(it,nuevo);
        }
        else
            return nuevo;
    }
    
    
    @Override
    public ConjuntoADT<T> diferencia(ConjuntoADT<T> otro){
        if(otro==null)
            throw new RuntimeException();
        
        ConjuntoA<T> nuevo=new ConjuntoA();
        Iterator<T> it=otro.iterator();
        
        return diferencia(otro,it,nuevo);
    }
    
    private ConjuntoADT<T> diferencia(ConjuntoADT<T> otro, Iterator<T> it, ConjuntoADT<T> nuevo){
        if(it.hasNext()){
            T dato=it.next();
            
            if(!this.contiene(dato))
                nuevo.agrega(dato);
            
            return diferencia(otro,it,nuevo);
        }
        else
            return nuevo;
    }
    
    public String imprimeIt(){
        Iterator<T> it=iterator();
        StringBuilder sb=new StringBuilder();
        
        while(it.hasNext()){
            sb.append(it.next());
        }
        
        return sb.toString();
    }
    
    
    
    public boolean equalsR(ConjuntoADT<T> otro){
        if(otro!=null && otro.getCardinalidad()==cardinalidad){
            return equalsR(0,otro);
        }
        else
            return false;
    }
    
    private boolean equalsR(int i, ConjuntoADT<T> otro){
        if(i<cardinalidad){
            if(otro.contiene(conjunto[i]))
                return equalsR(i+1,otro);
            else
                return false;
        }
        else
            return true;
    }
    
    
    
    
    
    
}
