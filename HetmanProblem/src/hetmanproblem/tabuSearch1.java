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
// sposob: nie przeszukiwanie wszystkeigo by znalezc najlepszego kandydata do zmiany tylko "przewidywanie" go przez tablice kolizji: dziala najprawdopodobniej szybciej, ale wiecej iteracji
public class tabuSearch1 {

    private int[] table = {4, 5, 3, 6, 7, 1, 2}; //przed iteracjami
    //private int[] table = {2, 5, 3, 6, 7, 1, 4}; // 1 iteracja
    //private int[] table = {2, 6, 3, 5, 7, 1, 4}; // 2 iteracja
    //private int[] table = {3, 6, 2, 5, 7, 1, 4}; // 3 iteracja
    //private int[] table = {3, 6, 2, 5, 4, 1, 7}; // 4 iteracja
    //private int[] table = {3, 6, 2, 7, 4, 1, 5}; // 5 iteracja
    //private int[] table = {2, 6, 3, 7, 4, 1, 5}; // 6 iteracja
    //private int[] table;
    private int[][] tabuStructure;

    public tabuSearch1(/*int[] tab*/int size) {
        //table = new int[7];
        //table = tab;
        //table = new int[size];
        createRandomTable(size);
        tabuStructure = new int[table.length][table.length];
    }

    public void changeTab(int[] tab) {
        table = tab;
    }

    int loopAm;

    public void findSolution() {
        while (true) {
            if (collisionAmount(table) == 0) {
                System.out.println("znaleziono roz " + loopAm);
                return;
            }
            /*System.out.print("kolizje: ");
            //for (int i = 0; collisions.size() > 0;) {
            for (int i = 0; i < collisions.size(); i++) {
                //System.out.println("hm? " + collisions.get(i)[0] + " " + collisions.remove(i)[1]);
                System.out.print("(" + collisions.get(i)[0] + ", " + collisions.get(i)[1] + ") ");
            }*/
            predictValues();
            loopAm++;
            decrementTabu();
            //generateCandidates();
            generateCandidates2();
            tabuStructure[col1][col2] = 12;
            temp = table[col1];
            table[col1] = table[col2];
            table[col2] = temp;            
            //collisionAmount(table);
            
            /*System.out.println("zmiana: " + (col1 + 1) + " z " + (col2 + 1));
            System.out.print("po " + loopAm + " iteracji: ");
            for (int i = 0; i < table.length; i++) {
                System.out.print(table[i] + " ");
            }
            System.out.println("");*/
        }
    }
    
    int biggest;
    //tymczasowo do przewidywania wartosci
    private void predictValues(){
        int[] tab = new int[table.length];
        //int biggest = 0;
        for (int i = 0; i < collisions.size(); i++) {
            tab[collisions.get(i)[0]-1] += collisions.get(i)[0]-1;
        }
        for (int i = 0; i < tab.length; i++) {
            if(tab[i] > biggest){
                biggest = i;
            }
        }
        //System.out.println("najwieksza lewa: " + (biggest+1));
    }

    ArrayList<int[]> collisions = new ArrayList<>();

    //wyliczenie liczby kolizji dla danej tablicy
    public int collisionAmount(int[] table) {
        collisions.clear();
        int amount = 0;
        for (int i = 1; i <= table.length; i++) {
            for (int j = i + 1, shift = 1; j <= table.length; j++, shift++) {
                if (table[i - 1] + shift == table[j - 1] || table[i - 1] - shift == table[j - 1]) {
                    //System.out.println((i) + " oraz " + (j));
                    int[] a = {i, j};
                    collisions.add(a);
                    amount++;
                }
            }
        }
        /*System.out.print("kolizje: ");
        for (int i = 0; collisions.size() > 0;) {
            //System.out.println("hm? " + collisions.get(i)[0] + " " + collisions.remove(i)[1]);
            System.out.print("("+collisions.get(i)[0] + ", " + collisions.remove(i)[1] + ") ");
        }*/
        return amount;
    }

    int col1, col2, colAm, temp;

