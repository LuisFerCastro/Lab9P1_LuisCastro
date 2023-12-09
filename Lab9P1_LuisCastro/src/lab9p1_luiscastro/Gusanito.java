/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab9p1_luiscastro;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author lfern
 */
public class Gusanito {
    Random rand = new Random();
    ArrayList<String>instrucciones;
    char [][]tablero;
    int coorXGus;
    int coorYGus;
    int coorXMan;
    int coorYMan;
   
    public Gusanito(int x, int y) {
        tablero = new char[y][x];
        coorXGus = rand.nextInt(y);
        coorYGus = rand.nextInt(x);
        coorXMan = rand.nextInt(y);
        coorYMan = rand.nextInt(x);
        while(coorXGus == coorXMan && coorYGus == coorYMan){
            coorXMan = rand.nextInt(y);
            coorYMan = rand.nextInt(x);
        }
    }

    public int getCoorXGus() {
        return coorXGus;
    }

    public void setCoorXGus(int coorXGus) {
        this.coorXGus = coorXGus;
    }

    public int getCoorYGus() {
        return coorYGus;
    }

    public void setCoorYGus(int coorYGus) {
        this.coorYGus = coorYGus;
    }
    
    
    public ArrayList<String> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(ArrayList<String> instrucciones) {
        this.instrucciones = instrucciones;
    }

    
   public char [][] llenar_matriz(int x, int y){
       for (int i = 0; i < tablero.length; i++) {
           for (int j = 0; j < tablero[i].length; j++) {
               tablero[i][j] = '_';
           }
       }
       
        tablero[coorXGus][coorYGus] = 'G';
        tablero[coorXMan][coorYMan] = 'M';
       
        int menor = 0, mayor = 0;
        
        if(x > y){
            mayor = x;
            menor = y;
        }else if(x < y){
            mayor = y;
            menor = x;
        }
        int cant_obstaculos = rand.nextInt((mayor-menor)+1)+menor;
        
        while(cant_obstaculos != 0){
            int coorXrand = rand.nextInt(y);
            int coorYrand = rand.nextInt(x);
            while(tablero[coorXrand][coorYrand] != '_'){
                coorXrand = rand.nextInt(y);
                coorYrand = rand.nextInt(x);
            }
            tablero[coorXrand][coorYrand] = '#';
            cant_obstaculos--;
        }
       return tablero;
   }
    
