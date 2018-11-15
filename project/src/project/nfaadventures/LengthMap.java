package project.nfaadventures;

import java.util.ArrayList;
import java.util.List;

/**
 * a map from states to numbers that represent their position in the automaton
 * @author Pieter-JanLavaerts
 */
public class LengthMap
{
    /**
     * Constructor
     * @param startState
     */
    public LengthMap(State startState)
    {

    }

    /**
     *
     * @return list of states that's shortestpath
     */
    public List<State> getShortestPathAccept()
    {
        List<State> shortestPath = new ArrayList<>();

        //TODO: Implement getShortestPathAccept

        return shortestPath;
    }

    /**
     *
     * @return
     */
    public List<State> getShortestPathFail()
    {
        List<State> shortestPath = new ArrayList<>();

        //TODO: Implement getShortestPathFail

        return shortestPath;
    }
}
