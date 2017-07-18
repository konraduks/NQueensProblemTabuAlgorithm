/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hetmanproblem;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KonradD
 */
public class HetmanProblem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        //HetmanSolver hs = new HetmanSolver(50);
        //hs.resolve();

        // 50 -> ok. 1s
        // 100 -> ok. 34s
        //systematicAndGreedySearchAlgorithm sag = new systematicAndGreedySearchAlgorithm(50);
        //sag.firstStep();
        //sag.print();
        //System.out.println(sag.collisionAmount(2, 6));
        //System.out.println(sag.isNeedChange(0));
        /*for (int i = 0; i < 10000000; i++) {
            sag.findSolution();
        }*/
        //sag.print();
        //for (int i = 0; i < 10000; i++) {
        // sag.findSolution();
        //}
        //sag.print();
        //sag.printCollision();
        //sag.solve();
        //sag.print();
        //sag.printCollision();
        //System.err.println("ilosc iteracji: " + sag.loops);
        //int[] table = {4, 5, 3, 6, 7, 1, 2}; //przed iteracjami
        //int[] table = {2, 5, 3, 6, 7, 1, 4}; // 1 iteracja
        //int[] table = {2, 6, 3, 5, 7, 1, 4}; // 2 iteracja
        //int[] table = {3, 6, 2, 5, 7, 1, 4}; // 3 iteracja
        //int[] table = {3, 6, 2, 5, 4, 1, 7}; // 4 iteracja
        //int[] table = {3, 6, 2, 7, 4, 1, 5}; // 5 iteracja
        //int[] table = {2, 6, 3, 7, 4, 1, 5}; // 6 iteracja
        //int[] table = {3, 6, 2, 7, 4, 1, 5}; // 5 iteracja
        //int[] table = new int[10];
        /*for(int i = 0; i < table.length; i++){
            table[i] = i + 1;
        }*/
        //int[] table = {3, 6, 2, 7, 4, 1, 5, 8, 9, 10, 11, 14, 15, 12, 13, };
        /*Random r = new Random();
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + " fl");
            int next = 1 + r.nextInt(table.length);
            for (int j = 0; j < table.length; j++) {
                if (table[j] == next) {
                    //System.out.println(i);
                    i--;
                    System.out.println(i + " end");
                    break;
                }
            }
            System.out.println(i + " " + next);
            table[i] = next;
        }
        for(int i = 0; i < table.length; i++){
            System.out.print(table[i] + " ");
        }
        System.out.println("");*/
        //tabuSearch ts = new tabuSearch(table);
        //tabuSearch1 ts = new tabuSearch1(100);
        //tabuSearch2 ts = new tabuSearch2(500);
        
        /*int[] table = {4, 5, 3, 6, 7, 1, 2}; //przed iteracjami
        int[] table1 = {2, 5, 3, 6, 7, 1, 4}; // 1 iteracja
        int[] table2 = {2, 6, 3, 5, 7, 1, 4}; // 2 iteracja
        int[] table3 = {3, 6, 2, 5, 7, 1, 4}; // 3 iteracja
        int[] table4 = {3, 6, 2, 5, 4, 1, 7}; // 4 iteracja
        int[] table5 = {3, 6, 2, 7, 4, 1, 5}; // 5 iteracja         
        System.out.println(ts.collisionAmount(table));
        System.out.println("x: " + ts.getFirstPos());
        ts.changeTab(table1);
        ts.collisionAmount(table1);
        System.out.println("x: " + ts.getFirstPos());
        ts.changeTab(table2);
        ts.collisionAmount(table2);
        System.out.println("x: " + ts.getFirstPos());
        ts.changeTab(table3);
        ts.collisionAmount(table3);
        System.out.println("x: " + ts.getFirstPos());
        ts.changeTab(table4);
        ts.collisionAmount(table4);
        System.out.println("x: " + ts.getFirstPos());
        ts.changeTab(table5);
        ts.collisionAmount(table5);
        System.out.println("x: " + ts.getFirstPos());*/
        
        //ts = new tabuSearch1(10);
        //ts.findSolution();
        //ts.createRandomTable(500);
        //ts.tabClone();
        //ts.generateCandidates();
        //ts.findSolution();
        //ts.print();
        
//        tabuSearch2 ts2 = new tabuSearch2(200);        
//        ts2.findSolution();
//        ts2.print();
        
        tabuSearchMultiprocessing tsm = new tabuSearchMultiprocessing(300);
        tsm.findSolution();
        tsm.print();
        
        
        
        /*tabuSearchMultiprocessing tsm = new tabuSearchMultiprocessing(200);
        try {
            tsm.findSolution();
        } catch (InterruptedException ex) {
            Logger.getLogger(HetmanProblem.class.getName()).log(Level.SEVERE, null, ex);
        }        
        tsm.print();*/
        
    }

}
