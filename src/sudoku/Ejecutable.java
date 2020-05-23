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
public class Ejecutable {

    private static String imprime(int matriz[][]){
        if (matriz == null) {
            throw new NullPointerException("hay un numero repetido, el problema dado es incorrecto");
        }
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<=8; i++){
            sb.append("\n");
            for(int j=0; j<=8; j++){
              sb.append(matriz[i][j]+" ");
              
            }

            
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] sudoku1 = new int[][]{
          { 4, 2, 7,     8, 6, 5,     0, 1, 3},
          { 9, 1, 5,     2, 0, 3,     6, 0, 0},
          { 6, 8, 3,     0, 9, 0,     2, 0, 4},
          
          { 0, 0, 0,     0, 0, 0,     0, 0, 5},
          { 0, 4, 0,     1, 0, 8,     7, 0, 6},
          { 2, 5, 6,     3, 0, 4,     8, 9, 0},
          
          { 5, 9, 0,     0, 0, 7,     1, 0, 2},
          { 1, 0, 2,     0, 8, 0,     4, 7, 0},
          { 0, 0, 4,     9, 1, 0,     0, 3, 8}
        };
        
        Metodos prueba = new Metodos(sudoku1);
        ConjuntoADT<Integer> conjunto = new ConjuntoA();
        //System.out.println(prueba.recorridoPorTodaLaMatriz(2, 3)[0]);
        System.out.println(imprime(prueba.solucionFinal()));
    }
    
}
