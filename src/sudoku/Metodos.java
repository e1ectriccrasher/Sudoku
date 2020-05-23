/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author Alejandro César Moya
 * @author Jose Pablo Sanchez
 * @author Alexander Graciano
 */
public class Metodos {
    
    private int[][] mat;
    private final int MAX = 9;

    public Metodos() {
        mat = new int[MAX][MAX];
    }

    /**
     * Constructor que recibe una matriz como parámetro
     * @param mat: matriz dada para tener como atributo
     */
    public Metodos(int[][] mat) {
        this.mat = mat;
    }
    
        /**
     * Metodo recursivo que verifica que todas las columnas de cuadricula tengan 
     * formato válido y datos válidos
     * @param ren: el renglon de cuadricula que se esta analizando
     * @param col: la columna de cuadricula que se esta analizando
     * @param conj: el conjunto en el que se meten los digitos de la columna 
     * que se esta analizando
     * @return <ul>
     * <li>true: Si todos las columnas tienen formato valido</li>
     * <li>false: Si alguna columna es invalida</li>
     * <li>llamada recursiva: si aún faltan columnas por revisar</li>
     * </ul>  
     */
    
    public boolean verificaCol(int ren, int col, ConjuntoADT<Integer> conj) {
        int num = mat[ren][col];
        
        if (num != 0) {
          
            if (!conj.agrega(num)) {
                return false;
            }
        }
        if (ren == MAX - 1) {
            if (col == MAX - 1) {
                return true;
            } else {
                conj = new ConjuntoA();
                return verificaCol(0, col+1, conj);
            }
        } else {
            return verificaCol(ren+1, col, conj);
        }
    }
    
        /**
     * Metodo recursivo que verifica que todos los renglones de cuadricula tengan
     * formato válido y datos válidos
     * @param ren: el renglon de cuadricula que se esta analizando
     * @param col: la columna de cuadricula que se esta analizando
     * @param conj: el conjunto en el que se meten los digitos del renglon 
     * que se esta analizando
     * @return <ul>
     * <li>true: Si todos los renglones son validos</li>
     * <li>false: Si algun renglon es invalido</li>
     * <li>llamada recursiva: si aún faltan columnas por revisar</li>
     * </ul> 
     */
    
    public boolean verificaRen(int ren, int col, ConjuntoADT<Integer> conj) {
        int num=mat[ren][col];
        
        if (num != 0) {
            if (!conj.agrega(num)) {
                return false;
            }
        }
        if (col == MAX - 1) {
            if (ren == MAX - 1) {
                return true;
            } else {
                conj = new ConjuntoA();
                return verificaRen(ren+1, 0, conj);
            }
        } else {
            return verificaRen(ren, col+1, conj);
        }
    }
    
        /**
     * Metodo recursivo que verifica que todos los cuadrados de 3x3 cuadricula
     * son válidos y tienen datos válidos
     * @param ren: el renglon de cuadricula que se esta analizando
     * @param col: la columna de cuadricula que se esta analizando
     * @param conj: el conjunto en el que se meten los digitos del cuadrado 
     * que se esta analizando
     * @return <ul>
     * <li>true: Si todos los cuadrados son validos</li>
     * <li>false: Si alguno de los cuadrados no es valido</li>
     * <li>llamada recursiva: si aún quedan cuadrados por revisar</li>
     * </ul>  
     */
    
