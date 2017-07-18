/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hetmanproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author KonradD
 */
//http://www.leca.ufrn.br/~estefane/metaheuristicas/tsguide.pdf
//https://books.google.pl/books?id=ZHRTmJfyeVYC&pg=PA111&lpg=PA111&dq=tabu+n+queen+problem&source=bl&ots=z9ls-HKNib&sig=-Km_V_NFoyq4E6hsrNNWI4y2z7s&hl=pl&sa=X&ved=0ahUKEwitgZ7Bxc3NAhVLWBQKHSyGDdoQ6AEIdDAO#v=onepage&q&f=false
//http://www.zemris.fer.hr/~golub/clanci/iti2007.pdf
public class tabuSearch {

    //private int[] table = {4, 5, 3, 6, 7, 1, 2}; //przed iteracjami
    //private int[] table = {2, 5, 3, 6, 7, 1, 4}; // 1 iteracja
    //private int[] table = {2, 6, 3, 5, 7, 1, 4}; // 2 iteracja
    //private int[] table = {3, 6, 2, 5, 7, 1, 4}; // 3 iteracja
    //private int[] table = {3, 6, 2, 5, 4, 1, 7}; // 4 iteracja
    //private int[] table = {3, 6, 2, 7, 4, 1, 5}; // 5 iteracja
    //private int[] table = {2, 6, 3, 7, 4, 1, 5}; // 6 iteracja
    private int[] table;
    private int[][] tabuStructure;

    public tabuSearch(/*int[] tab*/int size) {
        //table = new int[7];
        //table = tab;
        table = new int[size];
        createRandomTable(size);
        tabuStructure = new int[table.length][table.length];
    }

    int loopAm;
    public void findSolution() {
        while (true) {
            if (collisionAmount(table) == 0) {
                System.out.println("znaleziono roz " + loopAm);
                return;
            }
            loopAm++;
            decrementTabu();
            generateCandidates();
            tabuStructure[col1][col2] = 12;
            temp = table[col1];
            table[col1] = table[col2];
            table[col2] = temp;
        }
    }

    //wyliczenie liczby kolizji dla danej tablicy
    public int collisionAmount(int[] table) {
        int amount = 0;
        for (int i = 1; i <= table.length; i++) {
            for (int j = i + 1, shift = 1; j <= table.length; j++, shift++) {
                if (table[i - 1] + shift == table[j - 1] || table[i - 1] - shift == table[j - 1]) {
                    //System.out.println((i) + " oraz " + (j));
                    amount++;
                }
            }
        }
        return amount;
    }

    int col1, col2, colAm, temp;

    //wygenerowanie kandydata do zmiany
    public void generateCandidates() {
        colAm = table.length;
        for (int i = 0; i < table.length; i++) {
            for (int j = i + 1; j < table.length; j++) {
                int[] tab = table.clone();
                temp = tab[i];
                tab[i] = tab[j];
                tab[j] = temp;
                //temp = collisionAmount(tab);
                if ((temp = collisionAmount(tab)) < colAm && tabuStructure[i][j] == 0) {
                //if (temp < colAm && tabuStructure[i][j] == 0) {
                    col1 = i;
                    col2 = j;
                    colAm = temp;
                }
            }
        }
        // System.out.println("najlepszy kandydat: " + col1 + " " + col2);
        //System.out.println("najlepszy kandydat: " + (col1 + 1) + " " + (col2 + 1));
    }

    private void decrementTabu() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (tabuStructure[i][j] != 0) {
                    tabuStructure[i][j]--;
                }
            }
        }
    }
    
    public void print(){
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i] + " ");
        }
    }
    
    public void createRandomTable(int size){
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for(int i = 1; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        /*while(list.size() > 0) {
            int index = rand.nextInt(list.size());
            
            //System.out.println("Selected: "+list.remove(index));
        }*/
        for(int i = 0; i < table.length; i++){
            int index = rand.nextInt(list.size());
            table[i] = list.remove(index);            
        }
        
        for(int i = 0; i < table.length; i++){
            System.out.print(table[i] + " ");
        }
        System.out.println("");
    }

}
