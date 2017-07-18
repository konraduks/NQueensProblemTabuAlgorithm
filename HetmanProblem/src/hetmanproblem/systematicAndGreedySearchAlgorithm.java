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
//https://sites.google.com/site/nqueensolver/home/algorithm-results
//minimum 31 hetmanow!
public class systematicAndGreedySearchAlgorithm {

    private int[] currentPos; //pozycja kazdego hetmana
    private int[][] board;
    private int size;

    public systematicAndGreedySearchAlgorithm(int size) {
        this.size = size;        
    }

    //inicjalizacja w tym samym rzedzie
    public void firstStep(int i) {
        board = new int[size][size];
        currentPos = new int[size];
        for (int j = 0; j < size; j++) {
            board[i][j] = 1;
            currentPos[j] = i;
        }
    }

    //czy potrzebna zmiana polozenia danego hetmana
    public boolean isNeedChange(int x) {
        int flag = collisionAmount(x, currentPos[x]);
        if (flag == 0) {
            return false;
        } else {
            return true;
        }
    }
    int nextBestPos, nextBestPosVal, temp;

    public void solve() {
        for (int i = 0; i < size; i++) {
            firstStep(i);
            if (findSolution()) {
                print();
                return;
            }
        }
    }

    public int loops;
    //1.sprawdzenie,czy potrzebna zmiana dla danego hetmana
    public boolean findSolution() {
        int changeAm/*, loops*/ = 0;
        loops = 0;
        while (true) {
            changeAm = 0;
            for (int i = 0; i < size; i++) {
                if (isNeedChange(i)) {
                    changeAm++;
                    nextBestPos = currentPos[i];
                    nextBestPosVal = collisionAmount(nextBestPos, i);
                    //idziemy w dol
                    for (int j = 0; j < size; j++) {
                        if (nextBestPosVal >= (temp = collisionAmount(j, i))) {
                            nextBestPos = j;
                            nextBestPosVal = temp;
                        }
                    }
                    //zmiana pozycji w board i curPos
                    /*board[i][currentPos[i]] = 0;
                currentPos[i] = nextBestPos;
                board[i][currentPos[i]] = 1;*/
                    board[currentPos[i]][i] = 0;
                    currentPos[i] = nextBestPos;
                    board[currentPos[i]][i] = 1;
                }
            }
            if (changeAm == 0) {                
                System.out.println("ilosc iteracji: " + loops);
                return true;
            } else {
                //System.out.println(changeAm);
                loops++;
                //if (loops > size * size) {
                if (loops > size) {
                    return false;
                }
            }
        }
    }

    /*
    int nextBestPos, nextBestPosVal, temp;
    //1.sprawdzenie,czy potrzebna zmiana dla danego hetmana
    public void findSolution(){
        for(int i = 0; i < size; i++){
            if(isNeedChange(i)){
                nextBestPos = currentPos[i];
                nextBestPosVal = collisionAmount(i, nextBestPos);
                //idziemy w dol
                for(int j = 0; j < size; j++){
                    if(nextBestPosVal > (temp = collisionAmount(i, j))){
                        nextBestPos = j;
                        nextBestPosVal = temp;
                    }
                }
                //zmiana pozycji w board i curPos
                board[i][currentPos[i]] = 0;
                currentPos[i] = nextBestPos;
                board[i][currentPos[i]] = 1;
            }
        }
    }
     */
    public int collisionAmount(int x) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            //1. w prawo
            for (int l = x + 1; l < size; l++) {
                if (board[0][0] == 1);
            }
            //2. w lewo
            for (int l = x - 1; l >= 0; l--) {

            }
        }
        return sum;
    }

    public int collisionAmount(int x, int y) {
        int sum = 0;
        //w poziomie
        for (int i = 0; i < size; i++) {
            if (board[x][i] == 1 && i != y) {
                sum++;
            }
        }
        //System.out.println("dotychczas: " + sum);
        // po przekatnych
        for (int i = 1; i < size; i++) {
            if (x - i >= 0 && y - i >= 0 && board[x - i][y - i] == 1) {
                sum++;
            }
            if (x + i < size && y + i < size && board[x + i][y + i] == 1) {
                sum++;
            }
            if (x - i >= 0 && y + i < size && board[x - i][y + i] == 1) {
                sum++;
            }
            if (x + i < size && y - i >= 0 && board[x + i][y - i] == 1) {
                sum++;
            }
        }
        return sum;
    }

    public void printCollision() {
        System.out.println("");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(collisionAmount(i, j) + " ");
            }
            System.out.println("");
        }
    }

    public void print() {
        System.out.println("");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