    public void mostrarPaso(){
       char [][]tab;
       int coorXra = coorYMan;
       int coorYra = coorXMan;
       tab = tablero;
       String mensaje = matriztoString(tab);
       int paso = 0;
       int opcion = Integer.parseInt(JOptionPane.showInputDialog("Mapa: \n"+mensaje+"\n1.Ver siguiente paso. \n2. Ver paso anterior.\n3.Seleccionar paso.\n4.Volver al menu."));
        while(opcion != 4){
            switch(opcion){
                case 1:
                    String mov = instrucciones.get(paso);
                    int move = mov.charAt(0)-'0';
                    
                    if(mov.charAt(1) == 'R'){
                        if(coorYGus+move > tablero[0].length-1){
                            continue;
                        }else if(tablero[coorXGus][coorYGus+move]== '#'){
                            continue;
                        }else{
                            tablero[coorXGus][coorYGus] ='_';
                            tablero[coorXGus][coorYGus+move] ='G';
                            setCoorYGus(coorYGus+move);
                        }
                    }else if(mov.charAt(1)== 'L'){
                        if(coorYGus-move < 0){
                            continue;
                        }else if(tablero[coorXGus][coorYGus-move]== '#'){
                            continue;
                        }else{
                            tablero[coorXGus][coorYGus] ='_';
                            tablero[coorXGus][coorYGus-move] ='G';
                            setCoorYGus(coorYGus-move);
                        }
                    }else if(mov.charAt(1)=='D'){
                        if(coorXGus+move > tablero.length-1){
                            continue;
                        }else if(tablero[coorXGus+move][coorYGus]== '#'){
                            continue;
                        }else{
                            tablero[coorXGus][coorYGus] ='_';
                            tablero[coorXGus+move][coorYGus] ='G';
                            setCoorXGus(coorXGus+move);
                            
                        }
                    }else if(mov.charAt(1)=='U'){
                        if(coorXGus-move < 0){
                            continue;
                        }else if(tablero[coorXGus-move][coorYGus]== '#'){
                            continue;
                        }else{
                            tablero[coorXGus][coorYGus] ='_';
                            tablero[coorXGus-move][coorYGus] = 'G';
                            setCoorXGus(coorXGus-move);
                            
                        }
                    }
                    
                    
                    mensaje = matriztoString(tablero);
                    
                    if(tablero[coorXGus][coorYGus]== 'G'&&tablero[coorXra][coorYra] == 'G'){
                        JOptionPane.showMessageDialog(null, "Ha encontrado la manzana.");
                    }
                    paso++;
                    
                    break;
                case 2:
                        if(paso == 0){
                            String mov2 = instrucciones.get(paso);
                            int move2 = mov2.charAt(0)-'0';

                            if(mov2.charAt(1) == 'R'){
                                if(coorYGus+move2 > tablero[0].length-1){
                                    continue;
                                }else if(tablero[coorXGus][coorYGus+move2]== '#'){
                                    continue;
                                }else{
                                    tablero[coorXGus][coorYGus] ='_';
                                    tablero[coorXGus][coorYGus+move2] ='G';
                                    setCoorYGus(coorYGus+move2);

                                }
                            }else if(mov2.charAt(1)== 'L'){
                                if(coorYGus-move2 < 0){
                                    continue;
                                }else if(tablero[coorXGus][coorYGus-move2]== '#'){
                                    continue;
                                }else{
                                    tablero[coorXGus][coorYGus] ='_';
                                    tablero[coorXGus][coorYGus-move2] ='G';
                                    setCoorYGus(coorYGus-move2);

                                }
                            }else if(mov2.charAt(1)=='U'){
                                if(coorXGus+move2 > tablero.length-1){
                                    continue;
                                }else if(tablero[coorXGus+move2][coorYGus]== '#'){
                                    continue;
                                }else{
                                    tablero[coorXGus][coorYGus] ='_';
                                    tablero[coorXGus+move2][coorYGus] ='G';
                                    setCoorXGus(coorXGus+move2);

                                }
                            }else if(mov2.charAt(1)=='D'){
                                if(coorXGus-move2 < 0){
                                    continue;
                                }else if(tablero[coorXGus-move2][coorYGus]== '#'){
                                    continue;
                                }else{
                                    tablero[coorXGus][coorYGus] ='_';
                                    tablero[coorXGus-move2][coorYGus] = 'G';
                                    setCoorXGus(coorXGus-move2);

                                }
                            }
                            
                            mensaje = matriztoString(tablero);
                            
                            
                        }else if (paso > 0){
                            paso = paso-1;
                            String mov3 = instrucciones.get(paso);
                            int move3 = mov3.charAt(0)-'0';

                            if(mov3.charAt(1) == 'R'){
                                if(coorYGus+move3 > tablero[0].length-1){
                                    continue;
                                }else if(tablero[coorXGus][coorYGus+move3]== '#'){
                                    continue;
                                }else{
                                    tablero[coorXGus][coorYGus] ='_';
                                    tablero[coorXGus][coorYGus+move3] ='G';
                                    setCoorYGus(coorYGus+move3);

                                }
                            }else if(mov3.charAt(1)== 'L'){
                                if(coorYGus-move3 < 0){
                                    continue;
                                }else if(tablero[coorXGus][coorYGus-move3]== '#'){
                                    continue;
                                }else{
                                    tablero[coorXGus][coorYGus] ='_';
                                    tablero[coorXGus][coorYGus-move3] ='G';
                                    setCoorYGus(coorYGus-move3);
                                }
                            }else if(mov3.charAt(1)=='U'){
                                if(coorXGus+move3 > tablero.length-1){
                                    continue;
                                }else if(tablero[coorXGus+move3][coorYGus]== '#'){
                                    continue;
                                }else{
                                    tablero[coorXGus][coorYGus] ='_';
                                    tablero[coorXGus+move3][coorYGus] ='G';
                                    setCoorXGus(coorXGus+move3);
                                }
                            }else if(mov3.charAt(1)=='D'){
                                if(coorXGus-move3 < 0){
                                    continue;
                                }else if(tablero[coorXGus-move3][coorYGus]== '#'){
                                    continue;
                                }else{
                                    tablero[coorXGus][coorYGus] ='_';
                                    tablero[coorXGus-move3][coorYGus] = 'G';
                                    setCoorXGus(coorXGus-move3);
                                }
                            }


                            mensaje = matriztoString(tablero);
                            
                            paso++;
                                }
                    break;
                case 3:
                    paso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el paso que desea:"));
                    String mov4 = instrucciones.get(paso);
                    int move4 = mov4.charAt(0)-'0';
                    
                    if(mov4.charAt(1) == 'R'){
                        if(coorYGus+move4 > tablero[0].length-1){
                            continue;
                        }else if(tablero[coorXGus][coorYGus+move4]== '#'){
                            continue;
                        }else{
                            tablero[coorXGus][coorYGus] ='_';
                            tablero[coorXGus][coorYGus+move4] ='G';
                            setCoorYGus(coorYGus+move4);
                        }
                    }else if(mov4.charAt(1)== 'L'){
                        if(coorYGus-move4 < 0){
                            continue;
                        }else if(tablero[coorXGus][coorYGus-move4]== '#'){
                            continue;
                        }else{
                            tablero[coorXGus][coorYGus] ='_';
                            tablero[coorXGus][coorYGus-move4] ='G';
                            setCoorYGus(coorYGus-move4);
                        }
                    }else if(mov4.charAt(1)=='U'){
                        if(coorXGus+move4 > tablero.length-1){
                            continue;
                        }else if(tablero[coorXGus+move4][coorYGus]== '#'){
                            continue;
                        }else{
                            tablero[coorXGus][coorYGus] ='_';
                            tablero[coorXGus+move4][coorYGus] ='G';
                            setCoorXGus(coorXGus+move4);
                        }
                    }else if(mov4.charAt(1)=='D'){
                        if(coorXGus-move4 < 0){
                            continue;
                        }else if(tablero[coorXGus-move4][coorYGus]== '#'){
                            continue;
                        }else{
                            tablero[coorXGus][coorYGus] ='_';
                            tablero[coorXGus-move4][coorYGus] = 'G';
                            setCoorXGus(coorXGus-move4);
                        }
                    }
                    
                    mensaje = matriztoString(tablero);
                    
                    break;
            }
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Mapa: \n"+mensaje+"\nMostrando paso: "+paso+"\n1.Ver siguiente paso. \n2. Ver paso anterior.\n3.Seleccionar paso.\n4.Volver al menu."));
        }
    }
    
    
    public void imprimirMatriz(char[][]temporal){
        for (int i = 0; i < temporal.length; i++) {
            for (int j = 0; j < temporal[i].length; j++) {
                System.out.print("["+temporal[i][j]+"]");
            }
            System.out.println("");
        }
    }
    
    public String matriztoString(char [][]matriz){
        String mensaje = "";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                mensaje += "["+matriz[i][j]+"]"+" "+"\t";
            }
            mensaje += "\n";
        }  
        return mensaje;
    }
    
    public String matriztoStringVict(char[][]matriz){
        String mensaje = "";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                mensaje += "["+matriz[i][j]+"]"+" "+"\t";
            }
            mensaje += "\n";
        }  
        mensaje += "\nLograste llegar a la manzana!";
        return mensaje;
    }
}
