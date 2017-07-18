/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hetmanproblem;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author KonradD
 */
public class tabuSearchMultiprocessing {

    private int processors;

    protected int size;
    protected int[] table;
    //protected int[] table = {382 ,479 ,478 ,270 ,237 ,119 ,422 ,496 ,319 ,144 ,96 ,171 ,166 ,10 ,488 ,14 ,164 ,294 ,445 ,134 ,413 ,104 ,180 ,196 ,107 ,231 ,371 ,334 ,367 ,174 ,354 ,122 ,184 ,466 ,117 ,230 ,148 ,188 ,309 ,205 ,79 ,308 ,215 ,432 ,53 ,392 ,485 ,90 ,155 ,209 ,377 ,245 ,288 ,163 ,364 ,41 ,437 ,149 ,21 ,224 ,235 ,360 ,412 ,249 ,328 ,349 ,40 ,498 ,74 ,50 ,379 ,332 ,293 ,73 ,261 ,471 ,302 ,268 ,70 ,497 ,331 ,97 ,438 ,348 ,389 ,66 ,154 ,456 ,123 ,467 ,77 ,330 ,418 ,32 ,67 ,317 ,136 ,140 ,258 ,228 ,416 ,481 ,49 ,477 ,61 ,34 ,352 ,443 ,403 ,417 ,433 ,424 ,105 ,15 ,131 ,116 ,195 ,95 ,307 ,301 ,460 ,267 ,36 ,455 ,102 ,126 ,55 ,408 ,222 ,373 ,351 ,335 ,490 ,470 ,439 ,43 ,342 ,493 ,266 ,113 ,242 ,35 ,81 ,176 ,162 ,409 ,22 ,59 ,306 ,431 ,283 ,427 ,425 ,430 ,159 ,446 ,247 ,193 ,454 ,287 ,475 ,276 ,175 ,227 ,208 ,211 ,46 ,322 ,108 ,458 ,453 ,292 ,86 ,401 ,130 ,337 ,23 ,296 ,421 ,259 ,495 ,192 ,500 ,272 ,378 ,185 ,355 ,200 ,39 ,221 ,384 ,223 ,472 ,368 ,340 ,30 ,17 ,226 ,274 ,343 ,216 ,219 ,381 ,280 ,318 ,203 ,127 ,58 ,329 ,99 ,238 ,94 ,380 ,179 ,407 ,344 ,289 ,428 ,33 ,135 ,462 ,304 ,147 ,299 ,110 ,305 ,350 ,457 ,161 ,449 ,327 ,183 ,406 ,190 ,240 ,285 ,173 ,398 ,62 ,232 ,290 ,256 ,333 ,264 ,271 ,461 ,210 ,244 ,366 ,9 ,57 ,169 ,248 ,45 ,341 ,191 ,220 ,324 ,69 ,18 ,27 ,64 ,103 ,92 ,491 ,484 ,26 ,243 ,158 ,202 ,474 ,250 ,152 ,143 ,8 ,476 ,7 ,19 ,278 ,356 ,353 ,316 ,124 ,376 ,482 ,402 ,132 ,82 ,358 ,217 ,186 ,494 ,68 ,51 ,363 ,194 ,291 ,106 ,394 ,326 ,448 ,138 ,115 ,3 ,197 ,411 ,128 ,346 ,393 ,463 ,388 ,31 ,168 ,442 ,71 ,372 ,440 ,279 ,400 ,204 ,118 ,241 ,114 ,391 ,464 ,339 ,48 ,246 ,452 ,323 ,239 ,419 ,397 ,85 ,361 ,63 ,42 ,199 ,139 ,157 ,65 ,11 ,260 ,414 ,405 ,25 ,320 ,257 ,198 ,120 ,473 ,37 ,5 ,365 ,47 ,98 ,311 ,415 ,78 ,338 ,207 ,84 ,436 ,451 ,321 ,263 ,395 ,465 ,370 ,434 ,229 ,480 ,236 ,295 ,252 ,265 ,426 ,262 ,386 ,212 ,83 ,4 ,153 ,469 ,44 ,151 ,404 ,325 ,450 ,177 ,410 ,206 ,347 ,447 ,286 ,20 ,303 ,89 ,38 ,218 ,145 ,76 ,269 ,16 ,385 ,60 ,313 ,315 ,387 ,6 ,72 ,109 ,101 ,314 ,182 ,282 ,233 ,189 ,423 ,396 ,251 ,253 ,178 ,54 ,160 ,275 ,187 ,141 ,312 ,75 ,489 ,297 ,111 ,336 ,214 ,234 ,499 ,420 ,255 ,359 ,146 ,2 ,137 ,298 ,125 ,483 ,52 ,284 ,362 ,29 ,486 ,225 ,201 ,435 ,459 ,181 ,12 ,167 ,93 ,281 ,374 ,441 ,300 ,429 ,142 ,165 ,56 ,468 ,80 ,254 ,213 ,13 ,375 ,345 ,357 ,369 ,172 ,121 ,310 ,156 ,150 ,112 ,129 ,100 ,399 ,492 ,91 ,133 ,444 ,170 ,24 ,277 ,273 ,390 ,28 ,487 ,1 ,88 ,87 ,383};    
    protected int[][] tabuStructure;

    public tabuSearchMultiprocessing(int size) {
        processors = Runtime.getRuntime().availableProcessors();
        this.size = size;
        createRandomTable(size);
        //print();
        tabuStructure = new int[table.length][table.length];
    }

    int loopAm;

