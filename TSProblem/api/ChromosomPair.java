/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TSProblem.api;

/**
 *
 * @author bartek
 */
public class ChromosomPair {

    private Chromosom first;
    private Chromosom second;

    public ChromosomPair( Chromosom f, Chromosom s ) {
        first = f;
        second = s;
    }

    public Chromosom first() {
        return first;
    }

    public Chromosom second() {
        return second;
    }

}
