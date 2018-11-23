package project.nfaadventures;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Models a NFA Automaton
 * @author Pieter-Jan Lavaerts 1746060
 */
public class Automaton {

    private State mStartState;
    private LengthMap mLengthMap;

    public Set<String> GetAlphabet()
    {
        return mAlphabet;
    }

    private Set<String> mAlphabet;

    public State GetStartState()
    {
        return mStartState;
    }

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
        List<State> ShortestPath;

        if (accept) {
            ShortestPath = mLengthMap.GetShortestPathAccept();
        }
        else {
            ShortestPath = mLengthMap.GetShortestPathFail();
        }

        if (ShortestPath.size() == 0)
        {
            //There is no path
            return null;
        }

        String result = new String();
        for (int i = 0; i < ShortestPath.size()-1; i++)
        {
            String transition = ShortestPath.get(i).GetTransitionTo(ShortestPath.get(i+1));
            if (!transition.equals("$"))
            {
                result += transition;
            }
        }

        return result;
    }

    public Automaton Merge(Automaton other)
    {
        Set<String> newAlphabet = new HashSet<>();
        newAlphabet.addAll(GetAlphabet());
        newAlphabet.addAll(other.GetAlphabet());
        return new Automaton(GetStartState().Merge(newAlphabet, other.GetStartState()), newAlphabet);
    }
}