    public void findSolution() throws InterruptedException {
        while (true) {
            //System.out.println("loop: " + loopAm);
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
            //predictValues();
            loopAm++;
            decrementTabu();
            generateCandidatesMultithread();
            tabuStructure[col1][col2] = 12;
            temp = table[col1];
            table[col1] = table[col2];
            table[col2] = temp;
            //collisionAmount(table);            
            /*System.out.print("zmiana: " + (col1 + 1) + " z " + (col2 + 1) + " ");
            //isValueInChange();
            System.out.print("po " + loopAm + " iteracji: ");
            for (int i = 0; i < table.length; i++) {
                System.out.print(table[i] + " ");
            }
            System.out.println("");*/
        }
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

    public void generateCandidatesMultithread() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        int diff = (size - 0) / processors;
        int d = 0;
        colAm = table.length;
        for (int i = 0; i < processors - 1; i++) {
            /*Thread th = new Thread(this);
            th.start();
            Thread thn = new Thread(new MyRun(i));
            thn.start();*/
            //Thread th = new Thread(new Generate(this, d, d += diff, i));
            Thread th;
            if (i == 0) {
                th = new Thread(new Generate(this, (size / processors) * i, ((size / processors) * (i + 1)), i));
            } else {
                th = new Thread(new Generate(this, (size / processors) * i + 1, ((size / processors) * (i + 1)), i));
            }
            threads.add(th);
            th.start();
        }
        Thread th = new Thread(new Generate(this, (size / processors) * (processors - 1) + 1, table.length, processors));
        threads.add(th);
        th.start();
        for (Thread thread : threads) {
            thread.join();
        }
    }

    /*public void generateCandidatesMultithread() throws InterruptedException {
        colAm = table.length;
        Thread th = new Thread(new Generate(this, 0, table.length, processors));
        //threads.add(th);
        th.start();
        th.join();
    }*/

    public int collisionAmount(int[] table) {
        //collisions.clear();
        int amount = 0;
        for (int i = 1; i <= table.length; i++) {
            for (int j = i + 1, shift = 1; j <= table.length; j++, shift++) {
                if (table[i - 1] + shift == table[j - 1] || table[i - 1] - shift == table[j - 1]) {
                    //System.out.println((i) + " oraz " + (j));
                    //int[] a = {i, j};
                    //collisions.add(a);
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

    private int col1, col2, temp;
    private int colAm;
    private final Object lock = new Object();

    protected int getColumnAmount() {
        synchronized (lock) {
            //System.out.println("pobranie wartosci");
            return colAm;
        }
    }

    protected /*synchronized*/ void setValues(int col1, int col2, int colAm) {
        synchronized (lock) {
            this.col1 = col1;
            this.col2 = col2;
            this.colAm = colAm;
            //System.out.println("zmiana wartosci");
        }
    }

    //wygenerowanie kandydata do zmiany
    //https://books.google.pl/books?id=hFPTBwAAQBAJ&pg=PA65&lpg=PA65&dq=sequential+fan+candidate+list&source=bl&ots=FeOCEc_3B4&sig=z6IyHKl6A9DZo8tCka5FyDDAx3o&hl=pl&sa=X&ved=0ahUKEwjU9sbpxM_NAhVFxxQKHSQBBQ4Q6AEIJDAB#v=onepage&q=sequential%20fan%20candidate%20list&f=false
    /*public void generateCandidates() {
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
    }*/
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

    public void print() {
        for (int i = 0; i < table.length; i++) {
            System.out.print(table[i] + " ");
        }
    }
}

class MyRun implements Runnable {

    private int id;

    public MyRun(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Watek " + id);
    }
}

class Generate implements Runnable {

    private int lowRange, UpRange, id, temp;
    private tabuSearchMultiprocessing tSM;

    public Generate(tabuSearchMultiprocessing tsM, int lowRange, int UpRange, int id) {
        this.tSM = tsM;
        this.lowRange = lowRange;
        this.UpRange = UpRange;
        this.id = id;
    }

    //trick: przechodzenie j tylko 10% wielkosci tablicy zwieksza szybkosc dzialania algorytmu o 50% -> for (int j = i + 1; j < tSM.table.length && j < i + 30; j++) dla wilekosci 300 wynik 15s zamiast 30
    @Override
    public void run() {
        //System.out.println(id + " watek od " + lowRange + " do " + UpRange);
        for (int i = lowRange; i < UpRange; i++) {
            //for (int i = getFirstPos(); i < table.length; i++) {
            for (int j = i + 1; j < tSM.table.length; j++) {
            //for (int j = i + 1; j < tSM.table.length && j < i + tSM.table.length / 10; j++) {
                int[] tab = tSM.table.clone();

                temp = tab[i];
                tab[i] = tab[j];
                tab[j] = temp;
                //mozliwy problem z odczytem colAm z klasy nadrzednej
                if ((temp = collisionAmount(tab)) < tSM.getColumnAmount() && tSM.tabuStructure[i][j] == 0) {
                    tSM.setValues(i, j, temp);
                }
            }
        }
    }

    public int collisionAmount(int[] table) {
        //collisions.clear();
        int amount = 0;
        for (int i = 1; i <= table.length; i++) {
            for (int j = i + 1, shift = 1; j <= table.length; j++, shift++) {
                if (table[i - 1] + shift == table[j - 1] || table[i - 1] - shift == table[j - 1]) {
                    //System.out.println((i) + " oraz " + (j));
                    //int[] a = {i, j};
                    //collisions.add(a);
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
}
