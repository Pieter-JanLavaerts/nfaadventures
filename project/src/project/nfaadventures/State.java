package project.nfaadventures;

import java.util.*;

/**
 * Represents a state as an object with references to the next states following the transition function.
 * @author Jeroen Peeters 1643057
 */
public class State implements Comparable<State>
{
    private String mLabel;
    private Map<String, List<State>> mNextStates; //letter => transition(this, letter)
    private boolean mIsAcceptState;

    @Override
    public int compareTo(State other)
    {
        return other.GetLabel().compareTo(this.GetLabel());
    }

    /**
     * Constructor for a state. (add next states later)
     * @param mLabel the label of this state
     * @param mIsAcceptState whether this is an accept state or not
     */
    public State(String mLabel, boolean mIsAcceptState)
    {
        this.mLabel = mLabel;
        this.mIsAcceptState = mIsAcceptState;

        mNextStates = new TreeMap<>();
    }

    /**
     * Add a state you can go to via a transition.
     * @param transition the transition
     * @param state the state it goes to
     */
    public void AddNextState(String transition, State state)
    {
        //Add to the list of states for transition as its key
        //Does Key exist, if not make it.
        if (! mNextStates.keySet().contains(transition))
        {
            mNextStates.put(transition, new ArrayList<>());
        }

        //Add state to the list //TODO: does get work or do we need to replace with a copy.add(state)
        mNextStates.get(transition).add(state);
    }

    /**
     * A way to get all the states you can go to from this one. (no duplicates)
     * @return a list with all these states
     */
    public List<State> GetAllNextStates()
    {
        List<State> allNextStates = new ArrayList<>();

        //For each key get all the states.
        for (String key : mNextStates.keySet())
        {
            allNextStates.addAll(mNextStates.get(key));
        }

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

        //Get all states from transition key if not null
        if (mNextStates.get(transition) != null)
        nextStates.addAll(mNextStates.get(transition));

        return nextStates;
    }

    /**
     * Find what transition is used to get from this state to another given state.
     * @param state requested state
     * @return transition to that state (empty string if no transition) (only returns one of the possibilities)
     */
    public String GetTransitionTo(State state)
    {
        for (String transition : mNextStates.keySet())
        {
            if (mNextStates.get(transition).contains(state))
            {
                return transition;
            }
        }

        return "";
    }

    /**
     * Getter method for label.
     * @return the label
     */
    public String GetLabel()
    {
        return mLabel;
    }

    /**
     * Getter method for isAcceptState.
     * @return true if this state is accept state
     */
    public boolean IsAcceptState()
    {
        return mIsAcceptState;
    }

    public State Merge(Set<String> alphabet, State other)
    {
        //call mergeHelper which recursively initializes all states using the transition function
        Set<State> states = new TreeSet<>();
        MergeHelper(alphabet, null, null, states, other);

        return new State("0 0", false);
    }

    private void MergeHelper(Set<String> alphabet, State previousState, String transition, Set<State> states, State other)
    {
        //create new merged state and add it to states
        boolean newIsAcceptState = IsAcceptState() && other.IsAcceptState();
        String newLabel = GetLabel() + " " + other.GetLabel();
        State newState = new State(newLabel, newIsAcceptState);
        states.add(newState);

        //adding the transition
        if (previousState != null) {
            previousState.AddNextState(transition, newState);
        }

        //recursively create states from transition function
        //if states already exists in states don't recurse
        //for (State thisNextState : GetAllNextStates())
        //{
        //    for (State otherNextState : GetAllNextStates())
        //    {
        //        //if the state with two merged labels doesn't exist yet
        //        String recursiveTestLabel = thisNextState.GetLabel() + " " + otherNextState.GetLabel();
        //        if (!states.contains(new State(thisNextState.GetLabel() + " " + otherNextState.GetLabel(), false)))
        //        {
        //            thisNextState.MergeHelper(newState, null, states, otherNextState);
        //        }
        //    }

        //recursively create states
        //loop over alphabet and get allnextstates for both this and other
        for (String letter : alphabet)
        {

        }
    }
}
