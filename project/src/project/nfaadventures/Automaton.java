package project.nfaadventures;

/**
 * Models a NFA Automaton
 * @author Pieter-Jan Lavaerts 1746060
 */
public class Automaton {
    private State mStartState;
    private LengthMap mLengthMap;

    /**
     * Constructor of automaton
     * @param startState the startstate of the automaton
     */
    public Automaton(State startState)
    {
        mStartState = startState;
        mLengthMap = new LengthMap(startState);
    }

    /**
     * Calculates intersection of this and a given automaton.
     * @param aut given automaton
     * @return new automaton that's the intersection of this and aut
     */
    public Automaton intersection(Automaton aut)
    {
        //TODO: Write the intersection method.
    }

    /**
     * Returns a shortest string that's either in the language or not in the language.
     * @param accept true if the the string must be in the language false otherwise
     * @return the shortest string
     */
    public String getShortestExample(boolean accept)
    {
        //TODO: Write the getShortestExample method.
    }
}