    public boolean verificaCuad3x3(int ren, int col, ConjuntoADT<Integer> conj) {
        int numero;

        numero = mat[ren][col];
       
        if (numero!=0) {
            if (!conj.agrega(numero)){
                return false;
            }
        }
        if (ren==MAX-1 && col==MAX-1) 
        {
            return true;
        } else if ((col +1) % 3 == 0) {
            if ((ren+1) % 3 == 0) {
                conj = new ConjuntoA(); 
                if (col == MAX-1) {
                    return verificaCuad3x3(ren+1, 0, conj);
                } else {
                    return verificaCuad3x3(ren-2, col+1, conj);
                }
            } else {
                return verificaCuad3x3(ren+1, col-2, conj);
            }
        } else {
            return verificaCuad3x3(ren, col+1, conj);
        }
    }
    
    
        /**
     * Metodo que verifica que no hayan numeros repetidos en los renglones, 
     * columnas y cuadros de 3 x 3. Junta todas las verificaciones para saber si 
     * se puede resolver el juego o no.
     * @return <ul>
     * <li>true: Si el sudoku es válido y se puede resolver</li>
     * <li>false: Si el sudoku no es válido y no se puede resolver</li>
     * </ul>  
     * @see verificaRenglones 
     * @see verificaColumnas
     * @see verificaCuadrados
     */
    
    public boolean preResol() {
        boolean res = false;

        if (this.verificaRen(0, 0, new ConjuntoA()) && this.verificaCol(0, 0, new ConjuntoA()) && this.verificaCuad3x3(0, 0, new ConjuntoA())) {
            res = true;
        }
        return res;
    }
    
    
        /**
     * Metodo  que se encarga de encontrar la siguiente 
     * casilla en cuadricula basandose en la posicion marcada por renglon y 
     * columna. Se utiliza en el resuelve. 
     * @param ren: de la casilla que se acaba de examinar
     * @param col: de la casilla que se acaba de examinar
     * @return: un arreglo de longitud dos. <ul>
     * <li>En la posicion 0 se encuentra el renglon de la siguiente casilla. Si 
     * en la posicion 0 hay un 9, ya no hay siguiente casilla. </li>
     * <li>en la posicion 1 se encuentra la columna de la siguiente casilla</li>
     * </ul> 
     */
    
    public int[] buscaCasilla(int ren, int col) {
        int[] pos;

        pos = new int[2];
        pos[0]=ren;
        if (ren <= MAX - 1) {
            if (col == MAX - 1) {
                pos[0] = ren+1;
                pos[1] = 0;
            } else {
                pos[1] = col+1;
            }
        } else {
            pos[0] = -1;
            pos[1] = -1;
        }
        
        return pos;
    }
    
         /**
     * Metodo auxiliar que verifica que numero no este repetido dentro de 
     * renglon en cuadricula. Para  agrega en un conjunto los numeros de esa 
     * renglon y numero.
     * @param ren: de la casilla en donde se intentara agregar a numero 
     * @param inter: entero del 1-9 que se va a intentar poner en la casilla
     * @return  <ul>
     * <li>true: Si el numero que se esta probando es valido en el renglon</li>
     * <li>false: Si el numero que se esta probando no es valido en el renglon
     * </li>
     * </ul> 
     */
    
    public boolean revisaRepRen(int ren,  int inter) {
        ConjuntoADT<Integer> conjuntoRen = new ConjuntoA();
        boolean res;
        int col = 0; 
        int value;

        conjuntoRen.agrega(inter);
        res = true;
        while (res && col < MAX) {
            value = mat[ren][col];
            if (value != 0) {
                res = conjuntoRen.agrega(value);
            }
            col++;
        }
        return res;
    }
    
        /**
     * Metodo auxiliar que verifica que numero no este repetido dentro de 
     * columna en cuadricula. Para  agrega en un conjunto los numeros de esa 
     * columna y numero.
     * @param col: de la casilla en donde se intentara agregar a numero 
     * @param inte: entero del 1-9 que se va a intentar poner en la casilla
     * @return  <ul>
     * <li>true: Si el numero que se esta probando es valido en la columna</li>
     * <li>false: Si el numero que se esta probando no es valido en la columna
     * </li>
     * </ul> 
     */
    
