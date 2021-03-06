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
     * getter for keys from transitionfunction
     * @return the set of transition letters available
     */
    public Set<String> GetTransitions()
    {
        return mNextStates.keySet();
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
        if (!mNextStates.keySet().contains(transition))
        {
            mNextStates.put(transition, new ArrayList<>());
        }

        //Add state to the list
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

    /**
     * Does A intersection B
     * @param A first State
     * @param B Second State
     * @return A intersection B
     * @author Pieter-Jan Lavaerts
     */
    public static State Intersect(State A, State B)
    {
        //initialize the map for recursively created states
        Map<String, State> states = new HashMap<>();

        return IntersectionRecursion(states, null, null, A, B);
    }

    /**
     * initialize a new state for intersection without transitions and add it to states map
     * @param A the first state
     * @param B the second state
     * @param states map that holds recursively created states
     * @return a new state with label A.GetLabel() B.GetLabel() and acceptness A.IsAcceptState() && B.IsAcceptState()
     * @author Pieter-Jan Lavaerts
     */
    private static State initNewState(Map<String, State> states, State A, State B)
    {
        String newLabel = A.GetLabel() + " " + B.GetLabel();
        boolean newIsAccept = A.IsAcceptState() && B.IsAcceptState();
        State newState = new State(newLabel, newIsAccept);

        states.put(newLabel, newState);

        return newState;
    }

    /**
     * Create A intersection B unless it already exists and recursively do the children
     * @param states a list of states already initialized in previous calls
     * @param from the previous state we came from for the transition
     * @param transition the letter we used to come here
     * @param A a state you want to intersect
     * @param B another state you want to intersect
     * @author Pieter-Jan Lavaerts
     */
    private static State IntersectionRecursion(Map<String, State> states,
                                               State from,
                                               String transition,
                                               State A,
                                               State B)
    {
        //check if the state "A B" exists
        //if not create it and add it to states
        String newLabel = A.GetLabel() + " " + B.GetLabel();
        if (!states.keySet().contains(newLabel)) {
            State newState = initNewState(states, A, B);

            //recursieve calls

            //letter transitions:
            //loop over letters in A.alfabet intersection B.alfabet
            Set<String> transitions = new HashSet<>();
            transitions.addAll(A.GetTransitions());
            transitions.retainAll(B.GetTransitions());
            transitions.remove("$");
            for (String letter : transitions)
            {
                //loop over A.getNetxStatesFor(letter)
                for (State nextA : A.GetNextStatesFor(letter))
                {
                    //loop over B.getNextStatesFor(letter)
                    for (State nextB : B.GetNextStatesFor(letter))
                    {
                        //call unionrecursion recursively with from: newstate, nextA, nextB
                        IntersectionRecursion(states, newState, letter, nextA, nextB);
                    }
                }
            }

            //epsilon transitions:
            //loop over states in A.getNextStatesFor($)
            for (State nextA : A.GetNextStatesFor("$"))
            {
                //call intersectionrecursion recursively with from: newState, nextA, B
                IntersectionRecursion(states, newState, "$", nextA, B);
            }

            //loop over states in B.getNextStatesFor($)
            for (State nextB : B.GetNextStatesFor("$"))
            {
                //call intersectionrecursion recursively with from: newState, A, nextB
                IntersectionRecursion(states, newState, "$", A, nextB);
            }
        }

        //initialize newState
        State newState = states.get(newLabel);

        if (from == null) //the start state
        {
           return newState;
        }
        else
        {
            //add the transition from -> newState
            from.AddNextState(transition, newState);
            return null;
        }
    }
}
