/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hetmanproblem;

/**
 *
 * @author KonradD
 */
public class HetmanSolver {

    private int amount;
    private int[][] board;    
    //private int[][] solution;

    public HetmanSolver(int queensAmount) {
        amount = queensAmount;
        board = new int[queensAmount][queensAmount];
    }

    public void resolve() {
        for (int i = 0; i < amount; i++) {
            int[][] tab = new int[amount][amount];
            tab[0][i] = 1;
            resolve(tab, 0);
        }
    }

    private void resolve(int[][] tab, int nextX/*, int nextY*/) {
        if (nextX == amount - 1 /*&& nextY == amount*/) {
            for (int i = 0; i < amount; i++) {
                for (int j = 0; j < amount; j++) {
                    board[i][j] = tab[i][j];
                    System.out.print(board[i][j] + " ");
                }
                System.out.println("");
            }            
            //System.out.println("koniec");
            System.exit(0);
            return;
        }else {
            int nextXTemp = nextX;
            /*int nextYTemp = nextY;
            if (nextY + 1 == amount) {
                nextX++;
                nextY = 0;
            } else {*/
                //nextY++;
                nextX++;
            //}
        }
        for(int i = 0; i < amount; i++ ){
            if(checkCollision(tab, nextX, i)){
                int[][] newTab = copyArray(tab);
                newTab[nextX][i] = 1;
                resolve(newTab, nextX);
            }
        }
        
    }

    private boolean checkCollision(int[][] tab, int x, int y) {
        //1. sprawdzenie w poziomie
        //2. sprawdzenie w pionie
        for (int pos = 0; pos < amount; pos++) {
            //System.out.println(pos);
            if (tab[x][pos] == 1 || tab[pos][y] == 1) {                
                return false;
            }
        }
        
        //3,sprawdzenie po przekatnych
        for(int i = 0; i < amount; i++){
            if(x-i >= 0 && y-i >= 0 && tab[x-i][y-i] == 1){
                return false;
            }
            if(x+i < amount && y+i < amount && tab[x+i][y+i] == 1){
                return false;
            }
            if(x-i >= 0 && y+i < amount && tab[x-i][y+i] == 1){
                return false;
            }
            if(x+i < amount && y-i >= 0 && tab[x+i][y-i] == 1){
                return false;
            }
        }
        
        return true;
    }
    
    private int[][] copyArray(int[][] tab) {
        int[][] temp = new int[amount][amount];
        for (int i = 0; i < amount; i++) {
            for (int j = 0; j < amount; j++) {
                temp[i][j] = tab[i][j];
            }
        }
        return temp;
    }
}