   public boolean revisaRepCol(int col, int inte) {
        ConjuntoADT<Integer> conjuntoColumna = new ConjuntoA();
        boolean res;
        int ren = 0;
        int value;

        conjuntoColumna.agrega(inte);
        res = true;
        while (res && ren < MAX) {
            value = mat[ren][col];
            if (value != 0) {
                res = conjuntoColumna.agrega(value);
            }
            ren++;
        }
        return res;
    }
   
       /**
     * Metodo auxiliar que verifica que numero no este repetido dentro del 
     * cuadrado perteneciente a la posicion indicada por renglon y columna de
     * cuadricula. Para hacerlo, determina el cuadrado de 3 x 3 perteneciente 
     * a la posicion marcada por renglon y columna y agrega en un conjunto los 
     * numeros de ese cuadrado y numero.
     * @param ren: de la casilla en donde se intentara agregar a numero
     * @param col: de la casilla en donde se intentara agregar a numero 
     * @param inter: entero del 1-9 que se va a intentar poner en la casilla
     * @return  <ul>
     * <li>true: Si el numero que se esta probando es valido en el cuadrado
     * de 3 x 3</li>
     * <li>false: Si el numero que se esta probando no es valido en el cuadrado 
     * de 3 x 3</li>
     * </ul>  
     */
   
   public boolean revisaCuad3x3(int ren, int col, int inter) {

        boolean res = true;
        ConjuntoADT<Integer> cuad = new ConjuntoA();
        int maxRen, maxCol, value;

        if (ren < 3) {
            maxRen = 3;
        } else {
            if (ren < 6) {
                maxRen = 6;
            } else {
                maxRen = 9;
            }
        }

        if (col < 3) {
            maxCol = 3;
        } else {
            if (col < 6) {
                maxCol = 6;
            } else {
                maxCol = 9;
            }
        }

        ren = maxRen-3;
        col=maxCol-3;
        cuad.agrega(inter);
        
            while(res && ren<maxRen){
                while(res && col<maxCol){
                    value=mat[ren][col];
                    
                    if(value!=0){
                         res=cuad.agrega(value);
                    }
                    col++;
                }
                ren++;
                col=maxCol-3;  
                }     
        return res;
    }
   
       /**
     * Metodo que da la solución final del sudoku a través de una búsqueda exhaustiva 
     * con retroceso similar a la solución del laberinto. Se modifica la matriz
     * que la clase tiene como atributo. 
     * @param ren: de la casilla de cuadricula para la cual se esta buscando 
     * una solucion
     * @param col: de la casilla de cuadricula para la cual se esta buscando 
     * una solucion
     * @return <ul>
     * <li>true: Si el sudoku tuvo solucion</li>
     * <li>false: Si no se puso encontrar una solucion despues de 3 segundos
     * </li>
     * <li>Llamada recursiva: Cuando no se pudo hacer nada en esa casilla y se
     * pasa a la siguiente</li>
     * </ul> 
     */
   private boolean solucionFinal(int ren, int col) {
        int posicionMatriz[];

        if (ren == MAX) {
            return true;
        }
        if (mat[ren][col] != 0) {
            posicionMatriz = buscaCasilla(ren, col);
            return solucionFinal(posicionMatriz[0], posicionMatriz[1]);
        } else {
            for (int i = 9; i > 0; i--){ 
                if (revisaRepRen(ren, i) && revisaRepCol(col, i) && revisaCuad3x3(ren, col, i)) {
                    mat[ren][col] = i;
                    posicionMatriz = buscaCasilla(ren, col);
                    if (solucionFinal(posicionMatriz[0], posicionMatriz[1])) {
                        return true;
                    }
                    mat[ren][col] = 0;
                }
            }
            return false;
        }
    }
 
     /**
     * Metodo público de la llamada recursiva para la solución final, sin parámetros
     */
    public int[][] solucionFinal() {
        
        if (!solucionFinal(0, 0)) {
            mat = null;
        }
        return mat;
    }

    
    public static void main(String[] args) {
        // TODO code application logic here
        
        
    }
    
}
