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
    private Set<String> mAlphabet;

    /**
     * Getter for mAlphabet
     * @return mAlphabet
     */
    public Set<String> GetAlphabet()
    {
        return mAlphabet;
    }


    /**
     * Getter for mStartState
     * @return mStartState
     */
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

    /**
     * Intersects two automatons
     * @param other the second automaton
     * @return this intersection other
     */
    public void intersection(Automaton other)
    {
        Set<String> newAlphabet = new HashSet<>();
        newAlphabet.addAll(GetAlphabet());
        newAlphabet.retainAll(other.GetAlphabet());

        mAlphabet = newAlphabet;

        State intersection = State.Intersect(GetStartState(), other.GetStartState());
        mStartState = intersection;

        mLengthMap = new LengthMap(mStartState);
    }
}
