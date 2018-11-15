package project.nfaadventures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a state as an object with references to the next states following the transition function.
 * @author Jeroen Peeters 1643057
 */
public class State
{
    private String mLabel;
    private Map<String, List<State>> mNextStates; //letter => transition(this, letter)
    private boolean mIsAcceptState;

    /**
     * Constructor for a state. (add next states later)
     * @param mLabel the label of this state
     * @param mIsAcceptState whether this is an accept state or not
     */
    public State(String mLabel, boolean mIsAcceptState)
    {
        this.mLabel = mLabel;
        this.mIsAcceptState = mIsAcceptState;
    }

    /**
     * Add a state you can go to via a transition.
     * @param transition the transition
     * @param state the state it goes to
     */
    public void AddNextState(String transition, State state)
    {

    }

    /**
     * A way to get all the states you can go to from this one. (no duplicates)
     * @return a list with all these states
     */
    public List<State> GetAllNextStates()
    {
        List<State> allNextStates = new ArrayList<>();

        //TODO: get all next states.

        return allNextStates;
    }

    /**
     * A way to get all the states you can go to using transitions marked with 'transition'.
     * @param transition name of the transition
     * @return a list with all these states
     */
    public List<State> GetNextStatesFor(String transition)
    {
        List<State> nextStates = new ArrayList<>();

        //TODO: get all next states from specified transition.

        return nextStates;
    }
}
