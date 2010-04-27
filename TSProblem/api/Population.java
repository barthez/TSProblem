/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TSProblem.api;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author bartek
 */
public class Population {

    LinkedList<Chromosom> pop = new LinkedList<Chromosom>();
    int size = 0;
    Random generator = new Random();

//    public Population(int size, Graph g) {
//    populacja powinna mieć też graf na podstawie którego bedzie oceniać chromosomy
    public Population(int size) {
        int nodesnumber = 20; //Powinnno sie to wyliczyć z grafu
        this.size = size;
        for( int i = 0; i< size; ++i) {
            pop.add( new Chromosom(nodesnumber));
            pop.get(i).create();
        }
    }

    public void emulate() throws Exception {
        LinkedList<Chromosom> newPop = new LinkedList<Chromosom>();

        for( int year = 0; year < 20000; ++year) {
            for( int i=0; i < this.size -1 ; ++i ) {
                for( int j = i+1; j < size; ++j) {
                    ChromosomPair childern = pop.get(i).crossover( pop.get(j) );
                    Chromosom ch1 = childern.first();
                    Chromosom ch2 = childern.second();
                    if ( getBoolean(70) ) {
                        ch1 = ch1.mutation() ;
                        ch2 = ch2.mutation() ;
                    }

                    //niepotrzebujemy identycznych osobników
                    //TODO: optymalizacja, bo zabiera mase czasu
                    if ( ! newPop.contains(ch1) ) newPop.add(ch1);
                    if ( ! newPop.contains(ch2) ) newPop.add(ch2);
                    
                }
            }

            //Sortuemy nasze chromosomy od najlepiej przystosowanego do naj mniej
            Collections.sort(newPop);
            
            System.out.println("Population:");
            for(int i = 0; i < this.size; ++i) {
                pop.set(i,newPop.get(i));
                System.out.println("\t" + pop.get(i));
            }

            System.out.println("Best: " + pop.getFirst() );
            System.out.println(" Fitness: " + pop.getFirst().fitness() );
        }

    }

    private boolean getBoolean(int percent) {
        return ( generator.nextInt(100) < percent );
    }



}