    //wygenerowanie kandydata do zmiany
    //https://books.google.pl/books?id=hFPTBwAAQBAJ&pg=PA65&lpg=PA65&dq=sequential+fan+candidate+list&source=bl&ots=FeOCEc_3B4&sig=z6IyHKl6A9DZo8tCka5FyDDAx3o&hl=pl&sa=X&ved=0ahUKEwjU9sbpxM_NAhVFxxQKHSQBBQ4Q6AEIJDAB#v=onepage&q=sequential%20fan%20candidate%20list&f=false
    public void generateCandidates() {
        colAm = table.length;
        for (int i = 0; i < table.length; i++) {
            //for (int i = getFirstPos(); i < table.length; i++) {
            for (int j = i + 1; j < table.length; j++) {
                int[] tab = table.clone();
                temp = tab[i];
                tab[i] = tab[j];
                tab[j] = temp;
                if ((temp = collisionAmount(tab)) < colAm && tabuStructure[i][j] == 0) {
                    col1 = i;
                    col2 = j;
                    colAm = temp;
                }
            }
        }
        //System.out.println("zmiana: " + (col1 + 1) + " z " + (col2 + 1));
        // System.out.println("najlepszy kandydat: " + col1 + " " + col2);
        //System.out.println("najlepszy kandydat: " + (col1 + 1) + " " + (col2 + 1));
    }
    
    public void generateCandidates2() {
        colAm = table.length;
        int i = biggest;
        //for (int i = 0; i < table.length; i++) {
            //for (int i = getFirstPos(); i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if(i == j){
                    continue;
                }
                int[] tab = table.clone();
                temp = tab[i];
                tab[i] = tab[j];
                tab[j] = temp;
                if ((temp = collisionAmount(tab)) < colAm && tabuStructure[i][j] == 0) {
                    col1 = i;
                    col2 = j;
                    colAm = temp;
                }
            }
        //}
        //System.out.println("zmiana: " + (col1 + 1) + " z " + (col2 + 1));
        // System.out.println("najlepszy kandydat: " + col1 + " " + col2);
        //System.out.println("najlepszy kandydat: " + (col1 + 1) + " " + (col2 + 1));
    }

    int firstPos, firstPosPast, flag, sh;

    //pozycja pierwszej kolumny do zmiany
    public int getFirstPos() {
        firstPosPast = firstPos;
        //pobranie wartosci z listy collisions
        //sprawdzenie czy pod ta wartoscia nie ma liczb w tabustructure, jesli nie mozna slac
        for (int i = 0 + sh; i < collisions.size(); i++) {
            int temp = collisions.get(i)[0];
            if (temp == firstPos) {
                continue;
            }
            flag = 1;
            for (int j = 0; j < tabuStructure.length; j++) {
                if (tabuStructure[j][temp - 1] != 0 || tabuStructure[temp - 1][j] != 0) {
                    flag = 0;
                }
            }
            if (flag == 1 && firstPosPast != temp) {
                if (sh != 0) {
                    sh--;
                }
                return firstPos = temp;
            }
        }
        sh += 2;
        for (int i = 0; i < collisions.size(); i++) {
            int temp = collisions.get(i)[1];
            if (temp == firstPos) {
                continue;
            }
            flag = 1;
            for (int j = 0; j < tabuStructure.length; j++) {
                if (tabuStructure[j][temp - 1] != 0 || tabuStructure[temp - 1][j] != 0) {
                    flag = 0;
                }
            }
            if (flag == 1 && firstPosPast != temp) {
                if (sh != 0) {
                    sh--;
                }
                return firstPos = temp;
            }
        }
        return firstPos;
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

    public void print() {
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i] + " ");
        }
    }

    public void createRandomTable(int size) {
        //temp
        table = new int[size];
        //end temp
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        /*while(list.size() > 0) {
            int index = rand.nextInt(list.size());
            
            //System.out.println("Selected: "+list.remove(index));
        }*/
        for (int i = 0; i < table.length; i++) {
            int index = rand.nextInt(list.size());
            table[i] = list.remove(index);
        }

        System.out.print("poczatkowo: ");
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i] + " ");
        }
        System.out.println("");
    }

    public void tabClone() {
        long start = System.currentTimeMillis();
        int[] tab = null;
        for (int i = 0; i < table.length; i++) {
            for (int j = i + 1; j < table.length; j++) {
                tab = table.clone();
                collisionAmount(tab);
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("czas kopiowania: " + (stop - start));
        int temp = tab[0];
        tab[0] = tab[499];
        tab[499] = temp;
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i] + " ");
        }
        System.out.println("");
        System.out.println("kopia:");
        for (int i = 0; i < table.length; i++) {
            System.out.print(tab[i] + " ");
        }
    }

}
