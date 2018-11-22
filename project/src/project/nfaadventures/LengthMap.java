package project.nfaadventures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * a map from states to numbers that represent their position in the automaton
 * @author Pieter-JanLavaerts (prototypes)
 * @author Jeroen Peeters 1643057
 */
public class LengthMap
{
    private Map<State, Integer> mLengthMap = new TreeMap<>();
    private State mStartState;
    /**
     * Constructor, initializes the map.
     * @param startState
     */
    public LengthMap(State startState)
    {
        //Remember start state
        mStartState = startState;

        //Generate values for the lengthmap
        GenerateMap(startState, 0);
    }

    /**
     * Generates the map with correct lengths recursively.
     * @param state the current state
     * @param length the current length from start to this state
     */
    private void GenerateMap(State state, Integer length)
    {
        // If it exists it must have a lower or equal value as current length. (already shortest path)
        if (! (mLengthMap.containsKey(state) && mLengthMap.get(state) <= length))
        {
            //Add as key with current length or replace if already exists.
            if (mLengthMap.containsKey(state))
            {
                mLengthMap.replace(state, length);
            }
            else
            {
                mLengthMap.put(state, length);
            }

            //Recursive call to all next states with length + 1
            for (State nextState : state.GetAllNextStates())
            {
                GenerateMap(nextState, length + 1);
            }
        }
    }

    /**
     * Get a list of states (start to accept state) that is the shortest list.
     * @return list of states that's shortestpath (start to accept) (empty if no path)
     */
    public List<State> GetShortestPathAccept()
    {
        List<State> shortestPath = new ArrayList<>();

        //TODO: Implement getShortestPathAccept
        //Find accept state with lowest value.
        int distance = 0;
        State shortestState = null;
        for (State state : mLengthMap.keySet())
        {
            if (state.IsAcceptState())
            {
                if (shortestState == null || distance > mLengthMap.get(state))
                {
                    shortestState = state;
                    distance = mLengthMap.get(state);
                }
            }
        }

        //Get path to this state
        if (shortestState != null)
        {
            shortestPath = GetPathFromStart(shortestState);
        }

        return shortestPath;
    }

    /**
     * Get a list of states (start to non-accept state) that is the shortest list.
     * @return list of these states in order starting with start state. (empty if no path)
     */
    public List<State> GetShortestPathFail()
    {
        List<State> shortestPath = new ArrayList<>();

        //Find non-accept state with lowest value.
        int distance = 0;
        State shortestState = null;
        for (State state : mLengthMap.keySet())
        {
            if (! state.IsAcceptState())
            {
                if (shortestState == null || distance > mLengthMap.get(state))
                {
                    shortestState = state;
                    distance = mLengthMap.get(state);
                }
            }
        }

        //Get path to this state (shortest path).
        if (shortestState != null)
        {
            shortestPath = GetPathFromStart(shortestState);
        }

        return shortestPath;
    }

    /**
     * Get a list of states that gives the shortest path from one state to another.
     * @param end the end of the path
     * @return shortest path as list of states
     */
    private List<State> GetPathFromStart(State end)
    {
        //Get value of end state. (If 0 it's the start state -> return list with only start state)
        Integer currentValue = mLengthMap.get(end);
        if (currentValue <= 0)
        {
            List<State> shortestPath = new ArrayList<>();
            shortestPath.add(end);
            return shortestPath;
        }

        //Get all states with a value that is one lower.
        List<State> possibleStates = new ArrayList<>();
        for (Map.Entry<State, Integer> entry : mLengthMap.entrySet())
        {
            if (entry.getValue() == currentValue - 1)
            {
                possibleStates.add(entry.getKey());
            }
        }

        //Find a state in this list that has a transition to this end. (only need one, don't need all equal shortest paths)
        Integer i = 0;
        State connectedState = possibleStates.get(i++);
        while (i < possibleStates.size() && !(connectedState.GetAllNextStates().contains(end)))
        {
            connectedState = possibleStates.get(i++);
        }

        //Get shortest path from start to this state.
        List<State> shortestPath = GetPathFromStart(connectedState);

        //Add end state to the end of this path to complete it
        shortestPath.add(end);

        //Return the shortest path.
        return shortestPath;
    }
}
