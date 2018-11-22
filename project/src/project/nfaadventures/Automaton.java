package project.nfaadventures;

import java.util.HashSet;
import java.util.Set;

/**
 * Models a NFA Automaton
 * @author Pieter-Jan Lavaerts 1746060
 */
public class Automaton {
    private State mStartState;
    private LengthMap mLengthMap;
    private Set<String> mAlphabet;

    /**
     * Constructor of automaton
     * @param startState the startstate of the automaton
     */
    public Automaton(State startState, Set<String> alphabet)
    {
        mStartState = startState;
        mLengthMap = new LengthMap(startState);
        mAlphabet = alphabet;
    }

    /**
     * Calculates intersection of this and a given automaton.
     * @param aut given automaton
     * @return new automaton that's the intersection of this and aut
     */
    public Automaton intersection(Automaton aut)
    {
        State bogusStartState = new State("Bogus", false);
        Automaton intersection = new Automaton(bogusStartState, new HashSet<>());

        //TODO: Write the intersection method.

        return intersection;
    }

    /**
     * Returns a shortest string that's either in the language or not in the language.
     * @param accept true if the the string must be in the language false otherwise
     * @return the shortest string
     */
    public String getShortestExample(boolean accept)
    {
        String shortestExample = new String();

        //TODO: Write the getShortestExample method.

        return shortestExample;
    }
}
